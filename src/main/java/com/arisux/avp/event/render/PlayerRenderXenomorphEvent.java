package com.arisux.avp.event.render;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.packets.server.PacketInvisiblePlayerServerUpdate;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class PlayerRenderXenomorphEvent
{
	private Minecraft mc = Minecraft.getMinecraft();
	public ItemStack helmSlot, chestplateSlot, leggingsSlot, bootsSlot;
	
	@SubscribeEvent
	public void renderTick(RenderTickEvent event)
	{
		if (mc.thePlayer != null)
		{
			helmSlot = mc.thePlayer.inventory.armorItemInSlot(3);
			chestplateSlot = mc.thePlayer.inventory.armorItemInSlot(2);
			leggingsSlot = mc.thePlayer.inventory.armorItemInSlot(1);
			bootsSlot = mc.thePlayer.inventory.armorItemInSlot(0);
			
			if (helmSlot != null && chestplateSlot != null && leggingsSlot != null && bootsSlot != null)
			{
				if (!mc.thePlayer.isInvisible() && helmSlot.getItem() == AliensVsPredator.INSTANCE.items.helmXeno && chestplateSlot.getItem() == AliensVsPredator.INSTANCE.items.plateXeno && leggingsSlot.getItem() == AliensVsPredator.INSTANCE.items.legsXeno && bootsSlot.getItem() == AliensVsPredator.INSTANCE.items.bootsXeno)
				{
					AliensVsPredator.INSTANCE.network.sendToServer(new PacketInvisiblePlayerServerUpdate(true));
				} else if (mc.thePlayer.isInvisible() && mc.thePlayer.getActivePotionEffects().contains(Potion.invisibility))
				{
					AliensVsPredator.INSTANCE.network.sendToServer(new PacketInvisiblePlayerServerUpdate(false));
				}
			} else if (mc.thePlayer.isInvisible())
			{
				AliensVsPredator.INSTANCE.network.sendToServer(new PacketInvisiblePlayerServerUpdate(false));
			}
		}
	}
}
