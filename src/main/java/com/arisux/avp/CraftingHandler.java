package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class CraftingHandler implements IInitializable
{
	public static final CraftingHandler instance = new CraftingHandler();

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		this.addRecipes();
		this.addSmelting();
	}

	public void addRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockAssembler, 1), new Object[]{"YXY", "YXY", "YXY", Character.valueOf('X'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('Y'), AliensVsPredator.items().itemIngotCopper});
	}
	
	public void addSmelting()
	{
		GameRegistry.addSmelting(AliensVsPredator.blocks().oreCopper, new ItemStack(AliensVsPredator.items().itemIngotCopper), 1.0F);
		GameRegistry.addSmelting(AliensVsPredator.blocks().oreLithium, new ItemStack(AliensVsPredator.items().itemIngotLithium), 1.0F);
		GameRegistry.addSmelting(AliensVsPredator.blocks().oreBauxite, new ItemStack(AliensVsPredator.items().itemIngotAluminum), 1.0F);
	}
}