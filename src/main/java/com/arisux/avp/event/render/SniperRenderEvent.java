package com.arisux.avp.event.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

import org.lwjgl.input.Mouse;

import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class SniperRenderEvent
{
	private Minecraft mc = Minecraft.getMinecraft();
	private float defaultFOV = mc.gameSettings.getOptionFloatValue(GameSettings.Options.FOV);
	
	@SubscribeEvent
	public void renderTick(RenderTickEvent event)
	{
		if (mc.thePlayer != null)
		{
			if (mc.gameSettings.thirdPersonView == 0 && mc.thePlayer.getHeldItem() != null)
			{
				if (mc.thePlayer.getHeldItem().getItem() == AliensVsPredator.INSTANCE.items.itemSniper)
				{
					if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
					{
						mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, -1.0F);
					} else if (!Mouse.isButtonDown(0) && mc.inGameHasFocus)
					{
						mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, defaultFOV);
					} else if (mc.inGameHasFocus)
					{
						this.defaultFOV = mc.gameSettings.getOptionFloatValue(GameSettings.Options.FOV);
					}
				}
			}
		}
	}
}
