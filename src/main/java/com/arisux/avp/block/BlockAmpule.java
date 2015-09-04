package com.arisux.avp.block;

import java.util.Random;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.entities.tile.TileEntityAmpule;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAmpule extends HookedBlock
{
	public BlockAmpule()
	{
		super(Material.iron);
		this.disableIcon();
		this.setLightOpacity(2);
		this.setRenderNormal(false);
		this.setOpaque(false);
		float size = 0.3F;
		this.setBlockBounds(size, 0.0F, size, 1.0F - size, 1F, 1.0F - size);
	}

	@Override
	public void updateTick(World world, int posX, int posY, int posZ, Random random)
	{
		super.updateTick(world, posX, posY, posZ, random);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityAmpule();
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess blockaccess, int posX, int posY, int posZ, int side)
	{
		return false;
	}
}
