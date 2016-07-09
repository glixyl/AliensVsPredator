package com.arisux.airi.api.wavefrontapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.client.render.Color;
import com.arisux.airi.lib.client.render.Normal;
import com.arisux.airi.lib.client.render.UV;
import com.arisux.airi.lib.client.render.Vertex;

import net.minecraft.util.ResourceLocation;

public class WavefrontModel
{
    private String pathName;
    private String mtlName;
    private String modid;
    private String directory;

    public ArrayList<Vertex> vertex = new ArrayList<Vertex>();
    public ArrayList<UV> uv = new ArrayList<UV>();
    public Hashtable<String, String> nameToStringHash = new Hashtable<String, String>();
    public Hashtable<String, Part> nameToPartHash = new Hashtable<String, Part>();

    public float xDim, yDim, zDim;
    public float xMin, yMin, zMin;
    public float xMax, yMax, zMax;
    public float dimMax, dimMaxInv;

    public ResourceLocation getAlternativeTexture(String name)
    {
        ResourceLocation resource = new ResourceLocation(modid, directory.substring(1) + name);
        return resource;
    }

    public boolean load(String modid, String path)
    {
        File fileModel = new File(path + ".obj");
        File fileTexture = new File(path + ".mtl");
        File fileOrigins = new File(path + ".ori");
        int lastSlashId = path.lastIndexOf('/');
        this.modid = modid;
        this.directory = path.substring(0, lastSlashId + 1);
        this.pathName = path.substring(lastSlashId + 1, path.length());
        Part part = null;
        FaceGroup fg = null;

        try
        {
            this.loadOBJ(fileModel, path, part, fg);
            part = null;
            this.loadMTL(fileTexture, path);
            part = null;
        }
        catch (Exception e)
        {
            AIRI.logger.warning("[WavefrontAPI] Error loading model for mod with id %s: %s", modid, path);
            e.printStackTrace();
            return false;
        }

        try
        {
            this.loadORI(fileOrigins, part);
        }
        catch (Exception e)
        {
            AIRI.logger.warning("[WavefrontAPI] Error (%s) loading origins for model for mod with id %s: %s", e, modid, path);
        }

        xDim = xMax - xMin;
        yDim = yMax - yMin;
        zDim = zMax - zMin;

        dimMax = Math.max(Math.max(xMax, yMax), zMax);
        dimMaxInv = 1.0f / dimMax;

        AIRI.logger.info("[WavefrontAPI] Loaded wavefront model for mod with id %s: %s", this.modid, this.pathName);

        return true;
    }

    private boolean loadOBJ(File fileModel, String path, Part part, FaceGroup fg) throws Exception
    {
        InputStream stream = new FileInputStream(fileModel);

        if (stream == null || fileModel == null || path == null)
        {
            AIRI.logger.bug("OBJ Loading Failed: " + path);
            stream.close();
            return false;
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        String line;

        while ((line = bufferedReader.readLine()) != null)
        {
            String[] words = line.split(" ");
            String command = words[0];

            if (command.equals("o"))
            {
                part = new Part(vertex, uv);
                nameToPartHash.put(words[1], part);
            }
            else if (command.equals("v"))
            {
                Vertex v;
                part.addVertex(v = new Vertex(Float.parseFloat(words[1]), Float.parseFloat(words[2]), Float.parseFloat(words[3])));

                xMin = Math.min(xMin, v.x);
                yMin = Math.min(yMin, v.y);
                zMin = Math.min(zMin, v.z);
                xMax = Math.max(xMax, v.x);
                yMax = Math.max(yMax, v.y);
                zMax = Math.max(zMax, v.z);
            }
            else if (command.equals("vt"))
            {
                part.getUV().add(new UV(Float.parseFloat(words[1]), 1 - Float.parseFloat(words[2])));
            }
            else if (command.equals("f"))
            {
                int vertexNbr = words.length - 1;
                if (vertexNbr == 3)
                {
                    Vertex[] verticeId = new Vertex[vertexNbr];
                    UV[] uvId = new UV[vertexNbr];
                    for (int idx = 0; idx < vertexNbr; idx++)
                    {
                        String[] id = words[idx + 1].split("/");

                        verticeId[idx] = part.getVertices().get(Integer.parseInt(id[0]) - 1);
                        if (id.length > 1 && !id[1].equals(""))
                        {
                            uvId[idx] = part.getUV().get(Integer.parseInt(id[1]) - 1);
                        }
                        else
                        {
                            uvId[idx] = null;
                        }
                    }

                    fg.faces.add(new Face(verticeId, uvId, new Normal(verticeId[0], verticeId[1], verticeId[2])));
                }
            }
            else if (command.equals("mtllib"))
            {
                mtlName = words[1];
            }
            else if (command.equals("usemtl"))
            {
                fg = new FaceGroup();
                fg.mtlName = words[1];

                if (part != null && part.faceGroup != null)
                {
                    part.faceGroup.add(fg);
                }
            }
        }
        bufferedReader.close();

        return true;
    }

    private boolean loadMTL(File fileTexture, String path) throws Exception
    {
        InputStream stream = new FileInputStream(fileTexture);

        System.out.println("Loading MTL: " + fileTexture);

        if (stream == null || fileTexture == null || path == null)
        {
            AIRI.logger.bug("MTL Loading failed: " + path);
            stream.close();
            return false;
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        String mtlName = "";
        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            String[] words = line.split(" ");
            String command = words[0];

            if (command.equals("newmtl"))
            {
                mtlName = words[1];

            }
            else if (command.equals("map_Kd"))
            {
                for (Part partPtr : nameToPartHash.values())
                {
                    for (FaceGroup faceGroup : partPtr.faceGroup)
                    {
                        if (faceGroup.mtlName != null && faceGroup.mtlName.equals(mtlName))
                        {
                            faceGroup.resource = new ResourceLocation(modid, "models/" + directory + words[1]);
                        }
                    }
                }
            }
            else if (command.equals("Kd"))
            {
                for (Part partPtr : nameToPartHash.values())
                {
                    for (FaceGroup faceGroup : partPtr.faceGroup)
                    {
                        if (faceGroup.mtlName != null && faceGroup.mtlName.equals(mtlName))
                        {
                            faceGroup.color = new Color(Float.parseFloat(words[1]), Float.parseFloat(words[2]), Float.parseFloat(words[3]), 1F);
                        }
                    }
                }
            }
        }
        bufferedReader.close();

        return true;
    }

    private void loadORI(File fileOrigins, Part part) throws Exception
    {
        InputStream stream = new FileInputStream(fileOrigins);

        if (stream != null)
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] words = line.split(" ");
                String command = words[0];

                if (command.equals("o"))
                {
                    part = nameToPartHash.get(words[1]);
                }
                else if (command.equals("f"))
                {
                    String key = words[1];
                    float value = Float.valueOf(words[2]);

                    if (key.equals("originX"))
                    {
                        part.setOriginX(value);
                    }
                    else if (key.equals("originY"))
                    {
                        part.setOriginY(value);
                    }
                    else if (key.equals("originZ"))
                    {
                        part.setOriginZ(value);
                    }
                    else if (key.equals("originX2"))
                    {
                        part.setOriginX2(value);
                    }
                    else if (key.equals("originY2"))
                    {
                        part.setOriginY2(value);
                    }
                    else if (key.equals("originZ2"))
                    {
                        part.setOriginZ2(value);
                    }
                    else
                    {
                        part.nameToFloatHash.put(key, Float.valueOf(value));
                    }
                }
                else if (command.equals("s"))
                {
                    nameToStringHash.put(words[1], words[2]);
                }
            }

            bufferedReader.close();
        }
    }

    public void draw(String part)
    {
        Part partPtr = getPart(part);

        if (partPtr != null)
        {
            partPtr.draw();
        }
    }

    public Part getPart(String part)
    {
        return this.nameToPartHash.get(part);
    }

    public String getString(String name)
    {
        return this.nameToStringHash.get(name);
    }

    public String getMtlName()
    {
        return this.mtlName;
    }
}
