package com.arisux.avp.entities.mob;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;

public class EntityYautja extends EntityMob implements IMob
{
	public EntityYautja(World par1World)
	{
		super(par1World);
		this.experienceValue = 250;
		this.setSize(1.0F, 2.5F);
		this.getNavigator().setCanSwim(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 0.800000011920929D, true));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityFacehugger.class, 0.800000011920929D, true));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityMarine.class, 0.800000011920929D, true));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityQueen.class, 0.800000011920929D, true));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityChestburster.class, 0.800000011920929D, true));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityDrone.class, 0.800000011920929D, true));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityWarrior.class, 0.800000011920929D, true));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntitySpitter.class, 0.800000011920929D, true));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPraetorian.class, 0.800000011920929D, true));
		this.tasks.addTask(3, new EntityAIWander(this, 0.800000011920929D));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(8, new EntityAIPanic(this, 0.800000011920929D));
		this.targetTasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityOvamorph.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityFacehugger.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityDrone.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityWarrior.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntitySpitter.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityQueen.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityCrusher.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPraetorian.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityMarine.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityChestburster.class, 0, true));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5199999761581421D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(5.0D);
	}

	/**
	 * Basic mob attack. Default to touch of death in EntityCreature.
	 * Overridden by each mob to define their attack.
	 */
	@Override
	protected void attackEntity(Entity entity, float f)
	{
		if (this.attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
		{
			this.attackTime = 20;
			this.attackEntityAsMob(entity);
		}

		float f1 = this.getBrightness(1.0F);

		if (f1 > 0.5F && this.rand.nextInt(100) == 0)
		{
			this.entityToAttack = null;
		} else
		{
			if (f > 2.0F && f < 6.0F && this.rand.nextInt(10) == 0)
			{
				if (this.onGround)
				{
					double d = entity.posX - this.posX;
					double d1 = entity.posZ - this.posZ;
					float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
					this.motionX = d / f2 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
					this.motionZ = d1 / f2 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
					this.motionY = 0.4000000059604645D;
				}
			} else
			{
				super.attackEntity(entity, f);
			}
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		int var2 = 5;

		if (this.isPotionActive(Potion.damageBoost))
		{
			var2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
		}

		if (this.isPotionActive(Potion.weakness))
		{
			var2 -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
		}

		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	/**
	 * Returns the current armor value as determined by a call to
	 * InventoryPlayer.getTotalArmorValue
	 */
	@Override
	public int getTotalArmorValue()
	{
		return 7;
	}

	@Override
	protected void collideWithEntity(Entity par1Entity)
	{
		if (par1Entity instanceof IMob && this.getRNG().nextInt(20) == 0 && !(par1Entity instanceof EntityYautja))
		{
			this.setAttackTarget((EntityLivingBase) par1Entity);
			this.setRevengeTarget((EntityLivingBase) par1Entity);
		}

		super.collideWithEntity(par1Entity);
	}

	/**
	 * Checks if this entity is inside water (if inWater field is true as a
	 * result of handleWaterMovement() returning true)
	 */
	@Override
	public boolean isInWater()
	{
		return this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.900000023841858D, 0.0D), Material.water, this);
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound()
	{
		return AliensVsPredator.properties().SOUND_YAUTJA_LIVING;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound()
	{
		return AliensVsPredator.properties().SOUND_YAUTJA_HURT;
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_YAUTJA_DEATH;
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this
	 * entity has recently been hit by a player. @param par2 - Level of
	 * Looting used to kill this mob.
	 */
	@Override
	protected void dropFewItems(boolean flag, int i)
	{
		if ((new Random()).nextInt(6) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.itemEnergy), 1);

		if ((new Random()).nextInt(6) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.itemSpear), 1);
	}

	@Override
	protected void dropRareDrop(int par1)
	{
		this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.helmTitanium), 1);
		this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.plateTitanium), 1);
		this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.legsTitanium), 1);
		this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.bootsTitanium), 1);
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away
	 * entities
	 */
	@Override
	public boolean canDespawn()
	{
		return false;
	}
}
