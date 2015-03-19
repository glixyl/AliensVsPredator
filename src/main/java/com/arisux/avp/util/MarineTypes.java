package com.arisux.avp.util;

import com.arisux.airi.lib.client.ModelTexMap;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.ItemFirearm;
import com.arisux.avp.items.render.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public enum MarineTypes
{
	M4(0, (ItemFirearm) AliensVsPredator.items().itemM4),
	AK47(1, (ItemFirearm) AliensVsPredator.items().itemAK47),
	M41A(2, (ItemFirearm) AliensVsPredator.items().itemM41A),
	SNIPER(3, (ItemFirearm) AliensVsPredator.items().itemSniper),
	M56SG(4, (ItemFirearm) AliensVsPredator.items().itemM56SG);

	private int id;
	private ItemFirearm itemFirearm;

	private MarineTypes(int id, ItemFirearm itemFirearm)
	{
		this.id = id;
		this.itemFirearm = itemFirearm;
	}

	public int getValue()
	{
		return id;
	}

	public String getGunfireSound()
	{
		return itemFirearm.getFireSound();
	}

	public ItemFirearm getFirearmItem()
	{
		return itemFirearm;
	}
	
	public static MarineTypes getTypeForId(int id)
	{
		for (MarineTypes type : values())
		{
			if (type.id == id)
			{
				return type;
			}
		}
		
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public ModelTexMap getFirearmModelTexMap()
	{
		switch (this)
		{
			case M4:
				return new ModelTexMap(RenderItemM4.model, RenderItemM4.resourceLocation);
			case AK47:
				return new ModelTexMap(RenderItemAK47.model, RenderItemAK47.resourceLocation);
			case M41A:
				return new ModelTexMap(RenderItemM41A.model, RenderItemM41A.resourceLocation);
			case SNIPER:
				return new ModelTexMap(RenderItemSniper.model, RenderItemSniper.resourceLocation);
			case M56SG:
				return new ModelTexMap(RenderItemM56SG.model, RenderItemM56SG.resourceLocation);
		}
		
		return null;
	}
}