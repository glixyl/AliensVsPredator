package com.arisux.airi.api.window.gui.taskbar;

import com.arisux.airi.AIRI;
import com.arisux.airi.api.window.gui.windows.Window;

import java.util.ArrayList;

public class TaskbarEntry
{
    /** The Window or sub windows assigned to this TaskbarEntry **/
    protected ArrayList<Window> windows;

    /** The text displayed on the entry in the taskbar **/
    private String text;

    public TaskbarEntry()
    {
        this.windows = new ArrayList<Window>();
        this.updateEntry();
    }

    public TaskbarEntry(Window... windows)
    {
        super();

        for (Window window : windows)
        {
            this.addSubWindow(window);
        }
    }

    public void updateEntry()
    {
        if (!this.windows.isEmpty())
        {
            this.text = this.windows.get(0).getTitle();
        }
    }

    public String getText()
    {
        return this.text;
    }

    public TaskbarEntry setText(String text)
    {
        this.text = text;
        return this;
    }

    public void draw(Taskbar taskbar, int mouseX, int mouseY)
    {
        AIRI.windowApi().getCurrentTheme().drawTaskbarEntry(this, taskbar, mouseX, mouseY);
    }

    public int getIndexOnTaskbar(Taskbar taskbar)
    {
        return taskbar.getTaskbarEntries().indexOf(this);
    }

    public ArrayList<Window> getWindows()
    {
        return windows;
    }

    public TaskbarEntry addSubWindow(Window window)
    {
        if (this.windows == null)
        {
            this.windows = new ArrayList<Window>();
        }

        if (this.windows != null)
        {
            this.windows.add(window);
        }

        return this;
    }

    public boolean isActive()
    {
        if (this.getWindows().get(0) == AIRI.windowApi().getWindowManager().getActiveWindow())
        {
            return true;
        }

        return false;
    }
}
