package com.arisux.avp.block;

import java.util.Random;

import com.arisux.airi.lib.BlockTypes.HookedBlock;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockUnidentifiedTreeTendon extends HookedBlock
{
	public BlockUnidentifiedTreeTendon()
	{
		super(Material.wood);
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return Items.stick;
	}
	
	@Override
    public int quantityDropped(Random random)
    {
        return 2 + random.nextInt(2);
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
}
