package org.avp.block;

import java.util.Random;

import org.avp.entities.tile.TileEntityWorkstation;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockWorkstation extends Block
{
    public BlockWorkstation(Material material)
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
    public void updateTick(World world, int posX, int posY, int posZ, Random rand)
    {
        super.updateTick(world, posX, posY, posZ, rand);
    }

    @Override
    public TileEntity createTileEntity(World world, int meta)
    {
        return new TileEntityWorkstation();
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    @Override
    public boolean onBlockActivated(World worldObj, int xCoord, int yCoord, int zCoord, EntityPlayer player, int side, float subX, float subY, float subZ)
    {
        return super.onBlockActivated(worldObj, xCoord, yCoord, zCoord, player, side, subX, subY, subZ);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, living, stack);

        TileEntityWorkstation tile = (TileEntityWorkstation) world.getTileEntity(x, y, z);

        if (tile != null)
        {
            tile.setDirection((byte) (MathHelper.floor_double(((living.rotationYaw * 4F) / 360F) + 0.5D) & 3));
            world.markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
        }
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }
}
