package org.avp.block;

import org.avp.entities.tile.TileEntitySolarPanel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSolarPanel extends Block
{
    public BlockSolarPanel(Material material)
    {
        super(material);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
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
    public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
    {
        super.breakBlock(world, posX, posY, posZ, blockBroken, meta);
    }

    @Override
    public TileEntity createTileEntity(World world, int meta)
    {
        return new TileEntitySolarPanel();
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
