package org.avp.entities.model;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ModelBaseWrapper;
import com.arisux.airi.lib.client.render.Color;
import com.arisux.airi.lib.client.render.Vertex;

import net.minecraft.client.renderer.Tessellator;

public class ModelPlasma extends ModelBaseWrapper
{
    private Tessellator tessellator = Tessellator.instance;
    private Color color;
    private float scale;
    public boolean drawInternalVertices = true;

    private Vertex t1 = new Vertex(1.0F, 0.0F, 0.0F).smooth(),
        t2 = new Vertex(0.0F, 1.0F, 0.0F).smooth(),
        t3 = new Vertex(0.0F, 0.0F, 1.0F).smooth(),
        t4 = new Vertex(0.5F, 0.5F, 0.0F).smooth(),
        t5 = new Vertex(0.0F, 0.5F, 0.5F).smooth(),
        t6 = new Vertex(0.5F, 0.0F, 0.5F).smooth(),
        t7 = new Vertex(0.75F, 0.25F, 0.0F).smooth(),
        t8 = new Vertex(0.5F, 0.25F, 0.25F).smooth(),
        t9 = new Vertex(0.75F, 0.0F, 0.25F).smooth(),
        t10 = new Vertex(0.0F, 0.75F, 0.25F).smooth(),
        t11 = new Vertex(0.25F, 0.5F, 0.25F).smooth(),
        t12 = new Vertex(0.25F, 0.75F, 0.0F).smooth(),
        t13 = new Vertex(0.25F, 0.0F, 0.75F).smooth(),
        t14 = new Vertex(0.25F, 0.25F, 0.5F).smooth(),
        t15 = new Vertex(0.0F, 0.25F, 0.75F).smooth();

    private void addTri(Vertex vertex1, Vertex vertex2, Vertex vertex3)
    {
        tessellator.startDrawing(6);
        tessellator.setColorRGBA_F(this.color.r, this.color.g, this.color.b, this.color.a);
        tessellator.addVertex(vertex1.x, vertex1.y, vertex1.z);
        tessellator.addVertex(vertex2.x, vertex2.y, vertex2.z);
        tessellator.addVertex(vertex3.x, vertex3.y, vertex3.z);

        if (drawInternalVertices)
        {
            tessellator.addVertex(vertex3.x, vertex3.y, vertex3.z);
            tessellator.addVertex(vertex2.x, vertex2.y, vertex2.z);
            tessellator.addVertex(vertex1.x, vertex1.y, vertex1.z);
        }
        tessellator.draw();
    }

    @Override
    protected void render(IRenderObject renderObject, float boxTranslation)
    {
        GlStateManager.pushMatrix();
        {
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.disable(GL11.GL_TEXTURE_2D);
            GlStateManager.disableLightMapping();
            GlStateManager.disableLight();
            GlStateManager.enable(GL11.GL_BLEND);
            GlStateManager.blendFunc(GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE);

            for (int rZ = 0; rZ < 2; ++rZ)
            {
                GlStateManager.rotate(rZ * 180, 0.0F, 0.0F, 1.0F);

                for (int rY = 0; rY < 4; ++rY)
                {
                    GlStateManager.pushMatrix();
                    {
                        GlStateManager.rotate(rY * 90, 0.0F, 1.0F, 0.0F);
                        this.addTri(t2, t10, t12);
                        this.addTri(t10, t5, t11);
                        this.addTri(t12, t11, t4);
                        this.addTri(t10, t11, t12);
                        this.addTri(t1, t7, t9);
                        this.addTri(t7, t4, t8);
                        this.addTri(t9, t8, t6);
                        this.addTri(t7, t8, t9);
                        this.addTri(t3, t13, t15);
                        this.addTri(t13, t6, t14);
                        this.addTri(t15, t14, t5);
                        this.addTri(t13, t14, t15);
                        this.addTri(t5, t14, t11);
                        this.addTri(t4, t11, t8);
                        this.addTri(t6, t8, t14);
                        this.addTri(t8, t11, t14);
                    }
                    GlStateManager.popMatrix();
                }
            }
            GlStateManager.enableLight();
            GlStateManager.enableLightMapping();
            GlStateManager.enable(GL11.GL_TEXTURE_2D);
            GlStateManager.disable(GL11.GL_BLEND);
        }
        GlStateManager.popMatrix();
    }
    
    public void setColor(Color color)
    {
        this.color = color;
    }
    
    public void setScale(float scale)
    {
        this.scale = scale;
    }
}
