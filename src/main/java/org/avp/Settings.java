package org.avp;

import java.io.File;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Settings
{
    public static final Settings instance = new Settings();
    private File configFile;
    private Configuration config;

    private final String CATEGORY_OTHER = "ETC";
    private final String CATEGORY_DIM = "DIMENSIONS";
    private final String CATEGORY_BIOMES = "BIOMES";

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
        configFile = new File(evt.getModConfigurationDirectory(), "AliensVsPredator.cfg");
        config = new Configuration(configFile);

        try
        {
            config.load();

            dimVarda = config.get(CATEGORY_DIM, "VARDA", 223).getInt();
            dimAcheron = config.get(CATEGORY_DIM, "ACHERON", 426).getInt();

            biomeVarda = config.get(CATEGORY_BIOMES, "VARDA", 223).getInt();
            biomeAcheron = config.get(CATEGORY_BIOMES, "ACHERON", 224).getInt();

            explosionsEnabled = config.get(CATEGORY_OTHER, "EXPLOSION_BLOCK_DAMAGE", true).getBoolean(true);
            nukesEnabled = config.get(CATEGORY_OTHER, "NUKES_ENABLED", true).getBoolean(true);
            updaterEnabled = config.get(CATEGORY_OTHER, "UPDATER_ENABLED", false, "Toggle the mod's updater.").getBoolean(false);
            debugToolsEnabled = config.get(CATEGORY_OTHER, "DEBUG_TOOLS", false, "Toggle the debugging tools.").getBoolean(false);
        }
        finally
        {
            config.save();
        }
    }

    public File getConfigFile()
    {
        return configFile;
    }

    public Configuration getConfig()
    {
        return config;
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
