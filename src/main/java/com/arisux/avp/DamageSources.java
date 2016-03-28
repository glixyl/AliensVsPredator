package com.arisux.avp;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

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
    public static DamageSource flamethrower = (new DamageSource("flamethrower")).setProjectile();
    public static DamageSource laserMine = (new DamageSource("laserMine")).setProjectile().setExplosion();
    public static DamageSource silicaStorm = (new DamageSource("silicaStorm")).setDifficultyScaled().setDamageBypassesArmor().setDamageIsAbsolute();

	public DamageSources(String source)
	{
		super(source);
	}
	
    public static DamageSource causeSilicaStormDamage(Entity entity)
    {
        return (new EntityDamageSource("silicaStorm", entity)).setDifficultyScaled().setDamageBypassesArmor().setDamageIsAbsolute();
    }
	
    public static DamageSource causeLaserMineDamage(Entity entityLaserMine, Entity entity)
    {
        return (new EntityDamageSourceIndirect(laserMine.getDamageType(), entityLaserMine, entity)).setProjectile().setExplosion();
    }
	
    public static DamageSource causeFlamethrowerDamage(Entity entityFlame, Entity entity)
    {
        return (new EntityDamageSourceIndirect(flamethrower.getDamageType(), entityFlame, entity)).setProjectile();
    }
	
    public static DamageSource causePlasmaCasterDamage(Entity sourceEntity, Entity entity)
    {
        return (new EntityDamageSourceIndirect(plasmacaster.getDamageType(), sourceEntity, entity)).setProjectile();
    }
	
    public static DamageSource causeSpearDamage(Entity sourceEntity, Entity entity)
    {
        return (new EntityDamageSourceIndirect(spear.getDamageType(), sourceEntity, entity)).setProjectile();
    }
	
    public static DamageSource causeShurikenDamage(Entity sourceEntity, Entity entity)
    {
        return (new EntityDamageSourceIndirect(shuriken.getDamageType(), sourceEntity, entity)).setProjectile();
    }
	
    public static DamageSource causeSmartDiscDamage(Entity sourceEntity, Entity entity)
    {
        return (new EntityDamageSourceIndirect(smartdisc.getDamageType(), sourceEntity, entity)).setProjectile();
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
