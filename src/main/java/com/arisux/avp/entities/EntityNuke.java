package com.arisux.avp.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.arisux.airi.engine.WorldEngine;
import com.arisux.avp.AliensVsPredator;

public class EntityNuke extends EntityThrowable
{
	double bounceFactor;
	int fuse;
	boolean exploded;

	public EntityNuke(World var1)
	{
		super(var1);
		this.setSize(0.5F, 0.5F);
		this.yOffset = this.height / 2.0F;
		this.bounceFactor = 0.75D;
		this.exploded = false;
		this.fuse = 0;
	}

	public EntityNuke(World var1, EntityLivingBase var2)
	{
		super(var1, var2);
	}

	public EntityNuke(World var1, double var2, double var4, double var6)
	{
		super(var1, var2, var4, var6);
	}

	@Override
	public void onUpdate()
	{
		this.fuse++;

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= 0.03999999910593033D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;

		if (this.onGround)
		{
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
			this.motionY *= -0.5D;
		}

		if (this.getCurFuse() >= this.getMaxFuse())
		{
			if (AliensVsPredator.instance.settings.areExplosionsEnabled())
			{
				WorldEngine.createThreadedExplosion((Entity) null, worldObj, (int) this.posX, (int) this.posY, (int) this.posZ, 47F);
			}

			this.setDead();
		} else
		{
			this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to
	 * NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound var1)
	{
		super.writeEntityToNBT(var1);
		var1.setByte("Fuse", (byte) this.fuse);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from
	 * NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound var1)
	{
		super.readEntityFromNBT(var1);
		this.fuse = var1.getByte("Fuse");
	}

	/**
	 * Called by a player entity when they collide with an entity
	 */
	public void onCollideWithPlayer(EntityPlayer var1)
	{
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(MovingObjectPosition var1)
	{
	}

	public double getMaxFuse()
	{
		return 300;
	}

	public double getCurFuse()
	{
		return this.fuse;
	}
}
