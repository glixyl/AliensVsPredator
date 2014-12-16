package com.arisux.avp.entities;

import java.util.ArrayList;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.arisux.avp.damagesource.DamageSourceAcid;
import com.arisux.avp.entities.ai.EntityAIMeltBlock;
import com.arisux.avp.entities.mob.EntitySpeciesAlien;

public class EntityAcidPool extends EntityCreature implements IMob
{
	private int lifetime = 600;
	private ArrayList<Class<? extends Entity>> targetList = new ArrayList<Class<? extends Entity>>();
	private ArrayList<Class<? extends Entity>> safeList = new ArrayList<Class<? extends Entity>>();
	
	public EntityAcidPool(World world)
	{
		super(world);
		this.isImmuneToFire = false;
		this.ignoreFrustumCheck = true;
		this.setSize(0.08F, 0.08F);
		this.tasks.addTask(0, new EntityAIMeltBlock(this, -1));
		this.applyTargets();
	}
	
	private void applyTargets()
	{
		safeList.add(EntitySpeciesAlien.class);
		safeList.add(EntityAcidPool.class);
	}

	public boolean isAcceptableTarget(Entity entity)
	{
		for (Class<? extends Entity> entityClass : safeList)
		{
			if (entityClass.isInstance(entity))
			{
				return false;
			}
		}

		for (Class<? extends Entity> entityClass : targetList)
		{
			if (entityClass.isInstance(entity))
			{
				return true;
			}
		}

		return true;
	}


	@Override
	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0D);
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return false;
	}

	@Override
	public boolean canBePushed()
	{
		return false;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public boolean isInRangeToRenderDist(double var1)
	{
		return true;
	}
	
	public float getAcidIntensity()
	{
		return 1F - (1F / this.getLifetime() / (1F / this.ticksExisted));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (!this.worldObj.isRemote)
		{
			double range = 1.2;
			EntityLivingBase target = (EntityLivingBase) (this.worldObj.findNearestEntityWithinAABB(EntityLivingBase.class, this.boundingBox.expand(range, 0.1D, range), this));

			if (target != null && isAcceptableTarget(target))
			{
				this.setAttackTarget(target);
				target.attackEntityFrom(DamageSourceAcid.causeAcidicDamage(this, target), 4F);
			}
		}

		if (worldObj.isRemote && worldObj.getWorldTime() % 4 <= 0)
		{
			this.worldObj.spawnParticle("smoke", this.posX + this.rand.nextDouble(), this.posY + this.rand.nextDouble(), this.posZ + this.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
		}

		if (!this.worldObj.isRemote)
		{
			if (this.ticksExisted > this.lifetime)
			{
				 this.setDead();
			}
		}
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer var1)
	{
		if (!this.worldObj.isRemote)
		{
			byte b0 = 14;
			var1.addPotionEffect(new PotionEffect(Potion.poison.id, b0 * 20, 0));
		}
	}

	@Override
	protected void attackEntity(Entity entity, float f)
	{
		super.attackEntity(entity, f);
	}
	
	public int getLifetime()
	{
		return lifetime;
	}
}
