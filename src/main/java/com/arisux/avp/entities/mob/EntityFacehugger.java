package com.arisux.avp.entities.mob;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
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

public class EntityFacehugger extends EntitySpeciesAlien implements IMob
{
	public EntityFacehugger(World par1World)
	{
		super(par1World);
		this.setSize(0.9F, 0.9F);
		this.experienceValue = 10;
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.8999999761581421D, false));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityAnimal.class, 0.8999999761581421D, true));
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
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMarine.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityYautja.class, 0, true));
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
		double var1 = leapTarget.posX - leaper.posX;
		double var3 = leapTarget.posZ - leaper.posZ;
		float var5 = MathHelper.sqrt_double(var1 * var1 + var3 * var3);
		leaper.motionX += var1 / var5 * 0.5D * 0.800000011920929D + leaper.motionX * 0.20000000298023224D;
		leaper.motionZ += var3 / var5 * 0.5D * 0.800000011920929D + leaper.motionZ * 0.20000000298023224D;
		leaper.motionY = leapMotionY;
	}

	@Override
	public void collideWithEntity(Entity par1Entity)
	{
		if (!this.worldObj.isRemote && par1Entity.riddenByEntity == null)
		{
			if (par1Entity instanceof EntityPlayer)
			{
				if (!((EntityPlayer) par1Entity).capabilities.isCreativeMode)
				{
					this.mountEntity(par1Entity);
				}
			}

			if (par1Entity instanceof EntityMarine)
			{
				this.mountEntity(par1Entity);
			}
		}
	}

	@Override
	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);
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
	protected void attackEntity(Entity entity, float f)
	{
		if (f > 2.0F && f < 6.0F && this.rand.nextInt(50) == 0)
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
			super.attackEntity(entity, f);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean isPotionApplicable(PotionEffect par1PotionEffect)
	{
		return par1PotionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(par1PotionEffect);
	}

	@Override
	public void onKillEntity(EntityLivingBase par1EntityLivingBase)
	{
		super.onKillEntity(par1EntityLivingBase);

		if (!this.worldObj.isRemote)
		{
			EntityChestburster entity;

			if (par1EntityLivingBase instanceof EntityPlayer)
			{
				entity = new EntityChestburster(this.worldObj);
				entity.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
				this.worldObj.spawnEntityInWorld(entity);

			}
			else if (par1EntityLivingBase instanceof EntityYautja)
			{
				entity = new EntityChestburster(this.worldObj);
				entity.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
				this.worldObj.spawnEntityInWorld(entity);

			}
			else if (par1EntityLivingBase instanceof EntityMarine)
			{
				entity = new EntityChestburster(this.worldObj);
				entity.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
				this.worldObj.spawnEntityInWorld(entity);

			}

			this.setDead();
		}
	}
}
