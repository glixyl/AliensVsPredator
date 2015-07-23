package com.arisux.avp.block;

import java.util.Random;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.entities.tile.TileEntityNegativeTransformer;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockNegativeTransformer extends HookedBlock
{
	public BlockNegativeTransformer(Material material)
	{
		super(material);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		this.setTickRandomly(true);
		this.setRenderNormal(false);
		this.setOpaque(false);
	}

	@Override
	public void updateTick(World world, int posX, int posY, int posZ, Random random)
	{
		super.updateTick(world, posX, posY, posZ, random);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg)
	{
		;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityNegativeTransformer();
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
	{
		super.breakBlock(world, posX, posY, posZ, blockBroken, meta);
		world.removeTileEntity(posX, posY, posZ);
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
}