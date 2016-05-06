package com.arisux.avp.util;

import com.arisux.avp.entities.extended.ExtendedEntityLivingBase;

import net.minecraft.nbt.NBTTagCompound;

public abstract class Embryo
{
    private int ticksExisted;
    private EmbryoType type;
    
    public Embryo(EmbryoType type)
    {
        this.type = type;
        this.ticksExisted = 0;
    }
    
    public void tick(ExtendedEntityLivingBase extendedEntity)
    {
        this.ticksExisted++;
    }

    public void saveNBTData(NBTTagCompound nbt)
    {
        nbt.setInteger("ticksExisted", this.ticksExisted);
    }

    public void loadNBTData(NBTTagCompound nbt)
    {
        this.ticksExisted = nbt.getInteger("ticksExisted");
    }
    
    public int getTicksExisted()
    {
        return ticksExisted;
    }
    
    public EmbryoType getType()
    {
        return type;
    }
    
    public int getGestationPeriod()
    {
        return this.getType().getGestationPeriod();
    }
}
