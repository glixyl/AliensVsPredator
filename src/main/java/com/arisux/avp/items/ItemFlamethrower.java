package com.arisux.avp.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.arisux.airi.engine.ItemTypeLib.HookedItem;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityFlame;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFlamethrower extends HookedItem
{
	public ItemFlamethrower()
	{
		super();
		this.setMaxDamage(100);
		this.maxStackSize = 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World worldObj, EntityPlayer entityPlayer)
	{
		worldObj.spawnEntityInWorld(new EntityFlame(worldObj, entityPlayer));
		itemstack.damageItem(1, entityPlayer);
		worldObj.playSoundEffect(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, AliensVsPredator.properties().SOUND_WEAPON_FLAMETHROWER, 0.5F, 0.5F);
		return itemstack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack itemstack, EntityPlayer entityPlayer, List tooltipList, boolean par4)
	{
		tooltipList.add("Right click to use.");
	}
}
