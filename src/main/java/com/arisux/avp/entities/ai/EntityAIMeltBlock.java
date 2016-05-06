package com.arisux.avp.entities.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumDifficulty;

public class EntityAIMeltBlock extends EntityAIYOffsetBlockInteract
{
    private EntityLiving theEntity;
    private int breakingTime;
    private int breakProgress = -1;

    public EntityAIMeltBlock(EntityLiving theEntity)
    {
        this(theEntity, 0);
    }

    public EntityAIMeltBlock(EntityLiving theEntity, int yOffset)
    {
        super(theEntity, yOffset);
        this.theEntity = theEntity;
        this.yOffset = yOffset;
    }

    @Override
    public boolean shouldExecute()
    {
        return this.theEntity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
    }

    @Override
    public void startExecuting()
    {
        super.startExecuting();
        this.breakingTime = 0;
    }

    @Override
    public boolean continueExecuting()
    {
        return this.breakingTime <= 240 && this.theEntity.getDistanceSq((int) this.theEntity.posX, (int) this.theEntity.posY + yOffset, (int) this.theEntity.posZ) < 4.0D && block != Blocks.air;
    }

    @Override
    public void resetTask()
    {
        super.resetTask();
        this.theEntity.worldObj.destroyBlockInWorldPartially(this.theEntity.getEntityId(), (int) this.theEntity.posX, (int) this.theEntity.posY + yOffset, (int) this.theEntity.posZ, -1);
    }

    @Override
    public void updateTask()
    {
        super.updateTask();

        if (this.theEntity.getRNG().nextInt(20) == 0)
        {
            this.theEntity.worldObj.playSoundEffect(this.theEntity.posX, this.theEntity.posY, this.theEntity.posZ, "minecraft:random.fizz", 0.3F, 1F);
        }

        ++this.breakingTime;
        int i = (int) (this.breakingTime / 240.0F * 10.0F);

        if (i != this.breakProgress)
        {
            this.theEntity.worldObj.destroyBlockInWorldPartially(this.theEntity.getEntityId(), (int) Math.floor(this.theEntity.posX), (int) this.theEntity.posY + yOffset, (int) Math.floor(this.theEntity.posZ), i);
            this.breakProgress = i;
        }

        if (this.breakingTime == 240 && (this.theEntity.worldObj.difficultySetting == EnumDifficulty.NORMAL || this.theEntity.worldObj.difficultySetting == EnumDifficulty.HARD))
        {
            if (block != Blocks.air)
            {
                this.theEntity.worldObj.setBlockToAir((int) Math.floor(this.theEntity.posX), (int) this.theEntity.posY - 1, (int) Math.floor(this.theEntity.posZ));
                this.theEntity.worldObj.playAuxSFX(2001, (int) Math.floor(this.theEntity.posX), (int) this.theEntity.posY + yOffset, (int) Math.floor(this.theEntity.posZ), Block.getIdFromBlock(this.block));
                this.resetTask();
                this.theEntity = (EntityLiving) this.theEntity.worldObj.getEntityByID(this.theEntity.getEntityId());
            }
        }
    }
}
