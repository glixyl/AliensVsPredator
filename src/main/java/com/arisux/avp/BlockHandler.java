package com.arisux.avp;

import static com.arisux.airi.lib.RegistryLib.registerBlock;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import com.arisux.airi.lib.BlockLib.BlockIconVector;
import com.arisux.airi.lib.BlockTypeLib.HookedBlock;
import com.arisux.airi.lib.BlockTypeLib.HookedBlockMultiSided;
import com.arisux.airi.lib.BlockTypeLib.HookedBlockSlab;
import com.arisux.airi.lib.BlockTypeLib.HookedBlockStairs;
import com.arisux.airi.lib.RegistryLib.IBHandler;
import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.block.*;

public class BlockHandler implements IInitializable, IBHandler
{
	private ArrayList<Object> blockList = new ArrayList<Object>();

	public Block terrainHiveResin = (new BlockHiveResin(Material.plants)).setHardness(0.1F).setResistance(2.0F),
		terrainHiveResinDead = (new HookedBlock(Material.ground)).setHardness(0.1F).setResistance(2.0F),
		blockOvamorph = (new HookedBlock(Material.rock)),
		blockShipMetal1 = (new HookedBlock(Material.iron)),
		blockShipMetal2 = (new HookedBlock(Material.iron)),
		blockFacehuggerRelic = (new HookedBlock(Material.rock)),
		blockAlienRelic = (new HookedBlock(Material.rock)),
		blockShipDecor1 = (new HookedBlock(Material.iron)),
		blockShipDecor2 = (new HookedBlock(Material.iron)),
		blockShipDecor3 = (new HookedBlock(Material.iron)),
		blockShipDecor4 = (new HookedBlock(Material.iron)),
		blockShipDecor5 = (new HookedBlock(Material.iron)),
		blockShipDecor6 = (new HookedBlock(Material.iron)),
		blockSacrificialSpawner = (new BlockTempleSpawner(Material.rock, false)),
		blockSpawnerCreative = (new BlockTempleSpawner(Material.rock, true)),
		blockHiveNode = (new BlockHiveNode(Material.rock)),
		blockRelicTile = (new HookedBlock(Material.rock)),
		blockTempleBrick = (new HookedBlock(Material.rock)),
		blockTempleTile = (new HookedBlock(Material.rock)),
		blockTempleWall1 = (new HookedBlock(Material.rock)),
		blockTempleWall2 = (new HookedBlock(Material.rock)),
		blockSkulls = (new HookedBlock(Material.rock)),
		blockPortal = (new BlockPortalLV223().setHardness(-1.0F).setLightLevel(2.0F)),
		blockDerelict1 = (new HookedBlock(Material.iron).setHardness(1.5F).setResistance(10.0F)),
		blockDerelict2 = (new HookedBlock(Material.iron).setHardness(1.5F).setResistance(10.0F)),
		blockDerelict3 = (new HookedBlock(Material.iron).setHardness(1.5F).setResistance(10.0F)),
		blockDerelict4 = (new HookedBlock(Material.iron).setHardness(1.5F).setResistance(10.0F)),
		blockDerelict5 = (new HookedBlock(Material.iron).setHardness(1.5F).setResistance(10.0F)),
		blockAssembler = (new BlockAssembler(Material.iron).setHardness(1.5F).setResistance(10.0F)),
		blockFloorGrill = ((HookedBlock) (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F)).setOpaque(false),
		blockWall = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockWallW = (new HookedBlockMultiSided(new BlockIconVector(getDomain() + "wall_top", getDomain() + "wall_top", getDomain() + "wall_top", getDomain() + "wall_side", getDomain() + "wall_side", getDomain() + "wall_side", getDomain() + "wall_side"), Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockCeiling = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F).setLightOpacity(100),
		blockCeiliingVent = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockCeilingGrill = ((HookedBlock) (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F)).setOpaque(false),
		blockGrillStairs = (new HookedBlockStairs(blockFloorGrill)).setHardness(3.0F).setResistance(4.0F),
		blockCeilingFan = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockCeilingGrillSlab = (new HookedBlockSlab(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockFloorGrillSlab = (new HookedBlockSlab(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockWallVent = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockIronBricks = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockIndustrialGlass = ((HookedBlock) (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F)).setOpaque(false),
		blockVerticalMetal = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockColumnMetal1 = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockColumnMetal2 = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockIronBricksStairs = (new HookedBlockStairs(blockIronBricks)).setHardness(3.0F).setResistance(4.0F),
		blockWallStairs = (new HookedBlockStairs(blockWall)).setHardness(3.5F).setResistance(2.0F),
		blockCeilingGrillStairs = (new HookedBlockStairs(blockCeilingGrill)).setHardness(3.5F).setResistance(2.0F),
		blockWallSlab = (new HookedBlockSlab(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockIronBricksSlab = (new HookedBlockSlab(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockIndustrialGlassSlab = (new HookedBlockSlab(Material.iron)).setHardness(3.5F).setResistance(2.0F).setLightOpacity(0),
		blockIndustrialGlassStairs = (new HookedBlockStairs(blockIndustrialGlass)).setHardness(3.5F).setResistance(2.0F).setLightOpacity(0),
		terrainUniDirt = (new HookedBlock(Material.ground)).setHardness(0.5F).setResistance(2.0F),
		terrainUniStone = (new HookedBlock(Material.rock)).setHardness(1.3F).setResistance(2.0F),
		terrainUniSand = (new HookedBlock(Material.sand)).setHardness(3.5F).setResistance(2.0F),
		terrainUniGravel = (new HookedBlock(Material.sand)).setHardness(3.0F),
		terrainStalagmite = (new BlockStalagmite(Material.plants)).setHardness(0.0F),
		oreSilicon = (new BlockOreSilicon(Material.rock)).setHardness(2.2F).setResistance(1.4F),
		oreLithium = (new BlockOreSilicon(Material.iron)).setHardness(4.2F).setResistance(5.4F),
		oreCopper = (new HookedBlock(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockTurret = (new BlockTurret(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		oreBauxite = (new HookedBlock(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockWorkstation = (new BlockWorkstation(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockStasisMechanism = (new BlockStasisMechanism(Material.iron)),
		blockGenerator = (new BlockGenerator(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockPowerline = (new BlockPowerline(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockBlastdoor = (new BlockBlastdoor(Material.iron)).setHardness(5F).setResistance(5F),
		blockWorklight = (new BlockWorklight(Material.iron)).setHardness(3.2F).setResistance(2.6F);

	public void initialize()
	{
		registerBlock(terrainUniDirt, "unidirt", this);
		registerBlock(terrainUniStone, "unistone", this);
		registerBlock(terrainUniSand, "unisand", this);
		registerBlock(terrainUniGravel, "unigravel", this);
		registerBlock(terrainStalagmite, "stalagmite", this);
		registerBlock(oreSilicon, "oresilicon", this);
		registerBlock(oreCopper, "orecopper", this);
		registerBlock(oreLithium, "orelithium", this);
		registerBlock(oreBauxite, "orebauxite", this);
		registerBlock(blockCeiling, "ceilingpanel", this);
		registerBlock(blockCeilingFan, "ceilingfan", this);
		registerBlock(blockCeiliingVent, "ceilingvent", this);
		registerBlock(blockCeilingGrill, "ceilinggrill", this);
		registerBlock(blockCeilingGrillStairs, "ceilinggrillstairs", blockCeilingGrill, this);
		registerBlock(blockCeilingGrillSlab, "ceilinggrillslab", blockCeilingGrill, this);
		registerBlock(blockFloorGrill, "floorgrill", this);
		registerBlock(blockGrillStairs, "floorgrillstairs", blockFloorGrill, this);
		registerBlock(blockFloorGrillSlab, "floorgrillslab", blockFloorGrill, this);
		registerBlock(blockWall, "industrialwall", this);
		registerBlock(blockWallW, "industrialwall2", blockWall, this);
		registerBlock(blockWallStairs, "industrialwallstairs", blockWall, this);
		registerBlock(blockWallSlab, "industrialslab", blockWall, this);
		registerBlock(blockWallVent, "industrialvent", this);
		registerBlock(blockIronBricks, "industrialbricks", this);
		registerBlock(blockIronBricksStairs, "industrialbrickstairs", blockIronBricks, this);
		registerBlock(blockIronBricksSlab, "industrialbrickslab", blockIronBricks, this);
		registerBlock(blockIndustrialGlass, "industrialglass", this);
		registerBlock(blockIndustrialGlassStairs, "industrialglassstairs", blockIndustrialGlass, this);
		registerBlock(blockIndustrialGlassSlab, "industrialglassslab", blockIndustrialGlass, this);
		registerBlock(blockVerticalMetal, "metalpanel1", this);
		registerBlock(blockColumnMetal1, "metalpanel2", this);
		registerBlock(blockColumnMetal2, "metalpanel3", this);
		registerBlock(terrainHiveResin, "hiveresin", this);
		registerBlock(terrainHiveResinDead, "deadhiveresin", "avp:hiveresin", this);
		registerBlock(blockHiveNode, "hivenode", "avp:hiveresin", this);
		registerBlock(blockShipMetal1, "shippanel", this);
		registerBlock(blockShipMetal2, "shippannelyautja", this);
		registerBlock(blockOvamorph, "tileovamorphdesign", this);
		registerBlock(blockFacehuggerRelic, "tilefacehuggerdesign", this);
		registerBlock(blockAlienRelic, "tilealiendesign", this);
		registerBlock(blockShipDecor1, "shipwallbase", this);
		registerBlock(blockShipDecor2, "shipsupportpillar", this);
		registerBlock(blockShipDecor3, "shipdecor1", this);
		registerBlock(blockShipDecor5, "shipdecor2", this);
		registerBlock(blockShipDecor6, "shipdecor3", this);
		registerBlock(blockShipDecor4, "shipbrick", this);
		registerBlock(blockSacrificialSpawner, "spawner", this);
		registerBlock(blockSpawnerCreative, "spawnerc", blockSacrificialSpawner, this);
		registerBlock(blockRelicTile, "templebricksingle", blockSacrificialSpawner, this);// TODO: bottom of spawner
		registerBlock(blockTempleBrick, "templebrick", this);
		registerBlock(blockTempleTile, "templetile", this);
		registerBlock(blockTempleWall1, "templewallbase", this);
		registerBlock(blockTempleWall2, "templefloor", this);
		registerBlock(blockSkulls, "skulls", this);
		registerBlock(blockPortal, "lv223portal", this);
		registerBlock(blockDerelict1, "engineershipwall", this);
		registerBlock(blockDerelict2, "engineershipfloor", this);
		registerBlock(blockDerelict3, "engineershiprock", this);
		registerBlock(blockDerelict4, "engineershipbrick", this);
		registerBlock(blockDerelict5, "engineershipwall2", this);
		registerBlock(blockAssembler, "assembler", this, false);
		registerBlock(blockTurret, "turret", this, true);
		registerBlock(blockWorkstation, "workstation", this, true);
		registerBlock(blockStasisMechanism, "stasismechanism", this, true);
		registerBlock(blockGenerator, "generator", this, true);
		registerBlock(blockPowerline, "powerline", this, true);
		registerBlock(blockBlastdoor, "blastdoor", this, true);
		registerBlock(blockWorklight, "worklight", this, true);
	}

	@Override
	public String getDomain()
	{
		return Properties.DOMAIN;
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return AliensVsPredator.INSTANCE.tab;
	}

	@Override
	public ArrayList<Object> getHandledObjects()
	{
		return blockList;
	}
}
