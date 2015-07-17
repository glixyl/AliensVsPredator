package com.arisux.avp.dimension.acheron;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.dimension.BiomeLVBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class ProviderAcheron extends WorldProvider
{
	@SideOnly(Side.CLIENT)
	private IRenderHandler skyProvider;

	public ProviderAcheron()
	{
		;
	}

	@Override
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new ChunkManagerAcheron(BiomeLVBase.acheron);
		this.hasNoSky = false;
		this.isHellWorld = false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IRenderHandler getSkyRenderer()
	{
		return skyProvider == null ? skyProvider = new SkyProviderAcheron() : skyProvider;
	}

	@Override
	public String getSaveFolder()
	{
		return AliensVsPredator.properties().DIMENSION_ID_ACHERON;
	}

	@Override
	public String getWelcomeMessage()
	{
		return "Enterring " + AliensVsPredator.properties().DIMENSION_NAME_ACHERON;
	}

	@Override
	public String getDepartMessage()
	{
		return "Leaving" + AliensVsPredator.properties().DIMENSION_NAME_ACHERON;
	}

	@Override
	public ChunkCoordinates getEntrancePortalLocation()
	{
		return new ChunkCoordinates(0, 48, 0);
	}

	@Override
	public int getAverageGroundLevel()
	{
		return 16;
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderAcheron(this.worldObj, worldObj.getSeed());
	}

	@Override
	public boolean canRespawnHere()
	{
		return true;
	}

	@Override
	public float getCloudHeight()
	{
		return 110.0F;
	}

	@Override
	public double getMovementFactor()
	{
		return 32.0D;
	}

	@Override
	public String getDimensionName()
	{
		return AliensVsPredator.properties().DIMENSION_NAME_ACHERON;
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
		return Vec3.createVectorHelper(0.0F, 0.01F, 0.05F);
	}

	@Override
	public Vec3 drawClouds(float partialTicks)
	{
		return Vec3.createVectorHelper(0.01F, 0.00F, 0.02F);
	}

	@Override
	public float getSunBrightness(float angle)
	{
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
