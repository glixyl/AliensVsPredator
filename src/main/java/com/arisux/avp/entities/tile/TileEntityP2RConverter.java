package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.energy.IVoltageReceiver;

import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityP2RConverter extends TileEntityElectrical implements IVoltageReceiver
{
    public TileEntityP2RConverter()
    {
        super(false);
    }

    public int rotation;

    public void setDirection(byte direction)
    {
        this.rotation = direction;
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        this.updateEnergyAsReceiver();

        World world = this.getWorldObj();
        int x = this.xCoord;
        int y = this.yCoord;
        int z = this.zCoord;

        if (world.getBlock(x + 1, y, z) instanceof BlockRedstoneWire)
        {
            if (world.getBlockMetadata(x + 1, y, z) == 0 && this.voltage >= 24)
            {
                world.setBlockMetadataWithNotify(x + 1, y, z, 15, 1);
            }
            if (world.getBlockMetadata(x + 1, y, z) == 15 && this.voltage == 0)
            {
                world.setBlockMetadataWithNotify(x + 1, y, z, 0, 1);
            }
        }

        if (world.getBlock(x, y + 1, z) instanceof BlockRedstoneWire)
        {
            if (world.getBlockMetadata(x, y + 1, z) == 0 && this.voltage >= 24)
            {
                world.setBlockMetadataWithNotify(x, y + 1, z, 15, 1);
            }
            if (world.getBlockMetadata(x, y + 1, z) == 15 && this.voltage == 0)
            {
                world.setBlockMetadataWithNotify(x, y + 1, z, 0, 1);
            }
        }

        if (world.getBlock(x, y, z + 1) instanceof BlockRedstoneWire)
        {
            if (world.getBlockMetadata(x, y, z + 1) == 0 && this.voltage >= 24)
            {
                world.setBlockMetadataWithNotify(x, y, z + 1, 15, 1);
            }
            if (world.getBlockMetadata(x, y, z + 1) == 15 && this.voltage == 0)
            {
                world.setBlockMetadataWithNotify(x, y, z + 1, 0, 1);
            }
        }

        if (world.getBlock(x - 1, y, z) instanceof BlockRedstoneWire)
        {
            if (world.getBlockMetadata(x - 1, y, z) == 0 && this.voltage >= 24)
            {
                world.setBlockMetadataWithNotify(x - 1, y, z, 15, 1);
            }
            if (world.getBlockMetadata(x - 1, y, z) == 15 && this.voltage == 0)
            {
                world.setBlockMetadataWithNotify(x - 1, y, z, 0, 1);
            }
        }

        if (world.getBlock(x, y - 1, z) instanceof BlockRedstoneWire)
        {
            if (world.getBlockMetadata(x, y - 1, z) == 0 && this.voltage >= 24)
            {
                world.setBlockMetadataWithNotify(x, y - 1, z, 15, 1);
            }
            if (world.getBlockMetadata(x, y - 1, z) == 15 && this.voltage == 0)
            {
                world.setBlockMetadataWithNotify(x, y - 1, z, 0, 1);
            }
        }

        if (world.getBlock(x, y, z - 1) instanceof BlockRedstoneWire)
        {
            if (world.getBlockMetadata(x, y, z - 1) == 0 && this.voltage >= 24)
            {
                world.setBlockMetadataWithNotify(x, y, z - 1, 15, 1);
            }
            if (world.getBlockMetadata(x, y, z - 1) == 15 && this.voltage == 0)
            {
                world.setBlockMetadataWithNotify(x, y, z - 1, 0, 1);
            }
        }
    }

    @Override
    public boolean canConnectPower(ForgeDirection from)
    {
        return false;
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
