package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.energy.IEnergyProvider;

import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityR2PConverter extends TileEntityElectrical implements IEnergyProvider
{
	public TileEntityR2PConverter()
	{
		super(false);
	}

	public int rotation;
	public boolean isActiveRedstoneWireAttached;

	@Override
	public void updateEntity()
	{
		if (this.canOutputPower())
		{
			;
		}
	}

	public void setDirection(byte direction)
	{
		this.rotation = direction;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return false;
	}

	@Override
	public double extractEnergy(ForgeDirection from, double maxExtract, boolean simulate)
	{
		return 0;
	}

	@Override
	public double getEnergyStored(ForgeDirection from)
	{
		return this.voltage;
	}

	@Override
	public double getMaxEnergyStored(ForgeDirection from)
	{
		return 10000;
	}

	public boolean canOutputPower()
	{
		World world = this.getWorldObj();
		int x = this.xCoord;
		int y = this.yCoord;
		int z = this.zCoord;

		if (world.getBlock(x + 1, y, z) instanceof BlockRedstoneWire)
		{
			this.isActiveRedstoneWireAttached = world.getBlockMetadata(x + 1, y, z) != 0;
		}

		else if (world.getBlock(x, y + 1, z) instanceof BlockRedstoneWire)
		{
			this.isActiveRedstoneWireAttached = world.getBlockMetadata(x, y + 1, z) != 0;
		}

		else if (world.getBlock(x, y, z + 1) instanceof BlockRedstoneWire)
		{
			this.isActiveRedstoneWireAttached = world.getBlockMetadata(x, y, z + 1) != 0;
		}

		else if (world.getBlock(x - 1, y, z) instanceof BlockRedstoneWire)
		{
			this.isActiveRedstoneWireAttached = world.getBlockMetadata(x - 1, y, z) != 0;
		}

		else if (world.getBlock(x, y - 1, z) instanceof BlockRedstoneWire)
		{
			this.isActiveRedstoneWireAttached = world.getBlockMetadata(x, y - 1, z) != 0;
		}

		else if (world.getBlock(x, y, z - 1) instanceof BlockRedstoneWire)
		{
			this.isActiveRedstoneWireAttached = world.getBlockMetadata(x, y, z - 1) != 0;
		}

		else
		{
			this.isActiveRedstoneWireAttached = false;
		}

		return this.isActiveRedstoneWireAttached;
	}
}
