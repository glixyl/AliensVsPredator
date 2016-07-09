package com.arisux.airi.api.window;

import net.minecraft.client.gui.GuiButton;

public interface IWindow
{
    public void draw(int mouseX, int mouseY);

    public void close();

    public void onButtonPress(GuiButton button);

    public void keyTyped(char c, int id);

    public void onActivated();
}
