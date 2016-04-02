package com.arisux.avp.block;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.entities.tile.TileEntityPowerline;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPowerline extends HookedBlock
{
	public BlockPowerline(Material material)
	{
		super(material);
		this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		this.setRenderNormal(false);
		this.setOpaque(false);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityPowerline();
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
}