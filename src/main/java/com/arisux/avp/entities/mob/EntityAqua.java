package com.arisux.avp.entities.mob;

import com.arisux.avp.AliensVsPredator;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.world.World;

public class EntityAqua extends EntityXenomorph
{
	public EntityAqua(World world)
	{
		super(world);
		this.jumpMovementFactor = 0.2F;
		this.experienceValue = 100;
		this.setSize(1F, 3F);
		this.getNavigator().setCanSwim(true);
		this.getNavigator().setAvoidsWater(false);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.800000011920929D, true));
		this.tasks.addTask(1, new EntityAIWander(this, 0.800000011920929D));
		this.targetTasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5500000238418579D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected String getHurtSound()
	{
		return AliensVsPredator.properties().SOUND_ALIEN_HURT;
	}

	@Override
	protected String getLivingSound()
	{
		return AliensVsPredator.properties().SOUND_ALIEN_LIVING;
	}
	
	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_ALIEN_DEATH;
	}
}
