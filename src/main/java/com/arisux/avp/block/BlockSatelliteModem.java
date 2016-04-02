package com.arisux.avp.block;

import java.util.Random;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.entities.tile.TileEntitySatelliteModem;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSatelliteModem extends HookedBlock
{
	public BlockSatelliteModem(Material material)
	{
		super(material);
		this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		this.setTickRandomly(true);
		this.setRenderNormal(false);
		this.setOpaque(false);
	}

	@Override
	public void updateTick(World world, int posX, int posY, int posZ, Random rand)
	{
		super.updateTick(world, posX, posY, posZ, rand);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg)
	{
		;
	}

	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		return new TileEntitySatelliteModem();
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