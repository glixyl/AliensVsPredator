package org.avp.block;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class BlockCustomSlab extends BlockSlab
{
    public BlockCustomSlab(Material material)
    {
        super(false, material);
        this.setBlockName(getLocalizedName() + "Slab");
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

    /**
     * OBF: func_150002_b
     * DEOBF: getFullSlabName
     */
    @Override
    public String func_150002_b(int type)
    {
        return getUnlocalizedName() + "-slab";
    }
}
