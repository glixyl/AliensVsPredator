package com.arisux.avp.dimension.acheron;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IRenderHandler;

public class SkyProviderAcheron extends IRenderHandler
{
	public int starGLCallList = GLAllocation.generateDisplayLists(3);
	public int glSkyList;

	public SkyProviderAcheron()
	{
		Tessellator tessellator = Tessellator.instance;

		GL11.glNewList(this.starGLCallList, GL11.GL_COMPILE);
		this.renderStars();
		GL11.glEndList();

		this.glSkyList = (this.starGLCallList + 1);
		GL11.glNewList(this.glSkyList, GL11.GL_COMPILE);
		byte var20 = 64;
		int var30 = 256 / var20 + 2;
		float skylineHeight = 18.0F;

		for (int var5 = -var20 * var30; var5 <= var20 * var30; var5 += var20)
		{
			for (int var6 = -var20 * var30; var6 <= var20 * var30; var6 += var20)
			{
				tessellator.startDrawingQuads();
				tessellator.addVertex(var5 + 0, skylineHeight, var6 + 0);
				tessellator.addVertex(var5 + var20, skylineHeight, var6 + 0);
				tessellator.addVertex(var5 + var20, skylineHeight, var6 + var20);
				tessellator.addVertex(var5 + 0, skylineHeight, var6 + var20);
				tessellator.draw();
			}
		}

		GL11.glEndList();
	}

	@Override
	public void render(float renderPartialTicks, WorldClient world, Minecraft mc)
	{
		Tessellator tessellator = Tessellator.instance;

		GlStateManager.disable(GL11.GL_TEXTURE_2D);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GL11.glDepthMask(false);
		GlStateManager.enable(GL11.GL_FOG);
		GL11.glColor3f(0.071F, 0.095F, 0.195F);
		GL11.glCallList(this.glSkyList);
		GlStateManager.disable(GL11.GL_FOG);
		GlStateManager.disable(GL11.GL_ALPHA_TEST);
		GlStateManager.enable(GL11.GL_BLEND);
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(0.0F, 0.5F, 1.0F, 0.15F);
		GL11.glCallList(this.starGLCallList);
		GlStateManager.enable(GL11.GL_TEXTURE_2D);
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, 1);

		GlStateManager.pushMatrix();
		{
			float scale = 5.0F;
			GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.rotate(world.getCelestialAngle(renderPartialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
			RenderUtil.bindTexture(AliensVsPredator.resources().SKY_SUN);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-scale, 150.0D, -scale, 0.0D, 0.0D);
			tessellator.addVertexWithUV(scale, 150.0D, -scale, 1.0D, 0.0D);
			tessellator.addVertexWithUV(scale, 150.0D, scale, 1.0D, 1.0D);
			tessellator.addVertexWithUV(-scale, 150.0D, scale, 0.0D, 1.0D);
			tessellator.draw();
		}
		GlStateManager.popMatrix();

		GlStateManager.disable(GL11.GL_BLEND);
		GlStateManager.enable(GL11.GL_ALPHA_TEST);
		GlStateManager.enable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(true);
		this.renderClouds(renderPartialTicks);
	}

	private void renderStars()
	{
		Random rand = new Random(10842L);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();

		for (int amountOfStars = 0; amountOfStars < 6000; amountOfStars++)
		{
			double var4 = rand.nextFloat() * 2.0F - 1.0F;
			double var6 = rand.nextFloat() * 2.0F - 1.0F;
			double var8 = rand.nextFloat() * 2.0F - 1.0F;
			double var10 = 0.15F + rand.nextFloat() * 0.1F;
			double var12 = var4 * var4 + var6 * var6 + var8 * var8;

			if ((var12 >= 1.0D) || (var12 <= 0.01D))
				continue;
			var12 = 1.0D / Math.sqrt(var12);
			var4 *= var12;
			var6 *= var12;
			var8 *= var12;
			double var14 = var4 * 100.0D;
			double var16 = var6 * 100.0D;
			double var18 = var8 * 100.0D;
			double var20 = Math.atan2(var4, var8);
			double var22 = Math.sin(var20);
			double var24 = Math.cos(var20);
			double var26 = Math.atan2(Math.sqrt(var4 * var4 + var8 * var8), var6);
			double var28 = Math.sin(var26);
			double var30 = Math.cos(var26);
			double var32 = rand.nextDouble() * 3.141592653589793D * 2.0D;
			double var34 = Math.sin(var32);
			double var36 = Math.cos(var32);

			for (int var38 = 0; var38 < 4; var38++)
			{
				double var39 = 0.0D;
				double var41 = ((var38 & 0x2) - 1) * var10;
				double var43 = ((var38 + 1 & 0x2) - 1) * var10;
				double var45 = var41 * var36 - var43 * var34;
				double var47 = var43 * var36 + var41 * var34;
				double var49 = var45 * var28 + var39 * var30;
				double var51 = var39 * var28 - var45 * var30;
				double var53 = var51 * var22 - var47 * var24;
				double var55 = var47 * var22 + var51 * var24;
				tessellator.addVertex(var14 + var53, var16 + var49, var18 + var55);
			}

		}

		tessellator.draw();
	}

	public float getSkyBrightness(float renderPartialTicks)
	{
		float angle = FMLClientHandler.instance().getClient().theWorld.getCelestialAngle(renderPartialTicks);
		float brightness = 1.0F - (MathHelper.sin(angle * 3.141593F * 2.0F) * 2.0F + 0.25F);

		if (brightness < 0.0F)
		{
			brightness = 0.0F;
		}

		if (brightness > 1.0F)
		{
			brightness = 1.0F;
		}

		return brightness * brightness * 1.0F;
	}

	public static float calculateAngleFromPlanet(long worldTime, float renderPartialTicks)
	{
		int remainingTime = (int) (worldTime % 48000L);
		float angle = (remainingTime + renderPartialTicks) / 48000.0F - 0.25F;

		if (angle < 0.0F)
		{
			angle += 1.0F;
		}

		if (angle > 1.0F)
		{
			angle -= 1.0F;
		}

		float var5 = angle;
		angle = 1.0F - (float) ((Math.cos(angle * 3.141592653589793D) + 1.0D) / 2.0D);
		angle = var5 + (angle - var5) / 3.0F;
		return angle;
	}


	public void renderClouds(float renderPartialTicks)
	{
		Minecraft mc = Minecraft.getMinecraft();

		if (mc.gameSettings.shouldRenderClouds())
		{
			GlStateManager.pushMatrix();
			{
				GlStateManager.enable(GL11.GL_FOG);

				if (mc.gameSettings.fancyGraphics)
				{
					this.renderCloudsFancy(renderPartialTicks);
				}
				else
				{
					GlStateManager.disable(GL11.GL_CULL_FACE);
					float f1 = (float) (mc.renderViewEntity.lastTickPosY + (mc.renderViewEntity.posY - mc.renderViewEntity.lastTickPosY) * renderPartialTicks);
					byte b0 = 32;
					int i = 256 / b0;
					Tessellator tessellator = Tessellator.instance;
					RenderUtil.bindTexture(AliensVsPredator.resources().SKY_CLOUDS);
					GlStateManager.enable(GL11.GL_BLEND);
					OpenGlHelper.glBlendFunc(770, 771, 1, 0);
					Vec3 vec3 = mc.theWorld.getCloudColour(renderPartialTicks);
					float f2 = (float) vec3.xCoord;
					float f3 = (float) vec3.yCoord;
					float f4 = (float) vec3.zCoord;
					float f5;

					if (mc.gameSettings.anaglyph)
					{
						f5 = (f2 * 30.0F + f3 * 59.0F + f4 * 11.0F) / 100.0F;
						float f6 = (f2 * 30.0F + f3 * 70.0F) / 100.0F;
						float f7 = (f2 * 30.0F + f4 * 70.0F) / 100.0F;
						f2 = f5;
						f3 = f6;
						f4 = f7;
					}

					f5 = 4.8828125E-4F;
					double d2 = Minecraft.getMinecraft().theWorld.getWorldTime() + renderPartialTicks;
					double d0 = mc.renderViewEntity.prevPosX + (mc.renderViewEntity.posX - mc.renderViewEntity.prevPosX) * renderPartialTicks + d2 * 0.029999999329447746D;
					double d1 = mc.renderViewEntity.prevPosZ + (mc.renderViewEntity.posZ - mc.renderViewEntity.prevPosZ) * renderPartialTicks;
					int j = MathHelper.floor_double(d0 / 2048.0D);
					int k = MathHelper.floor_double(d1 / 2048.0D);
					d0 -= j * 2048;
					d1 -= k * 2048;
					float f8 = mc.theWorld.provider.getCloudHeight() - 7 - f1 + 0.33F;
					float f9 = (float) (d0 * f5);
					float f10 = (float) (d1 * f5);
					tessellator.startDrawingQuads();
					tessellator.setColorRGBA_F(f2, f3, f4, 0.8F);

					for (int l = -b0 * i; l < b0 * i; l += b0)
					{
						for (int i1 = -b0 * i; i1 < b0 * i; i1 += b0)
						{
							tessellator.addVertexWithUV(l + 0, f8, i1 + b0, (l + 0) * f5 + f9, (i1 + b0) * f5 + f10);
							tessellator.addVertexWithUV(l + b0, f8, i1 + b0, (l + b0) * f5 + f9, (i1 + b0) * f5 + f10);
							tessellator.addVertexWithUV(l + b0, f8, i1 + 0, (l + b0) * f5 + f9, (i1 + 0) * f5 + f10);
							tessellator.addVertexWithUV(l + 0, f8, i1 + 0, (l + 0) * f5 + f9, (i1 + 0) * f5 + f10);
						}
					}

					tessellator.draw();
					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
					GlStateManager.disable(GL11.GL_BLEND);
					GlStateManager.enable(GL11.GL_CULL_FACE);
				}
				GlStateManager.disable(GL11.GL_FOG);
			}
			GlStateManager.popMatrix();
		}
	}

	public void renderCloudsFancy(float renderPartialTicks)
	{
		Minecraft mc = Minecraft.getMinecraft();
		Tessellator tessellator = Tessellator.instance;
		GlStateManager.disable(GL11.GL_CULL_FACE);
		float f1 = (float) (mc.renderViewEntity.lastTickPosY + (mc.renderViewEntity.posY - mc.renderViewEntity.lastTickPosY) * renderPartialTicks);
		float cloudSpan = 10.0F;
		float cloudHeight = 7.0F;
		double d0 = Minecraft.getMinecraft().theWorld.getWorldTime() + renderPartialTicks;
		double d1 = (mc.renderViewEntity.prevPosX + (mc.renderViewEntity.posX - mc.renderViewEntity.prevPosX) * renderPartialTicks + d0 * 0.029999999329447746D) / cloudSpan;
		double d2 = (mc.renderViewEntity.prevPosZ + (mc.renderViewEntity.posZ - mc.renderViewEntity.prevPosZ) * renderPartialTicks) / cloudSpan + 0.33000001311302185D;
		float posY = mc.theWorld.provider.getCloudHeight() - 7 - f1 + 0.33F;
		int i = MathHelper.floor_double(d1 / 2048.0D);
		int j = MathHelper.floor_double(d2 / 2048.0D);
		d1 -= i * 2048;
		d2 -= j * 2048;
		RenderUtil.bindTexture(AliensVsPredator.resources().SKY_VARDA_CLOUDS);
		GlStateManager.enable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		Vec3 color = mc.theWorld.getCloudColour(renderPartialTicks);
		float f5 = (float) color.xCoord;
		float f6 = (float) color.yCoord;
		float f7 = (float) color.zCoord;
		float f8;
		float f9;
		float f10;

		f8 = (float) (d1 * 0.0D);
		f9 = (float) (d2 * 0.0D);
		f10 = 0.00390625F;
		f8 = MathHelper.floor_double(d1) * f10;
		f9 = MathHelper.floor_double(d2) * f10;
		float f11 = (float) (d1 - MathHelper.floor_double(d1));
		float f12 = (float) (d2 - MathHelper.floor_double(d2));
		byte distance = (byte) (mc.gameSettings.renderDistanceChunks / 2);
		byte b1 = 4;
		float f13 = 9.765625E-4F;
		GlStateManager.scale(cloudSpan, 1.0F, cloudSpan);

		for (int k = 0; k < 2; ++k)
		{
			if (k == 0)
			{
				GL11.glColorMask(false, false, false, false);
			}
			else
			{
				GL11.glColorMask(true, true, true, true);
			}

			for (int l = -b1 + 1; l <= b1; ++l)
			{
				for (int i1 = -b1 + 1; i1 <= b1; ++i1)
				{
					tessellator.startDrawingQuads();
					float f14 = l * distance;
					float f15 = i1 * distance;
					float f16 = f14 - f11;
					float f17 = f15 - f12;

					if (posY > -cloudHeight - 1.0F)
					{
						tessellator.setColorRGBA_F(f5 * 0.7F, f6 * 0.7F, f7 * 0.7F, 0.8F);
						tessellator.setNormal(0.0F, -1.0F, 0.0F);
						tessellator.addVertexWithUV(f16 + 0.0F, posY + 0.0F, f17 + distance, (f14 + 0.0F) * f10 + f8, (f15 + distance) * f10 + f9);
						tessellator.addVertexWithUV(f16 + distance, posY + 0.0F, f17 + distance, (f14 + distance) * f10 + f8, (f15 + distance) * f10 + f9);
						tessellator.addVertexWithUV(f16 + distance, posY + 0.0F, f17 + 0.0F, (f14 + distance) * f10 + f8, (f15 + 0.0F) * f10 + f9);
						tessellator.addVertexWithUV(f16 + 0.0F, posY + 0.0F, f17 + 0.0F, (f14 + 0.0F) * f10 + f8, (f15 + 0.0F) * f10 + f9);
					}

					if (posY <= cloudHeight + 1.0F)
					{
						tessellator.setColorRGBA_F(f5, f6, f7, 0.8F);
						tessellator.setNormal(0.0F, 1.0F, 0.0F);
						tessellator.addVertexWithUV(f16 + 0.0F, posY + cloudHeight - f13, f17 + distance, (f14 + 0.0F) * f10 + f8, (f15 + distance) * f10 + f9);
						tessellator.addVertexWithUV(f16 + distance, posY + cloudHeight - f13, f17 + distance, (f14 + distance) * f10 + f8, (f15 + distance) * f10 + f9);
						tessellator.addVertexWithUV(f16 + distance, posY + cloudHeight - f13, f17 + 0.0F, (f14 + distance) * f10 + f8, (f15 + 0.0F) * f10 + f9);
						tessellator.addVertexWithUV(f16 + 0.0F, posY + cloudHeight - f13, f17 + 0.0F, (f14 + 0.0F) * f10 + f8, (f15 + 0.0F) * f10 + f9);
					}

					tessellator.setColorRGBA_F(f5 * 0.9F, f6 * 0.9F, f7 * 0.9F, 0.8F);
					int j1;

					if (l > -1)
					{
						tessellator.setNormal(-1.0F, 0.0F, 0.0F);

						for (j1 = 0; j1 < distance; ++j1)
						{
							tessellator.addVertexWithUV(f16 + j1 + 0.0F, posY + 0.0F, f17 + distance, (f14 + j1 + 0.5F) * f10 + f8, (f15 + distance) * f10 + f9);
							tessellator.addVertexWithUV(f16 + j1 + 0.0F, posY + cloudHeight, f17 + distance, (f14 + j1 + 0.5F) * f10 + f8, (f15 + distance) * f10 + f9);
							tessellator.addVertexWithUV(f16 + j1 + 0.0F, posY + cloudHeight, f17 + 0.0F, (f14 + j1 + 0.5F) * f10 + f8, (f15 + 0.0F) * f10 + f9);
							tessellator.addVertexWithUV(f16 + j1 + 0.0F, posY + 0.0F, f17 + 0.0F, (f14 + j1 + 0.5F) * f10 + f8, (f15 + 0.0F) * f10 + f9);
						}
					}

					if (l <= 1)
					{
						tessellator.setNormal(1.0F, 0.0F, 0.0F);

						for (j1 = 0; j1 < distance; ++j1)
						{
							tessellator.addVertexWithUV(f16 + j1 + 1.0F - f13, posY + 0.0F, f17 + distance, (f14 + j1 + 0.5F) * f10 + f8, (f15 + distance) * f10 + f9);
							tessellator.addVertexWithUV(f16 + j1 + 1.0F - f13, posY + cloudHeight, f17 + distance, (f14 + j1 + 0.5F) * f10 + f8, (f15 + distance) * f10 + f9);
							tessellator.addVertexWithUV(f16 + j1 + 1.0F - f13, posY + cloudHeight, f17 + 0.0F, (f14 + j1 + 0.5F) * f10 + f8, (f15 + 0.0F) * f10 + f9);
							tessellator.addVertexWithUV(f16 + j1 + 1.0F - f13, posY + 0.0F, f17 + 0.0F, (f14 + j1 + 0.5F) * f10 + f8, (f15 + 0.0F) * f10 + f9);
						}
					}

					tessellator.setColorRGBA_F(f5 * 0.8F, f6 * 0.8F, f7 * 0.8F, 0.8F);

					if (i1 > -1)
					{
						tessellator.setNormal(0.0F, 0.0F, -1.0F);

						for (j1 = 0; j1 < distance; ++j1)
						{
							tessellator.addVertexWithUV(f16 + 0.0F, posY + cloudHeight, f17 + j1 + 0.0F, (f14 + 0.0F) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9);
							tessellator.addVertexWithUV(f16 + distance, posY + cloudHeight, f17 + j1 + 0.0F, (f14 + distance) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9);
							tessellator.addVertexWithUV(f16 + distance, posY + 0.0F, f17 + j1 + 0.0F, (f14 + distance) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9);
							tessellator.addVertexWithUV(f16 + 0.0F, posY + 0.0F, f17 + j1 + 0.0F, (f14 + 0.0F) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9);
						}
					}

					if (i1 <= 1)
					{
						tessellator.setNormal(0.0F, 0.0F, 1.0F);

						for (j1 = 0; j1 < distance; ++j1)
						{
							tessellator.addVertexWithUV(f16 + 0.0F, posY + cloudHeight, f17 + j1 + 1.0F - f13, (f14 + 0.0F) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9);
							tessellator.addVertexWithUV(f16 + distance, posY + cloudHeight, f17 + j1 + 1.0F - f13, (f14 + distance) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9);
							tessellator.addVertexWithUV(f16 + distance, posY + 0.0F, f17 + j1 + 1.0F - f13, (f14 + distance) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9);
							tessellator.addVertexWithUV(f16 + 0.0F, posY + 0.0F, f17 + j1 + 1.0F - f13, (f14 + 0.0F) * f10 + f8, (f15 + j1 + 0.5F) * f10 + f9);
						}
					}

					tessellator.draw();
				}
			}
		}

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disable(GL11.GL_BLEND);
		GlStateManager.enable(GL11.GL_CULL_FACE);
	}
}
