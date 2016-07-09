package com.arisux.airi.api.window.themes;

import com.arisux.airi.api.window.gui.taskbar.Taskbar;
import com.arisux.airi.api.window.gui.taskbar.TaskbarEntry;
import com.arisux.airi.api.window.gui.windows.Window;

public interface ITheme
{
    public void draw(Window window, int mouseX, int mouseY);

    public void drawBackground(Window w, int mouseX, int mouseY);

    public void drawTitleBar(Window w, int mouseX, int mouseY);

    public void drawCloseButton(Window w, int mouseX, int mouseY);

    public void drawTaskbar(Taskbar taskbar, int mouseX, int mouseY);

    public void drawTaskbarEntry(TaskbarEntry taskbarEntry, Taskbar taskbar, int mouseX, int mouseY);

    public int getTextColor();

    public int getForegroundColor();

    public int getBackgroundColor();

    public int getButtonColor();

    public int getTaskbarColor();
}
