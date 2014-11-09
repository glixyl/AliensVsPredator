package com.arisux.avp.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.arisux.avp.entities.tile.TileEntityWorklight;

public class BlockWorklight extends BlockContainer
{
	public BlockWorklight(Material par3)
	{
		super(par3);
		this.setLightOpacity(2);
		float var5 = 0.38F;
		this.setBlockBounds(var5, 0.0F, var5, 1.0F - var5, 0.9F, 1.0F - var5);
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
		return new TileEntityWorklight();
	}

}
