package com.arisux.avp.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockTypeLib.HookedBlockContainer;
import com.arisux.avp.entities.tile.TileEntityBlastdoor;

public class BlockBlastdoor extends HookedBlockContainer
{
	public BlockBlastdoor(Material material)
	{
		super(material);
		this.disableIcon();
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityBlastdoor();
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}
}
