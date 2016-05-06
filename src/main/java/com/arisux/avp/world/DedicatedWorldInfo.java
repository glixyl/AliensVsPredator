package com.arisux.avp.world;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;

public class DedicatedWorldInfo
{
    private ArrayList<DerelictLocation> derelictLocations;

    public DedicatedWorldInfo(NBTTagCompound tag)
    {
        this.derelictLocations = new ArrayList<DerelictLocation>();

        this.load(tag);
        // {
        // NBTTagCompound newTag = new NBTTagCompound();
        // this.save(newTag);
        // this.load(newTag);
        // }
    }

    public boolean load(NBTTagCompound tag)
    {
        if (tag != null)
        {
            NBTTagCompound tagDerelictLocations = tag.getCompoundTag("DerelictLocations");

            for (int i = 0; i < 3; i++)
            {
                DerelictLocation derelictLocation = this.derelictLocations.size() - 1 >= i ? this.derelictLocations.get(i) : null;

                if (derelictLocation == null)
                {
                    this.derelictLocations.add(new DerelictLocation(false, i));
                }

                if (derelictLocation != null)
                {
                    derelictLocation.load(tagDerelictLocations);
                }
            }
        }
        else
        {
            return false;
        }

        return true;
    }

    public boolean save(NBTTagCompound tag)
    {
        if (tag != null)
        {
            NBTTagCompound tagDerelictLocations = new NBTTagCompound();

            if (this.getDerelictLocations() != null)
            {
                if (!this.getDerelictLocations().isEmpty())
                {
                    for (int i = 0; i < 3; i++)
                    {
                        DerelictLocation derelictLocation = this.getDerelictLocations().get(i);

                        if (derelictLocation != null)
                        {
                            derelictLocation.save(tagDerelictLocations);
                        }
                    }

                    tag.setTag("DerelictLocations", tagDerelictLocations);
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }

        return true;
    }

    public ArrayList<DerelictLocation> getDerelictLocations()
    {
        return derelictLocations;
    }
}
