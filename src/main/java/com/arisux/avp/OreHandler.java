package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

public class OreHandler implements IInitializable
{
	@Override
	public void initialize(FMLInitializationEvent event)
	{
		OreDictionary.registerOre("copper", AliensVsPredator.items().itemIngotCopper);
		OreDictionary.registerOre("lithium", AliensVsPredator.items().itemIngotLithium);
		OreDictionary.registerOre("aluminum", AliensVsPredator.items().itemIngotAluminum);
		OreDictionary.registerOre("aluminium", AliensVsPredator.items().itemIngotAluminum);
		OreDictionary.registerOre("silicon", AliensVsPredator.items().itemSilicon);
		OreDictionary.registerOre("polycarbonate", AliensVsPredator.items().itemPolycarbonate);
		OreDictionary.registerOre("plastic", AliensVsPredator.items().itemPolycarbonate);
	}
}
