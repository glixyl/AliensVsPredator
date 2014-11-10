package com.arisux.avp.entities.tile;

import com.arisux.avp.interfaces.INetworkDevice;
import com.arisux.avp.interfaces.NetworkHolder;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityNetworkCable extends PoweredTileEntity implements INetworkDevice
{

	private NetworkHolder nwh = null;

	@Override
	public void onVoltageTick()
	{
		super.onVoltageTick();
		if(this.nwh == null) {
			TileEntity above = this.getWorldObj().getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
			TileEntity below = this.getWorldObj().getTileEntity(this.xCoord, this.yCoord -1, this.zCoord);
			TileEntity left = this.getWorldObj().getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
			TileEntity right = this.getWorldObj().getTileEntity(this.xCoord -1, this.yCoord, this.zCoord);
			TileEntity front = this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
			TileEntity behind = this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord -1);
			if(above instanceof INetworkDevice) {
				if(((INetworkDevice) above).getNetwork() != null) {
					this.nwh = ((INetworkDevice) above).getNetwork();
				}
			} else if(below instanceof INetworkDevice) {
				if(((INetworkDevice) below).getNetwork() != null) {
					this.nwh = ((INetworkDevice) below).getNetwork();
				}
			} else if(left instanceof INetworkDevice) {
				if(((INetworkDevice) left).getNetwork() != null) {
					this.nwh = ((INetworkDevice) left).getNetwork();
				}
			} else if(right instanceof INetworkDevice) {
				if(((INetworkDevice) right).getNetwork() != null) {
					this.nwh = ((INetworkDevice) right).getNetwork();
				}
			} else if(front instanceof INetworkDevice) {
				if(((INetworkDevice) front).getNetwork() != null) {
					this.nwh = ((INetworkDevice) front).getNetwork();
				}
			} else if(behind instanceof INetworkDevice) {
				if(((INetworkDevice) behind).getNetwork() != null) {
					this.nwh = ((INetworkDevice) behind).getNetwork();
				}
			}
		}
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
		this.readFromNBT(packet.func_148857_g());
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
	}

	@Override
	public double getMinOperatingVoltage()
	{
		return 0;
	}

	@Override
	public double getMaxOperatingVoltage()
	{
		return 10000;
	}

	@Override
	public double getMinOperatingAmps()
	{
		return 0;
	}

	@Override
	public double getMaxOperatingAmps()
	{
		return 1000;
	}

	@Override
	public double getResistance()
	{
		return 0.002;
	}

	@Override
	public boolean canOutputPower()
	{
		return false;
	}

	@Override
	public Block getBlockType()
	{
		return Blocks.beacon;
	}

	@Override
	public void sendData()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void receiveData()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public NetworkHolder getNetwork()
	{
		// TODO Auto-generated method stub
		return this.nwh;
	}
}
