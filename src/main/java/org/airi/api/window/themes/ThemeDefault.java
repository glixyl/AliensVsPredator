package com.arisux.airi.api.window.themes;

import com.arisux.airi.AIRI;
import com.arisux.airi.api.window.gui.taskbar.Taskbar;
import com.arisux.airi.api.window.gui.taskbar.TaskbarEntry;
import com.arisux.airi.api.window.gui.windows.Window;
import com.arisux.airi.lib.ChatUtil;
import com.arisux.airi.lib.ModUtil;
import com.arisux.airi.lib.RenderUtil;

public class ThemeDefault extends Theme implements ITheme
{
    public ThemeDefault(String name)
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
        RenderUtil.drawGradientRect(window.getX(), window.getY() - 2, window.getWidth(), window.getHeight(), 0xFF000000, 0xAA001122);
    }

    @Override
    public void drawTitleBar(Window window, int mouseX, int mouseY)
    {
        RenderUtil.drawRect(window.getX(), window.getY() - 16, window.getWidth() - 15, 14, 0xBB000000);
        RenderUtil.drawString(window.getTitle(), window.getX() + 5, window.getY() - 12, 0xFF0088FF, false);
    }

    @Override
    public void drawCloseButton(Window window, int mouseX, int mouseY)
    {
        RenderUtil.drawRect(window.getX() + window.getWidth() - 15, window.getY() - 16, 15, 14, 0x22CCCCCC);
        RenderUtil.drawString("x", window.getX() + window.getWidth() - 10, window.getY() - 14, 0x330088FF, false);
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
        return 0xFFEEEEEE;
    }

    @Override
    public int getBackgroundColor()
    {
        return 0xAA000000;
    }

    @Override
    public int getButtonColor()
    {
        return 0xAAFFFFFF;
    }

    @Override
    public int getTaskbarColor()
    {
        return 0xAA000000;
    }
}
