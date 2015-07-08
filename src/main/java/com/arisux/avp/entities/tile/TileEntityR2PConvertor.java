package com.arisux.avp.entities.tile;

import java.util.ArrayList;
import java.util.List;

import cofh.api.energy.IEnergyProvider;

import com.arisux.airi.lib.WorldUtil;

import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityR2PConvertor extends TileEntity implements IEnergyProvider
{
	public boolean isActiveRedstoneWireAttached = false;
	public int voltage;
	
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
	public boolean canConnectEnergy(ForgeDirection from) {
		return false;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,
			boolean simulate) {
		if(isActiveRedstoneWireAttached)
		{
			this.voltage = 24;
			return 24;
		}
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		if(isActiveRedstoneWireAttached)
		{
			return this.voltage;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return 24;
	}
}
