package com.arisux.avp.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityFlame extends EntityThrowable
{
	double bounceFactor;
	int fuse;
	boolean exploded;

	public EntityFlame(World var1)
	{
		super(var1);
		this.setSize(0.5F, 0.5F);
		this.yOffset = this.height / 2.0F;
		this.bounceFactor = 0.75D;
		this.exploded = false;
		this.fuse = 0;
	}

	public EntityFlame(World var1, EntityLivingBase var2)
	{
		super(var1, var2);
	}

	public EntityFlame(World var1, double var2, double var4, double var6)
	{
		super(var1, var2, var4, var6);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		Vec3 var1 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		Vec3 var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var1, var2);
		var1 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		if (this.fuse++ >= 25)
		{
			this.setDead();
		} else
		{
			for (int p = 29; p > 0; --p)
			{
				this.worldObj.spawnParticle("flame", this.posX + this.rand.nextDouble(), this.posY + this.rand.nextDouble(), this.posZ + this.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
			}
		}

		if (var3 != null)
		{
			this.onImpact(var3);
			this.setDead();
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
		;
	}

	@Override
	protected void onImpact(MovingObjectPosition var1)
	{
		// Minecraft.getMinecraft().thePlayer.addChatMessage(new
		// ChatComponentText("Entity(" + var1.entityHit + ") hit @ " +
		// System.currentTimeMillis()));

		if (!this.worldObj.isRemote)
		{
			if (var1.entityHit != null && !var1.entityHit.isImmuneToFire())
			{
				var1.entityHit.setFire(10);
			}

			{
				int var2 = var1.blockX;
				int var3 = var1.blockY;
				int var4 = var1.blockZ;

				switch (var1.sideHit)
				{
					case 0:
						--var3;
						break;

					case 1:
						++var3;
						break;

					case 2:
						--var4;
						break;

					case 3:
						++var4;
						break;

					case 4:
						--var2;
						break;

					case 5:
						++var2;
				}

				this.worldObj.setBlock(var2, var3, var4, Blocks.fire);
			}

			this.setDead();
		}
	}
}
