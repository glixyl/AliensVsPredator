package com.arisux.avp;

import net.minecraft.item.ItemStack;

import com.arisux.airi.lib.interfaces.IInitializable;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingHandler implements IInitializable
{
	public static final CraftingHandler instance = new CraftingHandler();

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		addRecipes();
		addShapelessRecipes();
		addSmelting();
	}

	public void addRecipes()
	{
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemDoritos, 1), new Object[]{"***", "*0*", "***", Character.valueOf('0'), Items.potato, Character.valueOf('*'), Items.wheat});
//		GameRegistry.addRecipe(new ItemStack(Items.diamond, 1), new Object[]{"000", "000", "000", Character.valueOf('0'), AliensVsPredator.items().itemShardDiamond});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemAK47, 1), new Object[]{"***", "341", "32*", Character.valueOf('1'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('2'), Blocks.lever, Character.valueOf('3'), Blocks.planks, Character.valueOf('4'), AliensVsPredator.items().itemM4});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemM56sg, 1), new Object[]{"LR*", "ITI", "LA*", Character.valueOf('A'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('L'), Blocks.lever, Character.valueOf('T'), AliensVsPredator.items().itemM41A, Character.valueOf('I'), Items.iron_ingot, Character.valueOf('R'), Items.redstone});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemM4, 1), new Object[]{"***", "111", "12*", Character.valueOf('1'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('2'), Blocks.lever});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemSniper, 1), new Object[]{"*3*", "141", "12*", Character.valueOf('1'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('2'), Blocks.lever, Character.valueOf('3'), Blocks.glass, Character.valueOf('4'), Items.diamond});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemM4, 1), new Object[]{"***", "130", "12*", Character.valueOf('1'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('2'), Blocks.lever, Character.valueOf('3'), AliensVsPredator.items().itemM4, Character.valueOf('0'), Items.gold_ingot});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemFlamethrower, 1), new Object[]{"***", "111", "12*", Character.valueOf('1'), Items.iron_ingot, Character.valueOf('2'), Items.blaze_rod});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemPistol, 1), new Object[]{"***", "111", "1**", Character.valueOf('1'), Items.iron_ingot, Character.valueOf('2'), Blocks.lever});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemEnergy, 1), new Object[]{"***", "*0*", "*1*", Character.valueOf('0'), AliensVsPredator.items().itemShardDiamond, Character.valueOf('1'), AliensVsPredator.items().itemIngotTitanium});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().plateTitanium, 1), new Object[]{"0*0", "010", "000", Character.valueOf('0'), AliensVsPredator.items().itemIngotTitanium, Character.valueOf('1'), AliensVsPredator.items().itemArtifactTech});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().legsTitanium, 1), new Object[]{"010", "0*0", "0*0", Character.valueOf('0'), AliensVsPredator.items().itemIngotTitanium, Character.valueOf('1'), AliensVsPredator.items().itemArtifactTech});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().bootsTitanium, 1), new Object[]{"***", "0*0", "0*0", Character.valueOf('0'), AliensVsPredator.items().itemIngotTitanium, Character.valueOf('1'), AliensVsPredator.items().itemArtifactTech});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().helmTitanium, 1), new Object[]{"000", "010", "***", Character.valueOf('0'), AliensVsPredator.items().itemIngotTitanium, Character.valueOf('1'), AliensVsPredator.items().itemArtifactTech});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemArtifactTech, 1), new Object[]{"010", "121", "010", Character.valueOf('0'), AliensVsPredator.items().itemTitaniumPins, Character.valueOf('1'), AliensVsPredator.items().itemSiliconPlate, Character.valueOf('2'), Items.redstone});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().pickaxeTitanium, 1), new Object[]{"000", "*1*", "*0*", Character.valueOf('0'), AliensVsPredator.items().itemIngotTitanium, Character.valueOf('1'), Items.stick});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().axeTitanium, 1), new Object[]{"00*", "01*", "*0*", Character.valueOf('0'), AliensVsPredator.items().itemIngotTitanium, Character.valueOf('1'), Items.stick});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().shovelTitanium, 1), new Object[]{"*0*", "*1*", "*0*", Character.valueOf('0'), AliensVsPredator.items().itemIngotTitanium, Character.valueOf('1'), Items.stick});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().hoeTitanium, 1), new Object[]{"00*", "*1*", "*0*", Character.valueOf('0'), AliensVsPredator.items().itemIngotTitanium, Character.valueOf('1'), Items.stick});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemSpear, 1), new Object[]{"**0", "*1*", "1**", Character.valueOf('0'), AliensVsPredator.items().itemIngotTitanium, Character.valueOf('1'), Items.stick});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().swordTitanium, 1), new Object[]{"*0*", "*0*", "*1*", Character.valueOf('0'), AliensVsPredator.items().itemIngotTitanium, Character.valueOf('1'), Items.stick});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemPlasmaCaster, 1), new Object[]{"000", "*10", "000", Character.valueOf('0'), AliensVsPredator.items().itemIngotTitanium, Character.valueOf('1'), AliensVsPredator.items().itemArtifactTech});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemProximityMine, 1), new Object[]{"*0*", "010", "*0*", Character.valueOf('0'), AliensVsPredator.items().itemIngotTitanium, Character.valueOf('1'), AliensVsPredator.items().itemArtifactTech});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemGrenade, 1), new Object[]{"001", "020", "000", Character.valueOf('0'), Items.iron_ingot, Character.valueOf('1'), Blocks.stone_button, Character.valueOf('2'), Items.gunpowder});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemFireGrenade, 1), new Object[]{"001", "020", "000", Character.valueOf('0'), Items.iron_ingot, Character.valueOf('1'), Blocks.stone_button, Character.valueOf('2'), Items.blaze_powder});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemShuriken, 1), new Object[]{"*0*", "010", "*0*", Character.valueOf('0'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('1'), AliensVsPredator.items().itemArtifactTech});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemDisc, 1), new Object[]{"101", "020", "101", Character.valueOf('0'), AliensVsPredator.items().itemIngotTitanium, Character.valueOf('1'), AliensVsPredator.items().itemArtifactTech, Character.valueOf('2'), AliensVsPredator.items().itemShuriken});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().helmMarine, 1), new Object[]{"000", "0*0", "***", Character.valueOf('0'), Blocks.wool});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().plateMarine, 1), new Object[]{"0*0", "000", "000", Character.valueOf('0'), Blocks.wool});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().legsMarine, 1), new Object[]{"000", "0*0", "0*0", Character.valueOf('0'), Blocks.wool});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().bootsMarine, 1), new Object[]{"***", "0*0", "0*0", Character.valueOf('0'), Blocks.wool});
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemMagazineSniper, 4), new Object[] { "*A*", "ADA", "AGA", Character.valueOf('D'), AliensVsPredator.items().itemShardDiamond, Character.valueOf('A'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('G'), Items.gunpowder });
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemMagazinePistol, 4), new Object[] { "*A*", "AGA", "AAA", Character.valueOf('A'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('G'), Items.gunpowder });
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemMagazinePulseRifle, 4), new Object[] { "*A*", "AGA", "AIA", Character.valueOf('I'), Items.iron_ingot, Character.valueOf('A'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('G'), Items.gunpowder });
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemMagazineM4, 4), new Object[] { "*A*", "AGA", "AGA", Character.valueOf('A'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('G'), Items.gunpowder });
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemMagazineAK47, 4), new Object[] { "*GA", "GPG", "AG*", Character.valueOf('A'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('P'), Items.gunpowder, Character.valueOf('G'), Items.gold_ingot });
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemMagazineM56sg, 4), new Object[] { "AIA", "IGI", "AIA", Character.valueOf('A'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('G'), Items.gunpowder, Character.valueOf('I'), Items.iron_ingot });
//		GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemTitaniumPins, 64), new Object[]{"*1*", "*1*", "*1*", '1', AliensVsPredator.items().itemIngotTitanium});
	}

	public void addShapelessRecipes()
	{
//		GameRegistry.addShapelessRecipe(new ItemStack(AliensVsPredator.items().itemShardDiamond, 9), new Object[]{Items.diamond});
	}

	public void addSmelting()
	{
		GameRegistry.addSmelting(AliensVsPredator.blocks().oreCopper, new ItemStack(AliensVsPredator.items().itemIngotCopper), 1.0F);
		GameRegistry.addSmelting(AliensVsPredator.blocks().oreLithium, new ItemStack(AliensVsPredator.items().itemIngotLithium), 1.0F);
		GameRegistry.addSmelting(AliensVsPredator.blocks().oreBauxite, new ItemStack(AliensVsPredator.items().itemIngotAluminum), 1.0F);
//		GameRegistry.addSmelting(AliensVsPredator.items().itemSilicon, new ItemStack(AliensVsPredator.items().itemSiliconPlate), 1.0F);
	}
}