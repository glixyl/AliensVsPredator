package com.arisux.avp.entities.tile;

import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityR2PConverter extends TileEntity implements IEnergyProvider
{	
	public int rotation;
	public int voltage;
	public boolean isActiveRedstoneWireAttached;
	
	@Override
	public void updateEntity()
	{
		if(this.canOutputPower())
		{
			pushEnergy();
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

	protected void pushEnergy()
    {
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
        {
            TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
            if (tile instanceof IEnergyReceiver)
            {
                IEnergyReceiver ier = (IEnergyReceiver) tile;
                ier.receiveEnergy(dir, 120, false);
            }
        }
    }
	
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		if(maxExtract < this.voltage)
		{
			return maxExtract;
		}
		else
		{
			return this.voltage;
		}
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return this.voltage;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return 10000;
	}
	
	public boolean canOutputPower()
	{
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
		
		return this.isActiveRedstoneWireAttached;
	}
}