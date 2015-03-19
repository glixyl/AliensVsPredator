package com.arisux.avp.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.DamageSources;

public class EntitySmartDisc extends EntityProjectile
{
	public static final double RETURN_STRENGTH = 0.05D;
	public static final float MIN_FLOAT_STRENGTH = 0.7F;
	private float soundTimer;
	public float floatStrength;
	public Item thrownItem;

	public EntitySmartDisc(World world)
	{
		super(world);
		this.thrownItem = AliensVsPredator.items().itemDisc;
	}

	public EntitySmartDisc(World world, double posX, double posY, double posZ)
	{
		this(world);
		this.setPosition(posX, posY, posZ);
	}

	public EntitySmartDisc(World world, EntityLivingBase entityliving, ItemStack itemstack, float velocity)
	{
		this(world);
		this.shootingEntity = entityliving;
		this.setLocationAndAngles(entityliving.posX, entityliving.posY + entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
		this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
		this.posY -= 0.1D;
		this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
		this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
		this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI));
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, velocity, 5.0F);
		this.soundTimer = 0.0F;
		this.floatStrength = Math.min(1.5F, velocity);
		this.dataWatcher.updateObject(29, Integer.valueOf(Float.floatToRawIntBits(this.floatStrength)));
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(29, Integer.valueOf(Float.floatToRawIntBits(0.0F)));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.floatStrength = Float.intBitsToFloat(this.dataWatcher.getWatchableObjectInt(29));

		if (!this.inGround)
		{
			this.floatStrength *= 0.994F;

			if (this.floatStrength < 0.7F)
			{
				if (this.getIsCritical())
				{
					this.setIsCritical(false);
				}

				this.floatStrength = 0.0F;
			}

			float limitedStrength = Math.min(1.0F, this.floatStrength);

			if (!this.beenInGround)
			{
				this.rotationYaw += 20.0F * this.floatStrength;
			}

			if (!this.beenInGround && this.shootingEntity != null && this.floatStrength > 0.0F)
			{
				double dx = this.posX - this.shootingEntity.posX;
				double dy = this.posY - this.shootingEntity.posY - this.shootingEntity.getEyeHeight();
				double dz = this.posZ - this.shootingEntity.posZ;
				double d = Math.sqrt(dx * dx + dy * dy + dz * dz);
				dx /= d;
				dy /= d;
				dz /= d;
				this.motionX -= 0.05D * dx;
				this.motionY -= 0.05D * dy;
				this.motionZ -= 0.05D * dz;
				this.soundTimer += limitedStrength;

				if (this.soundTimer > 3.0F)
				{
					this.worldObj.playSoundAtEntity(this, "random.bow", 0.6F, 1.0F / (this.rand.nextFloat() * 0.2F + 2.2F - limitedStrength));
					this.soundTimer %= 3.0F;
				}
			}

			this.dataWatcher.updateObject(29, Integer.valueOf(Float.floatToRawIntBits(this.floatStrength)));
		}
	}

	@Override
	public void onEntityHit(Entity entity)
	{
		if (!this.worldObj.isRemote && this.floatStrength >= 0.7F)
		{
			EntityPlayer player;

			if (entity != this.shootingEntity)
			{
				player = null;
				DamageSource damagesource;

				if (this.shootingEntity == null)
				{
					damagesource = DamageSources.causeSmartDiscDamage(this, this);
				}
				else
				{
					damagesource = DamageSources.causeSmartDiscDamage(this, this.shootingEntity);
				}

				if (entity.attackEntityFrom(damagesource, 5.0F))
				{
					this.playHitSound();

					if (this.thrownItem.getMaxDamage() + 1 <= this.thrownItem.getMaxDamage())
					{
						this.setVelocity(0.2D * this.rand.nextDouble() - 0.1D, 0.2D * this.rand.nextDouble() - 0.1D, 0.2D * this.rand.nextDouble() - 0.1D);
					}
				}
				else
				{
					this.bounce();
				}
			}
			else
			{
				if (entity instanceof EntityPlayer)
				{
					player = (EntityPlayer) entity;
					ItemStack item = this.getItemstack();

					if (item == null)
					{
						return;
					}

					if (player.capabilities.isCreativeMode || player.inventory.addItemStackToInventory(item))
					{
						this.worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
						this.onItemPickup(player);
						this.setDead();
						return;
					}
				}
			}
		}
	}

	@Override
	public void onGroundHit(MovingObjectPosition mop)
	{
		this.xTile = mop.blockX;
		this.yTile = mop.blockY;
		this.zTile = mop.blockZ;
		this.inTile = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
		this.motionX = ((float) (mop.hitVec.xCoord - this.posX));
		this.motionY = ((float) (mop.hitVec.yCoord - this.posY));
		this.motionZ = ((float) (mop.hitVec.zCoord - this.posZ));
		float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		this.posX -= this.motionX / f1 * 0.05D;
		this.posY -= this.motionY / f1 * 0.05D;
		this.posZ -= this.motionZ / f1 * 0.05D;
		this.motionX *= -this.rand.nextFloat() * 0.5F;
		this.motionZ *= -this.rand.nextFloat() * 0.5F;
		this.motionY = this.rand.nextFloat() * 0.1F;

		if (mop.sideHit == 1)
		{
			this.inGround = true;
		}
		else
		{
			this.inGround = false;
		}

		this.setIsCritical(false);
		this.beenInGround = true;
		this.floatStrength = 0.0F;
	}

	@Override
	public void playHitSound()
	{
		this.worldObj.playSoundAtEntity(this, "random.bowhit", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.9F));
	}

	@Override
	public boolean aimRotation()
	{
		return this.beenInGround || this.floatStrength < 0.7F;
	}

	@Override
	public int getLifetime()
	{
		return 0;
	}

	@Override
	public boolean canBeCritical()
	{
		return true;
	}

	@Override
	public int getMaxArrowShake()
	{
		return 0;
	}

	@Override
	public float getGravity()
	{
		return !this.beenInGround && this.floatStrength >= 0.7F ? 0.0F : 0.05F;
	}

	@Override
	public float getAirResistance()
	{
		return 0.98F;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer)
	{
		if (!this.worldObj.isRemote)
		{
			if (this.inGround && entityplayer == this.shootingEntity && this.arrowShake <= 0)
			{
				if (entityplayer.inventory.addItemStackToInventory(new ItemStack(AliensVsPredator.items().itemDisc, 1)))
				{
					this.worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
					entityplayer.onItemPickup(this, 1);
					this.setDead();
				}
			}
		}
	}
}
