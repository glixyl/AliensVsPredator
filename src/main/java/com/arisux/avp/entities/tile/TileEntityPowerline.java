package com.arisux.avp.entities.tile;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPowerline extends TileEntity implements IEnergyProvider, IEnergyReceiver
{
	public int voltage;
	public boolean turnOff = true;
	public Set<ForgeDirection> d = new HashSet<ForgeDirection>();
	public boolean firstTimeCalled = true;
	
	@Override
	public void updateEntity()
	{
		if(!this.isAdjacentToPowerSource())
		{
			this.voltage = 0;
		}
		if(turnOff)
		{
			this.voltage = 0;
		}
		turnOff = true;
	}

	public void pushEnergy()
	{
		if(firstTimeCalled)
		{
			whatDirectionsAreBanned();
			firstTimeCalled = false;
		}
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
		{
			if(d.contains(dir))
			{		
				continue;
			}
			TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
			if(tile != null)
			{
				if (tile instanceof IEnergyReceiver && !isTileNextToGenerator(tile))
				{
					IEnergyReceiver ier = (IEnergyReceiver) tile;
					ier.receiveEnergy(dir, 120, false);
				}
			}
		}
	}

	public boolean isTileNextToGenerator(TileEntity te) {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
		{
			TileEntity tile = worldObj.getTileEntity(te.xCoord + dir.offsetX, te.yCoord + dir.offsetY, te.zCoord + dir.offsetZ);
			if(tile instanceof TileEntityRepulsionGenerator)
			{
				return true;
			}
			else if(tile instanceof TileEntitySolarPanel)
			{
				TileEntitySolarPanel tesp = (TileEntitySolarPanel) tile;
				if(tesp.canOutputPower())
				{
					return true;
				}
			}
			else if(tile instanceof TileEntityR2PConvertor)
			{
				TileEntityR2PConvertor r2p = (TileEntityR2PConvertor) tile;
				if(r2p.canOutputPower())
				{
					return true;
				}
			}
		}
		return false;
	}

	public void whatDirectionsAreBanned() {
		for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
		{
			if(this.d.contains(direction))
			{		
				continue;
			}
			TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
			if(tile != null && tile instanceof TileEntityPowerline)
			{
				TileEntityPowerline tep = (TileEntityPowerline) tile;
				if(tep.voltage > 0)
				{
					this.d.add(direction);
				}
			}
			
			if(tile != null && tile instanceof TileEntityRepulsionGenerator)
			{
				this.d.add(direction);
			}
			
			if(tile != null && tile instanceof TileEntitySolarPanel)
			{
				this.d.add(direction);
			}
		}
	}

	public boolean isAdjacentToPowerSource() {
		if(this.getWorldObj().getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord) instanceof TileEntityPowerline)
		{
			TileEntityPowerline te = (TileEntityPowerline) this.getWorldObj().getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord) instanceof TileEntityPowerline)
		{
			TileEntityPowerline te = (TileEntityPowerline) this.getWorldObj().getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof TileEntityPowerline)
		{
			TileEntityPowerline te = (TileEntityPowerline) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof TileEntityPowerline)
		{
			TileEntityPowerline te = (TileEntityPowerline) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1) instanceof TileEntityPowerline)
		{
			TileEntityPowerline te = (TileEntityPowerline) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1) instanceof TileEntityPowerline)
		{
			TileEntityPowerline te = (TileEntityPowerline) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord) instanceof TileEntityRepulsionGenerator)
		{
			return true;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord) instanceof TileEntityRepulsionGenerator)
		{
			return true;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof TileEntityRepulsionGenerator)
		{
			return true;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof TileEntityRepulsionGenerator)
		{
			return true;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1) instanceof TileEntityRepulsionGenerator)
		{
			return true;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1) instanceof TileEntityRepulsionGenerator)
		{
			return true;
		}

		if(this.getWorldObj().getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord) instanceof TileEntitySolarPanel)
		{
			TileEntitySolarPanel te = (TileEntitySolarPanel) this.getWorldObj().getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord) instanceof TileEntitySolarPanel)
		{
			TileEntitySolarPanel te = (TileEntitySolarPanel) this.getWorldObj().getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof TileEntitySolarPanel)
		{
			TileEntitySolarPanel te = (TileEntitySolarPanel) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof TileEntitySolarPanel)
		{
			TileEntitySolarPanel te = (TileEntitySolarPanel) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1) instanceof TileEntitySolarPanel)
		{
			TileEntitySolarPanel te = (TileEntitySolarPanel) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1) instanceof TileEntitySolarPanel)
		{
			TileEntitySolarPanel te = (TileEntitySolarPanel) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		
		if(this.getWorldObj().getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord) instanceof TileEntityR2PConvertor)
		{
			TileEntityR2PConvertor te = (TileEntityR2PConvertor) this.getWorldObj().getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord) instanceof TileEntityR2PConvertor)
		{
			TileEntityR2PConvertor te = (TileEntityR2PConvertor) this.getWorldObj().getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof TileEntityR2PConvertor)
		{
			TileEntityR2PConvertor te = (TileEntityR2PConvertor) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof TileEntityR2PConvertor)
		{
			TileEntityR2PConvertor te = (TileEntityR2PConvertor) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1) instanceof TileEntityR2PConvertor)
		{
			TileEntityR2PConvertor te = (TileEntityR2PConvertor) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1) instanceof TileEntityR2PConvertor)
		{
			TileEntityR2PConvertor te = (TileEntityR2PConvertor) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}

		if(this.getWorldObj().getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord) instanceof TileEntityTransformer)
		{
			TileEntityTransformer te = (TileEntityTransformer) this.getWorldObj().getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord) instanceof TileEntityTransformer)
		{
			TileEntityTransformer te = (TileEntityTransformer) this.getWorldObj().getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof TileEntityTransformer)
		{
			TileEntityTransformer te = (TileEntityTransformer) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof TileEntityTransformer)
		{
			TileEntityTransformer te = (TileEntityTransformer) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1) instanceof TileEntityTransformer)
		{
			TileEntityTransformer te = (TileEntityTransformer) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1) instanceof TileEntityTransformer)
		{
			TileEntityTransformer te = (TileEntityTransformer) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		
		if(this.getWorldObj().getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord) instanceof TileEntityNegativeTransformer)
		{
			TileEntityNegativeTransformer te = (TileEntityNegativeTransformer) this.getWorldObj().getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord) instanceof TileEntityNegativeTransformer)
		{
			TileEntityNegativeTransformer te = (TileEntityNegativeTransformer) this.getWorldObj().getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof TileEntityNegativeTransformer)
		{
			TileEntityNegativeTransformer te = (TileEntityNegativeTransformer) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof TileEntityNegativeTransformer)
		{
			TileEntityNegativeTransformer te = (TileEntityNegativeTransformer) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1) instanceof TileEntityNegativeTransformer)
		{
			TileEntityNegativeTransformer te = (TileEntityNegativeTransformer) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1) instanceof TileEntityNegativeTransformer)
		{
			TileEntityNegativeTransformer te = (TileEntityNegativeTransformer) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
			if(te.voltage > 0)
			{
				return true;
			}
			return false;
		}
		
		return false;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) 
	{
		d.add(from.getOpposite());
		pushEnergy();
		if(turnOff)
		{
			turnOff = false;
		}
		return this.voltage = maxReceive;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,
			boolean simulate) {
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
	public int getEnergyStored(ForgeDirection from) {
		return this.voltage;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return 10000;
	}

}