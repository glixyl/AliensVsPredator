package com.arisux.avp.block;

import java.util.Random;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.entities.tile.TileEntityBlastdoor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class GhostBlock extends HookedBlock
{
	public Block parentBlock;
	public TileEntity parentTileEntity;

	public GhostBlock()
	{
		super(Material.glass);
		setTickRandomly(true);
		this.setRenderNormal(false);
		this.setOpaque(false);
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
	public int quantityDropped(Random rand)
	{
		return 0;
	}

	@Override
	public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int side, float subX, float subY, float subZ)
	{
		return parentBlock.onBlockActivated(world, parentTileEntity.xCoord, parentTileEntity.yCoord, parentTileEntity.zCoord, player, side, subX, subY, subZ);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int posX, int posY, int posZ)
	{
		if (this.parentBlock != null && this.parentTileEntity != null)
		{
			TileEntityBlastdoor te = (TileEntityBlastdoor) worldIn.getTileEntity(this.parentTileEntity.xCoord, this.parentTileEntity.yCoord, this.parentTileEntity.zCoord);
			
			if (te.isDoorOpen())
			{
				return null;
			}
			else
			{
				return AxisAlignedBB.getBoundingBox((double) posX + this.minX, (double) posY + this.minY, (double) posZ + this.minZ, (double) posX + this.maxX, (double) posY + this.maxY, (double) posZ + this.maxZ);
			}
		}

		return AxisAlignedBB.getBoundingBox((double) posX + this.minX, (double) posY + this.minY, (double) posZ + this.minZ, (double) posX + this.maxX, (double) posY + this.maxY, (double) posZ + this.maxZ);
	}

	@Override
	public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
	{
		if (parentBlock != null && parentTileEntity != null)
		{
			TileEntityBlastdoor te = (TileEntityBlastdoor) parentTileEntity;

			if (!te.isDoorOpen())
			{
				parentBlock.breakBlock(world, parentTileEntity.xCoord, parentTileEntity.yCoord, parentTileEntity.zCoord, blockBroken, meta);
			}
		}
	}
}
