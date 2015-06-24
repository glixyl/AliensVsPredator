package com.arisux.avp.entities.tile;

public class TileEntityTransformer extends PoweredTileEntity
{
	public int rotation;
	
	public void setDirection(byte direction)
	{
		this.rotation = direction;
	}

	@Override
	public void outputPower()
	{
		super.outputPower();
	}

	@Override
	public void outputPowerToTile(PoweredTileEntity tile, double voltage)
	{
		super.outputPowerToTile(tile, voltage);
	}

	@Override
	public boolean canOutputPower()
	{
		return true;
	}

	@Override
	public double getVoltage()
	{
		return super.getVoltage();
	}


	@Override
	public void onVoltageTick()
	{
		double addedVoltage = 24;
		addedVoltage = (addedVoltage + (this.getPowerSourceTile() != null ? this.getPowerSourceTile().getVoltageAfterApplyingResistance() : 0));

		if (getTop() instanceof TileEntityPowerline && getTop() != getPowerSourceTile())
		{
			if(getTop().voltage <= 0)
			{
				getTop().setVoltage(addedVoltage);
			}
		}
		
		if (getBottom() instanceof TileEntityPowerline && getBottom() != getPowerSourceTile())
		{
			if(getBottom().voltage <= 0)
			{
				getBottom().setVoltage(addedVoltage);
			}
		}
		
		if (getLeft() instanceof TileEntityPowerline && getLeft() != getPowerSourceTile())
		{
			if(getLeft().voltage <= 0)
			{
				getLeft().setVoltage(addedVoltage);
			}
		}
		
		if (getRight() instanceof TileEntityPowerline && getRight() != getPowerSourceTile())
		{
			if(getRight().voltage <= 0)
			{
				getRight().setVoltage(addedVoltage);
			}
		}
		
		if (getFront() instanceof TileEntityPowerline && getFront() != getPowerSourceTile())
		{
			if(getFront().voltage <= 0 )
			{
				getFront().setVoltage(addedVoltage);
			}
		}
		
		if (getBack() instanceof TileEntityPowerline && getBack() != getPowerSourceTile())
		{
			if(getBack().voltage <= 0 )
			{
				getBack().setVoltage(addedVoltage);
			}
		}		
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
	public boolean isOriginalPowerSourceAttached() 
	{
		return false;
	}

	@Override
	public void getOriginalPowerSource() 
	{
		;
	}

	@Override
	public TileEntityRepulsionGenerator getPowerSource() 
	{
		return null;
	}

	@Override
	public void setOriginalPowerSource(PoweredTileEntity e)
	{
		;
	}
}
