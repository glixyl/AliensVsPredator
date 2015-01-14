package com.arisux.avp.event.action;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class SilicaStormUpdateEvent
{
	private int stormUpdateCount = 0;

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event)
	{
		if (!Minecraft.getMinecraft().isGamePaused())
		{
			this.stormUpdateCount++;
		} 
		else 
		{
			this.stormUpdateCount = 0;
		}
	}

	public int getStormUpdateCount()
	{
		return this.stormUpdateCount;
	}
}
