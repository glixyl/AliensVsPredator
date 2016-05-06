package com.arisux.avp.block;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockUnidentifiedTreeSapling extends BlockSapling
{
    public BlockUnidentifiedTreeSapling()
    {
        super();
    }

    @Override
    public int getRenderType()
    {
        return 1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(this.getTextureName());
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.blockIcon;
    }
}
