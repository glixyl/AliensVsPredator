package com.arisux.avp.util;

import com.arisux.avp.entities.mob.EntityAqua;
import com.arisux.avp.entities.mob.EntityCrusher;
import com.arisux.avp.entities.mob.EntityDrone;
import com.arisux.avp.entities.mob.EntityPredalien;
import com.arisux.avp.entities.mob.EntityRunnerDrone;
import com.arisux.avp.entities.mob.EntitySpitter;
import com.arisux.avp.entities.mob.EntityXenomorph;
import com.arisux.avp.entities.mob.EntityYautja;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWolf;

@SuppressWarnings("all")
public enum HostType
{
	NORMAL(0, EntityLiving.class, EntityDrone.class),
	SPITTER(2, EntityCreeper.class, EntitySpitter.class),
	AQUA(3, EntitySquid.class, EntityAqua.class),
	PREDALIEN(4, EntityYautja.class, EntityPredalien.class),
	RUNNER(5, new Class[]{ EntityCow.class, EntityHorse.class, EntityWolf.class }, EntityRunnerDrone.class);
	
	public int id;
	private Class<? extends Entity>[] hosts;
	private Class<? extends EntityXenomorph> result;
	
	HostType(int id, Class<? extends Entity> host, Class<? extends EntityXenomorph> result)
	{
		this(id, new Class[]{ host }, result);
	}
	
	HostType(int id, Class<? extends Entity>[] hosts, Class<? extends EntityXenomorph> result)
	{
		this.id = id;
		this.hosts = hosts;
		this.result = result;
	}

	public static HostType get(int id)
	{
		for(HostType mode : values())
		{
			if (mode.id == id)
			{
				return mode;
			}
		}
		
		return null;
	}
	
	public static HostType getMappingFromHost(Class<? extends Entity> host)
	{
		for(HostType map : values())
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
	
	public static HostType getMappingFromResult(Class<? extends Entity> result)
	{
		for(HostType map : values())
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
}
