package com.arisux.avp.entities.tile;

import java.util.ArrayList;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.interfaces.energy.IEnergyReceiver;
import com.arisux.avp.packets.client.PacketOpenBlastdoor;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityBlastdoor extends TileEntityElectrical implements IEnergyReceiver
{
	private ForgeDirection direction;
	private float doorProgress;
	private boolean doorOpen;
	private boolean setupParent;
	private TileEntityBlastdoor parent;
	private ArrayList<TileEntityBlastdoor> children;
	private int ticksExisted;

	public TileEntityBlastdoor()
	{
		super(false);
		this.children = new ArrayList<TileEntityBlastdoor>();
	}

	public void addToParent(TileEntityBlastdoor parent)
	{
		if (!parent.getChildren().contains(this))
		{
			parent.getChildren().add(this);
		}

		this.setParent(parent);
	}

	public ArrayList<TileEntityBlastdoor> getChildren()
	{
		return this.children;
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

		if (this.direction != null)
		{
			nbt.setInteger("Direction", this.direction.ordinal());
		}

		nbt.setBoolean("DoorOpen", this.isDoorOpen());
		nbt.setFloat("DoorProgress", this.doorProgress);
		nbt.setBoolean("Parent", this.isParent());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		if (ForgeDirection.getOrientation(nbt.getInteger("Direction")) != null)
		{
			this.direction = ForgeDirection.getOrientation(nbt.getInteger("Direction"));
		}

		this.doorProgress = nbt.getFloat("DoorProgress");
		setupParent = nbt.getBoolean("Parent");

		if (setupParent)
		{
			this.setDoorOpen(nbt.getBoolean("DoorOpen"));
		}
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		this.updateEnergyAsReceiver();
		this.ticksExisted++;

		if (this.setupParent && this.ticksExisted > 1)
		{
			this.setup();
			this.setupParent = false;
		}

		if (!this.isDoorOpen())
		{
			this.doorProgress = this.doorProgress > 0.0F ? this.doorProgress - 0.02F : this.doorProgress;
		}

		if (this.isDoorOpen())
		{
			this.doorProgress = this.doorProgress < 1.0F ? this.doorProgress + 0.02F : this.doorProgress;
		}

		if (!this.isDoorOpen())
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
		return this.isChild() ? (this.getParent() != null ? this.getParent().isDoorOpen() : false) : doorOpen;
	}
	
	public void setDoorOpen(boolean doorOpen)
	{
		this.setDoorOpen(doorOpen, true);
	}

	public void setDoorOpen(boolean doorOpen, boolean sendPacket)
	{
		if (this.isChild())
		{
			this.getParent().setDoorOpen(doorOpen);
		}
		else if (this.isParent())
		{
			this.doorOpen = doorOpen;
			
			if (this.worldObj.isRemote && sendPacket)
			{
				System.out.println("send packet");
				AliensVsPredator.network().sendToAll(new PacketOpenBlastdoor(doorOpen, this.xCoord, this.yCoord, this.zCoord));
			}
		}
	}

	@Override
	public Block getBlockType()
	{
		return Blocks.beacon;
	}

	public float getDoorProgress()
	{
		return this.doorProgress;
	}

	public boolean setChildTile(int posX, int posY, int posZ)
	{
		if (worldObj.getBlock(posX, posY, posZ) != Blocks.air)
		{
			return false;
		}

		worldObj.setBlock(posX, posY, posZ, AliensVsPredator.blocks().blockBlastdoor);
		TileEntityBlastdoor blastdoor = (TileEntityBlastdoor) worldObj.getTileEntity(posX, posY, posZ);

		if (blastdoor == null)
		{
			return false;
		}
		
		if (blastdoor != null)
		{
			blastdoor.addToParent(this);
			blastdoor.setParent(this);
		}

		return true;
	}

	public void breakChildren()
	{
		for (TileEntityBlastdoor child : this.getChildren())
		{
			worldObj.setBlockToAir(child.xCoord, child.yCoord, child.zCoord);
		}
	}

	public boolean isChild()
	{
		return this.getParent() != null;
	}

	public boolean isParent()
	{
		return !this.isChild();
	}

	public TileEntityBlastdoor getParent()
	{
		return parent;
	}

	public void setParent(TileEntityBlastdoor parent)
	{
		this.parent = parent;
	}

	public boolean setup()
	{
		ForgeDirection direction = this.direction;

		if (direction != null)
		{
			if (direction == ForgeDirection.NORTH || direction == ForgeDirection.SOUTH)
			{
				return this.setChildTile(this.xCoord + 1, this.yCoord, this.zCoord) && this.setChildTile(this.xCoord + 2, this.yCoord, this.zCoord) && this.setChildTile(this.xCoord + 3, this.yCoord, this.zCoord) && this.setChildTile(this.xCoord, this.yCoord + 1, this.zCoord) && this.setChildTile(this.xCoord, this.yCoord + 2, this.zCoord) && this.setChildTile(this.xCoord + 1, this.yCoord + 2, this.zCoord) && this.setChildTile(this.xCoord + 1, this.yCoord + 1, this.zCoord) && this.setChildTile(this.xCoord + 2, this.yCoord + 2, this.zCoord) && this.setChildTile(this.xCoord + 2, this.yCoord + 1, this.zCoord) && this.setChildTile(this.xCoord + 3, this.yCoord + 2, this.zCoord) && this.setChildTile(this.xCoord + 3, this.yCoord + 1, this.zCoord);
			}

			if (direction == ForgeDirection.WEST || direction == ForgeDirection.EAST)
			{
				return this.setChildTile(this.xCoord, this.yCoord, this.zCoord - 1) && this.setChildTile(this.xCoord, this.yCoord, this.zCoord - 2) && this.setChildTile(this.xCoord, this.yCoord, this.zCoord - 3) && this.setChildTile(this.xCoord, this.yCoord + 1, this.zCoord) && this.setChildTile(this.xCoord, this.yCoord + 2, this.zCoord) && this.setChildTile(this.xCoord, this.yCoord + 2, this.zCoord - 1) && this.setChildTile(this.xCoord, this.yCoord + 1, this.zCoord - 1) && this.setChildTile(this.xCoord, this.yCoord + 2, this.zCoord - 2) && this.setChildTile(this.xCoord, this.yCoord + 1, this.zCoord - 2) && this.setChildTile(this.xCoord, this.yCoord + 2, this.zCoord - 3) && this.setChildTile(this.xCoord, this.yCoord + 1, this.zCoord - 3);
			}
		}
		
		return false;
	}

	public ForgeDirection getDirection()
	{
		return direction;
	}

	public void setDirection(ForgeDirection direction)
	{
		this.direction = direction;
	}
}
