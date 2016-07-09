package com.arisux.airi;

import org.lwjgl.input.Keyboard;

import com.arisux.airi.api.window.gui.OverlayWindowManager;
import com.arisux.airi.api.window.gui.windows.WindowATWarning;
import com.arisux.airi.lib.ModUtil;
import com.arisux.airi.lib.interfaces.IInitializablePre;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ClientSideEvents implements IInitializablePre
{
    private boolean openedInitial;

    @SubscribeEvent
    public void clientTick(ClientTickEvent event)
    {
        AIRI.windowApi().onTick();

        if (!openedInitial)
        {
            AIRI.logger.info("Verifying access transformer...");

            if (!AIRI.COREMOD_INITIALIZED && !ModUtil.isDevEnvironment())
            {
                AIRI.logger.warning("Access transformer could not be verified. If you are in an installation environment, you are going to run into problems!");

                AIRI.windowApi().showWindowManager();
                AIRI.windowApi().addWindow(new WindowATWarning("WarningATNotFound"));
            }
            else
            {
                AIRI.logger.info("Access transformer verified.");
            }

            openedInitial = true;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && Keyboard.isKeyDown(Keyboard.KEY_TAB) && Keyboard.next())
        {
            if (Minecraft.getMinecraft().currentScreen instanceof OverlayWindowManager)
            {
                Minecraft.getMinecraft().displayGuiScreen(null);
            }
            else
            {
                AIRI.windowApi().showWindowManager(true);
            }
        }

        GuiElementHandler.instance().tick();
    }

    @Override
    public void preInitialize(FMLPreInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
