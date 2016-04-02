package com.arisux.avp.block;

import java.util.Random;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.entities.tile.TileEntityPowerline;
import com.arisux.avp.entities.tile.TileEntityRepulsionGenerator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGenerator extends HookedBlock
{
	public BlockGenerator(Material material)
	{
		super(material);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		this.setTickRandomly(true);
		this.setRenderNormal(false);
		this.setOpaque(false);
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
	public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
	{
		for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
		{
			TileEntity te = world.getTileEntity(posX + direction.offsetX, posY + direction.offsetY, posZ + direction.offsetZ);
			if(te != null)
			{
				if(te instanceof TileEntityPowerline)
				{
//					TileEntityPowerline tep = (TileEntityPowerline) te;
//					if(tep.d.contains(direction.getOpposite()))
//					{
//						tep.d.remove(direction.getOpposite());
//					}
				}
			}
		}
		super.breakBlock(world, posX, posY, posZ, blockBroken, meta);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityRepulsionGenerator();
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