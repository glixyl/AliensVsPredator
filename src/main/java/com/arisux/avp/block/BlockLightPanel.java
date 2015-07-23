package com.arisux.avp.block;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.entities.tile.TileEntityLightPanel;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockLightPanel extends HookedBlock
{
	public BlockLightPanel(Material material, boolean isLightOn)
	{
		super(material);
		this.setLightOpacity(2);
		this.setLightLevel(isLightOn ? 1 : 0);
		this.disableIcon();
		this.setRenderNormal(false);
		this.setOpaque(false);
	}
	
	@Override
	public int onBlockPlaced(World world, int posX, int posY, int posZ, int side, float subX, float subY, float subZ, int meta)
	{
		this.setLightLevel(0);
		world.setLightValue(EnumSkyBlock.Block, posX, posY, posZ, 7);
		world.func_147479_m(posX, posY, posZ);
		return super.onBlockPlaced(world, posX, posY, posZ, side, subX, subY, subZ, meta);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityLightPanel();
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		super.registerBlockIcons(iconRegister);
	}
	
	@Override
	public int getRenderType()
	{
		return  -1;
	}
}
