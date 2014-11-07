package com.arisux.avp;

import java.util.ArrayList;

import net.minecraftforge.common.MinecraftForge;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.event.action.*;
import com.arisux.avp.event.render.*;
import com.arisux.avp.event.server.EventDimensionInitialization;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class LocalEventHandler implements IInitializable
{
	public ArrayList<Object> events = new ArrayList<Object>();

	public void initialize()
	{
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			this.registerEvent(new AlienArmorEvents());
			this.registerEvent(new AlienEmergeEvent());
			this.registerEvent(new CommonFirearmEvents());
			this.registerEvent(new PlayerRenderXenomorphEvent());
			this.registerEvent(new BossHealthIndicatorRenderEvent());
			this.registerEvent(new AmmoIndicatorRenderEvent());
			this.registerEvent(new SniperRenderEvent());
			this.registerEvent(new DebugToolsRenderEvent());
			this.registerEvent(new TacticalHUDRenderEvent());
			this.registerEvent(new FacehuggerRenderEvent());
			this.registerEvent(new VisionModeRenderEvent());
			this.registerEvent(new MotionTrackerDisplay());
			this.registerEvent(new LightmapUpdateEvent());
			this.registerEvent(new FeedbackRenderEvent());
			this.registerEvent(new PulseRifleEvents());
			this.registerEvent(new WristBracerEvents());
		}

		this.registerEvent(new EventDimensionInitialization());
		this.registerEvent(new PlayerPropertiesEvents());
	}

	public void registerEvent(Object event)
	{
		this.events.add(event);
		FMLCommonHandler.instance().bus().register(event);
		MinecraftForge.EVENT_BUS.register(event);
	}

	public Object getEvent(Class<?> c)
	{
		Object event = null;

		for (Object e : events)
		{
			if (e.getClass() == c)
			{
				event = e;
			}
		}

		return event;
	}
}
