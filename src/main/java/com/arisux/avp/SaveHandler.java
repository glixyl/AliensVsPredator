package com.arisux.avp;

import java.io.File;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.WorldUtil.NBT;
import com.arisux.avp.world.DedicatedWorldInfo;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SaveHandler
{
    private DedicatedWorldInfo worldInfo;

    public SaveHandler()
    {
        ;
    }

    public DedicatedWorldInfo getWorldInfo()
    {
        return worldInfo == null ? worldInfo = new DedicatedWorldInfo(new NBTTagCompound()) : worldInfo;
    }

    public File getWorldFile(World world)
    {
        return new File(world.getSaveHandler().getWorldDirectory(), this.getFileName());
    }

    public String getFileName()
    {
        return "aliensvspredator.dat";
    }

    public void saveData(World world)
    {
        File file = this.getWorldFile(world);
        NBTTagCompound tag = new NBTTagCompound();

        try
        {
            if (this.getWorldInfo() != null)
            {
                if (!this.getWorldInfo().save(tag))
                {
                    AIRI.logger.info("Unable to save world data: ", this.getFileName());
                }
            }

            NBT.writeCompressed(tag, file);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.gc();
        }
    }

    public NBTTagCompound loadData(World world)
    {
        NBTTagCompound tag = new NBTTagCompound();
        File file = this.getWorldFile(world);

        try
        {
            if (!file.exists())
            {
                NBT.writeCompressed(tag, file);
            }

            NBTTagCompound read = NBT.readCompressed(file);
            tag = read == null ? tag : read;

            this.worldInfo = new DedicatedWorldInfo(tag);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return tag;
    }
}
