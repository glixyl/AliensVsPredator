package com.arisux.avp;

import com.arisux.airi.lib.ItemTypes.HookedItem;
import com.arisux.airi.lib.ItemTypes.HookedItemAxe;
import com.arisux.airi.lib.ItemTypes.HookedItemPickaxe;
import com.arisux.airi.lib.ModUtil.IBHandler;
import com.arisux.airi.lib.interfaces.IInitializable;
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
import com.arisux.avp.items.ItemAPC;
import com.arisux.avp.items.ItemArmorMarine;
import com.arisux.avp.items.ItemArmorPressureSuit;
import com.arisux.avp.items.ItemArmorTitanium;
import com.arisux.avp.items.ItemArmorXeno;
import com.arisux.avp.items.ItemBlackGooBucket;
import com.arisux.avp.items.ItemMaintenanceJack;
import com.arisux.avp.items.ItemDisc;
import com.arisux.avp.items.ItemEntitySummoner;
import com.arisux.avp.items.ItemFirearm;
import com.arisux.avp.items.ItemFirearm.ItemAmmo;
import com.arisux.avp.items.ItemGrenade;
import com.arisux.avp.items.ItemIngotLithium;
import com.arisux.avp.items.ItemLaserMine;
import com.arisux.avp.items.ItemM240IncineratorUnit;
import com.arisux.avp.items.ItemNostromoFlamethrower;
import com.arisux.avp.items.ItemPlasmaCaster;
import com.arisux.avp.items.ItemShuriken;
import com.arisux.avp.items.ItemSpear;
import com.arisux.avp.items.ItemStorageDevice;
import com.arisux.avp.items.ItemSupplyChute;
import com.arisux.avp.items.ItemWristbracer;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ItemHandler extends IBHandler implements IInitializable
{
	public static final ArmorMaterial YAUTJA = EnumHelper.addArmorMaterial("YAUTJA", 32, new int[] { 4, 7, 5, 3 }, 9);
	public static final ArmorMaterial XENO = EnumHelper.addArmorMaterial("XENO", 30, new int[] { 2, 7, 5, 3 }, 7);
	public static final ArmorMaterial TACTICAL = EnumHelper.addArmorMaterial("TACTICAL", 25, new int[] { 2, 6, 3, 2 }, 5);
	public static final ArmorMaterial PRESSURESUIT = EnumHelper.addArmorMaterial("PRESSURESUIT", 24, new int[] { 2, 4, 3, 2 }, 6);
	public static final ToolMaterial YAUTJA_TOOLS = EnumHelper.addToolMaterial("CELTIC", 12, 1430, 9.0F, 8.0F, 9);
	public static final ToolMaterial XENO_TOOLS = EnumHelper.addToolMaterial("XENO", 7, 730, 10.0F, 7.0F, 14);

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
		blackGooBucket = (new ItemBlackGooBucket(AliensVsPredator.blocks().blockBlackGoo)),
		pickaxeTitanium = (new HookedItemPickaxe(YAUTJA_TOOLS)),
		axeTitanium = (new HookedItemAxe(YAUTJA_TOOLS)),
		swordTitanium = (new ItemSword(YAUTJA_TOOLS)),
		hoeTitanium = (new ItemHoe(YAUTJA_TOOLS)),
		itemSpear = (new ItemSpear(YAUTJA_TOOLS)),
		itemWristBlade = (new ItemWristbracer()).setDescription("Applies " + ItemWristbracer.getDamageToApply() + " damage to any entity, regardless of their armor, while blades are inserted.").setMaxStackSize(1),
		itemWristbracerBlades = (new HookedItem()).setDescription("Place these in the first slot of your wristbracer").setMaxStackSize(1).setMaxDamage(YAUTJA_TOOLS.getMaxUses()),
		itemPlasmaCaster = (new ItemPlasmaCaster()).setFull3D(),
		itemProximityMine = (new ItemLaserMine()),
		itemDisc = (new ItemDisc()),
		itemShuriken = (new ItemShuriken()),
		itemGrenade = (new ItemGrenade()).setFlaming(false),
		itemIncendiaryGrenade = (new ItemGrenade()).setFlaming(true),
		itemAmmoPistol = ((HookedItem) (new ItemAmmo(1.0F))).setDescription("A magazine classified for use with pistols."),
		itemPistolStock = (new HookedItem()).setDescription("A Pistol stock for use in assembling pistols.").setMaxStackSize(64),
		itemPistolBarrel = (new HookedItem()).setDescription("A Pistol barrel for use in assembling pistols.").setMaxStackSize(64),
		itemPistolAction = (new HookedItem()).setDescription("A Pistol action for use in assembling pistols.").setMaxStackSize(64),
		itemAmmoAR = ((HookedItem) (new ItemAmmo(2.0F))).setDescription("A magazine classified for use with assault rifles."),
		itemAmmoAC = ((HookedItem) (new ItemAmmo(2.5F))).setDescription("A magazine classified for use with assault carbines."),
		itemAmmoSMG = ((HookedItem) (new ItemAmmo(4.5F))).setDescription("A magazine classified for use with sub machine-guns."),
		itemAmmoSniper = ((HookedItem) (new ItemAmmo(18.0F))).setDescription("A magazine classified for use with sniper rifles."),
		itemFuelTank = (new HookedItem()).setDescription("Pressurized fuel tank.").setMaxStackSize(1).setMaxDamage(64),
		itemM240ICU = (new ItemM240IncineratorUnit(itemFuelTank)).setDescription("M240 Incinerator Unit"),
		itemNostromoFlamethrower = (new ItemNostromoFlamethrower(itemFuelTank)).setDescription("Flame Thrower (Nostromo)"),
		itemPistol = (new ItemFirearm(12, 2.0F, 15, 120, (ItemAmmo) itemAmmoPistol, AliensVsPredator.properties().SOUND_WEAPON_GUNSHOT)),
		itemM4 = (new ItemFirearm(24, 0.5F, 3, 120, (ItemAmmo) itemAmmoAR, AliensVsPredator.properties().SOUND_WEAPON_GUNSHOT).setSoundLength(0.75D).disableIcon()).setFull3D(),
		itemM4Stock = (new HookedItem()).setDescription("An M4 stock for use in assembling M4's.").setMaxStackSize(64),
		itemM4Barrel = (new HookedItem()).setDescription("An M4 barrel for use in assembling M4's.").setMaxStackSize(64),
		itemM4Action = (new HookedItem()).setDescription("An M4 action for use in assembling M4's.").setMaxStackSize(64),
		itemSniper = (new ItemFirearm(6, 1.8F, 40, 150, (ItemAmmo) itemAmmoSniper, AliensVsPredator.properties().SOUND_WEAPON_SNIPER).disableIcon()).setFull3D(),
		itemSniperStock = (new HookedItem()).setDescription("A Sniper Rifle stock for use in assembling Sniper Rifle's.").setMaxStackSize(64),
		itemSniperBarrel = (new HookedItem()).setDescription("A Sniper Rifle barrel for use in assembling Sniper Rifle's.").setMaxStackSize(64),
		itemSniperAction = (new HookedItem()).setDescription("A Sniper Rifle action for use in assembling Sniper Rifle's.").setMaxStackSize(64),
		itemSniperScope = (new HookedItem()).setDescription("A Sniper Rifle scope for use in assembling Sniper Rifle's.").setMaxStackSize(64),
		itemSniperPeripherals = (new HookedItem()).setDescription("Sniper Rifle peripherals for use in assembling Sniper Rifle's.").setMaxStackSize(64),
		itemM41A = (new ItemFirearm(99, 0.5F, 2, 120, (ItemAmmo) itemAmmoAC, AliensVsPredator.properties().SOUND_WEAPON_PULSERIFLE).setSoundLength(1.25D).disableIcon()).setFull3D(),
		itemM41AStock = (new HookedItem()).setDescription("An M41A stock for use in assembling M41A's.").setMaxStackSize(64),
		itemM41ABarrel = (new HookedItem()).setDescription("An M41A barrel for use in assembling M41A's.").setMaxStackSize(64),
		itemM41AAction = (new HookedItem()).setDescription("An M41A action for use in assembling M41A's.").setMaxStackSize(64),
		itemM41APeripherals = (new HookedItem()).setDescription("M41A peripherals for use in assembling M41A's.").setMaxStackSize(64),
		itemM56SG = (new ItemFirearm(128, 0.2F, 2, 120, (ItemAmmo) itemAmmoSMG, AliensVsPredator.properties().SOUND_WEAPON_M56SG).disableIcon()).setFull3D(),
		itemM56SGStock = (new HookedItem()).setDescription("An M56SG stock for use in assembling M56SG's.").setMaxStackSize(64),
		itemM56SGBarrel = (new HookedItem()).setDescription("An M56SG barrel for use in assembling M56SG's.").setMaxStackSize(64),
		itemM56SGAction = (new HookedItem()).setDescription("An M56SG action for use in assembling M56SG's.").setMaxStackSize(64),
		itemM56SGSupportFrame = (new HookedItem()).setDescription("An M56SG support frame for use in assembling M56SG's.").setMaxStackSize(64),
		itemM56SGAimingModule = (new HookedItem()).setDescription("An M56SG aiming module for use in assembling M56SG's.").setMaxStackSize(64),
		itemAK47 = (new ItemFirearm(32, 0.6F, 3, 110, (ItemAmmo) itemAmmoAR, AliensVsPredator.properties().SOUND_WEAPON_GUNSHOT).disableIcon()).setFull3D(),
		itemAK47Stock = (new HookedItem()).setDescription("An AK47 stock for use in assembling AK-47's").setMaxStackSize(64),
		itemAK47Barrel = (new HookedItem()).setDescription("An AK47 barrel for use in assembling AK-47's.").setMaxStackSize(64),
		itemAK47Action = (new HookedItem()).setDescription("An AK47 action for use in assembling AK-47's.").setMaxStackSize(64),
		itemDoritos = (new ItemFood(8, true)).setAlwaysEdible(),
		itemDoritosCoolRanch = (new ItemFood(8, true)).setAlwaysEdible(),
		itemCapacitor = (new HookedItem()).setMaxStackSize(64),
		itemTransistor = (new HookedItem()).setMaxStackSize(64),
		itemRAM = (new HookedItem()).setMaxStackSize(64),
		itemResistor = (new HookedItem()).setMaxStackSize(64),
		itemArtifactTech = (new HookedItem()).setDescription("An unknown piece of technology aquired from the yautja species."),
		itemProcessor = ((new HookedItem())),
		itemSolidStateDrive = ((new HookedItem())),
		itemDiode = ((new HookedItem())),
		itemMotherboard = ((new HookedItem())),
		itemPowerSupply = ((new HookedItem())),
		itemSupplyChute = ((new ItemSupplyChute())),
		itemFlashDrive = (new ItemStorageDevice()),
		itemVoltageRegulator = ((new HookedItem())),
		itemLed = ((new HookedItem())),
		itemLedDisplay = ((new HookedItem())),
		itemCarbon = (new HookedItem()).setDescription("Used in the crafting of several electrical components."),
		itemIntegratedCircuit = ((new HookedItem())),
		itemAPC = ((new ItemAPC())),
		itemSilicon = new HookedItem().setDescription("A few chunks of silicon, for use with electronics."),
		itemIngotAluminum = new HookedItem().setDescription("An ingot melted down from bauxite ore, for use with guns."),
		itemIngotCopper = new HookedItem().setDescription("An ingot melted down from copper ore, for use with electronics."),
		itemIngotLithium = ((HookedItem) new ItemIngotLithium()).setDescription("An ingot of lithium. Depletes quicly after mining."),
		itemMotionTracker = ((HookedItem) (new HookedItem()).disableIcon()).setDescription("Tracks movement by detecting changes in air density."),
		itemPolycarbonate = (new HookedItem()).setDescription("Polycarbonate (A hard plastic used in the crafting of many items/blocks)"),
		itemRoyalJelly = (new HookedItem()).setDescription("Royal Jelly (A potent, transformative substance produced by a Xenomorph Queen)"),
		itemBlackGoo = (new HookedItem()).setDescription("Black Goo (A0-3959X.91 – 15)"),
		itemPhial = (new HookedItem()).setDescription(""),
		itemPhialEmpty = (new HookedItem()).setDescription(""),
		itemMaintenanceJack = (new ItemMaintenanceJack());

	public ItemEntitySummoner itemSummonerDrone = (new ItemEntitySummoner(this.getMod().domain(), EntityDrone.class)),
		itemSummonerAqua = (new ItemEntitySummoner(this.getMod().domain(), EntityAqua.class)),
		itemSummonerWarrior = (new ItemEntitySummoner(this.getMod().domain(), EntityWarrior.class)),
		itemSummonerCrusher = (new ItemEntitySummoner(this.getMod().domain(), EntityCrusher.class)),
		itemSummonerSpitter = (new ItemEntitySummoner(this.getMod().domain(), EntitySpitter.class)),
		itemSummonerPraetorian = (new ItemEntitySummoner(this.getMod().domain(), EntityPraetorian.class)),
		itemSummonerQueen = (new ItemEntitySummoner(this.getMod().domain(), EntityQueen.class)),
		itemSummonerChestburster = (new ItemEntitySummoner(this.getMod().domain(), EntityChestburster.class)),
		itemSummonerFacehugger = (new ItemEntitySummoner(this.getMod().domain(), EntityFacehugger.class)),
		itemSummonerMarine = (new ItemEntitySummoner(this.getMod().domain(), EntityMarine.class)),
		itemSummonerOvamorph = (new ItemEntitySummoner(this.getMod().domain(), EntityOvamorph.class)),
		itemSummonerRoyalFacehugger = (new ItemEntitySummoner(this.getMod().domain(), EntityRoyalFacehugger.class)),
		itemSummonerYautja = (new ItemEntitySummoner(this.getMod().domain(), EntityYautja.class)),
		itemSummonerPredalien = (new ItemEntitySummoner(this.getMod().domain(), EntityPredalien.class)),
		itemSummonerCombatSynthetic = (new ItemEntitySummoner(this.getMod().domain(), EntityCombatSynthetic.class)),
		itemSummonerProtomorph = (new ItemEntitySummoner(this.getMod().domain(), EntityProtomorph.class)),
		itemSummonerHammerpede = (new ItemEntitySummoner(this.getMod().domain(), EntityHammerpede.class)),
		itemSummonerTrilobite = (new ItemEntitySummoner(this.getMod().domain(), EntityTrilobite.class)),
		itemSummonerYautjaBerserker = (new ItemEntitySummoner(this.getMod().domain(), EntityYautjaBerserker.class)),
		itemSummonerSpaceJockey = (new ItemEntitySummoner(this.getMod().domain(), EntitySpaceJockey.class)),
		itemSummonerEngineer = (new ItemEntitySummoner(this.getMod().domain(), EntityEngineer.class));

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
		registerItem(itemSummonerPredalien, "summon.predalien");
		registerItem(itemSummonerProtomorph, "summon.protomorph");
		registerItem(itemSummonerHammerpede, "summon.hammerpede");
		registerItem(itemSummonerTrilobite, "summon.trilobite");
		registerItem(itemSummonerEngineer, "summon.engineer");
		registerItem(itemSummonerSpaceJockey, "summon.spacejockey");
		registerItem(itemSummonerOvamorph, "summon.ovamorph");
		registerItem(itemSummonerFacehugger, "summon.facehugger");
		registerItem(itemSummonerRoyalFacehugger, "summon.royalfacehugger");
		registerItem(itemSummonerChestburster, "summon.chestburster");
		registerItem(itemSummonerYautja, "summon.yautja");
		registerItem(itemSummonerYautjaBerserker, "summon.yautja.berserker");
		registerItem(itemSummonerMarine, "summon.marine");
		registerItem(itemSummonerCombatSynthetic, "summon.synthetic.combat");
		registerItem(itemSummonerAqua, "summon.aqua");
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
		registerItem(itemM240ICU, "gun.m240icu");
		registerItem(itemNostromoFlamethrower, "gun.flamethrower.nostromo");
		registerItem(itemPistol, "gun.pistol");
		registerItem(itemM4, "gun.m4");
		registerItem(itemSniper, "gun.sniper");
		registerItem(itemM41A, "gun.m41a");
		registerItem(itemM56SG, "gun.m56sg");
		registerItem(itemAK47, "gun.ak47");
		registerItem(blackGooBucket, "blackGooBucket");
		registerItem(itemAmmoAR, "ammo.ar");
		registerItem(itemAmmoAC, "ammo.ac");
		registerItem(itemAmmoPistol, "ammo.pistol");
		registerItem(itemAmmoSMG, "ammo.smg");
		registerItem(itemAmmoSniper, "ammo.sniper");
		registerItem(itemFuelTank, "ammo.flamethrower");
		registerItem(itemGrenade, "grenade.m40");
		registerItem(itemIncendiaryGrenade, "grenade.incindiary");
		registerItem(itemWristBlade, "wristblade");
		registerItem(itemPlasmaCaster, "plasmacaster");
		registerItem(itemProximityMine, "mine.laser");
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
		registerItem(itemFlashDrive, "device.nbtdrive");
		registerItem(itemSupplyChute, "device.supplychute");
		registerItem(itemProcessor, "part.processor");
		registerItem(itemSolidStateDrive, "part.solidstatedrive");
		registerItem(itemVoltageRegulator, "part.regulator");
		registerItem(itemCapacitor, "part.capacitor");
		registerItem(itemTransistor, "part.transistor");
		registerItem(itemMotherboard, "part.motherboard");
		registerItem(itemRAM, "part.ram");
		registerItem(itemResistor, "part.resistor");
		registerItem(itemLed, "part.led");
		registerItem(itemDiode, "part.diode");
		registerItem(itemPowerSupply, "part.powersupply");
		registerItem(itemLedDisplay, "part.led.display");
		registerItem(itemPistolStock, "part.pistol.stock");
		registerItem(itemPistolBarrel, "part.pistol.barrel");
		registerItem(itemPistolAction, "part.pistol.action");
		registerItem(itemSniperStock, "part.sniper.stock");
		registerItem(itemSniperBarrel, "part.sniper.barrel");
		registerItem(itemSniperAction, "part.sniper.action");
		registerItem(itemSniperScope, "part.sniper.scope");
		registerItem(itemSniperPeripherals, "part.sniper.peripherals");
		registerItem(itemM41AStock, "part.m41a.stock");
		registerItem(itemM41AAction, "part.m41a.action");
		registerItem(itemM41ABarrel, "part.m41a.barrel");
		registerItem(itemM41APeripherals, "part.m41a.peripherals");
		registerItem(itemM56SGStock, "part.m56sg.stock");
		registerItem(itemM56SGAction, "part.m56sg.action");
		registerItem(itemM56SGBarrel, "part.m56sg.barrel");
		registerItem(itemM56SGSupportFrame, "part.m56sg.supportframe");
		registerItem(itemM56SGAimingModule, "part.m56sg.aimingmodule");
		registerItem(itemM4Stock, "part.m4.stock");
		registerItem(itemM4Action, "part.m4.action");
		registerItem(itemM4Barrel, "part.m4.barrel");
		registerItem(itemAK47Action, "part.ak47.action");
		registerItem(itemAK47Stock, "part.ak47.stock");
		registerItem(itemAK47Barrel, "part.ak47.barrel");
		registerItem(itemIntegratedCircuit, "part.ic");
		registerItem(itemIngotCopper, "ingot.copper");
		registerItem(itemIngotLithium, "ingot.lithium");
		registerItem(itemIngotAluminum, "ingot.aluminum");
		registerItem(itemSilicon, "silicon");
		registerItem(itemCarbon, "carbon");
		registerItem(itemAPC, "apc");
		registerItem(itemMotionTracker, "motiontracker");
		registerItem(itemWristbracerBlades, "wristbracer.blades");
		registerItem(itemPolycarbonate, "polycarbonate");
		registerItem(itemRoyalJelly, "royaljelly");
		registerItem(itemBlackGoo, "blackgoo.item");
		registerItem(itemPhial, "phial.goo");
		registerItem(itemPhialEmpty, "phial.empty");
		registerItem(itemMaintenanceJack, "tool.maintenancejack");
	}
}
