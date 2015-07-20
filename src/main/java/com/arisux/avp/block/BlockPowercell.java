package com.arisux.avp.block;

import com.arisux.airi.lib.BlockTypes.HookedBlockContainer;
import com.arisux.avp.entities.tile.TileEntityPowercell;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPowercell extends HookedBlockContainer
{
	public BlockPowercell(Material material)
	{
		super(material);
		this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityPowercell();
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
	{
		super.breakBlock(world, posX, posY, posZ, blockBroken, meta);
	}
}