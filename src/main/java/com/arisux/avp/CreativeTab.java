package com.arisux.avp;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTab extends CreativeTabs
{
	public static CreativeTab tabMain = new CreativeTab();
	public static CreativeTab tabBlocks = new CreativeTab();
	
	public CreativeTab()
	{
		super(AliensVsPredator.instance().container().getName());
	}

	@Override
	public String getTranslatedTabLabel()
	{
		return AliensVsPredator.instance().container().getName();
	}

	@Override
	public Item getTabIconItem()
	{
		return AliensVsPredator.items().helmTitanium;
	}
}