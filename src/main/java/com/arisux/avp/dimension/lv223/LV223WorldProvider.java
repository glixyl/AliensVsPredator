package com.arisux.avp.dimension.lv223;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.DimensionManager;

import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LV223WorldProvider extends WorldProvider
{
	@SideOnly(Side.CLIENT)
	private IRenderHandler skyProvider;

	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new LV223WorldChunkManager(LV223BiomeBase.prometheusBiome);
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
		return skyProvider == null ? skyProvider = new LV223SkyProvider() : skyProvider;
	}

	@Override
	public String getSaveFolder()
	{
		return AliensVsPredator.INSTANCE.properties.DIMENSION_GLOBAL_LV223;
	}

	@Override
	public String getWelcomeMessage()
	{
		return "Enterring " + AliensVsPredator.INSTANCE.properties.DIMENSION_NAME_LV223;
	}

	@Override
	public String getDepartMessage()
	{
		return "Leaving" + AliensVsPredator.INSTANCE.properties.DIMENSION_NAME_LV223;
	}

	@Override
	public ChunkCoordinates getEntrancePortalLocation()
	{
		return new ChunkCoordinates(0, 160, 0);
	}

	@Override
	public int getAverageGroundLevel()
	{
		return 100;
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new LV223ChunkProvider(this.worldObj, worldObj.getSeed());
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
		return 200.0F;
	}

	@Override
	public double getMovementFactor()
	{
		return 32.0D;
	}

	@Override
	public String getDimensionName()
	{
		return AliensVsPredator.INSTANCE.properties.DIMENSION_NAME_LV223;
	}

	@Override
	public float getStarBrightness(float var1)
	{
		return 0.1F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float var1, float var2)
	{
		float var3 = MathHelper.cos(var1 * 3.141593F * 2.0F) * 2.0F + 0.5F;

		if (var3 < 0.0F)
		{
			var3 = 0.0F;
		}

		if (var3 > 1.0F)
		{
			var3 = 1.0F;
		}

		float var4 = 0.7529412F;
		float var5 = 0.8470588F;
		float var6 = 1.0F;
		var4 *= (var3 * 0.24F + 0.06F);
		var5 *= (var3 * 0.24F + 0.06F);
		var6 *= (var3 * 0.26F + 0.09F);
		return Vec3.createVectorHelper(var4, var5, var6);
	}
}