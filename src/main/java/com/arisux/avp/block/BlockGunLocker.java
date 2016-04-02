package com.arisux.avp.block;

import com.arisux.avp.entities.tile.TileEntityGunLocker;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGunLocker extends BlockLocker
{
	public BlockGunLocker(Material material)
	{
		super(material);
		setTickRandomly(true);
		this.setRenderNormal(false);
		this.setOpaque(false);
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityGunLocker();
	}
}
