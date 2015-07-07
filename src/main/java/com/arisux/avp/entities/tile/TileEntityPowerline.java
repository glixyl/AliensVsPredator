package com.arisux.avp.entities.tile;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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
	public PoweredTileEntity originalpowersource = null;
	
	@Override
	public void updateEntity()
	{
		this.updateState();
	}
	
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
		return true;
	}
	
	@Override
	public void updateState()
	{
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
		for (PoweredTileEntity te : list) {
			if(te != null){
			//If it's a powersource, then add it.
			if(te.isOutputter() && !te.isReciever())
			{
				this.parents.add(te);
				te.children.add(this);
				if(!this.state)
				{
					System.out.println("peoeo");
					this.state = te.canOutputPower();
				}
			}
			else if(te.isOutputter() && te.isReciever())
			{
				//Is the neighbor powered?
				if(te.state)
				{
					try
					{
							this.parents.add(te);
							te.children.add(this);
							this.state = true;
							te.updateChildren();
					}
					catch(ConcurrentModificationException e)
					{
						System.out.println("Hello world.");
					}
				}
				else if(this.state && !te.state)
				{
					try{
						this.children.add(te);
						te.parents.add(this);
						te.state = true;
						te.updateChildren();
					}
					catch(ConcurrentModificationException e)
					{
						System.out.println("Hello world.");
					}
				}
			}
			else if(te.isReciever() && !te.isOutputter())
			{
				this.children.add(te);
				te.parents.add(this);
				te.state = this.state;
				te.updateChildren();
			}
		}
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
			PoweredTileEntity t = this.getPowerSource();
			int x = t.xCoord;
			int y = t.yCoord;
			int z = t.zCoord;
			World world = t.getWorldObj();
			if(world.getTileEntity(x, y, z) instanceof TileEntityRepulsionGenerator)
			{
				return true;
			}
			else if(world.getTileEntity(x, y, z) instanceof TileEntitySolarPanel)
			{
				return true;
			}
			else if(world.getTileEntity(x, y, z) instanceof TileEntityR2PConvertor)
			{
				TileEntityR2PConvertor tre = (TileEntityR2PConvertor) world.getTileEntity(x, y, z);
				if(tre.isActiveRedstoneWireAttached)
				{
					return true;
				}
				else
				{
					return false;
				}
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
//		List<PoweredTileEntity> list = new ArrayList<PoweredTileEntity>();
//		list.add(this.getTop());
//		list.add(this.getBack());
//		list.add(this.getBottom());
//		list.add(this.getLeft());
//		list.add(this.getRight());
//		list.add(this.getFront());
//			for(PoweredTileEntity p : list){
//				if(p instanceof TileEntityRepulsionGenerator || p instanceof TileEntityPowerline || p instanceof TileEntitySolarPanel || p instanceof TileEntityTransformer || p instanceof TileEntityNegativeTransformer || p instanceof TileEntityR2PConvertor){
//					if(p instanceof TileEntityRepulsionGenerator){
//						setOriginalPowerSource(p);
//						break;
//					}
//					if(p instanceof TileEntitySolarPanel){
//						setOriginalPowerSource(p);
//						break;
//					}
//					if(p instanceof TileEntityR2PConvertor){
//						setOriginalPowerSource(p);
//						break;
//					}
//					else if(p instanceof TileEntityPowerline && p.getPowerSource() != null){
//						if(p.getPowerSource() instanceof TileEntityR2PConvertor && this.getPowerSource() == null && this.neighborRemoved)
//						{
//							p.setOriginalPowerSource(null);
//						}
//						else
//						{
//							setOriginalPowerSource(p.getPowerSource());
//						}
//						break;
//					}
//					else if(p instanceof TileEntityTransformer && p.getPowerSource() != null){
//						setOriginalPowerSource(p.getPowerSource());
//						break;
//					}
//					else if(p instanceof TileEntityNegativeTransformer && p.getPowerSource() != null){
//						setOriginalPowerSource(p.getPowerSource());
//						break;
//					}
//				}
//				
//			}
	}
	
	@Override
	public void setOriginalPowerSource(PoweredTileEntity e) {
		originalpowersource = e;
	}
	@Override
	public PoweredTileEntity getPowerSource(){
		if(originalpowersource != null){
				return originalpowersource;
		}
		return null;
	}

	@Override
	public boolean isReciever() {
		return true;
	}

	@Override
	public boolean isOutputter() {
		return true;
	}
}