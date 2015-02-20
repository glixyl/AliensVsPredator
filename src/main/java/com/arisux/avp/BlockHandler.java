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
import com.arisux.avp.block.BlockShape.ShapeTypes;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public class BlockHandler extends IBHandler implements IInitializable
{
	public Block
		terrainHiveResin = (new BlockHiveResin(Material.wood)).setHardness(0.1F).setResistance(2.0F),
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
		blockFloorGrill = ((HookedBlock) (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F)).setRenderNormal(false).setOpaque(false).setLightOpacity(4),
		blockFloorGrillSlab = (new HookedBlockSlab(Material.iron)).setRenderNormal(false).setOpaque(false).setHardness(3.5F).setResistance(2.0F).setLightOpacity(4),
		blockFloorGrillStairs = (new HookedBlockStairs(blockFloorGrill)).setRenderNormal(false).setOpaque(false).setHardness(3.0F).setResistance(4.0F).setLightOpacity(4),
		blockWall = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockWallSlope = (new BlockShape(Material.iron, ShapeTypes.SLOPE)).setHardness(3.5F).setResistance(2.0F),
		blockWallCorner = (new BlockShape(Material.iron, ShapeTypes.CORNER)).setHardness(3.5F).setResistance(2.0F),
		blockWallInvertedCorner = (new BlockShape(Material.iron, ShapeTypes.INVERTED_CORNER)).setHardness(3.5F).setResistance(2.0F),
		blockWallRidge = (new BlockShape(Material.iron, ShapeTypes.RIDGE)).setHardness(3.5F).setResistance(2.0F),
		blockWallInvertedRidge = (new BlockShape(Material.iron, ShapeTypes.INVERTED_RIDGE)).setHardness(3.5F).setResistance(2.0F),
		blockWallSmartInvertedRidge = (new BlockShape(Material.iron, ShapeTypes.SMART_INVERTED_RIDGE)).setHardness(3.5F).setResistance(2.0F),
		blockWallSmartRidge = (new BlockShape(Material.iron, ShapeTypes.SMART_RIDGE)).setHardness(3.5F).setResistance(2.0F),
		blockWallW = (new HookedBlock(Material.iron).setIconSet(new RenderUtil.IconSet(this.getMod().domain() + "wall_top", this.getMod().domain() + "wall_top", this.getMod().domain() + "wall_top", this.getMod().domain() + "wall_side", this.getMod().domain() + "wall_side", this.getMod().domain() + "wall_side", this.getMod().domain() + "wall_side"))).setHardness(3.5F).setResistance(2.0F),
		blockCeiling = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F).setLightOpacity(0),
		blockCeilingFan = (new HookedBlock(Material.iron)).setOpaque(false).setHardness(3.5F).setResistance(2.0F),
		blockCeiliingVent = (new HookedBlock(Material.iron)).setOpaque(false).setRenderNormal(false).setHardness(3.5F).setResistance(2.0F).setLightOpacity(0),
		blockCeilingGrill = ((HookedBlock) (new HookedBlock(Material.iron)).setOpaque(false).setRenderNormal(false).setHardness(3.5F).setResistance(2.0F)).setLightOpacity(4),
		blockCeilingGrillStairs = (new HookedBlockStairs(blockCeilingGrill)).setOpaque(false).setRenderNormal(false).setHardness(3.5F).setResistance(2.0F).setLightOpacity(4),
		blockCeilingGrillSlab = (new HookedBlockSlab(Material.iron)).setOpaque(false).setRenderNormal(false).setHardness(3.5F).setResistance(2.0F).setLightOpacity(4),
		blockWallVent = (new HookedBlock(Material.iron)).setOpaque(false).setHardness(3.5F).setResistance(2.0F).setLightOpacity(0),
		blockIronBricks = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockVerticalMetal = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockColumnMetal1 = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockColumnMetal2 = (new HookedBlock(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockIronBricksStairs = (new HookedBlockStairs(blockIronBricks)).setHardness(3.0F).setResistance(4.0F),
		blockWallStairs = (new HookedBlockStairs(blockWall)).setHardness(3.5F).setResistance(2.0F),
		blockWallSlab = (new HookedBlockSlab(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockIronBricksSlab = (new HookedBlockSlab(Material.iron)).setHardness(3.5F).setResistance(2.0F),
		blockIndustrialGlass = (new HookedBlock(Material.iron)).setOpaque(false).setRenderNormal(false).setHardness(3.5F).setResistance(2.0F).setLightOpacity(0),
		blockIndustrialGlassSlab = (new HookedBlockSlab(Material.iron)).setOpaque(false).setHardness(3.5F).setResistance(2.0F).setLightOpacity(0),
		blockIndustrialGlassStairs = (new HookedBlockStairs(blockIndustrialGlass)).setOpaque(false).setHardness(3.5F).setResistance(2.0F).setLightOpacity(0),
		terrainUniDirt = (new HookedBlock(Material.ground)).setHardness(0.5F).setResistance(2.0F),
		terrainUniStone = (new HookedBlock(Material.rock)).setHardness(1.3F).setResistance(2.0F),
		terrainUniSand = (new HookedBlock(Material.sand)).setHardness(3.5F).setResistance(2.0F),
		terrainUniGravel = (new HookedBlock(Material.sand)).setHardness(3.0F),
		terrainStalagmite = (new BlockStalagmite(Material.plants)).setHardness(0.0F).setLightOpacity(0),
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
		blockServer = (new BlockServer(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockCryostasisTube = (new BlockCryostasisTube(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockPlastic = (new HookedBlock(Material.clay)).setHardness(1.5F).setResistance(2.0F),
		blockPlasticTile = (new HookedBlock(Material.clay)).setHardness(1.5F).setResistance(2.0F);

	public BlockHandler()
	{
		super(AliensVsPredator.instance());
	}

	public void initializeClient(FMLInitializationEvent event)
	{
		((HookedBlock) blockWallSlope).setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled);
		((HookedBlock) blockWallCorner).setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled);
		((HookedBlock) blockWallInvertedCorner).setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled);
		((HookedBlock) blockWallRidge).setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled);
		((HookedBlock) blockWallInvertedRidge).setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled);
		((HookedBlock) blockWallSmartInvertedRidge).setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled);
		((HookedBlock) blockWallSmartRidge).setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled);
	}

	public void registerShapedBlockSet(Block block, String reference)
	{
		this.registerShapedBlockSet(block, reference, block);
	}

	public void registerShapedBlockSet(Block block, String reference, Block blockParent)
	{
		if (block instanceof HookedBlock)
		{
			HookedBlock blockParentHook = (HookedBlock) blockParent;
			HookedBlock blockBaseHook = (HookedBlock) block;
			HookedBlock blockSlope = ((HookedBlock) (new BlockShape(blockBaseHook.getMaterial(), ShapeTypes.SLOPE)).setIconSet(blockParentHook.getIconSet()).setResistance(blockBaseHook.getResistance()).setHardness(blockBaseHook.getHardness()).setLightOpacity(blockBaseHook.getLightOpacity()));
			HookedBlock blockCorner = ((HookedBlock) (new BlockShape(blockBaseHook.getMaterial(), ShapeTypes.CORNER)).setIconSet(blockParentHook.getIconSet()).setResistance(blockBaseHook.getResistance()).setHardness(blockBaseHook.getHardness()).setLightOpacity(blockBaseHook.getLightOpacity()));
			HookedBlock blockInvertedCorner = ((HookedBlock) (new BlockShape(blockBaseHook.getMaterial(), ShapeTypes.INVERTED_CORNER)).setIconSet(blockParentHook.getIconSet()).setResistance(blockBaseHook.getResistance()).setHardness(blockBaseHook.getHardness()).setLightOpacity(blockBaseHook.getLightOpacity()));
			HookedBlock blockRidge = ((HookedBlock) (new BlockShape(blockBaseHook.getMaterial(), ShapeTypes.RIDGE)).setIconSet(blockParentHook.getIconSet()).setResistance(blockBaseHook.getResistance()).setHardness(blockBaseHook.getHardness()).setLightOpacity(blockBaseHook.getLightOpacity()));
			HookedBlock blockInvertedRidge = ((HookedBlock) (new BlockShape(blockBaseHook.getMaterial(), ShapeTypes.INVERTED_RIDGE)).setIconSet(blockParentHook.getIconSet()).setResistance(blockBaseHook.getResistance()).setHardness(blockBaseHook.getHardness()).setLightOpacity(blockBaseHook.getLightOpacity()));
			HookedBlock blockSmartInvertedRidge = ((HookedBlock) (new BlockShape(blockBaseHook.getMaterial(), ShapeTypes.SMART_INVERTED_RIDGE)).setIconSet(blockParentHook.getIconSet()).setResistance(blockBaseHook.getResistance()).setHardness(blockBaseHook.getHardness()).setLightOpacity(blockBaseHook.getLightOpacity()));
			HookedBlock blockSmartRidge = ((HookedBlock) (new BlockShape(blockBaseHook.getMaterial(), ShapeTypes.SMART_RIDGE)).setIconSet(blockParentHook.getIconSet()).setResistance(blockBaseHook.getResistance()).setHardness(blockBaseHook.getHardness()).setLightOpacity(blockBaseHook.getLightOpacity()));

			registerBlock(blockBaseHook, reference, blockParent);
			registerBlock(blockSlope, reference + ".slope", blockParent);
			registerBlock(blockCorner, reference + ".corner", blockParent);
			registerBlock(blockInvertedCorner, reference + ".invertedcorner", blockParent);
			registerBlock(blockRidge, reference + ".ridge", blockParent);
			registerBlock(blockInvertedRidge, reference + ".invertedridge", blockParent);
			registerBlock(blockSmartInvertedRidge, reference + ".smartinvertedridge", blockParent);
			registerBlock(blockSmartRidge, reference + ".smartridge", blockParent);

			if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
			{
				blockSlope.setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled);
				blockCorner.setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled);
				blockInvertedCorner.setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled);
				blockRidge.setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled);
				blockInvertedRidge.setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled);
				blockSmartInvertedRidge.setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled);
				blockSmartRidge.setRenderType(AliensVsPredator.instance().renderer.renderTypeAngled);
			}
		}
	}

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			this.initializeClient(event);
		}

		registerShapedBlockSet(terrainUniDirt, "unidirt");
		registerShapedBlockSet(terrainUniStone, "unistone");
		registerShapedBlockSet(terrainUniSand, "unisand");
		registerShapedBlockSet(terrainUniGravel, "unigravel");
		registerBlock(terrainStalagmite, "stalagmite");
		registerBlock(oreSilicon, "oresilicon");
		registerBlock(oreCopper, "orecopper");
		registerBlock(oreLithium, "orelithium");
		registerBlock(oreBauxite, "orebauxite");
		registerShapedBlockSet(blockCeiling, "ceilingpanel");
		registerShapedBlockSet(blockCeilingFan, "ceilingfan");
		registerShapedBlockSet(blockCeiliingVent, "ceilingvent");
		registerShapedBlockSet(blockCeilingGrill, "ceilinggrill");
		registerBlock(blockCeilingGrillStairs, "ceilinggrillstairs", blockCeilingGrill);
		registerBlock(blockCeilingGrillSlab, "ceilinggrillslab", blockCeilingGrill);
		registerShapedBlockSet(blockFloorGrill, "floorgrill");
		registerBlock(blockFloorGrillStairs, "floorgrillstairs", blockFloorGrill);
		registerBlock(blockFloorGrillSlab, "floorgrillslab", blockFloorGrill);
		registerShapedBlockSet(blockWall, "industrialwall");
		registerShapedBlockSet(blockWallW, "industrialwall2");
		registerBlock(blockWallStairs, "industrialwallstairs", blockWall);
		registerBlock(blockWallSlab, "industrialslab", blockWall);
		registerShapedBlockSet(blockWallVent, "industrialvent");
		registerShapedBlockSet(blockIronBricks, "industrialbricks");
		registerBlock(blockIronBricksStairs, "industrialbrickstairs", blockIronBricks);
		registerBlock(blockIronBricksSlab, "industrialbrickslab", blockIronBricks);
		registerShapedBlockSet(blockIndustrialGlass, "industrialglass");
		registerBlock(blockIndustrialGlassStairs, "industrialglassstairs", blockIndustrialGlass);
		registerBlock(blockIndustrialGlassSlab, "industrialglassslab", blockIndustrialGlass);
		registerShapedBlockSet(blockVerticalMetal, "metalpanel1");
		registerShapedBlockSet(blockColumnMetal1, "metalpanel2");
		registerShapedBlockSet(blockColumnMetal2, "metalpanel3");
		registerShapedBlockSet(terrainHiveResin, "hiveresin");
		registerBlock(blockHiveNode, "hivenode", "avp:hiveresin");
		registerShapedBlockSet(blockShipMetal1, "shippanel");
		registerShapedBlockSet(blockShipMetal2, "shippannelyautja");
		registerShapedBlockSet(blockOvamorph, "tileovamorphdesign");
		registerShapedBlockSet(blockFacehuggerRelic, "tilefacehuggerdesign");
		registerShapedBlockSet(blockAlienRelic, "tilealiendesign");
		registerShapedBlockSet(blockShipDecor1, "shipwallbase");
		registerShapedBlockSet(blockShipDecor2, "shipsupportpillar");
		registerShapedBlockSet(blockShipDecor3, "shipdecor1");
		registerShapedBlockSet(blockShipDecor5, "shipdecor2");
		registerShapedBlockSet(blockShipDecor6, "shipdecor3");
		registerShapedBlockSet(blockShipDecor4, "shipbrick");
		registerBlock(blockSacrificialSpawner, "spawner");
		registerBlock(blockSpawnerCreative, "spawnerc", blockSacrificialSpawner);
		registerShapedBlockSet(blockRelicTile, "templebricksingle", blockSacrificialSpawner);
		registerShapedBlockSet(blockTempleBrick, "templebrick");
		registerShapedBlockSet(blockTempleTile, "templetile");
		registerShapedBlockSet(blockTempleWall1, "templewallbase");
		registerShapedBlockSet(blockTempleWall2, "templefloor");
		registerShapedBlockSet(blockSkulls, "skulls");
		registerBlock(blockPortal, "lv223portal");
		registerShapedBlockSet(blockDerelict1, "engineershipwall");
		registerShapedBlockSet(blockDerelict2, "engineershipfloor");
		registerShapedBlockSet(blockDerelict3, "engineershiprock");
		registerShapedBlockSet(blockDerelict4, "engineershipbrick");
		registerShapedBlockSet(blockDerelict5, "engineershipwall2");
		registerBlock(blockAssembler, "assembler", true);
		registerBlock(blockTurret, "turret", true);
		registerBlock(blockWorkstation, "terminal", true);
		registerBlock(blockStasisMechanism, "stasismechanism", true);
		registerBlock(blockGenerator, "generator", true);
		registerBlock(blockPowerline, "powerline", true);
		registerBlock(blockBlastdoor, "blastdoor", true);
		registerBlock(ghostBlockBlastdoor, "blastdoorghost", false);
		registerBlock(blockWorklight, "worklight", true);
		registerBlock(blockServer, "server", true);
		registerBlock(blockCryostasisTube, "cryostasistube", true);
		registerShapedBlockSet(blockPlastic, "plasticblock");
		registerShapedBlockSet(blockPlasticTile, "plastictile");
	}
}
