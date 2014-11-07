package com.arisux.avp.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import com.arisux.avp.AliensVsPredator;

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
				return AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_TITANIUM1;
			case 1:
				return AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_TITANIUM1;
			case 2:
				return AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_TITANIUM2;
			case 3:
				return AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_TITANIUM2;
			default:
				return AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_TITANIUM1;
		}
	}
}
