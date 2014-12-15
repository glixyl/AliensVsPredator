package com.arisux.avp.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class ContainerWristbracer extends Container
{
	public IInventory inventory;
	public ItemStack stack;
	public EntityPlayer player;

	public ContainerWristbracer(EntityPlayer player)
	{
		this.inventory = new InventoryBasic("container.wristbracer.slots", true, 5);
		this.stack = player.getCurrentEquippedItem();
		this.player = player;
		this.initialize();
	}

	public void initialize()
	{
		for (byte x = 0; x < this.inventory.getSizeInventory(); x++)
		{
			addSlotToContainer(new Slot(inventory, x, 31 + (28 * x), 16));
		}
		
		for (byte x = 0; x < 9; x++)
		{
			addSlotToContainer(new Slot(player.inventory, x, 15 + (18 * x), 136));
		}

		this.loadFromNBT();
	}

	public NBTTagCompound saveToNBT()
	{
		NBTTagCompound nbt = player.getCurrentEquippedItem().getTagCompound();
		NBTTagList items = new NBTTagList();
		
		if (nbt == null)
		{
			nbt = new NBTTagCompound();
		}

		for (byte x = 0; x < this.inventory.getSizeInventory(); x++)
		{
			ItemStack stackSlot = this.inventory.getStackInSlot(x);

			if (stackSlot != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", x);
				stackSlot.writeToNBT(item);
				items.appendTag(item);
			}
		}

		nbt.setTag("Items", items);
		player.getCurrentEquippedItem().setTagCompound(nbt);
		return nbt;
	}

	public NBTTagCompound loadFromNBT()
	{
		if (stack != null && stack.getTagCompound() != null)
		{
			NBTTagList items = stack.getTagCompound().getTagList("Items", Constants.NBT.TAG_COMPOUND);

			for (byte x = 0; x < items.tagCount(); x++)
			{
				NBTTagCompound item = items.getCompoundTagAt(x);

				byte slot = item.getByte("Slot");

				if (slot >= 0 && slot <= this.inventory.getSizeInventory())
				{
					this.inventory.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
				}
			}
		}

		return stack.getTagCompound();
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i)
	{
		Slot slot = getSlot(i);

		if (slot != null && slot.getHasStack())
		{

			ItemStack stack = slot.getStack();
			ItemStack result = stack.copy();

			if (i >= 36)
			{
				if (!super.mergeItemStack(stack, 0, 36, false))
				{
					return null;

				}
			} else if (i != 36 || !this.mergeItemStack(stack, 36, 36 + (this.inventory.getSizeInventory() - 1), false))
			{
				return null;

			} else
			{
				return null;
			}

			if (stack.stackSize == 0)
			{
				slot.putStack(null);
			} else
			{
				slot.onSlotChanged();
			}

			slot.onPickupFromSlot(null, stack);

			return result;
		}

		return null;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player)
	{
		super.onContainerClosed(player);
		this.saveToNBT();
	}
}
