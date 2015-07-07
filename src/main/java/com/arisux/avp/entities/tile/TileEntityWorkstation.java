package com.arisux.avp.entities.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import com.arisux.avp.interfaces.INetworkDevice;

public class TileEntityWorkstation extends PoweredTileEntity implements INetworkDevice
{
	public int rotation;

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
	public void onVoltageTick()
	{
		;
	}

	@Override
	public void onOverloadTick()
	{
		;
	}

	@Override
	public void onUnderloadTick()
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
	public boolean isOriginalPowerSourceAttached() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void getOriginalPowerSource() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TileEntityRepulsionGenerator getPowerSource() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setOriginalPowerSource(PoweredTileEntity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isReciever() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isOutputter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateState() {
		//Must keep lists empty so we don't have 1 block as a parent AND a child.
		parents.clear();
		children.clear();
				
		List<PoweredTileEntity> list = new ArrayList<PoweredTileEntity>();
		list.add(this.getTop());
		list.add(this.getBack());
		list.add(this.getBottom());
		list.add(this.getLeft());
		list.add(this.getRight());
		list.add(this.getFront());
		for (PoweredTileEntity e : list) {
			if(e != null){
			if(e.isOutputter())
			{
				if(!this.parents.contains(e))
				{
					this.parents.add(e);
				}
				if(!e.children.contains(this))
				{
					e.children.add(this);
				}
				if(!this.state)
				{
					this.state = e.canOutputPower();
				}
			}
		}
		}
	}
}