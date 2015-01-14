package com.arisux.avp.dimension.varda;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.IRenderHandler;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.event.action.SilicaStormUpdateEvent;

import cpw.mods.fml.client.FMLClientHandler;

public class SkyProviderVarda extends IRenderHandler
{
	private SilicaStormUpdateEvent event;
	private float[] stormXCoords = null;
	private float[] stormZCoords = null;

	public int starGLCallList = GLAllocation.generateDisplayLists(3);
	public int glSkyList;
	public int glSkyList2;

	public SkyProviderVarda()
	{
		this.event = (SilicaStormUpdateEvent) AliensVsPredator.instance().localEvents.getEvent(SilicaStormUpdateEvent.class);
	}

	@Override
	public void render(float renderPartialTicks, WorldClient world, Minecraft mc)
	{
		GL11.glPushMatrix();
		{
			GL11.glNewList(this.starGLCallList, GL11.GL_COMPILE);
			this.renderStars();
			GL11.glEndList();
		}
		GL11.glPopMatrix();

		try
		{
			this.renderWeather(renderPartialTicks, mc);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Tessellator tessellator = Tessellator.instance;
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
		this.glSkyList2 = (this.starGLCallList + 2);
		GL11.glNewList(this.glSkyList2, GL11.GL_COMPILE);
		skylineHeight = -16.0F;
		tessellator.startDrawingQuads();

		for (int var5 = -var20 * var30; var5 <= var20 * var30; var5 += var20)
		{
			for (int var6 = -var20 * var30; var6 <= var20 * var30; var6 += var20)
			{
				tessellator.addVertex(var5 + var20, skylineHeight, var6 + 0);
				tessellator.addVertex(var5 + 0, skylineHeight, var6 + 0);
				tessellator.addVertex(var5 + 0, skylineHeight, var6 + var20);
				tessellator.addVertex(var5 + var20, skylineHeight, var6 + var20);
			}
		}

		tessellator.draw();
		GL11.glEndList();

		ProviderVarda provider = world.provider instanceof ProviderVarda ? (ProviderVarda) world.provider : null;

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		world.getStarBrightness(renderPartialTicks);
		world.getStarBrightness(renderPartialTicks);
		world.getStarBrightness(renderPartialTicks);

		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_FOG);
		GL11.glColor3f(0.0F, 0.0F, 0.0F);
		GL11.glCallList(this.glSkyList);
		GL11.glDisable(GL11.GL_FOG);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		RenderHelper.disableStandardItemLighting();

		GL11.glColor4f(1.0F, 1.0F, 1.0F, provider.getStarBrightness(renderPartialTicks) * 2);
		GL11.glCallList(this.starGLCallList);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, 1);
		float scale = 30.0F;

		GL11.glPushMatrix();
		{
			GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glRotatef(world.getCelestialAngle(renderPartialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
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
			scale = 275.0F;
			world.getSpawnPoint();
			GL11.glTranslatef(30F, 0F, 0F);
			GL11.glRotatef(calculateAngleFromPlanet(world.getWorldTime(), renderPartialTicks) * 360.0F, 0.0F, 1.0F, 0.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glRotatef(calculateAngleFromPlanet(world.getWorldTime(), renderPartialTicks) * 360.0F, 10.0F, -6.0F, -20.0F);
			GL11.glRotatef(135F, 0.0F, 1.0F, 0.0F);
			RenderUtil.bindTexture(AliensVsPredator.resources().SKY_CALPAMOS);
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
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_FOG);
		}
		GL11.glPopMatrix();

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor3f(0.0F, 0.0F, 0.0F);
		double var201 = mc.thePlayer.getPosition(renderPartialTicks).yCoord - world.getHorizon();

		GL11.glColor3f(0.273438F, 0.273438F, 0.273438F);

		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0.0F, -(float) (var201 - 16.0D), 0.0F);
			GL11.glCallList(this.glSkyList2);
		}
		GL11.glPopMatrix();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(true);
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

	private Vec3 getCustomSkyColor()
	{
		return Vec3.createVectorHelper(0.26796875D, 0.1796875D, 0.0D);
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

	public void renderWeather(float renderPartialTicks, Minecraft mc)
	{
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
		byte layers = (byte) (mc.gameSettings.fancyGraphics ? (maxStormSize / 2) : (maxStormSize / 4));

		GL11.glDisable(GL11.GL_CULL_FACE);
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

				if (biomegenbase == BiomeVardaBase.varda)
				{
					int precipitationHeight = worldclient.getPrecipitationHeight(vX, vZ);
					int minY = posY - 64;
					int maxY = posY + 64;

					if (minY < precipitationHeight)
					{
						minY = precipitationHeight;
					}

					if (maxY < precipitationHeight)
					{
						maxY = precipitationHeight;
					}

					int vY = precipitationHeight;

					if (precipitationHeight < renderYFloor)
					{
						vY = renderYFloor;
					}

					if (minY != maxY)
					{
						float movement = 0F;
						Block block = worldclient.getBlock(vX, vY, vZ);

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
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		mc.entityRenderer.disableLightmap(renderPartialTicks);
	}
}
