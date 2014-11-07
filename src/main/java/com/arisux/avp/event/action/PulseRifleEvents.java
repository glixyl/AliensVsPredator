package com.arisux.avp.event.action;

import net.minecraft.client.Minecraft;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.ItemFirearm;
import com.arisux.avp.packets.server.PacketGrenadeLaunchServerUpdate;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;

public class PulseRifleEvents
{
	private Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void onItemUse(ClientTickEvent event)
	{
		if (mc.thePlayer != null)
		{
			if (mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() == AliensVsPredator.INSTANCE.items.itemM41A)
			{
				if (AliensVsPredator.INSTANCE.keybinds.KEYBIND_ITEM_ACTION.isPressed())
				{
					((ItemFirearm) mc.thePlayer.getCurrentEquippedItem().getItem()).cancelRightClick = true;
					Minecraft.getMinecraft().rightClickDelayTimer = 20;
					AliensVsPredator.INSTANCE.network.sendToServer(new PacketGrenadeLaunchServerUpdate());
				}
			}
		}
	}
}