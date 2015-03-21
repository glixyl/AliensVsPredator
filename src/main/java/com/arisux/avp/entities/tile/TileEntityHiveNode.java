package com.arisux.avp.entities.tile;

import java.util.UUID;

import net.minecraft.tileentity.TileEntity;

import com.arisux.avp.interfaces.IHiveSignature;

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
