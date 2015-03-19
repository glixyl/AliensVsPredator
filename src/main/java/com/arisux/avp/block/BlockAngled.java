package com.arisux.avp.block;

import net.minecraft.block.material.Material;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.AliensVsPredator;

public class BlockAngled extends HookedBlock
{
	public BlockAngled(Material material)
	{
		super(material);
	}
	
	@Override
	public int getRenderType()
	{
		return AliensVsPredator.renderer().renderTypeAngled.getRenderId();
	}
}
