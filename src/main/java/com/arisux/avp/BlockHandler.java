package com.arisux.avp;

import com.arisux.airi.lib.BlockTypes.BlockMaterial;
import com.arisux.airi.lib.ModUtil.IBHandler;
import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.block.BlockAmpule;
import com.arisux.avp.block.BlockAssembler;
import com.arisux.avp.block.BlockBlackGoo;
import com.arisux.avp.block.BlockBlastdoor;
import com.arisux.avp.block.BlockCryostasisTube;
import com.arisux.avp.block.BlockCustomSlab;
import com.arisux.avp.block.BlockCustomStairs;
import com.arisux.avp.block.BlockGenerator;
import com.arisux.avp.block.BlockGunLocker;
import com.arisux.avp.block.BlockHiveNode;
import com.arisux.avp.block.BlockHiveResin;
import com.arisux.avp.block.BlockLightPanel;
import com.arisux.avp.block.BlockLocker;
import com.arisux.avp.block.BlockMist;
import com.arisux.avp.block.BlockNegativeTransformer;
import com.arisux.avp.block.BlockP2RConverter;
import com.arisux.avp.block.BlockPortal;
import com.arisux.avp.block.BlockPowercell;
import com.arisux.avp.block.BlockPowerline;
import com.arisux.avp.block.BlockR2PConverter;
import com.arisux.avp.block.BlockSatelliteModem;
import com.arisux.avp.block.BlockSolarPanel;
import com.arisux.avp.block.BlockStalagmite;
import com.arisux.avp.block.BlockStasisMechanism;
import com.arisux.avp.block.BlockSupplyCrate;
import com.arisux.avp.block.BlockTempleSpawner;
import com.arisux.avp.block.BlockTransformer;
import com.arisux.avp.block.BlockTurret;
import com.arisux.avp.block.BlockUnidentifiedDirt;
import com.arisux.avp.block.BlockUnidentifiedLog;
import com.arisux.avp.block.BlockUnidentifiedTreeLeaves;
import com.arisux.avp.block.BlockUnidentifiedTreeSapling;
import com.arisux.avp.block.BlockUnidentifiedTreeTendon;
import com.arisux.avp.block.BlockWall;
import com.arisux.avp.block.BlockWorkstation;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockHandler extends IBHandler implements IInitializable
{
	public Block terrainHiveResin = (new BlockHiveResin(Material.wood)).setHardness(5F).setResistance(10.0F).setLightOpacity(255),
		blockOvamorph = (new BlockMaterial(Material.rock)),
		blockShipMetal1 = (new BlockMaterial(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockShipMetal2 = (new BlockMaterial(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockFacehuggerRelic = (new BlockMaterial(Material.rock).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockAlienRelic = (new BlockMaterial(Material.rock).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockShipDecor1 = (new BlockMaterial(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockShipDecor2 = (new BlockMaterial(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockShipDecor3 = (new BlockMaterial(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockShipDecor4 = (new BlockMaterial(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockShipDecor5 = (new BlockMaterial(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockShipDecor6 = (new BlockMaterial(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockRelicTile = (new BlockMaterial(Material.rock) {
			public void registerBlockIcons(IIconRegister register) 
			{
				this.blockIcon = register.registerIcon("avp:spawner");
			};
		}.setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockTempleBrick = (new BlockMaterial(Material.rock).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockTempleTile = (new BlockMaterial(Material.rock).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockTempleWall1 = (new BlockMaterial(Material.rock).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockTempleWall2 = (new BlockMaterial(Material.rock).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockWall = (new BlockMaterial(Material.iron)).setHardness(5F).setResistance(30.0F).setLightOpacity(255),
		blockCeiling = (new BlockMaterial(Material.iron)).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		blockCeilingFan = (new BlockMaterial(Material.iron)).setHardness(5F).setResistance(30.0F),
		blockCeiliingVent = (new BlockMaterial(Material.iron)).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		blockCeilingGrill = ((new BlockMaterial(Material.iron)).setHardness(5F).setResistance(30.0F)).setLightOpacity(4),
		blockSkulls = (new BlockMaterial(Material.rock)),
		blockFloorGrill = ((new BlockMaterial(Material.iron)).setHardness(5F).setResistance(30.0F)).setLightOpacity(4),
		blockIronBricks = (new BlockMaterial(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockVerticalMetal = (new BlockMaterial(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockColumnMetal1 = (new BlockMaterial(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockColumnMetal2 = (new BlockMaterial(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255)),
		blockPlasticCircle = (new BlockMaterial(Material.clay)).setHardness(10F).setResistance(30.0F).setLightOpacity(0),
		blockPlastic = (new BlockMaterial(Material.clay)).setHardness(10F).setResistance(30.0F).setLightOpacity(0),
		blockPlasticTri = (new BlockMaterial(Material.clay)).setHardness(10F).setResistance(30.0F).setLightOpacity(0),
		blockPlasticTile = (new BlockMaterial(Material.clay)).setHardness(10F).setResistance(30.0F).setLightOpacity(0),
		oreSilicon = (new BlockMaterial(Material.rock)).setHardness(2.2F).setResistance(1.4F).setLightOpacity(255),
		oreLithium = (new BlockMaterial(Material.iron)).setHardness(4.2F).setResistance(5.4F).setLightOpacity(255),
		oreCopper = (new BlockMaterial(Material.iron)).setHardness(3.2F).setResistance(2.6F).setLightOpacity(255),
		oreBauxite = (new BlockMaterial(Material.iron)).setHardness(3.2F).setResistance(2.6F).setLightOpacity(255),
		mainframePanelShimmer = (new BlockMaterial(Material.iron).setHardness(5F).setResistance(1F).setLightLevel(0.5F)),
		mainframePanelFlicker = (new BlockMaterial(Material.iron).setHardness(5F).setResistance(10F).setLightLevel(0.5F)),
		blockVent0 = (new BlockMaterial(Material.iron)).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		blockVent1 = (new BlockMaterial(Material.iron)).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		blockVent2 = (new BlockMaterial(Material.iron)).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		blockEngineerShipFloor = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipBrick0 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipBrick1 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipBrick2 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipBrick3 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipGravel = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipWall0 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipWall1 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipWall2 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipWall3 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipWall4 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipRock0 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipRock1 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipRock2 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipRock3 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipColumn1 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipColumn2 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipMaterial1 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockEngineerShipMaterial2 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockIndustrialGlass = (new BlockMaterial(Material.iron){
			@Override public boolean renderAsNormalBlock() 	{ return false; }
			@Override public boolean isOpaqueCube() 		{ return false; }
		}).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		terrainUniStone = (new BlockMaterial(Material.rock)).setHardness(1.3F).setResistance(2.0F).setLightOpacity(255),
		terrainUniSand = (new BlockMaterial(Material.sand)).setHardness(3.5F).setResistance(2.0F).setLightOpacity(255),
		terrainUniGravel = (new BlockMaterial(Material.sand)).setHardness(3.0F).setLightOpacity(255),
		blockSatelliteDish = (new BlockMaterial(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockEngineerShipMaterial0 = (new BlockMaterial(Material.iron).setHardness(10F).setResistance(30.0F).setLightOpacity(255)),
		blockSacrificialSpawner = (new BlockTempleSpawner(Material.rock, false)),
		blockSpawnerCreative = (new BlockTempleSpawner(Material.rock, true)),
		blockHiveNode = (new BlockHiveNode(Material.rock).setHardness(5F).setResistance(10.0F).setLightOpacity(255)),
		blockPortalVarda = (new BlockPortal(AliensVsPredator.settings().dimensionIdVarda()).setHardness(-1.0F).setLightLevel(2.0F)),
		blockPortalAcheron = (new BlockPortal(AliensVsPredator.settings().dimensionIdAcheron()).setHardness(-1.0F).setLightLevel(2.0F)),
		blockAssembler = (new BlockAssembler(Material.iron).setHardness(1.5F).setResistance(10.0F)),
		blockFloorGrillSlab = (new BlockCustomSlab(Material.iron){
			public void registerBlockIcons(IIconRegister register) 
			{
				this.blockIcon = register.registerIcon("avp:floorgrill");
			};
		}).setHardness(5F).setResistance(30.0F).setLightOpacity(4),
		blockFloorGrillStairs = (new BlockCustomStairs(blockFloorGrill)).setHardness(5F).setResistance(30.0F).setLightOpacity(4),
		blockCeilingGrillStairs = (new BlockCustomStairs(blockCeilingGrill)).setHardness(5F).setResistance(30.0F).setLightOpacity(4),
		blockCeilingGrillSlab = (new BlockCustomSlab(Material.iron){
			public void registerBlockIcons(IIconRegister register) 
			{
				this.blockIcon = register.registerIcon("avp:ceilinggrill");
			};
		}).setHardness(5F).setResistance(30.0F).setLightOpacity(4),
		blockIronBricksStairs = (new BlockCustomStairs(blockIronBricks)).setHardness(5F).setResistance(30.0F).setLightOpacity(255),
		blockWallStairs = (new BlockCustomStairs(blockWall)).setHardness(5F).setResistance(30.0F).setLightOpacity(255),
		blockWallSlab = (new BlockCustomSlab(Material.iron){
			public void registerBlockIcons(IIconRegister register) 
			{
				this.blockIcon = register.registerIcon("avp:wall_top");
			};
		}).setHardness(5F).setResistance(30.0F).setLightOpacity(255),
		blockIronBricksSlab = (new BlockCustomSlab(Material.iron){
			public void registerBlockIcons(IIconRegister register) 
			{
				this.blockIcon = register.registerIcon("avp:industrialbricks");
			};
		}).setHardness(5F).setResistance(30.0F).setLightOpacity(255),
		blockIndustrialGlassSlab = (new BlockCustomSlab(Material.iron){
			public void registerBlockIcons(IIconRegister register) 
			{
				this.blockIcon = register.registerIcon("avp:industrialglass");
			};
		}).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		blockIndustrialGlassStairs = (new BlockCustomStairs(blockIndustrialGlass)).setHardness(5F).setResistance(30.0F).setLightOpacity(0),
		terrainUniDirt = (new BlockUnidentifiedDirt()).setHardness(0.5F).setResistance(2.0F).setLightOpacity(255),
		terrainStalagmite = (new BlockStalagmite(Material.plants)).setHardness(0.0F).setLightOpacity(0),
		terrainUniTreeLog = (new BlockUnidentifiedLog()).setHardness(0.0F).setLightOpacity(0),
		terrainUniTreeTendon = (new BlockUnidentifiedTreeTendon()).setHardness(0.0F).setLightOpacity(0),
		terrainUniTreeLeaves = (new BlockUnidentifiedTreeLeaves()).setHardness(0.0F).setLightOpacity(0),
		terrainUniTreeSapling = (new BlockUnidentifiedTreeSapling()).setHardness(0.0F).setLightOpacity(0),
		blockTurret = (new BlockTurret(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockWorkstation = (new BlockWorkstation(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockStasisMechanism = (new BlockStasisMechanism(Material.iron)).setHardness(5.0F).setResistance(10.0F),
		blockRepulsionGenerator = (new BlockGenerator(Material.iron)).setHardness(5.0F).setResistance(10.0F),
		blockPowerline = (new BlockPowerline(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockBlastdoor = (new BlockBlastdoor(Material.iron)).setHardness(10F).setResistance(30.0F).setLightOpacity(0),
		blockCryostasisTube = (new BlockCryostasisTube(Material.iron)).setHardness(10F).setResistance(30.0F).setLightOpacity(4),
		blockLightPanel = (new BlockLightPanel(Material.iron, true)).setHardness(1.5F).setResistance(2.0F),
		blockSatelliteModem = (new BlockSatelliteModem(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockPowercell = (new BlockPowercell(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockR2PConvertor = (new BlockR2PConverter(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockP2RConvertor = (new BlockP2RConverter(Material.iron)).setHardness(3.2F).setResistance(2.6F),
		blockBlackGoo = (new BlockBlackGoo(AliensVsPredator.fluids().fluidBlackGoo, Material.water)),
		blockMist = (new BlockMist(AliensVsPredator.fluids().fluidMist, Material.air)),
		blockTransformer = (new BlockTransformer(Material.iron)).setHardness(5.0F).setResistance(10.0F),
		blockNegativeTransformer = (new BlockNegativeTransformer(Material.iron)).setHardness(5.0F).setResistance(10.0F),
		blockSupplies = (new BlockSupplyCrate(Material.iron)),
		blockSolarPanel = (new BlockSolarPanel(Material.iron)).setHardness(5.0F).setResistance(10.0F),
		blockLocker = (new BlockLocker(Material.iron).setHardness(1.5F).setResistance(10.0F)),
		blockGunLocker = (new BlockGunLocker(Material.iron).setHardness(1.5F).setResistance(10.0F)),
		blockAmpule = (new BlockAmpule().setHardness(5.0F).setResistance(10.0F)),
		blockWallW = (new BlockWall(Material.iron).setHardness(5F).setResistance(30.0F).setLightOpacity(255));

	public BlockHandler()
	{
		super(AliensVsPredator.instance());
	}

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		ShapedBlockUtil.registerBlock(this, terrainUniDirt, "unidirt");
		ShapedBlockUtil.registerBlock(this, terrainUniStone, "unistone");
		ShapedBlockUtil.registerBlock(this, terrainUniSand, "unisand");
		ShapedBlockUtil.registerBlock(this, terrainUniGravel, "unigravel");
		registerBlock(terrainUniTreeLog, "unitree.wood");
		registerBlock(terrainUniTreeTendon, "unitree.tendons");
		registerBlock(terrainUniTreeLeaves, "unitree.leaves");
		registerBlock(terrainUniTreeSapling, "unitree.sapling");
		registerBlock(terrainStalagmite, "stalagmite");
		registerBlock(oreSilicon, "oresilicon");
		registerBlock(oreCopper, "orecopper");
		registerBlock(oreLithium, "orelithium");
		registerBlock(oreBauxite, "orebauxite");
		registerBlock(blockSolarPanel, "solarpanel");
		registerBlock(blockSatelliteModem, "satellitemodem");
		registerBlock(blockSatelliteDish, "satellitedish");
		registerBlock(blockPowercell, "powercell");
		ShapedBlockUtil.registerBlock(this, blockCeiling, "ceilingpanel");
		ShapedBlockUtil.registerBlock(this, blockCeilingFan, "ceilingfan");
		ShapedBlockUtil.registerBlock(this, blockCeiliingVent, "ceilingvent");
		registerBlock(blockTransformer, "transformer");
		registerBlock(blockNegativeTransformer, "transformernegative");
		ShapedBlockUtil.registerBlock(this, blockCeilingGrill, "ceilinggrill");
		registerBlock(blockCeilingGrillStairs, "ceilinggrillstairs", AliensVsPredator.instance().tabBlocks());
		registerBlock(blockCeilingGrillSlab, "ceilinggrillslab", AliensVsPredator.instance().tabBlocks());
		ShapedBlockUtil.registerBlock(this, blockFloorGrill, "floorgrill");
		registerBlock(blockFloorGrillStairs, "floorgrillstairs", AliensVsPredator.instance().tabBlocks());
		registerBlock(blockFloorGrillSlab, "floorgrillslab", AliensVsPredator.instance().tabBlocks());
		ShapedBlockUtil.registerBlock(this, blockWall, "industrialwall");
		ShapedBlockUtil.registerBlock(this, blockWallW, "industrialwall2");
		registerBlock(blockR2PConvertor, "r2pconverter");
		registerBlock(blockP2RConvertor, "p2rconverter");
		registerBlock(blockWallStairs, "industrialwallstairs", AliensVsPredator.instance().tabBlocks());
		registerBlock(blockWallSlab, "industrialslab", AliensVsPredator.instance().tabBlocks());
		ShapedBlockUtil.registerBlock(this, blockVent0, "industrialvent");
		ShapedBlockUtil.registerBlock(this, blockVent1, "vent.wall");
		ShapedBlockUtil.registerBlock(this, blockVent2, "vent.ceiling");
		ShapedBlockUtil.registerBlock(this, blockIronBricks, "industrialbricks");
		registerBlock(blockIronBricksStairs, "industrialbrickstairs", AliensVsPredator.instance().tabBlocks());
		registerBlock(blockIronBricksSlab, "industrialbrickslab", AliensVsPredator.instance().tabBlocks());
		ShapedBlockUtil.registerBlock(this, blockIndustrialGlass, "industrialglass");
		registerBlock(blockIndustrialGlassStairs, "industrialglassstairs", AliensVsPredator.instance().tabBlocks());
		registerBlock(blockIndustrialGlassSlab, "industrialglassslab", AliensVsPredator.instance().tabBlocks());
		ShapedBlockUtil.registerBlock(this, blockVerticalMetal, "metalpanel1");
		ShapedBlockUtil.registerBlock(this, blockColumnMetal1, "metalpanel2");
		ShapedBlockUtil.registerBlock(this, blockColumnMetal2, "metalpanel3");
		ShapedBlockUtil.registerBlock(this, terrainHiveResin, "hiveresin");
		registerBlock(blockHiveNode, "hivenode", null);
		ShapedBlockUtil.registerBlock(this, blockShipMetal1, "shippanel");
		ShapedBlockUtil.registerBlock(this, blockShipMetal2, "shippannelyautja");
		ShapedBlockUtil.registerBlock(this, blockOvamorph, "tileovamorphdesign");
		ShapedBlockUtil.registerBlock(this, blockFacehuggerRelic, "tilefacehuggerdesign");
		ShapedBlockUtil.registerBlock(this, blockAlienRelic, "tilealiendesign");
		ShapedBlockUtil.registerBlock(this, blockShipDecor1, "shipwallbase");
		ShapedBlockUtil.registerBlock(this, blockShipDecor2, "shipsupportpillar");
		ShapedBlockUtil.registerBlock(this, blockShipDecor3, "shipdecor1");
		ShapedBlockUtil.registerBlock(this, blockShipDecor5, "shipdecor2");
		ShapedBlockUtil.registerBlock(this, blockShipDecor6, "shipdecor3");
		ShapedBlockUtil.registerBlock(this, blockShipDecor4, "shipbrick");
		registerBlock(blockSacrificialSpawner, "spawner");
		registerBlock(blockSpawnerCreative, "spawnerc");
		ShapedBlockUtil.registerBlock(this, blockRelicTile, "templebricksingle", 0, blockSacrificialSpawner);
		ShapedBlockUtil.registerBlock(this, blockTempleBrick, "templebrick");
		ShapedBlockUtil.registerBlock(this, blockTempleTile, "templetile");
		ShapedBlockUtil.registerBlock(this, blockTempleWall1, "templewallbase");
		ShapedBlockUtil.registerBlock(this, blockTempleWall2, "templefloor");
		ShapedBlockUtil.registerBlock(this, blockSkulls, "skulls");
		registerBlock(blockPortalVarda, "portal.varda");
		registerBlock(blockPortalAcheron, "portal.acheron");
		registerBlock(blockAssembler, "assembler");
		registerBlock(blockTurret, "turret");
		registerBlock(blockWorkstation, "terminal");
		registerBlock(blockStasisMechanism, "stasismechanism");
		registerBlock(blockRepulsionGenerator, "generator");
		registerBlock(blockPowerline, "powerline");
		registerBlock(blockBlastdoor, "blastdoor");
		registerBlock(blockSupplies, "supplychuteblock", null);
		registerBlock(blockBlackGoo, "blackgoo");
		registerBlock(blockMist, "mist");
		registerBlock(blockCryostasisTube, "cryostasistube");
		ShapedBlockUtil.registerBlock(this, blockPlastic, "plasticblock");
		ShapedBlockUtil.registerBlock(this, blockPlasticTile, "plastictile");
		ShapedBlockUtil.registerBlock(this, blockPlasticTri, "plastictiletri");
		ShapedBlockUtil.registerBlock(this, blockPlasticCircle, "plastictilecircle");
		registerBlock(blockLightPanel, "lightpanel");
		registerBlock(mainframePanelShimmer, "mainframepanel.shimmer");
		registerBlock(mainframePanelFlicker, "mainframepanel.flicker");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipFloor, "engineershipfloor");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipBrick0, "engineershipbrick");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipBrick1, "engineershipbrick1");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipBrick2, "engineershipbrick2");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipBrick3, "engineershipbrick3");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipGravel, "engineershipgravel");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipWall0, "engineershipwall");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipWall1, "engineershipwall1");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipWall2, "engineershipwall2");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipWall3, "engineershipwall3");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipWall4, "engineershipwall4");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipRock0, "engineershiprock");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipRock1, "engineershiprock1");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipRock2, "engineershiprock2");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipRock3, "engineershiprock3");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipColumn1, "engineershipcolumn1");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipColumn2, "engineershipcolumn2");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipMaterial0, "engineershipmaterial0");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipMaterial1, "engineershipmaterial1");
		ShapedBlockUtil.registerBlock(this, blockEngineerShipMaterial2, "engineershipmaterial2");
		registerBlock(blockAmpule, "engineership.ampule");
		registerBlock(blockLocker, "locker");
		registerBlock(blockGunLocker, "gunlocker");
	}
}
