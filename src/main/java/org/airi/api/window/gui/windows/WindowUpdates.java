package com.arisux.airi.api.window.gui.windows;

import java.util.ArrayList;

import com.arisux.airi.AIRI;
import com.arisux.airi.api.updater.Changelog;
import com.arisux.airi.api.updater.Updater;
import com.arisux.airi.api.window.IWindow;
import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.GuiElements.GuiCustomButton;
import com.arisux.airi.lib.ModUtil;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.interfaces.IActionPerformed;

import cpw.mods.fml.common.ModContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class WindowUpdates extends Window implements IWindow
{
    private Updater updater;
    private GuiCustomButton buttonNext = new GuiCustomButton(customButtonList, 0, 0, 0, 20, 20, "notification.next", null);
    private GuiCustomButton buttonPrevious = new GuiCustomButton(customButtonList, 0, 0, 0, 20, 20, "notification.previous", null);

    public WindowUpdates(String id, String title, int xPos, int yPos, int width, int height)
    {
        super(id, title, xPos, yPos, width, height);
        this.updater = AIRI.updaterApi().getAvailableUpdates().get(0);
    }

    @Override
    public void close()
    {
        super.close();
    }

    @Override
    public void draw(int mouseX, int mouseY)
    {
        super.draw(mouseX, mouseY);

        this.setTitle((AIRI.updaterApi().getAvailableUpdates().size() > 1 ? "Updates" : "Update") + " Available - " + (AIRI.updaterApi().getAvailableUpdates().indexOf(updater) + 1) + " of " + AIRI.updaterApi().getAvailableUpdates().size());
        ModContainer modContainer = ModUtil.getModContainerForId(updater.getVersionData().get("MODID"));
        String message = (modContainer != null ? modContainer.getName() : updater.getModId()) + " " + updater.getVersionData().get("MODVER") + " for Minecraft " + updater.getVersionData().get("MCVER");

        RenderUtil.drawStringAlignCenter(message, this.xPos + this.width / 2, this.yPos + 10, this.manager.getWindowAPI().getCurrentTheme().getButtonColor(), false);
        RenderUtil.drawStringAlignCenter("Minecraft Forge " + updater.getVersionData().get("FORGEVER"), this.xPos + this.width / 2, this.yPos + 20, this.manager.getWindowAPI().getCurrentTheme().getTextColor() << 1, false);

        if (updater.getChangelog() != null && !updater.getChangelog().equals(""))
        {
            GlStateManager.pushMatrix();
            {
                Changelog.SubChangelog changelog = updater.getChangelog().getChangelogByVersion(updater.getVersionData().get("MODVER"));

                if (changelog == null || changelog != null && changelog.equals(""))
                {
                    if (updater.getChangelog() != null)
                    {
                        if (updater.getChangelog().getSubLogs() != null && updater.getChangelog().getSubLogs().size() > 0)
                        {
                            changelog = updater.getChangelog().getSubLogs().get(0);
                        }
                    }
                }

                if (changelog != null && !changelog.equals(""))
                {
                    RenderUtil.drawRectWithOutline(this.xPos + 5, this.yPos + 35, this.width - 10, this.height - 40, 1, 0xFF000000, 0xFF111111);
                    GlStateManager.scale(0.5F, 0.5F, 0.5F);

                    String[] lines = changelog.getContents().split("\n");
                    String longestLine = "";

                    for (int x = lines.length; x > 0; x--)
                    {
                        Minecraft.getMinecraft().fontRendererObj.drawString(lines[x - 1], this.xPos * 2 + 20, this.yPos * 2 + 70 + (x * 10), 0xFF444444);

                        if (RenderUtil.getStringRenderWidth(lines[x - 1]) > RenderUtil.getStringRenderWidth(longestLine))
                        {
                            longestLine = lines[x - 1];
                            this.width = RenderUtil.getStringRenderWidth(longestLine) / 2 < RenderUtil.getStringRenderWidth(message) ? RenderUtil.getStringRenderWidth(message) + 10 : RenderUtil.getStringRenderWidth(longestLine) / 2 + 50;
                            this.height = 50 + lines.length * 5;
                        }
                    }
                }
                else
                {
                    this.height = 41;
                }
            }
            GlStateManager.popMatrix();
        }

        this.setWindowCentered();

        // notifications.next
        {
            buttonNext.displayString = ">";
            buttonNext.baseColor = this.manager.getWindowAPI().getCurrentTheme().getButtonColor();
            buttonNext.width = 16;
            buttonNext.height = 16;
            buttonNext.xPosition = this.xPos + this.width - 10;
            buttonNext.yPosition = this.yPos + this.height / 2;
            buttonNext.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
            buttonNext.setAction(new IActionPerformed()
            {
                @Override
                public void actionPerformed(GuiCustomButton button)
                {
                    ArrayList<Updater> updates = AIRI.updaterApi().getAvailableUpdates();
                    updater = updates.get(updates.indexOf(updater) < (updates.size() - 1) ? updates.indexOf(updater) + 1 : 0);
                }
            });
            if (buttonNext.isActive())
            {
                RenderUtil.drawToolTip(mouseX + 10, mouseY, "View next update");
            }
        }

        // notifications.previous
        {
            buttonPrevious.displayString = "<";
            buttonPrevious.baseColor = this.manager.getWindowAPI().getCurrentTheme().getButtonColor();
            buttonPrevious.width = 16;
            buttonPrevious.height = 16;
            buttonPrevious.xPosition = this.xPos - buttonPrevious.width + 10;
            buttonPrevious.yPosition = this.yPos + this.height / 2;
            buttonPrevious.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
            buttonPrevious.setAction(new IActionPerformed()
            {
                @Override
                public void actionPerformed(GuiCustomButton button)
                {
                    ArrayList<Updater> updates = AIRI.updaterApi().getAvailableUpdates();
                    updater = updates.get(updates.indexOf(updater) > 0 && updates.indexOf(updater) <= updates.size() - 1 ? updates.indexOf(updater) - 1 : updates.size() - 1);
                }
            });
            if (buttonPrevious.isActive())
            {
                RenderUtil.drawToolTip(mouseX + 10, mouseY, "View previous update");
            }
        }
    }

    @Override
    public void onButtonPress(GuiButton button)
    {
        ;
    }

    @Override
    public void keyTyped(char c, int id)
    {
        ;
    }
}
