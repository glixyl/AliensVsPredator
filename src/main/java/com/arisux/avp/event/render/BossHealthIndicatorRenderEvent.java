package com.arisux.avp.event.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.entities.mob.EntityQueen;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class BossHealthIndicatorRenderEvent
{
	private Minecraft mc = Minecraft.getMinecraft();
	
	@SubscribeEvent
	public void renderTick(RenderTickEvent event)
	{
		if (mc.thePlayer != null)
		{
			EntityQueen target = (EntityQueen) mc.thePlayer.worldObj.findNearestEntityWithinAABB(EntityQueen.class, mc.thePlayer.boundingBox.expand(64.0D, 128.0D, 64.0D), mc.thePlayer);

			if (mc.inGameHasFocus && target != null && mc.thePlayer.canEntityBeSeen(target) || target != null && mc.currentScreen instanceof GuiChat && mc.thePlayer.canEntityBeSeen(target))
			{
				RenderUtil.drawProgressBar(target.getCommandSenderName() + " " + (int) target.getHealth() + "/" + (int) target.getMaxHealth(), (int) target.getMaxHealth(), (int) target.getHealth(), 0, 0, RenderUtil.scaledDisplayResolution().getScaledWidth(), 4, 2, 0xFFFF0000, false);
			}
		}
	}
}
