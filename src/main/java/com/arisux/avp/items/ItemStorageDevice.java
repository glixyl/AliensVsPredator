package com.arisux.avp.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import com.arisux.airi.lib.ItemTypeLib.HookedItem;

public class ItemStorageDevice extends HookedItem
{
	private NBTTagList storedNBTTags = new NBTTagList();

	public ItemStorageDevice()
	{
		;
	}

	public NBTTagList getStoredNBTTags()
	{
		return storedNBTTags;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
	}
}
