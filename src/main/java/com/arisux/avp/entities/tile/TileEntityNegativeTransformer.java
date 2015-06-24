package com.arisux.avp.entities.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityNegativeTransformer extends PoweredTileEntity
{
	public int rotation;
	
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
	public void outputPower()
	{
		super.outputPower();
	}

	@Override
	public void outputPowerToTile(PoweredTileEntity tile, double voltage)
	{
		if (tile != null)
		{
			double previousVoltage = tile.getVoltage();
			tile.setVoltage(previousVoltage + voltage);
		}
	}

	@Override
	public boolean canOutputPower()
	{
		return true;
	}

	@Override
	public double getVoltage(){
		double addedVoltage = -24;

		if (getTop() instanceof TileEntityPowerline)
		{
			if(getTop().voltage > 0){
				return addedVoltage;
			}
		}
		
		if (getBottom() instanceof TileEntityPowerline)
		{
			if(getBottom().voltage > 0){
				return addedVoltage;
			}
		}
		
		if (getLeft() instanceof TileEntityPowerline)
		{
			if(getLeft().voltage > 0){
				return addedVoltage;
			}
		}
		
		if (getRight() instanceof TileEntityPowerline)
		{
			if(getRight().voltage > 0){
				return addedVoltage;
			}
		}
		
		if (getFront() instanceof TileEntityPowerline)
		{
				if(getFront().voltage > 0 ){
					return addedVoltage;
				}
		}
		
		if (getBack() instanceof TileEntityPowerline){
			if(getBack().voltage > 0 ){
				return addedVoltage;
			}
		}		
		return 0;
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
	public double getMaxOperatingVoltage()
	{
		return 24000;
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
