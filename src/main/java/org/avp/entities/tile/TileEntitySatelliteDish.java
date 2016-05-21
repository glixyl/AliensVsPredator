package org.avp.entities.tile;

import org.avp.util.IVoltageReceiver;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntitySatelliteDish extends TileEntityElectrical implements IVoltageReceiver
{
    public TileEntitySatelliteDish()
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
