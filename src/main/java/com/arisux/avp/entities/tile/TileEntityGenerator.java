package com.arisux.avp.entities.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityGenerator extends PoweredTileEntity
{
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		readFromNBT(packet.func_148857_g());
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
	}
	
	@Override
	public boolean canOutputPower()
	{
		return true;
	}
	
	@Override
	public double getVoltage()
	{
		return 120;
	}

	@Override
	public void onVoltageTick()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOverloadTick()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnderloadTick()
	{
		// TODO Auto-generated method stub
		
	}
}
