package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.energy.IVoltageProvider;
import com.arisux.avp.interfaces.energy.IVoltageReceiver;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPowerline extends TileEntityElectrical implements IVoltageProvider, IVoltageReceiver
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
    public boolean canConnectPower(ForgeDirection from)
    {
        return true;
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
