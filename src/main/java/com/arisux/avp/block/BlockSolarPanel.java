package com.arisux.avp.block;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.entities.tile.TileEntitySolarPanel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSolarPanel extends HookedBlock
{
	public BlockSolarPanel(Material material)
	{
		super(material);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		this.setRenderNormal(false);
		this.setOpaque(false);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg)
	{
		;
	}
	
	@Override
	public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
	{
		super.breakBlock(world, posX, posY, posZ, blockBroken, meta);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		return new TileEntitySolarPanel();
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