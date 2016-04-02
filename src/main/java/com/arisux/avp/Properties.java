package com.arisux.avp;

import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModMetadata;

public class Properties
{	
	public static final Properties instance = new Properties();
	public final ModContainer CONTAINER = AliensVsPredator.instance().container();
	public final ModMetadata METADATA = CONTAINER.getMetadata();
	public final String DOMAIN = METADATA.modId + ":";
	
	public final int GUI_ASSEMBLER = 0;
	public final int GUI_TURRET = 1;
	public final int GUI_WRISTBRACER = 2;
	public final int GUI_LOCKER = 3;

	public final String DIMENSION_NAME_ACHERON = "LV-426 (Acheron)";
	public final String DIMENSION_ID_ACHERON = "DIM_LV426";
	
	public final String DIMENSION_NAME_VARDA = "LV-223 (Varda)";
	public final String DIMENSION_ID_VARDA = "DIM_LV223";

	public final String SOUND_ALIEN_LIVING = DOMAIN + "xeno.living";
	public final String SOUND_ALIEN_HURT = DOMAIN + "xeno.hurt";
	public final String SOUND_ALIEN_DEATH = DOMAIN + "xeno.death";
	public final String SOUND_PRAETORIAN_HURT = DOMAIN + "praetorian.hurt";
	public final String SOUND_PRAETORIAN_LIVING = DOMAIN + "praetorian.living";
	public final String SOUND_PRAETORIAN_DEATH = DOMAIN + "xeno.death";
	public final String SOUND_QUEEN_HURT = DOMAIN + "queen.hurt";
	public final String SOUND_QUEEN_LIVING = DOMAIN + "queen.living";
	public final String SOUND_QUEEN_DEATH = DOMAIN + "queen.death";
	public final String SOUND_SPITTER_HURT = DOMAIN + "praetorian.hurt";
	public final String SOUND_SPITTER_LIVING = DOMAIN + "praetorian.living";
	public final String SOUND_SPITTER_DEATH = DOMAIN + "xeno.death";
	public final String SOUND_WARRIOR_HURT = DOMAIN + "xeno.hurt";
	public final String SOUND_WARRIOR_LIVING = DOMAIN + "xeno.living";
	public final String SOUND_WARRIOR_DEATH = DOMAIN + "xeno.death";
	public final String SOUND_CRUSHER_HURT = DOMAIN + "praetorian.hurt";
	public final String SOUND_CRUSHER_LIVING = DOMAIN + "praetorian.living";
	public final String SOUND_CRUSHER_DEATH = DOMAIN + "xeno.death";
	public final String SOUND_CHESTBURSTER_DEATH = DOMAIN + "chestburster.death";
	public final String SOUND_FACEHUGGER_DEATH = DOMAIN + "facehugger.death";
	public final String SOUND_MARINE_HURT = DOMAIN + "marine.hurt";
	public final String SOUND_MARINE_DEATH = DOMAIN + "marine.death";
	public final String SOUND_YAUTJA_LIVING = DOMAIN + "predator.living";
	public final String SOUND_YAUTJA_HURT = DOMAIN + "predator.hurt";
	public final String SOUND_YAUTJA_DEATH = DOMAIN + "predator.death";
	public final String SOUND_WEAPON_FLAMETHROWER = DOMAIN + "weapon.flamethrower";
	public final String SOUND_WEAPON_GUNSHOT = DOMAIN + "weapon.gunshot";
	public final String SOUND_WEAPON_PLASMACASTER = DOMAIN + "weapon.plasmacaster";
	public final String SOUND_WEAPON_WRISTBLADES = DOMAIN + "weapon.blades";
	public final String SOUND_WEAPON_SNIPER = DOMAIN + "weapon.sniper";
	public final String SOUND_WEAPON_PULSERIFLE = DOMAIN + "weapon.pulserifle";
	public final String SOUND_WEAPON_M56SG = DOMAIN + "weapon.m56sg";
	public final String SOUND_MOTIONTRACKER_PING = DOMAIN + "motiontracker.ping";
	public final String SOUND_MOTIONTRACKER_PONG = DOMAIN + "motiontracker.pong";
}