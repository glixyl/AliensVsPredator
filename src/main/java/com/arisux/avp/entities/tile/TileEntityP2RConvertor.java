package com.arisux.avp.entities.tile;

import java.util.ArrayList;
import java.util.List;

import cofh.api.energy.IEnergyReceiver;

import com.arisux.airi.lib.WorldUtil;

import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityP2RConvertor extends TileEntity implements IEnergyReceiver
{
	public int rotation;
	public int voltage;
	
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
	public boolean canConnectEnergy(ForgeDirection from) {
		return false;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,
			boolean simulate) {
		if(!simulate)
		{
			this.voltage += maxReceive;
		}
		return maxReceive + this.voltage;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return this.voltage;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return 10000;
	}
}
