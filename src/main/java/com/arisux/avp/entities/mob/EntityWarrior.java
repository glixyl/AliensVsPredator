package com.arisux.avp.entities.mob;

import com.arisux.avp.AliensVsPredator;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.world.World;

public class EntityWarrior extends EntityXenomorph implements IMob
{
	public EntityWarrior(World world)
	{
		super(world);
		this.experienceValue = 175;
		this.setSize(1.0F, 2.5F);
		this.setEvolveTo(EntityPraetorian.class, 32);
		this.getNavigator().setCanSwim(true);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
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
		return AliensVsPredator.properties().SOUND_WARRIOR_HURT;
	}

	@Override
	protected String getLivingSound()
	{
		return AliensVsPredator.properties().SOUND_WARRIOR_LIVING;
	}

	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_WARRIOR_DEATH;
	}
}
