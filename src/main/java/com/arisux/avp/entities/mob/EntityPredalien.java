package com.arisux.avp.entities.mob;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAcidPool;

public class EntityPredalien extends EntityXenomorph implements IMob
{
	public EntityPredalien(World world)
	{
		super(world);
		this.jumpMovementFactor = 0.2F;
		this.experienceValue = 15;
		this.setSize(1.0F, 4.0F);
		this.ignoreFrustumCheck = true;
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setBreakDoors(true);
		this.tasks.addTask(0, new EntityAILeapAtTarget(this, 0.6F));
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityMarine.class, 1.0D, true));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityYautja.class, 1.0D, false));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityAnimal.class, 1.0D, false));
		this.tasks.addTask(4, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityYautja.class, 0, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityMarine.class, 0, false));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, false));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5500000238418579D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected String getHurtSound()
	{
		return AliensVsPredator.properties().SOUND_PRAETORIAN_HURT;
	}

	@Override
	protected String getLivingSound()
	{
		return AliensVsPredator.properties().SOUND_PRAETORIAN_LIVING;
	}

	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_PRAETORIAN_DEATH;
	}

	@Override
	protected void dropRareDrop(int par1)
	{
		;
	}

	@Override
	public void onDeath(DamageSource damageSource)
	{
		super.onDeath(damageSource);

		if (this.worldObj.isRemote)
		{
			EntityAcidPool entity = new EntityAcidPool(this.worldObj);
			entity.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
			this.worldObj.spawnEntityInWorld(entity);
		}
	}
}
