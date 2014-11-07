package com.arisux.avp.entities.mob;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;

public class EntityChestburster extends EntitySpeciesAlien implements IMob
{
	protected Minecraft mc;
	public EntityChestburster(World par1World)
	{
		super(par1World);

		this.setSize(1.0F, 0.2F);
		this.experienceValue = 16;
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.5699999928474426D, false));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityMarine.class, 0.5699999928474426D, true));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityAnimal.class, 0.5699999928474426D, true));
		this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.5699999928474426D));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.targetTasks.addTask(6, new EntityAIHurtByTarget(this, false));
		this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
		this.targetTasks.addTask(8, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, true));
		this.targetTasks.addTask(10, new EntityAINearestAttackableTarget(this, EntityMarine.class, 0, true));
		this.targetTasks.addTask(11, new EntityAINearestAttackableTarget(this, EntityYautja.class, 0, true));
		this.tasks.addTask(12, new EntityAIWander(this, 0.4000000059604645D));
		this.tasks.addTask(13, new EntityAIAttackOnCollide(this, EntityYautja.class, 0.5699999928474426D, true));
		this.tasks.addTask(14, new EntityAISwimming(this));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(14.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6499999761581421D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.5D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte) 0));
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (((getAge() / 100) / 20) / 24 > 1)
		{
			if (!this.worldObj.isRemote)
			{
				EntityDrone entityxeno = new EntityDrone(this.worldObj);
				double d = this.posX;
				double d1 = this.posY;
				double d2 = this.posZ;
				entityxeno.setLocationAndAngles(d, d1, d2, 0.0F, 0.0F);
				this.worldObj.spawnEntityInWorld(entityxeno);

				for (int i = 0; i < 8; ++i)
				{
					this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
				}

				this.setDead();
			}
		}
	}

	protected Entity findPlayerToAttack(EntityPlayer entityplayer)
	{
		float f = this.getBrightness(1.0F);

		if (f < 0.5F)
		{
			double d = 40.0D;
			return this.worldObj.getClosestVulnerablePlayerToEntity(this, d);
		} else
		{
			return null;
		}
	}

	protected String getDeathSound()
	{
		return AliensVsPredator.INSTANCE.properties.SOUND_CHESTBURSTER_DEATH;
	}

	protected boolean canTriggerWalking()
	{
		return false;
	}

	protected boolean canDespawn()
	{
		return false;
	}

	public boolean isOnLadder()
	{
		return this.isCollidedHorizontally;
	}

	public boolean isClimbing()
	{
		return this.isOnLadder() && this.motionY > 1.0099999997764826D;
	}

	protected void attackEntity(Entity entity, float f)
	{
		if (f > 2.0F && f < 6.0F && this.rand.nextInt(50) == 0)
		{
			if (this.onGround)
			{
				double var4 = entity.posX - this.posX;
				double var6 = entity.posZ - this.posZ;
				float var8 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
				this.motionX = var4 / (double) var8 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
				this.motionZ = var6 / (double) var8 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
				this.motionY = 0.4000000059604645D;
			}
		} else
		{
			super.attackEntity(entity, f);
		}
	}

	public boolean isPotionApplicable(PotionEffect par1PotionEffect)
	{
		return par1PotionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(par1PotionEffect);
	}
}