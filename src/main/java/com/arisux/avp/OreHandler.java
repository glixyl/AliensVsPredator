package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

public class OreHandler implements IInitializable
{
    public static OreHandler instance = new OreHandler();

    public void initialize(FMLInitializationEvent event)
    {
        OreDictionary.registerOre("oreCopper", AliensVsPredator.blocks().oreCopper);
        OreDictionary.registerOre("ingotCopper", AliensVsPredator.items().itemIngotCopper);
        OreDictionary.registerOre("oreLithium", AliensVsPredator.blocks().oreLithium);
        OreDictionary.registerOre("lithium", AliensVsPredator.items().itemIngotLithium);
        OreDictionary.registerOre("oreAluminum", AliensVsPredator.blocks().oreBauxite);
        OreDictionary.registerOre("ingotAluminum", AliensVsPredator.items().itemIngotAluminum);
        OreDictionary.registerOre("oreAluminium", AliensVsPredator.blocks().oreBauxite);
        OreDictionary.registerOre("ingotAluminium", AliensVsPredator.items().itemIngotAluminum);
        OreDictionary.registerOre("oreSilicon", AliensVsPredator.blocks().oreSilicon);
        OreDictionary.registerOre("silicon", AliensVsPredator.items().itemSilicon);
        OreDictionary.registerOre("polycarbonate", AliensVsPredator.items().itemPolycarbonate);
        OreDictionary.registerOre("plastic", AliensVsPredator.items().itemPolycarbonate);
        OreDictionary.registerOre("logWood", AliensVsPredator.blocks().terrainUniTreeLog);
    }
}
