package org.avp.entities.ai.alien;

import org.avp.entities.mob.EntityXenomorph;

import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIClimb extends EntityAIBase
{
    private EntityXenomorph xenomorph;
    private float climbSpeed;

    public EntityAIClimb(EntityXenomorph xenomorph, float climbSpeed)
    {
        super();
        this.xenomorph = xenomorph;
        this.climbSpeed = climbSpeed;
    }

    @Override
    public boolean shouldExecute()
    {
        return xenomorph.canClimb();
    }

    @Override
    public void startExecuting()
    {
        super.startExecuting();
    }

    @Override
    public boolean continueExecuting()
    {
        return xenomorph.canClimb() && xenomorph.isCollidedHorizontally;
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

        xenomorph.motionY += this.climbSpeed;
    }
}
