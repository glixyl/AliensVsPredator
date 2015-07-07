package com.arisux.avp.entities.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWorklight extends PoweredTileEntity
{
	@Override
	public void onVoltageTick()
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
		return 5;
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
	public void onUnderloadTick()
	{
		;
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
