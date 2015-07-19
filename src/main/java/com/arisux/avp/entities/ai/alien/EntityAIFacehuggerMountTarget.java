package com.arisux.avp.entities.ai.alien;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.avp.entities.mob.EntityFacehugger;
import com.arisux.avp.entities.mob.EntitySpeciesAlien;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIFacehuggerMountTarget extends EntityAIBase
{
	private EntityFacehugger facehugger;

	public EntityAIFacehuggerMountTarget(EntityFacehugger facehugger)
	{
		super();
		this.facehugger = facehugger;
	}

	@Override
	public boolean shouldExecute()
	{
		return this.facehugger.isFertile();
	}

	@Override
	public void startExecuting()
	{
		super.startExecuting();
	}

	@Override
	public boolean continueExecuting()
	{
		return true;
	}

	@Override
	public void resetTask()
	{
		super.resetTask();
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		if (this.facehugger.getEntityToFacehug() != null && (this.facehugger.getEntityToFacehug().isDead || this.facehugger.getEntityToFacehug() instanceof EntitySpeciesAlien || this.facehugger.getEntityToFacehug().ridingEntity != null))
		{
			this.facehugger.setEntityToFacehug(null);
		}
		
		if (this.facehugger.getEntityToFacehug() == null)
		{
			EntityLivingBase target = (EntityLivingBase) WorldUtil.Entities.getEntityInCoordsRange(facehugger.worldObj, EntityLivingBase.class, new WorldUtil.Blocks.CoordData(facehugger), 64, 64);
			
			if (!(target instanceof EntitySpeciesAlien))
			{
				this.facehugger.setEntityToFacehug(target);
			}
		}

		if (this.facehugger.getEntityToFacehug() != null)
		{
			if (this.facehugger.getRNG().nextInt(10) == 0)
			{
				facehugger.getNavigator().tryMoveToEntityLiving(this.facehugger.getEntityToFacehug(), 0.8D);
			}
			
			if (facehugger.getDistanceToEntity(this.facehugger.getEntityToFacehug()) <= 10 && facehugger.isCollidedVertically)
			{
				this.facehugger.jumpAtEntity(this.facehugger, this.facehugger.getEntityToFacehug(), 0.6);
			}

			if (!facehugger.worldObj.isRemote)
			{
				if (facehugger.getNavigator().getPath() == null)
				{
					facehugger.getNavigator().setPath(facehugger.worldObj.getPathEntityToEntity(facehugger, this.facehugger.getEntityToFacehug(), 32, true, true, true, true), 0.03D);
				}
			}
		}
	}
}
