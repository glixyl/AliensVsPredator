package com.arisux.avp;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.arisux.airi.lib.util.interfaces.IInitializable;

import cpw.mods.fml.common.event.FMLInitializationEvent;

public class CreativeTab extends CreativeTabs implements IInitializable
{
	public CreativeTab()
	{
		super(AliensVsPredator.properties().getName());
	}

	@Override
	public String getTranslatedTabLabel()
	{
		return AliensVsPredator.properties().getName();
	}

	@Override
	public Item getTabIconItem()
	{
		return (new Random()).nextInt(2) == 1 ? Item.getItemFromBlock((Block) AliensVsPredator.instance.blocks.getHandledObjects().get(new Random().nextInt(AliensVsPredator.instance.blocks.getHandledObjects().size()))) : (Item) AliensVsPredator.instance.items.getHandledObjects().get(new Random().nextInt(AliensVsPredator.instance.items.getHandledObjects().size()));
	}

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		;
	}
}