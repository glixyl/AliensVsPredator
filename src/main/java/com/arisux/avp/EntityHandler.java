package com.arisux.avp;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

import com.arisux.airi.lib.interfaces.IInitializable;
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
		this.registerInstanceEntities();
		this.registerEntities();
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
		GameRegistry.registerTileEntity(TileEntityGenerator.class, "tileEntityGenerator");
		GameRegistry.registerTileEntity(TileEntityPowerline.class, "tileEntityPowerline");
		GameRegistry.registerTileEntity(TileEntityBlastdoor.class, "tileEntityBlastdoor");
		GameRegistry.registerTileEntity(TileEntityWorklight.class, "tileEntityWorklight");
		GameRegistry.registerTileEntity(TileEntityNetworkCable.class, "tileEntityNetworkCable");
		GameRegistry.registerTileEntity(TileEntityNetworkLight.class, "tileEntityNetworkLight");
		GameRegistry.registerTileEntity(TileEntityServer.class, "tileEntityServer");
	}

	private void registerEntities()
	{
		EntityRegistry.registerGlobalEntityID(EntityDrone.class, "Drone", AliensVsPredator.instance().settings.entityList.get("DRONE"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityWarrior.class, "Warrior", AliensVsPredator.instance().settings.entityList.get("WARRIOR"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntitySpitter.class, "Spitter", AliensVsPredator.instance().settings.entityList.get("SPITTER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityCrusher.class, "Crusher", AliensVsPredator.instance().settings.entityList.get("CRUSHER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityPraetorian.class, "Praetorian", AliensVsPredator.instance().settings.entityList.get("PRAETORIAN"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityMarine.class, "Marine", AliensVsPredator.instance().settings.entityList.get("MARINE"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityYautja.class, "Yautja", AliensVsPredator.instance().settings.entityList.get("YAUTJA"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityQueen.class, "Queen", AliensVsPredator.instance().settings.entityList.get("QUEEN"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityFacehugger.class, "Facehugger", AliensVsPredator.instance().settings.entityList.get("FACEHUGGER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityChestburster.class, "Chestbuster", AliensVsPredator.instance().settings.entityList.get("CHESTBUSTER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityOvamorph.class, "Ovamorph", AliensVsPredator.instance().settings.entityList.get("OVAMORPH"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityRoyalFacehugger.class, "RoyalFacehugger", AliensVsPredator.instance().settings.entityList.get("ROYAL_FACEHUGGER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityAqua.class, "AquaticAlien", AliensVsPredator.instance().settings.entityList.get("AQUA"));
		EntityRegistry.registerGlobalEntityID(EntityPredalien.class, "Predalien", AliensVsPredator.instance().settings.entityList.get("PREDALIEN"));
		EntityRegistry.registerGlobalEntityID(EntityCombatSynthetic.class, "CombatSynthetic", AliensVsPredator.instance().settings.entityList.get("COMBAT_SYNTHETIC"), 0x333333, 0xFF0000);
	}

	private void registerInstanceEntities()
	{
		EntityRegistry.registerModEntity(EntitySpear.class, "Spear", AliensVsPredator.instance().settings.entityList.get("CELTIC_SPEAR"), AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityProximityMine.class, "ProximityMine", AliensVsPredator.instance().settings.entityList.get("PROXIMITY_MINE"), AliensVsPredator.instance(), 250, 5, true);
		EntityRegistry.registerModEntity(EntityPlasma.class, "Plasma", AliensVsPredator.instance().settings.entityList.get("PLASMA"), AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", AliensVsPredator.instance().settings.entityList.get("GRENADE"), AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityFlame.class, "Flamethrower", AliensVsPredator.instance().settings.entityList.get("FLAME"), AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityAcidPool.class, "AcidPool", AliensVsPredator.instance().settings.entityList.get("FXACID"), AliensVsPredator.instance(), 250, 5, true);
		EntityRegistry.registerModEntity(EntityAcidProjectile.class, "AcidSpit", AliensVsPredator.instance().settings.entityList.get("AIACID"), AliensVsPredator.instance(), 250, 5, true);
		EntityRegistry.registerModEntity(EntitySmartDisc.class, "EntityDisc", AliensVsPredator.instance().settings.entityList.get("DISC"), AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityShuriken.class, "EntityShuriken", AliensVsPredator.instance().settings.entityList.get("SHURIKEN"), AliensVsPredator.instance(), 250, 15, true);
		EntityRegistry.registerModEntity(EntityTurret.class, "EntityTurret", AliensVsPredator.instance().settings.entityList.get("TURRETENTITY"), AliensVsPredator.instance(), 250, 5, true);
	}

	private void registerSpawns()
	{
		EntityRegistry.addSpawn(EntityMarine.class, 16, 1, 2, EnumCreatureType.creature, new BiomeGenBase[] { BiomeGenBase.swampland, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.plains });
		EntityRegistry.addSpawn(EntityYautja.class, 20, 1, 1, EnumCreatureType.creature, new BiomeGenBase[] { BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.desertHills });
		EntityRegistry.addSpawn(EntityDrone.class, 20, 1, 2, EnumCreatureType.creature, new BiomeGenBase[] { BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills });
		EntityRegistry.addSpawn(EntityWarrior.class, 25, 1, 2, EnumCreatureType.creature, new BiomeGenBase[] { BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills });
		EntityRegistry.addSpawn(EntityPraetorian.class, 3, 1, 2, EnumCreatureType.creature, new BiomeGenBase[] { BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills });
		EntityRegistry.addSpawn(EntitySpitter.class, 5, 1, 2, EnumCreatureType.creature, new BiomeGenBase[] { BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills });
		EntityRegistry.addSpawn(EntityCrusher.class, 6, 1, 2, EnumCreatureType.creature, new BiomeGenBase[] { BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills });
		EntityRegistry.addSpawn(EntityFacehugger.class, 20, 2, 8, EnumCreatureType.creature, new BiomeGenBase[] { BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.plains, BiomeGenBase.jungle, BiomeGenBase.jungleHills });
		EntityRegistry.addSpawn(EntityChestburster.class, 6, 1, 2, EnumCreatureType.creature, new BiomeGenBase[] { BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.plains, BiomeGenBase.jungle, BiomeGenBase.jungleHills });
	}
}
