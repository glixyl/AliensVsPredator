package com.arisux.avp.block;

import com.arisux.avp.entities.tile.TileEntityGunLocker;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGunLocker extends BlockLocker
{
    public BlockGunLocker(Material material)
    {
        super(material);
        setTickRandomly(true);
    }

    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        return;
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
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityGunLocker();
    }
}
