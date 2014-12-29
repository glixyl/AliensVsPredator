package com.arisux.avp;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.arisux.airi.lib.BlockTypes.GhostBlock;
import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.airi.lib.BlockTypes.HookedBlockSlab;
import com.arisux.airi.lib.BlockTypes.HookedBlockStairs;
import com.arisux.airi.lib.ModUtil.IBHandler;
import com.arisux.airi.lib.*;
import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.block.*;

import cpw.mods.fml.common.event.FMLInitializationEvent;

public class BlockHandler extends IBHandler implements IInitializable
{
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
//		blockWallAngled = (new HookedBlock(Material.iron).setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled)).setHardness(3.5F).setResistance(2.0F),
		blockWallW = (new HookedBlock(Material.iron).setIconSet(new RenderUtil.IconSet(this.getMod().domain() + "wall_top", this.getMod().domain() + "wall_top", this.getMod().domain() + "wall_top", this.getMod().domain() + "wall_side", this.getMod().domain() + "wall_side", this.getMod().domain() + "wall_side", this.getMod().domain() + "wall_side"))).setHardness(3.5F).setResistance(2.0F),
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
		ghostBlockBlastdoor = (new GhostBlock(blockBlastdoor)).setAttributesFrom((HookedBlock) blockBlastdoor),
		blockWorklight = (new BlockWorklight(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockNetworkCable = (new BlockNetworkCable(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockNetworkLight = (new BlockNetworkLight(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockServer = (new BlockServer(Material.iron)).setHardness(3.2F).setResistance(2.6F);

	public BlockHandler()
	{
		super(AliensVsPredator.instance());
	}
	
	@Override
	public void initialize(FMLInitializationEvent event)
	{
		registerBlock(terrainUniDirt, "unidirt");
		registerBlock(terrainUniStone, "unistone");
		registerBlock(terrainUniSand, "unisand");
		registerBlock(terrainUniGravel, "unigravel");
		registerBlock(terrainStalagmite, "stalagmite");
		registerBlock(oreSilicon, "oresilicon");
		registerBlock(oreCopper, "orecopper");
		registerBlock(oreLithium, "orelithium");
		registerBlock(oreBauxite, "orebauxite");
		registerBlock(blockCeiling, "ceilingpanel");
		registerBlock(blockCeilingFan, "ceilingfan");
		registerBlock(blockCeiliingVent, "ceilingvent");
		registerBlock(blockCeilingGrill, "ceilinggrill");
		registerBlock(blockCeilingGrillStairs, "ceilinggrillstairs", blockCeilingGrill);
		registerBlock(blockCeilingGrillSlab, "ceilinggrillslab", blockCeilingGrill);
		registerBlock(blockFloorGrill, "floorgrill");
		registerBlock(blockGrillStairs, "floorgrillstairs", blockFloorGrill);
		registerBlock(blockFloorGrillSlab, "floorgrillslab", blockFloorGrill);
		registerBlock(blockWall, "industrialwall");
//		registerBlock(blockWallAngled, "industrialwallangled", blockWall);
		registerBlock(blockWallW, "industrialwall2", blockWall);
		registerBlock(blockWallStairs, "industrialwallstairs", blockWall);
		registerBlock(blockWallSlab, "industrialslab", blockWall);
		registerBlock(blockWallVent, "industrialvent");
		registerBlock(blockIronBricks, "industrialbricks");
		registerBlock(blockIronBricksStairs, "industrialbrickstairs", blockIronBricks);
		registerBlock(blockIronBricksSlab, "industrialbrickslab", blockIronBricks);
		registerBlock(blockIndustrialGlass, "industrialglass");
		registerBlock(blockIndustrialGlassStairs, "industrialglassstairs", blockIndustrialGlass);
		registerBlock(blockIndustrialGlassSlab, "industrialglassslab", blockIndustrialGlass);
		registerBlock(blockVerticalMetal, "metalpanel1");
		registerBlock(blockColumnMetal1, "metalpanel2");
		registerBlock(blockColumnMetal2, "metalpanel3");
		registerBlock(terrainHiveResin, "hiveresin");
		registerBlock(terrainHiveResinDead, "deadhiveresin", "avp:hiveresin");
		registerBlock(blockHiveNode, "hivenode", "avp:hiveresin");
		registerBlock(blockShipMetal1, "shippanel");
		registerBlock(blockShipMetal2, "shippannelyautja");
		registerBlock(blockOvamorph, "tileovamorphdesign");
		registerBlock(blockFacehuggerRelic, "tilefacehuggerdesign");
		registerBlock(blockAlienRelic, "tilealiendesign");
		registerBlock(blockShipDecor1, "shipwallbase");
		registerBlock(blockShipDecor2, "shipsupportpillar");
		registerBlock(blockShipDecor3, "shipdecor1");
		registerBlock(blockShipDecor5, "shipdecor2");
		registerBlock(blockShipDecor6, "shipdecor3");
		registerBlock(blockShipDecor4, "shipbrick");
		registerBlock(blockSacrificialSpawner, "spawner");
		registerBlock(blockSpawnerCreative, "spawnerc", blockSacrificialSpawner);
		registerBlock(blockRelicTile, "templebricksingle", blockSacrificialSpawner);
		registerBlock(blockTempleBrick, "templebrick");
		registerBlock(blockTempleTile, "templetile");
		registerBlock(blockTempleWall1, "templewallbase");
		registerBlock(blockTempleWall2, "templefloor");
		registerBlock(blockSkulls, "skulls");
		registerBlock(blockPortal, "lv223portal");
		registerBlock(blockDerelict1, "engineershipwall");
		registerBlock(blockDerelict2, "engineershipfloor");
		registerBlock(blockDerelict3, "engineershiprock");
		registerBlock(blockDerelict4, "engineershipbrick");
		registerBlock(blockDerelict5, "engineershipwall2");
		registerBlock(blockAssembler, "assembler", true);
		registerBlock(blockTurret, "turret", true);
		registerBlock(blockWorkstation, "terminal", true);
		registerBlock(blockStasisMechanism, "stasismechanism", true);
		registerBlock(blockGenerator, "generator", true);
		registerBlock(blockPowerline, "powerline", true);
		registerBlock(blockNetworkCable, "networkCable", true);
		registerBlock(blockNetworkLight, "networkLight", true);
		registerBlock(blockBlastdoor, "blastdoor", true);
		registerBlock(ghostBlockBlastdoor, "blastdoorghost", false);
		registerBlock(blockWorklight, "worklight", true);
		registerBlock(blockServer, "server", true);
	}
}
