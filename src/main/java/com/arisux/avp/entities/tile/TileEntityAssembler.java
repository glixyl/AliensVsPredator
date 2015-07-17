package com.arisux.avp.entities.tile;

import java.util.Random;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.inventory.container.ContainerAssembler;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.Constants;

public class TileEntityAssembler extends TileEntity implements IInventory
{
	private Item randomItem = AliensVsPredator.items().helmTitanium;
	private ItemStack[] items = new ItemStack[28];
	private ItemStack previousTickStack;
	public int clickAmount = 0;
	public EntityPlayer player;
	private ContainerAssembler container;

	public TileEntityAssembler()
	{
		;
	}

	@Override
	public int getSizeInventory()
	{
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return items[i];
	}

	@Override
	public void updateEntity()
	{
		if (this.getWorldObj().getWorldTime() % 20 == 0)
		{
			Random rand = new Random();

			if (rand.nextInt(3) == 0)
			{
				this.randomItem = AliensVsPredator.assembler().getSchematicRegistry().get(rand.nextInt(AliensVsPredator.assembler().getSchematicRegistry().size())).getItemStackAssembled().getItem();
			}
		}

		if (previousTickStack != getStackInSlot(27) && getStackInSlot(27) != null)
		{
			setInventorySlotContents(26, null);
		}

		if (clickAmount >= 10)
		{
			this.shiftCraftResult();
			clickAmount = 0;
		}

		previousTickStack = getStackInSlot(27);
	}

	private void shiftCraftResult()
	{
		if (getStackInSlot(26) == null && getStackInSlot(27) != null)
		{
			this.setInventorySlotContents(26, this.getStackInSlot(27));
			this.setInventorySlotContents(27, (ItemStack) null);
		}
	}

	@Override
	public ItemStack decrStackSize(int i, int count)
	{
		ItemStack stack = this.getStackInSlot(i);

		if (stack != null)
		{
			if (stack.stackSize <= count)
			{
				setInventorySlotContents(i, null);
			}
			else
			{
				stack = stack.splitStack(count);
				markDirty();
			}
		}

		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		ItemStack stack = getStackInSlot(i);
		setInventorySlotContents(i, null);

		return stack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack)
	{
		items[i] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
		{
			stack.stackSize = getInventoryStackLimit();
		}

		super.markDirty();
	}

	@Override
	public String getInventoryName()
	{
		return "ElectricalWorkbench";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
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

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		if (slot == 25)
		{
			return TileEntityFurnace.getItemBurnTime(stack) > 313;

		}
		else if (slot == 26 || slot == 27)
		{

			return false;

		}
		else
		{

			return true;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		NBTTagList items = new NBTTagList();

		for (byte x = 0; x < this.getSizeInventory(); x++)
		{

			ItemStack stack = this.getStackInSlot(x);

			if (stack != null)
			{

				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", x);
				stack.writeToNBT(item);
				items.appendTag(item);
			}
		}

		compound.setTag("Items", items);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		NBTTagList items = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);

		for (byte x = 0; x < items.tagCount(); x++)
		{
			NBTTagCompound item = items.getCompoundTagAt(x);

			byte slot = item.getByte("Slot");

			if (slot >= 0 && slot <= this.getSizeInventory())
			{
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
	}

	public ContainerAssembler getNewContainer(EntityPlayer player)
	{
		this.player = player;
		return (container = new ContainerAssembler(player.inventory, this, xCoord, yCoord, zCoord));
	}

	public ContainerAssembler getCurrentContainer()
	{
		return container;
	}

	@Override
	public boolean canUpdate()
	{
		return true;
	}

	public void openGui(EntityPlayer player)
	{
		this.player = player;

		if (!player.worldObj.isRemote)
			FMLNetworkHandler.openGui(player, AliensVsPredator.instance(), AliensVsPredator.properties().GUI_ASSEMBLER, player.worldObj, xCoord, yCoord, zCoord);
	}

	@Override
	public Block getBlockType()
	{
		return Blocks.beacon;
	}

	public Item getRandomItem()
	{
		return this.randomItem;
	}
}
