package com.arisux.avp.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockTypeLib.HookedBlockContainer;
import com.arisux.avp.entities.tile.TileEntityBlastdoor;

public class BlockBlastdoor extends HookedBlockContainer
{
	public BlockBlastdoor(Material par3)
	{
		super(par3);
		this.setBlockBounds(0F, 0F, 0F, 4F, 3F, 1F);
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
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityBlastdoor();
	}
}
