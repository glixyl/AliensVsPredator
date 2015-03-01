package com.arisux.avp.event.client;

import net.minecraft.client.Minecraft;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.ItemFirearm;
import com.arisux.avp.packets.server.PacketReloadFirearm;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class CommonFirearmEvents
{
	private Minecraft mc = Minecraft.getMinecraft();
	private int lastReload = 0;

	@SubscribeEvent
	public void tick(TickEvent.ClientTickEvent event)
	{
		if (mc.thePlayer != null)
		{
			this.lastReload++;

			if (mc.inGameHasFocus && mc.thePlayer.inventory.getCurrentItem() != null && mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemFirearm)
			{
				ItemFirearm fireArm = (ItemFirearm) mc.thePlayer.inventory.getCurrentItem().getItem();

				if (AliensVsPredator.instance().keybinds.KEYBIND_FIREARM_RELOAD.isPressed() && this.lastReload > fireArm.getReloadRate())
				{
					this.lastReload = 0;
					AliensVsPredator.instance().network.sendToServer(new PacketReloadFirearm());
				}
			}
		}
	}
}
