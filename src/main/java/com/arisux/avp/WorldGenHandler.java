package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.world.WorldGenerator;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class WorldGenHandler implements IInitializable
{
	@Override
	public void initialize(FMLInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 1);
	}
}
