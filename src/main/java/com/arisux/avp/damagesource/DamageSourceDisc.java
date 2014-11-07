package com.arisux.avp.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

import com.arisux.avp.entities.EntitySmartDisc;

public class DamageSourceDisc extends DamageSource
{
    public static DamageSourceDisc spear = (new DamageSourceDisc("disc")).setDamageBypassesArmor1();
    protected DamageSourceDisc(String s)
    {
        super(s);
    }

    protected DamageSourceDisc setDamageBypassesArmor1()
    {
        return this;
    }

    public static DamageSource causeSpearDamage(EntitySmartDisc entityBoomerang, Entity entity)
    {
        return (new EntityDamageSourceIndirect("disc", entityBoomerang, entity)).setProjectile();
    }

    protected DamageSource func_76348_h()
    {
        return this.setDamageBypassesArmor1();
    }
}
