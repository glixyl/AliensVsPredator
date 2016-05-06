package com.arisux.avp.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryCustomPlayer implements IInventory
{
    private final String name = "Inventory";
    private final String tagName = "InventoryNBTTagName";
    public static final int INV_SIZE = 2;
    private ItemStack[] inventory = new ItemStack[INV_SIZE];

    public InventoryCustomPlayer()
    {
        ;
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null)
        {
            if (stack.stackSize > amount)
            {
                stack = stack.splitStack(amount);
                this.onInventoryChanged();
            }
            else
            {
                setInventorySlotContents(slot, null);
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack stack = getStackInSlot(slot);
        setInventorySlotContents(slot, null);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack)
    {
        this.inventory[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    public String getInvName()
    {
        return name;
    }

    public boolean isInvNameLocalized()
    {
        return name.length() > 0;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    public void onInventoryChanged()
    {
        for (int i = 0; i < this.getSizeInventory(); ++i)
        {
            if (this.getStackInSlot(i) != null && this.getStackInSlot(i).stackSize == 0)
                this.setInventorySlotContents(i, null);
        }
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    public void openChest()
    {
    }

    public void closeChest()
    {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack)
    {
        return itemstack.getItem() instanceof Item;
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        NBTTagList items = new NBTTagList();

        for (int i = 0; i < getSizeInventory(); ++i)
        {
            if (getStackInSlot(i) != null)
            {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("Slot", (byte) i);
                getStackInSlot(i).writeToNBT(item);
                items.appendTag(item);
            }
        }

        compound.setTag(tagName, items);
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        NBTTagList items = compound.getTagList(tagName, 0);

        for (int i = 0; i < items.tagCount(); ++i)
        {
            NBTTagCompound item = (NBTTagCompound) items.getCompoundTagAt(i);
            byte slot = item.getByte("Slot");

            if (slot >= 0 && slot < getSizeInventory())
            {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
            }
        }
    }

    @Override
    public String getInventoryName()
    {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public void markDirty()
    {
        ;
    }

    @Override
    public void openInventory()
    {
        ;
    }

    @Override
    public void closeInventory()
    {
        ;
    }
}
