package com.arisux.avp.entities.mob;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.ExtendedEntityPlayer;
import com.arisux.avp.util.HostParasiteTypes;

public class EntityFacehugger extends EntitySpeciesAlien implements IMob
{
	public EntityFacehugger(World world)
	{
		super(world);
		this.setSize(0.9F, 0.9F);
		this.experienceValue = 10;
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.8999999761581421D, false));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityAnimal.class, 0.8999999761581421D, true));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityCreeper.class, 0.8999999761581421D, true));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityMarine.class, 0.8999999761581421D, true));
		this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.8999999761581421D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(5, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(6, new EntityAIWander(this, 0.4000000059604645D));
		this.tasks.addTask(7, new EntityAIAttackOnCollide(this, EntityYautja.class, 0.8999999761581421D, true));
		this.tasks.addTask(8, new EntityAISwimming(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCreeper.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMarine.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityYautja.class, 0, true));
		this.addTargetType(EntityCreeper.class);
		this.addTargetType(EntityPlayer.class);
		this.addTargetType(EntityAnimal.class);
		this.addTargetType(EntityMarine.class);
		this.addTargetType(EntityYautja.class);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(14.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6999999761581421D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.50D);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer p_70100_1_)
	{
		super.onCollideWithPlayer(p_70100_1_);

		ExtendedEntityPlayer playerProperties = (ExtendedEntityPlayer) p_70100_1_.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);

		if (!playerProperties.isPlayerImpregnated() && !p_70100_1_.capabilities.isCreativeMode)
		{
			playerProperties.setPlayerImpregnated(true);
		}
	}

	@Override
	public boolean isOnLadder()
	{
		return this.motionY > 1.0099999997764826D;
	}

	public void jumpAtEntity(EntityLivingBase leaper, EntityLivingBase leapTarget, double leapMotionY)
	{
		double rX = leapTarget.posX - leaper.posX;
		double rZ = leapTarget.posZ - leaper.posZ;
		float sq = MathHelper.sqrt_double(rX * rX + rZ * rZ);
		leaper.motionX += rX / sq * 0.5D * 0.800000011920929D + leaper.motionX * 0.20000000298023224D;
		leaper.motionZ += rZ / sq * 0.5D * 0.800000011920929D + leaper.motionZ * 0.20000000298023224D;
		leaper.motionY = leapMotionY;
	}

	@Override
	public void collideWithEntity(Entity entity)
	{
		if (!this.worldObj.isRemote && entity.riddenByEntity == null)
		{
			if (entity instanceof EntityPlayer)
			{
				if (!((EntityPlayer) entity).capabilities.isCreativeMode)
				{
					this.mountEntity(entity);
				}
			}

			if (entity instanceof EntityMarine)
			{
				this.mountEntity(entity);
			}
		}
	}

	@Override
	public void onDeath(DamageSource damagesource)
	{
		super.onDeath(damagesource);
	}

	public float facehuggerScaleAmount()
	{
		return 0.8F;
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public double getYOffset()
	{
		if (this.ridingEntity instanceof EntityPlayer)
		{
			return -1.3D;
		}

		return 0.5D;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_FACEHUGGER_DEATH;
	}

	@Override
	protected boolean canDespawn()
	{
		return true;
	}

	@Override
	protected void attackEntity(Entity entity, float attackStrength)
	{
		if (attackStrength > 2.0F && attackStrength < 6.0F && this.rand.nextInt(50) == 0)
		{
			if (this.onGround)
			{
				this.jump();
				double var4 = entity.posX - this.posX;
				double var6 = entity.posZ - this.posZ;
				float var8 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
				this.motionX = var4 / var8 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
				this.motionZ = var6 / var8 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
				this.motionY = 0.4000000059604645D;
			}
		}
		else
		{
			super.attackEntity(entity, attackStrength);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean isPotionApplicable(PotionEffect effect)
	{
		return effect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(effect);
	}

	@Override
	public void onKillEntity(EntityLivingBase host)
	{
		super.onKillEntity(host);

		if (!this.worldObj.isRemote)
		{
			EntityChestburster chestburster = new EntityChestburster(this.worldObj);
			
			chestburster.setHostParasiteType(HostParasiteTypes.getTypeForHost(host.getClass()));
			chestburster.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
			this.worldObj.spawnEntityInWorld(chestburster);
			this.setDead();
		}
	}
}
