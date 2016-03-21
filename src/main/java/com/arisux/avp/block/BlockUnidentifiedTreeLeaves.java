package com.arisux.avp.block;

import net.minecraft.block.BlockLeaves;
import net.minecraft.util.IIcon;

public class BlockUnidentifiedTreeLeaves extends BlockLeaves
{
	public BlockUnidentifiedTreeLeaves()
	{
		super();
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return null;
	}

	@Override
	public String[] func_150125_e()
	{
		return null;
	}
}
