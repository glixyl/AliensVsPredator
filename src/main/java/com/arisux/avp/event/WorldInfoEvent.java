package com.arisux.avp.event;

import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

public class WorldInfoEvent
{
	@SubscribeEvent
	public void onWorldSave(WorldEvent.Save event)
	{
		//AliensVsPredator.worldgen().getSaveHandler().saveData(event.world);
	}
	
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event)
	{
		//AliensVsPredator.worldgen().getSaveHandler().loadData(event.world);
	}
}
