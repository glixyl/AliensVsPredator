package com.arisux.avp.event.action;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

import com.arisux.airi.engine.RenderEngine;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.ExtendedEntityPlayer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class AlienEmergeEvent
{
	private Minecraft mc = Minecraft.getMinecraft();
	private ResourceLocation resChestbursterEmerge = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_BLUR_CHESTBURSTER_EMERGE);
	
	@SubscribeEvent
	public void tick(TickEvent.ClientTickEvent event)
	{
		if (mc.thePlayer != null)
		{
			ExtendedEntityPlayer playerProperties = (ExtendedEntityPlayer) mc.thePlayer.getExtendedProperties(ExtendedEntityPlayer.ID_PROPERTIES);

			if (playerProperties.isPlayerImpregnated() && !mc.thePlayer.capabilities.isCreativeMode)
			{
				if (playerProperties.getImpregnatedTime() <= playerProperties.getMaxImpregnatedTime() / 2)
				{
					mc.thePlayer.addPotionEffect(new PotionEffect(Potion.blindness.getId(), playerProperties.getMaxImpregnatedTime() / 2));
				}

				if (!mc.isGamePaused())
				{
					playerProperties.setImpregnatedTime(playerProperties.getImpregnatedTime() - 1);
				}

				if (mc.thePlayer.isDead)
				{
					playerProperties.setImpregnatedTime(playerProperties.getMaxImpregnatedTime());
					playerProperties.setPlayerImpregnated(false);
				}
			}
		}
	}

	@SubscribeEvent
	public void renderTickOverlay(Pre event)
	{
		if (mc.thePlayer != null)
		{
			if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
			{
				ExtendedEntityPlayer playerProperties = (ExtendedEntityPlayer) mc.thePlayer.getExtendedProperties(ExtendedEntityPlayer.ID_PROPERTIES);

				if (playerProperties.isPlayerImpregnated() && !mc.thePlayer.capabilities.isCreativeMode)
				{
					if (playerProperties.getImpregnatedTime() <= 0)
					{
						RenderEngine.renderOverlay(resChestbursterEmerge);
					}
				}
			}
		}
	}
}
