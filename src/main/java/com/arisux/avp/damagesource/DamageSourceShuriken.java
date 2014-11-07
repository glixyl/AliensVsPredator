package com.arisux.avp.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

import com.arisux.avp.entities.EntityShuriken;

public class DamageSourceShuriken extends DamageSource
{
    public static DamageSourceShuriken spear = (new DamageSourceShuriken("shuriken")).setDamageBypassesArmor1();
    public DamageSourceShuriken(String par1Str)
    {
        super(par1Str);
    }

    protected DamageSourceShuriken setDamageBypassesArmor1()
    {
        return this;
    }

    public static DamageSource causeSpearDamage(EntityShuriken entityShuriken, Entity entity)
    {
        return (new EntityDamageSourceIndirect("shuriken", entityShuriken, entity)).setProjectile();
    }

    protected DamageSource func_76348_h()
    {
        return this.setDamageBypassesArmor1();
    }
}
