package org.avp.items;

import org.avp.AliensVsPredator;
import org.avp.entities.mob.model.ModelDrone;

import com.arisux.airi.lib.RenderUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
		switch (slot)
		{
			case 0:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().XENO1);
			case 1:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().XENO1);
			case 2:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().XENO2);
			default:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().XENO1);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
	{
		return null;
	}

	public static boolean isPlayerWearingXenoArmorSet(EntityPlayer player)
	{
		if (player != null)
		{
			ItemStack helm = player.inventory.armorItemInSlot(3), chest = player.inventory.armorItemInSlot(2), legs = player.inventory.armorItemInSlot(1), boots = player.inventory.armorItemInSlot(0);
			return (helm != null && chest != null && legs != null && boots != null && (helm.getItem() == AliensVsPredator.items().helmXeno && chest.getItem() == AliensVsPredator.items().plateXeno && legs.getItem() == AliensVsPredator.items().legsXeno && boots.getItem() == AliensVsPredator.items().bootsXeno));
		}

		return false;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		// if you are wearing the set, fall damage is negated
		if (player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == AliensVsPredator.items().helmXeno && player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == AliensVsPredator.items().plateXeno && player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == AliensVsPredator.items().legsXeno && player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == AliensVsPredator.items().bootsXeno)
		{
			player.fallDistance = 0.0F;
			// if you also have empty hands, you can climb up vertical surfaces
			if (player.inventory.getCurrentItem() == null)
			{
				if (player.isCollidedHorizontally)
				{
					player.motionY += 0.03F;
				}
			}
		}
	}
}
