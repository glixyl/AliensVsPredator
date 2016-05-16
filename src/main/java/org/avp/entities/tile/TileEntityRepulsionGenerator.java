package org.avp.entities.tile;

import org.avp.util.IVoltageProvider;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRepulsionGenerator extends TileEntityElectrical implements IVoltageProvider
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
