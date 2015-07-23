package com.arisux.avp.entities.tile;

import java.util.HashSet;
import java.util.Set;

import com.arisux.avp.interfaces.energy.IEnergyProvider;
import com.arisux.avp.interfaces.energy.IEnergyReceiver;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityTransformer extends TileEntityElectrical implements IEnergyProvider, IEnergyReceiver
{
	public TileEntityTransformer()
	{
		super(false);
	}

	public boolean turnOff = true;
	public Set<ForgeDirection> d = new HashSet<ForgeDirection>();

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
		return maxReceive;
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
