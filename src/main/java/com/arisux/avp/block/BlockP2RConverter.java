package com.arisux.avp.block;

import com.arisux.avp.entities.tile.TileEntityP2RConverter;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockP2RConverter extends Block
{
	public BlockP2RConverter(Material material)
	{
		super(material);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		this.setTickRandomly(true);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		return;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World worldObj, int xCoord, int yCoord, int zCoord, EntityPlayer player, int side, float subX, float subY, float subZ)
	{
		return super.onBlockActivated(worldObj, xCoord, yCoord, zCoord, player, side, subX, subY, subZ);
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		return new TileEntityP2RConverter();
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
}
