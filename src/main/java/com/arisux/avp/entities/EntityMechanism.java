package com.arisux.avp.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMechanism extends Entity
{
	public EntityMechanism(World world)
	{
		super(world);
		this.setSize(1F, 0.1F);
	}

	@Override
	protected void entityInit()
	{
		this.dataWatcher.addObject(14, 0);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag)
	{
		this.dataWatcher.updateObject(14, tag.getInteger("EntityContainedId"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag)
	{
		tag.setInteger("EntityContainedId", this.getEntityContainedId());
	}
	
	public int getEntityContainedId()
	{
		return this.dataWatcher.getWatchableObjectInt(14);
	}
	
	public void setEntityContainedId(Entity entityContained)
	{
		this.dataWatcher.updateObject(14, entityContained.getEntityId());
	}
}
