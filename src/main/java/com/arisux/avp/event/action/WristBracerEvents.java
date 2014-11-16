package com.arisux.avp.event.action;

import net.minecraft.client.Minecraft;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.packets.server.PacketOpenWristbracerGUIServerUpdate;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;

public class WristBracerEvents
{
	private Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void tick(ClientTickEvent event)
	{
		if (mc.thePlayer != null && mc.thePlayer.getCurrentEquippedItem() != null)
		{
			if (mc.thePlayer.getCurrentEquippedItem().getItem() == AliensVsPredator.instance.items.itemWristBlade && AliensVsPredator.instance.keybinds.KEYBIND_ITEM_ACTION.isPressed())
			{
				AliensVsPredator.instance.network.sendToServer(new PacketOpenWristbracerGUIServerUpdate());
			}
		}
	}
}
