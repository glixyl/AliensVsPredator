package com.arisux.avp;

import net.minecraft.entity.Entity;
import net.minecraft.util.*;

public class DamageSources extends DamageSource
{
	public static DamageSource acid = (new DamageSource("acid")).setDamageBypassesArmor();
	public static DamageSource acidProjectile = (new DamageSource("acidshot")).setDamageBypassesArmor().setProjectile();
	public static DamageSource chestburster = (new DamageSource("chestburster")).setDamageIsAbsolute().setDamageBypassesArmor();
	public static DamageSource vardaAtmosphere = new DamageSource("atmosphere.varda").setDamageBypassesArmor();
    public static DamageSource bullet = (new DamageSource("bullet")).setProjectile();
    public static DamageSource smartdisc = (new DamageSource("smartdisc")).setProjectile();
    public static DamageSource shuriken = (new DamageSource("shuriken")).setProjectile();
    public static DamageSource spear = (new DamageSource("spear")).setProjectile();
    public static DamageSource plasmacaster = (new DamageSource("plasmacaster")).setProjectile().setMagicDamage().setDamageBypassesArmor();

	public DamageSources(String source)
	{
		super(source);
	}
	
    public static DamageSource causePlasmaCasterDamage(Entity entityBoomerang, Entity entity)
    {
        return (new EntityDamageSourceIndirect(plasmacaster.getDamageType(), entityBoomerang, entity)).setProjectile();
    }
	
    public static DamageSource causeSpearDamage(Entity entityBoomerang, Entity entity)
    {
        return (new EntityDamageSourceIndirect(spear.getDamageType(), entityBoomerang, entity)).setProjectile();
    }
	
    public static DamageSource causeShurikenDamage(Entity entityBoomerang, Entity entity)
    {
        return (new EntityDamageSourceIndirect(shuriken.getDamageType(), entityBoomerang, entity)).setProjectile();
    }
	
    public static DamageSource causeSmartDiscDamage(Entity entityBoomerang, Entity entity)
    {
        return (new EntityDamageSourceIndirect(smartdisc.getDamageType(), entityBoomerang, entity)).setProjectile();
    }
	
    public static DamageSource causeBulletDamage(Entity sourceEntity, Entity entity)
    {
        return (new EntityDamageSourceIndirect(bullet.getDamageType(), sourceEntity, entity)).setProjectile();
    }

	public static DamageSource causeVardaAtmosphereDamage(Entity entity)
	{
		return new EntityDamageSource(vardaAtmosphere.getDamageType(), entity).setDamageBypassesArmor();
	}

	public static DamageSource causeChestbursterDamage(Entity sourceEntity, Entity entity)
	{
		return (new EntityDamageSourceIndirect(chestburster.getDamageType(), sourceEntity, entity)).setDamageIsAbsolute().setDamageBypassesArmor();
	}

	public static DamageSource causeAcidicDamage(Entity sourceEntity, Entity entity)
	{
		return (new EntityDamageSourceIndirect(acid.getDamageType(), sourceEntity, entity)).setDamageBypassesArmor();
	}

	public static DamageSource causeAcidicProjectile(Entity sourceEntity, Entity entity)
	{
		return (new EntityDamageSourceIndirect(acidProjectile.getDamageType(), sourceEntity, entity)).setProjectile();
	}
}
