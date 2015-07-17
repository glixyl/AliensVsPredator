package com.arisux.avp.block;

import java.util.Random;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockOreSilicon extends HookedBlock
{
	public BlockOreSilicon(Material var2)
	{
		super(var2);
	}
	
	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return AliensVsPredator.items().itemSilicon;
	}
}
