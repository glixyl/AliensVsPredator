package com.arisux.avp;

import java.io.File;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.ModUtil;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Settings
{
	public static final Settings instance = new Settings();
    private final String CATEGORY_OTHER = "ETC";
	private final String CATEGORY_IDS = "IDS";
	private final String CATEGORY_URLS = "URLS";
    private final String CATEGORY_DIM = "DIMENSIONS";
    private final String CATEGORY_BIOMES = "BIOMES";
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
	private boolean explosionsEnabled;
	private boolean updaterEnabled;
	private boolean debugToolsEnabled;
	private boolean nukesEnabled;
	private int dimVarda;
	private int dimAcheron;
	private int biomeVarda;
	private int biomeAcheron;

	@EventHandler
	public void preInitialize(FMLPreInitializationEvent evt)
	{
		AIRI.remappingApi().registerRemappedMod("AliensVsPredator", AliensVsPredator.ID, "com.arisux.avp.AliensVsPredator");
		
		File configFile = new File(evt.getModConfigurationDirectory(), "AliensVsPredator.cfg");
		Configuration config = new Configuration(configFile);
		verifyConfigVersion(evt, configFile, config);

		try
		{
			config.addCustomCategoryComment(CATEGORY_IDS, "Configuration for the mod's entity IDs");
			config.addCustomCategoryComment(CATEGORY_IDS, "WARNING: ONLY MODIFY THESE IF YOU KNOW WHAT YOU ARE DOING, YOU CAN BREAK WORLD SAVES AND/OR SERVERS.");
            config.addCustomCategoryComment(CATEGORY_DIM, "WARNING: ONLY MODIFY THESE IF YOU KNOW WHAT YOU ARE DOING, YOU CAN BREAK WORLD SAVES AND/OR SERVERS.");
            config.addCustomCategoryComment(CATEGORY_BIOMES, "WARNING: ONLY MODIFY THESE IF YOU KNOW WHAT YOU ARE DOING, YOU CAN BREAK WORLD SAVES AND/OR SERVERS.");
			config.addCustomCategoryComment(CATEGORY_OTHER, "Other configuration options");
			config.load();

            dimVarda = config.get(CATEGORY_DIM, "VARDA", 223).getInt();
            dimAcheron = config.get(CATEGORY_DIM, "ACHERON", 426).getInt();

            biomeVarda = config.get(CATEGORY_BIOMES, "VARDA", 223).getInt();
            biomeAcheron = config.get(CATEGORY_BIOMES, "ACHERON", 224).getInt();

			mainDomain = config.get(CATEGORY_URLS, "DOMAIN_MAIN", "https://aliensvspredator.org", "").getString();
			devDomain = config.get(CATEGORY_URLS, "DOMAIN_DEV", "https://aliensvspredator.org", "").getString();
			urlUpdater = config.get(CATEGORY_URLS, "UPDATE_STRING_URL", "/page/beta/aliensvspredator/latest.php", "").getString();
			urlChangelog = config.get(CATEGORY_URLS, "URL_CHANGELOG", "/changelog.txt", "").getString();
			urlFeedbackSubmit = config.get(CATEGORY_URLS, "URL_AVP_FEEDBACK_SUBMIT", "/page/beta/submit.php?user=%s&uuid=%s&info=%s", "").getString();
			urlFeedbackValidation = config.get(CATEGORY_URLS, "URL_FEEDBACK_VALIDATION", "/page/beta/validate.php?uuid=%s", "").getString();
			urlSkins = config.get(CATEGORY_URLS, "URL_SKINS", "/page/skins", "").getString();
			urlSkinAk47 = config.get(CATEGORY_URLS, "URL_SKIN_AK47", "/ak47/%s.png", "").getString();
			urlSkinM4 = config.get(CATEGORY_URLS, "URL_SKIN_M4", "/m4/%s.png", "").getString();
			urlSkinM41a = config.get(CATEGORY_URLS, "URL_SKIN_M41A", "/m4a1/%s.png", "").getString();
			urlSkinM56sg = config.get(CATEGORY_URLS, "URL_SKIN_M56SG", "/m56sg/%s.png", "").getString();
			urlSkinSniper = config.get(CATEGORY_URLS, "URL_SKIN_SNIPER", "/sniper/%s.png", "").getString();

			explosionsEnabled = config.get(CATEGORY_OTHER, "EXPLOSION_BLOCK_DAMAGE", true).getBoolean(true);
			nukesEnabled = config.get(CATEGORY_OTHER, "NUKES_ENABLED", true).getBoolean(true);
			updaterEnabled = config.get(CATEGORY_OTHER, "UPDATER_ENABLED", true, "Toggle the mod's updater.").getBoolean(true);
			debugToolsEnabled = config.get(CATEGORY_OTHER, "DEBUG_TOOLS", false, "Toggle the debugging tools.").getBoolean(false);
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
		return this.explosionsEnabled;
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
	
	public int dimensionIdVarda() 
	{
        return this.dimVarda;
    }
	
	public int dimensionIdAcheron() 
	{
        return this.dimAcheron;
    }
    
    public int biomeIdVarda()
    {
        return this.biomeVarda;
    }
	
	public int biomeIdAcheron()
	{
        return this.biomeAcheron;
    }

	public boolean areNukesEnabled()
	{
		return this.nukesEnabled;
	}
}