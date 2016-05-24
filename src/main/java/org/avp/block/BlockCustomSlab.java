package org.avp.block;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class BlockCustomSlab extends BlockSlab
{
    public BlockCustomSlab(Material material)
    {
        super(false, material);
        this.setUnlocalizedName(getLocalizedName() + "Slab");
    }

    @Override
    public int getRenderType()
    {
        return 0;
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
    public String getFullSlabName(int type)
    {
        return getUnlocalizedName() + "-slab";
    }
}
