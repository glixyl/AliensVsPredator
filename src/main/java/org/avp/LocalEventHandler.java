package org.avp;

import java.util.ArrayList;

import org.avp.event.BucketHandlingEvent;
import org.avp.event.EmbryoTickEvent;
import org.avp.event.EntityTrackingEvent;
import org.avp.event.ExtendedPropertiesEvents;
import org.avp.event.FarmlandRegistry;
import org.avp.event.VardaStormHandler;
import org.avp.event.WorldInfoEvent;
import org.avp.event.client.AlienArmorEvents;
import org.avp.event.client.AmmoIndicatorRenderEvent;
import org.avp.event.client.BossBarEvent;
import org.avp.event.client.ChestbursterOverlayEvent;
import org.avp.event.client.CommonFirearmEvents;
import org.avp.event.client.FacehuggerRenderEvent;
import org.avp.event.client.FireAPCEvent;
import org.avp.event.client.LightmapUpdateEvent;
import org.avp.event.client.RenderEntityInMedpodEvent;
import org.avp.event.client.PlayerModeRenderEvent;
import org.avp.event.client.PressureHUDRenderEvent;
import org.avp.event.client.PulseRifleEvents;
import org.avp.event.client.RenderPlayerAPCEvent;
import org.avp.event.client.RenderPlayerHotbarAPCEvent;
import org.avp.event.client.TacticalHUDRenderEvent;
import org.avp.event.client.UserInterfaceEvents;
import org.avp.event.client.VisionModeRenderEvent;
import org.avp.event.client.WristBracerEvents;
import org.avp.items.render.RenderMotionTrackerScreen;

import com.arisux.airi.lib.interfaces.IInitializable;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;

public class LocalEventHandler implements IInitializable
{
    public static final LocalEventHandler instance = new LocalEventHandler();
    public ArrayList<Object> events = new ArrayList<Object>();

    @Override
    public void initialize(FMLInitializationEvent event)
    {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            this.registerEvent(new AlienArmorEvents());
            this.registerEvent(new FireAPCEvent());
            this.registerEvent(new RenderPlayerHotbarAPCEvent());
            this.registerEvent(new ChestbursterOverlayEvent());
            this.registerEvent(new CommonFirearmEvents());
            this.registerEvent(new PlayerModeRenderEvent());
            this.registerEvent(new AmmoIndicatorRenderEvent());
            this.registerEvent(new TacticalHUDRenderEvent());
            this.registerEvent(new PressureHUDRenderEvent());
            this.registerEvent(new FacehuggerRenderEvent());
            this.registerEvent(new VisionModeRenderEvent());
            this.registerEvent(new RenderMotionTrackerScreen());
            this.registerEvent(new LightmapUpdateEvent());
            this.registerEvent(new RenderPlayerAPCEvent());
            this.registerEvent(new UserInterfaceEvents());
            this.registerEvent(new PulseRifleEvents());
            this.registerEvent(new WristBracerEvents());
            this.registerEvent(new BossBarEvent());
            this.registerEvent(new RenderEntityInMedpodEvent());
        }

        if (FMLCommonHandler.instance().getSide() == Side.SERVER)
        {
            this.registerEvent(new DimensionHandler());
        }

        this.registerEvent(new EntityTrackingEvent());
        this.registerEvent(new EmbryoTickEvent());
        this.registerEvent(new ExtendedPropertiesEvents());
        this.registerEvent(new WorldInfoEvent());
        this.registerEvent(VardaStormHandler.INSTANCE);
        this.registerEvent(FarmlandRegistry.INSTANCE);
        this.registerEvent(BucketHandlingEvent.INSTANCE);
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
        return (LightmapUpdateEvent) AliensVsPredator.events().getEvent(LightmapUpdateEvent.class);
    }
}
