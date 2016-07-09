package com.arisux.airi;

import com.arisux.airi.api.updater.Updater;

public class UpdateHandler extends Updater
{
    public static UpdateHandler instance = new UpdateHandler();

    public UpdateHandler()
    {
        super(Properties.MODID, AIRI.instance().container().getVersion(), AIRI.settings().getServer() + "/page/beta/airi/latest.php", AIRI.settings().getServer() + "/page/mods/airi/", AIRI.settings().getServer() + "/page/mods/airi/changelog.txt");
    }
}
