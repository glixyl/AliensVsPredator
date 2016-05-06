package com.arisux.avp.event.client;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAPC;
import com.arisux.avp.packets.server.PacketFireAPC;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;

public class FireAPCEvent
{
    private Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event)
    {
        if (mc.thePlayer != null)
        {
            if (mc.thePlayer.isRiding() && mc.thePlayer.ridingEntity instanceof EntityAPC)
            {
                if (AliensVsPredator.keybinds().KEYBIND_FIRE_APC.isPressed())
                {
                    AliensVsPredator.network().sendToServer(new PacketFireAPC());
                }
            }
        }
    }
}
