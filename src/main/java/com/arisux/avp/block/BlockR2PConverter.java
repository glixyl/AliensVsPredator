package com.arisux.avp.block;

import com.arisux.avp.entities.tile.TileEntityR2PConverter;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockR2PConverter extends Block
{
    public BlockR2PConverter(Material material)
    {
        super(material);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
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
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
    {
        TileEntityR2PConverter tile = (TileEntityR2PConverter) world.getTileEntity(x, y, z);

        if (world.getBlock(tileX, tileY, tileZ) == null)
        {
            tile.isActiveRedstoneWireAttached = false;
        }
    }

    @Override
    public boolean onBlockActivated(World worldObj, int xCoord, int yCoord, int zCoord, EntityPlayer player, int side, float subX, float subY, float subZ)
    {
        return true;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public TileEntity createTileEntity(World world, int meta)
    {
        return new TileEntityR2PConverter();
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
}
