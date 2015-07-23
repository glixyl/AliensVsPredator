package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.energy.IEnergyProvider;
import com.arisux.avp.interfaces.energy.IEnergyReceiver;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityNegativeTransformer extends TileEntityElectrical implements IEnergyProvider, IEnergyReceiver
{
	public TileEntityNegativeTransformer()
	{
		super(false);
	}

	public boolean turnOff = true;

	@Override
	public void updateEntity()
	{
		if (turnOff)
		{
			this.voltage = 0;
		}
		turnOff = true;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public double receiveEnergy(ForgeDirection from, double maxReceive, boolean simulate)
	{
		return 0;
	}

	@Override
	public double extractEnergy(ForgeDirection from, double maxExtract, boolean simulate)
	{
		return 0;
	}

	@Override
	public double getEnergyStored(ForgeDirection from)
	{
		return this.voltage;
	}

	@Override
	public double getMaxEnergyStored(ForgeDirection from)
	{
		return 10000;
	}
}
