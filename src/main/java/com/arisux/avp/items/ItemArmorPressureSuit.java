package com.arisux.avp.items;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemArmorPressureSuit extends ItemArmor
{
	public ItemArmorPressureSuit(ArmorMaterial material, int renderIndex, int armorType)
	{
		super(material, renderIndex, armorType);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
	{
		switch (slot)
		{
			case 0:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().PRESSURESUIT1);
			case 1:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().PRESSURESUIT1);
			case 2:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().PRESSURESUIT2);
			case 3:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().PRESSURESUIT2);
			default:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().PRESSURESUIT2);
		}
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
			// if you have the set, which is craftable without an assembler with industrial glass and aluminum, you can breath in water and gain night vision; which brightens water as well
			if (player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == AliensVsPredator.items().pressureMask
				&& player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == AliensVsPredator.items().pressureChest
				&& player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == AliensVsPredator.items().pressurePants
				&& player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == AliensVsPredator.items().pressureBoots)
			{
				player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(), 1, 1));
				player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 1, 1));
			}
	}
}