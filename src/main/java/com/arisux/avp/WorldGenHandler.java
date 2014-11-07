package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.world.WorldGenerator;

import cpw.mods.fml.common.registry.GameRegistry;

public class WorldGenHandler implements IInitializable
{
	public void initialize()
	{
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 1);
	}
}
