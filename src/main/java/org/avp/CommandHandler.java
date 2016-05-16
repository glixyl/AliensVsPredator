package org.avp;

import org.avp.command.CommandGenerate;
import org.avp.command.CommandPlayerMode;

import com.arisux.airi.lib.interfaces.IInitializable;

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
