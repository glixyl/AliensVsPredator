package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.energy.IEnergyProvider;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEntityElectrical extends TileEntity
{
	protected double voltage;
	protected double srcVoltage;
	protected double thresholdVoltage;
	protected double resistance;
	protected double boost;
	protected int srcHertz;
	protected boolean isSrc;

	public TileEntityElectrical(boolean isSource)
	{
		this.isSrc = isSource;
		this.thresholdVoltage = 110;
		this.srcVoltage = 120;
		this.srcHertz = 50;
		/** 1000 / 50Hz = 20 Ticks **/
		this.resistance = 0.1;
		this.boost = 0;
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		readFromNBT(packet.func_148857_g());
	}

	/**
	 * Saves the amount of voltage this component contains to the world.
	 */
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setDouble("voltage", this.voltage);
	}

	/**
	 * Loads the amount of voltage this component contains from the world.
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.voltage = nbt.getDouble("voltage");
	}

	/**
	 * @return Returns true if this component is currently operational.
	 */
	public boolean isOperational()
	{
		return this.getVoltage() >= this.getThresholdVoltage();
	}

	/**
	 * @return Returns the amount of resistance this component applies.
	 */
	public double getResistance()
	{
		return resistance;
	}

	/**
	 * @param resistance - The amount of resistance this component will apply 
	 * on components extracting energy from it.
	 */
	public void setResistance(double resistance)
	{
		this.resistance = resistance;
	}

	/**
	 * @return The threshold voltage required for this component to operate.
	 */
	public double getThresholdVoltage()
	{
		return thresholdVoltage;
	}

	/**
	 * @param thresholdVoltage - The threshold voltage required for this 
	 * component to operate.
	 */
	public void setThresholdVoltage(double thresholdVoltage)
	{
		this.thresholdVoltage = thresholdVoltage;
	}

	/**
	 * @param side - ForgeDirection from the receiver. 
	 * @return If this side can provide energy to the receiver.
	 */
	public boolean canProvideEnergyToReceiver(ForgeDirection side)
	{
		return true;
	}

	/**
	 * @return The amount of voltage this component currently contains.
	 */
	public double getVoltage()
	{
		return this.voltage;
	}

	/**
	 * @param voltage - The amount of voltage this component should contain.
	 */
	public void setVoltage(double voltage)
	{
		this.voltage = voltage;
	}

	/**
	 * @return The amount of boost this component currently contains.
	 */
	public double getBoost()
	{
		return this.boost;
	}

	/**
	 * @param voltage - The amount of boost this component should contain.
	 */
	public void setBoost(double boost)
	{
		this.boost = boost;
	}

	/**
	 * @return The rate at which this source component will update its voltage.
	 */
	public int getSourceHertz()
	{
		return srcHertz;
	}

	/**
	 * @param hertz - The rate at which this source component should update its voltage.
	 */
	public void setSourceHertz(int hertz)
	{
		this.srcHertz = hertz;
	}

	/**
	 * @return The voltage this source component provides.
	 */
	public double getSourceVoltage()
	{
		return srcVoltage;
	}

	/**
	 * @return The Source Direction that a receiver can extract from
	 */
	public ForgeDirection getSourcePowerDirection()
	{
		return null;
	}

	/**
	 * @param srcVoltage - The voltage this source component should provide.
	 */
	public void setSourceVoltage(double srcVoltage)
	{
		this.srcVoltage = srcVoltage;
	}

	/**
	 * @return True if this is a source component.
	 */
	public boolean isSource()
	{
		return this.isSrc;
	}

	/**
	 * @param isSrc - Set true if this should be a source component.
	 */
	public void setIsSource(boolean isSrc)
	{
		this.isSrc = isSrc;
	}

	/**
	 * Updates the voltage of this component based on surrounding components.
	 */
	public void updateEnergyAsReceiver()
	{
		for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
		{
			TileEntity tile = this.worldObj.getTileEntity(this.xCoord + direction.offsetX, this.yCoord + direction.offsetY, this.zCoord + direction.offsetZ);

			if (tile != null && tile instanceof TileEntityElectrical)
			{
				TileEntityElectrical electrical = (TileEntityElectrical) tile;

				if (electrical instanceof IEnergyProvider)
				{
					IEnergyProvider provider = (IEnergyProvider) electrical;

					if (electrical.canProvideEnergyToReceiver(direction) && provider.canConnectEnergy(direction) && electrical.getVoltage() > this.getVoltage())
					{
						this.receiveEnergy(direction.getOpposite(), provider.extractEnergy(direction.getOpposite(), electrical.getVoltage() - this.getVoltage(), false), false);
					}
				}
			}
		}

		TileEntity surroundingTile = null;

		for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
		{
			TileEntity tile = this.worldObj.getTileEntity(this.xCoord + direction.offsetX, this.yCoord + direction.offsetY, this.zCoord + direction.offsetZ);

			if (tile != null && tile instanceof TileEntityElectrical)
			{
				TileEntityElectrical tee = (TileEntityElectrical) tile;

				if (tee.getBoost() == 0 && tee.getVoltage() > this.getVoltage() && tile instanceof IEnergyProvider)
				{
					surroundingTile = tile;
				}
				else if (tee.getVoltage() > 0 && tee.getBoost() != 0 && direction == tee.getSourcePowerDirection())
				{
					surroundingTile = tile;
				}
			}
		}

		if (surroundingTile == null || this.getVoltage() < 0)
		{
			this.setVoltage(0);
		}
	}

	/**
	 * Returns the amount of energy to be extracted from this component.
	 * 
	 * @param from - The direction this request was sent from.
	 * @param maxExtract - The amount of energy we're trying to extract.
	 * @param simulate - If true, this request will only be simulated.
	 * @return - The amount of energy to be extracted.
	 */
	public double extractEnergy(ForgeDirection from, double maxExtract, boolean simulate)
	{
		TileEntity tile = this.worldObj.getTileEntity(this.xCoord + from.offsetX, this.yCoord + from.offsetY, this.zCoord + from.offsetZ);

		if (tile != null && tile instanceof TileEntityElectrical)
		{
			return maxExtract - this.getResistance();
		}

		return 0;
	}

	/**
	 * Returns the amount of energy this component will contain after adding 
	 * the specified amount of energy.
	 * 
	 * @param from - The direction this request was sent from.
	 * @param maxReceive - The amount of energy this component is receiving.
	 * @param simulate - If true, this request will only be simulated.
	 * @return The amount of energy this component will contain after adding 
	 * the specified amount of energy.
	 */
	public double receiveEnergy(ForgeDirection from, double maxReceive, boolean simulate)
	{
		double result = this.getVoltage() + maxReceive;

		if (!simulate)
		{
			this.setVoltage(result);
		}

		return result;
	}
}
