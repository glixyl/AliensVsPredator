package com.arisux.avp.event.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.GuiTypeLib.GuiCustomButton;
import com.arisux.airi.lib.RenderLib;
import com.arisux.airi.lib.interfaces.IActionPerformed;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.windows.WindowSubmitFeedback;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class FeedbackRenderEvent
{
	private Minecraft mc = Minecraft.getMinecraft();
	public WindowSubmitFeedback window = new WindowSubmitFeedback();
	public GuiCustomButton buttonFeedback = new GuiCustomButton(0, 0, 0, 60, 20, "Feedback", null);
	
	@SubscribeEvent
	public void renderTick(RenderTickEvent event)
	{
		if (AliensVsPredator.INSTANCE.isBetaRelease())
		{
			buttonFeedback.xPosition = RenderLib.scaledDisplayResolution().getScaledWidth() - buttonFeedback.width;
			buttonFeedback.yPosition = mc.currentScreen instanceof GuiMainMenu ? 0 : RenderLib.scaledDisplayResolution().getScaledHeight() - buttonFeedback.height;
			buttonFeedback.baseColor = 0x55000000;
			buttonFeedback.drawButton();
			buttonFeedback.setAction(new IActionPerformed()
			{
				@Override
				public void actionPerformed(GuiCustomButton button)
				{
					AIRI.INSTANCE.windowapi.addWindow(window);
					AIRI.INSTANCE.windowapi.showWindowManager();
					window.setWindowCentered();
					window.textbox.setText("");
				}
			});
			buttonFeedback.handleInput();
		}
	}
}
