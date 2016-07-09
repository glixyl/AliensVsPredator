package com.arisux.airi.lib.client;

import com.arisux.airi.lib.WorldUtil;

import net.minecraft.util.ResourceLocation;

public class PlayerResource
{
    private String name, uuid;
    private ResourceLocation resource;

    public PlayerResource(String username)
    {
        this.name = username;
        this.uuid = username;

        Thread uuidThread = new Thread()
        {
            @Override
            public void run()
            {
                super.run();
                uuid = WorldUtil.Entities.Players.getUUID(name);
            }
        };
        uuidThread.start();
    }

    public String getName()
    {
        return name;
    }

    public String getUUID()
    {
        return uuid;
    }

    public ResourceLocation getResource()
    {
        return this.resource;
    }

    public ResourceLocation setResource(ResourceLocation resource)
    {
        return this.resource = resource;
    }
}
