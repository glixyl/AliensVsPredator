package com.arisux.airi.api.window.themes;

import com.arisux.airi.AIRI;
import com.arisux.airi.api.window.gui.taskbar.Taskbar;
import com.arisux.airi.api.window.gui.taskbar.TaskbarEntry;
import com.arisux.airi.api.window.gui.windows.Window;
import com.arisux.airi.lib.ChatUtil;
import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.ModUtil;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.client.ScaledResolution;

public class ThemeModern extends Theme implements ITheme
{
    public ThemeModern(String name)
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
        GlStateManager.enableBlend();
        RenderUtil.drawGradientRect(window.getX(), window.getY() - 2, window.getWidth(), window.getHeight(), 0xFF000000, this.getBackgroundColor());

        GlStateManager.pushMatrix();
        {
            float scale = 0.5F;
            GlStateManager.scale(scale, scale, scale);
            RenderUtil.drawRectWithOutline((int) ((window.getX() - 1) / scale), (int) ((window.getY() - 17) / scale), (int) ((window.getWidth() + 2) / scale), (int) ((window.getHeight() + 16) / scale), 1, 0x00000000, this.getButtonColor());
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void drawTitleBar(Window window, int mouseX, int mouseY)
    {
        RenderUtil.drawRect(window.getX(), window.getY() - 16, window.getWidth() - 15, 14, 0xFF222222);
        RenderUtil.drawString(window.getTitle(), window.getX() + 5, window.getY() - 12, this.getTextColor(), false);
    }

    @Override
    public void drawCloseButton(Window window, int mouseX, int mouseY)
    {
        RenderUtil.drawRect(window.getX() + window.getWidth() - 15, window.getY() - 16, 15, 14, 0xFF222222);
        RenderUtil.drawString("x", window.getX() + window.getWidth() - 10, window.getY() - 14, 0xFFFFFFFF, false);
    }

    @Override
    public void drawTaskbar(Taskbar taskbar, int mouseX, int mouseY)
    {
        ScaledResolution resolution = RenderUtil.scaledDisplayResolution();
        GlStateManager.enableBlend();

        taskbar.setX(0);
        taskbar.setY(resolution.getScaledHeight() - taskbar.getH());
        taskbar.setW(resolution.getScaledWidth());
        taskbar.setH(18);

        int shadowWidth = 6;

        for (int shadowLayer = 0; shadowLayer < shadowWidth; shadowLayer += 2)
        {
            RenderUtil.drawRect(taskbar.getX() - shadowWidth + shadowLayer, taskbar.getY() - shadowWidth + shadowLayer, taskbar.getW() + shadowWidth * 2 - shadowLayer * 2, taskbar.getH() + shadowWidth * 2 - shadowLayer * 2, 0x22000000);
        }

        RenderUtil.drawRect(taskbar.getX(), taskbar.getY(), taskbar.getW(), taskbar.getH(), this.getTaskbarColor());
        RenderUtil.drawString(ChatUtil.format(String.format("&7AIRI %s", ModUtil.getModContainerForId("airi").getVersion())), 4, 4, this.getForegroundColor());

        taskbar.getTaskbarEntries().clear();

        for (Window window : AIRI.windowApi().getWindowsRegistry())
        {
            taskbar.addTaskbarEntry(new TaskbarEntry(window).setText(window.getTitle().substring(0, window.getTitle().length() > 14 ? 14 : window.getTitle().length()) + (window.getTitle().length() < 14 ? "" : "...")));
        }

        for (TaskbarEntry taskbarEntry : taskbar.getTaskbarEntries())
        {
            taskbarEntry.draw(taskbar, mouseX, mouseY);
        }
    }

    @Override
    public void drawTaskbarEntry(TaskbarEntry taskbarEntry, Taskbar taskbar, int mouseX, int mouseY)
    {
        int width = 100;
        int entryX = taskbar.getX() + taskbarEntry.getIndexOnTaskbar(taskbar) * (width + 1);

        RenderUtil.drawRect(entryX, taskbar.getY(), width, taskbar.getH(), taskbarEntry.isActive() ? this.getButtonColor() : 0x22AAAAAA);
        RenderUtil.drawString(taskbarEntry.getText(), entryX + 10, taskbar.getY() + taskbar.getH() / 3, taskbarEntry.isActive() ? this.getForegroundColor() : this.getTextColor(), false);
    }

    @Override
    public int getBackgroundColor()
    {
        return 0xAA000000;
    }

    @Override
    public int getForegroundColor()
    {
        return 0xFFFFFFFF;
    }

    @Override
    public int getTextColor()
    {
        return 0xFFCCCCCC;
    }

    @Override
    public int getButtonColor()
    {
        return 0xFF00AAFF;
    }

    @Override
    public int getTaskbarColor()
    {
        return 0xEE000000;
    }
}
