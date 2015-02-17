package com.arisux.avp;

import java.util.ArrayList;

import net.minecraftforge.common.MinecraftForge;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.event.*;
import com.arisux.avp.event.client.*;
import com.arisux.avp.event.server.EventDimensionInitialization;
import com.arisux.avp.items.render.RenderMotionTrackerScreen;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LocalEventHandler implements IInitializable
{
	public ArrayList<Object> events = new ArrayList<Object>();

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			this.registerEvent(new AlienArmorEvents());
			this.registerEvent(new ChestbursterOverlayEvent());
			this.registerEvent(new CommonFirearmEvents());
			this.registerEvent(new PlayerModeRenderEvent());
			this.registerEvent(new AmmoIndicatorRenderEvent());
			this.registerEvent(new DebugToolsRenderEvent());
			this.registerEvent(new TacticalHUDRenderEvent());
			this.registerEvent(new FacehuggerRenderEvent());
			this.registerEvent(new VisionModeRenderEvent());
			this.registerEvent(new RenderMotionTrackerScreen());
			this.registerEvent(new LightmapUpdateEvent());
			this.registerEvent(new UserInterfaceEvents());
			this.registerEvent(new PulseRifleEvents());
			this.registerEvent(new WristBracerEvents());
		}

		if (FMLCommonHandler.instance().getSide() == Side.SERVER)
		{
			this.registerEvent(new EventDimensionInitialization());
		}

		this.registerEvent(new EmbryoTickEvent());
		this.registerEvent(new StormUpdateEvent());
		this.registerEvent(new ExtendedPropertiesEvents());
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

	@SideOnly(Side.CLIENT)
	public LightmapUpdateEvent getLightmapUpdateEvent()
	{
		return (LightmapUpdateEvent) AliensVsPredator.instance().localEvents.getEvent(LightmapUpdateEvent.class);
	}
}
