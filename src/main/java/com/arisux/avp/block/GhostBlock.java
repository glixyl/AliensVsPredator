package com.arisux.avp.block;

import java.util.Random;

import com.arisux.avp.entities.tile.TileEntityBlastdoor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class GhostBlock extends Block
{
	public Block parentBlock;
	public TileEntity parentTileEntity;
	
	public GhostBlock()
	{
		super(Material.glass); //The only reason this is here is because shadekiller666 is awesome.
		setTickRandomly(true);
	}

	@Override
	public int getRenderType()
	{
		return -1;
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
	public int quantityDropped(Random p_149745_1_)
	{
		return 0;
	}

	@Override
	public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int side, float subX, float subY, float subZ)
	{
		try
		{
			return parentBlock.onBlockActivated(world, parentTileEntity.xCoord, parentTileEntity.yCoord, parentTileEntity.zCoord, player, side, subX, subY, subZ);
		}
		catch(NullPointerException e)
		{

		}
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		if(this.parentBlock != null && this.parentTileEntity != null)
		{
			TileEntityBlastdoor te = (TileEntityBlastdoor) this.parentTileEntity;
			if(te.isDoorOpen())
			{
				return null;
			}
			else
			{
				return AxisAlignedBB.getBoundingBox((double)p_149668_2_ + this.minX, (double)p_149668_3_ + this.minY, (double)p_149668_4_ + this.minZ, (double)p_149668_2_ + this.maxX, (double)p_149668_3_ + this.maxY, (double)p_149668_4_ + this.maxZ);
			}
		}
		return AxisAlignedBB.getBoundingBox((double)p_149668_2_ + this.minX, (double)p_149668_3_ + this.minY, (double)p_149668_4_ + this.minZ, (double)p_149668_2_ + this.maxX, (double)p_149668_3_ + this.maxY, (double)p_149668_4_ + this.maxZ);
	}

	@Override
	public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
	{
		//getParentBlock(world, posX, posY, posZ);
		if(parentBlock != null && parentTileEntity != null)
		{
			try
			{
				TileEntityBlastdoor te = (TileEntityBlastdoor) parentTileEntity;
				if(!te.isDoorOpen())
				{
					parentBlock.breakBlock(world, parentTileEntity.xCoord, parentTileEntity.yCoord, parentTileEntity.zCoord, blockBroken, meta);
				}
			}
			catch(NullPointerException e)
			{

			}
		}
		else
		{
			try
			{
				TileEntityBlastdoor te = (TileEntityBlastdoor) parentTileEntity;
				if(!te.isDoorOpen())
				{
					parentBlock.breakBlock(world, parentTileEntity.xCoord, parentTileEntity.yCoord, parentTileEntity.zCoord, blockBroken, meta);
				}
			}
			catch(NullPointerException e)
			{

			}
		}			
	}
}