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
import net.minecraft.world.World;

public class TileEntityPowerline extends PoweredTileEntity
{
	public TileEntityRepulsionGenerator originalpowersource = null;
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
		return 0.002;
	}
	
	@Override
	public boolean canOutputPower()
	{
		getOriginalPowerSource();
		if(isOriginalPowerSourceAttached()){
			return true;
		}
		else{
			return false;
		}
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
		try{
			TileEntityRepulsionGenerator t = this.getPowerSource();
			int x = t.xCoord;
			int y = t.yCoord;
			int z = t.zCoord;
			World world = t.getWorldObj();
			if(world.getTileEntity(x, y, z) instanceof TileEntityRepulsionGenerator){
				return true;
			}
			else{
				return false;
			}
		}
		catch(java.lang.NullPointerException e){
			return false;
		}
	}

	@Override
	public void getOriginalPowerSource() {
		List<PoweredTileEntity> list = new ArrayList<PoweredTileEntity>();
		list.add(this.getTop());
		list.add(this.getBack());
		list.add(this.getBottom());
		list.add(this.getLeft());
		list.add(this.getRight());
		list.add(this.getFront());
			for(PoweredTileEntity p : list){
				if(p instanceof TileEntityRepulsionGenerator || p instanceof TileEntityPowerline){
					if(p instanceof TileEntityRepulsionGenerator){
						setOriginalPowerSource((TileEntityRepulsionGenerator) p);
						break;
					}
					else if(p instanceof TileEntityPowerline && p.getPowerSource() != null){
						setOriginalPowerSource((TileEntityRepulsionGenerator) p.getPowerSource());
						break;
					}
				}
				
			}
	}
	@Override
	public void setOriginalPowerSource(TileEntityRepulsionGenerator e) {
		originalpowersource = e;
		
	}
	@Override
	public TileEntityRepulsionGenerator getPowerSource(){
		if(originalpowersource != null){
				return originalpowersource;
		}
		return null;
	}
}