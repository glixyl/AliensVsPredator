package org.avp.entities.tile;

import org.avp.util.IVoltageProvider;
import org.avp.util.IVoltageReceiver;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPowercell extends TileEntityElectrical implements IVoltageReceiver, IVoltageProvider
{
    public long energyStored;
    public double voltageCapacity;

    public TileEntityPowercell()
    {
        super(true);
    }

    @Override
    public boolean canConnectPower(ForgeDirection from)
    {
        return true;
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            TileEntity tile = this.worldObj.getTileEntity(this.xCoord + direction.offsetX, this.yCoord + direction.offsetY, this.zCoord + direction.offsetZ);

            if (tile != null && tile instanceof TileEntityElectrical)
            {
                TileEntityElectrical electrical = (TileEntityElectrical) tile;

                if (electrical.getVoltage() > this.voltageCapacity)
                {
                    this.voltageCapacity = electrical.getVoltage();
                }

                if (electrical instanceof IVoltageProvider)
                {
                    IVoltageProvider provider = (IVoltageProvider) electrical;

                    if (provider.getCurrentVoltage(direction.getOpposite()) > 0)
                    {
                        if (this.energyStored <= this.getMaxEnergyStored())
                        {
                            this.energyStored++;
                        }
                    }
                }
            }
        }

        if (this.energyStored > 0)
        {
            this.setVoltage(this.voltageCapacity);
            this.energyStored = this.energyStored - 2;
        }
        else
        {
            this.setVoltage(0);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        this.energyStored = nbt.getLong("energyStored");
        this.voltageCapacity = nbt.getDouble("voltageCapacity");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setLong("energyStored", this.energyStored);
        nbt.setDouble("voltageCapacity", this.voltageCapacity);
    }

    @Override
    public double receiveVoltage(ForgeDirection from, double maxReceive, boolean simulate)
    {
        return super.receiveVoltage(from, maxReceive, simulate);
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

    public long getEnergyStored()
    {
        return energyStored;
    }

    public long getMaxEnergyStored()
    {
        return 10000;
    }

    public double getVoltageCapacity()
    {
        return voltageCapacity;
    }
}
