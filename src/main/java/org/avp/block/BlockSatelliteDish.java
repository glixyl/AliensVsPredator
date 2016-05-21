package org.avp.block;

import java.util.Random;

import org.avp.entities.tile.TileEntitySatelliteDish;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSatelliteDish extends Block
{
    public BlockSatelliteDish()
    {
        super(Material.iron);
        this.setBlockBounds(0F, 0F, 0F, 1F, 2F, 1F);
        this.setTickRandomly(true);
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
    public void updateTick(World world, int posX, int posY, int posZ, Random rand)
    {
        super.updateTick(world, posX, posY, posZ, rand);
    }

    @Override
    public TileEntity createTileEntity(World world, int meta)
    {
        return new TileEntitySatelliteDish();
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }
}
