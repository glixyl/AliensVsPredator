package com.arisux.avp.entities.tile;

import java.util.ArrayList;
import java.util.List;

import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntitySolarPanel extends TileEntity implements IEnergyProvider
{	
	public int rotation;
	public int voltage;
	
	@Override
	public void updateEntity()
	{
		if(this.canOutputPower())
		{
			pushEnergy();
		}
	}
	
	public void setDirection(byte direction)
	{
		this.rotation = direction;
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return false;
	}

	protected void pushEnergy()
    {
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
        {
            TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
            if (tile instanceof IEnergyReceiver)
            {
                IEnergyReceiver ier = (IEnergyReceiver) tile;
                ier.receiveEnergy(dir, 120, false);
            }
        }
    }
	
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		if(maxExtract < this.voltage)
		{
			return maxExtract;
		}
		else
		{
			return this.voltage;
		}
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return this.voltage;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return 10000;
	}
	
	public boolean canOutputPower()
	{
		World world = this.getWorldObj();
		if(world.getWorldTime() < 12300 || world.getWorldTime()  > 23850){
			return true;
		}
		return false;
	}
}
