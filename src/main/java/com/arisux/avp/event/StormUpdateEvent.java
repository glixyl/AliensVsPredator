package com.arisux.avp.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class StormUpdateEvent
{
	private int stormUpdateCount = 0;
	private int cloudTickCounter = 0;

	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event)
	{
		this.updateStorm();
		this.updateClouds(event.world);
	}

	public void updateStorm()
	{
		this.stormUpdateCount++;
	}

	public void updateClouds(World world)
	{
		if (world.isRemote)
		{
			if (!Minecraft.getMinecraft().isGamePaused())
			{
				this.cloudTickCounter++;
			}
		}
	}

	public int getStormUpdateCount()
	{
		return this.stormUpdateCount;
	}

	@SideOnly(Side.CLIENT)
	public int getCloudTickCounter()
	{
		return cloudTickCounter;
	}
}
