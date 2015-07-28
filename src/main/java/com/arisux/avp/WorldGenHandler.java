package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.dimension.varda.WorldGeneratorVarda;
import com.arisux.avp.world.WorldGenerator;
import com.arisux.avp.world.WorldGeneratorDerelict;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class WorldGenHandler implements IInitializable
{
	public static final WorldGenHandler instance = new WorldGenHandler();
	private IWorldGenerator worldGeneratorVarda;
	private IWorldGenerator worldGeneratorDerelict;
	
	@Override
	public void initialize(FMLInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 1);
		GameRegistry.registerWorldGenerator(this.worldGeneratorVarda = new WorldGeneratorVarda(), 1);
		GameRegistry.registerWorldGenerator(this.worldGeneratorDerelict = new WorldGeneratorDerelict(), 1);
	}
	
	public WorldGeneratorVarda getWorldGeneratorVarda()
	{
		return (WorldGeneratorVarda) worldGeneratorVarda;
	}
	
	public WorldGeneratorDerelict getWorldGeneratorDerelict()
	{
		return (WorldGeneratorDerelict) worldGeneratorDerelict;
	}
}
