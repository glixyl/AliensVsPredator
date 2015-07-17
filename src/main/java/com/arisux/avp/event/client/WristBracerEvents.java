package com.arisux.avp.event.client;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.packets.server.PacketOpenWristbracerContainer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.client.Minecraft;

public class WristBracerEvents
{
	private Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void tick(ClientTickEvent event)
	{
		if (mc.thePlayer != null && mc.thePlayer.getCurrentEquippedItem() != null)
		{
			if (mc.thePlayer.getCurrentEquippedItem().getItem() == AliensVsPredator.items().itemWristBlade && AliensVsPredator.keybinds().KEYBIND_ITEM_ACTION.isPressed())
			{
				AliensVsPredator.network().sendToServer(new PacketOpenWristbracerContainer());
			}
		}
	}
}
