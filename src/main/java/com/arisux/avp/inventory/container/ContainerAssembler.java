package com.arisux.avp.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

import com.arisux.avp.entities.tile.TileEntityAssembler;

public class ContainerAssembler extends Container
{
	public InventoryCrafting craftMatrix;
	public IInventory craftResult;
	private TileEntityAssembler tile;
	
	public ContainerAssembler(InventoryPlayer invPlayer, TileEntityAssembler assembler, int posX, int posY, int posZ)
	{
		this.tile = assembler;
		this.craftMatrix = new InventoryCrafting(this, 5, 5);
		this.craftResult = new InventoryCraftResult();
		
		/** Add the 9 hotbar slots to the container **/
		for (byte x = 0; x < 9; x++)
		{
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 178));
		}

		/** Add the 27 inventory slots to the container **/
		for (byte y = 0; y < 3; y++)
		{
			for (byte x = 0; x < 9; x++)
			{
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 120 + 18 * y));
			}
		}

		/** Add the 25 grid slots to the container **/
		for (byte x = 0; x < 5; x++)
		{
			for (byte y = 0; y < 5; y++)
			{
				this.addSlotToContainer(new Slot(craftMatrix, y + x * 5, 8 + x * 18, 17 + y * 18));
			}
		}

		/** Add the single output slot to the container **/
		addSlotToContainer(new SlotCrafting(invPlayer.player, craftMatrix, craftResult, 26, 140, 30));

		/**
		 * Get all itemstacks assigned to the container and place them
		 * into the 15 grid slots
		 **/
		for (byte x = 0; x < craftMatrix.getSizeInventory(); x++)
		{
			ItemStack stack = assembler.getStackInSlot(x);

			if (stack != null)
				craftMatrix.setInventorySlotContents(x, stack);
		}

		onCraftMatrixChanged(craftMatrix);
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

		for (int id = 0; id < 25; id++)
		{
			tile.setInventorySlotContents(id, craftMatrix.getStackInSlotOnClosing(id));
		}

		tile.setInventorySlotContents(27, craftResult.getStackInSlotOnClosing(0));
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
			} else if (i != 36 || !this.mergeItemStack(stack, 36, 36 + (tile.getSizeInventory() - 1), false))
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
	public boolean canInteractWith(EntityPlayer player)
	{
		return tile.isUseableByPlayer(player);
	}
}
