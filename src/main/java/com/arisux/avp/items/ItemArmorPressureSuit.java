package com.arisux.avp.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import com.arisux.avp.AliensVsPredator;

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
				return AliensVsPredator.properties().TEXTURE_PATH_PRESSURESUIT1;
			case 1:
				return AliensVsPredator.properties().TEXTURE_PATH_PRESSURESUIT1;
			case 2:
				return AliensVsPredator.properties().TEXTURE_PATH_PRESSURESUIT2;
			case 3:
				return AliensVsPredator.properties().TEXTURE_PATH_PRESSURESUIT2;
			default:
				return AliensVsPredator.properties().TEXTURE_PATH_PRESSURESUIT2;
		}
	}
}