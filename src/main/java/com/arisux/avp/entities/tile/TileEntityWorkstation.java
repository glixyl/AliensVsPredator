package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.INetworkDevice;
import com.arisux.avp.interfaces.NetworkHolder;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityWorkstation extends PoweredTileEntity implements INetworkDevice
{
	public int rotation;
	public boolean isPowered;
	public NetworkHolder nwH;

	public TileEntityWorkstation()
	{
		;
	}

	public void setDirection(byte direction)
	{
		this.rotation = direction;
	}
	
	public void setPowered(boolean isPowered)
	{
		this.isPowered = isPowered;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveData()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public NetworkHolder getNetwork()
	{
		// TODO Auto-generated method stub
		if(this.nwH != null) {
			return this.nwH;
		} else {
			this.nwH = new NetworkHolder(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord));
			return this.nwH;
		}
	}
}