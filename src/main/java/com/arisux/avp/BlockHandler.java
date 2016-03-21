package com.arisux.avp;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.airi.lib.BlockTypes.HookedBlockSlab;
import com.arisux.airi.lib.BlockTypes.HookedBlockStairs;
import com.arisux.airi.lib.ModUtil.IBHandler;
import com.arisux.airi.lib.client.render.IconSet;
import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.block.BlockAmpule;
import com.arisux.avp.block.BlockAssembler;
import com.arisux.avp.block.BlockBlackGoo;
import com.arisux.avp.block.BlockBlastdoor;
import com.arisux.avp.block.BlockCryostasisTube;
import com.arisux.avp.block.BlockGenerator;
import com.arisux.avp.block.BlockGunLocker;
import com.arisux.avp.block.BlockHiveNode;
import com.arisux.avp.block.BlockHiveResin;
import com.arisux.avp.block.BlockLightPanel;
import com.arisux.avp.block.BlockLocker;
import com.arisux.avp.block.BlockMist;
import com.arisux.avp.block.BlockNegativeTransformer;
import com.arisux.avp.block.BlockP2RConvertor;
import com.arisux.avp.block.BlockPortal;
import com.arisux.avp.block.BlockPowercell;
import com.arisux.avp.block.BlockPowerline;
import com.arisux.avp.block.BlockR2PConvertor;
import com.arisux.avp.block.BlockSatelliteModem;
import com.arisux.avp.block.BlockShape;
import com.arisux.avp.block.BlockShape.ShapeTypes;
import com.arisux.avp.block.BlockSolarPanel;
import com.arisux.avp.block.BlockStalagmite;
import com.arisux.avp.block.BlockStasisMechanism;
import com.arisux.avp.block.BlockSupplies;
import com.arisux.avp.block.BlockTempleSpawner;
import com.arisux.avp.block.BlockTransformer;
import com.arisux.avp.block.BlockTurret;
import com.arisux.avp.block.BlockUnidentifiedLog;
import com.arisux.avp.block.BlockUnidentifiedTreeTendon;
import com.arisux.avp.block.BlockWorkstation;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockHandler extends IBHandler implements IInitializable
{
	public Block terrainHiveResin = (new BlockHiveResin(Material.wood)).setHardness(5F).setResistance(10.0F).setLightOpacity(255),
		blockOvamorph = (new HookedBlock(Material.rock)),
		blockShipMetal1 = (new HookedBlock(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockShipMetal2 = (new HookedBlock(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockFacehuggerRelic = (new HookedBlock(Material.rock).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockAlienRelic = (new HookedBlock(Material.rock).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockShipDecor1 = (new HookedBlock(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockShipDecor2 = (new HookedBlock(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockShipDecor3 = (new HookedBlock(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockShipDecor4 = (new HookedBlock(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockShipDecor5 = (new HookedBlock(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockShipDecor6 = (new HookedBlock(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockSacrificialSpawner = (new BlockTempleSpawner(Material.rock, false)),
		blockSpawnerCreative = (new BlockTempleSpawner(Material.rock, true)),
		blockHiveNode = (new BlockHiveNode(Material.rock).setHardness(5F).setResistance(10.0F).setLightOpacity(255)),
		blockRelicTile = (new HookedBlock(Material.rock).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockTempleBrick = (new HookedBlock(Material.rock).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockTempleTile = (new HookedBlock(Material.rock).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockTempleWall1 = (new HookedBlock(Material.rock).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockTempleWall2 = (new HookedBlock(Material.rock).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockSkulls = (new HookedBlock(Material.rock)),
		blockPortalVarda = (new BlockPortal(AliensVsPredator.settings().dimensionIdVarda()).setIconSet(new IconSet(this.getMod().domain() + "portal.varda")).setHardness(-1.0F).setLightLevel(2.0F)),
		blockPortalAcheron = (new BlockPortal(AliensVsPredator.settings().dimensionIdAcheron()).setIconSet(new IconSet(this.getMod().domain() + "portal.acheron")).setHardness(-1.0F).setLightLevel(2.0F)),
		blockAssembler = (new BlockAssembler(Material.iron).setHardness(1.5F).setResistance(10.0F)),
		blockFloorGrill = ((HookedBlock) (new HookedBlock(Material.iron)).setHardness(5F).setResistance(30.0F)).setRenderNormal(false).setOpaque(false).setLightOpacity(4),
		blockFloorGrillSlab = (new HookedBlockSlab(Material.iron)).setRenderNormal(false).setOpaque(false).setHardness(5F).setResistance(30.0F).setLightOpacity(4),
		blockFloorGrillStairs = (new HookedBlockStairs(blockFloorGrill)).setRenderNormal(false).setOpaque(false).setHardness(5F).setResistance(30.0F).setLightOpacity(4),
		blockWall = (new HookedBlock(Material.iron)).setHardness(5F).setResistance(30.0F).setLightOpacity(255),
		blockWallW = (new HookedBlock(Material.iron).setIconSet(new IconSet(this.getMod().domain() + "wall_top", this.getMod().domain() + "wall_top", this.getMod().domain() + "wall_top", this.getMod().domain() + "wall_side", this.getMod().domain() + "wall_side", this.getMod().domain() + "wall_side", this.getMod().domain() + "wall_side"))).setHardness(5F).setResistance(30.0F).setLightOpacity(255),
		blockCeiling = (new HookedBlock(Material.iron)).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		blockCeilingFan = (new HookedBlock(Material.iron)).setOpaque(false).setHardness(5F).setResistance(30.0F),
		blockCeiliingVent = (new HookedBlock(Material.iron)).setOpaque(false).setRenderNormal(false).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		blockCeilingGrill = ((HookedBlock) (new HookedBlock(Material.iron)).setOpaque(false).setRenderNormal(false).setHardness(5F).setResistance(30.0F)).setLightOpacity(4),
		blockCeilingGrillStairs = (new HookedBlockStairs(blockCeilingGrill)).setOpaque(false).setRenderNormal(false).setHardness(5F).setResistance(30.0F).setLightOpacity(4),
		blockCeilingGrillSlab = (new HookedBlockSlab(Material.iron)).setOpaque(false).setRenderNormal(false).setHardness(5F).setResistance(30.0F).setLightOpacity(4),
		blockIronBricks = (new HookedBlock(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockVerticalMetal = (new HookedBlock(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockColumnMetal1 = (new HookedBlock(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockColumnMetal2 = (new HookedBlock(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockIronBricksStairs = (new HookedBlockStairs(blockIronBricks)).setHardness(5F).setResistance(30.0F).setLightOpacity(255),
		blockWallStairs = (new HookedBlockStairs(blockWall)).setHardness(5F).setResistance(30.0F).setLightOpacity(255),
		blockWallSlab = (new HookedBlockSlab(Material.iron)).setHardness(5F).setResistance(30.0F).setLightOpacity(255),
		blockIronBricksSlab = (new HookedBlockSlab(Material.iron)).setHardness(5F).setResistance(30.0F).setLightOpacity(255),
		blockIndustrialGlass = (new HookedBlock(Material.iron)).setOpaque(false).setRenderNormal(false).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		blockIndustrialGlassSlab = (new HookedBlockSlab(Material.iron)).setOpaque(false).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		blockIndustrialGlassStairs = (new HookedBlockStairs(blockIndustrialGlass)).setOpaque(false).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		terrainUniDirt = (new HookedBlock(Material.ground)).setHardness(0.5F).setResistance(2.0F).setLightOpacity(255),
		terrainUniStone = (new HookedBlock(Material.rock)).setHardness(1.3F).setResistance(2.0F).setLightOpacity(255),
		terrainUniSand = (new HookedBlock(Material.sand)).setHardness(3.5F).setResistance(2.0F).setLightOpacity(255),
		terrainUniGravel = (new HookedBlock(Material.sand)).setHardness(3.0F).setLightOpacity(255),
		terrainStalagmite = (new BlockStalagmite(Material.plants)).setHardness(0.0F).setLightOpacity(0),
		terrainUniTreeLog = (new BlockUnidentifiedLog()).setHardness(0.0F).setLightOpacity(0),
		terrainUniTreeTendon = (new BlockUnidentifiedTreeTendon()).setHardness(0.0F).setLightOpacity(0),
		blockTurret = (new BlockTurret(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockWorkstation = (new BlockWorkstation(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockStasisMechanism = (new BlockStasisMechanism(Material.iron)).setHardness(5.0F).setResistance(10.0F),
		blockRepulsionGenerator = (new BlockGenerator(Material.iron)).setHardness(5.0F).setResistance(10.0F),
		blockPowerline = (new BlockPowerline(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockBlastdoor = (new BlockBlastdoor(Material.iron)).setHardness(10F).setResistance(30.0F).setLightOpacity(0),
		blockCryostasisTube = (new BlockCryostasisTube(Material.iron)).setHardness(10F).setResistance(30.0F).setLightOpacity(4),
		blockPlastic = (new HookedBlock(Material.clay)).setHardness(10F).setResistance(30.0F).setLightOpacity(0),
		blockPlasticTri = (new HookedBlock(Material.clay)).setHardness(10F).setResistance(30.0F).setLightOpacity(0),
		blockPlasticCircle = (new HookedBlock(Material.clay)).setHardness(10F).setResistance(30.0F).setLightOpacity(0),
		blockLightPanel = (new BlockLightPanel(Material.iron, true)).setHardness(1.5F).setResistance(2.0F),
		blockPlasticTile = (new HookedBlock(Material.clay)).setHardness(10F).setResistance(30.0F).setLightOpacity(0),
		blockSatelliteModem = (new BlockSatelliteModem(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockSatelliteDish = (new HookedBlock(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockPowercell = (new BlockPowercell(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockR2PConvertor = (new BlockR2PConvertor(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockP2RConvertor = (new BlockP2RConvertor(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockBlackGoo = (new BlockBlackGoo(AliensVsPredator.fluids().fluidBlackGoo, Material.water)),
		blockMist = (new BlockMist(AliensVsPredator.fluids().fluidMist, Material.air)),
		blockTransformer = (new BlockTransformer(Material.iron)).setHardness(5.0F).setResistance(10.0F),
		blockNegativeTransformer = (new BlockNegativeTransformer(Material.iron)).setHardness(5.0F).setResistance(10.0F),
		blockSupplies = (new BlockSupplies(Material.iron)),
		blockSolarPanel = (new BlockSolarPanel(Material.iron)).setHardness(5.0F).setResistance(10.0F),
		oreSilicon = (new HookedBlock(Material.rock)).setHardness(2.2F).setResistance(1.4F).setLightOpacity(255),
		oreLithium = (new HookedBlock(Material.iron)).setHardness(4.2F).setResistance(5.4F).setLightOpacity(255),
		oreCopper = (new HookedBlock(Material.iron)).setHardness(3.2F).setResistance(2.6F).setLightOpacity(255),
		oreBauxite = (new HookedBlock(Material.iron)).setHardness(3.2F).setResistance(2.6F).setLightOpacity(255),
		mainframePanelShimmer = (new HookedBlock(Material.iron).setIconSet(new IconSet("avp:mainframe_shimmer")).setHardness(5F).setResistance(1F).setLightLevel(0.5F)),
		mainframePanelFlicker = (new HookedBlock(Material.iron).setIconSet(new IconSet("avp:mainframe_flicker")).setHardness(5F).setResistance(10F).setLightLevel(0.5F)),
		blockVent0 = (new HookedBlock(Material.iron)).setOpaque(false).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		blockVent1 = (new HookedBlock(Material.iron)).setOpaque(false).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		blockVent2 = (new HookedBlock(Material.iron)).setOpaque(false).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		blockEngineerShipFloor = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipBrick0 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipBrick1 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipBrick2 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipBrick3 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipGravel = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipWall0 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipWall1 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipWall2 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipWall3 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipWall4 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipRock0 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipRock1 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipRock2 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipRock3 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipColumn1 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipColumn2 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipMaterial1 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipMaterial2 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipMaterial0 = (new HookedBlock(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockLocker = (new BlockLocker(Material.iron).setHardness(1.5F).setResistance(10.0F)),
		blockGunLocker = (new BlockGunLocker(Material.iron).setHardness(1.5F).setResistance(10.0F)),
		blockAmpule = (new BlockAmpule().setHardness(5.0F).setResistance(10.0F));

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
		registerBlock(terrainUniTreeLog, "unitree.wood");
		registerBlock(terrainUniTreeTendon, "unitree.tendons");
		registerBlock(terrainStalagmite, "stalagmite");
		registerBlock(oreSilicon, "oresilicon");
		registerBlock(oreCopper, "orecopper");
		registerBlock(oreLithium, "orelithium");
		registerBlock(oreBauxite, "orebauxite");
		registerBlock(blockSolarPanel, "solarpanel");
		registerBlock(blockSatelliteModem, "satellitemodem");
		registerBlock(blockSatelliteDish, "satellitedish");
		registerBlock(blockPowercell, "powercell");
		registerShapedBlockSet(blockCeiling, "ceilingpanel");
		registerShapedBlockSet(blockCeilingFan, "ceilingfan");
		registerShapedBlockSet(blockCeiliingVent, "ceilingvent");
		registerBlock(blockTransformer, "transformer");
		registerBlock(blockNegativeTransformer, "transformernegative");
		registerShapedBlockSet(blockCeilingGrill, "ceilinggrill");
		registerBlock(blockCeilingGrillStairs, "ceilinggrillstairs", blockCeilingGrill, tab);
		registerBlock(blockCeilingGrillSlab, "ceilinggrillslab", blockCeilingGrill, tab);
		registerShapedBlockSet(blockFloorGrill, "floorgrill");
		registerBlock(blockFloorGrillStairs, "floorgrillstairs", blockFloorGrill, tab);
		registerBlock(blockFloorGrillSlab, "floorgrillslab", blockFloorGrill, tab);
		registerShapedBlockSet(blockWall, "industrialwall");
		registerShapedBlockSet(blockWallW, "industrialwall2");
		registerBlock(blockR2PConvertor, "r2pconverter");
		registerBlock(blockP2RConvertor, "p2rconverter");
		registerBlock(blockWallStairs, "industrialwallstairs", blockWall, tab);
		registerBlock(blockWallSlab, "industrialslab", blockWall, tab);
		registerShapedBlockSet(blockVent0, "industrialvent");
		registerShapedBlockSet(blockVent1, "vent.wall");
		registerShapedBlockSet(blockVent2, "vent.ceiling");
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
		registerBlock(blockAssembler, "assembler", true);
		registerBlock(blockTurret, "turret", true);
		registerBlock(blockWorkstation, "terminal", true);
		registerBlock(blockStasisMechanism, "stasismechanism", true);
		registerBlock(blockRepulsionGenerator, "generator", true);
		registerBlock(blockPowerline, "powerline", true);
		registerBlock(blockBlastdoor, "blastdoor", true);
		registerBlock(blockSupplies, "supplychuteblock", false);
		registerBlock(blockBlackGoo, "blackgoo", false);
		registerBlock(blockMist, "mist", false);
		registerBlock(blockCryostasisTube, "cryostasistube", true);
		registerShapedBlockSet(blockPlastic, "plasticblock");
		registerShapedBlockSet(blockPlasticTile, "plastictile");
		registerShapedBlockSet(blockPlasticTri, "plastictiletri");
		registerShapedBlockSet(blockPlasticCircle, "plastictilecircle");
		registerBlock(blockLightPanel, "lightpanel", true);
		registerBlock(mainframePanelShimmer, "mainframepanel.shimmer", true);
		registerBlock(mainframePanelFlicker, "mainframepanel.flicker", true);
		registerShapedBlockSet(blockEngineerShipFloor, "engineershipfloor");
		registerShapedBlockSet(blockEngineerShipBrick0, "engineershipbrick");
		registerShapedBlockSet(blockEngineerShipBrick1, "engineershipbrick1");
		registerShapedBlockSet(blockEngineerShipBrick2, "engineershipbrick2");
		registerShapedBlockSet(blockEngineerShipBrick3, "engineershipbrick3");
		registerShapedBlockSet(blockEngineerShipGravel, "engineershipgravel");
		registerShapedBlockSet(blockEngineerShipWall0, "engineershipwall");
		registerShapedBlockSet(blockEngineerShipWall1, "engineershipwall1");
		registerShapedBlockSet(blockEngineerShipWall2, "engineershipwall2");
		registerShapedBlockSet(blockEngineerShipWall3, "engineershipwall3");
		registerShapedBlockSet(blockEngineerShipWall4, "engineershipwall4");
		registerShapedBlockSet(blockEngineerShipRock0, "engineershiprock");
		registerShapedBlockSet(blockEngineerShipRock1, "engineershiprock1");
		registerShapedBlockSet(blockEngineerShipRock2, "engineershiprock2");
		registerShapedBlockSet(blockEngineerShipRock3, "engineershiprock3");
		registerShapedBlockSet(blockEngineerShipColumn1, "engineershipcolumn1");
		registerShapedBlockSet(blockEngineerShipColumn2, "engineershipcolumn2");
		registerShapedBlockSet(blockEngineerShipMaterial0, "engineershipmaterial0");
		registerShapedBlockSet(blockEngineerShipMaterial1, "engineershipmaterial1");
		registerShapedBlockSet(blockEngineerShipMaterial2, "engineershipmaterial2");
		registerBlock(blockAmpule, "engineership.ampule");
		registerBlock(blockLocker, "locker");
		registerBlock(blockGunLocker, "gunlocker");
	}
}
