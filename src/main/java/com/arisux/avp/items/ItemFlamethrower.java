package com.arisux.avp.items;

import java.util.List;

import com.arisux.airi.lib.ItemTypes.HookedItem;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityFlame;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ItemFlamethrower extends HookedItem
{
	public ItemFlamethrower()
	{
		super();
		this.maxStackSize = 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World worldObj, EntityPlayer entityPlayer)
	{
		if (!worldObj.isRemote)
		{
			EntityFlame entity = new EntityFlame(worldObj, entityPlayer);
			entity.setLocationAndAngles(entity.posX, entity.posY - 0.35, entity.posZ, entity.rotationYaw, entity.rotationPitch);
			worldObj.spawnEntityInWorld(entity);
			worldObj.playSoundEffect(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, AliensVsPredator.properties().SOUND_WEAPON_FLAMETHROWER, 0.5F, 0.5F);
		}
		
		return itemstack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("all")
	public void addInformation(ItemStack itemstack, EntityPlayer entityPlayer, List tooltipList, boolean par4)
	{
		super.addInformation(itemstack, entityPlayer, tooltipList, par4);
		tooltipList.add("Left click to aim. Right click to use.");
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
	{
		return true;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int posX, int posY, int posZ, EntityPlayer player)
	{
		return false;
	}
}
