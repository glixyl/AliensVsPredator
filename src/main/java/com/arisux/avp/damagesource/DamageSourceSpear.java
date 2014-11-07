package com.arisux.avp.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

import com.arisux.avp.entities.EntitySpear;

public class DamageSourceSpear extends DamageSource
{
    public static DamageSourceSpear spear = (new DamageSourceSpear("spear")).setDamageBypassesArmor1();
    protected DamageSourceSpear(String s)
    {
        super(s);
    }

    protected DamageSourceSpear setDamageBypassesArmor1()
    {
        return this;
    }

    public static DamageSource causeSpearDamage(EntitySpear entityspear, Entity entity)
    {
        return (new EntityDamageSourceIndirect("spear", entityspear, entity)).setProjectile();
    }

    protected DamageSource func_76348_h()
    {
        return this.setDamageBypassesArmor1();
    }
}
