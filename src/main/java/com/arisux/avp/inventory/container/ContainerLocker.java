package com.arisux.avp.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.arisux.avp.entities.tile.TileEntityLocker;

public class ContainerLocker extends Container
{
	public IInventory inventory;
	public EntityPlayer player;
	public TileEntityLocker locker;
	
	public ContainerLocker(EntityPlayer player, TileEntityLocker locker)
	{
		this.inventory = locker.inventory;
		this.player = player;
		this.locker = locker;
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
	}

	@Override
	public void onCraftMatrixChanged(IInventory iinventory)
	{
		super.onCraftMatrixChanged(iinventory);
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);
		
		this.locker.inventory = this.inventory;
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
			}
			else if (i != 36 || !this.mergeItemStack(stack, 36, 36 + (this.inventory.getSizeInventory() - 1), false))
			{
				return null;

			}
			else
			{
				return null;
			}

			if (stack.stackSize == 0)
			{
				slot.putStack(null);
			}
			else
			{
				slot.onSlotChanged();
			}

			slot.onPickupFromSlot(null, stack);

			return result;
		}

		return null;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
}
