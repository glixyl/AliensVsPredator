package com.arisux.avp.entities.mob;

import com.arisux.avp.AliensVsPredator;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityProtomorph extends EntityXenomorph
{
	public EntityProtomorph(World world)
	{
		super(world);

		this.jumpMovementFactor = 0.02F;
		this.experienceValue = 100;
		this.setSize(0.8F, 1.8F);
		this.canClimb = false;
		this.isDependant = false;
		this.getNavigator().setCanSwim(true);
		this.getNavigator().setAvoidsWater(true);
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4700000238418579D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.75D);
	}

	@Override
	protected void dropRareDrop(int par1)
	{
		super.dropRareDrop(par1);
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
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_ALIEN_DEATH;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
	}
}
