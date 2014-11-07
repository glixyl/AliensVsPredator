package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.command.CommandWorldSelectorExport;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommandHandler implements IInitializable
{
	public CommandWorldSelectorExport commandExport;
	
	@Override
	public void initialize()
	{
		FMLCommonHandler.instance().bus().register(this);
	}

	public void onServerStart(FMLServerStartingEvent event)
	{
		event.registerServerCommand(this.commandExport = new CommandWorldSelectorExport());
	}
}
