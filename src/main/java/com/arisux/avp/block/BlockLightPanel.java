package com.arisux.avp.block;

import com.arisux.airi.lib.BlockTypes.HookedBlockContainer;
import com.arisux.avp.entities.tile.TileEntityLightPanel;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockLightPanel extends HookedBlockContainer
{
	public BlockLightPanel(Material material, boolean isLightOn)
	{
		super(material);
		this.setLightOpacity(2);
		this.setLightLevel(isLightOn ? 1 : 0);
		this.disableIcon();
	}
	
	@Override
	public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
	{
		this.setLightLevel(0);
		p_149660_1_.setLightValue(EnumSkyBlock.Block, p_149660_2_, p_149660_3_, p_149660_4_, 7);
		p_149660_1_.func_147479_m(p_149660_2_, p_149660_3_, p_149660_4_);
		return super.onBlockPlaced(p_149660_1_, p_149660_2_, p_149660_3_, p_149660_4_, p_149660_5_, p_149660_6_, p_149660_7_, p_149660_8_, p_149660_9_);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int rand)
	{
		return new TileEntityLightPanel();
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
