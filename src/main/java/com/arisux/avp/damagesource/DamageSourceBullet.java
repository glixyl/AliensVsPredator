package com.arisux.avp.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

import com.arisux.avp.entities.EntityBullet;

public class DamageSourceBullet extends DamageSource
{
    public static DamageSourceBullet bullet = (new DamageSourceBullet("bullet")).setDamageBypassesArmor1();
    protected DamageSourceBullet(String s)
    {
        super(s);
    }

    protected DamageSourceBullet setDamageBypassesArmor1()
    {
        return this;
    }

    public static DamageSource causeBulletDamage(EntityBullet entitybullet, Entity entity)
    {
        return (new EntityDamageSourceIndirect("bullet", entitybullet, entity)).setProjectile();
    }

    protected DamageSource func_76348_h()
    {
        return this.setDamageBypassesArmor1();
    }
}
