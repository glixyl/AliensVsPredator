package com.arisux.avp.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockTypes.HookedBlockContainer;
import com.arisux.avp.entities.tile.PoweredTileEntity;
import com.arisux.avp.entities.tile.TileEntityPowerline;
import com.arisux.avp.entities.tile.TileEntityRepulsionGenerator;
import com.arisux.avp.entities.tile.TileEntitySolarPanel;

public class BlockSolarPanel extends HookedBlockContainer
{
	public BlockSolarPanel(Material material)
	{
		super(material);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
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
		return new TileEntitySolarPanel();
	}
	
	@Override
	public void breakBlock(World worldIn, int posX, int posY, int posZ, Block blockBroken, int meta)
	{
		if(worldIn.getTileEntity(posX, posY, posZ) != null && worldIn.getTileEntity(posX, posY, posZ) instanceof TileEntityPowerline)
		{
			TileEntityPowerline te = (TileEntityPowerline) worldIn.getTileEntity(posX, posY, posZ);
			for(PoweredTileEntity tep : te.parents)
			{
				tep.children.remove(te);
			}
			for(PoweredTileEntity tep : te.children)
			{
				tep.parents.remove(te);
				tep.updateState();
			}
		}	
		super.breakBlock(worldIn, posX, posY, posZ, blockBroken, meta);
	}

	@Override
	public boolean onBlockActivated(World worldObj, int xCoord, int yCoord, int zCoord, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		return true;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
}