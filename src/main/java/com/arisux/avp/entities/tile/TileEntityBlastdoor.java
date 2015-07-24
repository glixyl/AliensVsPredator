package com.arisux.avp.entities.tile;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.block.GhostBlock;
import com.arisux.avp.interfaces.energy.IEnergyReceiver;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityBlastdoor extends TileEntityElectrical implements IEnergyReceiver
{
	public TileEntityBlastdoor()
	{
		super(false);
	}

	private float doorProgress;
	private boolean doorOpen;
	public ForgeDirection direction;

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
		
		if (this.direction != null)
		{
			nbt.setInteger("Direction", this.direction.ordinal());
		}

		nbt.setBoolean("DoorOpen", this.doorOpen);
		nbt.setFloat("DoorProgress", this.doorProgress);

	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		if (ForgeDirection.getOrientation(nbt.getInteger("Direction")) != null)
		{
			this.direction = ForgeDirection.getOrientation(nbt.getInteger("Direction"));
		}
		
		this.doorOpen = nbt.getBoolean("DoorOpen");
		this.doorProgress = nbt.getFloat("DoorProgress");
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		this.updateEnergyAsReceiver();
		
		World world = this.getWorldObj();
		Block parentBlock = world.getBlock(this.xCoord, this.yCoord, this.zCoord);
		
		if (!this.isOperational())
		{
			this.setDoorOpen(false);
		}

		if (!this.doorOpen)
		{
			if (direction != null)
			{
				GhostBlock block = (GhostBlock) AliensVsPredator.blocks().ghostBlockBlastdoor;
				block.parentBlock = parentBlock;
				block.parentTileEntity = this;

				if (direction == ForgeDirection.NORTH || direction == ForgeDirection.SOUTH)
				{
					world.setBlock(this.xCoord + 1, this.yCoord, this.zCoord, block);
					world.setBlock(this.xCoord + 2, this.yCoord, this.zCoord, block);
					world.setBlock(this.xCoord + 3, this.yCoord, this.zCoord, block);
					world.setBlock(this.xCoord, this.yCoord + 1, this.zCoord, block);
					world.setBlock(this.xCoord, this.yCoord + 2, this.zCoord, block);
					world.setBlock(this.xCoord + 1, this.yCoord + 2, this.zCoord, block);
					world.setBlock(this.xCoord + 1, this.yCoord + 1, this.zCoord, block);
					world.setBlock(this.xCoord + 2, this.yCoord + 2, this.zCoord, block);
					world.setBlock(this.xCoord + 2, this.yCoord + 1, this.zCoord, block);
					world.setBlock(this.xCoord + 3, this.yCoord + 2, this.zCoord, block);
					world.setBlock(this.xCoord + 3, this.yCoord + 1, this.zCoord, block);
				}

				if (direction == ForgeDirection.WEST || direction == ForgeDirection.EAST)
				{
					world.setBlock(this.xCoord, this.yCoord, this.zCoord - 1, block);
					world.setBlock(this.xCoord, this.yCoord, this.zCoord - 2, block);
					world.setBlock(this.xCoord, this.yCoord, this.zCoord - 3, block);
					world.setBlock(this.xCoord, this.yCoord + 1, this.zCoord, block);
					world.setBlock(this.xCoord, this.yCoord + 2, this.zCoord, block);
					world.setBlock(this.xCoord, this.yCoord + 2, this.zCoord - 1, block);
					world.setBlock(this.xCoord, this.yCoord + 1, this.zCoord - 1, block);
					world.setBlock(this.xCoord, this.yCoord + 2, this.zCoord - 2, block);
					world.setBlock(this.xCoord, this.yCoord + 1, this.zCoord - 2, block);
					world.setBlock(this.xCoord, this.yCoord + 2, this.zCoord - 3, block);
					world.setBlock(this.xCoord, this.yCoord + 1, this.zCoord - 3, block);
				}
			}

			this.doorProgress = this.doorProgress > 0.0F ? this.doorProgress - 0.02F : this.doorProgress;
		}

		if (this.doorOpen)
		{
			this.doorProgress = this.doorProgress < 1.0F ? this.doorProgress + 0.02F : this.doorProgress;
		}

		if (!this.doorOpen)
		{
			this.doorProgress = this.doorProgress > 0.0F ? this.doorProgress - 0.02F : this.doorProgress;
		}
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public double getEnergyStored(ForgeDirection from)
	{
		return this.getVoltage();
	}

	@Override
	public double getMaxEnergyStored(ForgeDirection from)
	{
		return 220;
	}

	public boolean isDoorOpen()
	{
		return doorOpen;
	}

	public void setDoorOpen(boolean doorOpen)
	{
		this.doorOpen = doorOpen;
	}

	@Override
	public Block getBlockType()
	{
		return Blocks.beacon;
	}

	public float getDoorProgress()
	{
		return doorProgress;
	}
}
