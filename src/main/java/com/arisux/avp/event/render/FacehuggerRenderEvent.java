package com.arisux.avp.event.render;

import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityFacehugger;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class FacehuggerRenderEvent
{
	private Minecraft mc = Minecraft.getMinecraft();
	private ResourceLocation resOverlayFacehugger = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_BLUR_FACEHUGGER);

	@SubscribeEvent
	public void renderTickOverlay(Pre event)
	{
		if (mc.thePlayer != null)
		{
			if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
			{
				if (mc.gameSettings.thirdPersonView == 0 && mc.thePlayer.riddenByEntity != null && mc.thePlayer.riddenByEntity instanceof EntityFacehugger)
				{
					GL11.glPushMatrix();
					{
						glRotatef(180F, 0, 0, 1);
						glTranslatef(-RenderUtil.scaledDisplayResolution().getScaledWidth(), -RenderUtil.scaledDisplayResolution().getScaledHeight(), 0);
						RenderUtil.renderOverlay(resOverlayFacehugger);
					}
					GL11.glPopMatrix();
				}
			}
		}
	}
}
