package com.arisux.airi.api.window.gui.windows;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import com.arisux.airi.api.window.IWindow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WindowATWarning extends Window implements IWindow
{
    public WindowATWarning(String id)
    {
        super(id, "WARNING", -100, 100, 350, 90);
    }

    @Override
    public void draw(int mouseX, int mouseY)
    {
        this.setWindowCentered();

        String message1 = "Access Transformer could not be verified. You are either in a development environment or something has gone wrong. If this is not corrected, you will experience unexpected crashes.";
        String message2 = "This can be fixed by changing the file extension of AIRI located in '.minecraft/mods/' from '.zip' to '.jar'. If you do not know how to change the extension of a file, please search the internet for information regarding the subject. -Arisux";

        Minecraft.getMinecraft().fontRendererObj.drawSplitString(message1, this.xPos + 10, this.yPos + 10, width - 20, 0xFF0000);
        Minecraft.getMinecraft().fontRendererObj.drawSplitString(message2, this.xPos + 10, this.yPos + 45, width - 20, 0xFFFFFF);
    }

    @Override
    public void close()
    {
        super.close();
    }

    @Override
    public void onButtonPress(GuiButton button)
    {

    }

    @Override
    public void keyTyped(char c, int id)
    {

    }
}
