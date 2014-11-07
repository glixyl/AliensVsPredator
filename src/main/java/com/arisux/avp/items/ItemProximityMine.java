package com.arisux.avp.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.arisux.avp.entities.EntityProximityMine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemProximityMine extends Item
{
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ)
	{
		byte metaValue = (byte) (side == 4 ? 1 : side == 3 ? 2 : side == 5 ? 3 : 0);

		EntityProximityMine entity = new EntityProximityMine(world, posX, posY, posZ, metaValue, entityplayer.getUniqueID().toString());

		if (!world.isRemote && entity.canStay())
		{
			--itemstack.stackSize;
			world.spawnEntityInWorld(entity);
			return true;
		}

		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("Right click to place on wall (Explodes when hostiles pass)");
	}
}
