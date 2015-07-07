package com.arisux.avp.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockTypes.HookedBlockContainer;
import com.arisux.avp.entities.tile.PoweredTileEntity;
import com.arisux.avp.entities.tile.TileEntityPowerline;
import com.arisux.avp.entities.tile.TileEntityR2PConvertor;
import com.google.common.collect.Lists;

public class BlockPowerline extends HookedBlockContainer
{
	TileEntityPowerline pte;
	public BlockPowerline(Material material)
	{
		super(material);
		this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		this.setTickRandomly(true);
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
	public void onBlockPlacedBy(World worldIn, int xPos, int yPos, int zPos, EntityLivingBase entityIn, ItemStack itemStackIn)
	{
		if(worldIn.getTileEntity(xPos, yPos, zPos) != null && worldIn.getTileEntity(xPos, yPos, zPos) instanceof TileEntityPowerline)
		{
			TileEntityPowerline te = (TileEntityPowerline) worldIn.getTileEntity(xPos, yPos, zPos);
			te.updateState();
		}
	}

//	public int onBlockPlaced(World worldIn, int xPos, int yPos, int zPos, int side, float hitX, float hitY, float hitZ, int metadata)
//    {
//		if(worldIn.getTileEntity(xPos, yPos, zPos) != null && worldIn.getTileEntity(xPos, yPos, zPos) instanceof TileEntityPowerline)
//		{
//			System.out.println("dis far");
//			TileEntityPowerline te = (TileEntityPowerline) worldIn.getTileEntity(xPos, yPos, zPos);
//			te.updateState();
//		}	
//        return metadata;
//    }
	
	@Override
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
    {
		
    }
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityPowerline();
	}
	
	@Override
	public void breakBlock(World worldIn, int posX, int posY, int posZ, Block blockBroken, int meta)
	{
		if(worldIn.getTileEntity(posX, posY, posZ) != null && worldIn.getTileEntity(posX, posY, posZ) instanceof TileEntityPowerline)
		{
			TileEntityPowerline te = (TileEntityPowerline) worldIn.getTileEntity(posX, posY, posZ);
			System.out.println("Parents: " + te.parents.size() + " Children: " + te.children.size());
			List<PoweredTileEntity> l = Lists.newArrayList(te.parents);
			for(PoweredTileEntity tep : l)
			{
				tep.children.remove(te);
			}
			List<PoweredTileEntity> list = Lists.newArrayList(te.children);
			for(PoweredTileEntity tep : list)
			{
				tep.parents.remove(te);
				tep.updateState();
			}
		}	
		super.breakBlock(worldIn, posX, posY, posZ, blockBroken, meta);
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
}