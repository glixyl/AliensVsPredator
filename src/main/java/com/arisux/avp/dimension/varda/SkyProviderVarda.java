package com.arisux.avp.dimension.varda;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.dimension.BiomeLVBase;
import com.arisux.avp.event.StormUpdateEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.IRenderHandler;

public class SkyProviderVarda extends IRenderHandler
{
	private StormUpdateEvent event;
	private float[] stormXCoords = null;
	private float[] stormZCoords = null;

	public int starGLCallList = GLAllocation.generateDisplayLists(3);
	public int glSkyList;

	public SkyProviderVarda()
	{
		this.event = (StormUpdateEvent) AliensVsPredator.events().getEvent(StormUpdateEvent.class);
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
		ProviderVarda provider = (ProviderVarda) world.provider;
		Tessellator tessellator = Tessellator.instance;

		if (provider.isSilicaStormActive())
		{
			 this.renderStorm(renderPartialTicks);
		}

		GlStateManager.disable(GL11.GL_TEXTURE_2D);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_FOG);
		GL11.glColor3f(0.11F, 0.225F, 0.265F);
		GL11.glCallList(this.glSkyList);
		GlStateManager.disable(GL11.GL_FOG);
		GlStateManager.disable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, provider.getStarBrightness(renderPartialTicks) * 2);
		GL11.glCallList(this.starGLCallList);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, 1);

		GL11.glPushMatrix();
		{
			float scale = 30.0F;
			GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.rotate(world.getCelestialAngle(renderPartialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
			RenderUtil.bindTexture(AliensVsPredator.resources().SKY_SUN);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-scale, 150.0D, -scale, 0.0D, 0.0D);
			tessellator.addVertexWithUV(scale, 150.0D, -scale, 1.0D, 0.0D);
			tessellator.addVertexWithUV(scale, 150.0D, scale, 1.0D, 1.0D);
			tessellator.addVertexWithUV(-scale, 150.0D, scale, 0.0D, 1.0D);
			tessellator.draw();
		}
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		{
			float scale = 275.0F;
			GlStateManager.translate(30F, 0F, 0F);
			GlStateManager.rotate(calculateAngleFromPlanet(world.getWorldTime(), renderPartialTicks) * 360.0F, 0.0F, 1.0F, 0.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.rotate(calculateAngleFromPlanet(world.getWorldTime(), renderPartialTicks) * 360.0F, 10.0F, -6.0F, -20.0F);
			GlStateManager.rotate(135F, 0.0F, 1.0F, 0.0F);
			RenderUtil.bindTexture(AliensVsPredator.resources().SKY_CALPAMOS);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-scale, 150.0D, -scale, 0.0D, 0.0D);
			tessellator.addVertexWithUV(scale, 150.0D, -scale, 1.0D, 0.0D);
			tessellator.addVertexWithUV(scale, 150.0D, scale, 1.0D, 1.0D);
			tessellator.addVertexWithUV(-scale, 150.0D, scale, 0.0D, 1.0D);
			tessellator.draw();
		}
		GL11.glPopMatrix();

		GlStateManager.disable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(true);
		this.renderClouds(renderPartialTicks);
	}

	private void renderStars()
	{
		Random rand = new Random(10842L);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();

		for (int var3 = 0; var3 < 6000; var3++)
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

	public void renderStorm(float renderPartialTicks)
	{
		Minecraft mc = Minecraft.getMinecraft();
		float gustSize = 4F;
		float intensity = 2F;
		float windSpeed = 1F;
		int maxStormSize = 32;
		mc.entityRenderer.enableLightmap(renderPartialTicks);

		stormXCoords = null;

		if (stormXCoords == null || stormZCoords == null)
		{
			stormXCoords = new float[maxStormSize * maxStormSize];
			stormZCoords = new float[maxStormSize * maxStormSize];

			for (int zCoord = 0; zCoord < maxStormSize; ++zCoord)
			{
				for (int xCoord = 0; xCoord < maxStormSize; ++xCoord)
				{
					float x = xCoord - 16;
					float z = zCoord - 16;
					float sq = MathHelper.sqrt_float(x * x + z * z);
					stormXCoords[zCoord << 5 | xCoord] = -z / sq;
					stormZCoords[zCoord << 5 | xCoord] = x / sq;
				}
			}
		}

		EntityLivingBase renderViewEntity = mc.renderViewEntity;
		WorldClient worldclient = mc.theWorld;
		Tessellator tessellator = Tessellator.instance;
		int posX = MathHelper.floor_double(renderViewEntity.posX);
		int posY = MathHelper.floor_double(renderViewEntity.posY);
		int posZ = MathHelper.floor_double(renderViewEntity.posZ);
		double renderPartialX = renderViewEntity.lastTickPosX + (renderViewEntity.posX - renderViewEntity.lastTickPosX) * renderPartialTicks;
		double renderPartialY = renderViewEntity.lastTickPosY + (renderViewEntity.posY - renderViewEntity.lastTickPosY) * renderPartialTicks;
		double renderPartialZ = renderViewEntity.lastTickPosZ + (renderViewEntity.posZ - renderViewEntity.lastTickPosZ) * renderPartialTicks;
		int renderYFloor = MathHelper.floor_double(renderPartialY);
		byte layers = (byte) (mc.gameSettings.fancyGraphics ? (maxStormSize / 2) - 1 : (maxStormSize / 4));

		GlStateManager.disable(GL11.GL_CULL_FACE);
		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderUtil.bindTexture(AliensVsPredator.resources().SKY_SILICA);

		for (int vZ = posZ - layers; vZ <= posZ + layers; ++vZ)
		{
			for (int vX = posX - layers; vX <= posX + layers; ++vX)
			{
				int j1 = (vZ - posZ + 16) * 32 + vX - posX + 16;
				float rotationX = stormXCoords[j1] * 0.5F;
				float rotationZ = stormZCoords[j1] * 0.5F;
				BiomeGenBase biomegenbase = worldclient.getBiomeGenForCoords(vX, vZ);

				if (biomegenbase == BiomeLVBase.varda)
				{
					int stormHeight = worldclient.getPrecipitationHeight(vX, vZ);
					int minY = posY - (mc.gameSettings.fancyGraphics ? 32 : 16);
					int maxY = posY + (mc.gameSettings.fancyGraphics ? 32 : 16);

					if (minY < stormHeight)
					{
						minY = stormHeight;
					}

					if (maxY < stormHeight)
					{
						maxY = stormHeight;
					}

					int vY = stormHeight;

					if (stormHeight < renderYFloor)
					{
						vY = renderYFloor;
					}

					if (maxY >= worldclient.provider.getCloudHeight())
					{
						maxY = (int) worldclient.provider.getCloudHeight();
					}

					if (minY != maxY)
					{
						float movement = 0F;

						movement = ((event.getStormUpdateCount() + vX * vX * 3121 + vX * 45238971 + vZ * vZ * 418711 + vZ * 13761 & 31) + renderPartialTicks) / 16.0F * intensity;
						movement = movement * windSpeed;
						tessellator.startDrawingQuads();
						tessellator.setBrightness(worldclient.getLightBrightnessForSkyBlocks(vX, vY, vZ, 0));
						tessellator.setColorRGBA_F(0.3F, 0.3F, 0.3F, 1F);
						tessellator.setTranslation(-renderPartialX * 1.0D, -renderPartialY * 1.0D, -renderPartialZ * 1.0D);
						tessellator.addVertexWithUV(vX - rotationX + 0.5D, minY, vZ - rotationZ + 0.5D + movement * gustSize, 0.0F * gustSize, minY * gustSize / 4.0F + movement * gustSize);
						tessellator.addVertexWithUV(vX + rotationX + 0.5D, minY, vZ + rotationZ + 0.5D + movement * gustSize, 1.0F * gustSize, minY * gustSize / 4.0F + movement * gustSize);
						tessellator.addVertexWithUV(vX + rotationX + 0.5D, maxY, vZ + rotationZ + 0.5D + movement * gustSize, 1.0F * gustSize, maxY * gustSize / 4.0F + movement * gustSize);
						tessellator.addVertexWithUV(vX - rotationX + 0.5D, maxY, vZ - rotationZ + 0.5D + movement * gustSize, 0.0F * gustSize, maxY * gustSize / 4.0F + movement * gustSize);
						tessellator.setTranslation(0.0D, 0.0D, 0.0D);
						tessellator.draw();
					}
				}
			}
		}

		GL11.glEnable(GL11.GL_CULL_FACE);
		GlStateManager.disable(GL11.GL_BLEND);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		mc.entityRenderer.disableLightmap(renderPartialTicks);
	}

	public void renderClouds(float renderPartialTicks)
	{
		Minecraft mc = Minecraft.getMinecraft();

		if (mc.gameSettings.shouldRenderClouds())
		{
			GL11.glPushMatrix();
			{
				GL11.glEnable(GL11.GL_FOG);

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
					GL11.glEnable(GL11.GL_BLEND);
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
					double d2 = this.event.getCloudTickCounter() + renderPartialTicks;
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
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GlStateManager.disable(GL11.GL_BLEND);
					GL11.glEnable(GL11.GL_CULL_FACE);
				}
				GlStateManager.disable(GL11.GL_FOG);
			}
			GL11.glPopMatrix();
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
		double d0 = this.event.getCloudTickCounter() + renderPartialTicks;
		double d1 = (mc.renderViewEntity.prevPosX + (mc.renderViewEntity.posX - mc.renderViewEntity.prevPosX) * renderPartialTicks + d0 * 0.029999999329447746D) / cloudSpan;
		double d2 = (mc.renderViewEntity.prevPosZ + (mc.renderViewEntity.posZ - mc.renderViewEntity.prevPosZ) * renderPartialTicks) / cloudSpan + 0.33000001311302185D;
		float posY = mc.theWorld.provider.getCloudHeight() - 7 - f1 + 0.33F;
		int i = MathHelper.floor_double(d1 / 2048.0D);
		int j = MathHelper.floor_double(d2 / 2048.0D);
		d1 -= i * 2048;
		d2 -= j * 2048;
		RenderUtil.bindTexture(AliensVsPredator.resources().SKY_VARDA_CLOUDS);
		GL11.glEnable(GL11.GL_BLEND);
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

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

}
