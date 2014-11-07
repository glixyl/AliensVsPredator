package com.arisux.avp.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.arisux.airi.lib.ItemTypeLib.HookedItem;
import com.arisux.airi.lib.PlayerLib;
import com.arisux.avp.entities.EntityGrenade;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGrenade extends HookedItem
{
	private boolean isFlaming;

	public ItemGrenade()
	{
		super();
		this.maxStackSize = 16;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World worldObj, EntityPlayer entityplayer)
	{
		if (!worldObj.isRemote)
		{
			EntityGrenade grenade = new EntityGrenade(worldObj, entityplayer);
			grenade.isFlaming = isFlaming;
			worldObj.spawnEntityInWorld(grenade);
			PlayerLib.consumeItem(entityplayer, this);
		}
		
		return itemstack;
	}

	public Item setFlaming(boolean isFlaming)
	{
		this.isFlaming = isFlaming;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("Right click to throw (explodes)");
	}
}
