package com.arisux.airi;

import java.util.ArrayList;

import com.arisux.airi.api.remapping.RemappingAPI;
import com.arisux.airi.api.updater.UpdaterAPI;
import com.arisux.airi.api.wavefrontapi.WavefrontAPI;
import com.arisux.airi.api.window.WindowAPI;
import com.arisux.airi.lib.BlockTypes.BlockMaterial;
import com.arisux.airi.lib.ModUtil;
import com.arisux.airi.lib.world.Schematic;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = Properties.MODID, name = Properties.MODID)
public class AIRI
{
    public static Block WORLDGEN_GHOST;
    public static boolean COREMOD_INITIALIZED;

    public static Logger logger = new Logger();
    private Properties properties = new Properties();
    private ModContainer container;
    public LocalEventHandler events;
    public GuiElementHandler guiElementHandler;
    public Settings settings;

    private WindowAPI windowAPI;
    private WavefrontAPI wavefrontAPI;
    private RemappingAPI remappingAPI;

    private ArrayList<Schematic> loadedSchematics = new ArrayList<Schematic>();

    @Mod.Instance(Properties.MODID)
    private static AIRI instance;

    public static AIRI instance()
    {
        return instance;
    }

    public ModContainer container()
    {
        return this.container == null ? this.container = ModUtil.getModContainerForId(Properties.MODID) : this.container;
    }

    public static Properties properties()
    {
        return instance().properties;
    }

    public static Settings settings()
    {
        return instance().settings;
    }

    public static class Logger
    {
        public void info(String info, Object... args)
        {
            System.out.println(String.format("[AIRI/INFO] %s", String.format(info, args)));
        }

        public void bug(String info, Object... args)
        {
            System.out.println(String.format("[AIRI/BUG] %s. This should not happen, report it.", String.format(info, args)));
        }

        public void warning(String warning, Object... args)
        {
            System.out.println(String.format("[AIRI/WARNING] %s", String.format(warning, args)));
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger.info("[AIRI] Copyright(C) 2013-2016 Arisux Technology Group");

        (settings = new Settings()).preInitialize(event);
        (remappingAPI = new RemappingAPI()).preInitialize(event);
        (events = new LocalEventHandler()).preInitialize(event);
        (guiElementHandler = new GuiElementHandler()).preInitialize(event);

        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            this.windowAPI = new WindowAPI();
            updaterApi().preInitialize(event);
            this.wavefrontAPI = new WavefrontAPI();
        }
    }

    @Mod.EventHandler
    public void postInitialize(FMLPostInitializationEvent event)
    {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            UpdateHandler.instance.postInitialize(event);
        }

        GameRegistry.registerBlock(WORLDGEN_GHOST = (new BlockMaterial(Material.air)).setCreativeTab(CreativeTabs.tabAllSearch), "airi.wgghost");
    }

    @Mod.EventHandler
    public void onLoadMissingMapping(FMLMissingMappingsEvent event)
    {
        (remappingAPI).onLoadMissingMapping(event);
    }

    public void disableNetworking(String reason)
    {
        AIRI.logger.warning("Networking was disabled. " + reason);
        AIRI.settings().setNetworking(false);
    }

    public static void setASMInitialized(boolean b)
    {
        COREMOD_INITIALIZED = b;
    }

    public static RemappingAPI remappingApi()
    {
        return AIRI.instance().remappingAPI;
    }

    public static UpdaterAPI updaterApi()
    {
        return UpdaterAPI.instance;
    }

    public static WindowAPI windowApi()
    {
        return AIRI.instance().windowAPI;
    }

    public static WavefrontAPI wavefrontAPI()
    {
        return AIRI.instance().wavefrontAPI;
    }

    public ArrayList<Schematic> getLoadedSchematics()
    {
        return loadedSchematics;
    }
}
