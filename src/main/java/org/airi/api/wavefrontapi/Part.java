package com.arisux.airi.api.wavefrontapi;

import java.util.ArrayList;
import java.util.Hashtable;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.render.UV;
import com.arisux.airi.lib.client.render.Vertex;

public class Part
{
    public ArrayList<FaceGroup> faceGroup = new ArrayList<FaceGroup>();
    public Hashtable<String, Float> nameToFloatHash = new Hashtable<String, Float>();
    private ArrayList<Vertex> vertices;
    private ArrayList<UV> uv;

    private double minX;
    private double minY;
    private double minZ;

    private double maxX;
    private double maxY;
    private double maxZ;

    private float oX;
    private float oY;
    private float oZ;

    private float oX2;
    private float oY2;
    private float oZ2;

    void addVertex(Vertex v)
    {
        vertices.add(v);
        minX = Math.min(minX, v.x);
        minY = Math.min(minY, v.y);
        minZ = Math.min(minZ, v.z);
        maxX = Math.max(maxX, v.x);
        maxY = Math.max(maxY, v.y);
        maxZ = Math.max(maxZ, v.z);
    }

    public Part(ArrayList<Vertex> vertex, ArrayList<UV> uv)
    {
        this.vertices = vertex;
        this.uv = uv;
    }

    public float getFloat(String name)
    {
        return nameToFloatHash.get(name);
    }

    public void draw(float angle, float x, float y, float z)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(oX, oY, oZ);
            GlStateManager.rotate(angle, x, y, z);
            GlStateManager.translate(-oX, -oY, -oZ);
            this.draw();
        }
        GlStateManager.popMatrix();
    }

    public void draw(float angle, float x, float y, float z, float angle2, float x2, float y2, float z2)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(oX, oY, oZ);
            GlStateManager.rotate(angle, x, y, z);
            GlStateManager.translate(oX2, oY2, oZ2);
            GlStateManager.rotate(angle2, x2, y2, z2);
            GlStateManager.translate(-oX2, -oY2, -oZ2);
            GlStateManager.translate(-oX, -oY, -oZ);
            this.draw();
        }
        GlStateManager.popMatrix();
    }

    public void drawNoBind(float angle, float x, float y, float z)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(oX, oY, oZ);
            GlStateManager.rotate(angle, x, y, z);
            GlStateManager.translate(-oX, -oY, -oZ);
            this.drawNoBind();
        }
        GlStateManager.popMatrix();
    }

    public void drawNoBind()
    {
        for (FaceGroup fg : faceGroup)
        {
            fg.drawNoBind();
        }
    }

    public void draw()
    {
        for (FaceGroup fg : faceGroup)
        {
            fg.draw();
        }
    }

    public ArrayList<UV> getUV()
    {
        return this.uv;
    }

    public ArrayList<FaceGroup> getFaceGroup()
    {
        return this.faceGroup;
    }

    public Hashtable<String, Float> getNameToFloatHash()
    {
        return this.nameToFloatHash;
    }

    public ArrayList<Vertex> getVertices()
    {
        return this.vertices;
    }

    public void setOriginX(float ox)
    {
        this.oX = ox;
    }

    public float getOriginX()
    {
        return this.oX;
    }

    public void setOriginX2(float ox2)
    {
        this.oX2 = ox2;
    }

    public float getOriginX2()
    {
        return this.oX2;
    }

    public void setOriginY(float oy)
    {
        this.oY = oy;
    }

    public float getOriginY()
    {
        return this.oY;
    }

    public void setOriginY2(float oy2)
    {
        this.oY2 = oy2;
    }

    public float getOriginY2()
    {
        return this.oY2;
    }

    public void setOriginZ(float oz)
    {
        this.oZ = oz;
    }

    public float getOriginZ()
    {
        return this.oZ;
    }

    public void setOriginZ2(float oz2)
    {
        this.oZ2 = oz2;
    }

    public float getOriginZ2()
    {
        return this.oZ2;
    }

    public void setMaxX(double maxX)
    {
        this.maxX = maxX;
    }

    public double getMaxX()
    {
        return this.maxX;
    }

    public void setMaxY(double maxY)
    {
        this.maxY = maxY;
    }

    public double getMaxY()
    {
        return this.maxY;
    }

    public void setMaxZ(double maxZ)
    {
        this.maxZ = maxZ;
    }

    public double getMaxZ()
    {
        return this.maxZ;
    }

    public void setMinX(double minX)
    {
        this.minX = minX;
    }

    public double getMinX()
    {
        return this.minX;
    }

    public void setMinY(double minY)
    {
        this.minY = minY;
    }

    public double getMinY()
    {
        return this.minY;
    }

    public void setMinZ(double minZ)
    {
        this.minZ = minZ;
    }

    public double getMinZ()
    {
        return this.minZ;
    }
}
