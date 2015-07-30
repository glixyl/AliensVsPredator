package com.arisux.avp.entities.mob;

import com.arisux.avp.entities.EntityAcidPool;
import com.arisux.avp.entities.ai.alien.EntityAIClimb;
import com.arisux.avp.entities.ai.alien.EntityAIQueenIdentificationTask;
import com.arisux.avp.entities.ai.alien.EntitySelectorXenomorph;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class EntityXenomorph extends EntitySpeciesAlien implements IMob
{
	public int targetQueenId;
	protected boolean canClimb;
	protected boolean isDependant;

	public EntityXenomorph(World world)
	{
		super(world);
		this.jumpMovementFactor = 0.06F;
		this.canClimb = true;
		this.isDependant = true;
		this.getNavigator().setCanSwim(true);
		this.tasks.addTask(0, new EntityAIQueenIdentificationTask(this));
		this.tasks.addTask(1, new EntityAIClimb(this, 0.03F));
		this.tasks.addTask(2, new EntityAIWander(this, 0.8D));
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, Entity.class, /** targetChance **/0, /** shouldCheckSight **/false, /** nearbyOnly **/false, EntitySelectorXenomorph.instance));
		this.targetTasks.addTask(2, new EntityAIAttackOnCollide(this, 0.8D, true));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1F);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
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

	public boolean canClimb()
	{
		return this.canClimb;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.fallDistance = 0F;
		
		
	}
	
	@Override
	protected void attackEntity(Entity entity, float damage)
	{
		super.attackEntity(entity, damage);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		return super.attackEntityAsMob(entity);
	}

	public void attackAI()
	{
		if (worldObj.getWorldInfo().getWorldTime() % 70 == 0)
		{
			double range = this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();
			Entity targetEntity = (this.worldObj.findNearestEntityWithinAABB(EntityLiving.class, this.boundingBox.expand(range * 10, 64.0D, range * 10), this));
			Entity targetPlayer = (this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand(range * 10, 64.0D, range * 10), this));
			if (targetPlayer != null && !((EntityPlayer) targetPlayer).capabilities.isCreativeMode)
			{
				this.setAttackTarget((EntityLivingBase) targetPlayer);
				this.getNavigator().tryMoveToEntityLiving(targetPlayer, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * 2.5D);
				if (this.isCollidedHorizontally)
				{
					this.addVelocity(0, 0.6D, 0);
				}
			}
			else if (targetEntity != null && !(targetEntity instanceof EntityAcidPool) && !(targetEntity instanceof EntitySpeciesAlien))
			{
				this.setAttackTarget((EntityLivingBase) targetEntity);
				this.getNavigator().tryMoveToEntityLiving(targetEntity, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * 2.5D);
				if (this.isCollidedHorizontally)
				{
					this.addVelocity(0, 0.6D, 0);
				}
			}
			else
			{
				this.setAttackTarget(null);
			}
		}
	}

	public boolean isDependant()
	{
		return this.isDependant;
	}
}
