package com.arisux.avp.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.world.World;

import com.arisux.airi.lib.WorldUtil;
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
		if (entityplayer.inventory.hasItem(AliensVsPredator.instance().items.itemSpear))
		{
			float charge = (this.getMaxItemUseDuration(itemstack) - itemInUseCount * 1F) / 9F;
			float maxCharge = 3.5F;
			
			charge = charge >= maxCharge ? 3.5F : charge;

			if (charge < 0.1D)
			{
				return;
			}
			
			if (!world.isRemote)
			{
				System.out.println("PRE:" + itemstack);
				EntitySpear entityspear = new EntitySpear(world, entityplayer, itemstack);
				entityspear.setThrowableHeading(entityspear.motionX, entityspear.motionY, entityspear.motionZ, 0.8F * charge, 3.0F);
				world.playSoundAtEntity(entityplayer, "random.pop", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + charge * 0.5F);
				world.spawnEntityInWorld(entityspear);
				WorldUtil.Entities.Players.Inventories.consumeItem(entityplayer, AliensVsPredator.instance().items.itemSpear, true);
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
		if (entityplayer.inventory.hasItem(AliensVsPredator.instance().items.itemSpear))
		{
			entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
		}

		return itemstack;
	}
}
