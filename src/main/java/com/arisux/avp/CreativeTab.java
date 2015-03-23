package com.arisux.avp;

import java.util.Random;

import net.minecraft.block.Block;
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
		return (new Random()).nextInt(2) == 1 ? Item.getItemFromBlock((Block) AliensVsPredator.blocks().getHandledObjects().get(new Random().nextInt(AliensVsPredator.blocks().getHandledObjects().size()))) : (Item) AliensVsPredator.items().getHandledObjects().get(new Random().nextInt(AliensVsPredator.items().getHandledObjects().size()));
	}
}