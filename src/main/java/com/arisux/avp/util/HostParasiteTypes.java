package com.arisux.avp.util;

import com.arisux.avp.entities.mob.EntityAqua;
import com.arisux.avp.entities.mob.EntityCrusher;
import com.arisux.avp.entities.mob.EntityDrone;
import com.arisux.avp.entities.mob.EntityPredalien;
import com.arisux.avp.entities.mob.EntitySpitter;
import com.arisux.avp.entities.mob.EntityXenomorph;
import com.arisux.avp.entities.mob.EntityYautja;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySquid;

@SuppressWarnings("all")
public enum HostParasiteTypes
{
	NORMAL(0, EntityLiving.class, EntityDrone.class),
	CRUSHER(1, EntityHorse.class, EntityCrusher.class),
	SPITTER(2, EntityCreeper.class, EntitySpitter.class),
	AQUA(3, EntitySquid.class, EntityAqua.class),
	PREDALIEN(4, EntityYautja.class, EntityPredalien.class);
	
	public int id;
	private Class<? extends Entity> host;
	private Class<? extends EntityXenomorph> parasite;
	
	HostParasiteTypes(int id, Class<? extends Entity> host, Class<? extends EntityXenomorph> parasite)
	{
		this.id = id;
		this.host = host;
		this.parasite = parasite;
	}

	public static HostParasiteTypes get(int id)
	{
		for(HostParasiteTypes mode : values())
		{
			if (mode.id == id)
			{
				return mode;
			}
		}
		
		return null;
	}
	
	public static HostParasiteTypes getTypeForHost(Class<? extends Entity> host)
	{
		for(HostParasiteTypes map : values())
		{
			if (map.host == host)
			{
				return map;
			}
		}
		
		return NORMAL;
	}
	
	public static HostParasiteTypes getTypeForParasite(Class<? extends Entity> parasite)
	{
		for(HostParasiteTypes map : values())
		{
			if (map.parasite == parasite)
			{
				return map;
			}
		}
		
		return NORMAL;
	}
	
	public Class<? extends Entity> getHost()
	{
		return host;
	}
	
	public Class<? extends EntityXenomorph> getParasiteType()
	{
		return parasite;
	}
}
