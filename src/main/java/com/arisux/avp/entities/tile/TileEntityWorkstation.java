package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.INetworkDevice;

import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityWorkstation extends TileEntity implements INetworkDevice, IEnergyReceiver
{
	public int rotation;
	public int voltage;
	
	public TileEntityWorkstation()
	{
		;
	}

	@Override
	public void updateEntity()
	{
		if(!this.isAdjacentToPowerSource())
		{
			this.voltage = 0;
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
	}
	
	public void setDirection(byte direction)
	{
		this.rotation = direction;
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
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("Rotation", this.rotation);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.rotation = nbt.getInteger("Rotation");
	}

	@Override
	public void sendData()
	{
		;
	}

	@Override
	public void receiveData()
	{
		;
	}
	
	@Override
	public INetworkDevice getHostDevice()
	{
		return null;
	}

	@Override
	public String getChannel()
	{
		return "Default";
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return false;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,
			boolean simulate) {
		if(!simulate)
		{
			this.voltage = maxReceive;
		}
		return maxReceive;
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