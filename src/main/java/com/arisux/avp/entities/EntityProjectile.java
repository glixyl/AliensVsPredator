package com.arisux.avp.entities;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.*;
import net.minecraft.world.World;

import com.arisux.airi.lib.WorldUtil.Entities;

import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class EntityProjectile extends EntityArrow implements IThrowableEntity
{
	protected int xTile;
	protected int yTile;
	protected int zTile;
	protected Block inTile;
	protected int inData;
	protected boolean inGround;
	protected int ticksInGround;
	protected int ticksInAir;
	public boolean beenInGround;
	public float additionalDamage;
	public int knockback;

	public EntityProjectile(World world)
	{
		super(world);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = null;
		this.inData = 0;
		this.inGround = false;
		this.arrowShake = 0;
		this.ticksInAir = 0;
		this.yOffset = 0F;
		this.additionalDamage = 0;
		this.knockback = 0;
		this.setSize(0.5F, 0.5F);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	public Entity getThrower()
	{
		return shootingEntity;
	}

	@Override
	public void setThrower(Entity entity)
	{
		shootingEntity = entity;
	}

	@Override
	public void setThrowableHeading(double posX, double posY, double posZ, float velocity, float deviation)
	{
		float sq = MathHelper.sqrt_double(posX * posX + posY * posY + posZ * posZ);
		posX /= sq;
		posY /= sq;
		posZ /= sq;
		posX += rand.nextGaussian() * 0.0075F * deviation;
		posY += rand.nextGaussian() * 0.0075F * deviation;
		posZ += rand.nextGaussian() * 0.0075F * deviation;
		posX *= velocity;
		posY *= velocity;
		posZ *= velocity;
		this.motionX = posX;
		this.motionY = posY;
		this.motionZ = posZ;
		float f3 = MathHelper.sqrt_double(posX * posX + posZ * posZ);
		this.prevRotationYaw = rotationYaw = (float) ((Math.atan2(posX, posZ) * 180D) / Math.PI);
		this.prevRotationPitch = rotationPitch = (float) ((Math.atan2(posY, f3) * 180D) / Math.PI);
		this.ticksInGround = 0;
	}

	@Override
	public void setVelocity(double motionX, double motionY, double motionZ)
	{
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;

		if (aimRotation() && prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
		{
			this.prevRotationYaw = rotationYaw = (float) ((Math.atan2(motionX, motionZ) * 180D) / Math.PI);
			this.prevRotationPitch = rotationPitch = (float) ((Math.atan2(motionY, MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ)) * 180D) / Math.PI);
			this.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			this.ticksInGround = 0;
		}
	}

	@Override
	public void onUpdate()
	{
		this.onEntityUpdate();
	}

	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();

		if (aimRotation())
		{
			prevRotationYaw = rotationYaw = (float) ((Math.atan2(motionX, motionZ) * 180D) / Math.PI);
			prevRotationPitch = rotationPitch = (float) ((Math.atan2(motionY, MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ)) * 180D) / Math.PI);
		}

		Block block = worldObj.getBlock(this.xTile, this.yTile, this.zTile);

		if (block != null)
		{
			block.setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
			AxisAlignedBB blockBounds = block.getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);

			if (blockBounds != null && blockBounds.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))
			{
				this.inGround = true;
			}
		}

		if (this.arrowShake > 0)
		{
			this.arrowShake--;
		}

		if (this.inGround)
		{
			Block blockIn = worldObj.getBlock(this.xTile, this.yTile, this.zTile);
			int blockMetaIn = worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);

			if (blockIn == this.inTile && blockMetaIn == this.inData)
			{
				ticksInGround++;

				if (this.getLifetime() != 0 && this.ticksInGround >= this.getLifetime())
				{
					this.setDead();
				}
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
			return;
		}

		ticksInAir++;

		Vec3 vecPos = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		Vec3 vecPosNext = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition hit = worldObj.func_147447_a(vecPos, vecPosNext, false, true, false);

		if (hit != null)
		{
			vecPosNext = Vec3.createVectorHelper(hit.hitVec.xCoord, hit.hitVec.yCoord, hit.hitVec.zCoord);
		}

		Entity target = null;
		@SuppressWarnings("unchecked")
		List<Entity> possibleTargets = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
		double distanceTo = 0.0D;

		for (int x = 0; x < possibleTargets.size(); x++)
		{
			Entity possibleTarget = possibleTargets.get(x);
			if (!possibleTarget.canBeCollidedWith() || possibleTarget == shootingEntity && ticksInAir < 5)
			{
				continue;
			}
			float radius = 0.3F;
			AxisAlignedBB targetBounds = possibleTarget.boundingBox.expand(radius, radius, radius);
			MovingObjectPosition intercept = targetBounds.calculateIntercept(vecPos, vecPosNext);

			if (intercept == null)
			{
				continue;
			}

			double distance = vecPos.distanceTo(intercept.hitVec);

			if (distance < distanceTo || distanceTo == 0.0D)
			{
				target = possibleTarget;
				distanceTo = distance;
			}
		}

		if (target != null)
		{
			hit = new MovingObjectPosition(target);
		}

		if (hit != null)
		{
			if (hit.entityHit != null)
			{
				this.onEntityHit(hit.entityHit);
			}
			else
			{
				this.onGroundHit(hit);
			}
		}

		if (this.getIsCritical())
		{
			for (int x = 0; x < 2; x++)
			{
				this.worldObj.spawnParticle("crit", posX + (motionX * x) / 4D, posY + (motionY * x) / 4D, posZ + (motionZ * x) / 4D, -motionX, -motionY + 0.2D, -motionZ);
			}
		}

		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;

		if (this.aimRotation())
		{
			this.rotationYaw = (float) ((Math.atan2(this.motionX, this.motionZ) * 180D) / Math.PI);

			for (this.rotationPitch = (float) ((Math.atan2(this.motionY, MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ)) * 180D) / Math.PI); this.rotationPitch - this.prevRotationPitch < -180F; this.prevRotationPitch -= 360F)
			{
				;
			}
			while (this.rotationPitch - this.prevRotationPitch >= 180F)
			{
				this.prevRotationPitch += 360F;
			}
			while (this.rotationYaw - this.prevRotationYaw < -180F)
			{
				this.prevRotationYaw -= 360F;
			}
			while (this.rotationYaw - this.prevRotationYaw >= 180F)
			{
				this.prevRotationYaw += 360F;
			}

			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		}

		float resistance = getAirResistance();
		float gravity = getGravity();

		if (this.isInWater())
		{
			this.beenInGround = true;

			for (int x = 0; x < 4; x++)
			{
				float radius = 0.25F;
				this.worldObj.spawnParticle("bubble", this.posX - this.motionX * radius, this.posY - this.motionY * radius, this.posZ - this.motionZ * radius, this.motionX, this.motionY, this.motionZ);
			}

			resistance *= 0.80808080F;
		}

		this.motionX *= resistance;
		this.motionY *= resistance;
		this.motionZ *= resistance;
		this.motionY -= gravity;
		this.setPosition(posX, posY, posZ);
		Entities.applyCollision(this);
	}

	public void onEntityHit(Entity entity)
	{
		this.applyEntityHitEffects(entity);
		this.bounce();
	}

	public void applyEntityHitEffects(Entity entity)
	{
		if (this.isBurning() && !(entity instanceof EntityEnderman))
		{
			entity.setFire(5);
		}

		if (entity instanceof EntityLivingBase)
		{
			EntityLivingBase entityliving = (EntityLivingBase) entity;

			if (this.knockback > 0)
			{
				float sq = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

				if (sq > 0.0F)
				{
					entity.addVelocity(this.motionX * this.knockback * 0.6D / sq, 0.1D, this.motionZ * this.knockback * 0.6D / sq);
				}
			}

			if (this.shootingEntity instanceof EntityLivingBase)
			{
				EnchantmentHelper.func_151384_a(entityliving, this.shootingEntity);
				EnchantmentHelper.func_151385_b((EntityLivingBase) this.shootingEntity, entityliving);
			}

			if (this.shootingEntity instanceof EntityPlayerMP && this.shootingEntity != entity && entity instanceof EntityPlayer)
			{
				((EntityPlayerMP) shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0));
			}
		}
	}

	public void onGroundHit(MovingObjectPosition movingObjPos)
	{
		this.xTile = movingObjPos.blockX;
		this.yTile = movingObjPos.blockY;
		this.zTile = movingObjPos.blockZ;
		this.inTile = worldObj.getBlock(this.xTile, this.yTile, this.zTile);
		this.inData = worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
		this.motionX = movingObjPos.hitVec.xCoord - this.posX;
		this.motionY = movingObjPos.hitVec.yCoord - this.posY;
		this.motionZ = movingObjPos.hitVec.zCoord - this.posZ;
		float sq = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		this.posX -= this.motionX / sq * 0.05D;
		this.posY -= this.motionY / sq * 0.05D;
		this.posZ -= this.motionZ / sq * 0.05D;
		this.inGround = true;
		this.beenInGround = true;
		this.setIsCritical(false);
		this.arrowShake = this.getMaxArrowShake();
		this.playHitSound();

		if (this.inTile != null)
		{
			this.inTile.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
		}
	}

	protected void bounce()
	{
		this.motionX *= -0.1D;
		this.motionY *= -0.1D;
		this.motionZ *= -0.1D;
		this.rotationYaw += 180F;
		this.prevRotationYaw += 180F;
		this.ticksInAir = 0;
	}

	public final double getVelocity()
	{
		return Math.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
	}

	public ItemStack getItemstack()
	{
		return null;
	}

	public boolean aimRotation()
	{
		return true;
	}

	public int getLifetime()
	{
		return 1200;
	}

	public float getAirResistance()
	{
		return 0.99F;
	}

	public float getGravity()
	{
		return 0.05F;
	}

	public int getMaxArrowShake()
	{
		return 7;
	}

	public void playHitSound()
	{
		;
	}

	public boolean canBeCritical()
	{
		return false;
	}

	@Override
	public void setIsCritical(boolean flag)
	{
		if (this.canBeCritical())
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (flag ? 1 : 0)));
		}
	}

	@Override
	public boolean getIsCritical()
	{
		return this.canBeCritical() && this.dataWatcher.getWatchableObjectByte(16) != 0;
	}

	public void setAdditionalDamage(float additionalDamage)
	{
		this.additionalDamage = additionalDamage;
	}

	@Override
	public void setKnockbackStrength(int knockback)
	{
		this.knockback = knockback;
	}

	public boolean canPickup(EntityPlayer entityplayer)
	{
		return true;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer)
	{
		if (this.inGround && this.arrowShake <= 0)
		{
			if (this.canPickup(entityplayer))
			{
				if (!this.worldObj.isRemote)
				{
					ItemStack item = new ItemStack(this.getItemstack().getItem(), 1, this.getItemstack().getItemDamage() + 1);

					if (item != null && entityplayer.inventory.addItemStackToInventory(item))
					{
						this.worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
						this.onItemPickup(entityplayer);
						this.setDead();
					}
				}
			}
		}
	}

	protected void onItemPickup(EntityPlayer entityplayer)
	{
		entityplayer.onItemPickup(this, 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 1.0F;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setShort("xTile", (short) this.xTile);
		nbttagcompound.setShort("yTile", (short) this.yTile);
		nbttagcompound.setShort("zTile", (short) this.zTile);
		nbttagcompound.setByte("inTile", (byte) Block.getIdFromBlock(this.inTile));
		nbttagcompound.setByte("inData", (byte) this.inData);
		nbttagcompound.setByte("shake", (byte) this.arrowShake);
		nbttagcompound.setBoolean("inGround", this.inGround);
		nbttagcompound.setBoolean("beenInGround", this.beenInGround);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		this.xTile = nbttagcompound.getShort("xTile");
		this.yTile = nbttagcompound.getShort("yTile");
		this.zTile = nbttagcompound.getShort("zTile");
		this.inTile = Block.getBlockById(nbttagcompound.getByte("inTile") & 0xFF);
		this.inData = nbttagcompound.getByte("inData") & 0xFF;
		this.arrowShake = nbttagcompound.getByte("shake") & 0xFF;
		this.inGround = nbttagcompound.getBoolean("inGround");
		this.beenInGround = nbttagcompound.getBoolean("beenInGrond");
	}
}
