package com.arisux.avp.entities.tile;

import java.util.ArrayList;
import java.util.List;

import com.arisux.airi.lib.WorldUtil;

import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityR2PConvertor extends PoweredTileEntity
{
	public int rotation;
	public boolean isActiveRedstoneWireAttached = false;
	
	public void setDirection(byte direction)
	{
		this.rotation = direction;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		World world = this.getWorldObj();
		int x = this.xCoord;
		int y = this.yCoord;
		int z = this.zCoord;
		
		if(world.getBlock(x + 1, y, z) instanceof BlockRedstoneWire)
		{
			BlockRedstoneWire wire = (BlockRedstoneWire) world.getBlock(x + 1, y, z);
			this.isActiveRedstoneWireAttached = world.getBlockMetadata(x + 1, y, z) != 0;
		}
		
		else if(world.getBlock(x, y + 1, z) instanceof BlockRedstoneWire)
		{
			BlockRedstoneWire wire = (BlockRedstoneWire) world.getBlock(x, y + 1, z);
			this.isActiveRedstoneWireAttached = world.getBlockMetadata(x, y + 1, z) != 0;
		}
		
		else if(world.getBlock(x, y, z + 1) instanceof BlockRedstoneWire)
		{
			BlockRedstoneWire wire = (BlockRedstoneWire) world.getBlock(x, y, z + 1);
			this.isActiveRedstoneWireAttached = world.getBlockMetadata(x, y, z + 1) != 0;
		}
		
		else if(world.getBlock(x - 1, y, z) instanceof BlockRedstoneWire)
		{
			BlockRedstoneWire wire = (BlockRedstoneWire) world.getBlock(x - 1, y, z);
			this.isActiveRedstoneWireAttached = world.getBlockMetadata(x - 1, y, z) != 0;
		}
		
		else if(world.getBlock(x, y - 1, z) instanceof BlockRedstoneWire)
		{
			BlockRedstoneWire wire = (BlockRedstoneWire) world.getBlock(x, y - 1, z);
			this.isActiveRedstoneWireAttached = world.getBlockMetadata(x, y - 1, z) != 0;
		}
		
		else if(world.getBlock(x, y, z - 1) instanceof BlockRedstoneWire)
		{
			BlockRedstoneWire wire = (BlockRedstoneWire) world.getBlock(x, y, z - 1);
			this.isActiveRedstoneWireAttached = world.getBlockMetadata(x, y, z - 1) != 0;
		}
		
		else
		{
			this.isActiveRedstoneWireAttached = false;
		}
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
	public boolean canOutputPower()
	{
		return isActiveRedstoneWireAttached;
	}

	@Override
	public double getVoltage()
	{
		double addedVoltage = 0;
		if(isActiveRedstoneWireAttached){
			addedVoltage = 120;
		
			if (getTop() instanceof TileEntityRepulsionGenerator)
			{
				addedVoltage += 120;
			}
		
			if (getBottom() instanceof TileEntityRepulsionGenerator)
			{
				addedVoltage += 120;
			}
		
			if (getLeft() instanceof TileEntityRepulsionGenerator)
			{
				addedVoltage += 120;
			}
		
			if (getRight() instanceof TileEntityRepulsionGenerator)
			{
				addedVoltage += 120;
			}
		
			if (getFront() instanceof TileEntityRepulsionGenerator)
			{
				addedVoltage += 120;
			}
		
			if (getBack() instanceof TileEntityRepulsionGenerator)
			{
				addedVoltage += 120;
			}
		
		
			if (getTop() instanceof TileEntitySolarPanel)
			{
				addedVoltage += 220;
			}
		
			if (getBottom() instanceof TileEntitySolarPanel)
			{
				addedVoltage += 220;
			}
		
			if (getLeft() instanceof TileEntitySolarPanel)
			{
				addedVoltage += 220;
			}
		
			if (getRight() instanceof TileEntitySolarPanel)
			{
				addedVoltage += 220;
			}
		
			if (getFront() instanceof TileEntitySolarPanel)
			{
				addedVoltage += 220;
			}
		
			if (getBack() instanceof TileEntitySolarPanel)
			{
				addedVoltage += 220;
			}
		}
		return addedVoltage;
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

	@Override
	public boolean isReciever() {
		return false;
	}

	@Override
	public boolean isOutputter() {
		return true;
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
			if(e.isReciever())
			{
				e.parents.add(this);
				this.children.add(e);
				e.state = this.canOutputPower();
				e.updateChildren();
			}
		}
		}
		
	}
}
