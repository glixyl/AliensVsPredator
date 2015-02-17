package com.arisux.avp.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockTypes.HookedBlockContainer;
import com.arisux.avp.entities.tile.TileEntityHiveResin;

public class BlockHiveResin extends HookedBlockContainer
{
	public BlockHiveResin(Material material)
	{
		super(material);
		this.renderNormal = true;
	}

	@Override
	public void updateTick(World worldObj, int posX, int posY, int posZ, Random rand)
	{
		super.updateTick(worldObj, posX, posY, posZ, rand);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityHiveResin();
	}

	@Override
	public void onBlockPreDestroy(World world, int posX, int posY, int posZ, int oldMetadata)
	{
		super.onBlockPreDestroy(world, posX, posY, posZ, oldMetadata);
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int posX, int posY, int posZ, int metadata)
	{
		TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);

		if (tileEntity != null && tileEntity instanceof TileEntityHiveResin)
		{
			TileEntityHiveResin resin = (TileEntityHiveResin) tileEntity;

			if (resin.getBlockCovering() != null)
			{
				Block block = resin.getBlockCovering();
				world.setBlock(posX, posY, posZ, block);
			}
		}
	}
}
