package com.arisux.avp.entities;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.arisux.avp.damagesource.DamageSourceAcidShot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityAcidSpit extends Entity implements IProjectile
{
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private Block inTile;
	private int inData;
	private boolean inGround;
	public int canBePickedUp;
	public int AcidShake;
	public Entity shootingEntity;
	private int ticksInGround;
	private int ticksInAir;
	private double damage = 2.0D;
	private int knockbackStrength;

	public EntityAcidSpit(World par1World)
	{
		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
	}

	public EntityAcidSpit(World par1World, double par2, double par4, double par6)
	{
		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
		this.setPosition(par2, par4, par6);
		this.yOffset = 0.0F;
	}

	public EntityAcidSpit(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, float par5)
	{
		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = par2EntityLivingBase;

		if (par2EntityLivingBase instanceof EntityPlayer)
		{
			this.canBePickedUp = 1;
		}

		this.posY = par2EntityLivingBase.posY + (double) par2EntityLivingBase.getEyeHeight() - 0.10000000149011612D;
		double d0 = par3EntityLivingBase.posX - par2EntityLivingBase.posX;
		double d1 = par3EntityLivingBase.boundingBox.minY + (double) (par3EntityLivingBase.height / 3.0F) - this.posY;
		double d2 = par3EntityLivingBase.posZ - par2EntityLivingBase.posZ;
		double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D)
		{
			float f2 = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI));
			double d4 = d0 / d3;
			double d5 = d2 / d3;
			this.setLocationAndAngles(par2EntityLivingBase.posX + d4, this.posY, par2EntityLivingBase.posZ + d5, f2, f3);
			this.yOffset = 0.0F;
			float f4 = (float) d3 * 0.2F;
			this.setThrowableHeading(d0, d1 + (double) f4, d2, par4, par5);
		}
	}

	public EntityAcidSpit(World par1World, EntityLivingBase par2EntityLivingBase, float par3)
	{
		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = par2EntityLivingBase;

		if (par2EntityLivingBase instanceof EntityPlayer)
		{
			this.canBePickedUp = 1;
		}

		this.setSize(0.5F, 0.5F);
		this.setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + (double) par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
		this.posX -= (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.posY -= 0.10000000149011612D;
		this.posZ -= (double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = (double) (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI));
		this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI));
		this.motionY = (double) (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI));
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, par3 * 1.5F, 1.0F);
	}

	protected void entityInit()
	{
		this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
	}

	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
	 */
	public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8)
	{
		float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
		par1 /= (double) f2;
		par3 /= (double) f2;
		par5 /= (double) f2;
		par1 += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) par8;
		par3 += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) par8;
		par5 += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) par8;
		par1 *= (double) par7;
		par3 *= (double) par7;
		par5 *= (double) par7;
		this.motionX = par1;
		this.motionY = par3;
		this.motionZ = par5;
		float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3, (double) f3) * 180.0D / Math.PI);
		this.ticksInGround = 0;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
	 * posY, posZ, yaw, pitch
	 */
	public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
	{
		this.setPosition(par1, par3, par5);
		this.setRotation(par7, par8);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	public void setVelocity(double par1, double par3, double par5)
	{
		this.motionX = par1;
		this.motionY = par3;
		this.motionZ = par5;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3, (double) f) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@SuppressWarnings("unchecked")
	public void onUpdate()
	{
		super.onUpdate();

		++this.ticksInGround;

		if (this.ticksInGround >= 200)
		{
			this.setDead();
		}

		Block block;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float var16 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, (double) var16) * 180.0D / Math.PI);
		}

		block = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);

		if (block != null)
		{
			// setBlockBoundsBasedOnState
			block.setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
			// getCollisionBoundingBoxFromPool
			AxisAlignedBB vec3 = block.getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);

			if (vec3 != null && vec3.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))
			{
				this.inGround = true;
			}
		}

		if (this.AcidShake > 0)
		{
			--this.AcidShake;
		}

		if (this.inGround)
		{
			Block var17 = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
			int vec31 = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);

			if (var17 == this.inTile && vec31 == this.inData)
			{

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
			Vec3 var18 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			Vec3 var19 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			// rayTraceBlocks_do_do
			MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(var18, var19, false, true, true);
			var18 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			var19 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

			if (movingobjectposition != null)
			{
				var19 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
			}

			Entity entity = null;
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;
			int l;
			float f1;

			for (l = 0; l < list.size(); ++l)
			{
				Entity f2 = (Entity) list.get(l);

				if (f2.canBeCollidedWith() && (f2 != this.shootingEntity || this.ticksInAir >= 5))
				{
					f1 = 0.3F;
					AxisAlignedBB f3 = f2.boundingBox.expand((double) f1, (double) f1, (double) f1);
					MovingObjectPosition f4 = f3.calculateIntercept(var18, var19);

					if (f4 != null)
					{
						double j1 = var18.distanceTo(f4.hitVec);

						if (j1 < d0 || d0 == 0.0D)
						{
							entity = f2;
							d0 = j1;
						}
					}
				}
			}

			if (entity != null)
			{
				movingobjectposition = new MovingObjectPosition(entity);
			}

			if (movingobjectposition != null && movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityPlayer)
			{
				EntityPlayer var20 = (EntityPlayer) movingobjectposition.entityHit;

				if (var20.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer) this.shootingEntity).canAttackPlayer(var20))
				{
					movingobjectposition = null;
				}
			}

			float var21;
			float var22;

			if (movingobjectposition != null)
			{
				if (movingobjectposition.entityHit != null)
				{
					var22 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					int var23 = MathHelper.ceiling_double_int((double) var22 * this.damage);

					if (this.getIsCritical())
					{
						var23 += this.rand.nextInt(var23 / 2 + 2);
					}

					DamageSource var25 = null;

					if (this.shootingEntity == null)
					{
						var25 = DamageSourceAcidShot.causeAcidicDamage(this, this);
					}
					else
					{
						var25 = DamageSourceAcidShot.causeAcidicDamage(this, this.shootingEntity);
					}

					if (this.isBurning() && !(movingobjectposition.entityHit instanceof EntityEnderman))
					{
						movingobjectposition.entityHit.setFire(5);
					}

					if (movingobjectposition.entityHit.attackEntityFrom(var25, (float) var23))
					{
						if (movingobjectposition.entityHit instanceof EntityLivingBase)
						{
							EntityLivingBase entitylivingbase = (EntityLivingBase) movingobjectposition.entityHit;

							if (!this.worldObj.isRemote)
							{
								entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);
							}

							if (this.knockbackStrength > 0)
							{
								var21 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

								if (var21 > 0.0F)
								{
									movingobjectposition.entityHit.addVelocity(this.motionX * (double) this.knockbackStrength * 0.6000000238418579D / (double) var21, 0.1D, this.motionZ * (double) this.knockbackStrength * 0.6000000238418579D / (double) var21);
								}
							}

							if (this.shootingEntity != null)
							{
								// EnchantmentThorns.func_92096_a(this.shootingEntity,
								// entitylivingbase,
								// this.rand);
							}

							if (this.shootingEntity != null && movingobjectposition.entityHit != this.shootingEntity && movingobjectposition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
							{
								// ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacketToPlayer(new
								// Packet70GameEvent(6,
								// 0));
							}
						}

						if (!(movingobjectposition.entityHit instanceof EntityEnderman))
						{
							this.setDead();
						}
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
					this.motionX = (double) ((float) (movingobjectposition.hitVec.xCoord - this.posX));
					this.motionY = (double) ((float) (movingobjectposition.hitVec.yCoord - this.posY));
					this.motionZ = (double) ((float) (movingobjectposition.hitVec.zCoord - this.posZ));
					var22 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					this.posX -= this.motionX / (double) var22 * 0.05000000074505806D;
					this.posY -= this.motionY / (double) var22 * 0.05000000074505806D;
					this.posZ -= this.motionZ / (double) var22 * 0.05000000074505806D;
					this.inGround = true;
					this.AcidShake = 7;
					this.setIsCritical(false);

					if (this.inTile != null)
					{
						inTile.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
					}
				}
			}

			if (this.getIsCritical())
			{
				for (l = 0; l < 4; ++l)
				{
					this.worldObj.spawnParticle("crit", this.posX + this.motionX * (double) l / 4.0D, this.posY + this.motionY * (double) l / 4.0D, this.posZ + this.motionZ * (double) l / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
				}
			}

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			var22 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

			for (this.rotationPitch = (float) (Math.atan2(this.motionY, (double) var22) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
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
			float var24 = 0.99F;
			f1 = 0.05F;

			if (this.isInWater())
			{
				for (int var26 = 0; var26 < 4; ++var26)
				{
					var21 = 0.25F;
					this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double) var21, this.posY - this.motionY * (double) var21, this.posZ - this.motionZ * (double) var21, this.motionX, this.motionY, this.motionZ);
				}

				var24 = 0.8F;
			}

			this.motionX *= (double) var24;
			this.motionY *= (double) var24;
			this.motionZ *= (double) var24;
			this.motionY -= (double) f1;
			this.setPosition(this.posX, this.posY, this.posZ);
			// this.doBlockCollisions();
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setShort("xTile", (short) this.xTile);
		par1NBTTagCompound.setShort("yTile", (short) this.yTile);
		par1NBTTagCompound.setShort("zTile", (short) this.zTile);
		// par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
		par1NBTTagCompound.setByte("inData", (byte) this.inData);
		par1NBTTagCompound.setByte("shake", (byte) this.AcidShake);
		par1NBTTagCompound.setByte("inGround", (byte) (this.inGround ? 1 : 0));
		par1NBTTagCompound.setByte("pickup", (byte) this.canBePickedUp);
		par1NBTTagCompound.setDouble("damage", this.damage);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		this.xTile = par1NBTTagCompound.getShort("xTile");
		this.yTile = par1NBTTagCompound.getShort("yTile");
		this.zTile = par1NBTTagCompound.getShort("zTile");
		// this.inTile = par1NBTTagCompound.getByte("inTile") & 255;
		this.inData = par1NBTTagCompound.getByte("inData") & 255;
		this.AcidShake = par1NBTTagCompound.getByte("shake") & 255;
		this.inGround = par1NBTTagCompound.getByte("inGround") == 1;

		if (par1NBTTagCompound.hasKey("damage"))
		{
			this.damage = par1NBTTagCompound.getDouble("damage");
		}

		if (par1NBTTagCompound.hasKey("pickup"))
		{
			this.canBePickedUp = par1NBTTagCompound.getByte("pickup");
		}
		else if (par1NBTTagCompound.hasKey("player"))
		{
			this.canBePickedUp = par1NBTTagCompound.getBoolean("player") ? 1 : 0;
		}
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}

	public void setDamage(double par1)
	{
		this.damage = par1;
	}

	public double getDamage()
	{
		return this.damage;
	}

	public void setKnockbackStrength(int par1)
	{
		this.knockbackStrength = par1;
	}

	/**
	 * If returns false, the item will not inflict any damage against entities.
	 */
	public boolean canAttackWithItem()
	{
		return false;
	}

	public void setIsCritical(boolean par1)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (par1)
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 | 1)));
		}
		else
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & -2)));
		}
	}

	public boolean getIsCritical()
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
		return (b0 & 1) != 0;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer var1)
	{
		if (!this.worldObj.isRemote)
		{
			byte b0 = 14;
			var1.addPotionEffect(new PotionEffect(Potion.poison.id, b0 * 20, 0));
		}
	}

	protected void onImpact(MovingObjectPosition var1)
	{
		if (!this.worldObj.isRemote)
		{
			if (var1.entityHit != null)
			{
				byte b0 = 7;
				((EntityLiving) var1.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, b0 * 20, 0));
			}

			this.setDead();
		}
	}
}
