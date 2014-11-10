package com.arisux.avp;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.entities.*;
import com.arisux.avp.entities.mob.*;
import com.arisux.avp.entities.tile.*;

import cpw.mods.fml.common.registry.*;

public class EntityHandler implements IInitializable
{
	public void initialize()
	{
		this.registerTileEntities();
		this.registerInstanceEntities();
		this.registerEntities();
		this.registerSpawns();
		this.registerDeathMessages();
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
	}

	private void registerEntities()
	{
		EntityRegistry.registerGlobalEntityID(EntityDrone.class, "Drone", AliensVsPredator.INSTANCE.settings.entityList.get("DRONE"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityWarrior.class, "Warrior", AliensVsPredator.INSTANCE.settings.entityList.get("WARRIOR"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntitySpitter.class, "Spitter", AliensVsPredator.INSTANCE.settings.entityList.get("SPITTER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityCrusher.class, "Crusher", AliensVsPredator.INSTANCE.settings.entityList.get("CRUSHER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityPraetorian.class, "Praetorian", AliensVsPredator.INSTANCE.settings.entityList.get("PRAETORIAN"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityMarine.class, "Marine", AliensVsPredator.INSTANCE.settings.entityList.get("MARINE"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityYautja.class, "Yautja", AliensVsPredator.INSTANCE.settings.entityList.get("YAUTJA"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityQueen.class, "Queen", AliensVsPredator.INSTANCE.settings.entityList.get("QUEEN"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityFacehugger.class, "Facehugger", AliensVsPredator.INSTANCE.settings.entityList.get("FACEHUGGER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityChestburster.class, "Chestbuster", AliensVsPredator.INSTANCE.settings.entityList.get("CHESTBUSTER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityOvamorph.class, "Ovamorph", AliensVsPredator.INSTANCE.settings.entityList.get("OVAMORPH"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityRoyalFacehugger.class, "RoyalFacehugger", AliensVsPredator.INSTANCE.settings.entityList.get("ROYAL_FACEHUGGER"), 0x333333, 0xFF0000);
		EntityRegistry.registerGlobalEntityID(EntityAqua.class, "AquaticAlien", AliensVsPredator.INSTANCE.settings.entityList.get("AQUA"));
		EntityRegistry.registerGlobalEntityID(EntityPredalien.class, "Predalien", AliensVsPredator.INSTANCE.settings.entityList.get("PREDALIEN"));
	}

	private void registerInstanceEntities()
	{
		EntityRegistry.registerModEntity(EntitySpear.class, "TitaniumSpear", AliensVsPredator.INSTANCE.settings.entityList.get("CELTIC_SPEAR"), AliensVsPredator.INSTANCE, 250, 1, true);
		EntityRegistry.registerModEntity(EntityProximityMine.class, "ProximityMine", AliensVsPredator.INSTANCE.settings.entityList.get("PROXIMITY_MINE"), AliensVsPredator.INSTANCE, 250, 1, true);
		EntityRegistry.registerModEntity(EntityPlasma.class, "Plasma", AliensVsPredator.INSTANCE.settings.entityList.get("PLASMA"), AliensVsPredator.INSTANCE, 250, 1, true);
		EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", AliensVsPredator.INSTANCE.settings.entityList.get("GRENADE"), AliensVsPredator.INSTANCE, 250, 1, true);
		EntityRegistry.registerModEntity(EntityFlame.class, "Flamethrower", AliensVsPredator.INSTANCE.settings.entityList.get("FLAME"), AliensVsPredator.INSTANCE, 250, 1, true);
		EntityRegistry.registerModEntity(EntityAcidPool.class, "AcidPool", AliensVsPredator.INSTANCE.settings.entityList.get("FXACID"), AliensVsPredator.INSTANCE, 32, 1, true);
		EntityRegistry.registerModEntity(EntityAcidSpit.class, "AcidSpit", AliensVsPredator.INSTANCE.settings.entityList.get("AIACID"), AliensVsPredator.INSTANCE, 32, 1, true);
		EntityRegistry.registerModEntity(EntitySmartDisc.class, "EntityDisc", AliensVsPredator.INSTANCE.settings.entityList.get("DISC"), AliensVsPredator.INSTANCE, 250, 1, true);
		EntityRegistry.registerModEntity(EntityShuriken.class, "EntityShuriken", AliensVsPredator.INSTANCE.settings.entityList.get("SHURIKEN"), AliensVsPredator.INSTANCE, 250, 1, true);
		EntityRegistry.registerModEntity(EntityTurret.class, "EntityTurret", AliensVsPredator.INSTANCE.settings.entityList.get("TURRETENTITY"), AliensVsPredator.INSTANCE, 32, 1, true);
	}

	private void registerSpawns()
	{
		if (AliensVsPredator.INSTANCE.settings.doesModUseVanillaSpawnSystem())
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
		} else
		{
			AIRI.instance().spawnsystem.registerSpawnsForEntity(EntityDrone.class, AliensVsPredator.INSTANCE.settings.spawnList.get("DRONE"), EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.desert, BiomeGenBase.savanna, BiomeGenBase.birchForest, BiomeGenBase.extremeHills, BiomeGenBase.mesa, BiomeGenBase.roofedForest, BiomeGenBase.beach });
			AIRI.instance().spawnsystem.registerSpawnsForEntity(EntityMarine.class, AliensVsPredator.INSTANCE.settings.spawnList.get("MARINE"), EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.swampland, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.plains });
			AIRI.instance().spawnsystem.registerSpawnsForEntity(EntityYautja.class, AliensVsPredator.INSTANCE.settings.spawnList.get("YAUTJA"), EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.desertHills });
			AIRI.instance().spawnsystem.registerSpawnsForEntity(EntityWarrior.class, AliensVsPredator.INSTANCE.settings.spawnList.get("WARRIOR"), EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.forest, BiomeGenBase.taigaHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.desert, BiomeGenBase.savanna, BiomeGenBase.roofedForest, BiomeGenBase.beach });
			AIRI.instance().spawnsystem.registerSpawnsForEntity(EntitySpitter.class, AliensVsPredator.INSTANCE.settings.spawnList.get("SPITTER"), EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills });
			AIRI.instance().spawnsystem.registerSpawnsForEntity(EntityCrusher.class, AliensVsPredator.INSTANCE.settings.spawnList.get("CRUSHER"), EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills });
			AIRI.instance().spawnsystem.registerSpawnsForEntity(EntityFacehugger.class, AliensVsPredator.INSTANCE.settings.spawnList.get("FACEHUGGER"), EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.plains, BiomeGenBase.jungle, BiomeGenBase.jungleHills });
			AIRI.instance().spawnsystem.registerSpawnsForEntity(EntityChestburster.class, AliensVsPredator.INSTANCE.settings.spawnList.get("CHESTBURSTER"), EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.plains, BiomeGenBase.jungle, BiomeGenBase.jungleHills });
		}
	}

	@SuppressWarnings("deprecation")
	/*
	 * TODO: Fix deprecation
	 */
	private void registerDeathMessages()
	{
		LanguageRegistry.instance().addStringLocalization("death.attack.bullet", "en_US", "%1$s was shot by %2$s.");
		LanguageRegistry.instance().addStringLocalization("death.attack.spear", "en_US", "%1$s was speared by %2$s.");
		LanguageRegistry.instance().addStringLocalization("death.attack.acidshot", "en_US", "%1$s melted away in acid.");
	}
}
