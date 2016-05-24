package org.avp.event.client;

import org.avp.entities.EntityAPC;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class RenderPlayerAPCEvent
{
    @SubscribeEvent
    public void onRenderPlayerEvent(RenderPlayerEvent.Pre event)
    {
        if (event.entityPlayer.isRiding() && event.entityPlayer.ridingEntity instanceof EntityAPC)
        {
            event.setCanceled(true);
        }
    }
}
