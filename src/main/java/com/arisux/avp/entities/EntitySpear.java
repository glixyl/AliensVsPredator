package com.arisux.avp.entities;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import com.arisux.avp.DamageSources;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

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
		this.setThrowableHeading(motionX, motionY, motionZ, 1.8F, 1.0F);
	}

	@Override
	public void onUpdate()
	{
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float i = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, i) * 180.0D / Math.PI);
		}

		Block block = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);

		if (block != Blocks.air)
		{
			block.setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);

			if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
			{
				AxisAlignedBB vec3d = block.getSelectedBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);

				if (vec3d != null && vec3d.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))
				{
					this.inGround = true;
				}
			}
		}

		if (this.arrowShake > 0)
		{
			--this.arrowShake;
		}

		if (this.inGround)
		{
			Block var18 = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
			int var19 = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);

			if (var18 == this.inTile && var19 == this.inData)
			{
				++this.ticksInGround;
			}
			else
			{
				this.inGround = false;
				this.motionX *= this.rand.nextFloat() * 0.2F;
				this.motionY *= this.rand.nextFloat() * 0.2F;
				this.motionZ *= this.rand.nextFloat() * 0.2F;
				this.ticksInGround = 0;
				this.ticksInAir = 0;
			}
		}
		else
		{
			++this.ticksInAir;
			Vec3 var17 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			Vec3 vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(var17, vec3d1, false, true, true);
			var17 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

			if (movingobjectposition != null)
			{
				vec3d1 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
			}

			Entity entity = null;
			
			@SuppressWarnings("unchecked")
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			
			double d = 0.0D;
			int f3;
			float f6;

			for (f3 = 0; f3 < list.size(); ++f3)
			{
				Entity f4 = list.get(f3);

				if (f4.canBeCollidedWith() && (f4 != this.shootingEntity || this.ticksInAir >= 5))
				{
					f6 = 0.3F;
					AxisAlignedBB k1 = f4.boundingBox.expand(f6, f6, f6);
					MovingObjectPosition f7 = k1.calculateIntercept(var17, vec3d1);

					if (f7 != null)
					{
						double d1 = var17.distanceTo(f7.hitVec);

						if (d1 < d || d == 0.0D)
						{
							entity = f4;
							d = d1;
						}
					}
				}
			}

			if (entity != null)
			{
				movingobjectposition = new MovingObjectPosition(entity);
			}

			float var20;

			if (movingobjectposition != null)
			{
				if (movingobjectposition.entityHit != null)
				{
					var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					int var23 = (int) Math.ceil(var20 * 2.0D);

					DamageSource damagesource = null;

					if (this.shootingEntity == null)
					{
						damagesource = DamageSources.causeShurikenDamage(this, this);
					}
					else
					{
						damagesource = DamageSources.causeShurikenDamage(this, this.shootingEntity);
					}

					if (movingobjectposition.entityHit.attackEntityFrom(damagesource, var23))
					{
						if (movingobjectposition.entityHit instanceof EntityLivingBase)
						{
							++((EntityLivingBase) movingobjectposition.entityHit).arrowHitTimer;
						}

						this.worldObj.playSoundAtEntity(this, "random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
					}
					else
					{
						this.motionX *= -0.10000000149011612D;
						this.motionY *= -0.10000000149011612D;
						this.motionZ *= -0.10000000149011612D;
						this.rotationYaw += 180.0F;
						this.prevRotationYaw += 180.0F;
						this.ticksInAir = 0;
					}
				}
				else
				{
					this.xTile = movingobjectposition.blockX;
					this.yTile = movingobjectposition.blockY;
					this.zTile = movingobjectposition.blockZ;
					this.inTile = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
					this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
					this.motionX = ((float) (movingobjectposition.hitVec.xCoord - this.posX));
					this.motionY = ((float) (movingobjectposition.hitVec.yCoord - this.posY));
					this.motionZ = ((float) (movingobjectposition.hitVec.zCoord - this.posZ));
					var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					this.posX -= this.motionX / var20 * 0.05000000074505806D;
					this.posY -= this.motionY / var20 * 0.05000000074505806D;
					this.posZ -= this.motionZ / var20 * 0.05000000074505806D;
					this.worldObj.playSoundAtEntity(this, "random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
					this.inGround = true;
					this.arrowShake = 7;
				}
			}

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

			for (this.rotationPitch = (float) (Math.atan2(this.motionY, var20) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
			{
				;
			}

			while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
			{
				this.prevRotationPitch += 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw < -180.0F)
			{
				this.prevRotationYaw -= 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
			{
				this.prevRotationYaw += 360.0F;
			}

			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
			float var22 = 0.99F;

			if (this.isInWater())
			{
				for (int var24 = 0; var24 < 4; ++var24)
				{
					float var25 = 0.25F;
					this.worldObj.spawnParticle("bubble", this.posX - this.motionX * var25, this.posY - this.motionY * var25, this.posZ - this.motionZ * var25, this.motionX, this.motionY, this.motionZ);
				}

				var22 = 0.8F;
			}

			this.motionX *= var22;
			this.motionY *= var22;
			this.motionZ *= var22;
			this.motionY -= this.motionX + this.motionZ > 0.7 ? 0.01F : 0.09F;
			this.setPosition(this.posX, this.posY, this.posZ);
		}
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
	public float getGravity()
	{
		return 0.01F;
	}
	
	@Override
	public boolean canPickup(EntityPlayer entityplayer)
	{
		return true;
	}
}
