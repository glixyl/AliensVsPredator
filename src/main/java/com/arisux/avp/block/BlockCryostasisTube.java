package com.arisux.avp.block;

import java.util.Random;

import com.arisux.airi.lib.BlockTypes.HookedBlockContainer;
import com.arisux.airi.lib.WorldUtil;
import com.arisux.avp.entities.tile.TileEntityCryostasisTube;
import com.arisux.avp.items.ItemEntitySummoner;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCryostasisTube extends HookedBlockContainer
{
	public BlockCryostasisTube(Material material)
	{
		super(material);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2F, 1.0F);
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
		return new TileEntityCryostasisTube();
	}

	@Override
	public boolean onBlockActivated(World worldObj, int xCoord, int yCoord, int zCoord, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof ItemEntitySummoner)
		{
			TileEntityCryostasisTube tile = (TileEntityCryostasisTube) worldObj.getTileEntity(xCoord, yCoord, zCoord);

			if (tile != null)
			{
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemEntitySummoner)
				{
					ItemEntitySummoner item = (ItemEntitySummoner) player.getCurrentEquippedItem().getItem();
					tile.stasisItemstack = new ItemStack(item, 1);
					tile.stasisEntity = item.createNewEntity(worldObj);
					WorldUtil.Entities.Players.Inventories.consumeItem(player, item);
				}
				else if (player.getCurrentEquippedItem() == null)
				{
					player.inventory.addItemStackToInventory(tile.stasisItemstack);
					tile.stasisEntity = null;
					tile.stasisItemstack = null;
				}
			}

			return true;
		}
		
		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
	{
		super.onBlockPlacedBy(world, x, y, z, living, stack);

		TileEntityCryostasisTube tile = (TileEntityCryostasisTube) world.getTileEntity(x, y, z);

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
