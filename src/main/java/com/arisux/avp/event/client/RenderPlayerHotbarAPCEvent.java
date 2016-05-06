package com.arisux.avp.event.client;

import com.arisux.avp.entities.EntityAPC;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class RenderPlayerHotbarAPCEvent
{

    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderGameOverlayEvent(RenderGameOverlayEvent.Pre event)
    {
        if (mc.thePlayer.isRiding() && mc.thePlayer.ridingEntity instanceof EntityAPC)
        {
            if (event.type == event.type.HOTBAR)
            {
                event.setCanceled(true);
            }
        }
    }
}
