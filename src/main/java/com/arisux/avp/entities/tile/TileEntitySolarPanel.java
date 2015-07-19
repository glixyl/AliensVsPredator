package com.arisux.avp.entities.tile;

import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntitySolarPanel extends TileEntity implements IEnergyProvider
{	
	public int rotation;
	public int voltage;
	
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
			if (tile instanceof TileEntityPowerline)
			{
				TileEntityPowerline tep = (TileEntityPowerline) tile;
				if(tep.d.contains(dir.getOpposite()) || tep.voltage == 0)
				{
					tep.receiveEnergy(dir, 120, false);
				}
			}
			else if(tile instanceof IEnergyReceiver)
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
		if(world.getWorldTime() < 12300 || world.getWorldTime()  > 23850){
			return true;
		}
		return false;
	}
}
