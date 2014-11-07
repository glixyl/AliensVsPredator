package com.arisux.avp.interfaces;

public interface IPowerDevice
{
	public boolean canOutputPower();
	public double getVoltage();
	public double getMaxOperatingVoltage();
	public double getMinOperatingVoltage();
	public double getAmperage();
	public double getMaxOperatingAmps();
	public double getMinOperatingAmps();
	public double getResistance();
	public void onVoltageTick();
	public void onOverloadTick();
	public void onUnderloadTick();
}
