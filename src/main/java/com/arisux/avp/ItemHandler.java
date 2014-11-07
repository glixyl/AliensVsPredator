package com.arisux.avp;

import static com.arisux.airi.lib.RegistryLib.registerItem;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

import com.arisux.airi.lib.ItemTypeLib.HookedItem;
import com.arisux.airi.lib.ItemTypeLib.HookedItemAxe;
import com.arisux.airi.lib.ItemTypeLib.HookedItemPickaxe;
import com.arisux.airi.lib.RegistryLib.IBHandler;
import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.entities.mob.*;
import com.arisux.avp.items.ItemArmorMarine;
import com.arisux.avp.items.ItemArmorPressureSuit;
import com.arisux.avp.items.ItemArmorTitanium;
import com.arisux.avp.items.ItemArmorXeno;
import com.arisux.avp.items.ItemDisc;
import com.arisux.avp.items.ItemEntitySummoner;
import com.arisux.avp.items.ItemFirearm;
import com.arisux.avp.items.ItemFirearm.ItemAmmo;
import com.arisux.avp.items.*;

public class ItemHandler implements IInitializable, IBHandler
{
	private ArrayList<Object> itemList = new ArrayList<Object>();

	public ArmorMaterial YAUTJA = EnumHelper.addArmorMaterial("YAUTJA", 32, new int[] { 4, 7, 5, 3 }, 9);
	public ArmorMaterial XENO = EnumHelper.addArmorMaterial("XENO", 30, new int[] { 2, 7, 5, 3 }, 7);
	public ArmorMaterial TACTICAL = EnumHelper.addArmorMaterial("TACTICAL", 25, new int[] { 2, 6, 3, 2 }, 5);
	public ArmorMaterial PRESSURESUIT = EnumHelper.addArmorMaterial("PRESSURESUIT", 24, new int[] { 2, 4, 3, 2 }, 6);
	public ToolMaterial YAUTJA_TOOLS = EnumHelper.addToolMaterial("CELTIC", 12, 1430, 9.0F, 7.0F, 9);
	public ToolMaterial XENO_TOOLS = EnumHelper.addToolMaterial("XENO", 7, 630, 9.0F, 6.0F, 14);

	public Item helmXeno = (new ItemArmorXeno(XENO, 4, 0)),
			plateXeno = (new ItemArmorXeno(XENO, 4, 1)),
			legsXeno = (new ItemArmorXeno(XENO, 4, 2)),
			bootsXeno = (new ItemArmorXeno(XENO, 4, 3)),
			pressureMask = new ItemArmorPressureSuit(PRESSURESUIT, 4, 0),
			pressureChest = new ItemArmorPressureSuit(PRESSURESUIT, 4, 1),
			pressurePants = new ItemArmorPressureSuit(PRESSURESUIT, 4, 2),
			pressureBoots = new ItemArmorPressureSuit(PRESSURESUIT, 4, 3),
			helmMarine = (new ItemArmorMarine(TACTICAL, 4, 0)),
			plateMarine = (new ItemArmorMarine(TACTICAL, 4, 1)),
			legsMarine = (new ItemArmorMarine(TACTICAL, 4, 2)),
			bootsMarine = (new ItemArmorMarine(TACTICAL, 4, 3)),
			helmTitanium = (new ItemArmorTitanium(YAUTJA, 4, 0)),
			legsTitanium = (new ItemArmorTitanium(YAUTJA, 4, 2)),
			plateTitanium = (new ItemArmorTitanium(YAUTJA, 4, 1)),
			bootsTitanium = (new ItemArmorTitanium(YAUTJA, 4, 3)),
			shovelTitanium = (new ItemSpade(YAUTJA_TOOLS)),
			pickaxeTitanium = (new HookedItemPickaxe(YAUTJA_TOOLS)),
			axeTitanium = (new HookedItemAxe(YAUTJA_TOOLS)),
			swordTitanium = (new ItemSword(YAUTJA_TOOLS)),
			hoeTitanium = (new ItemHoe(YAUTJA_TOOLS)),
			itemSpear = (new ItemSpear(YAUTJA_TOOLS)),
			itemWristBlade = (new ItemWristbracer(YAUTJA_TOOLS)),
			itemPlasmaCaster = (new ItemPlasmaCaster()).setFull3D(),
			itemProximityMine = (new ItemProximityMine()),
			itemDisc = (new ItemDisc()),
			itemShuriken = (new ItemShuriken()),
			itemGrenade = (new ItemGrenade()).setFlaming(false),
			itemFireGrenade = (new ItemGrenade()).setFlaming(true),
			itemFlamethrower = (new ItemFlamethrower()),
			itemAmmoPistol = ((HookedItem) (new ItemAmmo(0.05F))).setDescription("A magazine classified for use with pistols."),
			itemAmmoAR = ((HookedItem) (new ItemAmmo(0.09F))).setDescription("A magazine classified for use with assault rifles."),
			itemAmmoAC = ((HookedItem) (new ItemAmmo(0.09F))).setDescription("A magazine classified for use with assault carbines."),
			itemAmmoSMG = ((HookedItem) (new ItemAmmo(0.5F))).setDescription("A magazine classified for use with sub machine-guns."),
			itemAmmoSniper = ((HookedItem) (new ItemAmmo(0.1F))).setDescription("A magazine classified for use with sniper rifles."),
			itemPistol = (new ItemFirearm(12, 2.0F, 15, 120, (ItemAmmo) itemAmmoPistol, AliensVsPredator.INSTANCE.properties.SOUND_WEAPON_GUNSHOT)),
			itemM4 = (new ItemFirearm(24, 0.5F, 3, 120, (ItemAmmo) itemAmmoAR, AliensVsPredator.INSTANCE.properties.SOUND_WEAPON_GUNSHOT).disableIcon()).setFull3D(),
			itemSniper = (new ItemFirearm(6, 1.8F, 40, 150, (ItemAmmo) itemAmmoSniper, AliensVsPredator.INSTANCE.properties.SOUND_WEAPON_SNIPER).disableIcon()).setFull3D(),
			itemM41A = (new ItemFirearm(99, 0.5F, 2, 120, (ItemAmmo) itemAmmoAC, AliensVsPredator.INSTANCE.properties.SOUND_WEAPON_PULSERIFLE).disableIcon()).setFull3D(),
			itemM56sg = (new ItemFirearm(128, 0.2F, 1, 120, (ItemAmmo) itemAmmoSMG, AliensVsPredator.INSTANCE.properties.SOUND_WEAPON_M56SG).disableIcon()).setFull3D(),
			itemAK47 = (new ItemFirearm(32, 0.6F, 3, 110, (ItemAmmo) itemAmmoAR, AliensVsPredator.INSTANCE.properties.SOUND_WEAPON_GUNSHOT).disableIcon()).setFull3D(),
			itemDoritos = (new ItemFood(8, true)).setAlwaysEdible(),
			itemDoritosCoolRanch = (new ItemFood(8, true)).setAlwaysEdible(),
			itemEnergy = ((HookedItem) (new HookedItem())).setDescription("A capsule full of plasma. For use with plasma casters."),
			itemArtifactTech = ((HookedItem) (new HookedItem())).setDescription("An unknown piece of technology aquired from the yautja species."),
			healthProbe = ((HookedItem) new HookedItem()).disableIcon(),
			itemProcessor = ((HookedItem) (new HookedItem())),
			itemFlashDrive = (new ItemStorageDevice()),
			itemCapacitor = ((HookedItem) (new HookedItem())),
			itemDiode = ((HookedItem) (new HookedItem())),
			itemLed = ((HookedItem) (new HookedItem())),
			itemLedDisplay = ((HookedItem) (new HookedItem())),
			itemIntegratedCircuit = ((HookedItem) (new HookedItem())),
			itemRegulator = ((HookedItem) (new HookedItem())),
			itemResistor = ((HookedItem) (new HookedItem())),
			itemTransistor = ((HookedItem) (new HookedItem())),
			itemSilicon = ((HookedItem) new HookedItem()).setDescription("A few chunks of silicon, for use with electronics."),
			itemIngotAluminum = ((HookedItem) new HookedItem()).setDescription("An ingot melted down from bauxite ore, for use with guns."),
			itemIngotCopper = ((HookedItem) new HookedItem()).setDescription("An ingot melted down from copper ore, for use with electronics."),
			itemIngotLithium = ((HookedItem) new ItemIngotLithium()).setDescription("An ingot of lithium. Depletes quicly after mining."),
			itemMotionTracker = ((HookedItem) (new HookedItem()).disableIcon()).setDescription("F.E.M.S. 5.547.60 - Tracks movement by detecting changes in air density."),
			itemWorldSelector = ((HookedItem) (new ItemWorldSelectionExporter()));

	public ItemEntitySummoner itemSummonerDrone = (new ItemEntitySummoner(getDomain(), EntityDrone.class)),
			itemSummonerAqua = (new ItemEntitySummoner(getDomain(), EntityAqua.class)),
			itemSummonerWarrior = (new ItemEntitySummoner(getDomain(), EntityWarrior.class)),
			itemSummonerCrusher = (new ItemEntitySummoner(getDomain(), EntityCrusher.class)),
			itemSummonerSpitter = (new ItemEntitySummoner(getDomain(), EntitySpitter.class)),
			itemSummonerPraetorian = (new ItemEntitySummoner(getDomain(), EntityPraetorian.class)),
			itemSummonerQueen = (new ItemEntitySummoner(getDomain(), EntityQueen.class)),
			itemSummonerChestburster = (new ItemEntitySummoner(getDomain(), EntityChestburster.class)),
			itemSummonerFacehugger = (new ItemEntitySummoner(getDomain(), EntityFacehugger.class)),
			itemSummonerMarine = (new ItemEntitySummoner(getDomain(), EntityMarine.class)),
			itemSummonerOvamorph = (new ItemEntitySummoner(getDomain(), EntityOvamorph.class)),
			itemSummonerRoyalFacehugger = (new ItemEntitySummoner(getDomain(), EntityRoyalFacehugger.class)),
			itemSummonerYautja = (new ItemEntitySummoner(getDomain(), EntityYautja.class)),
			itemSummonerPredalien = (new ItemEntitySummoner(getDomain(), EntityPredalien.class));

	public void initialize()
	{
		registerItem(itemSummonerDrone, "summon.drone", this);
		registerItem(itemSummonerWarrior, "summon.warrior", this);
		registerItem(itemSummonerCrusher, "summon.crusher", this);
		registerItem(itemSummonerSpitter, "summon.spitter", this);
		registerItem(itemSummonerPraetorian, "summon.praetorian", this);
		registerItem(itemSummonerQueen, "summon.queen", this);
		registerItem(itemSummonerOvamorph, "summon.ovamorph", this);
		registerItem(itemSummonerFacehugger, "summon.facehugger", this);
		registerItem(itemSummonerRoyalFacehugger, "summon.royalfacehugger", this);
		registerItem(itemSummonerChestburster, "summon.chestburster", this);
		registerItem(itemSummonerYautja, "summon.yautja", this);
		registerItem(itemSummonerMarine, "summon.marine", this);
		registerItem(itemSummonerAqua, "summon.aqua", this);
		registerItem(itemSummonerPredalien, "summon.predalien", this);
		registerItem(pressureMask, "helm.pressure", this);
		registerItem(pressureChest, "body.pressure", this);
		registerItem(pressurePants, "legwear.pressure", this);
		registerItem(pressureBoots, "boots.pressure", this);
		registerItem(helmMarine, "helm.tactical", this);
		registerItem(plateMarine, "body.tactical", this);
		registerItem(legsMarine, "legwear.tactical", this);
		registerItem(bootsMarine, "boots.tactical", this);
		registerItem(helmXeno, "helm.xeno", this);
		registerItem(plateXeno, "body.xeno", this);
		registerItem(legsXeno, "legwear.xeno", this);
		registerItem(bootsXeno, "boots.xeno", this);
		registerItem(helmTitanium, "helm.celtic", this);
		registerItem(plateTitanium, "body.celtic", this);
		registerItem(legsTitanium, "legwear.celtic", this);
		registerItem(bootsTitanium, "boots.celtic", this);
		registerItem(itemPistol, "gun.pistol", this);
		registerItem(itemM4, "gun.m4", this);
		registerItem(itemSniper, "gun.sniper", this);
		registerItem(itemM41A, "gun.m41a", this);
		registerItem(itemM56sg, "gun.m56sg", this);
		registerItem(itemAK47, "gun.ak47", this);
		registerItem(itemFlamethrower, "gun.flamethrower", this);
		registerItem(itemAmmoAR, "ammo.ar", this);
		registerItem(itemAmmoAC, "ammo.ac", this);
		registerItem(itemAmmoPistol, "ammo.pistol", this);
		registerItem(itemAmmoSMG, "ammo.smg", this);
		registerItem(itemAmmoSniper, "ammo.sniper", this);
		registerItem(itemGrenade, "grenade.m40", this);
		registerItem(itemFireGrenade, "grenade.incinderary", this);
		registerItem(itemWristBlade, "wristblade", this);
		registerItem(itemPlasmaCaster, "plasmacaster", this);
		registerItem(itemProximityMine, "mine.proximity", this);
		registerItem(itemDisc, "smartdisc", this);
		registerItem(itemShuriken, "shuriken", this);
		registerItem(itemSpear, "tool.celtic.spear", this);
		registerItem(shovelTitanium, "tool.celtic.shovel", this);
		registerItem(swordTitanium, "tool.celtic.sword", this);
		registerItem(hoeTitanium, "tool.celtic.hoe", this);
		registerItem(axeTitanium, "tool.celtic.axe", this);
		registerItem(pickaxeTitanium, "tool.celtic.pick", this);
		registerItem(itemEnergy, "plasma.capsule", this);
		registerItem(itemArtifactTech, "artifact.tech", this);
		registerItem(itemDoritos, "food.doritos", this);
		registerItem(itemDoritosCoolRanch, "food.doritos.coolranch", this);
		registerItem(healthProbe, "healthprobe", this, false, null);
		registerItem(itemFlashDrive, "device.nbtdrive", this);
		registerItem(itemProcessor, "part.processor", this);
		registerItem(itemCapacitor, "part.capacitor", this);
		registerItem(itemDiode, "part.diode", this);
		registerItem(itemLed, "part.led", this);
		registerItem(itemLedDisplay, "part.led.display", this);
		registerItem(itemIntegratedCircuit, "part.ic", this);
		registerItem(itemRegulator, "part.regulator", this);
		registerItem(itemResistor, "part.resistor", this);
		registerItem(itemTransistor, "part.transistor", this);
		registerItem(itemIngotCopper, "ingot.copper", this);
		registerItem(itemIngotLithium, "ingot.lithium", this);
		registerItem(itemIngotAluminum, "ingot.aluminum", this);
		registerItem(itemSilicon, "silicon", this);
		registerItem(itemMotionTracker, "motiontracker", this);
		registerItem(itemWorldSelector, "worldselector", this);
	}

	@Override
	public String getDomain()
	{
		return Properties.DOMAIN;
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return AliensVsPredator.INSTANCE.tab;
	}

	@Override
	public ArrayList<Object> getHandledObjects()
	{
		return itemList;
	}
}
