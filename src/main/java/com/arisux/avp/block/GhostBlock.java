package com.arisux.avp.block;

import com.arisux.avp.entities.tile.PoweredTileEntity;
import com.arisux.avp.entities.tile.TileEntityBlastdoor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GhostBlock extends Block
	{
		private Block parentBlock;
		private PoweredTileEntity parentTileEntity;
		
		public GhostBlock()
		{
			super(Material.iron);
		}

		@Override
		public int getRenderType()
		{
			return -1;
		}

		public Block getParentBlock(World world, int xPos, int yPos, int zPos)
		{
			for(int x = xPos + 1; x <= (3 + xPos); x++)
			{
				if(world.getTileEntity(x, yPos, zPos) instanceof TileEntityBlastdoor)
				{
					TileEntityBlastdoor te = (TileEntityBlastdoor) world.getTileEntity(x, yPos, zPos);
					parentTileEntity = te;
					BlockBlastdoor b = (BlockBlastdoor) world.getBlock(x, yPos, zPos);
					parentBlock = b;
					return parentBlock;
				}
				for(int y = yPos + 1; y <= (3 + yPos); y++)
				{
					if(world.getTileEntity(x, y, zPos) instanceof TileEntityBlastdoor)
					{
						TileEntityBlastdoor te = (TileEntityBlastdoor) world.getTileEntity(x, y, zPos);
						parentTileEntity = te;
						BlockBlastdoor b = (BlockBlastdoor) world.getBlock(x, y, zPos);
						parentBlock = b;
						return parentBlock;
					}
					for(int z = zPos + 1; z <= (3 + zPos); z++)
					{
						if(world.getTileEntity(x, y, z) instanceof TileEntityBlastdoor)
						{
							TileEntityBlastdoor te = (TileEntityBlastdoor) world.getTileEntity(x, y, z);
							parentTileEntity = te;
							BlockBlastdoor b = (BlockBlastdoor) world.getBlock(x, y, z);
							parentBlock = b;
							return parentBlock;
						}
					}
				}
			}
			
			for(int x = xPos - 1; x >= (xPos - 3); x--)
			{
				if(world.getTileEntity(x, yPos, zPos) instanceof TileEntityBlastdoor)
				{
					TileEntityBlastdoor te = (TileEntityBlastdoor) world.getTileEntity(x, yPos, zPos);
					parentTileEntity = te;
					BlockBlastdoor b = (BlockBlastdoor) world.getBlock(x, yPos, zPos);
					parentBlock = b;
					return parentBlock;
				}
				for(int y = yPos - 1; y >= (yPos - 3); y--)
				{
					if(world.getTileEntity(x, y, zPos) instanceof TileEntityBlastdoor)
					{
						TileEntityBlastdoor te = (TileEntityBlastdoor) world.getTileEntity(x, y, zPos);
						parentTileEntity = te;
						BlockBlastdoor b = (BlockBlastdoor) world.getBlock(x, y, zPos);
						parentBlock = b;
						return parentBlock;
					}
					for(int z = zPos - 1; z >= (zPos - 3); z--)
					{
						if(world.getTileEntity(x, y, z) instanceof TileEntityBlastdoor)
						{
							TileEntityBlastdoor te = (TileEntityBlastdoor) world.getTileEntity(x, y, z);
							parentTileEntity = te;
							BlockBlastdoor b = (BlockBlastdoor) world.getBlock(x, y, z);
							parentBlock = b;
							return parentBlock;
						}
					}
				}
			}
			return parentBlock;
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
		public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
		{
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