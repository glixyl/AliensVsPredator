package com.arisux.avp;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.arisux.airi.lib.interfaces.IInitializable;

public class CreativeTab extends CreativeTabs implements IInitializable
{
	public CreativeTab()
	{
		super(Properties.NAME);
	}

	@Override
	public String getTranslatedTabLabel()
	{
		return Properties.NAME;
	}

	@Override
	public Item getTabIconItem()
	{
		return (new Random()).nextInt(2) == 1 ? Item.getItemFromBlock((Block) AliensVsPredator.INSTANCE.blocks.getHandledObjects().get(new Random().nextInt(AliensVsPredator.INSTANCE.blocks.getHandledObjects().size()))) : (Item) AliensVsPredator.INSTANCE.items.getHandledObjects().get(new Random().nextInt(AliensVsPredator.INSTANCE.items.getHandledObjects().size()));
	}

	@Override
	public void initialize()
	{
		;
	}
}