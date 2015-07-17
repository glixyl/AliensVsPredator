package com.arisux.avp.event.client;

import com.arisux.airi.lib.AccessWrapper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;

public class LightmapUpdateEvent
{
	public float gammaValue = 0F;
	
	@SubscribeEvent
	public void renderTick(RenderTickEvent event)
	{
		this.updateLightmap(event.renderTickTime);
	}
	
	public void updateLightmap(float partialTicks)
	{
		Minecraft mc = Minecraft.getMinecraft();
		WorldClient worldclient = mc.theWorld;

		if (worldclient != null)
		{
			for (int i = 0; i < 256; ++i)
			{
				float f1 = worldclient.getSunBrightness(1.0F) * 0.95F + 0.05F;
				float f2 = worldclient.provider.lightBrightnessTable[i / 16] * f1;
				float f3 = worldclient.provider.lightBrightnessTable[i % 16] * (AccessWrapper.getTorchFlickerX() * 0.1F + 1.5F);

				if (worldclient.lastLightningBolt > 0)
				{
					f2 = worldclient.provider.lightBrightnessTable[i / 16];
				}

				float f4 = f2 * (worldclient.getSunBrightness(1.0F) * 0.65F + 0.35F);
				float f5 = f2 * (worldclient.getSunBrightness(1.0F) * 0.65F + 0.35F);
				float f6 = f3 * ((f3 * 0.6F + 0.4F) * 0.6F + 0.4F);
				float f7 = f3 * (f3 * f3 * 0.6F + 0.4F);
				float f8 = f4 + f3;
				float f9 = f5 + f6;
				float f10 = f2 + f7;
				f8 = f8 * 0.96F + 0.03F;
				f9 = f9 * 0.96F + 0.03F;
				f10 = f10 * 0.96F + 0.03F;
				float gamma;

				if (AccessWrapper.getBossColorModifier() > 0.0F)
				{
					gamma = AccessWrapper.getBossColorModifierPrev() + (AccessWrapper.getBossColorModifier() - AccessWrapper.getBossColorModifierPrev()) * partialTicks;
					f8 = f8 * (1.0F - gamma) + f8 * 0.7F * gamma;
					f9 = f9 * (1.0F - gamma) + f9 * 0.6F * gamma;
					f10 = f10 * (1.0F - gamma) + f10 * 0.6F * gamma;
				}

				if (worldclient.provider.dimensionId == 1)
				{
					f8 = 0.22F + f3 * 0.75F;
					f9 = 0.28F + f6 * 0.75F;
					f10 = 0.25F + f7 * 0.75F;
				}

				float f12;

				if (mc.thePlayer.isPotionActive(Potion.nightVision))
				{
					gamma = this.getNightVisionBrightness(mc.thePlayer, partialTicks);
					f12 = 1.0F / f8;

					if (f12 > 1.0F / f9)
					{
						f12 = 1.0F / f9;
					}

					if (f12 > 1.0F / f10)
					{
						f12 = 1.0F / f10;
					}

					f8 = f8 * (1.0F - gamma) + f8 * f12 * gamma;
					f9 = f9 * (1.0F - gamma) + f9 * f12 * gamma;
					f10 = f10 * (1.0F - gamma) + f10 * f12 * gamma;
				}

				if (f8 > 1.0F)
				{
					f8 = 1.0F;
				}

				if (f9 > 1.0F)
				{
					f9 = 1.0F;
				}

				if (f10 > 1.0F)
				{
					f10 = 1.0F;
				}

				gamma = gammaValue + mc.gameSettings.gammaSetting;
				f12 = 1.0F - f8;
				float f13 = 1.0F - f9;
				float f14 = 1.0F - f10;
				f12 = 1.0F - f12 * f12 * f12 * f12;
				f13 = 1.0F - f13 * f13 * f13 * f13;
				f14 = 1.0F - f14 * f14 * f14 * f14;
				f8 = f8 * (1.0F - gamma) + f12 * gamma;
				f9 = f9 * (1.0F - gamma) + f13 * gamma;
				f10 = f10 * (1.0F - gamma) + f14 * gamma;
				f8 = f8 * 0.96F + 0.03F;
				f9 = f9 * 0.96F + 0.03F;
				f10 = f10 * 0.96F + 0.03F;

				if (f8 > 1.0F)
				{
					f8 = 1.0F;
				}

				if (f9 > 1.0F)
				{
					f9 = 1.0F;
				}

				if (f10 > 1.0F)
				{
					f10 = 1.0F;
				}

				if (f8 < 0.0F)
				{
					f8 = 0.0F;
				}

				if (f9 < 0.0F)
				{
					f9 = 0.0F;
				}

				if (f10 < 0.0F)
				{
					f10 = 0.0F;
				}

				short short1 = 255;
				int j = (int) (f8 * 255.0F);
				int k = (int) (f9 * 255.0F);
				int l = (int) (f10 * 255.0F);
				AccessWrapper.getLightmapColors()[i] = short1 << 24 | j << 16 | k << 8 | l;
			}

			AccessWrapper.getLightmapTexture().updateDynamicTexture();
			AccessWrapper.setLightmapUpdateNeeded(false);
		}
	}

	private float getNightVisionBrightness(EntityPlayer entityPlayer, float partialTicks)
	{
		return entityPlayer.getActivePotionEffect(Potion.nightVision).getDuration() > 200 ? 1.0F : 0.7F + MathHelper.sin((entityPlayer.getActivePotionEffect(Potion.nightVision).getDuration() - partialTicks) * (float) Math.PI * 0.2F) * 0.3F;
	}
}
