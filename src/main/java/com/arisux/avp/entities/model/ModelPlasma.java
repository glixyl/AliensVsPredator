package com.arisux.avp.entities.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.*;
import com.arisux.airi.lib.RenderUtil.Color;
import com.arisux.airi.lib.RenderUtil.Vertex;

public class ModelPlasma extends ModelBase
{
	private Tessellator tessellator = Tessellator.instance;
	private Color color;
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

	public void render(float scale, Color color)
	{
		this.color = color;
		this.render(scale, color.r, color.g, color.b, color.a);
	}

	public void render(float scale, float r, float g, float b, float a)
	{
		this.color = new Color(r, g, b, a);

		GL11.glPushMatrix();
		{
			GL11.glScalef(scale, scale, scale);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			RenderUtil.glDisableLightMapping();
			RenderUtil.glDisableLight();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);

			for (int rZ = 0; rZ < 2; ++rZ)
			{
				GL11.glRotatef(rZ * 180, 0.0F, 0.0F, 1.0F);

				for (int rY = 0; rY < 4; ++rY)
				{
					GL11.glPushMatrix();
					{
						GL11.glRotatef(rY * 90, 0.0F, 1.0F, 0.0F);
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
					GL11.glPopMatrix();
				}
			}
			RenderUtil.glEnableLight();
			RenderUtil.glEnableLightMapping();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}
		GL11.glPopMatrix();
	}

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
}
