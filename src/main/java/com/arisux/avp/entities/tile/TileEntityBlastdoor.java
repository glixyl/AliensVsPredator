package com.arisux.avp.entities.tile;

import java.util.ArrayList;
import java.util.Random;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.interfaces.energy.IEnergyReceiver;
import com.arisux.avp.packets.client.PacketOpenBlastdoor;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityBlastdoor extends TileEntityElectrical implements IEnergyReceiver
{
	private ForgeDirection direction;
	private float doorProgress;
	private boolean doorOpen;
	private boolean isParent;
	private boolean placedByPlayer;
	private TileEntityBlastdoor parent;
	private ArrayList<TileEntityBlastdoor> children;
	private int ticksExisted;
	private String identifier;
	private String password;
	private long timeOfLastPry;

	public TileEntityBlastdoor()
	{
		super(false);
		this.children = new ArrayList<TileEntityBlastdoor>();
		this.identifier = "BD" + (1000 + new Random().nextInt(8999));
		this.password = "";
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

		nbt.setFloat("DoorProgress", this.doorProgress);
		nbt.setBoolean("DoorOpen", this.isDoorOpen());
		nbt.setLong("TimeOfLastPry", this.getTimeOfLastPry());
		nbt.setBoolean("Parent", this.isParent);
		
		if (!identifier.isEmpty())
		nbt.setString("Identifier", this.identifier);
		
		if (!password.isEmpty())
		nbt.setString("Password", this.password);
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
		this.isParent = nbt.getBoolean("Parent");
		this.setDoorOpen(nbt.getBoolean("DoorOpen"));
		this.timeOfLastPry = nbt.getLong("TimeOfLastPry");
		this.identifier = nbt.getString("Identifier");
		this.password = nbt.getString("Password");
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		this.updateEnergyAsReceiver();
		this.ticksExisted++;

		if (this.isParent && this.ticksExisted > 1 && !placedByPlayer)
		{
			this.setup(false);
		}

		if (this.isParent())
		{
			if (this.isDoorOpen())
			{
				this.doorProgress = this.doorProgress < getMaxDoorProgress() ? this.doorProgress + 0.02F : this.doorProgress;
			}

			if (!this.isDoorOpen() && !isBeingPryedOpen())
			{
				this.doorProgress = this.doorProgress > 0.0F ? this.doorProgress - 0.02F : this.doorProgress;
			}

			long timeSinceLastPry = (System.currentTimeMillis() - this.getTimeOfLastPry());

			if (this.timeOfLastPry != 0 && timeSinceLastPry > getDoorResealTime())
			{
				this.timeOfLastPry = 0;
			}

			if (isBeingPryedOpen() && this.doorProgress >= getMaxDoorPryProgress())
			{
				this.timeOfLastPry = 0;
				this.setDoorOpen(true);
			}
		}
	}

	public boolean isBeingPryedOpen()
	{
		return this.timeOfLastPry != 0;
	}

	public void setBeingPryedOpen(boolean beingPryedOpen)
	{
		if (beingPryedOpen)
		{
			this.timeOfLastPry = System.currentTimeMillis();
		}
	}

	public long getTimeOfLastPry()
	{
		return timeOfLastPry;
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
			if (this.getParent() != null)
			{
				this.getParent().setDoorOpen(doorOpen);
			}
		}
		else if (this.isParent())
		{
			this.doorOpen = doorOpen;

			if (this.worldObj != null && !this.worldObj.isRemote && sendPacket)
			{
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
		Block block = worldObj.getBlock(posX, posY, posZ);

		if (block != Blocks.air && block != AliensVsPredator.blocks().blockBlastdoor)
		{
			if (this.worldObj.isRemote)
			{
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Unable to place a blastdoor here. Blocks are in the way."));
			}

			return false;
		}

		worldObj.setBlock(posX, posY, posZ, AliensVsPredator.blocks().blockBlastdoor);
		TileEntityBlastdoor blastdoor = (TileEntityBlastdoor) worldObj.getTileEntity(posX, posY, posZ);

		if (blastdoor == null)
		{
			System.out.println("Blastdoor was null");
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
		return !this.isParent();
	}

	public boolean isParent()
	{
		return this.isParent;
	}

	public TileEntityBlastdoor getParent()
	{
		return parent;
	}

	public void setParent(TileEntityBlastdoor parent)
	{
		this.parent = parent;
	}

	public boolean setup(boolean placedByPlayer)
	{
		ForgeDirection direction = this.direction;

		this.isParent = true;
		this.placedByPlayer = true;

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

	public String getIdentifier()
	{
		return identifier;
	}

	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setDoorProgress(float doorProgress)
	{
		this.doorProgress = doorProgress;
	}

	public float getMaxDoorPryProgress()
	{
		return 0.4F;
	}

	public float getMaxDoorProgress()
	{
		return 1.0F;
	}
	
	public int getDoorResealTime()
	{
		return 600;
	}
}
