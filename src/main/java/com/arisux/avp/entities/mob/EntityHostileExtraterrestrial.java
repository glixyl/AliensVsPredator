package com.arisux.avp.entities.mob;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.airi.lib.WorldUtil.Entities;

public abstract class EntityHostileExtraterrestrial extends EntityMob implements IMob
{
	public ArrayList<Class<? extends Entity>> targetTypes = new ArrayList<Class<? extends Entity>>();
	private int targetSearchRadius;

	public EntityHostileExtraterrestrial(World world)
	{
		super(world);
		this.targetSearchRadius = 16;
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.getAttackTarget() == null || this.getAttackTarget() != null && this.getAttackTarget().isDead)
		{
			for (Class<? extends Entity> target : targetTypes)
			{
				EntityLivingBase possibleTarget = (EntityLivingBase) Entities.getEntityInCoordsRange(this.worldObj, target, new CoordData(this), this.targetSearchRadius, this.targetSearchRadius);

				if (possibleTarget != null && !possibleTarget.isDead)
				{
					if (possibleTarget instanceof EntityPlayer && ((EntityPlayer)possibleTarget).capabilities.isCreativeMode)
					{
						break;
					}
					
					this.setAttackTarget(possibleTarget);
				}
			}
		}
	}

	public ArrayList<Class<? extends Entity>> getTargetTypes()
	{
		return this.targetTypes;
	}

	public void addTargetType(Class<? extends Entity> targetType)
	{
		if (!this.targetTypes.contains(targetType))
		{
			this.targetTypes.add(targetType);
		}
	}

	public int getTargetSearchRadius()
	{
		return targetSearchRadius;
	}

	public void setTargetSearchRadius(int targetRadius)
	{
		this.targetSearchRadius = targetRadius;
	}
}
