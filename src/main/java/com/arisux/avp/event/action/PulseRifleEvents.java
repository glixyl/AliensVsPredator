package com.arisux.avp.event.action;

import net.minecraft.client.Minecraft;

import com.arisux.airi.lib.AccessWrapper;
import com.arisux.avp.AliensVsPredator;
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
			if (mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() == AliensVsPredator.instance().items.itemM41A)
			{
				if (AliensVsPredator.instance().keybinds.KEYBIND_ITEM_ACTION.isPressed())
				{
					//((ItemFirearm) mc.thePlayer.getCurrentEquippedItem().getItem()).cancelRightClick = true;
					AccessWrapper.setRightClickDelayTimer(20);
					AliensVsPredator.instance().network.sendToServer(new PacketGrenadeLaunchServerUpdate());
				}
			}
		}
	}
}