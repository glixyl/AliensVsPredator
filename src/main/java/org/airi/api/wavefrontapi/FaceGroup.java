package com.arisux.airi.api.wavefrontapi;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.render.Color;

import net.minecraft.util.ResourceLocation;

public class FaceGroup
{
    public String mtlName;
    public ResourceLocation resource;
    public ArrayList<Face> faces = new ArrayList<Face>();
    public boolean listReady = false;
    public int glList;
    public Color color;

    public void bindTexture()
    {
        if (resource != null)
        {
            RenderUtil.bindTexture(resource);
        }
    }

    public void draw()
    {
        if (resource != null)
        {
            bindTexture();
            drawNoBind();
        }
        else
        {
            GlStateManager.disableTexture2d();
            drawNoBind();
            GlStateManager.enableTexture2d();
        }
    }

    public void drawNoBind()
    {
        if (listReady == false)
        {
            listReady = true;
            glList = GL11.glGenLists(1);

            GlStateManager.newList(glList, GL11.GL_COMPILE);
            this.drawVertex();
            GlStateManager.endList();
        }

        GlStateManager.callList(glList);
    }

    private void drawVertex()
    {
        int mode = 0;

        for (Face f : faces)
        {
            if (f.vertexNbr != mode)
            {
                if (mode != 0)
                {
                    GL11.glEnd();
                }

                switch (f.vertexNbr)
                {
                    case 3:
                        GlStateManager.begin(GL11.GL_TRIANGLES);
                        break;
                    case 4:
                        GlStateManager.begin(GL11.GL_QUADS);
                        break;
                    case 6:
                        GlStateManager.begin(GL11.GL_TRIANGLE_STRIP);
                        break;
                    case 8:
                        GlStateManager.begin(GL11.GL_TRIANGLE_STRIP);
                        break;
                }

                mode = f.vertexNbr;
            }

            if (this.color != null)
            {
                GlStateManager.color(this.color.r, this.color.g, this.color.b);
            }

            GlStateManager.normal(f.normal.x, f.normal.y, f.normal.z);

            for (int idx = 0; idx < mode; idx++)
            {
                if (f.uv[idx] != null)
                {
                    GlStateManager.texCoord(f.uv[idx].u, f.uv[idx].v);
                }

                GlStateManager.vertex(f.vertex[idx].x, f.vertex[idx].y, f.vertex[idx].z);
            }
        }

        if (mode != 0)
        {
            GlStateManager.end();
        }
    }
}
