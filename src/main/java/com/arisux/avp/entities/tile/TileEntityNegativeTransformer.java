package com.arisux.avp.entities.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.World;

public class TileEntityNegativeTransformer extends PoweredTileEntity
{
	public int rotation;
	public PoweredTileEntity originalpowersource;
	
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
		getOriginalPowerSource();
		if(isOriginalPowerSourceAttached()){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public double getVoltage()
	{
		return super.getVoltage();
	}


	@Override
	public void onVoltageTick()
	{
		double addedVoltage = ((this.getPowerSourceTile() != null ? this.getPowerSourceTile().getVoltageAfterApplyingResistance() : 0));
		addedVoltage -= 24;

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
		try{
			PoweredTileEntity t = this.getPowerSource();
			int x = t.xCoord;
			int y = t.yCoord;
			int z = t.zCoord;
			World world = t.getWorldObj();
			if(world.getTileEntity(x, y, z) instanceof TileEntityRepulsionGenerator){
				return true;
			}
			else if(world.getTileEntity(x, y, z) instanceof TileEntitySolarPanel){
				return true;
			}
			else{
				return false;
			}
		}
		catch(java.lang.NullPointerException e){
			return false;
		}
	}

	@Override
	public void setOriginalPowerSource(PoweredTileEntity e) {
		originalpowersource = e;	
	}
	@Override
	public PoweredTileEntity getPowerSource(){
		if(originalpowersource != null){
			System.out.println("here 3");
			return originalpowersource;
		}
		return null;
	}
	
	@Override
	public void getOriginalPowerSource() {
		List<PoweredTileEntity> list = new ArrayList<PoweredTileEntity>();
		list.add(this.getTop());
		list.add(this.getBack());
		list.add(this.getBottom());
		list.add(this.getLeft());
		list.add(this.getRight());
		list.add(this.getFront());
			for(PoweredTileEntity p : list){
				if(p instanceof TileEntityRepulsionGenerator || p instanceof TileEntityPowerline || p instanceof TileEntitySolarPanel || p instanceof TileEntityTransformer || p instanceof TileEntityNegativeTransformer){
					if(p instanceof TileEntityRepulsionGenerator){
						setOriginalPowerSource(p);
						break;
					}
					if(p instanceof TileEntitySolarPanel){
						setOriginalPowerSource(p);
						break;
					}
					else if(p instanceof TileEntityPowerline && p.getPowerSource() != null){
						setOriginalPowerSource(p.getPowerSource());
						break;
					}
					else if(p instanceof TileEntityTransformer && p.getPowerSource() != null){
						setOriginalPowerSource(p.getPowerSource());
						break;
					}
					else if(p instanceof TileEntityNegativeTransformer && p.getPowerSource() != null){
						setOriginalPowerSource(p.getPowerSource());
						break;
					}
				}
				
			}
	}
}
