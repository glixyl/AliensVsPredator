package com.arisux.avp;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.dimension.lv223.LV223Teleporter;
import com.arisux.avp.dimension.lv223.LV223WorldGenerator;
import com.arisux.avp.dimension.lv223.LV223WorldProvider;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class DimensionHandler implements IInitializable
{
	public void initialize()
	{
		DimensionManager.registerProviderType(AliensVsPredator.INSTANCE.properties.DIMENSION_ID_LV223, LV223WorldProvider.class, true);
		DimensionManager.registerDimension(AliensVsPredator.INSTANCE.properties.DIMENSION_ID_LV223, AliensVsPredator.INSTANCE.properties.DIMENSION_ID_LV223);
		GameRegistry.registerWorldGenerator(new LV223WorldGenerator(), 1);
	}

	public void initialWorldChunkLoad(WorldServer worldServerObj)
	{
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		long startTime = System.currentTimeMillis();
		short var3 = 196;
		server.logInfo("Preparing start region for level " + AliensVsPredator.INSTANCE.properties.DIMENSION_GLOBAL_LV223);

		for (int chunkX = -var3; (chunkX <= var3) && (server.isServerRunning()); chunkX += 16)
		{
			for (int chunkZ = -var3; (chunkZ <= var3) && (server.isServerRunning()); chunkZ += 16)
			{
				long curTime = System.currentTimeMillis();

				if (curTime < startTime)
				{
					startTime = curTime;
				}

				if (curTime > startTime + 1000L)
				{
					startTime = curTime;
				}

				worldServerObj.theChunkProviderServer.loadChunk(worldServerObj.getSpawnPoint().posX + chunkX >> 4, worldServerObj.getSpawnPoint().posZ + chunkZ >> 4);
			}
		}
	}

	public static void teleportPlayerToDimension(EntityPlayerMP player)
	{
		ServerConfigurationManager serverManager = MinecraftServer.getServer().getConfigurationManager();

		if (player.dimension == 0)
		{
			LV223Teleporter teleporter = new LV223Teleporter(MinecraftServer.getServer().worldServerForDimension(AliensVsPredator.INSTANCE.properties.DIMENSION_ID_LV223));
			serverManager.transferPlayerToDimension(player, AliensVsPredator.INSTANCE.properties.DIMENSION_ID_LV223, teleporter);
		} else if (player.dimension == AliensVsPredator.INSTANCE.properties.DIMENSION_ID_LV223)
		{
			LV223Teleporter teleporter = new LV223Teleporter(MinecraftServer.getServer().worldServerForDimension(0));
			serverManager.transferPlayerToDimension(player, 0, teleporter);
		} else
		{
			LV223Teleporter teleporter = new LV223Teleporter(MinecraftServer.getServer().worldServerForDimension(player.dimension));
			serverManager.transferPlayerToDimension(player, AliensVsPredator.INSTANCE.properties.DIMENSION_ID_LV223, teleporter);
		}
	}
}
