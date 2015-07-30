package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.energy.IEnergyProvider;
import com.arisux.avp.interfaces.energy.IEnergyReceiver;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPowerline extends TileEntityElectrical implements IEnergyProvider, IEnergyReceiver
{
	public TileEntityPowerline()
	{
		super(false);
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
		return true;
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
