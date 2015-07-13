package com.arisux.avp.entities;

import java.util.List;

import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAPC extends Entity
{
	private boolean isVehicleEmpty;
	private double speedMultiplier;
	private int rotationIncrements;
	private double boatX;
	private double boatY;
	private double boatZ;
	private double boatYaw;
	private double boatPitch;
	@SideOnly(Side.CLIENT)
	private double velocityX;
	@SideOnly(Side.CLIENT)
	private double velocityY;
	@SideOnly(Side.CLIENT)
	private double velocityZ;

	public EntityAPC(World worldIn)
	{
		super(worldIn);
		this.isVehicleEmpty = true;
		this.speedMultiplier = 1.37D;
		this.preventEntitySpawning = true;
		this.setSize(3.75F, 2F);
		this.yOffset = this.height;
		this.ignoreFrustumCheck = true;
	}

	protected boolean canTriggerWalking()
	{
		return false;
	}

	protected void entityInit()
	{
		this.dataWatcher.addObject(17, new Integer(0));
		this.dataWatcher.addObject(18, new Integer(1));
		this.dataWatcher.addObject(19, new Float(0.0F));
	}

	public AxisAlignedBB getCollisionBox(Entity entityIn)
	{
		return entityIn.boundingBox;
	}

	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}

	public boolean canBePushed()
	{
		return false;
	}

	public EntityAPC(World worldIn, double x, double y, double z)
	{
		this(worldIn);
		this.setPosition(x, y + (double)this.yOffset, z);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	public double getMountedYOffset()
	{
		return (double)this.height * 0.0D + 0.25;
	}

	public boolean attackEntityFrom(DamageSource dmgSource, float f)
	{
		if (this.isEntityInvulnerable())
		{
			return false;
		}
		else if (!this.worldObj.isRemote && !this.isDead)
		{
			this.setForwardDirection(-this.getForwardDirection());
			this.setTimeSinceHit(10);
			this.setDamageTaken(this.getDamageTaken() + f * 10.0F);
			this.setBeenAttacked();
			boolean flag = dmgSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)dmgSource.getEntity()).capabilities.isCreativeMode;

			if (flag || this.getDamageTaken() > 200.0F)
			{
				if (this.riddenByEntity != null)
				{
					this.riddenByEntity.mountEntity(this);
				}

				if (!flag)
				{
					this.func_145778_a(AliensVsPredator.items().itemAPC, 1, 0.0F);
				}

				this.setDead();
			}

			return true;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
	 */
	@SideOnly(Side.CLIENT)
	public void performHurtAnimation()
	{
		this.setForwardDirection(-this.getForwardDirection());
		this.setTimeSinceHit(10);
		this.setDamageTaken(this.getDamageTaken() * 11.0F);
	}

	/**
	 * Returns true if other Entities should be prevented from moving through this Entity.
	 */
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	/**
	 * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
	 * posY, posZ, yaw, pitch
	 */
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double posX, double posY, double posZ, float yaw, float pitch, int p_70056_9_)
	{
		if (this.isVehicleEmpty)
		{
			this.rotationIncrements = p_70056_9_ + 5;
		}
		else
		{
			double d3 = posX - this.posX;
			double d4 = posY - this.posY;
			double d5 = posZ - this.posZ;
			double d6 = d3 * d3 + d4 * d4 + d5 * d5;

			if (d6 <= 1.0D)
			{
				return;
			}

			this.rotationIncrements = 3;
		}

		this.boatX = posX;
		this.boatY = posY;
		this.boatZ = posZ;
		this.boatYaw = (double)yaw;
		this.boatPitch = (double)pitch;
		this.motionX = this.velocityX;
		this.motionY = this.velocityY;
		this.motionZ = this.velocityZ;
	}

	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public void setVelocity(double x, double y, double z)
	{
		this.velocityX = this.motionX = x;
		this.velocityY = this.motionY = y;
		this.velocityZ = this.motionZ = z;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		this.speedMultiplier = 1.95D;
		this.fallDistance = 0;
		
		if (this.getTimeSinceHit() > 0)
		{
			this.setTimeSinceHit(this.getTimeSinceHit() - 1);
		}

		if (this.getDamageTaken() > 0.0F)
		{
			this.setDamageTaken(this.getDamageTaken() - 1.0F);
		}

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		int blockZ;
		double mass = -0.1D;
		double curVelocity = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
		double rotX;
		double rotY;
		double rotZ;
		double vehicleYaw; 

		if (curVelocity > 0.26249999999999996D)
		{
			rotX = Math.cos((double)this.rotationYaw * Math.PI / 180.0D);
			rotY = Math.sin((double)this.rotationYaw * Math.PI / 180.0D);
		}
		
		if (this.worldObj.isRemote && this.isVehicleEmpty)
		{
			if (this.rotationIncrements > 0)
			{
				rotX = this.posX + (this.boatX - this.posX) / (double)this.rotationIncrements;
				rotY = this.posY + (this.boatY - this.posY) / (double)this.rotationIncrements;
				rotZ = this.posZ + (this.boatZ - this.posZ) / (double)this.rotationIncrements;
				vehicleYaw = MathHelper.wrapAngleTo180_double(this.boatYaw - (double)this.rotationYaw);
				this.rotationYaw = (float)((double)this.rotationYaw + vehicleYaw / (double)this.rotationIncrements);
				this.rotationPitch = (float)((double)this.rotationPitch + (this.boatPitch - (double)this.rotationPitch) / (double)this.rotationIncrements);
				--this.rotationIncrements;
				this.setPosition(rotX, rotY, rotZ);
				this.setRotation(this.rotationYaw, this.rotationPitch);
			}
			else
			{
				rotX = this.posX + this.motionX;
				rotY = this.posY + this.motionY;
				rotZ = this.posZ + this.motionZ;
				this.setPosition(rotX, rotY, rotZ);

				this.motionX *= 0.9900000095367432D;
				this.motionY *= 0.949999988079071D;
				this.motionZ *= 0.9900000095367432D;
			}
		}
		else
		{
			if (mass < 1.0D)
			{
				rotX = mass * 2.0D - 1.0D;
				this.motionY += 0.03999999910593033D * rotX;
			}
			else
			{
				if (this.motionY < 0.0D)
				{
					this.motionY /= 2.0D;
				}

				this.motionY += 0.007000000216066837D;
			}

			if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase)
			{
				EntityLivingBase entitylivingbase = (EntityLivingBase)this.riddenByEntity;
				float f = this.riddenByEntity.rotationYaw + -entitylivingbase.moveStrafing * 90.0F;
				this.motionX += -Math.sin((double)(f * (float)Math.PI / 180.0F)) * this.speedMultiplier * (double)entitylivingbase.moveForward * 0.05000000074505806D;
				this.motionZ += Math.cos((double)(f * (float)Math.PI / 180.0F)) * this.speedMultiplier * (double)entitylivingbase.moveForward * 0.05000000074505806D;
			}

			rotX = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

			if (rotX > 0.35D)
			{
				rotY = 0.35D / rotX;
				this.motionX *= rotY;
				this.motionZ *= rotY;
				rotX = 0.35D;
			}

			if (rotX > curVelocity && this.speedMultiplier < 0.35D)
			{
				this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D;

				if (this.speedMultiplier > 0.35D)
				{
//					this.speedMultiplier = 0.35D;
				}
			}
			else
			{
				this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D;

				if (this.speedMultiplier < 0.07D)
				{
					this.speedMultiplier = 0.07D;
				}
			}

			for (int checkDistance = 0; checkDistance < 4; ++checkDistance)
			{
				int blockX = MathHelper.floor_double(this.posX + ((double)(checkDistance % 2) - 0.5D) * 0.8D);
				blockZ = MathHelper.floor_double(this.posZ + ((double)(checkDistance / 2) - 0.5D) * 0.8D);

				for (int checkHeight = 0; checkHeight < 2; ++checkHeight)
				{
					int blockY = MathHelper.floor_double(this.posY) + checkHeight;
					Block block = this.worldObj.getBlock(blockX, blockY, blockZ);

					if (block == Blocks.snow_layer)
					{
						this.worldObj.setBlockToAir(blockX, blockY, blockZ);
						this.isCollidedHorizontally = false;
					}
					else if (block == Blocks.waterlily)
					{
						this.worldObj.func_147480_a(blockX, blockY, blockZ, true);
						this.isCollidedHorizontally = false;
					}
				}
			}
			
			if(this.riddenByEntity == null)
			{
				this.motionX = 0;
				this.motionY = 0;
				this.motionZ = 0;
			}

			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.9900000095367432D;
			this.motionY *= 0.949999988079071D;
			this.motionZ *= 0.9900000095367432D;

			this.rotationPitch = 0.0F;
			rotY = (double)this.rotationYaw;
			rotZ = this.prevPosX - this.posX;
			vehicleYaw = this.prevPosZ - this.posZ;

			if (rotZ * rotZ + vehicleYaw * vehicleYaw > 0.001D)
			{
				rotY = (double)((float)(Math.atan2(vehicleYaw, rotZ) * 180.0D / Math.PI));
			}

			double d7 = MathHelper.wrapAngleTo180_double(rotY - (double)this.rotationYaw);

			if (d7 > 20.0D)
			{
				d7 = 20.0D;
			}

			if (d7 < -20.0D)
			{
				d7 = -20.0D;
			}

			this.rotationYaw = (float)((double)this.rotationYaw + d7);
			this.setRotation(this.rotationYaw, this.rotationPitch);

			if (!this.worldObj.isRemote)
			{
				List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));

				if (list != null && !list.isEmpty())
				{
					for (int k1 = 0; k1 < list.size(); ++k1)
					{
						Entity entity = (Entity)list.get(k1);

						if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityAPC)
						{
							entity.applyEntityCollision(this);
						}
					}
				}

				if (this.riddenByEntity != null && this.riddenByEntity.isDead)
				{
					this.riddenByEntity = null;
				}
			}
		}
	}

	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
		{
			double d0 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			double d1 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			this.riddenByEntity.setPosition(this.posX + d0 - 2.5F, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1 + 0.25F);
			if(this.riddenByEntity instanceof EntityPlayer)
			{
				if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
				{
					Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
				}
			}
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {}

	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}

	/**
	 * First layer of player interaction
	 */
	public boolean interactFirst(EntityPlayer playerIn)
	{
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != playerIn)
		{
			return true;
		}
		else
		{
			if (!this.worldObj.isRemote)
			{
				playerIn.mountEntity(this);
				Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
			}

			return true;
		}
	}

	/**
	 * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
	 * and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround
	 */
	protected void updateFallState(double distanceFallenThisTick, boolean onGround)
	{
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posY);
		int k = MathHelper.floor_double(this.posZ);

		if (onGround)
		{
			if (this.fallDistance > 3.0F)
			{
				this.fall(this.fallDistance);

				if (!this.worldObj.isRemote && !this.isDead)
				{
					this.setDead();
					int l;
				}

				this.fallDistance = 0.0F;
			}
		}
		else if (this.worldObj.getBlock(i, j - 1, k).getMaterial() != Material.water && distanceFallenThisTick < 0.0D)
		{
			this.fallDistance = (float)((double)this.fallDistance - distanceFallenThisTick);
		}
	}

	/**
	 * Sets the damage taken from the last hit.
	 */
	public void setDamageTaken(float damage)
	{
		this.dataWatcher.updateObject(19, Float.valueOf(damage));
	}

	/**
	 * Gets the damage taken from the last hit.
	 */
	public float getDamageTaken()
	{
		return this.dataWatcher.getWatchableObjectFloat(19);
	}

	/**
	 * Sets the time to count down from since the last time entity was hit.
	 */
	public void setTimeSinceHit(int time)
	{
		this.dataWatcher.updateObject(17, Integer.valueOf(time));
	}

	/**
	 * Gets the time since the last hit.
	 */
	public int getTimeSinceHit()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	/**
	 * Sets the forward direction of the entity.
	 */
	public void setForwardDirection(int direction)
	{
		this.dataWatcher.updateObject(18, Integer.valueOf(direction));
	}

	/**
	 * Gets the forward direction of the entity.
	 */
	public int getForwardDirection()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	/**
	 * true if no player in boat
	 */
	@SideOnly(Side.CLIENT)
	public void setIsBoatEmpty(boolean isEmpty)
	{
		this.isVehicleEmpty = isEmpty;
	}
}