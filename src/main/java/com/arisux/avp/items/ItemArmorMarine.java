package com.arisux.avp.items;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemArmorMarine extends ItemArmor
{
	public ItemArmorMarine(ArmorMaterial material, int renderIndex, int armorType)
	{
		super(material, renderIndex, armorType);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		switch (slot)
		{
			case 0:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().MARINE1);
			case 1:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().MARINE1);
			case 2:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().MARINE2);
			case 3:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().MARINE1);
			default:
				return RenderUtil.getResourcePath(AliensVsPredator.resources().MARINE1);
		}
	}
}
