package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.energy.IEnergyReceiver;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityLightPanel extends TileEntityElectrical implements IEnergyReceiver
{
	public TileEntityLightPanel()
	{
		super(false);
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
