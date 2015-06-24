package com.arisux.avp.entities.tile;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBlastdoor extends PoweredTileEntity
{
	private float doorProgress;
	private boolean doorOpen;
	private ArrayList<com.arisux.airi.lib.WorldUtil.Blocks.CoordData> managedCoords = new ArrayList<com.arisux.airi.lib.WorldUtil.Blocks.CoordData>();

	public TileEntityBlastdoor()
	{
		;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
	}

	@Override
	public void onVoltageTick()
	{
		if (this.doorOpen)
		{
			this.doorProgress = this.doorProgress < 1.0F ? this.doorProgress + 0.02F : this.doorProgress;
		}

		if (!this.doorOpen)
		{
			this.doorProgress = this.doorProgress > 0.0F ? this.doorProgress - 0.02F : this.doorProgress;
		}
	}

	@Override
	public void onUnderloadTick()
	{
		;
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
		this.readFromNBT(packet.func_148857_g());
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
	}

	@Override
	public double getMinOperatingVoltage()
	{
		return 0;
	}

	@Override
	public double getMaxOperatingVoltage()
	{
		return 10000;
	}

	@Override
	public double getMinOperatingAmps()
	{
		return 0;
	}

	@Override
	public double getMaxOperatingAmps()
	{
		return 1000;
	}

	@Override
	public double getResistance()
	{
		return 0.1;
	}

	@Override
	public boolean canOutputPower()
	{
		return false;
	}

	@Override
	public Block getBlockType()
	{
		return Blocks.beacon;
	}

	@Override
	public void onOverloadTick()
	{
		;
	}

	public boolean isDoorOpen()
	{
		return doorOpen;
	}

	public void setDoorOpen(boolean doorOpen)
	{
		this.doorOpen = doorOpen;
	}

	public float getDoorProgress()
	{
		return doorProgress;
	}

	public void assignCoord(com.arisux.airi.lib.WorldUtil.Blocks.CoordData data)
	{
		if (!this.managedCoords.contains(data))
		{
			this.managedCoords.add(data);
		}
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
}
