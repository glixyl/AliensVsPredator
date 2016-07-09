package com.arisux.airi.api.window.gui.taskbar;

import com.arisux.airi.AIRI;

import java.util.ArrayList;

public class Taskbar
{
    private ArrayList<TaskbarEntry> taskbarEntries;

    private int x;
    private int y;
    private int w;
    private int h;

    public Taskbar()
    {
        this.taskbarEntries = new ArrayList<TaskbarEntry>();
    }

    public Taskbar addTaskbarEntry(TaskbarEntry... taskbarEntries)
    {
        for (TaskbarEntry taskbarEntry : taskbarEntries)
        {
            if (!this.taskbarEntries.contains(taskbarEntry))
            {
                this.taskbarEntries.add(taskbarEntry);
            }
        }

        return this;
    }

    public ArrayList<TaskbarEntry> getTaskbarEntries()
    {
        return taskbarEntries;
    }

    public void draw(int mouseX, int mouseY)
    {
        AIRI.windowApi().getCurrentTheme().drawTaskbar(this, mouseX, mouseY);
    }

    public int getX()
    {
        return x;
    }

    public Taskbar setX(int x)
    {
        this.x = x;
        return this;
    }

    public int getY()
    {
        return y;
    }

    public Taskbar setY(int y)
    {
        this.y = y;
        return this;
    }

    public int getW()
    {
        return w;
    }

    public Taskbar setW(int w)
    {
        this.w = w;
        return this;
    }

    public int getH()
    {
        return h;
    }

    public Taskbar setH(int h)
    {
        this.h = h;
        return this;
    }
}
