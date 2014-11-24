package com.arisux.avp.entities.mob;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.arisux.avp.entities.EntityAcidPool;

public abstract class EntityXenomorph extends EntitySpeciesAlien implements IMob
{
	public EntityXenomorph(World world)
	{
		super(world);
		this.jumpMovementFactor = 0.1F;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8F);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte) 0));
	}

	@Override
	protected boolean canDespawn()
	{
		return super.canDespawn();
	}

	@Override
	protected boolean isAIEnabled()
	{
		return super.isAIEnabled();
	}

	@Override
	public boolean isOnLadder()
	{
		return this.isBesideClimbableBlock();
	}

	public void setBesideClimbableBlock(boolean par1)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (par1)
		{
			b0 = (byte) (b0 | 1);
		} else
		{
			b0 &= -2;
		}

		this.dataWatcher.updateObject(16, Byte.valueOf(b0));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (worldObj.getWorldInfo().getWorldTime() % 70L == 0L)
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

			} else if (targetEntity != null && !(targetEntity instanceof EntityAcidPool) && !(targetEntity.getClass().getSuperclass().getSuperclass() == EntitySpeciesAlien.class) && !(targetEntity.getClass().getSuperclass() == EntitySpeciesAlien.class))
			{
				this.setAttackTarget((EntityLivingBase) targetEntity);
			}
		}
	}

	public boolean isBesideClimbableBlock()
	{
		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	public abstract ResourceLocation getResource();
}
