package com.arisux.avp;

import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

import com.arisux.airi.engine.ItemTypeLib.HookedItem;
import com.arisux.airi.engine.ItemTypeLib.HookedItemAxe;
import com.arisux.airi.engine.ItemTypeLib.HookedItemPickaxe;
import com.arisux.airi.engine.ModEngine.IBHandler;
import com.arisux.airi.lib.util.interfaces.IInitializable;
import com.arisux.avp.entities.mob.*;
import com.arisux.avp.items.*;
import com.arisux.avp.items.ItemFirearm.ItemAmmo;

import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ItemHandler extends IBHandler implements IInitializable
{
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
			itemPistol = (new ItemFirearm(12, 2.0F, 15, 120, (ItemAmmo) itemAmmoPistol, AliensVsPredator.properties().SOUND_WEAPON_GUNSHOT)),
			itemM4 = (new ItemFirearm(24, 0.5F, 3, 120, (ItemAmmo) itemAmmoAR, AliensVsPredator.properties().SOUND_WEAPON_GUNSHOT).disableIcon()).setFull3D(),
			itemSniper = (new ItemFirearm(6, 1.8F, 40, 150, (ItemAmmo) itemAmmoSniper, AliensVsPredator.properties().SOUND_WEAPON_SNIPER).disableIcon()).setFull3D(),
			itemM41A = (new ItemFirearm(99, 0.5F, 2, 120, (ItemAmmo) itemAmmoAC, AliensVsPredator.properties().SOUND_WEAPON_PULSERIFLE).disableIcon()).setFull3D(),
			itemM56sg = (new ItemFirearm(128, 0.2F, 1, 120, (ItemAmmo) itemAmmoSMG, AliensVsPredator.properties().SOUND_WEAPON_M56SG).disableIcon()).setFull3D(),
			itemAK47 = (new ItemFirearm(32, 0.6F, 3, 110, (ItemAmmo) itemAmmoAR, AliensVsPredator.properties().SOUND_WEAPON_GUNSHOT).disableIcon()).setFull3D(),
			itemDoritos = (new ItemFood(8, true)).setAlwaysEdible(),
			itemDoritosCoolRanch = (new ItemFood(8, true)).setAlwaysEdible(),
			itemArtifactTech = (new HookedItem()).setDescription("An unknown piece of technology aquired from the yautja species."),
			healthProbe = new HookedItem().disableIcon(),
			itemProcessor = ((new HookedItem())),
			itemFlashDrive = (new ItemStorageDevice()),
			itemCapacitor = ((new HookedItem())),
			itemDiode = ((new HookedItem())),
			itemLed = ((new HookedItem())),
			itemLedDisplay = ((new HookedItem())),
			itemIntegratedCircuit = ((new HookedItem())),
			itemRegulator = ((new HookedItem())),
			itemResistor = ((new HookedItem())),
			itemTransistor = ((new HookedItem())),
			itemSilicon = new HookedItem().setDescription("A few chunks of silicon, for use with electronics."),
			itemIngotAluminum = new HookedItem().setDescription("An ingot melted down from bauxite ore, for use with guns."),
			itemIngotCopper = new HookedItem().setDescription("An ingot melted down from copper ore, for use with electronics."),
			itemIngotLithium = ((HookedItem) new ItemIngotLithium()).setDescription("An ingot of lithium. Depletes quicly after mining."),
			itemMotionTracker = ((HookedItem) (new HookedItem()).disableIcon()).setDescription("F.E.M.S. 5.547.60 - Tracks movement by detecting changes in air density."),
			itemWorldSelector = ((new ItemWorldSelectionExporter()));

	public ItemEntitySummoner itemSummonerDrone = (new ItemEntitySummoner(this.getModController().domain(), EntityDrone.class)),
			itemSummonerAqua = (new ItemEntitySummoner(this.getModController().domain(), EntityAqua.class)),
			itemSummonerWarrior = (new ItemEntitySummoner(this.getModController().domain(), EntityWarrior.class)),
			itemSummonerCrusher = (new ItemEntitySummoner(this.getModController().domain(), EntityCrusher.class)),
			itemSummonerSpitter = (new ItemEntitySummoner(this.getModController().domain(), EntitySpitter.class)),
			itemSummonerPraetorian = (new ItemEntitySummoner(this.getModController().domain(), EntityPraetorian.class)),
			itemSummonerQueen = (new ItemEntitySummoner(this.getModController().domain(), EntityQueen.class)),
			itemSummonerChestburster = (new ItemEntitySummoner(this.getModController().domain(), EntityChestburster.class)),
			itemSummonerFacehugger = (new ItemEntitySummoner(this.getModController().domain(), EntityFacehugger.class)),
			itemSummonerMarine = (new ItemEntitySummoner(this.getModController().domain(), EntityMarine.class)),
			itemSummonerOvamorph = (new ItemEntitySummoner(this.getModController().domain(), EntityOvamorph.class)),
			itemSummonerRoyalFacehugger = (new ItemEntitySummoner(this.getModController().domain(), EntityRoyalFacehugger.class)),
			itemSummonerYautja = (new ItemEntitySummoner(this.getModController().domain(), EntityYautja.class)),
			itemSummonerPredalien = (new ItemEntitySummoner(this.getModController().domain(), EntityPredalien.class)),
			itemSummonerCombatSynthetic = (new ItemEntitySummoner(this.getModController().domain(), EntityCombatSynthetic.class));

	public ItemHandler()
	{
		super(AliensVsPredator.instance());
	}
	
	@Override
	public void initialize(FMLInitializationEvent event)
	{
		registerItem(itemSummonerDrone, "summon.drone");
		registerItem(itemSummonerWarrior, "summon.warrior");
		registerItem(itemSummonerCrusher, "summon.crusher");
		registerItem(itemSummonerSpitter, "summon.spitter");
		registerItem(itemSummonerPraetorian, "summon.praetorian");
		registerItem(itemSummonerQueen, "summon.queen");
		registerItem(itemSummonerOvamorph, "summon.ovamorph");
		registerItem(itemSummonerFacehugger, "summon.facehugger");
		registerItem(itemSummonerRoyalFacehugger, "summon.royalfacehugger");
		registerItem(itemSummonerChestburster, "summon.chestburster");
		registerItem(itemSummonerYautja, "summon.yautja");
		registerItem(itemSummonerMarine, "summon.marine");
		registerItem(itemSummonerCombatSynthetic, "summon.synthetic.combat");
		registerItem(itemSummonerAqua, "summon.aqua");
		registerItem(itemSummonerPredalien, "summon.predalien");
		registerItem(pressureMask, "helm.pressure");
		registerItem(pressureChest, "body.pressure");
		registerItem(pressurePants, "legwear.pressure");
		registerItem(pressureBoots, "boots.pressure");
		registerItem(helmMarine, "helm.tactical");
		registerItem(plateMarine, "body.tactical");
		registerItem(legsMarine, "legwear.tactical");
		registerItem(bootsMarine, "boots.tactical");
		registerItem(helmXeno, "helm.xeno");
		registerItem(plateXeno, "body.xeno");
		registerItem(legsXeno, "legwear.xeno");
		registerItem(bootsXeno, "boots.xeno");
		registerItem(helmTitanium, "helm.celtic");
		registerItem(plateTitanium, "body.celtic");
		registerItem(legsTitanium, "legwear.celtic");
		registerItem(bootsTitanium, "boots.celtic");
		registerItem(itemPistol, "gun.pistol");
		registerItem(itemM4, "gun.m4");
		registerItem(itemSniper, "gun.sniper");
		registerItem(itemM41A, "gun.m41a");
		registerItem(itemM56sg, "gun.m56sg");
		registerItem(itemAK47, "gun.ak47");
		registerItem(itemFlamethrower, "gun.flamethrower");
		registerItem(itemAmmoAR, "ammo.ar");
		registerItem(itemAmmoAC, "ammo.ac");
		registerItem(itemAmmoPistol, "ammo.pistol");
		registerItem(itemAmmoSMG, "ammo.smg");
		registerItem(itemAmmoSniper, "ammo.sniper");
		registerItem(itemGrenade, "grenade.m40");
		registerItem(itemFireGrenade, "grenade.incinderary");
		registerItem(itemWristBlade, "wristblade");
		registerItem(itemPlasmaCaster, "plasmacaster");
		registerItem(itemProximityMine, "mine.proximity");
		registerItem(itemDisc, "smartdisc");
		registerItem(itemShuriken, "shuriken");
		registerItem(itemSpear, "tool.celtic.spear");
		registerItem(shovelTitanium, "tool.celtic.shovel");
		registerItem(swordTitanium, "tool.celtic.sword");
		registerItem(hoeTitanium, "tool.celtic.hoe");
		registerItem(axeTitanium, "tool.celtic.axe");
		registerItem(pickaxeTitanium, "tool.celtic.pick");
		registerItem(itemArtifactTech, "artifact.tech");
		registerItem(itemDoritos, "food.doritos");
		registerItem(itemDoritosCoolRanch, "food.doritos.coolranch");
		registerItem(healthProbe, "healthprobe");
		registerItem(itemFlashDrive, "device.nbtdrive");
		registerItem(itemProcessor, "part.processor");
		registerItem(itemCapacitor, "part.capacitor");
		registerItem(itemDiode, "part.diode");
		registerItem(itemLed, "part.led");
		registerItem(itemLedDisplay, "part.led.display");
		registerItem(itemIntegratedCircuit, "part.ic");
		registerItem(itemRegulator, "part.regulator");
		registerItem(itemResistor, "part.resistor");
		registerItem(itemTransistor, "part.transistor");
		registerItem(itemIngotCopper, "ingot.copper");
		registerItem(itemIngotLithium, "ingot.lithium");
		registerItem(itemIngotAluminum, "ingot.aluminum");
		registerItem(itemSilicon, "silicon");
		registerItem(itemMotionTracker, "motiontracker");
		registerItem(itemWorldSelector, "worldselector");
	}
}
