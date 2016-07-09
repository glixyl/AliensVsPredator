package com.arisux.airi.lib.interfaces;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public interface IInitializable
{
    @Mod.EventHandler
    public void initialize(FMLInitializationEvent event);
}
