package com.arisux.avp.entities.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyReceiver;

import com.arisux.avp.interfaces.INetworkDevice;

public class TileEntityWorkstation extends TileEntity implements INetworkDevice, IEnergyReceiver
{
	public int rotation;
	public int voltage;
	
	public TileEntityWorkstation()
	{
		;
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
		readFromNBT(packet.func_148857_g());
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("Rotation", this.rotation);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.rotation = nbt.getInteger("Rotation");
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
		return "Default";
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return false;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,
			boolean simulate) {
		if(!simulate)
		{
			this.voltage += maxReceive;
		}
		return this.voltage + maxReceive;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return this.voltage;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return 10000;
	}
}