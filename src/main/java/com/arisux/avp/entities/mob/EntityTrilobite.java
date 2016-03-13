package com.arisux.avp.entities.mob;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.ai.alien.EntitySelectorXenomorph;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityTrilobite extends EntitySpeciesAlien implements IMob
{
	public EntityTrilobite(World world)
	{
		super(world);

		this.setSize(1.5F, 1.5F);
		this.experienceValue = 32;
		this.getNavigator().setCanSwim(true);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.800000011920929D, true));
		this.tasks.addTask(1, new EntityAIWander(this, 0.800000011920929D));
		this.targetTasks.addTask(1, new EntityAILeapAtTarget(this, 1.0F));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, Entity.class, /** targetChance **/0, /** shouldCheckSight **/false, /** nearbyOnly **/false, EntitySelectorXenomorph.instance));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(44.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5499999761581421D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		this.fallDistance = 0F;
	}

	// temporary sound override until ender23 has new sounds.  the ticking is annoying
	@Override
	protected String getHurtSound()
	{
		return AliensVsPredator.properties().SOUND_FACEHUGGER_HURT;
	}	
	
	// temporary sound override until ender23 has new sounds.  the ticking is annoying
	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_CHESTBURSTER_BURST;
	}
	
	@Override
	protected String getLivingSound()
	{
		return AliensVsPredator.properties().SOUND_FACEHUGGER_LIVING;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return true;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public boolean isOnLadder()
	{
		return this.isCollidedHorizontally;
	}

	public boolean isClimbing()
	{
		return this.isOnLadder() && this.motionY > 1.0099999997764826D;
	}

	@Override
	public boolean isPotionApplicable(PotionEffect potionEffect)
	{
		return potionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(potionEffect);
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
