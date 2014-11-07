package com.arisux.avp.entities.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderLib;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAcidPool;

public class RenderFXAcid extends Render
{
	private static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_FX_ACID);

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
	{
		this.shadowSize = 1.0F;
		GL11.glPushMatrix();
		this.renderImage(var1, var2, var4, var6, var8, var9, AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_FX_ACID);
		this.shadowSize = 0.0F;
		GL11.glPopMatrix();
	}

	private void renderImage(Entity var1, double var2, double var4, double var6, float var8, float var9, String var10)
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		// GL11.glDisable(GL11.GL_LIGHTING);
		// GL11.glBlendFunc(GL11.GL_SRC_ALPHA,
		// GL11.GL_ONE_MINUS_SRC_ALPHA);
		RenderLib.glDisableLightMapping();
		this.bindTexture(resourceLocation);
		World worldObj = this.renderManager.worldObj;
		float var13 = this.shadowSize;
		double var14 = var1.lastTickPosX + (var1.posX - var1.lastTickPosX) * (double) var9;
		double var16 = var1.lastTickPosY + (var1.posY - var1.lastTickPosY) * (double) var9 + (double) var1.getShadowSize();
		double var18 = var1.lastTickPosZ + (var1.posZ - var1.lastTickPosZ) * (double) var9;
		int var20 = MathHelper.floor_double(var14 - (double) var13);
		int var21 = MathHelper.floor_double(var14 + (double) var13);
		int var22 = MathHelper.floor_double(var16 - (double) var13);
		int var23 = MathHelper.floor_double(var16);
		int var24 = MathHelper.floor_double(var18 - (double) var13);
		int var25 = MathHelper.floor_double(var18 + (double) var13);
		double var26 = var2 - var14;
		double var28 = var4 - var16;
		double var30 = var6 - var18;
		Tessellator var32 = Tessellator.instance;
		var32.startDrawingQuads();
		double var33 = (double) ((EntityAcidPool) var1).strength / 100.0D;

		for (int var35 = var20; var35 <= var21; ++var35)
		{
			for (int var36 = var22; var36 <= var23; ++var36)
			{
				for (int var37 = var24; var37 <= var25; ++var37)
				{
					Block var38 = worldObj.getBlock(var35, var36 - 1, var37);

					if (var38 != Blocks.air && worldObj.getBlockLightValue(var35, var36, var37) > 3)
					{
						this.renderImageOnBlock(var38, var2, var4 + (double) var1.getShadowSize(), var6, var35, var36, var37, var8, var13, var26, var28 + (double) var1.getShadowSize(), var30, var33);
					}
				}
			}
		}

		var32.draw();
		GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		RenderLib.glEnableLightMapping();
		GL11.glPopMatrix();
	}

	private void renderImageOnBlock(Block var1, double var2, double var4, double var6, int var8, int var9, int var10, float var11, float var12, double var13, double var15, double var17, double var19)
	{
		Tessellator var21 = Tessellator.instance;

		if (var1.renderAsNormalBlock())
		{
			double var22 = var19;

			if (var19 > 1.0D)
			{
				var22 = 1.0D;
			}

			var21.setColorRGBA_F(0.0F, 1.0F, 0.0F, (float) var22);
			double var24 = (double) var8 + var1.getBlockBoundsMinX() + var13;
			double var26 = (double) var8 + var1.getBlockBoundsMaxX() + var13;
			double var28 = (double) var9 + var1.getBlockBoundsMinY() + var15 + 0.015625D;
			double var30 = (double) var10 + var1.getBlockBoundsMinZ() + var17;
			double var32 = (double) var10 + var1.getBlockBoundsMaxZ() + var17;
			float var34 = (float) ((var2 - var24) / 2.0D / (double) var12 + 0.5D);
			float var35 = (float) ((var2 - var26) / 2.0D / (double) var12 + 0.5D);
			float var36 = (float) ((var6 - var30) / 2.0D / (double) var12 + 0.5D);
			float var37 = (float) ((var6 - var32) / 2.0D / (double) var12 + 0.5D);
			var21.addVertexWithUV(var24, var28, var30, (double) var34, (double) var36);
			var21.addVertexWithUV(var24, var28, var32, (double) var34, (double) var37);
			var21.addVertexWithUV(var26, var28, var32, (double) var35, (double) var37);
			var21.addVertexWithUV(var26, var28, var30, (double) var35, (double) var36);
		}
	}

	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}