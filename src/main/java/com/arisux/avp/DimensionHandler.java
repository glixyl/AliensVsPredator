package com.arisux.avp;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.dimension.varda.*;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class DimensionHandler implements IInitializable
{
	@Override
	public void initialize(FMLInitializationEvent event)
	{
		DimensionManager.registerProviderType(AliensVsPredator.properties().DIMENSION_ID_LV223, ProviderVarda.class, true);
		DimensionManager.registerDimension(AliensVsPredator.properties().DIMENSION_ID_LV223, AliensVsPredator.properties().DIMENSION_ID_LV223);
		GameRegistry.registerWorldGenerator(new GeneratorVarda(), 1);
	}

	public void initialWorldChunkLoad(WorldServer worldServerObj)
	{
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		long startTime = System.currentTimeMillis();
		short var3 = 196;
		server.logInfo("Preparing start region for level " + AliensVsPredator.properties().DIMENSION_ID_VARDA);

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
			TeleporterVarda teleporter = new TeleporterVarda(MinecraftServer.getServer().worldServerForDimension(AliensVsPredator.properties().DIMENSION_ID_LV223));
			serverManager.transferPlayerToDimension(player, AliensVsPredator.properties().DIMENSION_ID_LV223, teleporter);
		} else if (player.dimension == AliensVsPredator.properties().DIMENSION_ID_LV223)
		{
			TeleporterVarda teleporter = new TeleporterVarda(MinecraftServer.getServer().worldServerForDimension(0));
			serverManager.transferPlayerToDimension(player, 0, teleporter);
		} else
		{
			TeleporterVarda teleporter = new TeleporterVarda(MinecraftServer.getServer().worldServerForDimension(player.dimension));
			serverManager.transferPlayerToDimension(player, AliensVsPredator.properties().DIMENSION_ID_LV223, teleporter);
		}
	}
}
