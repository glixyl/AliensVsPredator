package com.arisux.avp.block;

import java.util.Random;

import com.arisux.avp.entities.tile.TileEntityHiveNode;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockHiveNode extends Block
{
	public BlockHiveNode(Material material)
	{
		super(material);
		this.setLightOpacity(2);
		float size = 0.38F;
		this.setBlockBounds(size, 0.0F, size, 1.0F - size, 0.9F, 1.0F - size);
	}

	@Override
	public void updateTick(World world, int posX, int posY, int posZ, Random random)
	{
		super.updateTick(world, posX, posY, posZ, random);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityHiveNode();
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
}
