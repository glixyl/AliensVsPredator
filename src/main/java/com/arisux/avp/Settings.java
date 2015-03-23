package com.arisux.avp;

import java.io.File;
import java.util.HashMap;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.ModUtil;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Settings
{
	public static final Settings instance = new Settings();
	public HashMap<String, Integer> entityList = new HashMap<String, Integer>();
	private final String CATEGORY_OTHER = "ETC";
	private final String CATEGORY_IDS = "IDS";
	private final String CATEGORY_URLS = "URLS";
	private String mainDomain;
	private String devDomain;
	private String urlUpdater;
	private String urlChangelog;
	private String urlFeedbackSubmit;
	private String urlFeedbackValidation;
	private String urlSkins;
	private String urlSkinAk47;
	private String urlSkinM4;
	private String urlSkinM41a;
	private String urlSkinM56sg;
	private String urlSkinSniper;
	private boolean explosions;
	private boolean updater;
	private boolean debugTools;
	private int idStart = 101;

	@EventHandler
	public void preInitialize(FMLPreInitializationEvent evt)
	{
		AIRI.remappingApi().registerRemappedMod("AIRI", "avp", "com.arisux.avp.AliensVsPredator");
		AIRI.remappingApi().registerRemappedMod("AliensVsPredator", "avp", "com.arisux.avp.AliensVsPredator");
		
		File configFile = new File(evt.getModConfigurationDirectory(), "AliensVsPredator.cfg");
		Configuration config = new Configuration(configFile);
		verifyConfigVersion(evt, configFile, config);

		try
		{
			config.addCustomCategoryComment(CATEGORY_IDS, "Configuration for the mod's entity IDs");
			config.addCustomCategoryComment(CATEGORY_IDS, "WARNING: ONLY MODIFY THIS IF YOU KNOW WHAT YOU ARE DOING, YOU CAN BREAK WORLD SAVES AND/OR SERVERS.");
			config.addCustomCategoryComment(CATEGORY_OTHER, "Other configuration options");
			config.load();

			mainDomain = config.get(CATEGORY_URLS, "DOMAIN_MAIN", "http://avp.arisux.x10.mx", "").getString();
			devDomain = config.get(CATEGORY_URLS, "DOMAIN_DEV", "http://localhost:10/avp", "").getString();
			urlUpdater = config.get(CATEGORY_URLS, "UPDATE_STRING_URL", "/latest.txt", "").getString();
			urlChangelog = config.get(CATEGORY_URLS, "URL_CHANGELOG", "/changelog.txt", "").getString();
			urlFeedbackSubmit = config.get(CATEGORY_URLS, "URL_AVP_FEEDBACK_SUBMIT", "/page/beta/submit.php?user=%s&uuid=%s&info=%s", "").getString();
			urlFeedbackValidation = config.get(CATEGORY_URLS, "URL_FEEDBACK_VALIDATION", "/page/beta/validate.php?uuid=%s", "").getString();
			urlSkins = config.get(CATEGORY_URLS, "URL_SKINS", "/page/skins", "").getString();
			urlSkinAk47 = config.get(CATEGORY_URLS, "URL_SKIN_AK47", "/ak47/%s.png", "").getString();
			urlSkinM4 = config.get(CATEGORY_URLS, "URL_SKIN_M4", "/m4/%s.png", "").getString();
			urlSkinM41a = config.get(CATEGORY_URLS, "URL_SKIN_M41A", "/m4a1/%s.png", "").getString();
			urlSkinM56sg = config.get(CATEGORY_URLS, "URL_SKIN_M56SG", "/m56sg/%s.png", "").getString();
			urlSkinSniper = config.get(CATEGORY_URLS, "URL_SKIN_SNIPER", "/sniper/%s.png", "").getString();
			
			explosions = config.get(CATEGORY_OTHER, "EXPLOSION_BLOCK_DAMAGE", true).getBoolean(true);
			updater = config.get(CATEGORY_OTHER, "UPDATER_ENABLED", true, "Toggle the mod's updater.").getBoolean(true);
			debugTools = config.get(CATEGORY_OTHER, "DEBUG_TOOLS", false, "Toggle the debugging tools.").getBoolean(false);

			entityList.put("DRONE", config.get(CATEGORY_IDS, "DRONE", idStart++).getInt());
			entityList.put("WARRIOR", config.get(CATEGORY_IDS, "WARRIOR", idStart++).getInt());
			entityList.put("SPITTER", config.get(CATEGORY_IDS, "SPITTER", idStart++).getInt());
			entityList.put("CRUSHER", config.get(CATEGORY_IDS, "CRUSHER", idStart++).getInt());
			entityList.put("PRAETORIAN", config.get(CATEGORY_IDS, "PRAETORIAN", idStart++).getInt());
			entityList.put("QUEEN", config.get(CATEGORY_IDS, "QUEEN", idStart++).getInt());
			entityList.put("OVAMORPH", config.get(CATEGORY_IDS, "OVAMORPH", idStart++).getInt());
			entityList.put("FACEHUGGER", config.get(CATEGORY_IDS, "FACEHUGGER", idStart++).getInt());
			entityList.put("CHESTBUSTER", config.get(CATEGORY_IDS, "CHESTBUSTER", idStart++).getInt());
			entityList.put("ROYAL_FACEHUGGER", config.get(CATEGORY_IDS, "ROYAL_FACEHUGGER", idStart++).getInt());
			entityList.put("MARINE", config.get(CATEGORY_IDS, "MARINE", idStart++).getInt());
			entityList.put("YAUTJA", config.get(CATEGORY_IDS, "YAUTJA", idStart++).getInt());
			entityList.put("PREDALIEN", config.get(CATEGORY_IDS, "PREDALIEN", idStart++).getInt());
			entityList.put("AQUA", config.get(CATEGORY_IDS, "AQUA", idStart++).getInt());
			entityList.put("COMBAT_SYNTHETIC", config.get(CATEGORY_IDS, "COMBAT_SYNTHETIC", idStart++).getInt());
			entityList.put("PROTOMORPH", config.get(CATEGORY_IDS, "PROTOMORPH", idStart++).getInt());
			entityList.put("HAMMERPEDE", config.get(CATEGORY_IDS, "HAMMERPEDE", idStart++).getInt());

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
			entityList.put("WRISTBRACERNUKE", config.get(CATEGORY_IDS, "WRISTBRACERNUKE", 1522).getInt());
		} finally
		{
			config.save();
		}
	}
	
	private void verifyConfigVersion(FMLPreInitializationEvent evt, File configFile, Configuration config)
	{
		Property versionProperty = config.get(CATEGORY_OTHER, "VERSION", AliensVsPredator.instance().container().getVersion());
		
		if (versionProperty != null && !versionProperty.getString().equalsIgnoreCase(AliensVsPredator.instance().container().getVersion()))
		{
			if (configFile.exists())
			{
				File renamedConfig = new File(evt.getModConfigurationDirectory(), configFile.getName().replace(".cfg", "-" + versionProperty.getString() + ".cfg"));
				System.out.println("[ALIENSVSPREDATOR/CONFIG] Renaming " + configFile + " to " + renamedConfig);
				configFile.renameTo(renamedConfig);
				configFile.delete();
				
				this.preInitialize(evt);
			}
		}
	}

	public boolean areExplosionsEnabled()
	{
		return this.explosions;
	}

	public boolean isUpdaterEnabled()
	{
		return this.updater;
	}
	
	public boolean areDebugToolsEnabled()
	{
		return this.debugTools;
	}
	
	public String getUpdateStringUrl()
	{
		return this.urlUpdater;
	}
	
	public String getServer()
	{
		return ModUtil.isDevEnvironment() ? this.devDomain : this.mainDomain;
	}
	
	public String getUrlUpdater()
	{
		return getServer() + urlUpdater;
	}
	
	public String getUrlChangelog()
	{
		return getServer() + urlChangelog;
	}
	
	public String getUrlFeedbackSubmit()
	{
		return getServer() + urlFeedbackSubmit;
	}
	
	public String getUrlFeedbackValidation()
	{
		return getServer() + urlFeedbackValidation;
	}
	
	public String getUrlSkinAk47()
	{
		return getServer() + urlSkins + urlSkinAk47;
	}
	
	public String getUrlSkinM4()
	{
		return getServer() + urlSkins + urlSkinM4;
	}
	
	public String getUrlSkinM41a()
	{
		return getServer() + urlSkins + urlSkinM41a;
	}
	
	public String getUrlSkinM56sg()
	{
		return getServer() + urlSkins + urlSkinM56sg;
	}
	
	public String getUrlSkinSniper()
	{
		return getServer() + urlSkins + urlSkinSniper;
	}
}