package com.arisux.airi;

import java.util.ArrayList;

import javax.vecmath.Vector2d;

import org.lwjgl.input.Mouse;

import com.arisux.airi.lib.GuiElements.GuiCustomSlider;
import com.arisux.airi.lib.GuiElements.GuiCustomTextbox;
import com.arisux.airi.lib.GuiElements.IGuiElement;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.interfaces.IInitializablePre;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.gameevent.InputEvent.MouseInputEvent;
import net.minecraft.client.Minecraft;

public class GuiElementHandler implements IInitializablePre
{
    private ArrayList<IGuiElement> guiElements = new ArrayList<IGuiElement>();

    public static GuiElementHandler instance()
    {
        return AIRI.instance().guiElementHandler;
    }

    @Override
    public void preInitialize(FMLPreInitializationEvent event)
    {
        ;
    }

    public void tick()
    {
        Vector2d mousePosition = RenderUtil.scaledMousePosition();

        for (int x = 0; x < guiElements.size(); x++)
        {
            IGuiElement element = guiElements.get(x);

            if (element != null)
            {
                handleButtonInput(null, element, mousePosition);

                if (element instanceof GuiCustomTextbox)
                {
                    GuiCustomTextbox textbox = (GuiCustomTextbox) element;

                    if (textbox != null && Mouse.isButtonDown(0))
                    {
                        if (textbox.isActive())
                        {
                            textbox.setFocused(true);
                        }
                        else
                        {
                            textbox.setFocused(false);
                        }
                    }
                }
            }
        }
    }

    private static void handleButtonInput(MouseInputEvent event, IGuiElement element, Vector2d mousePosition)
    {
        if (!Minecraft.getMinecraft().inGameHasFocus && element.isActive())
        {
            if (Mouse.getEventButtonState())
            {
                element.mousePressed(mousePosition);
            }
            else if (Mouse.isButtonDown(0))
            {
                element.mouseReleased(mousePosition);
            }

            if (Mouse.isButtonDown(0))
            {
                element.mouseDragged(mousePosition);
            }
        }

        if (element instanceof GuiCustomSlider)
        {
            GuiCustomSlider slider = (GuiCustomSlider) element;

            if (!Mouse.getEventButtonState() && Mouse.getEventButton() != -1)
            {
                slider.dragging = false;
            }
        }
    }

    public void add(IGuiElement element)
    {
        this.remove(element);

        if (!guiElements.contains(element))
        {
            guiElements.add(element);
        }
    }

    public void remove(IGuiElement element)
    {
        if (guiElements.contains(element))
        {
            guiElements.remove(element);
        }
    }
}
