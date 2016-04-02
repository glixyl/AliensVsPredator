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

public class ItemArmorTitanium extends ItemArmor
{
	public ItemArmorTitanium(ArmorMaterial material, int renderIndex, int armorType)
	{
		super(material, renderIndex, armorType);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		switch (slot)
		{
			case 0:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().TITANIUM1);
			case 1:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().TITANIUM1);
			case 2:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().TITANIUM2);
			case 3:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().TITANIUM1);
			default:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().TITANIUM1);
		}
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
			// if you have the set, you get jump boost and fall damage is negated.  if you also have the wrist bracer, you get limited invisibility
			if (player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == AliensVsPredator.items().helmTitanium
				&& player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == AliensVsPredator.items().plateTitanium
				&& player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == AliensVsPredator.items().legsTitanium
				&& player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == AliensVsPredator.items().bootsTitanium)
			{
				player.fallDistance = 0.0F;
				player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 1, 1));
				if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == AliensVsPredator.items().itemWristBlade)
				{
					player.addPotionEffect(new PotionEffect(Potion.invisibility.getId(), 2, 0));
				}
			}
	}
	
}
