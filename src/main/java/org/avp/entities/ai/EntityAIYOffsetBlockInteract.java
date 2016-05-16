package org.avp.entities.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;

public abstract class EntityAIYOffsetBlockInteract extends EntityAIBase
{
    protected EntityLiving theEntity;
    protected int yOffset;
    protected Block block;
    boolean hasStoppedDoorInteraction;

    public EntityAIYOffsetBlockInteract(EntityLiving theEntity, int yOffset)
    {
        this.theEntity = theEntity;
        this.yOffset = yOffset;
        this.block = Blocks.air;
    }

    @Override
    public boolean shouldExecute()
    {
        this.block = this.theEntity.worldObj.getBlock((int) this.theEntity.posX, (int) this.theEntity.posY + yOffset, (int) this.theEntity.posZ);
        return this.block != null;
    }

    @Override
    public boolean continueExecuting()
    {
        return true;
    }

    @Override
    public void startExecuting()
    {
        this.getOffsetBlock();
    }

    @Override
    public void updateTask()
    {
        this.getOffsetBlock();
    }

    public void getOffsetBlock()
    {
        if (this.block == Blocks.air)
        {
            this.block = this.theEntity.worldObj.getBlock((int) this.theEntity.posX, (int) this.theEntity.posY + this.yOffset, (int) this.theEntity.posZ);
        }
    }
}
