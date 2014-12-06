package com.arisux.avp.event.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.GuiElements.GuiCustomButton;
import com.arisux.airi.lib.*;
import com.arisux.airi.lib.interfaces.IActionPerformed;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.windows.WindowSubmitFeedback;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class BetaReleaseEvents
{
	private Minecraft mc = Minecraft.getMinecraft();
	public WindowSubmitFeedback window = new WindowSubmitFeedback();
	public GuiCustomButton buttonFeedback = new GuiCustomButton(0, 0, 0, 60, 20, "Feedback", null);

	@SubscribeEvent
	public void renderTick(RenderTickEvent event)
	{
		if (AliensVsPredator.instance().isBetaRelease())
		{
			buttonFeedback.xPosition = RenderUtil.scaledDisplayResolution().getScaledWidth() - buttonFeedback.width;
			buttonFeedback.yPosition = mc.currentScreen instanceof GuiMainMenu ? 0 : RenderUtil.scaledDisplayResolution().getScaledHeight() - buttonFeedback.height;
			buttonFeedback.baseColor = 0x55000000;
			buttonFeedback.drawButton();
			buttonFeedback.setAction(new IActionPerformed()
			{
				@Override
				public void actionPerformed(GuiCustomButton button)
				{
					AIRI.windowApi().addWindow(window);
					AIRI.windowApi().showWindowManager();
					window.setWindowCentered();
					window.textbox.setText("");
				}
			});

			if (!ModUtil.isDevEnvironment())
			{
				GL11.glPushMatrix();
				{
					String displayString = "AliensVsPredator BETA Build - Do not redistribute.";
					GL11.glScalef(0.5F, 0.5F, 0.5F);
					RenderUtil.drawString(displayString, 5, 5, 0xFFFF0000, false);
					RenderUtil.drawString(displayString, 5, RenderUtil.scaledDisplayResolution().getScaledHeight() * 2 - 10, 0xFFFF0000, false);
					RenderUtil.drawString(displayString, RenderUtil.scaledDisplayResolution().getScaledWidth() * 2 - RenderUtil.getStringRenderWidth(displayString) - 5, RenderUtil.scaledDisplayResolution().getScaledHeight() * 2 - 10, 0xFFFF0000, false);
					RenderUtil.drawString(displayString, RenderUtil.scaledDisplayResolution().getScaledWidth() * 2 - RenderUtil.getStringRenderWidth(displayString) - 5, 5, 0xFFFF0000, false);
					RenderUtil.drawString(displayString, RenderUtil.scaledDisplayResolution().getScaledWidth() - (RenderUtil.getStringRenderWidth(displayString) - 5) / 2, RenderUtil.scaledDisplayResolution().getScaledHeight() - 2, 0xFFFF0000, false);
				}
				GL11.glPopMatrix();
			}
		}
	}
}
