package com.arisux.avp;

import java.util.HashMap;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.dimension.BiomeLVBase;
import com.arisux.avp.entities.*;
import com.arisux.avp.entities.mob.*;
import com.arisux.avp.entities.tile.*;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class EntityHandler implements IInitializable
{
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
		GameRegistry.registerTileEntity(TileEntityWorklight.class, "tileEntityWorklight");
		GameRegistry.registerTileEntity(TileEntityServer.class, "tileEntityServer");
		GameRegistry.registerTileEntity(TileEntityCryostasisTube.class, "tileEntityCryostasisTube");
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
	}

	private void registerSpawns()
	{
		EntityRegistry.addSpawn(EntityMarine.class, 16, 1, 2, EnumCreatureType.creature, new BiomeGenBase[] { 
			BiomeGenBase.swampland, 
			BiomeGenBase.forest, 
			BiomeGenBase.forestHills, 
			BiomeGenBase.taiga, 
			BiomeGenBase.taigaHills, 
			BiomeGenBase.plains 
		});
		EntityRegistry.addSpawn(EntityYautja.class, 20, 1, 1, EnumCreatureType.creature, new BiomeGenBase[] { 
			BiomeGenBase.jungle, 
			BiomeGenBase.jungleHills, 
			BiomeGenBase.desertHills 
		});
		EntityRegistry.addSpawn(EntityDrone.class, 20, 1, 2, EnumCreatureType.creature, new BiomeGenBase[] { 
			BiomeGenBase.plains, 
			BiomeGenBase.swampland, 
			BiomeGenBase.forest, 
			BiomeGenBase.forestHills, 
			BiomeGenBase.taiga, 
			BiomeGenBase.taigaHills, 
			BiomeGenBase.jungle, 
			BiomeGenBase.jungleHills 
		});
		EntityRegistry.addSpawn(EntityWarrior.class, 25, 1, 2, EnumCreatureType.creature, new BiomeGenBase[] { 
			BiomeGenBase.plains, 
			BiomeGenBase.swampland, 
			BiomeGenBase.forest, 
			BiomeGenBase.forestHills, 
			BiomeGenBase.taiga, 
			BiomeGenBase.taigaHills, 
			BiomeGenBase.jungle, 
			BiomeGenBase.jungleHills 
		});
		EntityRegistry.addSpawn(EntityPraetorian.class, 3, 1, 2, EnumCreatureType.creature, new BiomeGenBase[] { 
			BiomeGenBase.plains, 
			BiomeGenBase.swampland, 
			BiomeGenBase.forest, 
			BiomeGenBase.forestHills, 
			BiomeGenBase.taiga, 
			BiomeGenBase.taigaHills, 
			BiomeGenBase.jungle, 
			BiomeGenBase.jungleHills 
		});
		EntityRegistry.addSpawn(EntitySpitter.class, 5, 1, 2, EnumCreatureType.creature, new BiomeGenBase[] { 
			BiomeGenBase.plains, 
			BiomeGenBase.swampland, 
			BiomeGenBase.forest, 
			BiomeGenBase.forestHills, 
			BiomeGenBase.taiga, 
			BiomeGenBase.taigaHills, 
			BiomeGenBase.jungle, 
			BiomeGenBase.jungleHills 
		});
		EntityRegistry.addSpawn(EntityCrusher.class, 6, 1, 2, EnumCreatureType.creature, new BiomeGenBase[] { 
			BiomeGenBase.plains, 
			BiomeGenBase.swampland, 
			BiomeGenBase.forest, 
			BiomeGenBase.forestHills, 
			BiomeGenBase.taiga, 
			BiomeGenBase.taigaHills, 
			BiomeGenBase.jungle, 
			BiomeGenBase.jungleHills 
		});
		EntityRegistry.addSpawn(EntityProtomorph.class, 6, 1, 2, EnumCreatureType.monster, new BiomeGenBase[] { 
			BiomeLVBase.varda
		});
	}
}
