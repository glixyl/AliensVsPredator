package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.command.CommandGenerate;
import com.arisux.avp.command.CommandPlayerMode;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommandHandler implements IInitializable
{
	public static final CommandHandler instance = new CommandHandler();
	public CommandPlayerMode commandPlayerMode;
	public CommandGenerate commandGenerate;
	
	@Override
	public void initialize(FMLInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(this);
	}

	@Mod.EventHandler
	public void onServerStarting(FMLServerStartingEvent event)
	{
		event.registerServerCommand(this.commandPlayerMode = new CommandPlayerMode());
		event.registerServerCommand(this.commandGenerate = new CommandGenerate());
	}
}
