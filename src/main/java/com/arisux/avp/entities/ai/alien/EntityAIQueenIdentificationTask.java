package com.arisux.avp.entities.ai.alien;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.avp.entities.mob.EntityQueen;
import com.arisux.avp.entities.mob.EntityXenomorph;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIQueenIdentificationTask extends EntityAIBase
{
    private EntityXenomorph xenomorph;

    public EntityAIQueenIdentificationTask(EntityXenomorph xenomorph)
    {
        super();
        this.xenomorph = xenomorph;
    }

    @Override
    public boolean shouldExecute()
    {
        return xenomorph.isDependant();
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

        Entity entity = xenomorph.worldObj.getEntityByID(xenomorph.targetQueenId);
        EntityQueen targetQueen = entity instanceof EntityQueen ? (EntityQueen) xenomorph.worldObj.getEntityByID(xenomorph.targetQueenId) : null;

        if (targetQueen == null || targetQueen != null && targetQueen.isDead)
        {
            EntityQueen queen = (EntityQueen) WorldUtil.Entities.getEntityInCoordsRange(xenomorph.worldObj, EntityQueen.class, new WorldUtil.Blocks.CoordData(xenomorph), 64, 64);
            targetQueen = queen;
        }

        if (targetQueen == null || targetQueen != null && targetQueen.isDead)
        {
            xenomorph.targetQueenId = 0;
            xenomorph.setHiveSignature(null);
        }

        if (targetQueen != null)
        {
            xenomorph.targetQueenId = targetQueen.getEntityId();

            if (xenomorph.getHiveSignature() == null)
            {
                if (xenomorph.worldObj.getWorldTime() % 40 == 0)
                {
                    xenomorph.getNavigator().tryMoveToEntityLiving(targetQueen, 1.8D);
                }
                if (xenomorph.getDistanceToEntity(targetQueen) <= 16)
                {
                    xenomorph.setHiveSignature(targetQueen.getUniqueID());
                }
            }

            if (!xenomorph.worldObj.isRemote)
            {
                if (xenomorph.getNavigator().getPath() == null)
                {
                    xenomorph.getNavigator().setPath(xenomorph.worldObj.getPathEntityToEntity(xenomorph, targetQueen, 32, true, true, true, true), 0.8D);
                }

                if (targetQueen.getHealth() < targetQueen.getMaxHealth() / 4)
                {
                    xenomorph.setAttackTarget(targetQueen.getLastAttacker());
                }
            }
        }
    }
}
