package com.arisux.avp.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.arisux.avp.DamageSources;

public class EntitySpear extends EntityItemStackProjectile
{
	private int damage;

	public EntitySpear(World world)
	{
		super(world);
	}

	public EntitySpear(World world, double posX, double posY, double posZ)
	{
		this(world);
		setPosition(posX, posY, posZ);
	}

	public EntitySpear(World world, EntityLivingBase entityliving, ItemStack itemstack)
	{
		this(world);
		this.shootingEntity = entityliving;
		this.setItemstack(itemstack);
		this.setLocationAndAngles(entityliving.posX, entityliving.posY + entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
		this.posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
		this.posY -= 0.1D;
		this.posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
		this.motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F);
		this.motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
		this.setThrowableHeading(motionX, motionY, motionZ, 0.8F, 3.0F);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@Override
	public void onEntityHit(Entity entity)
	{
		if (!worldObj.isRemote)
		{
			DamageSource damagesource = null;

			if (shootingEntity == null)
			{
				damagesource = DamageSources.causeSpearDamage(this, this);
			}
			else
			{
				damagesource = DamageSources.causeSpearDamage(this, shootingEntity);
			}

			if (entity.attackEntityFrom(damagesource, damage + 1))
			{
				this.applyEntityHitEffects(entity);
				this.playHitSound();

				if (itemstack.getItemDamage() + 1 > itemstack.getMaxDamage())
				{
					itemstack.stackSize--;
					this.setDead();
				}
				else
				{
					if (shootingEntity instanceof EntityLivingBase)
					{
						itemstack.damageItem(1, (EntityLivingBase) shootingEntity);
					}
					else
					{
						itemstack.attemptDamageItem(1, rand);
					}
					this.setVelocity(0D, 0D, 0D);
				}
			}
			else
			{
				this.bounce();
			}
		}
	}

	@Override
	public void playHitSound()
	{
		worldObj.playSoundAtEntity(this, "random.bowhit", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.9F));
	}

	@Override
	public int getLifetime()
	{
		return 0;
	}

	@Override
	public int getMaxArrowShake()
	{
		return 10;
	}
	
	@Override
	public boolean canPickup(EntityPlayer entityplayer)
	{
		return true;
	}
}
