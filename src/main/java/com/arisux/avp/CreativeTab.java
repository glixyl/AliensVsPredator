package com.arisux.avp;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTab extends CreativeTabs
{
	public static CreativeTab instance = new CreativeTab();
	
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
		return (new Random()).nextInt(2) == 1 ? Item.getItemFromBlock((Block) AliensVsPredator.instance().blocks.getHandledObjects().get(new Random().nextInt(AliensVsPredator.instance().blocks.getHandledObjects().size()))) : (Item) AliensVsPredator.instance().items.getHandledObjects().get(new Random().nextInt(AliensVsPredator.instance().items.getHandledObjects().size()));
	}
}