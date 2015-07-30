package com.arisux.avp.entities.tile;

import java.util.ArrayList;

import com.arisux.avp.interfaces.energy.IEnergyProvider;
import com.arisux.avp.interfaces.energy.IEnergyReceiver;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityTransformer extends TileEntityElectrical implements IEnergyProvider, IEnergyReceiver
{	
	public TileEntityTransformer()
	{
		super(false);
		this.boost = 24;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		this.updateEnergyAsReceiver();
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return from == ForgeDirection.SOUTH;
	}

	@Override
	public double getEnergyStored(ForgeDirection from)
	{
		return this.voltage;
	}

	@Override
	public double extractEnergy(ForgeDirection from, double maxExtract, boolean simulate)
	{
		TileEntity tile = this.worldObj.getTileEntity(this.xCoord + from.offsetX, this.yCoord + from.offsetY, this.zCoord + from.offsetZ);

		if (tile != null && tile instanceof TileEntityElectrical)
		{
			return (maxExtract + this.getBoost()) - this.getResistance();
		}

		return 0;
	}

	@Override
	public boolean canProvideEnergyToReceiver(ForgeDirection side)
	{
		return side == ForgeDirection.SOUTH || side == ForgeDirection.NORTH;
	}

	@Override
	public double getMaxEnergyStored(ForgeDirection from)
	{
		return 10000;
	}
}
