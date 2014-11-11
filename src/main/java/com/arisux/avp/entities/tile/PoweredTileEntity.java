package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.IPowerDevice;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public abstract class PoweredTileEntity extends TileEntity implements IPowerDevice
{
	public double voltage, amps;

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (this.isVoltageInOperatingRange())
		{
			this.onVoltageTick();
		}
		
		if (this.voltage > this.getMaxOperatingVoltage())
		{
			this.onOverloadTick();
		}
		
		if (this.voltage <= this.getMinOperatingVoltage())
		{
			this.onUnderloadTick();
		}
		
		if (this.getPowerSourceTile() == null)
		{
			this.voltage = 0;
		}
		
		if (this.canOutputPower())
		{
			this.outputPower();
		}
	}

	public void outputPower()
	{
		double voltageOut = this.getVoltageAfterApplyingResistance();
		
		if (this instanceof TileEntityPowerline)
		{
			if ((this + "").replace(this.getClass().getName(), "").equalsIgnoreCase("@2745889"))
			System.out.println(this.getVoltage() + "V");
		}

		if (getTop() != null && getTop().getPowerSourceTile() == this)
		{
			getTop().setVoltage(voltageOut);
		}
		if (getBottom() != null && getBottom().getPowerSourceTile() == this)
		{
			getBottom().setVoltage(voltageOut);
		}
		if (getFront() != null && getFront().getPowerSourceTile() == this)
		{
			getFront().setVoltage(voltageOut);
		}
		if (getBack() != null && getBack().getPowerSourceTile() == this)
		{
			getBack().setVoltage(voltageOut);
		}
		if (getLeft() != null && getLeft().getPowerSourceTile() == this)
		{
			getLeft().setVoltage(voltageOut);
		}
		if (getRight() != null && getRight().getPowerSourceTile() == this)
		{
			getRight().setVoltage(voltageOut);
		}
	}

	@Override
	public abstract void onVoltageTick();

	@Override
	public abstract void onOverloadTick();

	@Override
	public abstract void onUnderloadTick();

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

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		tag.setDouble("Volts", this.voltage);
		tag.setDouble("Amps", this.amps);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		this.voltage = tag.getDouble("Volts");
		this.amps = tag.getDouble("Amps");
	}

	public void setVoltage(double voltageIn)
	{
		this.voltage = voltageIn;
	}

	public double getVoltageAfterApplyingResistance()
	{
		return this.getVoltage() - this.getResistance() <= 0 ? 0 : this.getVoltage() - this.getResistance();
	}

	public void setAmps(double ampsIn)
	{
		this.amps = ampsIn;
	}

	public PoweredTileEntity getPoweredTileAt(int x, int y, int z)
	{
		return this.worldObj.getTileEntity(x, y, z) instanceof PoweredTileEntity ? (PoweredTileEntity) this.worldObj.getTileEntity(x, y, z) : null;
	}

	public PoweredTileEntity getPowerSourceTile()
	{
		if (getTop() != null && getTop().isOutputVoltageHigherThan(this))
		{
			return getTop();
		}
		if (getBottom() != null && getBottom().isOutputVoltageHigherThan(this))
		{
			return getBottom();
		}
		if (getFront() != null && getFront().isOutputVoltageHigherThan(this))
		{
			return getFront();
		}
		if (getBack() != null && getBack().isOutputVoltageHigherThan(this))
		{
			return getBack();
		}
		if (getLeft() != null && getLeft().isOutputVoltageHigherThan(this))
		{
			return getLeft();
		}
		if (getRight() != null && getRight().isOutputVoltageHigherThan(this))
		{
			return getRight();
		}

		return null;
	}
	
	public boolean isVoltageInOperatingRange()
	{
		return this.getVoltage() < this.getMaxOperatingVoltage() && this.getVoltage() > this.getMinOperatingVoltage();
	}

	public boolean isPowered()
	{
		return this.getVoltage() != 0;
	}

	public boolean isOutputVoltageLessThan(PoweredTileEntity device)
	{
		return this.getVoltage() < device.getVoltage();
	}

	public boolean isOutputVoltageHigherThan(PoweredTileEntity device)
	{
		return this.getVoltage() > device.getVoltage();
	}

	public PoweredTileEntity getTop()
	{
		return this.getPoweredTileAt(this.xCoord, this.yCoord + 1, this.zCoord);
	}

	public PoweredTileEntity getBottom()
	{
		return this.getPoweredTileAt(this.xCoord, this.yCoord - 1, this.zCoord);
	}

	public PoweredTileEntity getFront()
	{
		return this.getPoweredTileAt(this.xCoord, this.yCoord, this.zCoord - 1);
	}

	public PoweredTileEntity getBack()
	{
		return this.getPoweredTileAt(this.xCoord, this.yCoord, this.zCoord + 1);
	}

	public PoweredTileEntity getRight()
	{
		return this.getPoweredTileAt(this.xCoord - 1, this.yCoord, this.zCoord);
	}

	public PoweredTileEntity getLeft()
	{
		return this.getPoweredTileAt(this.xCoord + 1, this.yCoord, this.zCoord);
	}

	@Override
	public double getVoltage()
	{
		return this.voltage;
	}

	@Override
	public double getMaxOperatingVoltage()
	{
		return 140;
	}

	@Override
	public double getMinOperatingVoltage()
	{
		return 110;
	}

	@Override
	public double getAmperage()
	{
		return this.amps;
	}

	@Override
	public double getMaxOperatingAmps()
	{
		return 4;
	}

	@Override
	public double getMinOperatingAmps()
	{
		return 1;
	}

	@Override
	public double getResistance()
	{
		return 0.1;
	}

	@Override
	public boolean canOutputPower()
	{
		return false;
	}
}
