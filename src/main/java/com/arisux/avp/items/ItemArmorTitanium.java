package com.arisux.avp.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import com.arisux.airi.lib.RenderUtil;
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
}
