package com.arisux.avp.entities.tile;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySatelliteModem extends PoweredTileEntity
{

	public TileEntitySatelliteModem()
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
		return 120;
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

	@Override
	public boolean isOriginalPowerSourceAttached() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setOriginalPowerSource(TileEntityRepulsionGenerator e) {
		// TODO Auto-generated method stub
		
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
}
