package com.arisux.avp.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.arisux.airi.engine.WorldEngine;
import com.arisux.airi.engine.WorldEngine.Blocks;
import com.arisux.avp.AliensVsPredator;

public class EntityPlasma extends EntityThrowable
{
	private boolean belongsToPlayer, released;
	private float size;
	public Entity shootingEntity;

	public EntityPlasma(World world)
	{
		super(world);
		this.setSize(0.5F, 0.5F);
	}

	public EntityPlasma(World world, EntityLivingBase shootingEntity)
	{
		super(world, shootingEntity);
		this.setSize(0.5F, 0.5F);
		this.shootingEntity = shootingEntity;
		this.belongsToPlayer = shootingEntity instanceof EntityPlayer;
	}

	@Override
	public void onUpdate()
	{
		if (this.released)
		{
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
		}

		if (this.ticksExisted >= 20 * 20)
		{
			// this.setDead();
		}

		// for (int p = 12; p > 0; --p)
		// {
		// this.worldObj.spawnParticle("enchantmenttable", this.posX + this.rand.nextDouble(), this.posY + this.rand.nextDouble(), this.posZ + this.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
		// this.worldObj.spawnParticle("reddust", this.posX + this.rand.nextDouble(), this.posY + this.rand.nextDouble(), this.posZ + this.rand.nextDouble(), 0.0D, 3.0D, 9.0D);
		// }

		Vec3 vec1 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		Vec3 vec2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition movingObjectPosition = this.worldObj.rayTraceBlocks(vec1, vec2);

		if (movingObjectPosition != null)
		{
			this.onImpact(movingObjectPosition);
			this.setDead();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("BelongsToPlayer", this.belongsToPlayer);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.belongsToPlayer = nbt.getBoolean("BelongsToPlayer");
	}

	@Override
	public void onImpact(MovingObjectPosition movingObjectPosition)
	{
		if (!this.worldObj.isRemote)
		{
			WorldEngine.createExplosion(null, worldObj, new Blocks.CoordData(this), 3F, false, true, AliensVsPredator.instance().settings.areExplosionsEnabled());
			this.worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);

			this.setDead();
		}
	}
	
	public float getSize()
	{
		return size;
	}

	public void increaseSize()
	{
		if (size < 1.0F)
		{
			size += 0.02F;
		}
	}
	
	public void release()
	{
		this.released = true;
	}
}
