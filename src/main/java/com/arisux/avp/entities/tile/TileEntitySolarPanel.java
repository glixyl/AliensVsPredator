package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.energy.IVoltageProvider;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntitySolarPanel extends TileEntityElectrical implements IVoltageProvider
{
	public TileEntitySolarPanel()
	{
		super(true);
	}

	@Override
	public void updateEntity()
	{
		if (this.worldObj.getWorldTime() < 12300 || this.worldObj.getWorldTime() > 23850)
		{
			if (this.getWorldObj().getWorldTime() % (1000 / this.getSourceHertz()) == 0)
			{
				this.setVoltage(120);
			}
		}
		else
		{
			this.setVoltage(0);
		}
	}

	@Override
	public boolean canConnectPower(ForgeDirection from)
	{
		return true;
	}
	
	@Override
	public double extractVoltage(ForgeDirection from, double maxExtract, boolean simulate)
	{
		return super.extractVoltage(from, maxExtract, simulate);
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
