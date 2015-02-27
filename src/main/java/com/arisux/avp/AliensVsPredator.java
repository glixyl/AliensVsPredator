/** AliensVsPredator - Copyright (C) 2012-2015 Arisux (Ri5ux/Dustin Christensen) **/
package com.arisux.avp;

import com.arisux.airi.AIRI;
import com.arisux.airi.api.updater.Updater;
import com.arisux.airi.lib.ModUtil;
import com.arisux.airi.lib.interfaces.IMod;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid=AliensVsPredator.ID)
public class AliensVsPredator implements IMod
{
	protected static final String ID = "avp";
	
	@Mod.Instance(AliensVsPredator.ID)
	private static AliensVsPredator instance;
	private ModContainer container;
	public LocalEventHandler localEvents;
	public NetworkHandler network;
	public KeybindHandler keybinds;
	public EntityHandler entities;
	public CreativeTab tab;
	public BlockHandler blocks;
	public ItemHandler items;
	public WorldGenHandler worldgen;
	public CraftingHandler crafting;
	public GuiHandler guis;
	public RenderingHandler renderer;
	public DimensionHandler dimensions;
	public CommandHandler commands;
	public PlayerModeHandler playerModeHandler;
	public Settings settings;
	public Updater updater;
	
	public static AliensVsPredator instance()
	{
		return AliensVsPredator.instance;
	}
	
	public static Properties properties()
	{
		return Properties.instance;
	}
	
	@SideOnly(Side.CLIENT)
	public static Resources resources()
	{
		return Resources.instance;
	}
	
	public static Schematics schematics()
	{
		return Schematics.instance;
	}
	
	public static Settings settings()
	{
		return instance().settings;
	}
	
	@Override
	public ModContainer container()
	{
		return this.container == null ? this.container = ModUtil.getModContainerForId(AliensVsPredator.ID) : this.container;
	}
	
	@Override
	public String domain()
	{
		return container().getModId() + ":";
	}
	
	@Override
	public CreativeTab tab()
	{
		return CreativeTab.instance;
	}

	@Override
	@Mod.EventHandler
	public void preInitialize(FMLPreInitializationEvent event)
	{
		AIRI.logger.info("[AliensVsPredator] Copyright(C) 2012-2015 Arisux");
		AIRI.logger.info("[AliensVsPredator] Pre-Initialization");
		
		(settings = new Settings()).preInitialize(event);

		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			(renderer = new RenderingHandler()).preInitialize(event);
		}
	}

	@Override
	@Mod.EventHandler
	public void initialize(FMLInitializationEvent event)
	{
		AIRI.logger.info("[AliensVsPredator] Initialization");

		(items = new ItemHandler()).initialize(event);
		(blocks = new BlockHandler()).initialize(event);
		(network = new NetworkHandler()).initialize(event);
		(dimensions = new DimensionHandler()).initialize(event);
		(entities = new EntityHandler()).initialize(event);
		(worldgen = new WorldGenHandler()).initialize(event);
		(crafting = new CraftingHandler()).initialize(event);
		(guis = new GuiHandler()).initialize(event);
		(localEvents = new LocalEventHandler()).initialize(event);
		(commands = new CommandHandler()).initialize(event);
		(playerModeHandler = new PlayerModeHandler()).initialize(event);
		schematics().initialize();
	}

	@Override
	@Mod.EventHandler
	public void postInitialize(FMLPostInitializationEvent event)
	{
		AIRI.logger.info("[AliensVsPredator] Post-Initialization");

		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			(renderer).postInitialize(event);
			(keybinds = new KeybindHandler()).postInitialize(event);

			if (settings.isUpdaterEnabled())
			{
				(updater = AIRI.updaterApi().createNewUpdater(AliensVsPredator.ID, "4.0", settings().getUrlUpdater(), settings.getServer(), settings().getUrlChangelog())).postInitialize(event);
			}
		}
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		this.commands.onServerStarting(event);
	}

	public boolean isDevCopy()
	{
		return true;
	}
}
