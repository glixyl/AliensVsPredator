package com.arisux.avp.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockTypeLib.HookedBlockContainer;
import com.arisux.avp.entities.tile.TileEntityAssembler;

public class BlockAssembler extends HookedBlockContainer
{
	public BlockAssembler(Material par3)
	{
		super(par3);
		this.setLightOpacity(2);
		float var5 = 0.38F;
		this.setBlockBounds(var5, 0.0F, var5, 1.0F - var5, 0.9F, 1.0F - var5);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.updateTick(par1World, par2, par3, par4, par5Random);
	}

	@Override
	public boolean onBlockActivated(World world, int xCoord, int yCoord, int zCoord, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			TileEntityAssembler tile = (TileEntityAssembler) world.getTileEntity(xCoord, yCoord, zCoord);

			if (tile != null)
			{
				tile.player = player;
				tile.openGui(player);
			}
		}

		return true;
	}

	@Override
	public void breakBlock(World world, int xCoord, int yCoord, int zCoord, Block block, int metadata)
	{
		TileEntityAssembler tile = (TileEntityAssembler) world.getTileEntity(xCoord, yCoord, zCoord);

		if (tile != null)
		{
			IInventory inv = (IInventory) tile;

			for (byte i = 0; i < inv.getSizeInventory(); i++)
			{
				ItemStack stack = inv.getStackInSlotOnClosing(i);

				if (stack != null)
				{
					EntityItem entity = new EntityItem(world, xCoord + world.rand.nextFloat(), yCoord + world.rand.nextFloat(), zCoord + world.rand.nextFloat(), stack);

					float mult = 0.05F;
					entity.motionX = (-0.5F + world.rand.nextFloat()) * mult;
					entity.motionY = (4F + world.rand.nextFloat()) * mult;
					entity.motionZ = (-0.5F + world.rand.nextFloat()) * mult;

					world.spawnEntityInWorld(entity);
				}
			}
		}

		super.breakBlock(world, xCoord, yCoord, zCoord, block, metadata);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityAssembler();
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		;
	}
}
