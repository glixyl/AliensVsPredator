package com.arisux.airi.lib.interfaces;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IInitializablePre
{
    @Mod.EventHandler
    public void preInitialize(FMLPreInitializationEvent event);
}
