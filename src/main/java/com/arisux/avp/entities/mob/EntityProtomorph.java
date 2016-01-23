package com.arisux.avp.entities.mob;

import com.arisux.avp.AliensVsPredator;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityProtomorph extends EntityXenomorph
{
	public EntityProtomorph(World world)
	{
		super(world);

		this.jumpMovementFactor = 0.02F;
		this.experienceValue = 100;
		this.setSize(0.8F, 1.8F);
		this.getNavigator().setCanSwim(true);
		this.getNavigator().setAvoidsWater(true);
		this.canClimb = false;
		this.isDependant = false;
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
