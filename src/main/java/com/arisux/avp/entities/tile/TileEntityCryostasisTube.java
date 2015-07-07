package com.arisux.avp.entities.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;

import com.arisux.avp.items.ItemEntitySummoner;

public class TileEntityCryostasisTube extends PoweredTileEntity
{
	public int rotation;
	public Entity stasisEntity;
	public ItemStack stasisItemstack;
	private boolean cracked;
	private boolean shattered;

	public TileEntityCryostasisTube()
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

		if (this.stasisEntity != null && !this.isPowered())
		{
			if (this.worldObj.getWorldTime() % 100 == 0)
			{
				if (this.worldObj.rand.nextInt(8) == 0)
				{
					if (this.isCracked())
					{
						this.setShattered(true);
					}

					this.setCracked(true);
				}
			}
		}

		if (this.stasisItemstack != null)
		{
			ItemEntitySummoner item = (ItemEntitySummoner) this.stasisItemstack.getItem();
			Entity entity = item.createNewEntity(worldObj);

			if (entity != null)
			{
				if (this.isShattered())
				{
					if (!this.worldObj.isRemote)
					{
						entity.setLocationAndAngles(this.xCoord, this.yCoord, this.zCoord, 0F, 0F);
						this.worldObj.spawnEntityInWorld(entity);
					}

					this.stasisItemstack = null;
					this.stasisEntity = null;
				}
			}
			else
			{
				this.stasisEntity = ((ItemEntitySummoner) this.stasisItemstack.getItem()).createNewEntity(this.worldObj);
			}
		}

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
		nbt.setBoolean("Cracked", this.cracked);
		nbt.setBoolean("Shattered", this.shattered);

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
		this.cracked = nbt.getBoolean("Cracked");
		this.shattered = nbt.getBoolean("Shattered");

		NBTTagCompound nbtStack = nbt.getCompoundTag("StasisItemstack");
		this.stasisItemstack = ItemStack.loadItemStackFromNBT(nbtStack);
		
		if (this.stasisEntity == null && this.stasisItemstack != null)
		{
			this.stasisEntity = ((ItemEntitySummoner) this.stasisItemstack.getItem()).createNewEntity(this.worldObj);
		}
	}

	public void setCracked(boolean cracked)
	{
		this.cracked = cracked;
	}

	public void setShattered(boolean shattered)
	{
		this.shattered = shattered;
	}

	public boolean isCracked()
	{
		return this.cracked;
	}

	public boolean isShattered()
	{
		return this.shattered;
	}

	@Override
	public void onVoltageTick()
	{
		;
	}

	@Override
	public void onOverloadTick()
	{
		Explosion explosion = new Explosion(this.worldObj, null, this.xCoord, this.yCoord, this.zCoord, 1);
		explosion.doExplosionA();
		explosion.doExplosionB(true);
		this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, Blocks.fire);
	}

	@Override
	public void onUnderloadTick()
	{
		;
	}

	@Override
	public boolean isOriginalPowerSourceAttached() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TileEntityRepulsionGenerator getPowerSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getOriginalPowerSource() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOriginalPowerSource(PoweredTileEntity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isReciever() {
		return true;
	}

	@Override
	public boolean isOutputter() {
		return false;
	}

	@Override
	public void updateState() {
		//Must keep lists empty so we don't have 1 block as a parent AND a child.
		parents.clear();
		children.clear();
				
		List<PoweredTileEntity> list = new ArrayList<PoweredTileEntity>();
		list.add(this.getTop());
		list.add(this.getBack());
		list.add(this.getBottom());
		list.add(this.getLeft());
		list.add(this.getRight());
		list.add(this.getFront());
		for (PoweredTileEntity e : list) {
			if(e != null){
			if(e.isOutputter())
			{
				if(!this.parents.contains(e))
				{
					this.parents.add(e);
				}
				if(!e.children.contains(this))
				{
					e.children.add(this);
				}
				if(!this.state)
				{
					this.state = e.canOutputPower();
				}
			}
		}
		}
		
	}
}