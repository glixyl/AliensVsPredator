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
		private Block parentBlock;
		private TileEntity parentTileEntity;
		public GhostBlock()
		{
			super(Material.circuits); //The only reason this is here is because shadekiller666 is awesome.
			setTickRandomly(true);
		}

		@Override
		public int getRenderType()
		{
			return -1;
		}
		
		@Override
		public void updateTick(World worldIn, int x, int y, int z, Random rand)
		{
			getParentBlock(worldIn, x, y, z);
		}
		
		public Block getParentBlock(World world, int xPos, int yPos, int zPos)
		{
			for(int x = xPos + 1; x <= (3 + xPos); x++)
			{
				if(world.getTileEntity(x, yPos, zPos) instanceof TileEntityBlastdoor)
				{
					parentTileEntity = (TileEntityBlastdoor) world.getTileEntity(x, yPos, zPos);
					parentBlock = (BlockBlastdoor) world.getBlock(x, yPos, zPos);
					return parentBlock;
				}
				for(int y = yPos + 1; y <= (3 + yPos); y++)
				{
					if(world.getTileEntity(x, y, zPos) instanceof TileEntityBlastdoor)
					{
						parentTileEntity = (TileEntityBlastdoor) world.getTileEntity(x, y, zPos);
						parentBlock = (BlockBlastdoor) world.getBlock(x, y, zPos);
						return parentBlock;
					}
					for(int z = zPos + 1; z <= (3 + zPos); z++)
					{
						if(world.getTileEntity(x, y, z) instanceof TileEntityBlastdoor)
						{
							parentTileEntity = (TileEntityBlastdoor) world.getTileEntity(x, y, z);
							parentBlock = (BlockBlastdoor) world.getBlock(x, y, z);
							return parentBlock;
						}
					}
				}
			}
			
			for(int x = xPos - 1; x >= (xPos - 3); x--)
			{
				if(world.getTileEntity(x, yPos, zPos) instanceof TileEntityBlastdoor)
				{
					parentTileEntity = (TileEntityBlastdoor) world.getTileEntity(x, yPos, zPos);
					parentBlock = (BlockBlastdoor) world.getBlock(x, yPos, zPos);
					return parentBlock;
				}
				for(int y = yPos - 1; y >= (yPos - 3); y--)
				{
					if(world.getTileEntity(x, y, zPos) instanceof TileEntityBlastdoor)
					{
						parentTileEntity = (TileEntityBlastdoor) world.getTileEntity(x, y, zPos);
						parentBlock = (BlockBlastdoor) world.getBlock(x, y, zPos);
						return parentBlock;
					}
					for(int z = zPos - 1; z >= (zPos - 3); z--)
					{
						if(world.getTileEntity(x, y, z) instanceof TileEntityBlastdoor)
						{
							parentTileEntity = (TileEntityBlastdoor) world.getTileEntity(x, y, z);
							parentBlock = (BlockBlastdoor) world.getBlock(x, y, z);
							return parentBlock;
						}
					}
				}
			}
			return null;
		}
		
		@Override
		public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int side, float subX, float subY, float subZ)
		{
			getParentBlock(world, posX, posY, posZ);
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
				getParentBlock(world, posX, posY, posZ);
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