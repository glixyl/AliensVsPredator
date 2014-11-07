package com.arisux.avp.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

import com.arisux.avp.entities.EntityAcidSpit;

public class DamageSourceAcidShot extends DamageSource
{
    public static DamageSourceAcidShot acid = (new DamageSourceAcidShot("acidshot")).setDamageBypassesArmor1();
    protected DamageSourceAcidShot(String s)
    {
        super(s);
    }

    protected DamageSourceAcidShot setDamageBypassesArmor1()
    {
        return this;
    }

    public static DamageSource causeAcidicDamage(EntityAcidSpit entityacid, Entity entity)
    {
        return (new EntityDamageSourceIndirect("acidshot", entityacid, entity)).setProjectile();
    }

    protected DamageSource func_76348_h()
    {
        return this.setDamageBypassesArmor1();
    }
}
