package com.arisux.avp.entities.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

public class ModelPlasma extends ModelBase
{
	/**
	 * This is a list of all the boxes (ModelRenderer.class) in the current
	 * model.
	 */
	private float boxList;
	private float g;
	private float b;

	/** A mapping for all texture offsets */
	private float modelTextureMap;

	public void renderModel(float var1, float var2, float var3, float var4, float var5)
	{
		this.boxList = var2 + 0.06F;
		this.g = var3;
		this.b = var4 + 0.3F;
		this.modelTextureMap = var5;
		GL11.glPushMatrix();
		Plasma var6 = new Plasma(this, 1.0D, 0.0D, 0.0D);
		Plasma var7 = new Plasma(this, 0.0D, 1.0D, 0.0D);
		Plasma var8 = new Plasma(this, 0.0D, 0.0D, 1.0D);
		Plasma var9 = new Plasma(this, 0.5D, 0.5D, 0.0D);
		Plasma var10 = new Plasma(this, 0.0D, 0.5D, 0.5D);
		Plasma var11 = new Plasma(this, 0.5D, 0.0D, 0.5D);
		Plasma var12 = new Plasma(this, 0.75D, 0.25D, 0.0D);
		Plasma var13 = new Plasma(this, 0.5D, 0.25D, 0.25D);
		Plasma var14 = new Plasma(this, 0.75D, 0.0D, 0.25D);
		Plasma var15 = new Plasma(this, 0.0D, 0.75D, 0.25D);
		Plasma var16 = new Plasma(this, 0.25D, 0.5D, 0.25D);
		Plasma var17 = new Plasma(this, 0.25D, 0.75D, 0.0D);
		Plasma var18 = new Plasma(this, 0.25D, 0.0D, 0.75D);
		Plasma var19 = new Plasma(this, 0.25D, 0.25D, 0.5D);
		Plasma var20 = new Plasma(this, 0.0D, 0.25D, 0.75D);
		GL11.glScalef(var1, var1, var1);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);

		for (int var21 = 0; var21 < 2; ++var21)
		{
			GL11.glRotatef((float) (var21 * 180), 0.0F, 0.0F, 1.0F);

			for (int var22 = 0; var22 < 4; ++var22)
			{
				GL11.glPushMatrix();
				GL11.glRotatef((float) (var22 * 90), 0.0F, 1.0F, 0.0F);
				this.addTri(var7, var15, var17);
				this.addTri(var15, var10, var16);
				this.addTri(var17, var16, var9);
				this.addTri(var15, var16, var17);
				this.addTri(var6, var12, var14);
				this.addTri(var12, var9, var13);
				this.addTri(var14, var13, var11);
				this.addTri(var12, var13, var14);
				this.addTri(var8, var18, var20);
				this.addTri(var18, var11, var19);
				this.addTri(var20, var19, var10);
				this.addTri(var18, var19, var20);
				this.addTri(var10, var19, var16);
				this.addTri(var9, var16, var13);
				this.addTri(var11, var13, var19);
				this.addTri(var13, var16, var19);
				GL11.glPopMatrix();
			}
		}

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	private void addTri(Plasma var1, Plasma var2, Plasma var3)
	{
		Tessellator var4 = Tessellator.instance;
		var4.startDrawing(6);
		var4.setColorRGBA_F(this.boxList, this.g, this.b, this.modelTextureMap);
		this.addVerticePlasma(var3);
		this.addVerticePlasma(var1);
		this.addVerticePlasma(var2);
		var4.draw();
	}

	private void addVerticePlasma(Plasma var1)
	{
		Tessellator var2 = Tessellator.instance;
		var2.addVertex(var1.x, var1.y, var1.z);
	}
}
