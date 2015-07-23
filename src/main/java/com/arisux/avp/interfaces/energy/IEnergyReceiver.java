package com.arisux.avp.interfaces.energy;

import net.minecraftforge.common.util.ForgeDirection;

public interface IEnergyReceiver extends IEnergyConnection
{
	/**
	 * Add energy to an IEnergyReceiver, internal distribution is left entirely to the IEnergyReceiver.
	 *
	 * @param from
	 *            Orientation the energy is received from.
	 * @param maxReceive
	 *            Maximum amount of energy to receive.
	 * @param simulate
	 *            If TRUE, the charge will only be simulated.
	 * @return Amount of energy that was (or would have been, if simulated) received.
	 */
	double receiveEnergy(ForgeDirection from, double maxReceive, boolean simulate);

	/**
	 * Returns the amount of energy currently stored.
	 */
	double getEnergyStored(ForgeDirection from);

	/**
	 * Returns the maximum amount of energy that can be stored.
	 */
	double getMaxEnergyStored(ForgeDirection from);
}
