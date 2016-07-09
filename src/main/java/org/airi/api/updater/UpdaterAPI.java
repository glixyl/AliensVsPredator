package com.arisux.airi.api.updater;

import java.util.ArrayList;
import java.util.Iterator;

import com.arisux.airi.AIRI;
import com.arisux.airi.GuiElementHandler;
import com.arisux.airi.api.window.gui.OverlayWindowManager;
import com.arisux.airi.api.window.gui.windows.Window;
import com.arisux.airi.api.window.gui.windows.WindowUpdates;
import com.arisux.airi.lib.ChatUtil;
import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.GuiElements.GuiCustomButton;
import com.arisux.airi.lib.RenderUtil;
import com.arisux.airi.lib.interfaces.IActionPerformed;
import com.arisux.airi.lib.interfaces.IInitializablePre;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.common.MinecraftForge;

public class UpdaterAPI implements IInitializablePre
{
    public static final UpdaterAPI instance = new UpdaterAPI();
    private ArrayList<Updater> updaters = new ArrayList<Updater>();
    private boolean recheckUpdates;
    private boolean canRecheckForUpdates;
    private Window updaterWindow;
    private GuiCustomButton buttonUpdates;

    @Override
    public void preInitialize(FMLPreInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onClientPlayerLogin(PlayerLoggedInEvent event)
    {
        if (AIRI.updaterApi().isUpdateAvailable())
        {
            ChatUtil.sendTo(event.player, "&7[&aAIRI&7] &fNew updates are available. To see them, press SHIFT + TAB.");
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderTitleScreen(TickEvent.RenderTickEvent event)
    {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu)
        {
            int amountOfUpdates = AIRI.updaterApi().getAvailableUpdates().size();

            if (amountOfUpdates > 0)
            {
                if (buttonUpdates == null && GuiElementHandler.instance() != null)
                {
                    buttonUpdates = new GuiCustomButton(0, 0, 0, 0, 0, "", null);
                }

                if (buttonUpdates != null)
                {
                    String updates = String.valueOf(amountOfUpdates);
                    String updateString = amountOfUpdates > 1 ? "Updates" : "Update";
                    int renderWidth = RenderUtil.getStringRenderWidth(updates);
                    int backgroundWidth = 30 + renderWidth + RenderUtil.getStringRenderWidth(updateString);

                    RenderUtil.drawRect(0, 0, backgroundWidth, 18, 0xCC444444);
                    GlStateManager.enableBlend();
                    RenderUtil.drawRectWithOutline(0, 0, 18 + renderWidth, 18, 2, 0x88000000, 0x00000000);
                    RenderUtil.drawString(updates, 10, 5, AIRI.windowApi().getCurrentTheme().getButtonColor(), false);
                    RenderUtil.drawString(updateString, 23 + renderWidth, 5, AIRI.windowApi().getCurrentTheme().getTextColor(), false);

                    buttonUpdates.displayString = "";
                    buttonUpdates.baseColor = 0x00000000;
                    buttonUpdates.overlayColorHover = 0x00000000;
                    buttonUpdates.overlayColorNormal = 0x00000000;
                    buttonUpdates.overlayColorPressed = 0x00000000;
                    buttonUpdates.fontColor = 0xFFFF3333;
                    buttonUpdates.xPosition = 0;
                    buttonUpdates.yPosition = 0;
                    buttonUpdates.width = backgroundWidth;
                    buttonUpdates.height = 18;
                    buttonUpdates.tooltip = "Click here or Press SHIFT + TAB to view.";
                    buttonUpdates.drawButton();
                    buttonUpdates.setAction(new IActionPerformed()
                    {
                        @Override
                        public void actionPerformed(GuiCustomButton button)
                        {
                            AIRI.windowApi().addWindow(AIRI.updaterApi().getUpdaterWindow());
                            AIRI.windowApi().showWindowManager();
                        }
                    });
                }
            }
        }
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event)
    {
        if (AIRI.settings().isNetworkingEnabled())
        {
            if (Minecraft.getMinecraft().theWorld != null && !Minecraft.getMinecraft().isGamePaused() && Minecraft.getMinecraft().theWorld.getWorldTime() % (20 * 60 * 30) == 1)
            {
                this.recheckUpdates = true;
            }

            if (!(Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) && !(Minecraft.getMinecraft().currentScreen instanceof OverlayWindowManager))
            {
                this.canRecheckForUpdates = true;
            }

            if (canRecheckForUpdates)
            {
                if (Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu)
                {
                    this.recheckUpdates = true;
                    this.canRecheckForUpdates = false;
                }
            }

            if (AIRI.updaterApi().isUpdateAvailable())
            {
                if (this.updaterWindow == null)
                {
                    this.updaterWindow = new WindowUpdates("UpdateWindow", "Update Available", -100, 20, 250, 200);

                    if (!AIRI.windowApi().getWindowsRegistry().contains(this.updaterWindow))
                    {
                        AIRI.windowApi().addWindow(this.updaterWindow);
                    }
                }
            }

            for (final Updater updater : this.updaters)
            {
                if (!(Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) && !(Minecraft.getMinecraft().currentScreen instanceof OverlayWindowManager))
                {
                    if (updater.isUpdateAvailable())
                    {
                        if (!AIRI.windowApi().getWindowsRegistry().contains(this.updaterWindow))
                        {
                            AIRI.windowApi().addWindow(this.updaterWindow);
                        }
                    }
                }

                updater.tick();

                if (this.recheckUpdates)
                {
                    if (Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu)
                    {
                        if (recheckUpdates)
                        {
                            (new Thread()
                            {
                                @Override
                                public void run()
                                {
                                    super.run();
                                    updater.downloadVersionInformation();
                                }
                            }).start();
                        }
                    }
                }
            }

            this.recheckUpdates = false;
        }
    }

    public void register(Updater updater)
    {
        this.updaters.add(updater);
    }

    public Updater getUpdaterByModIdentifier(String modid)
    {
        Iterator<Updater> iterator = updaters.iterator();
        Updater updater;

        do
        {
            if (!iterator.hasNext())
                return null;

            updater = iterator.next();
        }
        while (!(updater.getVersionData().get("MODID").equalsIgnoreCase(modid)));

        return updater;
    }

    public ArrayList<Updater> getAvailableUpdates()
    {
        ArrayList<Updater> updates = new ArrayList<Updater>();

        for (Updater updater : this.updaters)
        {
            if (updater.isUpdateAvailable())
            {
                updates.add(updater);
            }
        }

        return updates;
    }

    public ArrayList<Updater> getUpdaterRegistry()
    {
        return this.updaters;
    }

    public void setRecheckForUpdates(boolean b)
    {
        this.recheckUpdates = b;
    }

    public boolean isUpdateAvailable()
    {
        return this.getAvailableUpdates().size() > 0;
    }

    public Window getUpdaterWindow()
    {
        return updaterWindow;
    }
}
