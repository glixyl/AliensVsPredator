package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.energy.IVoltageProvider;

import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityR2PConverter extends TileEntityElectrical implements IVoltageProvider
{
    public boolean isActiveRedstoneWireAttached;

    public TileEntityR2PConverter()
    {
        super(true);
    }

    @Override
    public void updateEntity()
    {
        if (this.canOutputPower())
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
        return false;
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

    public boolean canOutputPower()
    {
        World world = this.getWorldObj();
        int x = this.xCoord;
        int y = this.yCoord;
        int z = this.zCoord;

        if (world.getBlock(x + 1, y, z) instanceof BlockRedstoneWire)
        {
            this.isActiveRedstoneWireAttached = world.getBlockMetadata(x + 1, y, z) != 0;
        }

        else if (world.getBlock(x, y + 1, z) instanceof BlockRedstoneWire)
        {
            this.isActiveRedstoneWireAttached = world.getBlockMetadata(x, y + 1, z) != 0;
        }

        else if (world.getBlock(x, y, z + 1) instanceof BlockRedstoneWire)
        {
            this.isActiveRedstoneWireAttached = world.getBlockMetadata(x, y, z + 1) != 0;
        }

        else if (world.getBlock(x - 1, y, z) instanceof BlockRedstoneWire)
        {
            this.isActiveRedstoneWireAttached = world.getBlockMetadata(x - 1, y, z) != 0;
        }

        else if (world.getBlock(x, y - 1, z) instanceof BlockRedstoneWire)
        {
            this.isActiveRedstoneWireAttached = world.getBlockMetadata(x, y - 1, z) != 0;
        }

        else if (world.getBlock(x, y, z - 1) instanceof BlockRedstoneWire)
        {
            this.isActiveRedstoneWireAttached = world.getBlockMetadata(x, y, z - 1) != 0;
        }

        else
        {
            this.isActiveRedstoneWireAttached = false;
        }

        return this.isActiveRedstoneWireAttached;
    }
}
