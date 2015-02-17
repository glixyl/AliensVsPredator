package com.arisux.avp.entities.mob;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks;
import com.arisux.avp.entities.EntityAcidPool;

public abstract class EntityXenomorph extends EntitySpeciesAlien implements IMob
{
	public int targetQueenId;

	public EntityXenomorph(World world)
	{
		super(world);
		this.jumpMovementFactor = 0.03F;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1F);
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

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		this.fallDistance = 0F;

		if (this.isCollidedHorizontally)
		{
			this.motionY += 0.2F;
		}

		EntityQueen targetQueen = (EntityQueen) this.worldObj.getEntityByID(this.targetQueenId);

		if (targetQueen == null || targetQueen != null && targetQueen.isDead)
		{
			EntityQueen queen = (EntityQueen) WorldUtil.Entities.getEntityInCoordsRange(this.worldObj, EntityQueen.class, new Blocks.CoordData(this), 128, 64);
			targetQueen = queen;
		}

		if (targetQueen == null || targetQueen != null && targetQueen.isDead)
		{
			this.targetQueenId = 0;
			this.setHiveSignature(null);
		}

		if (targetQueen != null)
		{
			this.acquireHiveSignature(targetQueen);

			if (!this.worldObj.isRemote)
			{
				if (this.getNavigator().getPath() == null)
				{
					this.getNavigator().setPath(this.worldObj.getPathEntityToEntity(this, targetQueen, 128, true, true, true, true), 0.8D);
				}

				if (targetQueen.getHealth() < targetQueen.getMaxHealth() / 4)
				{
					this.setAttackTarget(targetQueen.getLastAttacker());
				}
			}
		}

		if (worldObj.getWorldInfo().getWorldTime() % 70 == 0)
		{
			double range = this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();
			Entity targetEntity = (this.worldObj.findNearestEntityWithinAABB(EntityLiving.class, this.boundingBox.expand(range * 2, 64.0D, range * 2), this));
			Entity targetPlayer = (this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand(range * 2, 64.0D, range * 2), this));

			if (targetPlayer != null && !((EntityPlayer) targetPlayer).capabilities.isCreativeMode)
			{
				this.setAttackTarget((EntityLivingBase) targetPlayer);
				this.getNavigator().tryMoveToEntityLiving(targetPlayer, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * 2.5D);

				if (this.isCollidedHorizontally)
				{
					this.addVelocity(0, 0.6D, 0);
				}

			}
			else if (targetEntity != null && !(targetEntity instanceof EntityAcidPool) && !(targetEntity.getClass().getSuperclass().getSuperclass() == EntitySpeciesAlien.class) && !(targetEntity.getClass().getSuperclass() == EntitySpeciesAlien.class))
			{
				this.setAttackTarget((EntityLivingBase) targetEntity);
			}
			else
			{
				this.setAttackTarget(null);
			}
		}
	}

	public void acquireHiveSignature(EntityQueen targetQueen)
	{
		this.targetQueenId = targetQueen.getEntityId();

		if (this.getHiveSignature() == null)
		{
			if (this.worldObj.getWorldTime() % 40 == 0)
			{
				this.getNavigator().tryMoveToEntityLiving(targetQueen, 1.8D);
			}
			if (this.getDistanceToEntity(targetQueen) <= 16)
			{
				this.setHiveSignature(targetQueen.getUniqueID());
			}
		}
	}
}
