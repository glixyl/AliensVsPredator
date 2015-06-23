package com.arisux.avp;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import com.arisux.airi.lib.BlockTypes.GhostBlock;
import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.airi.lib.BlockTypes.HookedBlockSlab;
import com.arisux.airi.lib.BlockTypes.HookedBlockStairs;
import com.arisux.airi.lib.ItemTypes.HookedItem;
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
		blockPortalVarda = (new BlockPortalVarda().setHardness(-1.0F).setLightLevel(2.0F)),
		blockPortalAcheron = (new BlockPortalAcheron().setHardness(-1.0F).setLightLevel(2.0F)),
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
		blockRepulsionGenerator = (new BlockGenerator(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockPowerline = (new BlockPowerline(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockBlastdoor = (new BlockBlastdoor(Material.iron)).setHardness(5F).setResistance(5F),
		ghostBlockBlastdoor = (new GhostBlock(blockBlastdoor)).setAttributesFrom((HookedBlock) blockBlastdoor),
		blockWorklight = (new BlockWorklight(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockCryostasisTube = (new BlockCryostasisTube(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockPlastic = (new HookedBlock(Material.clay)).setHardness(1.5F).setResistance(2.0F),
		blockLightPanel = (new BlockLightPanel(Material.iron, true)).setHardness(1.5F).setResistance(2.0F),
		blockPlasticTile = (new HookedBlock(Material.clay)).setHardness(1.5F).setResistance(2.0F),
		blockSatelliteModem = (new BlockSatelliteModem(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockSatelliteDish = (new HookedBlock(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockBattery = (new HookedBlock(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockR2PConvertor = (new HookedBlock(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockP2RConvertor = (new HookedBlock(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockSolarPanel = (new BlockSolarPanel(Material.iron)).setHardness(3.2F).setResistance(2.6F);

	public BlockHandler()
	{
		super(AliensVsPredator.instance());
	}

	public void registerShapedBlockSet(Block block, String reference)
	{
		this.registerShapedBlockSet(block, reference, block);
	}

	public void registerShapedBlockSet(Block block, String reference, int textureSide)
	{
		this.registerShapedBlockSet(block, reference, block, textureSide);
	}

	public void registerShapedBlockSet(Block block, String reference, Block blockParent)
	{
		this.registerShapedBlockSet(block, reference, blockParent, 2);
	}

	public void registerShapedBlockSet(Block block, String reference, Block blockParent, int textureSide)
	{
		if (block instanceof HookedBlock)
		{
			HookedBlock blockParentHook = (HookedBlock) blockParent;
			HookedBlock blockBaseHook = (HookedBlock) block;
			HookedBlock blockSlope = ((HookedBlock) (new BlockShape(blockBaseHook.getMaterial(), ShapeTypes.SLOPE).setTextureSide(textureSide)).setIconSet(blockParentHook.getIconSet()).setResistance(blockBaseHook.getResistance()).setHardness(blockBaseHook.getHardness()).setLightOpacity(blockBaseHook.getLightOpacity()));
			HookedBlock blockCorner = ((HookedBlock) (new BlockShape(blockBaseHook.getMaterial(), ShapeTypes.CORNER).setTextureSide(textureSide)).setIconSet(blockParentHook.getIconSet()).setResistance(blockBaseHook.getResistance()).setHardness(blockBaseHook.getHardness()).setLightOpacity(blockBaseHook.getLightOpacity()));
			HookedBlock blockInvertedCorner = ((HookedBlock) (new BlockShape(blockBaseHook.getMaterial(), ShapeTypes.INVERTED_CORNER).setTextureSide(textureSide)).setIconSet(blockParentHook.getIconSet()).setResistance(blockBaseHook.getResistance()).setHardness(blockBaseHook.getHardness()).setLightOpacity(blockBaseHook.getLightOpacity()));
			HookedBlock blockRidge = ((HookedBlock) (new BlockShape(blockBaseHook.getMaterial(), ShapeTypes.RIDGE).setTextureSide(textureSide)).setIconSet(blockParentHook.getIconSet()).setResistance(blockBaseHook.getResistance()).setHardness(blockBaseHook.getHardness()).setLightOpacity(blockBaseHook.getLightOpacity()));
			HookedBlock blockInvertedRidge = ((HookedBlock) (new BlockShape(blockBaseHook.getMaterial(), ShapeTypes.INVERTED_RIDGE).setTextureSide(textureSide)).setIconSet(blockParentHook.getIconSet()).setResistance(blockBaseHook.getResistance()).setHardness(blockBaseHook.getHardness()).setLightOpacity(blockBaseHook.getLightOpacity()));
			HookedBlock blockSmartInvertedRidge = ((HookedBlock) (new BlockShape(blockBaseHook.getMaterial(), ShapeTypes.SMART_INVERTED_RIDGE).setTextureSide(textureSide)).setIconSet(blockParentHook.getIconSet()).setResistance(blockBaseHook.getResistance()).setHardness(blockBaseHook.getHardness()).setLightOpacity(blockBaseHook.getLightOpacity()));
			HookedBlock blockSmartRidge = ((HookedBlock) (new BlockShape(blockBaseHook.getMaterial(), ShapeTypes.SMART_RIDGE).setTextureSide(textureSide)).setIconSet(blockParentHook.getIconSet()).setResistance(blockBaseHook.getResistance()).setHardness(blockBaseHook.getHardness()).setLightOpacity(blockBaseHook.getLightOpacity()));

			CreativeTabs tab = AliensVsPredator.instance().tabBlocks();
			
			registerBlock(blockBaseHook, reference, blockParent, tab);
			registerBlock(blockSlope, reference + ".slope", blockParent, tab);
			registerBlock(blockCorner, reference + ".corner", blockParent, tab);
			registerBlock(blockInvertedCorner, reference + ".invertedcorner", blockParent, tab);
			registerBlock(blockRidge, reference + ".ridge", blockParent, tab);
			registerBlock(blockInvertedRidge, reference + ".invertedridge", blockParent, tab);
			registerBlock(blockSmartInvertedRidge, reference + ".smartinvertedridge", blockParent, tab);
			registerBlock(blockSmartRidge, reference + ".smartridge", blockParent, tab);

			if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
			{
				blockSlope.setRenderType(AliensVsPredator.renderer().renderTypeAngled);
				blockCorner.setRenderType(AliensVsPredator.renderer().renderTypeAngled);
				blockInvertedCorner.setRenderType(AliensVsPredator.renderer().renderTypeAngled);
				blockRidge.setRenderType(AliensVsPredator.renderer().renderTypeAngled);
				blockInvertedRidge.setRenderType(AliensVsPredator.renderer().renderTypeAngled);
				blockSmartInvertedRidge.setRenderType(AliensVsPredator.renderer().renderTypeAngled);
				blockSmartRidge.setRenderType(AliensVsPredator.renderer().renderTypeAngled);
			}
		}
	}

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		CreativeTabs tab = AliensVsPredator.instance().tabBlocks();
		registerShapedBlockSet(terrainUniDirt, "unidirt");
		registerShapedBlockSet(terrainUniStone, "unistone");
		registerShapedBlockSet(terrainUniSand, "unisand");
		registerShapedBlockSet(terrainUniGravel, "unigravel");
		registerBlock(terrainStalagmite, "stalagmite");
		registerBlock(oreSilicon, "oresilicon");
		registerBlock(oreCopper, "orecopper");
		registerBlock(oreLithium, "orelithium");
		registerBlock(oreBauxite, "orebauxite");
		registerBlock(blockSolarPanel, "blocksolarpanel");
		registerBlock(blockSatelliteModem, "blocksatellitemodem");
		registerBlock(blockSatelliteDish, "blocksatellitedish");
		registerBlock(blockBattery, "blockbattery");
		registerShapedBlockSet(blockCeiling, "ceilingpanel");
		registerShapedBlockSet(blockCeilingFan, "ceilingfan");
		registerShapedBlockSet(blockCeiliingVent, "ceilingvent");
		registerShapedBlockSet(blockCeilingGrill, "ceilinggrill");
		registerBlock(blockCeilingGrillStairs, "ceilinggrillstairs", blockCeilingGrill, tab);
		registerBlock(blockCeilingGrillSlab, "ceilinggrillslab", blockCeilingGrill, tab);
		registerShapedBlockSet(blockFloorGrill, "floorgrill");
		registerBlock(blockFloorGrillStairs, "floorgrillstairs", blockFloorGrill, tab);
		registerBlock(blockFloorGrillSlab, "floorgrillslab", blockFloorGrill, tab);
		registerShapedBlockSet(blockWall, "industrialwall");
		registerShapedBlockSet(blockWallW, "industrialwall2");
		registerBlock(blockR2PConvertor, "blockr2pconvertor");
		registerBlock(blockP2RConvertor, "blockp2rconvertor");
		registerBlock(blockWallStairs, "industrialwallstairs", blockWall, tab);
		registerBlock(blockWallSlab, "industrialslab", blockWall, tab);
		registerShapedBlockSet(blockWallVent, "industrialvent");
		registerShapedBlockSet(blockIronBricks, "industrialbricks");
		registerBlock(blockIronBricksStairs, "industrialbrickstairs", blockIronBricks, tab);
		registerBlock(blockIronBricksSlab, "industrialbrickslab", blockIronBricks, tab);
		registerShapedBlockSet(blockIndustrialGlass, "industrialglass");
		registerBlock(blockIndustrialGlassStairs, "industrialglassstairs", blockIndustrialGlass, tab);
		registerBlock(blockIndustrialGlassSlab, "industrialglassslab", blockIndustrialGlass, tab);
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
		registerShapedBlockSet(blockRelicTile, "templebricksingle", blockSacrificialSpawner, 0);
		registerShapedBlockSet(blockTempleBrick, "templebrick");
		registerShapedBlockSet(blockTempleTile, "templetile");
		registerShapedBlockSet(blockTempleWall1, "templewallbase");
		registerShapedBlockSet(blockTempleWall2, "templefloor");
		registerShapedBlockSet(blockSkulls, "skulls");
		registerBlock(blockPortalVarda, "lv223portal");
		registerBlock(blockPortalAcheron, "lv426portal");
		registerShapedBlockSet(blockDerelict1, "engineershipwall");
		registerShapedBlockSet(blockDerelict2, "engineershipfloor");
		registerShapedBlockSet(blockDerelict3, "engineershiprock");
		registerShapedBlockSet(blockDerelict4, "engineershipbrick");
		registerShapedBlockSet(blockDerelict5, "engineershipwall2");
		registerBlock(blockAssembler, "assembler", true);
		registerBlock(blockTurret, "turret", true);
		registerBlock(blockWorkstation, "terminal", true);
		registerBlock(blockStasisMechanism, "stasismechanism", true);
		registerBlock(blockRepulsionGenerator, "generator", true);
		registerBlock(blockPowerline, "powerline", true);
		registerBlock(blockBlastdoor, "blastdoor", true);
		registerBlock(ghostBlockBlastdoor, "blastdoorghost", false);
		registerBlock(blockWorklight, "worklight", true);
		registerBlock(blockCryostasisTube, "cryostasistube", true);
		registerShapedBlockSet(blockPlastic, "plasticblock");
		registerShapedBlockSet(blockPlasticTile, "plastictile");
		registerBlock(blockLightPanel, "lightpanel", true);
	}
}
