package com.arisux.avp;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.world.*;
import net.minecraftforge.common.DimensionManager;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.dimension.TeleporterLV;
import com.arisux.avp.dimension.acheron.ProviderAcheron;
import com.arisux.avp.dimension.varda.ProviderVarda;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DimensionHandler implements IInitializable
{
	public static final DimensionHandler instance = new DimensionHandler();
	public boolean dimensionsInitialized;
	
	@Override
	public void initialize(FMLInitializationEvent event)
	{
		registerDimension(AliensVsPredator.settings().dimensionIdAcheron(), ProviderAcheron.class, true);
		registerDimension(AliensVsPredator.settings().dimensionIdVarda(), ProviderVarda.class, true);
	}

	@SideOnly(Side.SERVER)
	@SubscribeEvent
	public void onServerTick(ServerTickEvent event)
	{
		if (FMLCommonHandler.instance() != null && FMLCommonHandler.instance().getMinecraftServerInstance() != null && !this.dimensionsInitialized)
		{
			tryLoadDimension(AliensVsPredator.settings().dimensionIdAcheron());
			tryLoadDimension(AliensVsPredator.settings().dimensionIdVarda());

			this.dimensionsInitialized = true;
		}
	}

	public void initialWorldChunkLoad(WorldServer worldServerObj)
	{
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		long startTime = System.currentTimeMillis();
		short chunkRadius = 196;

		for (int chunkX = -chunkRadius; (chunkX <= chunkRadius) && (server.isServerRunning()); chunkX += 16)
		{
			for (int chunkZ = -chunkRadius; (chunkZ <= chunkRadius) && (server.isServerRunning()); chunkZ += 16)
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

	public static void teleportPlayerToDimension(EntityPlayerMP player, int dimensionId)
	{
		ServerConfigurationManager serverManager = MinecraftServer.getServer().getConfigurationManager();

		if (player.dimension == 0 || player.dimension != dimensionId)
		{
			Teleporter teleporter = new TeleporterLV(MinecraftServer.getServer().worldServerForDimension(dimensionId));
			serverManager.transferPlayerToDimension(player, dimensionId, teleporter);
		} else if (player.dimension == dimensionId)
		{
			Teleporter teleporter = new TeleporterLV(MinecraftServer.getServer().worldServerForDimension(0));
			serverManager.transferPlayerToDimension(player, 0, teleporter);
		} else
		{
			Teleporter teleporter = new TeleporterLV(MinecraftServer.getServer().worldServerForDimension(dimensionId));
			serverManager.transferPlayerToDimension(player, dimensionId, teleporter);
		}
	}
	
	public void tryLoadDimension(int dimensionId)
	{
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		WorldServer worldServer = server.worldServerForDimension(dimensionId);
		
		if (worldServer != null && worldServer instanceof WorldServer)
		{
			server.logInfo("Preparing start region for level " + worldServer.provider.getDimensionName());
			initialWorldChunkLoad(worldServer);
		}
	}
	
	public void registerDimension(int dimensionId, Class<? extends WorldProvider> provider, boolean keepLoaded)
	{
		DimensionManager.registerProviderType(dimensionId, provider, keepLoaded);
		DimensionManager.registerDimension(dimensionId, dimensionId);
	}
}
