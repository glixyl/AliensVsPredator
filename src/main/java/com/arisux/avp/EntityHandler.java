package com.arisux.avp;

import java.util.HashMap;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.dimension.BiomeLVBase;
import com.arisux.avp.entities.EntityAPC;
import com.arisux.avp.entities.EntityAcidPool;
import com.arisux.avp.entities.EntityAcidProjectile;
import com.arisux.avp.entities.EntityFlame;
import com.arisux.avp.entities.EntityGrenade;
import com.arisux.avp.entities.EntityLaserMine;
import com.arisux.avp.entities.EntityMechanism;
import com.arisux.avp.entities.EntityNuke;
import com.arisux.avp.entities.EntityPlasma;
import com.arisux.avp.entities.EntityShuriken;
import com.arisux.avp.entities.EntitySmartDisc;
import com.arisux.avp.entities.EntitySpear;
import com.arisux.avp.entities.EntityTurret;
import com.arisux.avp.entities.mob.EntityAqua;
import com.arisux.avp.entities.mob.EntityChestburster;
import com.arisux.avp.entities.mob.EntityCombatSynthetic;
import com.arisux.avp.entities.mob.EntityCrusher;
import com.arisux.avp.entities.mob.EntityDrone;
import com.arisux.avp.entities.mob.EntityEngineer;
import com.arisux.avp.entities.mob.EntityFacehugger;
import com.arisux.avp.entities.mob.EntityHammerpede;
import com.arisux.avp.entities.mob.EntityMarine;
import com.arisux.avp.entities.mob.EntityOvamorph;
import com.arisux.avp.entities.mob.EntityPraetorian;
import com.arisux.avp.entities.mob.EntityPredalien;
import com.arisux.avp.entities.mob.EntityProtomorph;
import com.arisux.avp.entities.mob.EntityQueen;
import com.arisux.avp.entities.mob.EntityRoyalFacehugger;
import com.arisux.avp.entities.mob.EntitySpaceJockey;
import com.arisux.avp.entities.mob.EntitySpitter;
import com.arisux.avp.entities.mob.EntityTrilobite;
import com.arisux.avp.entities.mob.EntityWarrior;
import com.arisux.avp.entities.mob.EntityYautja;
import com.arisux.avp.entities.mob.EntityYautjaBerserker;
import com.arisux.avp.entities.tile.TileEntityAmpule;
import com.arisux.avp.entities.tile.TileEntityAssembler;
import com.arisux.avp.entities.tile.TileEntityBlastdoor;
import com.arisux.avp.entities.tile.TileEntityCryostasisTube;
import com.arisux.avp.entities.tile.TileEntityGunLocker;
import com.arisux.avp.entities.tile.TileEntityHiveNode;
import com.arisux.avp.entities.tile.TileEntityHiveResin;
import com.arisux.avp.entities.tile.TileEntityLightPanel;
import com.arisux.avp.entities.tile.TileEntityLocker;
import com.arisux.avp.entities.tile.TileEntityNegativeTransformer;
import com.arisux.avp.entities.tile.TileEntityP2RConverter;
import com.arisux.avp.entities.tile.TileEntityPowercell;
import com.arisux.avp.entities.tile.TileEntityPowerline;
import com.arisux.avp.entities.tile.TileEntityR2PConverter;
import com.arisux.avp.entities.tile.TileEntityRepulsionGenerator;
import com.arisux.avp.entities.tile.TileEntitySatelliteModem;
import com.arisux.avp.entities.tile.TileEntitySolarPanel;
import com.arisux.avp.entities.tile.TileEntityStasisMechanism;
import com.arisux.avp.entities.tile.TileEntityTransformer;
import com.arisux.avp.entities.tile.TileEntityTurret;
import com.arisux.avp.entities.tile.TileEntityWorkstation;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public class EntityHandler implements IInitializable
{
	public static final EntityHandler instance = new EntityHandler();

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		this.registerTileEntities();
		this.registerEntities();
		this.registerLivingEntities();
		this.registerSpawns();
	}

	public void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityTurret.class, "tileEntityTurret");
		GameRegistry.registerTileEntity(TileEntityWorkstation.class, "tileEntityWorkstation");
		GameRegistry.registerTileEntity(TileEntityHiveResin.class, "tileEntityBlockHive");
		GameRegistry.registerTileEntity(TileEntityHiveNode.class, "tileEntityBlockHiveNode");
		GameRegistry.registerTileEntity(TileEntityAssembler.class, "tileEntityAssembler");
		GameRegistry.registerTileEntity(TileEntityStasisMechanism.class, "tileStasisMechanism");
		GameRegistry.registerTileEntity(TileEntityRepulsionGenerator.class, "tileEntityGenerator");
		GameRegistry.registerTileEntity(TileEntityPowerline.class, "tileEntityPowerline");
		GameRegistry.registerTileEntity(TileEntityBlastdoor.class, "tileEntityBlastdoor");
		GameRegistry.registerTileEntity(TileEntityCryostasisTube.class, "tileEntityCryostasisTube");
		GameRegistry.registerTileEntity(TileEntityLightPanel.class, "tileEntityLightPanel");
		GameRegistry.registerTileEntity(TileEntitySatelliteModem.class, "tileEntitySatelliteModem");
		GameRegistry.registerTileEntity(TileEntitySolarPanel.class, "tileEntitySolarPanel");
		GameRegistry.registerTileEntity(TileEntityTransformer.class, "tileEntityTransformer");
		GameRegistry.registerTileEntity(TileEntityNegativeTransformer.class, "tileEntityNegativeTransformer");
		GameRegistry.registerTileEntity(TileEntityR2PConverter.class, "tileEntityR2PConverter");
		GameRegistry.registerTileEntity(TileEntityP2RConverter.class, "tileEntityP2RConverter");
		GameRegistry.registerTileEntity(TileEntityPowercell.class, "tileEntityPowercell");
		GameRegistry.registerTileEntity(TileEntityAmpule.class, "tileEntityAmpule");
		GameRegistry.registerTileEntity(TileEntityLocker.class, "tileEntityLocker");
		GameRegistry.registerTileEntity(TileEntityGunLocker.class, "tileEntityGunLocker");
	}

	private void registerLivingEntities()
	{
		HashMap<String, Integer> entityIDs = AliensVsPredator.settings().entityList;

		EntityRegistry.registerGlobalEntityID(EntityDrone.class, "Drone", entityIDs.get("DRONE"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityWarrior.class, "Warrior", entityIDs.get("WARRIOR"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntitySpitter.class, "Spitter", entityIDs.get("SPITTER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityCrusher.class, "Crusher", entityIDs.get("CRUSHER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityPraetorian.class, "Praetorian", entityIDs.get("PRAETORIAN"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityMarine.class, "Marine", entityIDs.get("MARINE"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityYautja.class, "Yautja", entityIDs.get("YAUTJA"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityQueen.class, "Queen", entityIDs.get("QUEEN"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityFacehugger.class, "Facehugger", entityIDs.get("FACEHUGGER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityChestburster.class, "Chestbuster", entityIDs.get("CHESTBUSTER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityOvamorph.class, "Ovamorph", entityIDs.get("OVAMORPH"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityRoyalFacehugger.class, "RoyalFacehugger", entityIDs.get("ROYAL_FACEHUGGER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityAqua.class, "AquaAlien", entityIDs.get("AQUA"));
		EntityRegistry.registerGlobalEntityID(EntityPredalien.class, "Predalien", entityIDs.get("PREDALIEN"));
		EntityRegistry.registerGlobalEntityID(EntityCombatSynthetic.class, "CombatSynthetic", entityIDs.get("COMBAT_SYNTHETIC"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityProtomorph.class, "Protomorph", entityIDs.get("PROTOMORPH"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityHammerpede.class, "Hammerpede", entityIDs.get("HAMMERPEDE"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityTrilobite.class, "Trilobite", entityIDs.get("TRILOBITE"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntitySpaceJockey.class, "SpaceJockey", entityIDs.get("SPACEJOCKEY"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityEngineer.class, "Engineer", entityIDs.get("ENGINEER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityYautjaBerserker.class, "YautjaBerserker", entityIDs.get("YAUTJA_BERSERKER"), 0x333333, 0xFF0000);
	}

	private void registerEntities()
	{
		HashMap<String, Integer> entityIDs = AliensVsPredator.settings().entityList;
		
		EntityRegistry.registerModEntity(EntitySpear.class, "Spear", entityIDs.get("CELTIC_SPEAR"), AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityLaserMine.class, "ProximityMine", entityIDs.get("PROXIMITY_MINE"), AliensVsPredator.instance(), 250, 5, true);
		EntityRegistry.registerModEntity(EntityPlasma.class, "Plasma", entityIDs.get("PLASMA"), AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", entityIDs.get("GRENADE"), AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityFlame.class, "Flamethrower", entityIDs.get("FLAME"), AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityAcidPool.class, "AcidPool", entityIDs.get("FXACID"), AliensVsPredator.instance(), 250, 5, true);
		EntityRegistry.registerModEntity(EntityAcidProjectile.class, "AcidSpit", entityIDs.get("AIACID"), AliensVsPredator.instance(), 250, 5, true);
		EntityRegistry.registerModEntity(EntitySmartDisc.class, "EntityDisc", entityIDs.get("DISC"), AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityShuriken.class, "EntityShuriken", entityIDs.get("SHURIKEN"), AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityTurret.class, "EntityTurret", entityIDs.get("TURRETENTITY"), AliensVsPredator.instance(), 250, 5, true);
		EntityRegistry.registerModEntity(EntityNuke.class, "Nuke", entityIDs.get("WRISTBRACERNUKE"), AliensVsPredator.instance(), 250, 5, true);
		EntityRegistry.registerModEntity(EntityAPC.class, "APC", entityIDs.get("APC"), AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityMechanism.class, "MECHANISM", entityIDs.get("MECHANISM"), AliensVsPredator.instance(), 250, 15, true);
	}

	private void registerSpawns()
	{
		BiomeGenBase[] xenomorphBiomes = new BiomeGenBase[] 
		{
				BiomeGenBase.birchForest,
				BiomeGenBase.birchForestHills,
				BiomeGenBase.coldBeach,
				BiomeGenBase.coldTaiga,
				BiomeGenBase.coldTaigaHills,
				BiomeGenBase.desertHills,
				BiomeGenBase.extremeHills,
				BiomeGenBase.extremeHillsEdge,
				BiomeGenBase.extremeHillsPlus,
				BiomeGenBase.forest,
				BiomeGenBase.forestHills,
				BiomeGenBase.iceMountains,
				BiomeGenBase.icePlains,
				BiomeGenBase.jungle,
				BiomeGenBase.jungleEdge,
				BiomeGenBase.jungleHills,
				BiomeGenBase.plains,
				BiomeGenBase.roofedForest,
				BiomeGenBase.swampland,
				BiomeGenBase.taiga,
				BiomeGenBase.taigaHills,
				BiomeLVBase.acheron
		};
		
		BiomeGenBase[] predatorBiomes = new BiomeGenBase[] 
		{
				BiomeGenBase.birchForest,
				BiomeGenBase.birchForestHills,
				BiomeGenBase.desert,
				BiomeGenBase.desertHills,
				BiomeGenBase.extremeHills,
				BiomeGenBase.extremeHillsEdge,
				BiomeGenBase.extremeHillsPlus,
				BiomeGenBase.forest,
				BiomeGenBase.forestHills,
				BiomeGenBase.frozenOcean,
				BiomeGenBase.frozenRiver,
				BiomeGenBase.icePlains,
				BiomeGenBase.jungle,
				BiomeGenBase.jungleEdge,
				BiomeGenBase.jungleHills,
				BiomeGenBase.taiga,
				BiomeGenBase.taigaHills,
				BiomeLVBase.varda
		};
		
		BiomeGenBase[] engineerBiomes = new BiomeGenBase[] 
		{
				BiomeGenBase.iceMountains, // need to get the NBT flash drive as a 5% engineer drop OnDeath to craft the assembler
				BiomeGenBase.coldTaigaHills, // found at snowy elevations according to the NBT item tip
				BiomeGenBase.coldTaiga,
				BiomeGenBase.extremeHills,
				BiomeLVBase.varda,
				BiomeLVBase.acheron
		};
		
		BiomeGenBase[] aquaXenomorphBiomes = new BiomeGenBase[] 
		{
				BiomeGenBase.river,
				BiomeGenBase.beach,
				BiomeGenBase.stoneBeach
		};
		
		EntityRegistry.addSpawn(EntityAqua.class, 5, 1, 2, EnumCreatureType.monster, aquaXenomorphBiomes);
		EntityRegistry.addSpawn(EntityDrone.class, 50, 1, 3, EnumCreatureType.monster, xenomorphBiomes);
		EntityRegistry.addSpawn(EntityWarrior.class, 20, 1, 3, EnumCreatureType.monster, xenomorphBiomes);
		EntityRegistry.addSpawn(EntityPraetorian.class, 5, 1, 2, EnumCreatureType.monster, xenomorphBiomes);
		EntityRegistry.addSpawn(EntityFacehugger.class, 5, 1, 2, EnumCreatureType.monster, xenomorphBiomes);
		EntityRegistry.addSpawn(EntityChestburster.class, 5, 1, 3, EnumCreatureType.monster, xenomorphBiomes);
		EntityRegistry.addSpawn(EntityYautja.class, 1, 1, 1, EnumCreatureType.monster, predatorBiomes);
		EntityRegistry.addSpawn(EntityYautjaBerserker.class, 1, 0, 1, EnumCreatureType.monster, predatorBiomes);
		EntityRegistry.addSpawn(EntityMarine.class, 2, 1, 1, EnumCreatureType.creature, new BiomeGenBase[] { 
			BiomeGenBase.swampland, 
			BiomeGenBase.forest, 
			BiomeGenBase.forestHills, 
			BiomeGenBase.taiga, 
			BiomeGenBase.taigaHills, 
			BiomeGenBase.plains
		});
		EntityRegistry.addSpawn(EntityProtomorph.class, 30, 1, 2, EnumCreatureType.monster, new BiomeGenBase[] { 
			BiomeLVBase.varda
		});
		EntityRegistry.addSpawn(EntityHammerpede.class, 30, 1, 4, EnumCreatureType.monster, new BiomeGenBase[] { 
			BiomeLVBase.varda
		});
		EntityRegistry.addSpawn(EntityTrilobite.class, 30, 1, 2, EnumCreatureType.monster, new BiomeGenBase[] { 
			BiomeLVBase.varda
		});
		EntityRegistry.addSpawn(EntityEngineer.class, 1, 1, 1, EnumCreatureType.monster, engineerBiomes);
		EntityRegistry.addSpawn(EntitySpaceJockey.class, 1, 0, 1, EnumCreatureType.monster, engineerBiomes);
	}
}
