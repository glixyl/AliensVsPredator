package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.energy.IEnergyProvider;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRepulsionGenerator extends TileEntityElectrical implements IEnergyProvider
{
	public TileEntityRepulsionGenerator()
	{
		super(true);
	}

	public int rotation;

	@Override
	public void updateEntity()
	{
		if (this.getWorldObj().getWorldTime() % (1000 / this.getSourceHertz()) == 0)
		{
			this.setVoltage(220);
		}
	}

	public void setDirection(byte direction)
	{
		this.rotation = direction;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public double extractEnergy(ForgeDirection from, double maxExtract, boolean simulate)
	{
		return super.extractEnergy(from, maxExtract, simulate);
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
