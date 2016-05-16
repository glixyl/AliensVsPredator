package org.avp.block;

import java.util.Random;

import org.avp.entities.tile.TileEntityStasisMechanism;
import org.avp.items.ItemEntitySummoner;

import com.arisux.airi.lib.WorldUtil;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockStasisMechanism extends Block
{
    public BlockStasisMechanism(Material material)
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
    public void updateTick(World worldObj, int xCoord, int yCoord, int zCoord, Random par5Random)
    {
        super.updateTick(worldObj, xCoord, yCoord, zCoord, par5Random);
    }

    @Override
    public TileEntity createTileEntity(World world, int meta)
    {
        return new TileEntityStasisMechanism();
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    @Override
    public boolean onBlockActivated(World worldObj, int xCoord, int yCoord, int zCoord, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        TileEntityStasisMechanism tile = (TileEntityStasisMechanism) worldObj.getTileEntity(xCoord, yCoord, zCoord);

        if (tile != null)
        {
            if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemEntitySummoner)
            {
                ItemEntitySummoner item = (ItemEntitySummoner) player.getCurrentEquippedItem().getItem();
                tile.itemstack = new ItemStack(item, 1);
                WorldUtil.Entities.Players.Inventories.consumeItem(player, item);
            }
            else if (player.getCurrentEquippedItem() == null)
            {
                player.inventory.addItemStackToInventory(tile.itemstack);
                tile.itemstack = null;
            }
        }

        return true;
    }

    @Override
    public void onBlockPreDestroy(World world, int posX, int posY, int posZ, int meta)
    {
        super.onBlockPreDestroy(world, posX, posY, posZ, meta);

        TileEntityStasisMechanism tile = (TileEntityStasisMechanism) world.getTileEntity(posX, posY, posZ);

        if (tile != null)
        {
            if (tile.dummyEntity != null)
            {
                world.removeEntity(tile.dummyEntity);
            }

            if (tile.itemstack != null)
            {
                EntityItem entityitem = new EntityItem(world, posX, posY, posZ, tile.itemstack);
                entityitem.delayBeforeCanPickup = 10;
                world.spawnEntityInWorld(entityitem);
            }
        }
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int posX, int posY, int posZ, int meta)
    {
        super.onBlockDestroyedByPlayer(world, posX, posY, posZ, meta);
    }

    @Override
    public void onBlockClicked(World world, int posX, int posY, int posZ, EntityPlayer player)
    {
        super.onBlockClicked(world, posX, posY, posZ, player);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, living, stack);

        TileEntityStasisMechanism tile = (TileEntityStasisMechanism) world.getTileEntity(x, y, z);

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

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }
}
