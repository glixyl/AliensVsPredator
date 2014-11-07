package com.arisux.avp.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.arisux.airi.lib.ItemTypeLib.HookedItem;
import com.arisux.airi.lib.PlayerLib;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntitySmartDisc;

public class ItemDisc extends HookedItem
{
	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int itemInUseCount)
	{
		if (entityplayer.inventory.hasItemStack(new ItemStack(AliensVsPredator.INSTANCE.items.itemDisc)))
		{
			int remainingCount = this.getMaxItemUseDuration(itemstack) - itemInUseCount;
			float f = (float) remainingCount / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if (f >= 0.1F)
			{
				boolean crit = f > 1.5F ? true : false;
				f = f > 1.5F ? 1.5F : f;
				f *= 1.5F;

				if (!world.isRemote)
				{
					world.spawnEntityInWorld((new EntitySmartDisc(world, entityplayer, itemstack, f)).setCritical(crit));
				}

				world.playSoundAtEntity(entityplayer, "random.bow", 0.6F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.0F));
				PlayerLib.consumeItem(entityplayer, this);
			}
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack)
	{
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack)
	{
		return EnumAction.block;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if (entityplayer.inventory.hasItemStack(new ItemStack(AliensVsPredator.INSTANCE.items.itemDisc)))
		{
			entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
		}

		return itemstack;
	}
}
