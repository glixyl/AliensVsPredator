package com.arisux.avp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockCustomStairs extends BlockStairs
{
    public BlockCustomStairs(Block parentBlock)
    {
        super(parentBlock, 0);
        this.setHardness(3.0F);
        this.setResistance(3.0F);
        this.setStepSound(parentBlock.stepSound);
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return 10;
    }
}
