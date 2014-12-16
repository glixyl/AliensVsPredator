package com.arisux.avp.util;

import com.arisux.airi.lib.client.ModelTexMap;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.items.ItemFirearm;
import com.arisux.avp.items.render.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public enum MarineTypes
{
	M4(0, (ItemFirearm) AliensVsPredator.instance().items.itemM4),
	AK47(1, (ItemFirearm) AliensVsPredator.instance().items.itemAK47),
	M41A(2, (ItemFirearm) AliensVsPredator.instance().items.itemM41A),
	SNIPER(3, (ItemFirearm) AliensVsPredator.instance().items.itemSniper),
	M56SG(4, (ItemFirearm) AliensVsPredator.instance().items.itemM56SG);

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
		return itemFirearm.getGunfireSound();
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
				return new ModelTexMap(RenderM4.model, RenderM4.resourceLocation);
			case AK47:
				return new ModelTexMap(RenderAK47.model, RenderAK47.resourceLocation);
			case M41A:
				return new ModelTexMap(RenderM41A.model, RenderM41A.resourceLocation);
			case SNIPER:
				return new ModelTexMap(RenderSniper.model, RenderSniper.resourceLocation);
			case M56SG:
				return new ModelTexMap(RenderM56SG.model, RenderM56SG.resourceLocation);
		}
		
		return null;
	}
}