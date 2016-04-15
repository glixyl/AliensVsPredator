package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.energy.IVoltageReceiver;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntitySatelliteModem extends TileEntityElectrical implements IVoltageReceiver
{
	public TileEntitySatelliteModem()
	{
		super(false);
	}

	public int voltage;

	@Override
	public boolean canConnectPower(ForgeDirection from)
	{
		return false;
	}

	@Override
	public double receiveVoltage(ForgeDirection from, double maxReceive, boolean simulate)
	{
		return maxReceive;
	}

	@Override
	public double getCurrentVoltage(ForgeDirection from)
	{
		return this.voltage;
	}

	@Override
	public double getMaxVoltage(ForgeDirection from)
	{
		return 10000;
	}
}
