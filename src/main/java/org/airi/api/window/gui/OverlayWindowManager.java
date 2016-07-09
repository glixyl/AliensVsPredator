package com.arisux.airi.api.window.gui;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.arisux.airi.api.window.WindowAPI;
import com.arisux.airi.api.window.gui.taskbar.Taskbar;
import com.arisux.airi.api.window.gui.windows.Window;
import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.GuiElements.GuiCustomScreen;
import com.arisux.airi.lib.RenderUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

@SideOnly(Side.CLIENT)
public class OverlayWindowManager extends GuiCustomScreen
{
    protected Taskbar taskbar;
    private WindowAPI windowapi;
    private Window resetWindow;
    private Window draggedWindow;
    private Window topWindow;
    public GuiScreen parentScreen;
    private int mouseXLast;
    private int mouseYLast;

    public OverlayWindowManager(WindowAPI windowapi, GuiScreen parentScreen)
    {
        this.windowapi = windowapi;
        this.parentScreen = parentScreen;
        this.taskbar = new Taskbar();
    }

    @Override
    public void initGui()
    {
        this.buttonList.clear();
    }

    public GuiScreen getParentScreen()
    {
        return parentScreen;
    }

    @Override
    protected void keyTyped(char par1, int key)
    {
        if (key == Keyboard.KEY_ESCAPE)
        {
            Minecraft.getMinecraft().currentScreen = parentScreen;
        }

        if (topWindow != null)
        {
            topWindow.keyTyped(par1, key);
        }
    }

    public Window getTopWindow(int mouseX, int mouseY)
    {
        Window top = null;

        for (Window window : windowapi.getWindowsRegistry())
        {
            if (mouseX > window.getX() && mouseX < window.getX() + window.getWidth() && mouseY > window.getY() - 16 && mouseY < window.getY() + window.getHeight())
            {
                top = window;
            }
        }

        return top;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int par3)
    {
        super.mouseClicked(mouseX, mouseY, par3);

        this.topWindow = getTopWindow(mouseX, mouseY);

        if (this.topWindow != null)
        {
            this.topWindow.onActivated();

            if (mouseX > this.topWindow.getX() + this.topWindow.getWidth() - 14 && mouseX < this.topWindow.getX() + this.topWindow.getWidth() - 2 && mouseY < this.topWindow.getY() - 2 && mouseY > this.topWindow.getY() - 14)
            {
                this.topWindow.close();
            }
        }

        for (Window window : this.windowapi.getWindowsRegistry())
        {
            if (this.topWindow == window)
            {
                this.resetWindow = window;

                window.onMousePressed(mouseX, mouseY);

                if (mouseX > window.getX() && mouseX < window.getX() + window.getWidth() && mouseY > window.getY() - 16 && mouseY < window.getY())
                {
                    this.draggedWindow = window;
                    this.mouseXLast = mouseX;
                    this.mouseYLast = mouseY;
                }

                for (GuiButton button : window.getButtonList())
                {
                    if (button.mousePressed(this.mc, mouseX, mouseY))
                    {
                        button.playPressSound(this.mc.getSoundHandler());
                        window.onButtonPress(button);
                    }
                }
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float par3)
    {
        if (parentScreen != null)
        {
            parentScreen.drawScreen(mouseX, mouseY, par3);
        }

        GlStateManager.enableBlend();
        RenderUtil.drawRect(0, 0, RenderUtil.scaledDisplayResolution().getScaledWidth(), RenderUtil.scaledDisplayResolution().getScaledHeight(), 0x88000000);
        this.taskbar.draw(mouseX, mouseY);
        GlStateManager.color(1F, 1F, 1F);

        if (resetWindow != null)
        {
            // windowapi.getWindowsRegistry().remove(resetWindow);
            // windowapi.getWindowsRegistry().add(windowapi.getWindowsRegistry().size(), resetWindow);
            resetWindow = null;
        }

        for (int x = windowapi.getWindowsRegistry().size() - 1; x >= 0; x--)
        {
            Window window = windowapi.getWindowsRegistry().get(x);

            if (Mouse.isButtonDown(0))
            {
                if (draggedWindow == window)
                {
                    int diffX = mouseX - mouseXLast;
                    int diffY = mouseY - mouseYLast;
                    window.setPosition(window.getX() + diffX, window.getY() + diffY);
                    mouseXLast = mouseX;
                    mouseYLast = mouseY;
                }
            }
            else
            {
                draggedWindow = null;
            }
        }

        for (Window window : new ArrayList<Window>(windowapi.getWindowsRegistry()))
        {
            if (window != null && windowapi.getWindowsRegistry().contains(window))
            {
                windowapi.drawWindow(window, mouseX, mouseY);
            }
        }

        super.drawScreen(mouseX, mouseY, par3);
    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height)
    {
        if (parentScreen != null)
        {
            parentScreen.setWorldAndResolution(mc, width, height);
        }

        super.setWorldAndResolution(mc, width, height);
    }

    @Override
    protected void actionPerformed(GuiButton b)
    {
        for (Window window : windowapi.getWindowsRegistry())
        {
            window.onButtonPress(b);
        }
    }

    public WindowAPI getWindowAPI()
    {
        return this.windowapi;
    }

    public Taskbar getTaskbar()
    {
        return this.taskbar;
    }

    public Window getActiveWindow()
    {
        return this.topWindow;
    }
}
