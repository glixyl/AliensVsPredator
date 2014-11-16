package com.arisux.avp;

import java.io.File;
import java.util.HashMap;

import net.minecraftforge.common.config.Configuration;

import com.arisux.airi.AIRI;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Settings
{
	private Configuration config;

	public HashMap<String, Integer> spawnList = new HashMap<String, Integer>(), entityList = new HashMap<String, Integer>();
	public String CATEGORY_OTHER = "ETC", CATEGORY_IDS = "IDS";

	public boolean explosions, hiveTick, hiveSpawnsMobs, updaterEnabled, debugToolsEnabled;
	public String updateStringUrl;

	@EventHandler
	public void preInitialize(FMLPreInitializationEvent evt)
	{
		AIRI.instance().remappingApi.registerRemappedMod("AIRI", "avp", "com.arisux.avp.AliensVsPredator");
		AIRI.instance().remappingApi.registerRemappedMod("AliensVsPredator", "avp", "com.arisux.avp.AliensVsPredator");
		
		config = new Configuration(new File(evt.getModConfigurationDirectory(), "AVP.cfg"));

		try
		{
			config.addCustomCategoryComment(CATEGORY_OTHER, "Configuration for spawn rates of the mod's mobs");
			config.addCustomCategoryComment(CATEGORY_IDS, "Configuration for the mod's entity IDs");
			config.addCustomCategoryComment(CATEGORY_IDS, "WARNING: ONLY MODIFY THIS IF YOU KNOW WHAT YOU ARE DOING, YOU CAN BREAK WORLD SAVES AND/OR SERVERS.");
			config.addCustomCategoryComment(CATEGORY_OTHER, "Other mod configuration options");
			config.addCustomCategoryComment(CATEGORY_OTHER, "WARNING: WHEN THE VANILLA SPAWN SYSTEM IS IN USE, THE SPAWN RATES ARE NOT CONFIGURABLE VIA CONFIG!");
			config.load();

			entityList.put("DRONE", config.get(CATEGORY_IDS, "DRONE", 102).getInt());
			entityList.put("WARRIOR", config.get(CATEGORY_IDS, "WARRIOR", 103).getInt());
			entityList.put("SPITTER", config.get(CATEGORY_IDS, "SPITTER", 104).getInt());
			entityList.put("CRUSHER", config.get(CATEGORY_IDS, "CRUSHER", 105).getInt());
			entityList.put("PRAETORIAN", config.get(CATEGORY_IDS, "PRAETORIAN", 106).getInt());
			entityList.put("QUEEN", config.get(CATEGORY_IDS, "QUEEN", 107).getInt());
			entityList.put("OVAMORPH", config.get(CATEGORY_IDS, "OVAMORPH", 108).getInt());
			entityList.put("FACEHUGGER", config.get(CATEGORY_IDS, "FACEHUGGER", 109).getInt());
			entityList.put("CHESTBUSTER", config.get(CATEGORY_IDS, "CHESTBUSTER", 110).getInt());
			entityList.put("ROYAL_FACEHUGGER", config.get(CATEGORY_IDS, "ROYAL_FACEHUGGER", 111).getInt());
			entityList.put("MARINE", config.get(CATEGORY_IDS, "MARINE", 112).getInt());
			entityList.put("YAUTJA", config.get(CATEGORY_IDS, "YAUTJA", 113).getInt());
			entityList.put("PREDALIEN", config.get(CATEGORY_IDS, "PREDALIEN", 114).getInt());
			entityList.put("AQUA", config.get(CATEGORY_IDS, "AQUA", 115).getInt());

			entityList.put("CELTIC_SPEAR", config.get(CATEGORY_IDS, "CELTIC_SPEAR", 1512).getInt());
			entityList.put("PROXIMITY_MINE", config.get(CATEGORY_IDS, "PROXIMITY_MINE", 1513).getInt());
			entityList.put("PLASMA", config.get(CATEGORY_IDS, "PLASMA", 1514).getInt());
			entityList.put("GRENADE", config.get(CATEGORY_IDS, "GRENADE", 1515).getInt());
			entityList.put("FLAME", config.get(CATEGORY_IDS, "FLAME", 1516).getInt());
			entityList.put("FXACID", config.get(CATEGORY_IDS, "FXACID", 1517).getInt());
			entityList.put("AIACID", config.get(CATEGORY_IDS, "AIACID", 1518).getInt());
			entityList.put("DISC", config.get(CATEGORY_IDS, "DISC", 1519).getInt());
			entityList.put("SHURIKEN", config.get(CATEGORY_IDS, "SHURIKEN", 1520).getInt());
			entityList.put("TURRETENTITY", config.get(CATEGORY_IDS, "TURRETENTITY", 1521).getInt());

			explosions = config.get(CATEGORY_OTHER, "EXPLOSION_BLOCK_DAMAGE", true).getBoolean(true);
			hiveTick = config.get(CATEGORY_OTHER, "HIVE_TICK", true, "Toggle spreading of hive resin.").getBoolean(true);
			hiveSpawnsMobs = config.get(CATEGORY_OTHER, "HIVE_SPAWNS_MOBS", true, "Toggle spawning of xenomorphs from hive nodes").getBoolean(true);
			updaterEnabled = config.get(CATEGORY_OTHER, "UPDATER_ENABLED", true, "Toggle the mod's updater.").getBoolean(true);
			updateStringUrl = config.get(CATEGORY_OTHER, "UPDATE_STRING_URL", AliensVsPredator.properties().getUpdateStringUrl(), "The URL that the updater uses to check for mod updates. If it changes in the future, this can be changed to the new URL to fix any problems.").getString();
			debugToolsEnabled = config.get(CATEGORY_OTHER, "DEBUG_TOOLS", false, "Toggle the debugging tools.").getBoolean(false);
		} finally
		{
			config.save();
		}
	}

	public boolean areExplosionsEnabled()
	{
		return this.explosions;
	}

	public boolean doesHiveTick()
	{
		return this.hiveTick;
	}

	public boolean doesHiveSpawnMobs()
	{
		return this.hiveSpawnsMobs;
	}
}