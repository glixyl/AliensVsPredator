package com.arisux.avp.block;

import com.arisux.avp.entities.tile.TileEntityNegativeTransformer;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockNegativeTransformer extends BlockTransformer
{
	public BlockNegativeTransformer(Material material)
	{
		super(material);
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		return new TileEntityNegativeTransformer();
	}
}