package com.arisux.avp.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockTypes.HookedBlockContainer;
import com.arisux.avp.entities.tile.TileEntityNetworkCable;

public class BlockNetworkCable extends HookedBlockContainer
{
	public BlockNetworkCable(Material material)
	{
		super(material);
		this.setBlockBounds(0.375F, 0.375F, 0.375F, 0.625F, 0.625F, 0.625F);
		this.setTickRandomly(true);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.updateTick(par1World, par2, par3, par4, par5Random);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg)
	{
		;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityNetworkCable();
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}
}