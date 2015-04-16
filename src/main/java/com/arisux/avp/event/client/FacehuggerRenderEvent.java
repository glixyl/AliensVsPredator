package com.arisux.avp.event.client;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityFacehugger;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import org.lwjgl.opengl.GL11;

public class FacehuggerRenderEvent
{
	private Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void renderTickOverlay(RenderGameOverlayEvent.Pre event)
	{
		if (mc.thePlayer != null)
		{
			if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
			{
				if (mc.gameSettings.thirdPersonView == 0 && mc.thePlayer.riddenByEntity != null && mc.thePlayer.riddenByEntity instanceof EntityFacehugger)
				{
					GL11.glPushMatrix();
					{
						RenderUtil.renderOverlay(AliensVsPredator.resources().BLUR_FACEHUGGER);
					}
					GL11.glPopMatrix();
				}
			}
		}
	}

	@SubscribeEvent
	public void entityRenderEvent(RenderLivingEvent.Pre event)
	{
		;
	}

	@SubscribeEvent
	public void entityRenderEvent(RenderLivingEvent.Post event)
	{
		;
	}
}
