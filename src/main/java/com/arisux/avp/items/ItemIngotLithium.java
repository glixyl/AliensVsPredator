package com.arisux.avp.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.arisux.airi.engine.ItemTypeLib.HookedItem;
import com.arisux.airi.engine.*;
import com.arisux.airi.engine.WorldEngine.Blocks;

public class ItemIngotLithium extends HookedItem
{
	public boolean isDepleting = true;
	public int depletionTicks = 20 * 60;

	public ItemIngotLithium()
	{
		this.setMaxDamage(20 * 60);
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int slot, boolean selected)
	{
		super.onUpdate(par1ItemStack, par2World, par3Entity, slot, selected);

		this.setDamage(par1ItemStack, par1ItemStack.getItemDamage() + 1);

		if (par1ItemStack.getItemDamage() >= par1ItemStack.getMaxDamage())
		{
			WorldEngine.createExplosion(par3Entity, par2World, new Blocks.CoordData(par3Entity), 1F, true, true, true);
			WorldEngine.Entities.Players.Inventories.consumeItem((EntityPlayer) par3Entity, this, true);
		}
	}
}
