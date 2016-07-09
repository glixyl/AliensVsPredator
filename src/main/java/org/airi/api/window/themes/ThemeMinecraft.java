package com.arisux.airi.api.window.themes;

import com.arisux.airi.AIRI;
import com.arisux.airi.api.window.gui.taskbar.Taskbar;
import com.arisux.airi.api.window.gui.taskbar.TaskbarEntry;
import com.arisux.airi.lib.ChatUtil;
import com.arisux.airi.lib.ModUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;

import com.arisux.airi.api.window.gui.windows.Window;
import com.arisux.airi.lib.RenderUtil;

public class ThemeMinecraft extends Theme implements ITheme
{
    public ThemeMinecraft(String name)
    {
        super(name);
        this.setName(name);
    }

    @Override
    public void draw(Window window, int mouseX, int mouseY)
    {
        super.draw(window, mouseX, mouseY);
    }

    @Override
    public void drawBackground(Window window, int mouseX, int mouseY)
    {
        RenderUtil.drawBlockSide(Blocks.stone_slab, 2, window.getX(), window.getY() - 16, window.getWidth(), 16, window.getWidth() / 50f, 0.5f);
    }

    @Override
    public void drawTitleBar(Window window, int mouseX, int mouseY)
    {
        RenderUtil.drawBlockSide(Blocks.bedrock, 0, window.getX(), window.getY(), window.getWidth(), window.getHeight(), window.getWidth() / 50f, window.getHeight() / 50f);
        getWindowManager().drawCenteredString(Minecraft.getMinecraft().fontRendererObj, window.getTitle(), window.getX() + window.getWidth() / 2, window.getY() - 12, 0xFFFFFF);
    }

    @Override
    public void drawCloseButton(Window window, int mouseX, int mouseY)
    {
        RenderUtil.drawBlockSide(Blocks.tnt, 2, window.getX() + window.getWidth() - 14, window.getY() - 14, 12, 12);
    }

    @Override
    public void drawTaskbar(Taskbar taskbar, int mouseX, int mouseY)
    {
        int height = 15;
        int shadowWidth = 6;

        for (int shadowLayer = 0; shadowLayer < shadowWidth; shadowLayer += 2)
        {
            RenderUtil.drawRect(0 - shadowWidth + shadowLayer, 0 - 16 - shadowWidth + shadowLayer, RenderUtil.scaledDisplayResolution().getScaledWidth() + shadowWidth * 2 - shadowLayer * 2, height + shadowWidth * 2 + 16 - shadowLayer * 2, 0x11000000);
        }

        RenderUtil.drawRect(0, 0, RenderUtil.scaledDisplayResolution().getScaledWidth(), height, this.getTaskbarColor());
        RenderUtil.drawString(ChatUtil.format(String.format("&7AIRI %s &8Press ESC to hide. Press LEFT ALT + W to show.", ModUtil.getModContainerForId("airi").getVersion())), 4, 4, this.getForegroundColor());

        for (TaskbarEntry taskbarEntry : taskbar.getTaskbarEntries())
        {
            this.drawTaskbarEntry(taskbarEntry, taskbar, mouseX, mouseY);
        }
    }

    @Override
    public void drawTaskbarEntry(TaskbarEntry taskbarEntry, Taskbar taskbar, int mouseX, int mouseY)
    {
        int iconPadding = 16;
        int index = 0;
        int y = (20 + iconPadding * index++);
        RenderUtil.drawRect(4, y, 100 + 5, 13, 0xAA000000);
        RenderUtil.drawString(taskbarEntry.getText(), 7, y + 3, AIRI.windowApi().getCurrentTheme().getButtonColor(), false);
    }

    @Override
    public int getTextColor()
    {
        return 0xFFFFFFFF;
    }

    @Override
    public int getForegroundColor()
    {
        return 0xFFAAAAAA;
    }

    @Override
    public int getBackgroundColor()
    {
        return 0x666666;
    }

    @Override
    public int getButtonColor()
    {
        return 0xFF00AAFF;
    }

    @Override
    public int getTaskbarColor()
    {
        return 0xAA000000;
    }
}
