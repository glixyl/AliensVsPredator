package com.arisux.avp;

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
import com.arisux.avp.entities.mob.EntityDeaconShark;
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
import com.arisux.avp.entities.mob.EntityRunnerDrone;
import com.arisux.avp.entities.mob.EntityRunnerWarrior;
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
import com.arisux.avp.entities.tile.TileEntityMedpod;
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
		GameRegistry.registerTileEntity(TileEntityMedpod.class, "tileEntityMedpod");
	}

	private void registerLivingEntities()
	{
		EntityRegistry.registerGlobalEntityID(EntityRunnerDrone.class, "RunnerDrone", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityRunnerWarrior.class, "RunnerWarrior", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityDrone.class, "Drone", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityWarrior.class, "Warrior", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntitySpitter.class, "Spitter", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityCrusher.class, "Crusher", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityPraetorian.class, "Praetorian", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityMarine.class, "Marine", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityYautja.class, "Yautja", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityQueen.class, "Queen", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityFacehugger.class, "Facehugger", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityChestburster.class, "Chestbuster", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityOvamorph.class, "Ovamorph", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityRoyalFacehugger.class, "RoyalFacehugger", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityAqua.class, "AquaAlien", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityPredalien.class, "Predalien", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityCombatSynthetic.class, "CombatSynthetic", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityProtomorph.class, "Protomorph", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityHammerpede.class, "Hammerpede", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityTrilobite.class, "Trilobite", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntitySpaceJockey.class, "SpaceJockey", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityEngineer.class, "Engineer", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityYautjaBerserker.class, "YautjaBerserker", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
		EntityRegistry.registerGlobalEntityID(EntityDeaconShark.class, "DeaconShark", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00EEFF);
	}

	private void registerEntities()
	{
		int entityId = 0;
		
		EntityRegistry.registerModEntity(EntitySpear.class, "Spear", entityId++, AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityLaserMine.class, "ProximityMine", entityId++, AliensVsPredator.instance(), 250, 5, true);
		EntityRegistry.registerModEntity(EntityPlasma.class, "Plasma", entityId++, AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", entityId++, AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityFlame.class, "Flamethrower", entityId++, AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityAcidPool.class, "AcidPool", entityId++, AliensVsPredator.instance(), 250, 5, true);
		EntityRegistry.registerModEntity(EntityAcidProjectile.class, "AcidSpit", entityId++, AliensVsPredator.instance(), 250, 5, true);
		EntityRegistry.registerModEntity(EntitySmartDisc.class, "EntityDisc", entityId++, AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityShuriken.class, "EntityShuriken", entityId++, AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityTurret.class, "EntityTurret", entityId++, AliensVsPredator.instance(), 250, 5, true);
		EntityRegistry.registerModEntity(EntityNuke.class, "Nuke", entityId++, AliensVsPredator.instance(), 250, 5, true);
		EntityRegistry.registerModEntity(EntityAPC.class, "APC", entityId++, AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityMechanism.class, "MECHANISM", entityId++, AliensVsPredator.instance(), 250, 15, true);
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
		EntityRegistry.addSpawn(EntityDeaconShark.class, 1, 0, 1, EnumCreatureType.waterCreature, new BiomeGenBase[] { BiomeLVBase.varda });
	}
}
