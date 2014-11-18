package com.arisux.avp.event.action;

import net.minecraft.client.Minecraft;

import com.arisux.airi.engine.WorldEngine;
import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class AlienArmorEvents
{
	private Minecraft mc = Minecraft.getMinecraft();
	
	@SubscribeEvent
	public void tick(TickEvent.ClientTickEvent event)
	{
		if (mc.thePlayer != null)
		{
			if (WorldEngine.Entities.Players.Inventories.getLegsSlotItemStack(mc.thePlayer) != null)
			{
				if (WorldEngine.Entities.Players.Inventories.getLegsSlotItemStack(mc.thePlayer).getItem() == AliensVsPredator.instance().items.legsXeno && mc.gameSettings.keyBindForward.isPressed() && AliensVsPredator.instance().keybinds.KEYBIND_XENO_ARMOR_CLIMB.isPressed() && mc.thePlayer.isCollidedHorizontally)
				{
					mc.thePlayer.motionY = 0.3D;
					mc.thePlayer.setAIMoveSpeed(5.5F);
					mc.thePlayer.fallDistance = -0.5F;
				}
			}
		}
	}
}
