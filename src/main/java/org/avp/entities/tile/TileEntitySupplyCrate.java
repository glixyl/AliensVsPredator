package org.avp.entities.tile;

import org.avp.AliensVsPredator;
import org.avp.inventory.container.ContainerSupplyCrate;
import org.avp.packets.client.PacketOpenable;
import org.avp.util.IOpenable;

import com.arisux.airi.lib.interfaces.IRotatable;

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

public class TileEntitySupplyCrate extends TileEntity implements IOpenable, IRotatable
{
	public IInventory inventory;
	private ForgeDirection direction;
	public Container container;
	private boolean isOpen;

	public TileEntitySupplyCrate()
	{
		super();
		this.isOpen = false;
		this.inventory = new InventoryBasic("container.supplycrate.slots", true, 64);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		if (this.direction != null)
		{
			nbt.setInteger("Direction", this.direction.ordinal());
		}

		if (this.inventory != null)
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
			FMLNetworkHandler.openGui(player, AliensVsPredator.instance(), AliensVsPredator.properties().GUI_SUPPLYCRATE, player.worldObj, xCoord, yCoord, zCoord);
		}
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
	}

	public Container getNewContainer(EntityPlayer player)
	{
		return (container = new ContainerSupplyCrate(player, this));
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		readFromNBT(packet.getNbtCompound());
	}

	@Override
	public ForgeDirection getDirection()
	{
		return direction;
	}

	@Override
	public void setDirection(ForgeDirection direction)
	{
		this.direction = direction;
	}

	@Override
	public void setOpen(boolean isOpen)
	{
		this.isOpen = isOpen;

		if (!this.worldObj.isRemote)
		{
			AliensVsPredator.network().sendToAll(new PacketOpenable(isOpen, this.xCoord, this.yCoord, this.zCoord));
		}
	}

	@Override
	public boolean isOpen()
	{
		return isOpen;
	}
}
