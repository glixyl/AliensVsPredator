package com.arisux.avp.entities.tile;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.packets.client.PacketOpenable;
import com.arisux.avp.util.IOpenable;
import com.arisux.avp.util.IRotatable;
import com.arisux.avp.util.IVoltageReceiver;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityMedpod extends TileEntityElectrical implements IOpenable, IVoltageReceiver, IRotatable
{
    private ForgeDirection direction;
    private boolean isOpen;

    public TileEntityMedpod()
    {
        super(false);
        this.isOpen = false;
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.func_148857_g());
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        if (this.direction != null)
        {
            nbt.setInteger("Direction", this.direction.ordinal());
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        if (ForgeDirection.getOrientation(nbt.getInteger("Direction")) != null)
        {
            this.direction = ForgeDirection.getOrientation(nbt.getInteger("Direction"));
        }
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        this.updateEnergyAsReceiver();
    }

    @Override
    public ForgeDirection getDirection()
    {
        return direction;
    }

    @Override
    public void setDirection(ForgeDirection direction)
    {
        this.direction = direction;
    }

    @Override
    public void setOpen(boolean isOpen)
    {
        this.isOpen = isOpen;

        if (!this.worldObj.isRemote)
        {
            AliensVsPredator.network().sendToAll(new PacketOpenable(isOpen, this.xCoord, this.yCoord, this.zCoord));
        }
    }

    @Override
    public boolean isOpen()
    {
        return isOpen;
    }

    @Override
    public double getCurrentVoltage(ForgeDirection from)
    {
        return this.voltage;
    }

    @Override
    public double getMaxVoltage(ForgeDirection from)
    {
        return 240;
    }

    @Override
    public boolean canConnectPower(ForgeDirection from)
    {
        return true;
    }
}
