package com.arisux.avp.entities;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.damagesource.DamageSourceSpear;

public class EntitySpear extends Entity
{
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private int inData = 0;
	private int ticksInAir = 0;
	public int spearShake = 0;
	private float lastDamage;
	private boolean inGround = false;
	public boolean doesArrowBelongToPlayer = false;
	public boolean arrowCritical = false;
	private Block inTile = Blocks.air;
	public Entity shootingEntity;

	public EntitySpear(World world)
	{
		super(world);
		this.setSize(0.5F, 0.5F);
	}

	public EntitySpear(World world, double posX, double posY, double posZ)
	{
		super(world);
		this.setSize(0.5F, 0.5F);
		this.setPosition(posX, posY, posZ);
		this.yOffset = 0.0F;
	}

	public EntitySpear(World world, EntityLivingBase target, float velocity, float lastDamage)
	{
		super(world);
		this.shootingEntity = target;
		this.doesArrowBelongToPlayer = target instanceof EntityPlayer;
		this.setSize(0.5F, 0.5F);
		this.setLocationAndAngles(target.posX, target.posY + target.getEyeHeight(), target.posZ, target.rotationYaw, target.rotationPitch);
		this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
		this.posY -= 0.10000000149011612D;
		this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
		this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
		this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI));
		this.setHeading(this.motionX, this.motionY, this.motionZ, velocity * 1.5F, 1.0F);
		this.lastDamage = lastDamage;
	}

	@Override
	protected void entityInit()
	{
		;
	}

	public void setHeading(double motionX, double motionY, double motionZ, float velocity, float gravity)
	{
		float divider = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
		motionX /= divider;
		motionY /= divider;
		motionZ /= divider;
		motionX += this.rand.nextGaussian() * 0.007499999832361937D * gravity;
		motionY += this.rand.nextGaussian() * 0.007499999832361937D * gravity;
		motionZ += this.rand.nextGaussian() * 0.007499999832361937D * gravity;
		motionX *= velocity;
		motionY *= velocity;
		motionZ *= velocity;
		this.motionX = motionX * 1.1D;
		this.motionY = motionY * 1.1D;
		this.motionZ = motionZ * 1.1D;
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(motionY, MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ)) * 180.0D / Math.PI);
	}

	@Override
	public void setVelocity(double motionX, double motionY, double motionZ)
	{
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(motionY, f) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void onUpdate()
	{
		super.onUpdate();

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ)) * 180.0D / Math.PI);
		}

		Block block = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);

		if (block != Blocks.air)
		{
			block.setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
			AxisAlignedBB vec3d = block.getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);

			if (vec3d != null && vec3d.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))
			{
				this.inGround = true;
			}
		}

		if (this.spearShake > 0)
		{
			--this.spearShake;
		}

		if (this.inGround)
		{
			if (this.worldObj.getBlock(this.xTile, this.yTile, this.zTile) != this.inTile && this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile) != this.inData)
			{
				this.inGround = false;
				this.motionX *= this.rand.nextFloat() * 0.2F;
				this.motionY *= this.rand.nextFloat() * 0.2F;
				this.motionZ *= this.rand.nextFloat() * 0.2F;
				this.ticksInAir = 0;
			}
		}
		else
		{
			++this.ticksInAir;
			Vec3 vecPos = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			Vec3 vecNextPos = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition hit = this.worldObj.func_147447_a(vecPos, vecNextPos, false, true, false);

			if (hit != null)
			{
				vecNextPos = Vec3.createVectorHelper(hit.hitVec.xCoord, hit.hitVec.yCoord, hit.hitVec.zCoord);
			}

			Entity targetEntity = null;
			List<Entity> entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double dist = 0.0D;
			float radius;

			for (int x = 0; x < entities.size(); ++x)
			{
				Entity entity = entities.get(x);

				if (entity.canBeCollidedWith() && (entity != this.shootingEntity || this.ticksInAir >= 5))
				{
					radius = 0.3F;
					MovingObjectPosition intercept = entity.boundingBox.expand(radius, radius, radius).calculateIntercept(vecPos, vecNextPos);

					if (intercept != null)
					{
						double distance = vecPos.distanceTo(intercept.hitVec);

						if (distance < dist || dist == 0.0D)
						{
							targetEntity = entity;
							dist = distance;
						}
					}
				}
			}

			if (targetEntity != null)
			{
				hit = new MovingObjectPosition(targetEntity);
			}

			float velocity;

			if (hit != null)
			{
				if (hit.entityHit != null)
				{
					velocity = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					int damage = (int) Math.ceil(velocity * 2.0D);

					if (this.arrowCritical)
					{
						damage += this.rand.nextInt(damage / 2 + 2);
					}

					DamageSource damageSource = null;

					if (this.shootingEntity == null)
					{
						damageSource = DamageSourceSpear.causeSpearDamage(this, this);
					}
					else
					{
						damageSource = DamageSourceSpear.causeSpearDamage(this, this.shootingEntity);
					}

					if (hit.entityHit.attackEntityFrom(damageSource, damage))
					{
						if (hit.entityHit instanceof EntityLivingBase)
						{
							++((EntityLivingBase) hit.entityHit).arrowHitTimer;
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
					this.xTile = hit.blockX;
					this.yTile = hit.blockY;
					this.zTile = hit.blockZ;
					this.inTile = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
					this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
					this.motionX = ((float) (hit.hitVec.xCoord - this.posX));
					this.motionY = ((float) (hit.hitVec.yCoord - this.posY));
					this.motionZ = ((float) (hit.hitVec.zCoord - this.posZ));
					velocity = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					this.posX -= this.motionX / velocity * 0.05000000074505806D;
					this.posY -= this.motionY / velocity * 0.05000000074505806D;
					this.posZ -= this.motionZ / velocity * 0.05000000074505806D;
					this.worldObj.playSoundAtEntity(this, "random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
					this.inGround = true;
					this.spearShake = 7;
					this.arrowCritical = false;
				}
			}

			if (this.arrowCritical)
			{
				for (int x = 0; x < 4; ++x)
				{
					this.worldObj.spawnParticle("crit", this.posX + this.motionX * x / 4.0D, this.posY + this.motionY * x / 4.0D, this.posZ + this.motionZ * x / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
				}
			}

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			velocity = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

			for (this.rotationPitch = (float) (Math.atan2(this.motionY, velocity) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
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
			float velocityMultiplier = 0.99F;
			radius = 0.05F;

			if (this.isInWater())
			{
				for (int x = 0; x < 4; ++x)
				{
					float multiplier = 0.25F;
					this.worldObj.spawnParticle("bubble", this.posX - this.motionX * multiplier, this.posY - this.motionY * multiplier, this.posZ - this.motionZ * multiplier, this.motionX, this.motionY, this.motionZ);
				}

				velocityMultiplier = 0.8F;
			}

			this.motionX *= velocityMultiplier;
			this.motionY *= velocityMultiplier;
			this.motionZ *= velocityMultiplier;
			this.motionY -= radius;
			this.setPosition(this.posX, this.posY, this.posZ);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		tag.setShort("xTile", (short) this.xTile);
		tag.setShort("yTile", (short) this.yTile);
		tag.setShort("zTile", (short) this.zTile);
		tag.setByte("inData", (byte) this.inData);
		tag.setByte("shake", (byte) this.spearShake);
		tag.setByte("inGround", (byte) (this.inGround ? 1 : 0));
		tag.setBoolean("player", this.doesArrowBelongToPlayer);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag)
	{
		this.xTile = tag.getShort("xTile");
		this.yTile = tag.getShort("yTile");
		this.zTile = tag.getShort("zTile");
		this.inData = tag.getByte("inData") & 255;
		this.spearShake = tag.getByte("shake") & 255;
		this.inGround = tag.getByte("inGround") == 1;
		this.doesArrowBelongToPlayer = tag.getBoolean("player");
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player)
	{
		if (!this.worldObj.isRemote)
		{
			if (this.inGround && this.doesArrowBelongToPlayer && this.spearShake <= 0)
			{
				if (player.inventory.addItemStackToInventory(new ItemStack(AliensVsPredator.instance().items.itemSpear, 1, (int) lastDamage + 5)))
				{
					this.worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
					player.onItemPickup(this, 1);
					this.setDead();
				}
			}
		}
	}

	@Override
	public float getShadowSize()
	{
		return 1.0F;
	}
}
