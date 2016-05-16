package org.avp;

import com.arisux.airi.lib.interfaces.IInitializablePre;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class RenderTypes implements IInitializablePre
{
    public static RenderTypes instance = new RenderTypes();

    public int RENDER_TYPE_SHAPED;

    @Override
    public void preInitialize(FMLPreInitializationEvent event)
    {
        RENDER_TYPE_SHAPED = RenderingRegistry.getNextAvailableRenderId();
    }
}
