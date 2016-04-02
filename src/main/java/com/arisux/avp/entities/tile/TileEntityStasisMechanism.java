package com.arisux.avp.entities.tile;

import com.arisux.avp.entities.EntityMechanism;
import com.arisux.avp.items.ItemEntitySummoner;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityStasisMechanism extends TileEntity
{
	private int direction;
	public EntityMechanism dummyEntity;
	private Entity stasisEntity;
	public ItemStack itemstack;
	private String readOnlyDmmyEntityUUID;
	private int readOnlyStasisEntityID;

	public TileEntityStasisMechanism()
	{
		super();
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (this.dummyEntity == null && this.worldObj.getWorldTime() % 20 == 0)
		{
			this.dummyEntity = (EntityMechanism) getEntityForUUID(this.worldObj, this.readOnlyDmmyEntityUUID);
			
			if (this.dummyEntity != null)
			{
				this.stasisEntity = this.dummyEntity.riddenByEntity;
			}
		}
		
		if (this.dummyEntity == null && !this.worldObj.isRemote)
		{
			this.dummyEntity = new EntityMechanism(this.worldObj);
			this.dummyEntity.setLocationAndAngles(this.xCoord + 0.5, this.yCoord, this.zCoord + 0.5, 0, 0);
			this.worldObj.spawnEntityInWorld(this.dummyEntity);
		}
		
		if (this.dummyEntity != null && this.itemstack != null && this.stasisEntity == null && !this.worldObj.isRemote)
		{
			ItemEntitySummoner summoner = (ItemEntitySummoner) this.itemstack.getItem();
			this.stasisEntity = summoner.createNewEntity(this.worldObj);
			this.stasisEntity.setLocationAndAngles(this.xCoord + 0.5, this.yCoord, this.zCoord + 0.5, 0, 0);
			this.worldObj.spawnEntityInWorld(this.stasisEntity);
		}
		
		if (this.dummyEntity != null)
		{
			this.dummyEntity.setLocationAndAngles(this.xCoord + 0.5, this.yCoord, this.zCoord + 0.5, 0, 0);
			
			if (this.dummyEntity.riddenByEntity == null)
			{
				this.itemstack = null;
			}
			
			if (this.dummyEntity.riddenByEntity != null && this.dummyEntity.riddenByEntity instanceof EntityLivingBase)
			{
				((EntityLivingBase)this.dummyEntity.riddenByEntity).rotationYawHead = direction * 90;
			}
		}
		
		if (this.stasisEntity != null)
		{
			this.stasisEntity.mountEntity(this.dummyEntity);
		}
	}

	public void setDirection(byte direction)
	{
		this.direction = direction;
	}
	
	public int getDirection()
	{
		return direction;
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
		nbt.setInteger("Direction", this.direction);
		
		if (this.dummyEntity != null)
		{
			nbt.setString("DummyEntity", this.dummyEntity.getUniqueID().toString());
			
			if (this.dummyEntity.riddenByEntity != null)
			{
				nbt.setInteger("StasisEntity", this.dummyEntity.riddenByEntity.getEntityId());
			}
		}

		if (this.itemstack != null)
		{
			NBTTagCompound nbtStack = new NBTTagCompound();
			this.itemstack.writeToNBT(nbtStack);
			nbt.setTag("StasisItemstack", nbtStack);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.direction = nbt.getInteger("Direction");
		this.readOnlyDmmyEntityUUID = nbt.getString("DummyEntity");
		this.readOnlyStasisEntityID = nbt.getInteger("StasisEntity");
		this.itemstack = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("StasisItemstack"));
	}
	
	public static Entity getEntityForUUID(World world, String uuid)
	{
		for (Object o : world.loadedEntityList)
		{
			Entity e = (Entity) o;
			
			if (e.getUniqueID().toString().equals(uuid))
			{
				return e;
			}
		}
		
		return null;
	}
	
	public Entity getStasisEntity()
	{
		return stasisEntity;
	}
	
	public String getReadOnlyDmmyEntityUUID()
	{
		return readOnlyDmmyEntityUUID;
	}
	
	public int getReadOnlyStasisEntityID()
	{
		return readOnlyStasisEntityID;
	}
}
