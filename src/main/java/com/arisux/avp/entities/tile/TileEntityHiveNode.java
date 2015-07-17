package com.arisux.avp.entities.tile;

import java.util.UUID;

import com.arisux.avp.interfaces.IHiveSignature;

import net.minecraft.tileentity.TileEntity;

public class TileEntityHiveNode extends TileEntity implements IHiveSignature
{
	private UUID signature;
	
	@Override
	public void updateEntity()
	{
		;
	}

	@Override
	public UUID getHiveSignature()
	{
		return this.signature;
	}

	@Override
	public void setHiveSignature(UUID signature)
	{
		this.signature = signature;
	}
}
