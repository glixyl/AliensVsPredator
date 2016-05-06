package com.arisux.avp.event.client;

import com.arisux.airi.AIRI;
import com.arisux.airi.api.window.gui.OverlayWindowManager;
import com.arisux.airi.lib.GuiElements.GuiCustomButton;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.interfaces.IActionPerformed;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.windows.WindowSubmitFeedback;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;

public class UserInterfaceEvents
{
    private Minecraft mc = Minecraft.getMinecraft();
    public WindowSubmitFeedback window = new WindowSubmitFeedback();
    public GuiCustomButton buttonFeedback = new GuiCustomButton(0, 0, 0, 20, 20, "", null);

    @SubscribeEvent
    public void renderTick(RenderTickEvent event)
    {
        if (AliensVsPredator.instance().isDevCopy() && Minecraft.getMinecraft().currentScreen instanceof OverlayWindowManager)
        {
            buttonFeedback.xPosition = RenderUtil.scaledDisplayResolution().getScaledWidth() - buttonFeedback.width;
            buttonFeedback.yPosition = mc.currentScreen instanceof GuiMainMenu || mc.currentScreen instanceof OverlayWindowManager ? 0 : RenderUtil.scaledDisplayResolution().getScaledHeight() - buttonFeedback.height;
            buttonFeedback.baseColor = 0xAA000000;
            buttonFeedback.width = 80;
            buttonFeedback.displayString = "New Feedback";
            buttonFeedback.drawButton();
            buttonFeedback.setAction(new IActionPerformed()
            {
                @Override
                public void actionPerformed(GuiCustomButton button)
                {
                    window = new WindowSubmitFeedback();
                    AIRI.windowApi().addWindow(window);
                    AIRI.windowApi().showWindowManager();
                    window.setWindowCentered();
                    window.textbox.setText("");
                }
            });
        }
    }
}
