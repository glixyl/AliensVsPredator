package com.arisux.avp.entities.tile;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.inventory.container.ContainerLocker;
import com.arisux.avp.items.ItemFirearm;
import com.arisux.avp.packets.client.PacketOpenLocker;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityLocker extends TileEntity
{
	public IInventory inventory;
	private ForgeDirection direction;
	public Container container;
	private boolean isOpen;

	public TileEntityLocker()
	{
		super();
		this.isOpen = false;
		final TileEntityLocker locker = this;
		this.inventory = new InventoryBasic("container.locker.slots", true, 64)
		{
			@Override
			public boolean isItemValidForSlot(int slot, ItemStack stack)
			{
				return locker instanceof TileEntityGunLocker ? (stack.getItem() instanceof ItemFirearm) : (true);
			}
		};
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

	public Container getNewContainer(EntityPlayer player)
	{
		return (container = new ContainerLocker(player, this));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		if (this.direction != null)
		{
			nbt.setInteger("Direction", this.direction.ordinal());
		}

		if(this.inventory != null)
		{
			this.saveInventoryToNBT(nbt, inventory);
		}
	}
	
	private void saveInventoryToNBT(NBTTagCompound nbt, IInventory inventory)
	{
		NBTTagList items = new NBTTagList();

		for (byte x = 0; x < inventory.getSizeInventory(); x++)
		{
			ItemStack stack = inventory.getStackInSlot(x);

			if (stack != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", x);
				stack.writeToNBT(item);
				items.appendTag(item);
			}
		}

		nbt.setTag(inventory.getInventoryName(), items);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		if (ForgeDirection.getOrientation(nbt.getInteger("Direction")) != null)
		{
			this.direction = ForgeDirection.getOrientation(nbt.getInteger("Direction"));
		}
		
		this.readInventoryFromNBT(nbt, this.inventory);
	}
	
	private void readInventoryFromNBT(NBTTagCompound nbt, IInventory inventory)
	{
		NBTTagList items = nbt.getTagList(inventory.getInventoryName(), Constants.NBT.TAG_COMPOUND);

		for (byte x = 0; x < items.tagCount(); x++)
		{
			NBTTagCompound item = items.getCompoundTagAt(x);

			byte slot = item.getByte("Slot");

			if (slot >= 0 && slot <= inventory.getSizeInventory())
			{
				inventory.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
	}
	
	public void openGui(EntityPlayer player)
	{
		if (!player.worldObj.isRemote)
		{
			FMLNetworkHandler.openGui(player, AliensVsPredator.instance(), AliensVsPredator.properties().GUI_LOCKER, player.worldObj, xCoord, yCoord, zCoord);
		}
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
	}

	public ForgeDirection getDirection()
	{
		return direction;
	}

	public void setDirection(ForgeDirection direction)
	{
		this.direction = direction;
	}

	public void setOpen(boolean isOpen)
	{
		this.isOpen = isOpen;
		
		if (!this.worldObj.isRemote)
		{
			AliensVsPredator.network().sendToAll(new PacketOpenLocker(isOpen, this.xCoord, this.yCoord, this.zCoord));
		}
	}
	
	public boolean isOpen()
	{
		return isOpen;
	}
}
