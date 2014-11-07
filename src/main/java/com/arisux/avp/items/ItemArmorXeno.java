package com.arisux.avp.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.model.ModelDrone;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArmorXeno extends ItemArmor
{
	@SideOnly(Side.CLIENT)
	public ModelDrone mainModel;

	public ItemArmorXeno(ArmorMaterial material, int renderIndex, int armorType)
	{
		super(material, renderIndex, armorType);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (isPlayerWearingXenoArmorSet((EntityPlayer) entity))
		{
			return AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_DRONE_ADVANCED;
		}

		switch (slot)
		{
			case 0:
				return AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_XENO1;
			case 1:
				return AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_XENO1;
			default:
				return AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_XENO2;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
	{
		if (isPlayerWearingXenoArmorSet((EntityPlayer) entityLiving))
		{
			return this.mainModel == null ? this.mainModel = new ModelDrone() : this.mainModel;
		}

		return null;
	}

	public static boolean isPlayerWearingXenoArmorSet(EntityPlayer player)
	{
		if (player != null)
		{
			ItemStack helm = player.inventory.armorItemInSlot(3), chest = player.inventory.armorItemInSlot(2), legs = player.inventory.armorItemInSlot(1), boots = player.inventory.armorItemInSlot(0);
			return (helm != null && chest != null && legs != null && boots != null && (helm.getItem() == AliensVsPredator.INSTANCE.items.helmXeno && chest.getItem() == AliensVsPredator.INSTANCE.items.plateXeno && legs.getItem() == AliensVsPredator.INSTANCE.items.legsXeno && boots.getItem() == AliensVsPredator.INSTANCE.items.bootsXeno));
		}

		return false;
	}
}