package com.arisux.avp.entities.tile;

import com.arisux.avp.entities.EntityMechanism;
import com.arisux.avp.items.ItemEntitySummoner;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStasisMechanism extends TileEntity
{
	public int rotation;
	public Entity stasisEntity;
	public ItemStack stasisItemstack;
	public EntityMechanism mechanism;

	public TileEntityStasisMechanism()
	{
		super();
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		if (this.mechanism == null)
		{
			if (!this.worldObj.isRemote)
			{
				this.mechanism = new EntityMechanism(this.worldObj);
				this.mechanism.setLocationAndAngles(this.xCoord + 0.5, this.yCoord, this.zCoord + 0.5, this.rotation * 90, 0);
				this.worldObj.spawnEntityInWorld(this.mechanism);
			}
		}

		if (stasisEntity != null && mechanism != null)
		{
			mechanism.setLocationAndAngles(this.xCoord + 0.5, this.yCoord, this.zCoord + 0.5, 0, 0);
			
			if (stasisEntity.ridingEntity == null)
			{
				stasisEntity.mountEntity(mechanism);
			}
//			System.out.println(this.mechanism.riddenByEntity);
		}
		
		// System.out.println((worldObj.isRemote ? "client" : "server") + ":" + this.stasisEntity);
	}

	public void setDirection(byte direction)
	{
		this.rotation = direction;
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
		this.stasisItemstack = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("StasisItemstack"));

		if (this.stasisEntity == null && this.stasisItemstack != null)
		{
			this.stasisEntity = ((ItemEntitySummoner) this.stasisItemstack.getItem()).createNewEntity(this.worldObj);

			if (this.stasisEntity != null && this.worldObj != null)
			{
				if (!this.worldObj.isRemote)
				{
					this.stasisEntity.setLocationAndAngles(this.xCoord, this.yCoord, this.zCoord, 0F, 0F);
					this.worldObj.spawnEntityInWorld(this.stasisEntity);
				}
			}
		}
	}
}
