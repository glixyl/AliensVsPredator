package com.arisux.avp.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

public class DamageSourceAtmosphere extends DamageSource
{
    public static DamageSourceAtmosphere atmosphere = new DamageSourceAtmosphere("the planets atmosphere").setDamageBypassesArmor1();
    protected DamageSourceAtmosphere(String s)
    {
	super(s);
    }

    protected DamageSourceAtmosphere setDamageBypassesArmor1()
    {
	return this;
    }

    public static DamageSource causeDamage(Entity atmosphereEntity, Entity entity)
    {
	return new EntityDamageSourceIndirect("the planets atmosphere", atmosphereEntity, entity).setMagicDamage();
    }

    public DamageSource setDamageBypassesArmor()
    {
	return setDamageBypassesArmor1();
    }
}