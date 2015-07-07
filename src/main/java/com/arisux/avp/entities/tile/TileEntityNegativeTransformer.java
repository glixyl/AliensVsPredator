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
		double addedVoltage = 24;
		List<PoweredTileEntity> list = new ArrayList<PoweredTileEntity>();
		list.add(this.getTop());
		list.add(this.getBack());
		list.add(this.getBottom());
		list.add(this.getLeft());
		list.add(this.getRight());
		list.add(this.getFront());
		for(PoweredTileEntity e : list){
			if(e instanceof TileEntityPowerline && e.getVoltage() > 0){
				addedVoltage = e.getVoltage() - addedVoltage;
				break;
			}
		}
		try{
			for(PoweredTileEntity e : list)
			{
				if(e instanceof TileEntityPowerline && e.getVoltage() <= 0)
				{
					e.setVoltage(addedVoltage);
				}
			}
		}
		catch(NullPointerException e)
		{
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

	@Override
	public boolean isReciever() {
		return true;
	}

	@Override
	public boolean isOutputter() {
		return true;
	}

	@Override
	public void updateState()
	{
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
		for (PoweredTileEntity te : list) {
			if(te != null){
			//If it's a powersource, then add it.
			if(te.isOutputter() && !te.isReciever())
			{
				this.parents.add(te);
				te.children.add(this);
				this.state = te.canOutputPower();
			}
			else if(te.isOutputter() && te.isReciever())
			{
				//Is the neighbor powered?
				if(te.state)
				{
					this.parents.add(te);
					te.children.add(this);
					this.state = true;
				}
				else if(this.state && !te.state)
				{
					this.children.add(te);
					te.parents.add(this);
					te.state = true;
					te.updateChildren();
				}
			}
			else if(te.isReciever() && !te.isOutputter())
			{
				this.children.add(te);
				te.parents.add(this);
				te.state = this.state;
			}
		}
		}
	}
}
