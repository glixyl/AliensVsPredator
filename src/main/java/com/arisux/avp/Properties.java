package com.arisux.avp;

import java.util.Arrays;
import java.util.List;

import com.arisux.airi.engine.ModEngine;
import com.arisux.airi.lib.util.ModProperties;

public class Properties extends ModProperties
{
	public Properties(String modid)
	{
		super(modid);
	}
	@Override
	public String getId()
	{
		return ID;
	}
	@Override
	public String getName()
	{
		return NAME;
	}
	@Override
	public String getVersion()
	{
		return VERSION;
	}
	@Override
	public String getDescription()
	{
		return "Contains content from several movie series, including the Alien, Predator, Aliens Vs Predator, and Prometheus series. The mod adds countless blocks, items, and mobs to the world of Minecraft for you to start Building Better Worlds(TM). ";
	}
	@Override
	public String getCredits()
	{
		return "The Minecraft Community";
	}
	@Override
	public List<String> getAuthors()
	{
		return Arrays.asList("Ri5ux", "Ieuan");
	}
	@Override
	public String getUrl()
	{
		return SERVER_ADDRESS + "/page/mods/aliensvspredator";
	}
	@Override
	public String getUpdateStringUrl()
	{
		return getUrl() + "/latest.txt";
	}
	@Override
	public String getChangelogUrl()
	{
		return getUrl() + "/changelog.txt";
	}
	
	public static final String ID = "avp";
	public static final String NAME = "AliensVsPredator";
	public static final String VERSION = "4.0.0";
	public final String PUBLIC_SERVER_ADDRESS = "http://arisux.com";
	public final String LOCAL_SERVER_ADDRESS = "http://localhost:8080";
	public final String SERVER_ADDRESS = ModEngine.isDevelopmentEnvironment() ? LOCAL_SERVER_ADDRESS : PUBLIC_SERVER_ADDRESS;
	public final String URL_SUBMIT_FEEDBACK = SERVER_ADDRESS + "/page/beta/submit.php?user=%s&uuid=%s&info=%s";
	public final String URL_SKIN_BASE = getUrl() + "/skins/";
	public final String URL_SKIN_AK47 = URL_SKIN_BASE + "ak47/%s.png";
	public final String URL_SKIN_M4 = URL_SKIN_BASE + "m4/%s.png";
	public final String URL_SKIN_M41A = URL_SKIN_BASE + "m4a1/%s.png";
	public final String URL_SKIN_M56SG = URL_SKIN_BASE + "m56sg/%s.png";
	public final String URL_SKIN_SNIPER = URL_SKIN_BASE + "sniper/%s.png";
	public final String URL_SKIN_SNIPER_SCOPE = URL_SKIN_BASE + "sniper-zoom/%s.png";

	public final int GUI_ASSEMBLER = 0;
	public final int GUI_TURRET = 1;
	public final int GUI_WRISTBRACER = 2;
	public final int DIMENSION_ID_LV223 = -7;

	public final String DIMENSION_NAME_LV223 = "LV-223 (Varda)";
	public final String DIMENSION_GLOBAL_LV223 = "DIM_LV223";

	public final String TEXTURE_PATH_LV223_GAS_PLANET = getDomain() + "textures/misc/gas-planet-zeta-2-reticuli.png";
	public final String TEXTURE_PATH_TITANIUM1 = getDomain() + "textures/armor/titanium_1.png";
	public final String TEXTURE_PATH_TITANIUM2 = getDomain() + "textures/armor/titanium_2.png";
	public final String TEXTURE_PATH_PRESSURESUIT1 = getDomain() + "textures/armor/suit_1.png";
	public final String TEXTURE_PATH_PRESSURESUIT2 = getDomain() + "textures/armor/suit_2.png";
	public final String TEXTURE_PATH_XENO1 = getDomain() + "textures/armor/xeno_1.png";
	public final String TEXTURE_PATH_XENO2 = getDomain() + "textures/armor/xeno_2.png";
	public final String TEXTURE_PATH_MARINE1 = getDomain() + "textures/armor/marine_1.png";
	public final String TEXTURE_PATH_MARINE2 = getDomain() + "textures/armor/marine_2.png";
	public final String TEXTURE_PATH_BULLET = getDomain() + "textures/misc/renderbullet.png";
	public final String TEXTURE_PATH_SPEAR = getDomain() + "textures/misc/renderspear.png";
	public final String TEXTURE_PATH_FX_ACID = getDomain() + "textures/misc/renderacid.png";
	public final String TEXTURE_PATH_DISC = getDomain() + "textures/misc/disc.png";
	public final String TEXTURE_PATH_SHURIKEN = getDomain() + "textures/misc/shuriken.png";
	public final String TEXTURE_PATH_BLUR_CELTIC_HUD = getDomain() + "textures/misc/celtic-helm-overlay.png";
	public final String TEXTURE_PATH_BLUR_MARINE_HUD = getDomain() + "textures/misc/marine-helm-overlay.png";
	public final String TEXTURE_PATH_BLUR_FACEHUGGER = getDomain() + "textures/misc/facehugger.png";
	public final String TEXTURE_PATH_BLUR_CHESTBURSTER_EMERGE = getDomain() + "textures/misc/chestburster-emerge-overlay.png";
	public final String TEXTURE_PATH_BLUR_GUNSCOPE = getDomain() + "textures/misc/scope.png";
	public final String TEXTURE_PATH_GRENADE = getDomain() + "textures/items/itemGrenade.png";
	public final String TEXTURE_PATH_WALL_MINE = getDomain() + "textures/items/itemWallMine.png";
	public final String TEXTURE_PATH_WRISTBLADES = getDomain() + "textures/items/models/wristblade.png";
	public final String TEXTURE_PATH_PULSERIFLE = getDomain() + "textures/items/models/pulserifle.png";
	public final String TEXTURE_PATH_M56SG = getDomain() + "textures/items/models/m56sg.png";
	public final String TEXTURE_PATH_AK47 = getDomain() + "textures/items/models/ak-47.png";
	public final String TEXTURE_PATH_M4 = getDomain() + "textures/items/models/m4.png";
	public final String TEXTURE_PATH_SNIPER = getDomain() + "textures/items/models/sniper.png";
	public final String TEXTURE_PATH_SNIPER_SCOPED = getDomain() + "textures/items/models/sniper-scoped.png";
	public final String TEXTURE_PATH_MOTIONTRACKER = getDomain() + "textures/items/models/motiontracker.png";
	public final String TEXTURE_PATH_TURRET = getDomain() + "textures/tile/turret.png";
	public final String TEXTURE_PATH_WORKSTATION = getDomain() + "textures/tile/workstation.png";
	public final String TEXTURE_PATH_WORKSTATION_ON = getDomain() + "textures/tile/workstation-on.png";
	public final String TEXTURE_PATH_GENERATOR = getDomain() + "textures/tile/generator.png";
	public final String TEXTURE_PATH_CABLE = getDomain() + "textures/tile/cable.png";
	public final String TEXTURE_PATH_NETWORKLIGHT = getDomain() + "textures/tile/networklight.png";
	public final String TEXTURE_PATH_BLASTDOOR = getDomain() + "textures/tile/blastdoor.png";
	public final String TEXTURE_PATH_WORKLIGHT = getDomain() + "textures/tile/worklight.png";
	public final String TEXTURE_PATH_CHESTBUSTER = getDomain() + "textures/mob/chestbuster.png";
	public final String TEXTURE_PATH_FACEHUGGER = getDomain() + "textures/mob/facehugger.png";
	public final String TEXTURE_PATH_ROYALFACEHUGGER = getDomain() + "textures/mob/royalfacehugger.png";
	public final String TEXTURE_PATH_STEVE = "/mob/char.png";
	public final String TEXTURE_PATH_MARINE = getDomain() + "textures/mob/marine.png";
	public final String TEXTURE_PATH_OVAMORPH = getDomain() + "textures/mob/alienegg.png";
	public final String TEXTURE_PATH_PREDALIEN = getDomain() + "textures/mob/predalien.png";
	public final String TEXTURE_PATH_PRAETORIAN = getDomain() + "textures/mob/praetorian.png";
	public final String TEXTURE_PATH_WARRIOR = getDomain() + "textures/mob/warrior.png";
	public final String TEXTURE_PATH_WARRIOR_BLOOD = getDomain() + "textures/mob/warrior_blood.png";
	public final String TEXTURE_PATH_DRONE_BASIC = getDomain() + "textures/mob/drone_basic.png";
	public final String TEXTURE_PATH_DRONE_BASIC_BLOOD = getDomain() + "textures/mob/drone_basic_blood.png";
	public final String TEXTURE_PATH_DRONE_ADVANCED = getDomain() + "textures/mob/drone_advanced.png";
	public final String TEXTURE_PATH_DRONE_ADVANCED_BLOOD = getDomain() + "textures/mob/drone_advanced_blood.png";
	public final String TEXTURE_PATH_AQUA_XENOMORPH = getDomain() + "textures/mob/aqua.png";
	public final String TEXTURE_PATH_AQUA_XENOMORPH_GLOW = getDomain() + "textures/mob/aqua_glow.png";
	public final String TEXTURE_PATH_XENOQUEEN = getDomain() + "textures/mob/queen.png";
	public final String TEXTURE_PATH_YAUTJA = getDomain() + "textures/mob/yautja.png";
	public final String TEXTURE_PATH_SPITTER = getDomain() + "textures/mob/spitter.png";
	public final String TEXTURE_PATH_SPITTER_GLOW = getDomain() + "textures/mob/spitter_glow.png";
	public final String TEXTURE_PATH_HIVE_NODE = getDomain() + "textures/tile/node.png";
	public final String TEXTURE_PATH_CRUSHER = getDomain() + "textures/mob/crusher.png";
	public final String TEXTURE_PATH_GUI_BASIC = getDomain() + "textures/gui/background.png";
	public final String TEXTURE_PATH_GUI_TURRET = getDomain() + "textures/gui/turret.png";
	public final String TEXTURE_PATH_GUI_WRISTBRACER = getDomain() + "textures/gui/wristbracer.png";
	public final String TEXTURE_PATH_ICON_AMMO = getDomain() + "textures/misc/icon-ammo.png";
	public final String TEXTURE_PATH_MOTIONTRACKER_BG = getDomain() + "textures/misc/motiontracker/background.png";
	public final String TEXTURE_PATH_MOTIONTRACKER_FG = getDomain() + "textures/misc/motiontracker/foreground.png";
	public final String TEXTURE_PATH_MOTIONTRACKER_PING = getDomain() + "textures/misc/motiontracker/ping.png";
	public final String TEXTURE_PATH_MOTIONTRACKER_S1 = getDomain() + "textures/misc/motiontracker/sweep1.png";
	public final String TEXTURE_PATH_MOTIONTRACKER_S2 = getDomain() + "textures/misc/motiontracker/sweep2.png";
	public final String TEXTURE_PATH_MOTIONTRACKER_S3 = getDomain() + "textures/misc/motiontracker/sweep3.png";
	public final String TEXTURE_PATH_MOTIONTRACKER_S4 = getDomain() + "textures/misc/motiontracker/sweep4.png";
	public final String TEXTURE_PATH_MOTIONTRACKER_S5 = getDomain() + "textures/misc/motiontracker/sweep5.png";
	public final String TEXTURE_PATH_MOTIONTRACKER_S6 = getDomain() + "textures/misc/motiontracker/sweep6.png";
	
	public final String SOUND_ALIEN_LIVING = getDomain() + "xeno.living";
	public final String SOUND_ALIEN_HURT = getDomain() + "xeno.hurt";
	public final String SOUND_ALIEN_DEATH = getDomain() + "xeno.death";
	public final String SOUND_PRAETORIAN_HURT = getDomain() + "praetorian.hurt";
	public final String SOUND_PRAETORIAN_LIVING = getDomain() + "praetorian.living";
	public final String SOUND_PRAETORIAN_DEATH = getDomain() + "xeno.death";
	public final String SOUND_QUEEN_HURT = getDomain() + "queen.hurt";
	public final String SOUND_QUEEN_LIVING = getDomain() + "queen.living";
	public final String SOUND_QUEEN_DEATH = getDomain() + "queen.death";
	public final String SOUND_SPITTER_HURT = getDomain() + "praetorian.hurt";
	public final String SOUND_SPITTER_LIVING = getDomain() + "praetorian.living";
	public final String SOUND_SPITTER_DEATH = getDomain() + "xeno.death";
	public final String SOUND_WARRIOR_HURT = getDomain() + "xeno.hurt";
	public final String SOUND_WARRIOR_LIVING = getDomain() + "xeno.living";
	public final String SOUND_WARRIOR_DEATH = getDomain() + "xeno.death";
	public final String SOUND_CRUSHER_HURT = getDomain() + "praetorian.hurt";
	public final String SOUND_CRUSHER_LIVING = getDomain() + "praetorian.living";
	public final String SOUND_CRUSHER_DEATH = getDomain() + "xeno.death";
	public final String SOUND_CHESTBURSTER_DEATH = getDomain() + "chestburster.death";
	public final String SOUND_FACEHUGGER_DEATH = getDomain() + "facehugger.death";
	public final String SOUND_MARINE_HURT = getDomain() + "marine.hurt";
	public final String SOUND_MARINE_DEATH = getDomain() + "marine.death";
	public final String SOUND_YAUTJA_LIVING = getDomain() + "predator.living";
	public final String SOUND_YAUTJA_HURT = getDomain() + "predator.hurt";
	public final String SOUND_YAUTJA_DEATH = getDomain() + "predator.death";
	public final String SOUND_WEAPON_FLAMETHROWER = getDomain() + "weapon.flamethrower";
	public final String SOUND_WEAPON_GUNSHOT = getDomain() + "weapon.gunshot";
	public final String SOUND_WEAPON_PLASMACASTER = getDomain() + "weapon.plasmacaster";
	public final String SOUND_WEAPON_WRISTBLADES = getDomain() + "weapon.blades";
	public final String SOUND_WEAPON_SNIPER = getDomain() + "weapon.sniper";
	public final String SOUND_WEAPON_PULSERIFLE = getDomain() + "weapon.pulserifle";
	public final String SOUND_WEAPON_M56SG = getDomain() + "weapon.m56sg";
	public final String SOUND_MOTIONTRACKER_PING = getDomain() + "motiontracker.ping";
	public final String SOUND_MOTIONTRACKER_PONG = getDomain() + "motiontracker.pong";
	
	public final String LANG_WRISTBRACER_DETMODENOTIFY = "gui.wristbracer.notify.detmode";
	public final String LANG_WRISTBRACER_ITEMSLOSTWARNING = "gui.wristbracer.warning.itemslost";
	public final String LANG_BETA_FEEDBACK_SUBMIT_TITLE = "gui.beta.feedback.submit.title";
	public final String LANG_BETA_FEEDBACK_CHARSLEFT_TITLE = "gui.beta.feedback.charsleft.title";
	public final String LANG_BETA_FEEDBACK_INFO = "gui.beta.feedback.info";
	public final String LANG_BETA_FEEDBACK_SUBMIT_ERROR = "gui.beta.feedback.info.submit.error";
	public final String LANG_BETA_FEEDBACK_NOTIFY_SPAM_PREVENTION = "gui.beta.feedback.info.spamprevention";
	public final String LANG_BETA_FEEDBACK_SUBMIT_THANKS = "gui.beta.feedback.info.submit.thanks";
	public final String LANG_BETA_FEEDBACK_SUBMIT_ERROR_TITLE = "gui.beta.feedback.info.submit.error.title";
	public final String LANG_BETA_FEEDBACK_NOTIFY_SPAM_PREVENTION_TITLE = "gui.beta.feedback.info.spamprevention.title";
	public final String LANG_BETA_FEEDBACK_SUBMIT_THANKS_TITLE = "gui.beta.feedback.info.submit.thanks";
	public final String LANG_BETA_FEEDBACK_SUBMIT_BUTTON = "gui.beta.feedback.info.submit.button";
}