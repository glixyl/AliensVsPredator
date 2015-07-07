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

public class TileEntityP2RConvertor extends PoweredTileEntity
{
	public int rotation;
	
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
			if(world.getBlockMetadata(x + 1, y, z) == 0 && this.voltage >= 24)
			{
				world.setBlockMetadataWithNotify(x + 1, y, z, 15, 1);
			}
			if(world.getBlockMetadata(x + 1, y, z) == 15 && this.voltage == 0)
			{
				world.setBlockMetadataWithNotify(x + 1, y, z, 0, 1);
				
			}
		}
		
		if(world.getBlock(x, y + 1, z) instanceof BlockRedstoneWire)
		{
			if(world.getBlockMetadata(x, y + 1, z) == 0 && this.voltage >= 24)
			{
				world.setBlockMetadataWithNotify(x, y + 1, z, 15, 1);
			}
			if(world.getBlockMetadata(x, y + 1, z) == 15 && this.voltage == 0)
			{
				world.setBlockMetadataWithNotify(x, y + 1, z, 0, 1);
				System.out.println("can't turn it off");
			}
		}
		
		if(world.getBlock(x, y, z + 1) instanceof BlockRedstoneWire)
		{
			if(world.getBlockMetadata(x, y, z + 1) == 0 && this.voltage >= 24)
			{
				world.setBlockMetadataWithNotify(x, y, z + 1, 15, 1);
			}
			if(world.getBlockMetadata(x, y, z + 1) == 15 && this.voltage == 0)
			{
				world.setBlockMetadataWithNotify(x, y, z + 1, 0, 1);
				System.out.println("can't turn it off");
			}
		}
		
		if(world.getBlock(x - 1, y, z) instanceof BlockRedstoneWire)
		{
			if(world.getBlockMetadata(x - 1, y, z) == 0 && this.voltage >= 24)
			{
				world.setBlockMetadataWithNotify(x - 1, y, z, 15, 1);
			}
			if(world.getBlockMetadata(x - 1, y, z) == 15 && this.voltage == 0)
			{
				world.setBlockMetadataWithNotify(x - 1, y, z, 0, 1);
				System.out.println("can't turn it off");
			}
		}
		
		if(world.getBlock(x, y - 1, z) instanceof BlockRedstoneWire)
		{
			if(world.getBlockMetadata(x, y - 1, z) == 0 && this.voltage >= 24)
			{
				world.setBlockMetadataWithNotify(x, y - 1, z, 15, 1);
			}
			if(world.getBlockMetadata(x, y - 1, z) == 15 && this.voltage == 0)
			{
				world.setBlockMetadataWithNotify(x, y - 1, z, 0, 1);
				System.out.println("can't turn it off");
			}
		}
		
		if(world.getBlock(x, y, z - 1) instanceof BlockRedstoneWire)
		{
			if(world.getBlockMetadata(x, y, z - 1) == 0 && this.voltage >= 24)
			{
				world.setBlockMetadataWithNotify(x, y, z - 1, 15, 1);
			}
			if(world.getBlockMetadata(x, y, z - 1) == 15 && this.voltage == 0)
			{
				world.setBlockMetadataWithNotify(x, y, z - 1, 0, 1);
				System.out.println("can't turn it off");
			}
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
		return false;
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
	public double getMinOperatingVoltage()
	{
		return 24;
	}
	
	@Override
	public double getMaxOperatingVoltage()
	{
		return 24000;
	}

	@Override
	public boolean isOriginalPowerSourceAttached()
	{
		return false;
	}

	@Override
	public void getOriginalPowerSource()
	{}

	@Override
	public TileEntityRepulsionGenerator getPowerSource()
	{
		return null;
	}

	@Override
	public void setOriginalPowerSource(PoweredTileEntity e)
	{}

	@Override
	public boolean isReciever() {
		return true;
	}

	@Override
	public boolean isOutputter() {
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
