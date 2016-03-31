package com.arisux.avp.entities.mob;

import com.arisux.airi.lib.AccessWrapper;
import com.arisux.avp.entities.pathfinding.PathNavigateSwimmer;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDeaconShark extends EntitySpeciesAlien
{
	private EntityAIWander wander;
	private EntityAIMoveTowardsRestriction moveTowardsRestriction;

	public EntityDeaconShark(World worldIn)
	{
		super(worldIn);
		this.experienceValue = 10;
		this.setSize(2F, 1F);
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 0.8D, true));
		this.tasks.addTask(5, moveTowardsRestriction = new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, this.wander = new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityDeaconShark.class, 12.0F, 0.01F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.moveTowardsRestriction.setMutexBits(3);
		this.wander.setMutexBits(3);
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, Entity.class, 10, true, false, new IEntitySelector()
		{
			@Override
			public boolean isEntityApplicable(Entity target)
			{
				return !(target instanceof EntityDeaconShark);
			}
		}));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		AccessWrapper.setMoveHelper(this, new EntityDeaconShark.DeaconSharkMoveHelper());
		AccessWrapper.setNavigator(this, new PathNavigateSwimmer(this, this.worldObj));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
	}

	@Override
	public boolean attackEntityAsMob(Entity target)
	{
		if (this.getDistanceToEntity(target) < 1.5F)
		{
			return super.attackEntityAsMob(target);
		}
		
		return false;
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public int getTalkInterval()
	{
		return 160;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public float getEyeHeight()
	{
		return this.height * 0.5F;
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.getAttackTarget() != null)
		{
			this.getMoveHelper().setMoveTo(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ, 1F);
		}

		if (this.worldObj.isRemote)
		{
			if (this.getAttackTarget() != null)
			{
				if (this.getAttackTarget() != null)
				{
					this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 90.0F, 90.0F);
					this.getLookHelper().onUpdateLook();
				}
			}
		}

		if (this.inWater)
		{
			this.setAir(300);
		}
		else if (this.onGround)
		{
			this.motionY += 0.5D;
			this.motionX += (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
			this.motionZ += (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
			this.rotationYaw = this.rand.nextFloat() * 360.0F;
			this.onGround = false;
			this.isAirBorne = true;
		}

		if (this.getAttackTarget() != null)
		{
			this.rotationYaw = this.rotationYawHead;
		}

		super.onLivingUpdate();
	}

	@Override
	protected void collideWithNearbyEntities()
	{
		super.collideWithNearbyEntities();
	}

	@Override
	protected boolean isValidLightLevel()
	{
		return true;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return (!this.worldObj.canBlockSeeTheSky((int) this.posX, (int) this.posY, (int) this.posZ)) && this.rand.nextInt(11111) == 0;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	protected void attackEntity(Entity entity, float damage)
	{
		super.attackEntity(entity, damage);
	}

	@Override
	protected void collideWithEntity(Entity entity)
	{
		if (entity instanceof IMob && this.getRNG().nextInt(20) == 0 && !(entity instanceof EntityDeaconShark))
		{
			this.setAttackTarget((EntityLivingBase) entity);
			this.setRevengeTarget((EntityLivingBase) entity);
		}

		super.collideWithEntity(entity);
	}

	@Override
	public int getVerticalFaceSpeed()
	{
		return 180;
	}

	@Override
	public void moveEntityWithHeading(float strafe, float forward)
	{
		if (!this.worldObj.isRemote)
		{
			if (this.isInWater())
			{
				this.moveFlying(strafe, forward, 0.1F);
				this.moveEntity(this.motionX, this.motionY, this.motionZ);
				this.motionX *= 0.8999999761581421D;
				this.motionY *= 0.8999999761581421D;
				this.motionZ *= 0.8999999761581421D;

				if (this.getAttackTarget() == null)
				{
					this.motionY -= 0.005D;
				}
			}
			else
			{
				super.moveEntityWithHeading(strafe, forward);
			}
		}
		else
		{
			super.moveEntityWithHeading(strafe, forward);
		}
	}

	class DeaconSharkMoveHelper extends EntityMoveHelper
	{
		private EntityDeaconShark shark = EntityDeaconShark.this;

		public DeaconSharkMoveHelper()
		{
			super(EntityDeaconShark.this);
		}

		private float limitAngle(float angle1, float angle2, float angle3)
		{
			float wrappedAngle = MathHelper.wrapAngleTo180_float(angle2 - angle1);

			if (wrappedAngle > angle3)
			{
				wrappedAngle = angle3;
			}

			if (wrappedAngle < -angle3)
			{
				wrappedAngle = -angle3;
			}

			return angle1 + wrappedAngle;
		}

		@Override
		public void onUpdateMoveHelper()
		{
			if (this.isUpdating())
			{
				double posX = AccessWrapper.getMoveHelperPosX(this) - this.shark.posX;
				double posY = AccessWrapper.getMoveHelperPosY(this) - this.shark.posY;
				double posZ = AccessWrapper.getMoveHelperPosZ(this) - this.shark.posZ;
				double velocity = posX * posX + posY * posY + posZ * posZ;
				velocity = (double) MathHelper.sqrt_double(velocity);
				posY /= velocity;
				this.shark.rotationYaw = this.limitAngle(this.shark.rotationYaw, (float) (Math.atan2(posZ, posX) * 180.0D / Math.PI) - 90.0F, 5.0F);
				this.shark.renderYawOffset = this.shark.rotationYaw;
				float speed = (float) (AccessWrapper.getMoveHelperSpeed(this) * this.shark.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
				this.shark.setAIMoveSpeed(this.shark.getAIMoveSpeed() + (speed - this.shark.getAIMoveSpeed()) * 0.125F);
				double waveX = Math.sin((double) (this.shark.ticksExisted + this.shark.getEntityId()) * 0.5D) * 0.05D;
				double waveY = Math.cos((double) (this.shark.rotationYaw * (float) Math.PI / 180.0F));
				double waveZ = Math.sin((double) (this.shark.rotationYaw * (float) Math.PI / 180.0F));
				this.shark.motionX += waveX * waveY;
				this.shark.motionZ += waveX * waveZ;
				waveX = Math.sin((double) (this.shark.ticksExisted + this.shark.getEntityId()) * 0.75D) * 0.05D;
				this.shark.motionY += waveX * (waveZ + waveY) * 0.25D;
				this.shark.motionY += (double) this.shark.getAIMoveSpeed() * posY * 0.1D;
				double offsetX = this.shark.posX + posX / velocity * 2.0D;
				double offsetY = (double) this.shark.getEyeHeight() + this.shark.posY + posY / velocity * 1.0D;
				double offsetZ = this.shark.posZ + posZ / velocity * 2.0D;
				double lookX = AccessWrapper.getLookHelperPosX(this.shark.getLookHelper());
				double lookY = AccessWrapper.getLookHelperPosY(this.shark.getLookHelper());
				double lookZ = AccessWrapper.getLookHelperPosZ(this.shark.getLookHelper());

				if (!AccessWrapper.getLookHelperIsLooking(this.shark.getLookHelper()))
				{
					lookX = offsetX;
					lookY = offsetY;
					lookZ = offsetZ;
				}

				this.shark.getLookHelper().setLookPosition(lookX + (offsetX - lookX) * 0.125D, lookY + (offsetY - lookY) * 0.125D, lookZ + (offsetZ - lookZ) * 0.125D, 10.0F, 40.0F);
			}
			else
			{
				this.shark.setAIMoveSpeed(0.0F);
			}
		}
	}
}
