package org.avp.util;

import org.avp.entities.mob.EntityAqua;
import org.avp.entities.mob.EntityDrone;
import org.avp.entities.mob.EntityPredalien;
import org.avp.entities.mob.EntityQueen;
import org.avp.entities.mob.EntityRunnerDrone;
import org.avp.entities.mob.EntitySpitter;
import org.avp.entities.mob.EntityXenomorph;
import org.avp.entities.mob.EntityYautja;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWolf;

@SuppressWarnings("all")
public enum EmbryoType
{
    NORMAL(0, EntityLiving.class, EntityDrone.class), 
    SPITTER(1, EntityCreeper.class, EntitySpitter.class), 
    AQUA(2, EntitySquid.class, EntityAqua.class), 
    PREDALIEN(3, EntityYautja.class, EntityPredalien.class), 
    RUNNER(4, new Class[] { EntityCow.class, EntityHorse.class, EntityWolf.class }, EntityRunnerDrone.class),
    QUEEN(5, Entity.class, EntityQueen.class);

    private int typeId;
    private int gestationPeriod;
    private Class<? extends Entity>[] hosts;
    private Class<? extends EntityXenomorph> result;
    
    EmbryoType(int id, Class<? extends Entity> host, Class<? extends EntityXenomorph> result)
    {
        this(id, 6000, host, result);
    }

    EmbryoType(int id, int gestationPeriod, Class<? extends Entity> host, Class<? extends EntityXenomorph> result)
    {
        this(id, gestationPeriod, new Class[] { host }, result);
    }

    EmbryoType(int id, Class<? extends Entity>[] hosts, Class<? extends EntityXenomorph> result)
    {
        this(id, 6000, hosts, result);
    }

    EmbryoType(int id, int gestationPeriod, Class<? extends Entity>[] hosts, Class<? extends EntityXenomorph> result)
    {
        this.typeId = id;
        this.hosts = hosts;
        this.result = result;
        this.gestationPeriod = gestationPeriod;
    }

    public static EmbryoType get(int id)
    {
        for (EmbryoType mode : values())
        {
            if (mode.typeId == id)
            {
                return mode;
            }
        }

        return null;
    }

    public static EmbryoType getMappingFromHost(Class<? extends Entity> host)
    {
        for (EmbryoType map : values())
        {
            for (Class c : map.hosts)
            {
                if (c == host)
                {
                    return map;
                }
            }
        }

        return NORMAL;
    }

    public static EmbryoType getMappingFromResult(Class<? extends Entity> result)
    {
        for (EmbryoType map : values())
        {
            if (map.result == result)
            {
                return map;
            }
        }

        return NORMAL;
    }

    public Class<? extends Entity>[] getHosts()
    {
        return hosts;
    }

    public Class<? extends EntityXenomorph> getResult()
    {
        return result;
    }
    
    public int getGestationPeriod()
    {
        return gestationPeriod;
    }
    
    public int getTypeId()
    {
        return typeId;
    }
}
