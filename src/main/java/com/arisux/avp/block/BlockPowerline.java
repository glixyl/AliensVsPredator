package com.arisux.avp.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;

import com.arisux.airi.lib.BlockTypes.HookedBlockContainer;
import com.arisux.avp.entities.tile.TileEntityPowerline;
import com.arisux.avp.entities.tile.TileEntityR2PConvertor;
import com.arisux.avp.entities.tile.TileEntityRepulsionGenerator;
import com.google.common.collect.Lists;

public class BlockPowerline extends HookedBlockContainer
{
	TileEntityPowerline pte;
	public BlockPowerline(Material material)
	{
		super(material);
		this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityPowerline();
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
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
					TileEntityPowerline tep = (TileEntityPowerline) te;
					if(tep.d.contains(direction.getOpposite()))
					{
						tep.d.remove(direction.getOpposite());
					}
				}
			}
		}
		super.breakBlock(world, posX, posY, posZ, blockBroken, meta);
	}
}