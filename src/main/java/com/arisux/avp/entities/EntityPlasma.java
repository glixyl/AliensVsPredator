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
	double bounceFactor;
	int fuse;
	boolean exploded;
	public static EntityPlayer Player;
	public boolean belongsToPlayer;
	public Entity shootingEntity;

	public EntityPlasma(World var1)
	{
		super(var1);
		this.setSize(0.5F, 0.5F);
		this.yOffset = this.height / 2.0F;
		this.bounceFactor = 0.75D;
		this.exploded = false;
		this.fuse = 0;
	}

	public EntityPlasma(World var1, EntityLivingBase var2)
	{
		super(var1, var2);
		this.shootingEntity = var2;
		this.belongsToPlayer = var2 instanceof EntityPlayer;
	}

	public EntityPlasma(World var1, double var2, double var4, double var6)
	{
		super(var1, var2, var4, var6);
	}

	@Override
	public void onUpdate()
	{
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		Vec3 var1 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		Vec3 var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var1, var2);
		var1 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		if (this.fuse++ >= 525)
		{
			this.setDead();
		} else
		{
			for (int p = 19; p > 0; --p)
			{
				this.worldObj.spawnParticle("enchantmenttable", this.posX + this.rand.nextDouble(), this.posY + this.rand.nextDouble(), this.posZ + this.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
				this.worldObj.spawnParticle("reddust", this.posX + this.rand.nextDouble(), this.posY + this.rand.nextDouble(), this.posZ + this.rand.nextDouble(), 0.0D, 3.0D, 9.0D);
			}
		}

		if (var3 != null)
		{
			this.onImpact(var3);
			this.setDead();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound var1)
	{
		super.writeEntityToNBT(var1);
		var1.setByte("Fuse", (byte) this.fuse);
		var1.setBoolean("player", this.belongsToPlayer);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound var1)
	{
		super.readEntityFromNBT(var1);
		this.fuse = var1.getByte("Fuse");
		this.belongsToPlayer = var1.getBoolean("player");
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
	{
		if (!this.worldObj.isRemote)
		{

			if (!this.belongsToPlayer)
			{
				WorldEngine.createExplosion(null, worldObj, new Blocks.CoordData(this), 3F, false, true, AliensVsPredator.instance().settings.areExplosionsEnabled());
				this.worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				this.setDead();
			}
		}
	}

	@Override
	public void onImpact(MovingObjectPosition var1)
	{
		if (!this.worldObj.isRemote)
		{
			WorldEngine.createExplosion(null, worldObj, new Blocks.CoordData(this), 3F, false, true, AliensVsPredator.instance().settings.areExplosionsEnabled());
			this.worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);

			this.setDead();
		}
	}
}
