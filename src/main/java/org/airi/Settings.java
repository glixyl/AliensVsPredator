package com.arisux.airi;

import com.arisux.airi.lib.interfaces.IInitializablePre;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Settings implements IInitializablePre
{
    private boolean networking;

    @Override
    @Mod.EventHandler
    public void preInitialize(FMLPreInitializationEvent event)
    {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        try
        {
            config.load();

            networking = config.get(Configuration.CATEGORY_GENERAL, "NETWORKING", true, "Toggles networking for mods that route external network access through AIRI.").getBoolean();
        }
        finally
        {
            config.save();
        }
    }

    public String getServer()
    {
        return "https://aliensvspredator.org";
    }

    public boolean isNetworkingEnabled()
    {
        return networking;
    }

    public void setNetworking(boolean networking)
    {
        this.networking = networking;
    }
}
