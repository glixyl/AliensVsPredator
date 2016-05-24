package org.avp.entities.tile;

import org.avp.util.INetworkDevice;
import org.avp.util.IVoltageReceiver;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityWorkstation extends TileEntityElectrical implements INetworkDevice, IVoltageReceiver
{
    public int rotation;

    public TileEntityWorkstation()
    {
        super(false);
        this.setThresholdVoltage(105);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        this.updateEnergyAsReceiver();
    }

    public void setDirection(byte direction)
    {
        this.rotation = direction;
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
        readFromNBT(packet.getNbtCompound());
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("rotation", this.rotation);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.rotation = nbt.getInteger("rotation");
    }

    @Override
    public void sendData()
    {
        ;
    }

    @Override
    public void receiveData()
    {
        ;
    }

    @Override
    public INetworkDevice getHostDevice()
    {
        return null;
    }

    @Override
    public String getChannel()
    {
        return "default";
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
        return 120;
    }
}
