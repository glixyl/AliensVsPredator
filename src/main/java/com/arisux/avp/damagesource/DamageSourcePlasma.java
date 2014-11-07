package com.arisux.avp.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

import com.arisux.avp.entities.EntityPlasma;

public class DamageSourcePlasma extends DamageSource
{
    public static DamageSourcePlasma plasma = (new DamageSourcePlasma("plasma")).setDamageBypassesArmor1();
    protected DamageSourcePlasma(String s)
    {
        super(s);
    }

    protected DamageSourcePlasma setDamageBypassesArmor1()
    {
        return this;
    }

    public static DamageSource causePlasmaDamage(EntityPlasma entityplasma, Entity entity)
    {
        return (new EntityDamageSourceIndirect("plasma", entityplasma, entity)).setProjectile();
    }

    protected DamageSource func_76348_h()
    {
        return this.setDamageBypassesArmor1();
    }
}
