package com.arisux.avp.dimension.varda;

import net.minecraft.util.*;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.DimensionManager;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.event.StormUpdateEvent;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ProviderVarda extends WorldProvider
{
	@SideOnly(Side.CLIENT)
	private IRenderHandler skyProvider;
	private StormUpdateEvent event;

	public ProviderVarda()
	{
		this.event = (StormUpdateEvent) AliensVsPredator.instance().localEvents.getEvent(StormUpdateEvent.class);
	}

	@Override
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new ChunkManagerVarda(BiomeVardaBase.varda);
		// this.worldChunkMgr = new WorldChunkManager(this.worldObj);
		this.hasNoSky = false;
		this.isHellWorld = false;
	}

	public static WorldProvider getProviderForDimension(int var0)
	{
		return DimensionManager.createProviderFor(7);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IRenderHandler getSkyRenderer()
	{
		return skyProvider == null ? skyProvider = new SkyProviderVarda() : skyProvider;
	}

	@Override
	public String getSaveFolder()
	{
		return AliensVsPredator.properties().DIMENSION_ID_VARDA;
	}

	@Override
	public String getWelcomeMessage()
	{
		return "Enterring " + AliensVsPredator.properties().DIMENSION_NAME_VARDA;
	}

	@Override
	public String getDepartMessage()
	{
		return "Leaving" + AliensVsPredator.properties().DIMENSION_NAME_VARDA;
	}

	@Override
	public ChunkCoordinates getEntrancePortalLocation()
	{
		return new ChunkCoordinates(0, 160, 0);
	}

	@Override
	public int getAverageGroundLevel()
	{
		return 110;
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderVarda(this.worldObj, worldObj.getSeed());
		// return new ChunkProviderGenerate(worldObj,
		// worldObj.getSeed(), true);
	}

	@Override
	public boolean canRespawnHere()
	{
		return false;
	}

	@Override
	public float getCloudHeight()
	{
		return 140.0F;
	}

	@Override
	public double getMovementFactor()
	{
		return 32.0D;
	}

	@Override
	public String getDimensionName()
	{
		return AliensVsPredator.properties().DIMENSION_NAME_VARDA;
	}

	@Override
	public float getStarBrightness(float var1)
	{
		return 0.2F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float var1, float var2)
	{
		return Vec3.createVectorHelper(0.0F, 0.0F, 0.01F);
	}

	@Override
	public Vec3 drawClouds(float partialTicks)
	{
		return Vec3.createVectorHelper(0.07F, 0.07F, 0.09F);
	}

	@Override
	public float getSunBrightness(float angle)
	{
		boolean isDay = (this.worldObj.getWorldTime() % 24000L) / 1000L < 14L;
		float celestialAngle = this.worldObj.getCelestialAngle(angle);
		float brightness = 1.0F - (MathHelper.cos(celestialAngle * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

		if (brightness < 0.0F)
		{
			brightness = 0.0F;
		}

		if (brightness > 1.0F)
		{
			brightness = 1.0F;
		}

		brightness = 1.0F - brightness;
		brightness = (float) (brightness * (1.0D - this.worldObj.getRainStrength(angle) * 5.0F / 16.0D));
		brightness = (float) (brightness * (1.0D - this.worldObj.getWeightedThunderStrength(angle) * 5.0F / 16.0D));
		return brightness * 0.25F;
	}

	public boolean isSilicaStormActive()
	{
		return (this.worldObj.getWorldTime() % 24000L) / 1000L < 7L;
	}

	public void doWeatherEffects()
	{
//		for (EntityPlayer entityPlayer : Players.getPlayersInWorld(this.worldObj))
//		{
//			Iterator iterator = this.worldObj.activeChunkSet.iterator();
//
//			while (iterator.hasNext())
//			{
//				ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair) iterator.next();
//				int k = chunkcoordintpair.chunkXPos * 16;
//				int l = chunkcoordintpair.chunkZPos * 16;
//				Chunk chunk = this.worldObj.getChunkFromChunkCoords(chunkcoordintpair.chunkXPos, chunkcoordintpair.chunkZPos);
//				chunk.func_150804_b(false);
//				int i1;
//				int j1;
//				int k1;
//				int l1;
//
//				if (this.worldObj.provider.canDoLightning(chunk) && this.worldObj.rand.nextInt(100000) == 0)
//				{
//					updateLCG = updateLCG * 3 + 1013904223;
//					i1 = updateLCG >> 2;
//					j1 = k + (i1 & 15);
//					k1 = l + (i1 >> 8 & 15);
//					l1 = this.worldObj.getPrecipitationHeight(j1, k1);
//
//					if (this.worldObj.canLightningStrikeAt(j1, l1, k1))
//					{
//						this.worldObj.addWeatherEffect(new EntityLightningBolt(this, (double) j1, (double) l1, (double) k1));
//					}
//				}
//			}
//		}
	}

	@Override
	public boolean canSnowAt(int x, int y, int z, boolean checkLight)
	{
		return false;
	}

	@Override
	public boolean canDoRainSnowIce(Chunk chunk)
	{
		return false;
	}
}
