package com.arisux.avp.entities;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class EntityBullet extends Entity
{
	private int xTile;
	private int yTile;
	private int zTile;
	private Block inTile;
	private int inData;
	private int arrowShake;
	private int ticksInGround;
	private int ticksInAir;
	private boolean inGround;
	private boolean doesArrowBelongToPlayer;
	private boolean arrowCritical;
	private boolean physics;
	public Entity shootingEntity;
	public EntityArrow entityarrow;
	public double damage;

	public EntityBullet(World world)
	{
		super(world);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = Blocks.air;
		this.inData = 0;
		this.inGround = false;
		this.doesArrowBelongToPlayer = false;
		this.arrowShake = 0;
		this.ticksInAir = 0;
		this.arrowCritical = false;
		this.setSize(0.5F, 0.5F);
	}

	public EntityBullet(World world, double d, double d1, double d2)
	{
		super(world);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = Blocks.air;
		this.inData = 0;
		this.inGround = false;
		this.doesArrowBelongToPlayer = false;
		this.arrowShake = 0;
		this.ticksInAir = 0;
		this.arrowCritical = true;
		this.setSize(0.5F, 0.5F);
		this.setPosition(d, d1, d2);
		this.yOffset = 0.0F;
	}
	
	public EntityBullet(World world, Entity entity, float velocity, double damage)
	{
		super(world);
		this.damage = damage;
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = Blocks.air;
		this.inData = 0;
		this.inGround = false;
		this.physics = true;
		this.doesArrowBelongToPlayer = false;
		this.arrowShake = 0;
		this.ticksInAir = 0;
		this.arrowCritical = true;
		this.shootingEntity = entity;
		this.doesArrowBelongToPlayer = entity instanceof EntityPlayer;
		this.setSize(0.5F, 0.5F);
		this.setLocationAndAngles(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ, entity.rotationYaw, entity.rotationPitch);
		this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
		this.posY -= 0.10000000149011612D;
		this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
		this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
		this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI));
        this.setArrowHeading(this.motionX, this.motionY, this.motionZ, velocity * 1.5F, 1.0F);
	}

	public EntityBullet(World world, Entity entity, Entity targetEntity, float velocity, double damage)
	{
		super(world);
		this.damage = damage;
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = Blocks.air;
		this.inData = 0;
		this.inGround = false;
		this.physics = true;
		this.doesArrowBelongToPlayer = false;
		this.arrowShake = 0;
		this.ticksInAir = 0;
		this.arrowCritical = true;
		this.shootingEntity = entity;
		this.doesArrowBelongToPlayer = entity instanceof EntityPlayer;
		this.setSize(0.5F, 0.5F);
		this.setLocationAndAngles(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ, entity.rotationYaw, entity.rotationPitch);
		this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
		this.posY -= 0.10000000149011612D;
		this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
		this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
		this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI));
		
        double d0 = targetEntity.posX - entity.posX;
        double d1 = targetEntity.boundingBox.minY + targetEntity.height / 3.0F - this.posY;
        double d2 = targetEntity.posZ - entity.posZ;
        double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D)
        {
            float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(entity.posX + d4, this.posY, entity.posZ + d5, f2, f3);
            this.yOffset = 0.0F;
            float f4 = (float)d3 * 0.2F;
            this.setThrowableHeading(d0, d1 + f4, d2, velocity, damage);
        }
	}
	
    public void setThrowableHeading(double posX, double posY, double posZ, float velocity, double damage)
    {
        float f2 = MathHelper.sqrt_double(posX * posX + posY * posY + posZ * posZ);
        posX /= f2;
        posY /= f2;
        posZ /= f2;
        posX += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * damage;
        posY += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * damage;
        posZ += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * damage;
        posX *= velocity;
        posY *= velocity;
        posZ *= velocity;
        this.motionX = posX;
        this.motionY = posY;
        this.motionZ = posZ;
        float f3 = MathHelper.sqrt_double(posX * posX + posZ * posZ);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(posX, posZ) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(posY, f3) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }

	@Override
	protected void entityInit()
	{
		;
	}

	public void setArrowHeading(double d, double d1, double d2, float f, float f1)
	{
		float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
		d /= f2;
		d1 /= f2;
		d2 /= f2;
		d += this.rand.nextGaussian() * 0.007499999832361937D * f1;
		d1 += this.rand.nextGaussian() * 0.007499999832361937D * f1;
		d2 += this.rand.nextGaussian() * 0.007499999832361937D * f1;
		d *= f;
		d1 *= f;
		d2 *= f;
		this.motionX = d;
		this.motionY = d1;
		this.motionZ = d2;
		float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(d, d2) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(d1, f3) * 180.0D / Math.PI);
		this.ticksInGround = 0;
	}

	@Override
	public void setVelocity(double d, double d1, double d2)
	{
		this.motionX = d;
		this.motionY = d1;
		this.motionZ = d2;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(d * d + d2 * d2);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(d, d2) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(d1, f) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onUpdate()
	{
		if (this.ticksInAir > 160 || this.ticksInGround > 20)
		{
			this.setDead();
		}

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float i = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, i) * 180.0D / Math.PI);
		}

		Block var16 = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);

		if (var16 != Blocks.air)
		{
			var16.setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
			AxisAlignedBB vec3d = var16.getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);

			if (vec3d != null && vec3d.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))
			{
				this.inGround = true;
			}
		}

		if (this.arrowShake > 0)
		{
			--this.arrowShake;
		}

		if (this.isInWater())
		{
			this.setDead();
		}

		if (this.inGround)
		{
			Block var18 = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
			int var19 = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);

			if (var18 == Blocks.glass_pane)
			{
				this.worldObj.setBlockToAir(this.xTile, this.yTile, this.zTile);
				this.worldObj.playSoundEffect(this.xTile + 0.5D, this.yTile + 0.5D, this.zTile + 0.5D, "random.glass3", 1.0F, 1.0F);
				this.shootingEntity.playSound("random.glass1", 1.0F, 1.0F);
			}

			if (var18 == this.inTile && var19 == this.inData)
			{
				++this.ticksInGround;

				if (!this.isInWater())
				{
					this.setDead();
				}

				if (this.ticksInGround > 20)
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
		}
		else
		{
			++this.ticksInAir;
			Vec3 var17 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			Vec3 vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(var17, vec3d1, false, true, false);
			var17 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

			if (movingobjectposition != null)
			{
				vec3d1 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
			}

			Entity entity = null;
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d = 0.0D;
			float f6;

			for (int f3 = 0; f3 < list.size(); ++f3)
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
					if (this.shootingEntity instanceof EntityTurret)
					{
						EntityTurret entityTurret = (EntityTurret) this.shootingEntity;

						if (!entityTurret.getTileEntity().isSafe(movingobjectposition.entityHit))
						{
							entityTurret.getTileEntity().setTargetEntity(movingobjectposition.entityHit);
						}
					}

					var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					int var23 = (int) Math.ceil(var20 * damage);

					if (this.arrowCritical)
					{
						var23 += this.rand.nextInt(var23 / 2 + 2);
					}

					DamageSource var21 = null;

					if (this.shootingEntity == null)
					{
						var21 = DamageSource.causeArrowDamage(this.entityarrow, this);
					}
					else
					{
						var21 = DamageSource.causeArrowDamage(this.entityarrow, this.shootingEntity);
					}

					movingobjectposition.entityHit.hurtResistantTime = 0;

					if (movingobjectposition.entityHit instanceof EntityLivingBase)
						((EntityLivingBase) movingobjectposition.entityHit).getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.9);

					if (movingobjectposition.entityHit instanceof EntityLivingBase && movingobjectposition.entityHit.attackEntityFrom(var21, var23))
					{
						this.setDead();
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
					this.inGround = true;
					this.arrowShake = 7;
					this.arrowCritical = false;
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
			f6 = 0.05F;

			// if (this.isInWater())
			{
				for (int var24 = 0; var24 < 5; ++var24)
				{
					this.worldObj.spawnParticle("largesmoke", this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ);
				}

				var22 = 0.8F;
			}

			if (this.isInWater())
			{
				for (int var24 = 0; var24 < 5; ++var24)
				{
					this.worldObj.spawnParticle("bubble", this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ);
				}

				var22 = 0.8F;
			}

			if (this.physics)
			{
				this.motionX *= var22;
				this.motionY *= var22;
				this.motionZ *= var22;
				this.motionY -= 0.05F;
			}
			this.setPosition(this.posX, this.posY, this.posZ);
		}
	}

	public void setPhysics(boolean physics)
	{
		this.physics = physics;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound var1)
	{
		var1.setShort("xTile", (short) this.xTile);
		var1.setShort("yTile", (short) this.yTile);
		var1.setShort("zTile", (short) this.zTile);
		var1.setByte("inData", (byte) this.inData);
		var1.setByte("shake", (byte) this.arrowShake);
		var1.setByte("inGround", (byte) (this.inGround ? 1 : 0));
		var1.setBoolean("player", this.doesArrowBelongToPlayer);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound var1)
	{
		this.xTile = var1.getShort("xTile");
		this.yTile = var1.getShort("yTile");
		this.zTile = var1.getShort("zTile");
		this.inData = var1.getByte("inData") & 255;
		this.arrowShake = var1.getByte("shake") & 255;
		this.inGround = var1.getByte("inGround") == 1;
		this.doesArrowBelongToPlayer = var1.getBoolean("player");
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer)
	{
		if (!this.doesArrowBelongToPlayer)
		{
			this.worldObj.spawnParticle("flame", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			this.setDead();
		}
	}

	@Override
	public float getShadowSize()
	{
		return 0.0F;
	}
}
