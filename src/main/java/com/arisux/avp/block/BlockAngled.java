package com.arisux.avp.block;

import com.arisux.avp.AliensVsPredator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAngled extends Block
{
	public BlockAngled(Material material)
	{
		super(material);
	}
	
	@Override
	public int getRenderType()
	{
		return AliensVsPredator.renderer().renderTypeShape.getRenderId();
	}
}
