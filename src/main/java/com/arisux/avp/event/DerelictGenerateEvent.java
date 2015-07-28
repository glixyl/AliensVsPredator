package com.arisux.avp.event;

import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

public class DerelictGenerateEvent
{
	@SubscribeEvent
	public void onWorldSave(WorldEvent.Save event)
	{
		AliensVsPredator.worldgen().getWorldGeneratorDerelict().saveLocations(event);
	}
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event)
	{
		AliensVsPredator.worldgen().getWorldGeneratorDerelict().loadLocations(event);
	}
}
