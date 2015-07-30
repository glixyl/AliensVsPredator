package com.arisux.avp.entities.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityNegativeTransformer extends TileEntityTransformer
{
	public TileEntityNegativeTransformer()
	{
		this.boost = -24;
	}
}
