package com.arisux.airi.lib.interfaces;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

public interface IInitializablePost
{
    @Mod.EventHandler
    public void postInitialize(FMLPostInitializationEvent event);
}
