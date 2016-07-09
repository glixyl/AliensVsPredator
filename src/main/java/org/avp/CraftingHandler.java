package org.avp;

import org.avp.api.AssemblerAPI;
import org.avp.api.AssemblerAPI.AssemblerSchematic;

import com.arisux.airi.lib.WorldUtil.Entities.Players.Inventories;
import com.arisux.airi.lib.interfaces.IInitializable;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingHandler implements IInitializable
{
    public static final CraftingHandler instance = new CraftingHandler();
    private ItemStack jungleWood = new ItemStack(Blocks.planks, 4);
    private ItemStack charcoal = new ItemStack(Items.coal, 1);

    public CraftingHandler()
    {
        jungleWood.setMetadata(3);
        charcoal.setMetadata(1);
    }

    @Override
    public void initialize(FMLInitializationEvent event)
    {
        this.addRecipes();
        this.addSmelting();
        this.addSchematics();
    }

    public void addRecipes()
    {
        // GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockAssembler, 1), new Object[]{"YXY", "YXY", "YXY", Character.valueOf('X'), AliensVsPredator.items().itemIngotAluminum, Character.valueOf('Y'), AliensVsPredator.items().itemIngotCopper});

        GameRegistry.addShapelessRecipe(jungleWood, AliensVsPredator.blocks().terrainUniTreeLog);

        // Tier 1 components required to craft assembler, armor and guns. Assembler doubles output and allows Tier 2+
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemCarbon, 2), "aa", "aa", 'a', Items.coal);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemCarbon, 2), "aa", "aa", 'a', new ItemStack(Items.coal, 1, 1));
        GameRegistry.addRecipe( // the top layer of silicon protects the softer polycarbonate
            new ItemStack(AliensVsPredator.items().itemPolycarbonate, 2), "aaa", "bbb", "bbb", 'a', AliensVsPredator.items().itemSilicon, 'b', AliensVsPredator.items().itemCarbon);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemResistor, 1), " a ", " b ", " a ", 'a', AliensVsPredator.items().itemIngotCopper, 'b', AliensVsPredator.items().itemCarbon);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemDiode, 1), " a ", " b ", " c ", 'a', AliensVsPredator.items().itemIngotCopper, 'b', AliensVsPredator.items().itemCarbon, 'c', AliensVsPredator.items().itemSilicon);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemLed, 1), " b ", " c ", " a ", 'a', AliensVsPredator.items().itemDiode, 'b', AliensVsPredator.items().itemPolycarbonate, 'c', Items.redstone);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemCapacitor, 1), " a ", " b ", " a ", 'a', AliensVsPredator.items().itemIngotCopper, 'b', AliensVsPredator.items().itemIngotLithium);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemVoltageRegulator, 1), " a ", " b ", " c ", 'a', AliensVsPredator.items().itemDiode, 'b', AliensVsPredator.items().itemIngotCopper, 'c', AliensVsPredator.items().itemResistor);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemTransistor, 1), "  a", "bc ", "  a", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', AliensVsPredator.items().itemSilicon, 'c', Blocks.lever);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemIntegratedCircuit, 1), "dbe", "cac", "fbg", 'a', AliensVsPredator.items().itemPolycarbonate, 'b', AliensVsPredator.items().itemIngotAluminum, 'c', AliensVsPredator.items().itemIngotCopper, 'd', AliensVsPredator.items().itemTransistor, 'e', AliensVsPredator.items().itemResistor, 'f', AliensVsPredator.items().itemVoltageRegulator, 'g', AliensVsPredator.items().itemDiode);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemProcessor, 1), "aaa", "aba", "aca", 'a', AliensVsPredator.items().itemIntegratedCircuit, 'b', AliensVsPredator.items().itemPolycarbonate, 'c', AliensVsPredator.items().itemIngotLithium);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemRAM, 1), "aaa", "cec", "dbd", 'a', AliensVsPredator.items().itemIntegratedCircuit, 'b', AliensVsPredator.items().itemPolycarbonate, 'c', AliensVsPredator.items().itemSilicon, 'd', AliensVsPredator.items().itemIngotCopper, 'e', AliensVsPredator.items().itemVoltageRegulator);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemMotherboard, 1), "aef", "gbc", "dbh", 'a', AliensVsPredator.items().itemIntegratedCircuit, 'b', AliensVsPredator.items().itemPolycarbonate, 'c', AliensVsPredator.items().itemTransistor, 'd', AliensVsPredator.items().itemCapacitor, 'e', AliensVsPredator.items().itemVoltageRegulator, 'f', AliensVsPredator.items().itemDiode, 'g', AliensVsPredator.items().itemResistor, 'h', AliensVsPredator.items().itemLed);
        GameRegistry.addRecipe( // polycarbonate can be manufactured to be bendable at room temperature
            new ItemStack(AliensVsPredator.blocks().blockPowerline, 2), " a ", "aba", " a ", 'a', AliensVsPredator.items().itemPolycarbonate, 'b', AliensVsPredator.items().itemIngotCopper);
        GameRegistry.addRecipe( // copper windings around a iron core
            new ItemStack(AliensVsPredator.blocks().blockTransformer, 1), "aaa", "bca", "aaa", 'a', AliensVsPredator.items().itemIngotCopper, 'b', AliensVsPredator.items().itemVoltageRegulator, 'c', Blocks.iron_block);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockNegativeTransformer, 1), "aaa", "acb", "aaa", 'a', AliensVsPredator.items().itemIngotCopper, 'b', AliensVsPredator.items().itemVoltageRegulator, 'c', Blocks.iron_block);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemPowerSupply, 1), "abc", "dec", "abc", 'a', AliensVsPredator.items().itemDiode, 'b', AliensVsPredator.items().itemVoltageRegulator, 'c', AliensVsPredator.items().itemIngotAluminum, 'd', AliensVsPredator.items().itemCapacitor, 'e', AliensVsPredator.blocks().blockTransformer);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemSolidStateDrive, 1), "aaa", "dcb", "fgh", 'a', AliensVsPredator.items().itemRAM, 'b', AliensVsPredator.items().itemVoltageRegulator, 'c', AliensVsPredator.items().itemIntegratedCircuit, 'd', AliensVsPredator.items().itemIngotLithium, 'e', AliensVsPredator.items().itemVoltageRegulator, 'f', AliensVsPredator.items().itemTransistor, 'g', AliensVsPredator.items().itemPolycarbonate, 'h', AliensVsPredator.items().itemCapacitor);
        // SpeciesEngineer now drop NBT flash drive 5% of the time on death and cant be crafted
        // creation theme and play on NBT idea
        /*
         * GameRegistry.addRecipe( new ItemStack(AliensVsPredator.items().itemFlashDrive, 1), "aca", "aba", "aca", 'a', AliensVsPredator.items().itemRAM, 'b', AliensVsPredator.items().itemPolycarbonate, 'c', AliensVsPredator.items().itemIngotLithium );
         */
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockAssembler, 1), "aba", "dfh", "ceg", 'a', AliensVsPredator.items().itemRAM, 'b', AliensVsPredator.items().itemProcessor, 'c', AliensVsPredator.items().itemPowerSupply, 'd', AliensVsPredator.items().itemPolycarbonate, 'e', AliensVsPredator.items().itemSolidStateDrive, 'f', AliensVsPredator.items().itemLedDisplay, 'g', AliensVsPredator.items().itemMotherboard, 'h', AliensVsPredator.items().itemFlashDrive);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemLedDisplay, 1), "bdb", "bcb", "bab", 'a', AliensVsPredator.items().itemIntegratedCircuit, 'b', AliensVsPredator.items().itemLed, 'c', AliensVsPredator.items().itemPolycarbonate, 'd', AliensVsPredator.items().itemIngotLithium);
        GameRegistry.addRecipe( // only source of AvP energy in Tier 1
            new ItemStack(AliensVsPredator.blocks().blockSolarPanel, 1), "aaa", "bbb", "dcd", 'a', AliensVsPredator.items().itemPolycarbonate, 'b', AliensVsPredator.items().itemSilicon, 'c', AliensVsPredator.items().itemIngotCopper, 'd', AliensVsPredator.items().itemIngotLithium);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().plateMarine, 1), "b b", "aba", "bab", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().legsMarine, 1), "bab", "b b", "a a", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().bootsMarine, 1), "b b", "a a", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().pressureMask, 1), "aaa", "b b", "dcd", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', AliensVsPredator.blocks().blockIndustrialGlass, 'c', AliensVsPredator.items().itemCarbon, 'd', AliensVsPredator.items().itemSilicon);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().pressureChest, 1), "b b", "aba", "bab", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', AliensVsPredator.blocks().blockIndustrialGlass);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().pressurePants, 1), "bab", "b b", "a a", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', AliensVsPredator.blocks().blockIndustrialGlass);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().pressureBoots, 1), "b b", "a a", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', AliensVsPredator.blocks().blockIndustrialGlass);
        // guns and ammo
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemPistol, 1), "bc", "a ", 'a', AliensVsPredator.items().itemPistolStock, 'b', AliensVsPredator.items().itemPistolAction, 'c', AliensVsPredator.items().itemPistolBarrel);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemPistolStock, 1), "ccc", "ab ", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', AliensVsPredator.items().itemSilicon, 'c', Blocks.planks);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemPistolAction, 1), "ca", "ab", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', Blocks.lever, 'c', AliensVsPredator.items().itemSilicon);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemPistolBarrel, 1), "aaa", "b  ", 'a', Items.iron_ingot, 'b', AliensVsPredator.items().itemSilicon);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemAmmoPistol, 2), " a ", "bcb", "bcb", 'a', AliensVsPredator.items().itemIngotCopper, 'b', AliensVsPredator.items().itemIngotAluminum, 'c', Items.gunpowder);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemM4, 1), "bc", "a ", 'a', AliensVsPredator.items().itemM4Stock, 'b', AliensVsPredator.items().itemM4Action, 'c', AliensVsPredator.items().itemM4Barrel);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemM4Stock, 1), "ccc", "ab ", 'c', AliensVsPredator.items().itemIngotAluminum, 'a', AliensVsPredator.items().itemSilicon, 'b', AliensVsPredator.items().itemCarbon);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemM4Action, 1), "caa", "ab ", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', Blocks.lever, 'c', AliensVsPredator.items().itemSilicon);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemM4Barrel, 1), "aaa", "bc ", 'a', Items.iron_ingot, 'b', AliensVsPredator.items().itemSilicon, 'c', AliensVsPredator.items().itemCarbon);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemAK47, 1), "bc", "a ", 'a', AliensVsPredator.items().itemAK47Stock, 'b', AliensVsPredator.items().itemAK47Action, 'c', AliensVsPredator.items().itemAK47Barrel);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemAK47Stock, 1), "ccc", "ab ", 'c', AliensVsPredator.items().itemIngotAluminum, 'b', AliensVsPredator.items().itemSilicon, 'a', AliensVsPredator.items().itemCarbon);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemAK47Action, 1), "caa", "db ", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', Blocks.lever, 'c', AliensVsPredator.items().itemSilicon, 'd', Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemAK47Barrel, 1), "aaa", "cb ", 'a', Items.iron_ingot, 'b', AliensVsPredator.items().itemSilicon, 'c', AliensVsPredator.items().itemCarbon);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemAmmoAR, 1), " a ", "bcb", "bdb", 'a', Items.iron_ingot, 'b', AliensVsPredator.items().itemIngotAluminum, 'c', Items.gunpowder, 'd', AliensVsPredator.items().itemIngotCopper);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemGrenade, 1), " d ", "aca", " b ", 'a', Items.iron_ingot, 'b', AliensVsPredator.items().itemIngotAluminum, 'c', Items.gunpowder, 'd', AliensVsPredator.items().itemIngotCopper);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemIncendiaryGrenade, 1), " d ", "aca", " b ", 'a', Items.iron_ingot, 'b', AliensVsPredator.items().itemIngotAluminum, 'c', Items.blaze_powder, 'd', AliensVsPredator.items().itemIngotCopper);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemNostromoFlamethrower, 1), "e f", "ada", "bbc", 'a', AliensVsPredator.items().itemPolycarbonate, 'b', AliensVsPredator.items().itemIngotAluminum, 'c', Blocks.lever, 'd', AliensVsPredator.items().itemSilicon, 'e', Items.iron_ingot, 'f', Items.flint_and_steel);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemFuelTank, 1), "dad", "bcb", "bbb", 'a', Items.slime_ball, 'b', AliensVsPredator.items().itemIngotAluminum, 'c', Items.blaze_powder, 'd', AliensVsPredator.items().itemPolycarbonate);
        // construction blocks and materials
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockWall, 16), "bbb", "aaa", "bbb", 'a', AliensVsPredator.items().itemPolycarbonate, 'b', Blocks.cobblestone);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockWallW, 16), "bbb", "aaa", "bbb", 'a', AliensVsPredator.items().itemPolycarbonate, 'b', Blocks.stone);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockWallStairs, 12), "b  ", "aa ", "bbb", 'a', AliensVsPredator.items().itemPolycarbonate, 'b', Blocks.cobblestone);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockIndustrialGlass, 16), "bbb", "aaa", "bbb", 'a', AliensVsPredator.items().itemPolycarbonate, 'b', Blocks.glass);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockIndustrialGlassStairs, 12), "b  ", "aa ", "bbb", 'a', AliensVsPredator.items().itemPolycarbonate, 'b', Blocks.glass);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockIronBricks, 16), "bbb", "aaa", "bbb", 'a', Items.iron_ingot, 'b', Items.brick);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockIronBricksStairs, 12), "b  ", "aa ", "bbb", 'a', Items.iron_ingot, 'b', Items.brick);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockBlastdoor, 1), "aba", "cdc", "aba", 'a', AliensVsPredator.items().itemPolycarbonate, 'b', Blocks.obsidian, 'c', Items.iron_door, 'd', Blocks.sticky_piston);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemMaintenanceJack, 1), "a  ", " a ", "aab", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', AliensVsPredator.items().itemPolycarbonate);
        // storage
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockLocker, 1), "aaa", "aba", "aaa", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', Items.wooden_door);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().blockGunLocker, 1), "aaa", "aba", "aaa", 'a', AliensVsPredator.items().itemIngotAluminum, 'b', Items.iron_door);
        // food
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemDoritos, 3), "aaa", "a b", "bbb", 'a', Items.wheat, 'b', Items.baked_potato);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.items().itemDoritosCoolRanch, 1), "ab", "b ", 'a', AliensVsPredator.items().itemDoritos, 'b', Items.wheat);
        // decorative
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().mainframePanelShimmer, 1), "aba", "bcb", "aba", 'a', Items.glowstone_dust, 'b', Items.redstone, 'c', AliensVsPredator.items().itemSilicon);
        GameRegistry.addRecipe(new ItemStack(AliensVsPredator.blocks().mainframePanelFlicker, 1), "bab", "aca", "bab", 'a', Items.glowstone_dust, 'b', Items.redstone, 'c', AliensVsPredator.items().itemSilicon);
    }

    public void addSmelting()
    {
        GameRegistry.addSmelting(AliensVsPredator.blocks().oreCopper, new ItemStack(AliensVsPredator.items().itemIngotCopper), 1.0F);
        GameRegistry.addSmelting(AliensVsPredator.blocks().oreLithium, new ItemStack(AliensVsPredator.items().itemIngotLithium), 1.0F);
        GameRegistry.addSmelting(AliensVsPredator.blocks().oreBauxite, new ItemStack(AliensVsPredator.items().itemIngotAluminum), 1.0F);
        GameRegistry.addSmelting(AliensVsPredator.blocks().oreSilicon, new ItemStack(AliensVsPredator.items().itemSilicon), 1.0F);
        GameRegistry.addSmelting(AliensVsPredator.blocks().terrainUniTreeLog, charcoal, 1.0F);
    }
    
    public void addSchematics()
    {
        ItemHandler avp = AliensVsPredator.items();
        BlockHandler avpBlock = AliensVsPredator.blocks();

        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("turret", Inventories.newStack(avpBlock.blockTurret, 1), Inventories.newStack(avp.itemM41A, 1), Inventories.newStack(avp.itemPolycarbonate, 4), Inventories.newStack(avp.itemIngotAluminum, 4), Inventories.newStack(avp.itemIngotCopper, 4), Inventories.newStack(avp.itemLedDisplay, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("repulsionGenerator", Inventories.newStack(avpBlock.blockRepulsionGenerator, 1), Inventories.newStack(avpBlock.blockTransformer, 4), Inventories.newStack(avpBlock.blockNegativeTransformer, 4), Inventories.newStack(avp.itemPolycarbonate, 4), Inventories.newStack(avp.itemIngotAluminum, 4), Inventories.newStack(Items.diamond, 4)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("cryostasisTube", Inventories.newStack(avpBlock.blockCryostasisTube, 1), Inventories.newStack(avp.itemPolycarbonate, 4), Inventories.newStack(avp.itemIngotAluminum, 4), Inventories.newStack(avpBlock.blockIndustrialGlass, 4), Inventories.newStack(avpBlock.blockLightPanel, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("medpod", Inventories.newStack(avpBlock.blockMedpod, 1), Inventories.newStack(avp.itemPolycarbonate, 4), Inventories.newStack(avp.itemIngotAluminum, 4), Inventories.newStack(avpBlock.blockIndustrialGlass, 4), Inventories.newStack(avp.itemPowerSupply, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("lightPanel", Inventories.newStack(avpBlock.blockLightPanel, 1), Inventories.newStack(avp.itemPolycarbonate, 2), Inventories.newStack(avp.itemIngotAluminum, 2), Inventories.newStack(avpBlock.blockIndustrialGlass, 2), Inventories.newStack(Items.glowstone_dust, 2)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("pulserifle", Inventories.newStack(avp.itemM41A, 1), Inventories.newStack(avp.itemPolycarbonate, 8), Inventories.newStack(Items.iron_ingot, 8), Inventories.newStack(avp.itemIngotAluminum, 6), Inventories.newStack(avp.itemIngotCopper, 6), Inventories.newStack(Items.gold_ingot, 4), Inventories.newStack(avp.itemIntegratedCircuit, 2), Inventories.newStack(avp.itemLedDisplay, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("grenade", Inventories.newStack(avp.itemGrenade, 2), Inventories.newStack(Items.iron_ingot, 2), Inventories.newStack(avp.itemIngotAluminum, 1), Inventories.newStack(Items.gunpowder, 1), Inventories.newStack(avp.itemIngotCopper, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("fire_grenade", Inventories.newStack(avp.itemIncendiaryGrenade, 2), Inventories.newStack(Items.iron_ingot, 2), Inventories.newStack(avp.itemIngotAluminum, 1), Inventories.newStack(Items.blaze_powder, 1), Inventories.newStack(avp.itemIngotCopper, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("marineHelm", Inventories.newStack(avp.helmMarine, 1), Inventories.newStack(Blocks.wool, 3), Inventories.newStack(avp.itemIngotAluminum, 2), Inventories.newStack(avp.itemLedDisplay, 2), Inventories.newStack(avp.itemPolycarbonate, 2), Inventories.newStack(avp.itemProcessor, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("marinePlate", Inventories.newStack(avp.plateMarine, 1), Inventories.newStack(Blocks.wool, 3), Inventories.newStack(avp.itemIngotAluminum, 2)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("marineLeggings", Inventories.newStack(avp.legsMarine, 1), Inventories.newStack(Blocks.wool, 3), Inventories.newStack(avp.itemIngotAluminum, 2)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("marineBoots", Inventories.newStack(avp.bootsMarine, 1), Inventories.newStack(Blocks.wool, 1), Inventories.newStack(avp.itemIngotAluminum, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("sniperMagazine", Inventories.newStack(avp.itemAmmoSniper, 1), Inventories.newStack(avp.itemIngotAluminum, 5), Inventories.newStack(Items.gunpowder, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("pistolMagazine", Inventories.newStack(avp.itemAmmoPistol, 3), Inventories.newStack(avp.itemIngotAluminum, 4), Inventories.newStack(Items.gunpowder, 2), Inventories.newStack(avp.itemIngotCopper, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("ARAmmo", Inventories.newStack(avp.itemAmmoAR, 1), Inventories.newStack(avp.itemIngotAluminum, 5), Inventories.newStack(Items.iron_ingot, 1), Inventories.newStack(Items.gunpowder, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("ACAmmo", Inventories.newStack(avp.itemAmmoAC, 1), Inventories.newStack(avp.itemIngotAluminum, 5), Inventories.newStack(Items.gunpowder, 2)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("SMGAmmo", Inventories.newStack(avp.itemAmmoSMG, 1), Inventories.newStack(avp.itemIngotAluminum, 4), Inventories.newStack(Items.iron_ingot, 4), Inventories.newStack(Items.gunpowder, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("m56sg", Inventories.newStack(avp.itemM56SG, 1), Inventories.newStack(avp.itemM56SGAimingModule, 1), Inventories.newStack(avp.itemM56SGStock, 1), Inventories.newStack(avp.itemM56SGBarrel, 1), Inventories.newStack(avp.itemM56SGSupportFrame, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("sniper", Inventories.newStack(avp.itemSniper, 1), Inventories.newStack(avp.itemSniperScope, 1), Inventories.newStack(avp.itemSniperAction, 1), Inventories.newStack(avp.itemSniperPeripherals, 1), Inventories.newStack(avp.itemSniperBarrel, 1), Inventories.newStack(avp.itemSniperStock, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("pistol", Inventories.newStack(avp.itemPistol, 1), Inventories.newStack(avp.itemPistolStock, 1), Inventories.newStack(avp.itemPistolBarrel, 1), Inventories.newStack(avp.itemPistolAction, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("m4", Inventories.newStack(avp.itemM4, 1), Inventories.newStack(avp.itemM4Stock, 1), Inventories.newStack(avp.itemM4Barrel, 1), Inventories.newStack(avp.itemM4Action, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("ak47", Inventories.newStack(avp.itemAK47, 1), Inventories.newStack(avp.itemAK47Action, 1), Inventories.newStack(avp.itemAK47Barrel, 1), Inventories.newStack(avp.itemAK47Stock, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("doritos", Inventories.newStack(avp.itemDoritos, 4), Inventories.newStack(Items.wheat, 4), Inventories.newStack(Items.baked_potato, 4)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("doritosCoolRanch", Inventories.newStack(avp.itemDoritosCoolRanch, 4), Inventories.newStack(avp.itemDoritos, 4), Inventories.newStack(Items.wheat, 3)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("motionTracker", Inventories.newStack(avp.itemMotionTracker, 1), Inventories.newStack(avp.itemPolycarbonate, 6), Inventories.newStack(avp.itemIngotAluminum, 8), Inventories.newStack(avp.itemIngotCopper, 6), Inventories.newStack(avp.itemLedDisplay, 2), Inventories.newStack(avp.itemProcessor, 2), Inventories.newStack(Items.diamond, 1), Inventories.newStack(Items.iron_ingot, 8)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("flamethrower", Inventories.newStack(avp.itemM240ICU, 1), Inventories.newStack(avp.itemPolycarbonate, 4), Inventories.newStack(avp.itemIngotAluminum, 4), Inventories.newStack(avp.itemIngotCopper, 3), Inventories.newStack(Items.blaze_rod, 1), Inventories.newStack(Items.iron_ingot, 6)));

        // assuming that NBT is a play on NBT tags and creation theme. NBT drive now drops from Engineer species as well as the black goo phials
        /*
         * AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("nbtDrive", Inventories.newStack(avp.itemFlashDrive, 1), Inventories.newStack(avp.itemPolycarbonate, 1), Inventories.newStack(avp.itemRAM, 4), Inventories.newStack(avp.itemIngotLithium, 1) ));
         */

        // the Yautja artifact is now dropped rarely by Yautja and is an ingredient in Celtic technology recipes
        /*
         * AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("artifactTech", Inventories.newStack(avp.itemArtifactTech, 4), Inventories.newStack(Items.redstone, 8), Inventories.newStack(avp.itemIngotAluminum, 4), Inventories.newStack(avp.itemSilicon, 4), Inventories.newStack(avp.itemIngotCopper, 4) ));
         */
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("celticBiomask", Inventories.newStack(avp.helmTitanium, 1), Inventories.newStack(avp.itemArtifactTech, 2), Inventories.newStack(Items.diamond_helmet, 1), Inventories.newStack(avp.itemIngotAluminum, 2), Inventories.newStack(avp.itemLedDisplay, 2), Inventories.newStack(avp.itemPolycarbonate, 2), Inventories.newStack(avp.itemProcessor, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("celticPlate", Inventories.newStack(avp.plateTitanium, 1), Inventories.newStack(avp.itemArtifactTech, 1), Inventories.newStack(Items.diamond_chestplate, 1), Inventories.newStack(avp.itemIngotAluminum, 3), Inventories.newStack(avp.itemPolycarbonate, 3)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("celticLegs", Inventories.newStack(avp.legsTitanium, 1), Inventories.newStack(avp.itemArtifactTech, 1), Inventories.newStack(Items.diamond_leggings, 1), Inventories.newStack(avp.itemIngotAluminum, 2), Inventories.newStack(avp.itemPolycarbonate, 2)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("celticBoots", Inventories.newStack(avp.bootsTitanium, 1), Inventories.newStack(avp.itemArtifactTech, 1), Inventories.newStack(Items.diamond_boots, 1), Inventories.newStack(avp.itemIngotAluminum, 1), Inventories.newStack(avp.itemPolycarbonate, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("celticAxe", Inventories.newStack(avp.axeTitanium, 1), Inventories.newStack(avp.itemArtifactTech, 1), Inventories.newStack(Items.diamond_axe, 1), Inventories.newStack(avp.itemIngotAluminum, 1), Inventories.newStack(avp.itemPolycarbonate, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("celticPickaxe", Inventories.newStack(avp.pickaxeTitanium, 1), Inventories.newStack(avp.itemArtifactTech, 1), Inventories.newStack(Items.diamond_pickaxe, 1), Inventories.newStack(avp.itemIngotAluminum, 1), Inventories.newStack(avp.itemPolycarbonate, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("celticHoe", Inventories.newStack(avp.hoeTitanium, 1), Inventories.newStack(avp.itemArtifactTech, 1), Inventories.newStack(Items.diamond_hoe, 1), Inventories.newStack(avp.itemIngotAluminum, 1), Inventories.newStack(avp.itemPolycarbonate, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("celticShovel", Inventories.newStack(avp.shovelTitanium, 1), Inventories.newStack(avp.itemArtifactTech, 1), Inventories.newStack(Items.diamond_shovel, 1), Inventories.newStack(avp.itemIngotAluminum, 1), Inventories.newStack(avp.itemPolycarbonate, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("celticSword", Inventories.newStack(avp.swordTitanium, 1), Inventories.newStack(avp.itemArtifactTech, 1), Inventories.newStack(Items.diamond_sword, 1), Inventories.newStack(avp.itemIngotAluminum, 1), Inventories.newStack(avp.itemPolycarbonate, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("wristBlade", Inventories.newStack(avp.itemWristBlade, 1), Inventories.newStack(avp.itemArtifactTech, 2), Inventories.newStack(Items.diamond, 4), Inventories.newStack(avp.itemLedDisplay, 2), Inventories.newStack(avp.itemIngotAluminum, 2), Inventories.newStack(avp.itemPolycarbonate, 2)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("wristbracerBlades", Inventories.newStack(avp.itemWristbracerBlades, 1), Inventories.newStack(avp.itemArtifactTech, 2), Inventories.newStack(Items.shears, 1), Inventories.newStack(Items.diamond, 2), Inventories.newStack(avp.itemIngotAluminum, 2), Inventories.newStack(avp.itemPolycarbonate, 2)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("proximityMine", Inventories.newStack(avp.itemProximityMine, 3), Inventories.newStack(avp.itemArtifactTech, 1), Inventories.newStack(Blocks.tnt, 2), Inventories.newStack(avp.itemIngotAluminum, 1), Inventories.newStack(avp.itemPolycarbonate, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("celticDisc", Inventories.newStack(avp.itemDisc, 1), Inventories.newStack(avp.itemArtifactTech, 1), Inventories.newStack(avp.itemIngotAluminum, 1), Inventories.newStack(Items.diamond, 1), Inventories.newStack(avp.itemPolycarbonate, 2)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("celticShuriken", Inventories.newStack(avp.itemShuriken, 2), Inventories.newStack(avp.itemArtifactTech, 1), Inventories.newStack(avp.itemIngotAluminum, 2), Inventories.newStack(avp.itemPolycarbonate, 2)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("celticSpear", Inventories.newStack(avp.itemSpear, 1), Inventories.newStack(avp.itemArtifactTech, 1), Inventories.newStack(avp.itemIngotAluminum, 1), Inventories.newStack(avp.itemPolycarbonate, 2), Inventories.newStack(Items.diamond, 1)));
        // for tier 1 level items, the assembler increases output or reduces input cost

        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("polycarbonate", Inventories.newStack(avp.itemPolycarbonate, 4), Inventories.newStack(avp.itemCarbon, 6), Inventories.newStack(avp.itemSilicon, 3)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("carbon", Inventories.newStack(avp.itemCarbon, 3), Inventories.newStack(Items.coal, 4)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("resistor", Inventories.newStack(avp.itemResistor, 2), Inventories.newStack(avp.itemIngotCopper, 2), Inventories.newStack(avp.itemCarbon, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("capacitor", Inventories.newStack(avp.itemCapacitor, 2), Inventories.newStack(avp.itemIngotCopper, 2), Inventories.newStack(avp.itemIngotLithium, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("diode", Inventories.newStack(avp.itemDiode, 2), Inventories.newStack(avp.itemIngotCopper, 1), Inventories.newStack(avp.itemCarbon, 1), Inventories.newStack(avp.itemSilicon, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("led", Inventories.newStack(avp.itemLed, 2), Inventories.newStack(avp.itemPolycarbonate, 1), Inventories.newStack(avp.itemDiode, 1), Inventories.newStack(Items.redstone, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("regulator", Inventories.newStack(avp.itemVoltageRegulator, 2), Inventories.newStack(avp.itemDiode, 1), Inventories.newStack(avp.itemIngotCopper, 1), Inventories.newStack(avp.itemResistor, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("transistor", Inventories.newStack(avp.itemTransistor, 2), Inventories.newStack(avp.itemIngotAluminum, 2), Inventories.newStack(avp.itemSilicon, 1), Inventories.newStack(Item.getItemFromBlock(Blocks.lever), 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("ic", Inventories.newStack(avp.itemIntegratedCircuit, 2), Inventories.newStack(avp.itemPolycarbonate, 1), Inventories.newStack(avp.itemIngotAluminum, 2), Inventories.newStack(avp.itemIngotCopper, 2), Inventories.newStack(avp.itemSilicon, 1), Inventories.newStack(avp.itemTransistor, 1), Inventories.newStack(avp.itemResistor, 1), Inventories.newStack(avp.itemVoltageRegulator, 1), Inventories.newStack(avp.itemDiode, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("processor", Inventories.newStack(avp.itemProcessor, 1), Inventories.newStack(avp.itemPolycarbonate, 1), Inventories.newStack(avp.itemIntegratedCircuit, 5), Inventories.newStack(avp.itemIngotLithium, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("motherboard", Inventories.newStack(avp.itemMotherboard, 1), Inventories.newStack(avp.itemPolycarbonate, 1), Inventories.newStack(avp.itemIntegratedCircuit, 1), Inventories.newStack(avp.itemSilicon, 1), Inventories.newStack(avp.itemTransistor, 1), Inventories.newStack(avp.itemVoltageRegulator, 1), Inventories.newStack(avp.itemDiode, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("powerline", Inventories.newStack(avpBlock.blockPowerline, 4), Inventories.newStack(avp.itemPolycarbonate, 4), Inventories.newStack(avp.itemIngotCopper, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("powersupply", Inventories.newStack(avp.itemPowerSupply, 1), Inventories.newStack(avp.itemDiode, 1), Inventories.newStack(avp.itemVoltageRegulator, 1), Inventories.newStack(avp.itemIngotAluminum, 2), Inventories.newStack(avpBlock.blockTransformer, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("ledDisplay", Inventories.newStack(avp.itemLedDisplay, 2), Inventories.newStack(avp.itemPolycarbonate, 1), Inventories.newStack(avp.itemLed, 6), Inventories.newStack(avp.itemIntegratedCircuit, 1), Inventories.newStack(avp.itemIngotLithium, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("ram", Inventories.newStack(avp.itemRAM, 2), Inventories.newStack(avp.itemIntegratedCircuit, 3), Inventories.newStack(avp.itemVoltageRegulator, 1), Inventories.newStack(avp.itemIngotCopper, 2), Inventories.newStack(avp.itemSilicon, 2), Inventories.newStack(avp.itemPolycarbonate, 1)));
        AssemblerAPI.instance.registerSchematic(new AssemblerSchematic("solidstatedrive", Inventories.newStack(avp.itemSolidStateDrive, 1), Inventories.newStack(avp.itemRAM, 2), Inventories.newStack(avp.itemVoltageRegulator, 1), Inventories.newStack(avp.itemIntegratedCircuit, 1), Inventories.newStack(avp.itemIngotLithium, 1), Inventories.newStack(avp.itemPolycarbonate, 1)));

    }
}
