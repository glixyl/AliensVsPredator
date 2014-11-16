package com.arisux.avp.event.server;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EventDimensionInitialization
{
	public boolean initializeDimension = true;

	@SideOnly(Side.SERVER)
	@SubscribeEvent
	public void onServerTick(ServerTickEvent event)
	{
		if (FMLCommonHandler.instance() != null && FMLCommonHandler.instance().getMinecraftServerInstance() != null)
		{
			MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
			WorldServer worldServer = server.worldServerForDimension(AliensVsPredator.properties().DIMENSION_ID_LV223);

			if (worldServer != null && worldServer instanceof WorldServer && this.initializeDimension)
			{
				AliensVsPredator.instance.dimensions.initialWorldChunkLoad(worldServer);
				this.initializeDimension = false;
			}
		}
	}
}
