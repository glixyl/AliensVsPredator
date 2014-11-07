package com.arisux.avp.entities;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class EntityProjectile extends Entity implements IProjectile
{
	public static final int NO_PICKUP = 0;
	public static final int PICKUP_ALL = 1;
	public static final int PICKUP_CREATIVE = 2;
	public static final int PICKUP_OWNER = 3;
	protected int xTile = -1;
	protected int yTile = -1;
	protected int zTile = -1;
	protected Block inTile = Blocks.air;
	protected int inData = 0;
	protected boolean inGround = false;
	public int arrowShake = 0;
	public int pickupMode;
	public Entity shootingEntity;
	protected int ticksInGround;
	protected int ticksInAir = 0;
	public boolean beenInGround;
	protected int extraDamage;
	protected int knockBack;

	public EntityProjectile(World world)
	{
		super(world);
		this.yOffset = 0.0F;
		this.pickupMode = 0;
		this.extraDamage = 0;
		this.knockBack = 0;
		this.setSize(0.5F, 0.5F);
	}

	@Override
	protected void entityInit()
	{
		if (this.canBeCritical())
		{
			this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
		}
	}
	
	protected void setPickupModeFromEntity(EntityLivingBase entityliving)
	{
		if (entityliving instanceof EntityPlayer)
		{
			if (((EntityPlayer) entityliving).capabilities.isCreativeMode)
			{
				this.setPickupMode(2);
			}
		}
		else
		{
			this.setPickupMode(0);
		}
	}

	@Override
	public void setThrowableHeading(double x, double y, double z, float speed, float deviation)
	{
		float f2 = MathHelper.sqrt_double(x * x + y * y + z * z);
		x /= (double) f2;
		y /= (double) f2;
		z /= (double) f2;
		x += this.rand.nextGaussian() * 0.007499999832361937D * (double) deviation;
		y += this.rand.nextGaussian() * 0.007499999832361937D * (double) deviation;
		z += this.rand.nextGaussian() * 0.007499999832361937D * (double) deviation;
		x *= (double) speed;
		y *= (double) speed;
		z *= (double) speed;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		float f3 = MathHelper.sqrt_double(x * x + z * z);
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, (double) f3) * 180.0D / Math.PI);
		this.ticksInGround = 0;
	}

	@Override
	public void setVelocity(double d, double d1, double d2)
	{
		this.motionX = d;
		this.motionY = d1;
		this.motionZ = d2;

		if (this.aimRotation() && this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(d * d + d2 * d2);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(d, d2) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(d1, (double) f) * 180.0D / Math.PI);
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void onUpdate()
	{
		super.onUpdate();

		if (this.aimRotation())
		{
			float i = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, (double) i) * 180.0D / Math.PI);
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

		if (this.inGround)
		{
			Block var18 = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
			int var19 = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);

			if (var18 == this.inTile && var19 == this.inData)
			{
				++this.ticksInGround;
				int var20 = this.getMaxLifetime();

				if (var20 != 0 && this.ticksInGround >= var20)
				{
					this.setDead();
				}
			}
			else
			{
				this.inGround = false;
				this.motionX *= (double) (this.rand.nextFloat() * 0.2F);
				this.motionY *= (double) (this.rand.nextFloat() * 0.2F);
				this.motionZ *= (double) (this.rand.nextFloat() * 0.2F);
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
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d = 0.0D;
			int res;

			for (res = 0; res < list.size(); ++res)
			{
				Entity grav = (Entity) list.get(res);

				if (grav.canBeCollidedWith() && (grav != this.shootingEntity || this.ticksInAir >= 5))
				{
					float i1 = 0.3F;
					AxisAlignedBB f6 = grav.boundingBox.expand((double) i1, (double) i1, (double) i1);
					MovingObjectPosition movingobjectposition1 = f6.calculateIntercept(var17, vec3d1);

					if (movingobjectposition1 != null)
					{
						double d1 = var17.distanceTo(movingobjectposition1.hitVec);

						if (d1 < d || d == 0.0D)
						{
							entity = grav;
							d = d1;
						}
					}
				}
			}

			if (entity != null)
			{
				movingobjectposition = new MovingObjectPosition(entity);
			}

			if (movingobjectposition != null)
			{
				if (movingobjectposition.entityHit != null)
				{
					this.onEntityHit(movingobjectposition.entityHit);
				}
				else
				{
					this.onGroundHit(movingobjectposition);
				}
			}

			if (this.isCritical())
			{
				for (res = 0; res < 2; ++res)
				{
					this.worldObj.spawnParticle("crit", this.posX + this.motionX * (double) res / 4.0D, this.posY + this.motionY * (double) res / 4.0D, this.posZ + this.motionZ * (double) res / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
				}
			}

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			float var23;

			if (this.aimRotation())
			{
				var23 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
				this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

				for (this.rotationPitch = (float) (Math.atan2(this.motionY, (double) var23) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
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
			}

			var23 = this.getAirResistance();
			float var22 = this.getGravity();

			if (this.isInWater())
			{
				this.beenInGround = true;

				for (int var21 = 0; var21 < 4; ++var21)
				{
					float var24 = 0.25F;
					this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double) var24, this.posY - this.motionY * (double) var24, this.posZ - this.motionZ * (double) var24, this.motionX, this.motionY, this.motionZ);
				}

				var23 *= 0.8080808F;
			}

			this.motionX *= (double) var23;
			this.motionY *= (double) var23;
			this.motionZ *= (double) var23;
			this.motionY -= (double) var22;
			this.setPosition(this.posX, this.posY, this.posZ);
			// this.doBlockCollisions();
		}
	}
	
	public void onEntityHit(Entity entity)
	{
		this.bounceBack();
	}

	public void onGroundHit(MovingObjectPosition mop)
	{
		this.xTile = mop.blockX;
		this.yTile = mop.blockY;
		this.zTile = mop.blockZ;
		this.inTile = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
		this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
		this.motionX = (double) ((float) (mop.hitVec.xCoord - this.posX));
		this.motionY = (double) ((float) (mop.hitVec.yCoord - this.posY));
		this.motionZ = (double) ((float) (mop.hitVec.zCoord - this.posZ));
		float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		this.posX -= this.motionX / (double) f1 * 0.05D;
		this.posY -= this.motionY / (double) f1 * 0.05D;
		this.posZ -= this.motionZ / (double) f1 * 0.05D;
		this.inGround = true;
		this.beenInGround = true;
		this.setCritical(false);
		this.arrowShake = this.getMaxArrowShake();
		this.playHitSound();

		if (this.inTile != Blocks.air)
		{
			this.inTile.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
		}
	}

	protected void bounceBack()
	{
		this.motionX *= -0.1D;
		this.motionY *= -0.1D;
		this.motionZ *= -0.1D;
		this.rotationYaw += 180.0F;
		this.prevRotationYaw += 180.0F;
		this.ticksInAir = 0;
	}

	public final double getTotalVelocity()
	{
		return Math.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
	}

	public boolean aimRotation()
	{
		return true;
	}

	public int getMaxLifetime()
	{
		return 1200;
	}

	public ItemStack getPickupItem()
	{
		return null;
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
	}

	public boolean canBeCritical()
	{
		return false;
	}

	public EntityProjectile setCritical(boolean flag)
	{
		if (this.canBeCritical())
		{
			if (flag)
			{
				this.dataWatcher.updateObject(16, Byte.valueOf((byte) 1));
			}
			else
			{
				this.dataWatcher.updateObject(16, Byte.valueOf((byte) 0));
			}
		}
		
		return this;
	}

	public boolean isCritical()
	{
		return this.canBeCritical() && this.dataWatcher.getWatchableObjectByte(16) != 0;
	}

	public void setExtraDamage(int i)
	{
		this.extraDamage = i;
	}

	public void setKnockback(int i)
	{
		this.knockBack = i;
	}

	public void setPickupMode(int i)
	{
		this.pickupMode = i;
	}

	public int getPickupMode()
	{
		return this.pickupMode;
	}

	public boolean canPickup(EntityPlayer entityplayer)
	{
		return this.pickupMode == 1 ? true : (this.pickupMode == 2 ? entityplayer.capabilities.isCreativeMode : (this.pickupMode == 3 ? entityplayer == this.shootingEntity : false));
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer)
	{
		if (!this.worldObj.isRemote && this.inGround && this.arrowShake <= 0)
		{
			if (this.canPickup(entityplayer))
			{
				ItemStack item = this.getPickupItem();

				if (item == null)
				{
					return;
				}

				if (this.pickupMode == 2 && entityplayer.capabilities.isCreativeMode || entityplayer.inventory.addItemStackToInventory(item))
				{
					this.worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
					this.onItemPickup(entityplayer);
					this.setDead();
				}
			}
		}
	}

	protected void onItemPickup(EntityPlayer entityplayer)
	{
		entityplayer.onItemPickup(this, 1);

		if (!this.isDead && !this.worldObj.isRemote)
		{
			((WorldServer) this.worldObj).getEntityTracker();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
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
		nbttagcompound.setByte("inData", (byte) this.inData);
		nbttagcompound.setByte("shake", (byte) this.arrowShake);
		nbttagcompound.setBoolean("inGround", this.inGround);
		nbttagcompound.setBoolean("beenInGround", this.beenInGround);
		nbttagcompound.setByte("pickup", (byte) this.pickupMode);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		this.xTile = nbttagcompound.getShort("xTile");
		this.yTile = nbttagcompound.getShort("yTile");
		this.zTile = nbttagcompound.getShort("zTile");
		this.inData = nbttagcompound.getByte("inData") & 255;
		this.arrowShake = nbttagcompound.getByte("shake") & 255;
		this.inGround = nbttagcompound.getBoolean("inGround");
		this.beenInGround = nbttagcompound.getBoolean("beenInGrond");
		this.pickupMode = nbttagcompound.getByte("pickup");
	}
}
