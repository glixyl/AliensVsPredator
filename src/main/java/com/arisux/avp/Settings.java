package com.arisux.avp;

import java.io.File;
import java.util.HashMap;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import com.arisux.airi.AIRI;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Settings
{
	private Configuration config;

	public HashMap<String, Integer> entityList = new HashMap<String, Integer>();
	public String CATEGORY_OTHER = "ETC", CATEGORY_IDS = "IDS";

	private boolean explosions, updaterEnabled, debugToolsEnabled;
	private String updateStringUrl;
	private int entityListIDs = 101;

	@EventHandler
	public void preInitialize(FMLPreInitializationEvent evt)
	{
		AIRI.remappingApi().registerRemappedMod("AIRI", "avp", "com.arisux.avp.AliensVsPredator");
		AIRI.remappingApi().registerRemappedMod("AliensVsPredator", "avp", "com.arisux.avp.AliensVsPredator");
		
		File configFile = new File(evt.getModConfigurationDirectory(), "AliensVsPredator.cfg");
		config = new Configuration(configFile);

		try
		{
			Property versionProperty = config.get(CATEGORY_OTHER, "VERSION", AliensVsPredator.instance().container().getVersion());
			
			if (!versionProperty.getString().equalsIgnoreCase(AliensVsPredator.instance().container().getVersion()))
			{
				if (configFile.exists())
				{
					configFile.renameTo(new File(configFile.getName().replace(".cfg", ".cfg.bak")));
					this.preInitialize(evt);
//					try
//					{
//						config = Configuration.class.getConstructor(File.class).newInstance(new Object[] { configFile });
//					}
//					catch (Exception e)
//					{
//						AIRI.logger.bug("Could not construct a new configuration file.");
//						e.printStackTrace();
//					}
				}
			}
			
			config.addCustomCategoryComment(CATEGORY_IDS, "Configuration for the mod's entity IDs");
			config.addCustomCategoryComment(CATEGORY_IDS, "WARNING: ONLY MODIFY THIS IF YOU KNOW WHAT YOU ARE DOING, YOU CAN BREAK WORLD SAVES AND/OR SERVERS.");
			config.addCustomCategoryComment(CATEGORY_OTHER, "Other mod configuration options");
			config.load();

			entityList.put("DRONE", config.get(CATEGORY_IDS, "DRONE", entityListIDs++).getInt());
			entityList.put("WARRIOR", config.get(CATEGORY_IDS, "WARRIOR", entityListIDs++).getInt());
			entityList.put("SPITTER", config.get(CATEGORY_IDS, "SPITTER", entityListIDs++).getInt());
			entityList.put("CRUSHER", config.get(CATEGORY_IDS, "CRUSHER", entityListIDs++).getInt());
			entityList.put("PRAETORIAN", config.get(CATEGORY_IDS, "PRAETORIAN", entityListIDs++).getInt());
			entityList.put("QUEEN", config.get(CATEGORY_IDS, "QUEEN", entityListIDs++).getInt());
			entityList.put("OVAMORPH", config.get(CATEGORY_IDS, "OVAMORPH", entityListIDs++).getInt());
			entityList.put("FACEHUGGER", config.get(CATEGORY_IDS, "FACEHUGGER", entityListIDs++).getInt());
			entityList.put("CHESTBUSTER", config.get(CATEGORY_IDS, "CHESTBUSTER", entityListIDs++).getInt());
			entityList.put("ROYAL_FACEHUGGER", config.get(CATEGORY_IDS, "ROYAL_FACEHUGGER", entityListIDs++).getInt());
			entityList.put("MARINE", config.get(CATEGORY_IDS, "MARINE", entityListIDs++).getInt());
			entityList.put("YAUTJA", config.get(CATEGORY_IDS, "YAUTJA", entityListIDs++).getInt());
			entityList.put("PREDALIEN", config.get(CATEGORY_IDS, "PREDALIEN", entityListIDs++).getInt());
			entityList.put("AQUA", config.get(CATEGORY_IDS, "AQUA", entityListIDs++).getInt());
			entityList.put("COMBAT_SYNTHETIC", config.get(CATEGORY_IDS, "COMBAT_SYNTHETIC", entityListIDs++).getInt());

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
			updaterEnabled = config.get(CATEGORY_OTHER, "UPDATER_ENABLED", true, "Toggle the mod's updater.").getBoolean(true);
			updateStringUrl = config.get(CATEGORY_OTHER, "UPDATE_STRING_URL", AliensVsPredator.instance().container().getMetadata().updateUrl, "The URL that the updater uses to check for mod updates. If it changes in the future, this can be changed to the new URL to fix any problems.").getString();
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

	public boolean isUpdaterEnabled()
	{
		return this.updaterEnabled;
	}
	
	public boolean areDebugToolsEnabled()
	{
		return this.debugToolsEnabled;
	}
	
	public String getUpdateStringUrl()
	{
		return updateStringUrl;
	}
}