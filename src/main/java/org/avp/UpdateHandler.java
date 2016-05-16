package org.avp;

import com.arisux.airi.api.updater.Updater;

public class UpdateHandler extends Updater
{
    public static UpdateHandler instance = new UpdateHandler();

    public UpdateHandler()
    {
        super("AliensVsPredator", AliensVsPredator.instance().container().getVersion(), URLs.urlUpdater, URLs.server, URLs.urlChangelog);
    }
}
