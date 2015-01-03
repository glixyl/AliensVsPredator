package com.arisux.avp.interfaces;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;

public class NetworkHolder
{
	private TileEntity controller;
	private List<TileEntity> networkBlocks;

	public NetworkHolder(TileEntity c)
	{
		this.controller = c;
		this.networkBlocks = new ArrayList<TileEntity>();
	}

	public boolean isRegistered(TileEntity b)
	{
		for (TileEntity i : this.networkBlocks)
		{
			if (i.xCoord == b.xCoord)
			{
				if (i.yCoord == b.yCoord)
				{
					if (i.zCoord == b.zCoord)
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	public void register(TileEntity b)
	{
		if (!isRegistered(b))
		{
			this.networkBlocks.add(b);
		}
	}
	
	public TileEntity getController()
	{
		return controller;
	}
}
