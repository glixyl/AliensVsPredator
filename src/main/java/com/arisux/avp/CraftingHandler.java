package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

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
		// GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockAssembler, 1), new Object[]{"YXY", "YXY", "YXY", Character.valueOf('X'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('Y'), AliensVsPredator.items().itemIngotCopper});
		
		// Tier 1 components required to craft assembler, armor and guns.  Assembler doubles output and allows Tier 2+
		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemCarbon, 2),
			"aa",
			"aa",
			'a', Items.coal
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemCarbon, 2),
			"aa",
			"aa",
			'a', new ItemStack(Items.coal, 1, 1)
		); 		
		GameRegistry.addRecipe(  // the top layer of silicon protects the softer polycarbonate
		    new ItemStack(AliensVsPredator.items().itemPolycarbonate, 2),
			"aaa",
			"bbb",
			"bbb",
			'a', AliensVsPredator.items().itemSilicon,
			'b', AliensVsPredator.items().itemCarbon
		);		
		GameRegistry.addRecipe( 
		    new ItemStack(AliensVsPredator.items().itemResistor, 1),
			" a ",
			" b ",
			" a ",
			'a', AliensVsPredator.items().itemIngotCopper,
			'b', AliensVsPredator.items().itemCarbon
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemDiode, 1),
			" a ",
			" b ",
			" c ",
			'a', AliensVsPredator.items().itemIngotCopper,
			'b', AliensVsPredator.items().itemCarbon,
			'c', AliensVsPredator.items().itemSilicon
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemLed, 1),
			" b ",
			" c ",
			" a ",
			'a', AliensVsPredator.items().itemDiode,
			'b', AliensVsPredator.items().itemPolycarbonate,
			'c', Items.redstone
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemCapacitor, 1),
			" a ",
			" b ",
			" a ",
			'a', AliensVsPredator.items().itemIngotCopper,
			'b', AliensVsPredator.items().itemIngotLithium
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemVoltageRegulator, 1),
			" a ",
			" b ",
			" c ",
			'a', AliensVsPredator.items().itemDiode,
			'b', AliensVsPredator.items().itemIngotCopper,
			'c', AliensVsPredator.items().itemResistor
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemTransistor, 1),
			"  a",
			"bc ",
			"  a",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', AliensVsPredator.items().itemSilicon,
			'c', Blocks.lever
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemIntegratedCircuit, 1),
			"dbe",
			"cac",
			"fbg",
			'a', AliensVsPredator.items().itemPolycarbonate,
			'b', AliensVsPredator.items().itemIngotAluminum,
			'c', AliensVsPredator.items().itemIngotCopper,
			'd', AliensVsPredator.items().itemTransistor,
			'e', AliensVsPredator.items().itemResistor,
			'f', AliensVsPredator.items().itemVoltageRegulator,
			'g', AliensVsPredator.items().itemDiode
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemProcessor, 1),
			"aaa",
			"aba",
			"aca",
			'a', AliensVsPredator.items().itemIntegratedCircuit,
			'b', AliensVsPredator.items().itemPolycarbonate,
			'c', AliensVsPredator.items().itemIngotLithium
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemRAM, 1),
			"aaa",
			"cec",
			"dbd",
			'a', AliensVsPredator.items().itemIntegratedCircuit,
			'b', AliensVsPredator.items().itemPolycarbonate,
			'c', AliensVsPredator.items().itemSilicon,
			'd', AliensVsPredator.items().itemIngotCopper,
			'e', AliensVsPredator.items().itemVoltageRegulator
		);
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemMotherboard, 1),
			"aef",
			"gbc",
			"dbh",
			'a', AliensVsPredator.items().itemIntegratedCircuit,
			'b', AliensVsPredator.items().itemPolycarbonate,
			'c', AliensVsPredator.items().itemTransistor,
			'd', AliensVsPredator.items().itemCapacitor,
			'e', AliensVsPredator.items().itemVoltageRegulator,
			'f', AliensVsPredator.items().itemDiode,
			'g', AliensVsPredator.items().itemResistor,
			'h', AliensVsPredator.items().itemLed
		);		
		GameRegistry.addRecipe(  // polycarbonate can be manufactured to be bendable at room temperature
		    new ItemStack(AliensVsPredator.blocks().blockPowerline, 2),
			" a ",
			"aba",
			" a ",
			'a', AliensVsPredator.items().itemPolycarbonate,
			'b', AliensVsPredator.items().itemIngotCopper
		);		
		GameRegistry.addRecipe(  // copper windings around a iron core
		    new ItemStack(AliensVsPredator.blocks().blockTransformer, 1),
			"aaa",
			"bca",
			"aaa",
			'a', AliensVsPredator.items().itemIngotCopper,
			'b', AliensVsPredator.items().itemVoltageRegulator,
			'c', Blocks.iron_block
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.blocks().blockNegativeTransformer, 1),
			"aaa",
			"acb",
			"aaa",
			'a', AliensVsPredator.items().itemIngotCopper,
			'b', AliensVsPredator.items().itemVoltageRegulator,
			'c', Blocks.iron_block
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemPowerSupply, 1),
			"abc",
			"dec",
			"abc",
			'a', AliensVsPredator.items().itemDiode,
			'b', AliensVsPredator.items().itemVoltageRegulator,
			'c', AliensVsPredator.items().itemIngotAluminum,
			'd', AliensVsPredator.items().itemCapacitor,
			'e', AliensVsPredator.blocks().blockTransformer
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemSolidStateDrive, 1),
			"aaa",
			"dcb",
			"fgh",
			'a', AliensVsPredator.items().itemRAM,
			'b', AliensVsPredator.items().itemVoltageRegulator,
			'c', AliensVsPredator.items().itemIntegratedCircuit,
			'd', AliensVsPredator.items().itemIngotLithium,
			'e', AliensVsPredator.items().itemVoltageRegulator,
			'f', AliensVsPredator.items().itemTransistor,
			'g', AliensVsPredator.items().itemPolycarbonate,
			'h', AliensVsPredator.items().itemCapacitor
		);
		// SpeciesEngineer now drop NBT flash drive 5% of the time on death and cant be crafted
		// creation theme and play on NBT idea
		/* GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemFlashDrive, 1),
			"aca",
			"aba",
			"aca",
			'a', AliensVsPredator.items().itemRAM,
			'b', AliensVsPredator.items().itemPolycarbonate,
			'c', AliensVsPredator.items().itemIngotLithium
		); */			
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.blocks().blockAssembler, 1),
			"aba",
			"dfh",
			"ceg",
			'a', AliensVsPredator.items().itemRAM,
			'b', AliensVsPredator.items().itemProcessor,
			'c', AliensVsPredator.items().itemPowerSupply,
			'd', AliensVsPredator.items().itemPolycarbonate,
			'e', AliensVsPredator.items().itemSolidStateDrive,
			'f', AliensVsPredator.items().itemLedDisplay,
			'g', AliensVsPredator.items().itemMotherboard,
			'h', AliensVsPredator.items().itemFlashDrive
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemLedDisplay, 1),
			"bdb",
			"bcb",
			"bab",
			'a', AliensVsPredator.items().itemIntegratedCircuit,
			'b', AliensVsPredator.items().itemLed,
			'c', AliensVsPredator.items().itemPolycarbonate,
			'd', AliensVsPredator.items().itemIngotLithium
		);		
		GameRegistry.addRecipe(  // only source of AvP energy in Tier 1
		    new ItemStack(AliensVsPredator.blocks().blockSolarPanel, 1),
			"aaa",
			"bbb",
			"dcd",
			'a', AliensVsPredator.items().itemPolycarbonate,
			'b', AliensVsPredator.items().itemSilicon,
			'c', AliensVsPredator.items().itemIngotCopper,
			'd', AliensVsPredator.items().itemIngotLithium
		);
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().plateMarine, 1),
			"b b",
			"aba",
			"bab",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', Blocks.wool
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().legsMarine, 1),
			"bab",
			"b b",
			"a a",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', Blocks.wool
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().bootsMarine, 1),
			"b b",
			"a a",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', Blocks.wool
		);
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().pressureMask, 1),
			"aaa",
			"b b",
			"dcd",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', AliensVsPredator.blocks().blockIndustrialGlass,
			'c', AliensVsPredator.items().itemCarbon,
			'd', AliensVsPredator.items().itemSilicon
		);			
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().pressureChest, 1),
			"b b",
			"aba",
			"bab",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', AliensVsPredator.blocks().blockIndustrialGlass
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().pressurePants, 1),
			"bab",
			"b b",
			"a a",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', AliensVsPredator.blocks().blockIndustrialGlass
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().pressureBoots, 1),
			"b b",
			"a a",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', AliensVsPredator.blocks().blockIndustrialGlass
		);		
		// guns and ammo		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemPistol, 1),
			"bc",
			"a ",
			'a', AliensVsPredator.items().itemPistolStock,
			'b', AliensVsPredator.items().itemPistolAction,
			'c', AliensVsPredator.items().itemPistolBarrel
		);
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemPistolStock, 1),
			"ccc",
			"ab ",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', AliensVsPredator.items().itemSilicon,
			'c', Blocks.planks
		);
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemPistolAction, 1),
			"ca",
			"ab",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', Blocks.lever,
			'c', AliensVsPredator.items().itemSilicon
		);					
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemPistolBarrel, 1),
			"aaa",
			"b  ",
			'a', Items.iron_ingot,
			'b', AliensVsPredator.items().itemSilicon
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemAmmoPistol, 2),
			" a ",
			"bcb",
			"bcb",
			'a', AliensVsPredator.items().itemIngotCopper,
			'b', AliensVsPredator.items().itemIngotAluminum,
			'c', Items.gunpowder
		);
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemM4, 1),
			"bc",
			"a ",
			'a', AliensVsPredator.items().itemM4Stock,
			'b', AliensVsPredator.items().itemM4Action,
			'c', AliensVsPredator.items().itemM4Barrel
		);
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemM4Stock, 1),
			"ccc",
			"ab ",
			'c', AliensVsPredator.items().itemIngotAluminum,
			'a', AliensVsPredator.items().itemSilicon,
			'b', AliensVsPredator.items().itemCarbon
		);
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemM4Action, 1),
			"caa",
			"ab ",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', Blocks.lever,
			'c', AliensVsPredator.items().itemSilicon
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemM4Barrel, 1),
			"aaa",
			"bc ",
			'a', Items.iron_ingot,
			'b', AliensVsPredator.items().itemSilicon,
			'c', AliensVsPredator.items().itemCarbon
		);	
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemAK47, 1),
			"bc",
			"a ",
			'a', AliensVsPredator.items().itemAK47Stock,
			'b', AliensVsPredator.items().itemAK47Action,
			'c', AliensVsPredator.items().itemAK47Barrel
		);
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemAK47Stock, 1),
			"ccc",
			"ab ",
			'c', AliensVsPredator.items().itemIngotAluminum,
			'b', AliensVsPredator.items().itemSilicon,
			'a', AliensVsPredator.items().itemCarbon
		);
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemAK47Action, 1),
			"caa",
			"db ",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', Blocks.lever,
			'c', AliensVsPredator.items().itemSilicon,
			'd', Items.iron_ingot
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemAK47Barrel, 1),
			"aaa",
			"cb ",
			'a', Items.iron_ingot,
			'b', AliensVsPredator.items().itemSilicon,
			'c', AliensVsPredator.items().itemCarbon
		);	
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemAmmoAR, 1),
			" a ",
			"bcb",
			"bdb",
			'a', Items.iron_ingot,
			'b', AliensVsPredator.items().itemIngotAluminum,
			'c', Items.gunpowder,
			'd', AliensVsPredator.items().itemIngotCopper
		);
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemGrenade, 1),
			" d ",
			"aca",
			" b ",
			'a', Items.iron_ingot,
			'b', AliensVsPredator.items().itemIngotAluminum,
			'c', Items.gunpowder,
			'd', AliensVsPredator.items().itemIngotCopper
		);
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemIncendiaryGrenade, 1),
			" d ",
			"aca",
			" b ",
			'a', Items.iron_ingot,
			'b', AliensVsPredator.items().itemIngotAluminum,
			'c', Items.blaze_powder,
			'd', AliensVsPredator.items().itemIngotCopper
		);
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemNostromoFlamethrower, 1),
			"e f",
			"ada",
			"bbc",
			'a', AliensVsPredator.items().itemPolycarbonate,
			'b', AliensVsPredator.items().itemIngotAluminum,
			'c', Blocks.lever,
			'd', AliensVsPredator.items().itemSilicon,
			'e', Items.iron_ingot,
			'f', Items.flint_and_steel
		);	
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemFuelTank, 1),
			"dad",
			"bcb",
			"bbb",
			'a', Items.slime_ball,
			'b', AliensVsPredator.items().itemIngotAluminum,
			'c', Items.blaze_powder,
			'd', AliensVsPredator.items().itemPolycarbonate
		);	
        // construction blocks and materials		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.blocks().blockWall, 16),
			"bbb",
			"aaa",
			"bbb",
			'a', AliensVsPredator.items().itemPolycarbonate,
			'b', Blocks.cobblestone
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.blocks().blockWallW, 16),
			"bbb",
			"aaa",
			"bbb",
			'a', AliensVsPredator.items().itemPolycarbonate,
			'b', Blocks.stone
		);
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.blocks().blockWallStairs, 12),
			"b  ",
			"aa ",
			"bbb",
			'a', AliensVsPredator.items().itemPolycarbonate,
			'b', Blocks.cobblestone
		);	
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.blocks().blockIndustrialGlass, 16),
			"bbb",
			"aaa",
			"bbb",
			'a', AliensVsPredator.items().itemPolycarbonate,
			'b', Blocks.glass
		);	
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.blocks().blockIndustrialGlassStairs, 12),
			"b  ",
			"aa ",
			"bbb",
			'a', AliensVsPredator.items().itemPolycarbonate,
			'b', Blocks.glass
		);	
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.blocks().blockIronBricks, 16),
			"bbb",
			"aaa",
			"bbb",
			'a', Items.iron_ingot,
			'b', Items.brick
		);	
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.blocks().blockIronBricksStairs, 12),
			"b  ",
			"aa ",
			"bbb",
			'a', Items.iron_ingot,
			'b', Items.brick
		);	
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.blocks().blockBlastdoor, 1),
			"aba",
			"cdc",
			"aba",
			'a', AliensVsPredator.items().itemPolycarbonate,
			'b', Blocks.obsidian,
			'c', Items.iron_door,
			'd', Blocks.sticky_piston
		);		
		GameRegistry.addRecipe( 
		    new ItemStack(AliensVsPredator.items().itemMaintenanceJack, 1),
			"a  ",
			" a ",
			"aab",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', AliensVsPredator.items().itemPolycarbonate
		);
		// storage		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.blocks().blockLocker, 1),
			"aaa",
			"aba",
			"aaa",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', Items.wooden_door
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.blocks().blockGunLocker, 1),
			"aaa",
			"aba",
			"aaa",
			'a', AliensVsPredator.items().itemIngotAluminum,
			'b', Items.iron_door
		);
        // food
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemDoritos, 3),
			"aaa",
			"a b",
			"bbb",
			'a', Items.wheat,
			'b', Items.baked_potato
		);			
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.items().itemDoritosCoolRanch, 1),
			"ab",
			"b ",
			'a', AliensVsPredator.items().itemDoritos,
			'b', Items.wheat
		);		
		// decorative		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.blocks().mainframePanelShimmer, 1),
			"aba",
			"bcb",
			"aba",
			'a', Items.glowstone_dust,
			'b', Items.redstone,
			'c', AliensVsPredator.items().itemSilicon
		);		
		GameRegistry.addRecipe(
		    new ItemStack(AliensVsPredator.blocks().mainframePanelFlicker, 1),
			"bab",
			"aca",
			"bab",
			'a', Items.glowstone_dust,
			'b', Items.redstone,
			'c', AliensVsPredator.items().itemSilicon
		);
	}
	
	public void addSmelting()
	{
		GameRegistry.addSmelting(AliensVsPredator.blocks().oreCopper, new ItemStack(AliensVsPredator.items().itemIngotCopper), 1.0F);
		GameRegistry.addSmelting(AliensVsPredator.blocks().oreLithium, new ItemStack(AliensVsPredator.items().itemIngotLithium), 1.0F);
		GameRegistry.addSmelting(AliensVsPredator.blocks().oreBauxite, new ItemStack(AliensVsPredator.items().itemIngotAluminum), 1.0F);
		GameRegistry.addSmelting(AliensVsPredator.blocks().oreSilicon, new ItemStack(AliensVsPredator.items().itemSilicon), 1.0F);
	}
}