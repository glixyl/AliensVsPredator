package com.arisux.avp.dimension.varda;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.render.Color;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.dimension.BiomeLVBase;
import com.arisux.avp.dimension.DimensionUtil;
import com.arisux.avp.event.StormUpdateEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.IRenderHandler;

public class SkyProviderVarda extends IRenderHandler
{
	private Tessellator tessellator = Tessellator.instance;
	private Minecraft mc = Minecraft.getMinecraft();
	private StormUpdateEvent event;
	private Color skyColor = new Color(0.11F, 0.225F, 0.265F, 1F);
	protected Color cloudColor = new Color(0.03F, 0.03F, 0.05F, 0.8F);
	private float[] stormXCoords = null;
	private float[] stormZCoords = null;

	public int starGLCallList = GLAllocation.generateDisplayLists(3);
	public int glSkyList;

	public SkyProviderVarda()
	{
		this.event = (StormUpdateEvent) AliensVsPredator.events().getEvent(StormUpdateEvent.class);

		GL11.glNewList(this.starGLCallList, GL11.GL_COMPILE);
		DimensionUtil.renderStars(new Random(10842L), 6000, 100);
		GL11.glEndList();

		this.glSkyList = (this.starGLCallList + 1);
		GL11.glNewList(this.glSkyList, GL11.GL_COMPILE);
		{
			int levels = 64;
			int size = 256 / levels + 2;
			float skylineHeight = 60.0F;
	
			for (int x = -levels * size; x <= levels * size; x += levels)
			{
				for (int z = -levels * size; z <= levels * size; z += levels)
				{
					tessellator.startDrawingQuads();
					tessellator.addVertex(x + 0.000F, skylineHeight, z + 0.000F);
					tessellator.addVertex(x + levels, skylineHeight, z + 0.000F);
					tessellator.addVertex(x + levels, skylineHeight, z + levels);
					tessellator.addVertex(x + 0.000F, skylineHeight, z + levels);
					tessellator.draw();
				}
			}
		}
		GL11.glEndList();
	}

	@Override
	public void render(float renderPartialTicks, WorldClient world, Minecraft mc)
	{
		ProviderVarda provider = (ProviderVarda) world.provider;
		
		this.cloudColor.r = 0.075F;
		this.cloudColor.g = 0.1F;
		this.cloudColor.b = 0.15F;
		this.cloudColor.a = 0.75F;

		if (provider.isSilicaStormActive())
		{
			 this.renderStorm(renderPartialTicks);
		}

		GlStateManager.disable(GL11.GL_TEXTURE_2D);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GL11.glDepthMask(false);
		GlStateManager.enable(GL11.GL_FOG);
		GL11.glColor3f(skyColor.r, skyColor.g, skyColor.b);
		
		/** Render Sky **/
		GL11.glCallList(this.glSkyList);
		GlStateManager.disable(GL11.GL_FOG);
		GlStateManager.disable(GL11.GL_ALPHA_TEST);
		GlStateManager.enable(GL11.GL_BLEND);
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, provider.getStarBrightness(renderPartialTicks) * 2);
		
		/** Render Stars **/
		GL11.glCallList(this.starGLCallList);
		GlStateManager.enable(GL11.GL_TEXTURE_2D);
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, 1);

		GlStateManager.pushMatrix();
		{
			float scale = 30.0F;
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

		GlStateManager.pushMatrix();
		{
			float scale = 275.0F;
			GlStateManager.translate(30F, 0F, 0F);
			GlStateManager.rotate(DimensionUtil.calculateCelestialAngle(world.getWorldTime(), renderPartialTicks) * 360.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.rotate(DimensionUtil.calculateCelestialAngle(world.getWorldTime(), renderPartialTicks) * 360.0F, 10.0F, -6.0F, -20.0F);
			GlStateManager.rotate(135F, 0.0F, 1.0F, 0.0F);
			RenderUtil.bindTexture(AliensVsPredator.resources().SKY_CALPAMOS);
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
		
		if (mc.gameSettings.shouldRenderClouds())
		{
			GlStateManager.pushMatrix();
			{
				if (mc.gameSettings.fancyGraphics)
				{
					GlStateManager.enable(GL11.GL_FOG);
				}
				
				this.renderClouds(renderPartialTicks);
				GlStateManager.disable(GL11.GL_FOG);
			}
			GlStateManager.popMatrix();
		}
	}
	
	public void renderClouds(float renderPartialTicks)
	{
		for (int cloudPass = 1; cloudPass > 0; cloudPass--)
		{
			float relativeHeight = (float) (mc.renderViewEntity.lastTickPosY + (mc.renderViewEntity.posY - mc.renderViewEntity.lastTickPosY) * renderPartialTicks);
			float cloudSpan = 18.0F;
			float cloudHeight = 7.0F * cloudPass;
			float cloudSpeed = 10;
			double time = mc.theWorld.getWorldTime() * cloudSpeed + renderPartialTicks;
			double viewX = (mc.renderViewEntity.prevPosX + (mc.renderViewEntity.posX - mc.renderViewEntity.prevPosX) * renderPartialTicks + time * 0.029999999329447746D) / cloudSpan;
			double viewZ = (mc.renderViewEntity.prevPosZ + (mc.renderViewEntity.posZ - mc.renderViewEntity.prevPosZ) * renderPartialTicks) / cloudSpan + 0.33000001311302185D;
			float cloudY = mc.theWorld.provider.getCloudHeight() - relativeHeight;
			viewX -= (MathHelper.floor_double(viewX / 2048.0D)) * 2048;
			viewZ -= (MathHelper.floor_double(viewZ / 2048.0D)) * 2048;
			float scaleUV = 0.00390625F;
			float offsetU = MathHelper.floor_double(viewX) * scaleUV;
			float offsetV = MathHelper.floor_double(viewZ) * scaleUV;
			byte dist = (byte) (mc.gameSettings.renderDistanceChunks);
			byte cloudSections = 16;
			
			GlStateManager.disableCullFace();
			RenderUtil.bindTexture(AliensVsPredator.resources().SKY_VARDA_CLOUDS);
			GlStateManager.enableBlend();
			OpenGlHelper.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GlStateManager.scale(cloudSpan, 1.0F, cloudSpan);
	
			for (int pass = 0; pass < 2; pass++)
			{
				if (pass == 0)
				{
					GL11.glColorMask(false, false, false, false);
				}
				else
				{
					GL11.glColorMask(true, true, true, true);
				}
	
				for (int x = -cloudSections + 1; x <= cloudSections; ++x)
				{
					for (int z = -cloudSections + 1; z <= cloudSections; ++z)
					{
						float cloudU = x * dist;
						float cloudV = z * dist;
						float cloudX = cloudU - ((float) (viewX - MathHelper.floor_double(viewX)));
						float cloudZ = cloudV - ((float) (viewZ - MathHelper.floor_double(viewZ)));

						tessellator.startDrawingQuads();
	
						if (cloudY > -cloudHeight - 1.0F)
						{
							tessellator.setColorRGBA_F(cloudColor.r * 0.7F, cloudColor.g * 0.7F, cloudColor.b * 0.7F, cloudColor.a + 0.1F);
							tessellator.setNormal(0.0F, -1.0F, 0.0F);
							tessellator.addVertexWithUV(cloudX + 0.0F, cloudY + 0.0F, cloudZ + dist, (cloudU + 0.0F) * scaleUV + offsetU, (cloudV + dist) * scaleUV + offsetV);
							tessellator.addVertexWithUV(cloudX + dist, cloudY + 0.0F, cloudZ + dist, (cloudU + dist) * scaleUV + offsetU, (cloudV + dist) * scaleUV + offsetV);
							tessellator.addVertexWithUV(cloudX + dist, cloudY + 0.0F, cloudZ + 0.0F, (cloudU + dist) * scaleUV + offsetU, (cloudV + 0.0F) * scaleUV + offsetV);
							tessellator.addVertexWithUV(cloudX + 0.0F, cloudY + 0.0F, cloudZ + 0.0F, (cloudU + 0.0F) * scaleUV + offsetU, (cloudV + 0.0F) * scaleUV + offsetV);
						}
	
						if (cloudY <= cloudHeight + 1.0F)
						{
							tessellator.setColorRGBA_F(cloudColor.r, cloudColor.g, cloudColor.b, cloudColor.a + 0.15F);
							tessellator.setNormal(0.0F, 1.0F, 0.0F);
							tessellator.addVertexWithUV(cloudX + 0.0F, cloudY + cloudHeight, cloudZ + dist, (cloudU + 0.0F) * scaleUV + offsetU, (cloudV + dist) * scaleUV + offsetV);
							tessellator.addVertexWithUV(cloudX + dist, cloudY + cloudHeight, cloudZ + dist, (cloudU + dist) * scaleUV + offsetU, (cloudV + dist) * scaleUV + offsetV);
							tessellator.addVertexWithUV(cloudX + dist, cloudY + cloudHeight, cloudZ + 0.0F, (cloudU + dist) * scaleUV + offsetU, (cloudV + 0.0F) * scaleUV + offsetV);
							tessellator.addVertexWithUV(cloudX + 0.0F, cloudY + cloudHeight, cloudZ + 0.0F, (cloudU + 0.0F) * scaleUV + offsetU, (cloudV + 0.0F) * scaleUV + offsetV);
						}
	
						tessellator.setColorRGBA_F(cloudColor.r * 0.9F, cloudColor.g * 0.9F, cloudColor.b * 0.9F, cloudColor.a);
	
						if (x > -1)
						{
							tessellator.setNormal(-1.0F, 0.0F, 0.0F);
	
							for (int size = 0; size < dist; ++size)
							{
								tessellator.addVertexWithUV(cloudX + size + 0.0F, cloudY + 0.0F, cloudZ + dist, (cloudU + size + 0.5F) * scaleUV + offsetU, (cloudV + dist) * scaleUV + offsetV);
								tessellator.addVertexWithUV(cloudX + size + 0.0F, cloudY + cloudHeight, cloudZ + dist, (cloudU + size + 0.5F) * scaleUV + offsetU, (cloudV + dist) * scaleUV + offsetV);
								tessellator.addVertexWithUV(cloudX + size + 0.0F, cloudY + cloudHeight, cloudZ + 0.0F, (cloudU + size + 0.5F) * scaleUV + offsetU, (cloudV + 0.0F) * scaleUV + offsetV);
								tessellator.addVertexWithUV(cloudX + size + 0.0F, cloudY + 0.0F, cloudZ + 0.0F, (cloudU + size + 0.5F) * scaleUV + offsetU, (cloudV + 0.0F) * scaleUV + offsetV);
							}
						}
	
						if (x <= 1)
						{
							tessellator.setNormal(1.0F, 0.0F, 0.0F);
	
							for (int size = 0; size < dist; ++size)
							{
								tessellator.addVertexWithUV(cloudX + size + 1.0F, cloudY + 0.0F, cloudZ + dist, (cloudU + size + 0.5F) * scaleUV + offsetU, (cloudV + dist) * scaleUV + offsetV);
								tessellator.addVertexWithUV(cloudX + size + 1.0F, cloudY + cloudHeight, cloudZ + dist, (cloudU + size + 0.5F) * scaleUV + offsetU, (cloudV + dist) * scaleUV + offsetV);
								tessellator.addVertexWithUV(cloudX + size + 1.0F, cloudY + cloudHeight, cloudZ + 0.0F, (cloudU + size + 0.5F) * scaleUV + offsetU, (cloudV + 0.0F) * scaleUV + offsetV);
								tessellator.addVertexWithUV(cloudX + size + 1.0F, cloudY + 0.0F, cloudZ + 0.0F, (cloudU + size + 0.5F) * scaleUV + offsetU, (cloudV + 0.0F) * scaleUV + offsetV);
							}
						}
	
						tessellator.setColorRGBA_F(cloudColor.r * 0.8F, cloudColor.g * 0.8F, cloudColor.b * 0.8F, 0.8F);
	
						if (z > -1)
						{
							tessellator.setNormal(0.0F, 0.0F, -1.0F);
	
							for (int size = 0; size < dist; ++size)
							{
								tessellator.addVertexWithUV(cloudX + 0.0F, cloudY + cloudHeight, cloudZ + size + 0.0F, (cloudU + 0.0F) * scaleUV + offsetU, (cloudV + size + 0.5F) * scaleUV + offsetV);
								tessellator.addVertexWithUV(cloudX + dist, cloudY + cloudHeight, cloudZ + size + 0.0F, (cloudU + dist) * scaleUV + offsetU, (cloudV + size + 0.5F) * scaleUV + offsetV);
								tessellator.addVertexWithUV(cloudX + dist, cloudY + 0.0F, cloudZ + size + 0.0F, (cloudU + dist) * scaleUV + offsetU, (cloudV + size + 0.5F) * scaleUV + offsetV);
								tessellator.addVertexWithUV(cloudX + 0.0F, cloudY + 0.0F, cloudZ + size + 0.0F, (cloudU + 0.0F) * scaleUV + offsetU, (cloudV + size + 0.5F) * scaleUV + offsetV);
							}
						}
	
						if (z <= 1)
						{
							tessellator.setNormal(0.0F, 0.0F, 1.0F);
	
							for (int size = 0; size < dist; ++size)
							{
								tessellator.addVertexWithUV(cloudX + 0.0F, cloudY + cloudHeight, cloudZ + size + 1.0F, (cloudU + 0.0F) * scaleUV + offsetU, (cloudV + size + 0.5F) * scaleUV + offsetV);
								tessellator.addVertexWithUV(cloudX + dist, cloudY + cloudHeight, cloudZ + size + 1.0F, (cloudU + dist) * scaleUV + offsetU, (cloudV + size + 0.5F) * scaleUV + offsetV);
								tessellator.addVertexWithUV(cloudX + dist, cloudY + 0.0F, cloudZ + size + 1.0F, (cloudU + dist) * scaleUV + offsetU, (cloudV + size + 0.5F) * scaleUV + offsetV);
								tessellator.addVertexWithUV(cloudX + 0.0F, cloudY + 0.0F, cloudZ + size + 1.0F, (cloudU + 0.0F) * scaleUV + offsetU, (cloudV + size + 0.5F) * scaleUV + offsetV);
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

	public void renderStorm(float renderPartialTicks)
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
		GlStateManager.enable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
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

		GlStateManager.enable(GL11.GL_CULL_FACE);
		GlStateManager.disable(GL11.GL_BLEND);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		mc.entityRenderer.disableLightmap(renderPartialTicks);
	}
}
