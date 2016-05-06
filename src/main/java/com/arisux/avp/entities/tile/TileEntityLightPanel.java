package com.arisux.avp.entities.tile;

import com.arisux.avp.util.IVoltageReceiver;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityLightPanel extends TileEntityElectrical implements IVoltageReceiver
{
    public TileEntityLightPanel()
    {
        super(false);
    }

    @Override
    public boolean canConnectPower(ForgeDirection from)
    {
        return true;
    }

    @Override
    public double receiveVoltage(ForgeDirection from, double maxReceive, boolean simulate)
    {
        return 0;
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
