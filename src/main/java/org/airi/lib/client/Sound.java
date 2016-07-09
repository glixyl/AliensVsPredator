package com.arisux.airi.lib.client;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;

public class Sound
{
    private String key;
    private float volume;
    private float pitch;

    public Sound(String key)
    {
        this(key, 1F, 1F);
    }

    public Sound(String key, float volume, float pitch)
    {
        this.key = key;
        this.volume = volume;
        this.pitch = pitch;
    }

    public String getKey()
    {
        return key;
    }

    public float getVolume()
    {
        return volume;
    }

    public float getPitch()
    {
        return pitch;
    }

    public void playSound(Entity entity)
    {
        playSound(entity, volume, pitch);
    }

    public void playSound(Entity entity, float volume, float pitch)
    {
        entity.playSound(key, volume, pitch);
    }

    public void playSound(World world, CoordData data)
    {
        playSound(world, (int) data.posX, (int) data.posY, (int) data.posZ, volume, pitch);
    }

    public void playSound(World world, CoordData data, float volume, float pitch)
    {
        playSound(world, (int) data.posX, (int) data.posY, (int) data.posZ, volume, pitch);
    }

    public void playSound(World world, int posX, int posY, int posZ)
    {
        playSound(world, posX, posY, posZ, volume, pitch);
    }

    public void playSound(World world, int posX, int posY, int posZ, float volume, float pitch)
    {
        world.playSound(posX, posY, posZ, key, volume, pitch, true);
    }
}
