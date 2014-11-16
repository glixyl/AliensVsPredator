package com.arisux.avp.event.render;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import com.arisux.airi.engine.RenderEngine;
import com.arisux.avp.items.ItemFirearm;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class AmmoIndicatorRenderEvent
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
			
			if (mc.inGameHasFocus && mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemFirearm)
			{
				ItemFirearm itemFireArm = (ItemFirearm) mc.thePlayer.getHeldItem().getItem();

				if (mc.thePlayer.getHeldItem().getItem() == itemFireArm)
				{
					if (!mc.thePlayer.capabilities.isCreativeMode && isWearingArmor())
						RenderEngine.drawProgressBar("Ammo - " + itemFireArm.getCurrentAmmo() + "/" + itemFireArm.getMaxAmmo(), itemFireArm.getMaxAmmo(), itemFireArm.getCurrentAmmo(), ((int) RenderEngine.scaledDisplayResolution().getScaledWidth() / 2), (int) RenderEngine.scaledDisplayResolution().getScaledHeight() - 48, 90, 1, 0, 0xFF00DDFF, false);
					else if (!mc.thePlayer.capabilities.isCreativeMode && !isWearingArmor())
						RenderEngine.drawProgressBar("Ammo - " + itemFireArm.getCurrentAmmo() + "/" + itemFireArm.getMaxAmmo(), itemFireArm.getMaxAmmo(), itemFireArm.getCurrentAmmo(), ((int) RenderEngine.scaledDisplayResolution().getScaledWidth() / 2) - (182 / 2), (int) RenderEngine.scaledDisplayResolution().getScaledHeight() - 48, 182, 1, 0, 0xFF00DDFF, false);
					else
						RenderEngine.drawProgressBar("\u221e", 1, 1, ((int) RenderEngine.scaledDisplayResolution().getScaledWidth() / 2) - (182 / 2), (int) RenderEngine.scaledDisplayResolution().getScaledHeight() - 35, 182, 1, 0, 0xFF00DDFF, false);
				}
			}
		}
	}
	
	public boolean isWearingArmor()
	{
		return helmSlot != null || chestplateSlot != null || leggingsSlot != null || bootsSlot != null;
	}
}
