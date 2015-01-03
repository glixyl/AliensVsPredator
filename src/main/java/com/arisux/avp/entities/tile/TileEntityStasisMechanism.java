package com.arisux.avp.entities.tile;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import com.arisux.avp.items.ItemEntitySummoner;

public class TileEntityStasisMechanism extends TileEntity
{
	public int rotation;
	public Entity stasisEntity;
	public ItemStack stasisItemstack;

	public TileEntityStasisMechanism()
	{
		;
	}

	public void setDirection(byte direction)
	{
		this.rotation = direction;
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (stasisEntity != null && worldObj.isRemote)
		{
			stasisEntity.onUpdate();
		}
	}
	
	@Override
	public Block getBlockType()
	{
		return Blocks.beacon;
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
		nbt.setInteger("Rotation", this.rotation);

		if (this.stasisItemstack != null)
		{
			NBTTagCompound nbtStack = new NBTTagCompound();
			this.stasisItemstack.writeToNBT(nbtStack);
			nbt.setTag("StasisItemstack", nbtStack);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.rotation = nbt.getInteger("Rotation");

		NBTTagCompound nbtStack = nbt.getCompoundTag("StasisItemstack");
		this.stasisItemstack = ItemStack.loadItemStackFromNBT(nbtStack);

		if (this.stasisEntity == null && this.stasisItemstack != null)
		{
			this.stasisEntity = ((ItemEntitySummoner) this.stasisItemstack.getItem()).createNewEntity(this.worldObj);
		}
	}
}