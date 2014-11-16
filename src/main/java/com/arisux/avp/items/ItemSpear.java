package com.arisux.avp.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.world.World;

import com.arisux.airi.engine.WorldEngine;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntitySpear;

public class ItemSpear extends ItemSword
{
	public ItemSpear(ToolMaterial material)
	{
		super(material);
		this.setMaxDamage(120);
		this.maxStackSize = 1;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int itemInUseCount)
	{
		if (entityplayer.inventory.hasItemStack(new ItemStack(AliensVsPredator.instance.items.itemSpear)))
		{
			int j = this.getMaxItemUseDuration(itemstack) - itemInUseCount;
			float f = j / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if (f < 0.1D)
			{
				return;
			}

			if (!world.isRemote)
			{
				EntitySpear entityspear = new EntitySpear(world, entityplayer, f * 2.0F, this.getDamage(itemstack));
				entityspear.arrowCritical = true;
				world.playSoundAtEntity(entityplayer, "random.pop", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
				world.spawnEntityInWorld(entityspear);

				WorldEngine.Entities.Players.Inventories.consumeItem(entityplayer, this);
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
		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if (entityplayer.inventory.hasItemStack(new ItemStack(AliensVsPredator.instance.items.itemSpear)))
		{
			entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
		}

		return itemstack;
	}
}
