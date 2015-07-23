package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.energy.IEnergyProvider;
import com.arisux.avp.interfaces.energy.IEnergyReceiver;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPowerline extends TileEntityElectrical implements IEnergyProvider, IEnergyReceiver
{
<<<<<<< HEAD
	public TileEntityPowerline()
	{
		super(false);
	}
=======
	public int voltage;
	public boolean turnOff = true;
	public Set<ForgeDirection> d = new HashSet<ForgeDirection>();
	public boolean firstTimeCalled = true;
>>>>>>> branch 'master' of https://github.com/Ri5ux/AliensVsPredator.git
	
	@Override
	public void updateEntity()
	{
<<<<<<< HEAD
		super.updateEntity();
		this.updateEnergyAsReceiver();
=======
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
					if(this.voltage > 0)
					{
						ier.receiveEnergy(dir, this.voltage, false);
					}
					else
					{
						ier.receiveEnergy(dir, 120, false);
					}
				}
				else
				{
					this.d.add(dir);
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
			else if(tile instanceof TileEntityR2PConverter)
			{
				TileEntityR2PConverter r2p = (TileEntityR2PConverter) tile;
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
		
		if(this.getWorldObj().getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord) instanceof TileEntityR2PConverter)
		{
			TileEntityR2PConverter te = (TileEntityR2PConverter) this.getWorldObj().getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord) instanceof TileEntityR2PConverter)
		{
			TileEntityR2PConverter te = (TileEntityR2PConverter) this.getWorldObj().getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof TileEntityR2PConverter)
		{
			TileEntityR2PConverter te = (TileEntityR2PConverter) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof TileEntityR2PConverter)
		{
			TileEntityR2PConverter te = (TileEntityR2PConverter) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1) instanceof TileEntityR2PConverter)
		{
			TileEntityR2PConverter te = (TileEntityR2PConverter) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
			if(te.canOutputPower())
			{
				return true;
			}
			return false;
		}
		if(this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1) instanceof TileEntityR2PConverter)
		{
			TileEntityR2PConverter te = (TileEntityR2PConverter) this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
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
>>>>>>> branch 'master' of https://github.com/Ri5ux/AliensVsPredator.git
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public double receiveEnergy(ForgeDirection from, double maxReceive, boolean simulate)
	{
		return super.receiveEnergy(from, maxReceive, simulate);
	}

	@Override
	public double extractEnergy(ForgeDirection from, double maxExtract, boolean simulate)
	{
		return super.extractEnergy(from, maxExtract, simulate);
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
}
