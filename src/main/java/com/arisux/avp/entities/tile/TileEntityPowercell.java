package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.energy.IEnergyReceiver;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPowercell extends TileEntityElectrical implements IEnergyReceiver
{
	public TileEntityPowercell()
	{
		super(true);
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return false;
	}

	@Override
	public double receiveEnergy(ForgeDirection from, double maxReceive, boolean simulate)
	{
		return maxReceive;
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