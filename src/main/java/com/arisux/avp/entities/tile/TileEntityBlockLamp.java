package com.arisux.avp.entities.tile;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBlockLamp extends TileEntity
{
	public int tick = 80;
	public double yaw = 0.0D;
	public double yaw2 = 0.0D;

	@Override
	public void updateEntity()
	{
		super.updateEntity();
	}
	

	@Override
	public Block getBlockType()
	{
		return Blocks.beacon;
	}
}
