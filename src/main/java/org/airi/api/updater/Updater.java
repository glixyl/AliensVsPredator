package com.arisux.airi.api.updater;

import java.util.HashMap;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.NetworkUtil;
import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.interfaces.IInitializablePost;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.realmsclient.util.JsonUtils;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;

public class Updater implements IInitializablePost
{
    private HashMap<String, String> versionData = new HashMap<String, String>();
    private String modId;
    private String curVer;
    private String updateUrl;
    private String downloadUrl;
    private String changelogUrl;
    private Thread changelogDownloadThread;
    private Changelog changelog;

    public Updater(String modId, String curVer, String updateUrl, String downloadUrl, String changelogUrl)
    {
        this.modId = modId;
        this.curVer = curVer;
        this.updateUrl = updateUrl;
        this.downloadUrl = downloadUrl;
        this.changelogUrl = changelogUrl;
    }

    @Override
    public void postInitialize(FMLPostInitializationEvent event)
    {
        AIRI.updaterApi().register(this);
        downloadVersionInformation();
    }

    @SideOnly(Side.CLIENT)
    public void printUpdateInformation(EntityPlayer thePlayer)
    {
        if (isVersionDataValid() && isUpdateAvailable())
        {
            String updateString = "Update: " + getVersionData().get("MODID") + " " + getVersionData().get("MODVER") + " for Minecraft " + getVersionData().get("MCVER");
            AIRI.logger.info(updateString);
            WorldUtil.Entities.Players.sendToChat(thePlayer, updateString);
        }
    }

    public void tick()
    {
        if (this.changelog == null && this.changelogDownloadThread == null)
        {
            this.changelogDownloadThread = new Thread()
            {
                @Override
                public void run()
                {
                    downloadChangelog();
                }
            };
            this.changelogDownloadThread.start();
        }
    }

    public void downloadVersionInformation()
    {
        new Thread()
        {
            @Override
            public void run()
            {
                System.out.println(String.format("Checking for %s updates...", modId));

                try
                {
                    clearCaches();
                    isVersionDataValid();

                    String retrieved = NetworkUtil.getURLContents(updateUrl);

                    if (retrieved != null)
                    {
                        JsonElement element = new JsonParser().parse(retrieved);
                        JsonObject obj = element.getAsJsonObject();

                        getVersionData().put("MCVER", JsonUtils.getStringOr("gameVersion", obj, ""));
                        getVersionData().put("MODVER", JsonUtils.getStringOr("buildVersion", obj, ""));
                        getVersionData().put("FORGEVER", JsonUtils.getStringOr("apiVersion", obj, ""));
                        getVersionData().put("MODID", JsonUtils.getStringOr("projectName", obj, ""));
                        System.out.println("Latest release of " + getVersionData().get("MODID") + " is version " + getVersionData().get("MODVER") + " for Minecraft " + getVersionData().get("MCVER"));
                    }
                    else
                    {
                        printConnectionError();
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Error while downloading version information: " + e + ", " + updateUrl);
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void downloadChangelog()
    {
        try
        {
            if (isUpdateAvailable() && changelogUrl != null)
            {
                String preParsedChangelog = NetworkUtil.getURLContents(changelogUrl, true);

                if (preParsedChangelog != null)
                {
                    changelog = new Changelog(preParsedChangelog);
                }
                else
                {
                    System.out.println("Could not retrieve changelog for mod with ID " + this.modId);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getVersionData()
    {
        return this.versionData;
    }

    public boolean isVersionDataValid()
    {
        if (this.versionData == null)
        {
            this.versionData = new HashMap<String, String>();
        }

        return getVersionData().get("MCVER") != null && getVersionData().get("MODVER") != null && getVersionData().get("FORGEVER") != null && getVersionData().get("MODID") != null;
    }

    public boolean isUpdateAvailable()
    {
        return (isVersionDataValid() && getVersionData().get("MODID").equalsIgnoreCase(modId) && !getVersionData().get("MODVER").equalsIgnoreCase(curVer));
    }

    public void printConnectionError()
    {
        AIRI.logger.warning("Could not check for updates for mod with ID " + modId);
    }

    public void clearCaches()
    {
        this.versionData = null;
    }

    public Changelog getChangelog()
    {
        return changelog;
    }

    public String getChangelogUrl()
    {
        return changelogUrl;
    }

    public String getCurVer()
    {
        return curVer;
    }

    public String getDownloadUrl()
    {
        return downloadUrl;
    }

    public String getModId()
    {
        return modId;
    }

    public String getUpdateUrl()
    {
        return updateUrl;
    }
}
