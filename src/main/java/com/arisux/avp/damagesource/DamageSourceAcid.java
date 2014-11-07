package com.arisux.avp.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

import com.arisux.avp.entities.EntityAcidPool;

public class DamageSourceAcid extends DamageSource
{
    public static DamageSourceAcid acid = (new DamageSourceAcid("acid")).setDamageBypassesArmor1();
    public DamageSourceAcid(String s)
    {
	super(s);
    }

    protected DamageSourceAcid setDamageBypassesArmor1()
    {
	return this;
    }

    public static DamageSource causeAcidicDamage(EntityAcidPool entityacid, Entity entity)
    {
	return (new EntityDamageSourceIndirect("acid", entityacid, entity)).setProjectile();
    }

    @Override
    public DamageSource setDamageBypassesArmor()
    {
	return this.setDamageBypassesArmor1();
    }
}
