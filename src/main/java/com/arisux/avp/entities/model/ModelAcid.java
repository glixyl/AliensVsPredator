package com.arisux.avp.entities.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

public class ModelAcid extends ModelBase
{
	private Tessellator tessellator = Tessellator.instance;
	private float modelTextureMap;
	private float boxList;

	public void renderModel(float scale, float var2, float var3, float var4, float var5)
	{
		this.boxList = 0.0F;
		this.modelTextureMap = 1.0F;
		GL11.glPushMatrix();
		{
			Vertex t1 = new Vertex(this, 1.0D, 0.0D, 0.0D);
			Vertex t2 = new Vertex(this, 0.0D, 1.0D, 0.0D);
			Vertex t3 = new Vertex(this, 0.0D, 0.0D, 1.0D);
			Vertex t4 = new Vertex(this, 0.5D, 0.5D, 0.0D);
			Vertex t5 = new Vertex(this, 0.0D, 0.5D, 0.5D);
			Vertex t6 = new Vertex(this, 0.5D, 0.0D, 0.5D);
			Vertex t7 = new Vertex(this, 0.75D, 0.25D, 0.0D);
			Vertex t8 = new Vertex(this, 0.5D, 0.25D, 0.25D);
			Vertex t9 = new Vertex(this, 0.75D, 0.0D, 0.25D);
			Vertex t10 = new Vertex(this, 0.0D, 0.75D, 0.25D);
			Vertex t11 = new Vertex(this, 0.25D, 0.5D, 0.25D);
			Vertex t12 = new Vertex(this, 0.25D, 0.75D, 0.0D);
			Vertex t13 = new Vertex(this, 0.25D, 0.0D, 0.75D);
			Vertex t14 = new Vertex(this, 0.25D, 0.25D, 0.5D);
			Vertex t15 = new Vertex(this, 0.0D, 0.25D, 0.75D);
			GL11.glScalef(scale, scale, scale);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
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

			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}
		GL11.glPopMatrix();
	}

	private void addTri(Vertex x, Vertex y, Vertex z)
	{
		tessellator.startDrawing(6);
		tessellator.setColorRGBA_F(this.boxList, 1F, 0F, this.modelTextureMap);
		this.addVerticeAcid(x);
		this.addVerticeAcid(y);
		this.addVerticeAcid(z);
		tessellator.draw();
	}

	private void addVerticeAcid(Vertex model)
	{
		tessellator.addVertex(model.x, model.y, model.z);
	}
}
