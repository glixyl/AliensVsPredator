package com.arisux.avp.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.arisux.avp.entities.tile.PoweredTileEntity;
import com.arisux.avp.entities.tile.TileEntityRepulsionGenerator;
import com.arisux.avp.entities.tile.TileEntitySolarPanel;

import net.minecraft.tileentity.TileEntity;

public interface IPowerDevice
{
	public boolean isReciever();
	public boolean isOutputter();
	public boolean isOriginalPowerSourceAttached();
	public boolean canOutputPower();
	public double getVoltage();
	public double getMaxOperatingVoltage();
	public double getMinOperatingVoltage();
	public double getAmperage();
	public double getMaxOperatingAmps();
	public List<PoweredTileEntity> parents = new ArrayList<PoweredTileEntity>();
	public List<PoweredTileEntity> children = new ArrayList<PoweredTileEntity>();
	public double getMinOperatingAmps();
	public double getResistance();
	public void getOriginalPowerSource();
	public void onVoltageTick();
	public void onOverloadTick();
	public void updateChildren();
	public void updateState();
	public PoweredTileEntity getPowerSource();
	public void onUnderloadTick();
	public void setOriginalPowerSource(PoweredTileEntity e);
}
