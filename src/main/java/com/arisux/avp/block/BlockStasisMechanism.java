package com.arisux.avp.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.arisux.airi.engine.BlockTypeLib.HookedBlockContainer;
import com.arisux.airi.engine.WorldEngine;
import com.arisux.avp.entities.tile.TileEntityStasisMechanism;
import com.arisux.avp.items.ItemEntitySummoner;

public class BlockStasisMechanism extends HookedBlockContainer
{
	public BlockStasisMechanism(Material material)
	{
		super(material);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		this.setTickRandomly(true);
	}

	@Override
	public void updateTick(World worldObj, int xCoord, int yCoord, int zCoord, Random par5Random)
	{
		super.updateTick(worldObj, xCoord, yCoord, zCoord, par5Random);
	}

	@Override
	public void registerBlockIcons(IIconRegister reg)
	{
		;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityStasisMechanism();
	}

	@Override
	public boolean onBlockActivated(World worldObj, int xCoord, int yCoord, int zCoord, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		TileEntityStasisMechanism tile = (TileEntityStasisMechanism) worldObj.getTileEntity(xCoord, yCoord, zCoord);

		if (tile != null)
		{
			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemEntitySummoner)
			{
				ItemEntitySummoner item = (ItemEntitySummoner) player.getCurrentEquippedItem().getItem();

				tile.stasisItemstack = new ItemStack(item, 1);
				tile.stasisEntity = item.createNewEntity(worldObj);
				WorldEngine.Entities.Players.Inventories.consumeItem(player, item);
			}
			else if (player.getCurrentEquippedItem() == null)
			{
				player.inventory.addItemStackToInventory(tile.stasisItemstack);
				tile.stasisEntity = null;
				tile.stasisItemstack = null;
			}
		}

		return super.onBlockActivated(worldObj, xCoord, yCoord, zCoord, player, side, hitX, hitY, hitZ);
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int posX, int posY, int posZ, int meta)
	{
		super.onBlockDestroyedByPlayer(world, posX, posY, posZ, meta);

		TileEntityStasisMechanism tile = (TileEntityStasisMechanism) world.getTileEntity(posX, posY, posZ);

		if (tile != null && tile.stasisItemstack != null)
		{
			EntityItem entityitem = new EntityItem(world, posX, posY, posZ, tile.stasisItemstack);
			entityitem.delayBeforeCanPickup = 10;
			world.spawnEntityInWorld(entityitem);
		}
	}

	@Override
	public void onBlockClicked(World world, int posX, int posY, int posZ, EntityPlayer player)
	{
		;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
	{
		super.onBlockPlacedBy(world, x, y, z, living, stack);

		TileEntityStasisMechanism tile = (TileEntityStasisMechanism) world.getTileEntity(x, y, z);
		
		if (tile != null)
		{
			tile.setDirection((byte) (MathHelper.floor_double(((living.rotationYaw * 4F) / 360F) + 0.5D) & 3));
			world.markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
		}
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}
}
