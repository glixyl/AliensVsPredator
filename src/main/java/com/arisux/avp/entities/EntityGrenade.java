package com.arisux.avp.entities;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityXenomorph;

public class EntityGrenade extends EntityThrowable
{
	public double velocity = 0.9800000190734863D;
	public double velocityGround = 0.699999988079071D;
	public boolean isFlaming, explodeOnImpact;
	private int fuse;

	public EntityGrenade(World var1)
	{
		super(var1);
		this.setSize(0.5F, 0.5F);
		this.yOffset = this.height / 2.0F;
		this.fuse = 0;
	}

	public EntityGrenade(World var1, EntityLivingBase var2)
	{
		super(var1, var2);
	}

	public EntityGrenade(World var1, double var2, double var4, double var6)
	{
		super(var1, var2, var4, var6);
	}

	@Override
	public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= 0.02999999910593033D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= velocity;
		this.motionY *= velocity;
		this.motionZ *= velocity;

		if (this.onGround)
		{
			this.motionX *= velocityGround;
			this.motionZ *= velocityGround;
			this.motionY *= -0.5D;
		}

		if (this.onGround && this.explodeOnImpact)
		{
			this.explode();
		}

		if (this.fuse++ >= 50)
		{
			this.explode();
		}
		else
		{
			this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	@SuppressWarnings("unchecked")
	public void explode()
	{
		if (!this.worldObj.isRemote)
		{
			Explosion explosion = WorldUtil.createExplosion(null, worldObj, new Blocks.CoordData(this), 2F, isFlaming, true, AliensVsPredator.instance().settings.areExplosionsEnabled());

			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(8, 8, 8));

			for (int i1 = 0; i1 < list.size(); ++i1)
			{
				Entity entity = list.get(i1);
				float targetDamage = entity instanceof EntityXenomorph ? 8F * 2 : 8F;
				entity.attackEntityFrom(DamageSource.setExplosionSource(explosion), targetDamage);
			}

		}

		this.setDead();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound var1)
	{
		super.writeEntityToNBT(var1);
		var1.setByte("Fuse", (byte) this.fuse);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound var1)
	{
		super.readEntityFromNBT(var1);
		this.fuse = var1.getByte("Fuse");
	}

	@Override
	protected void onImpact(MovingObjectPosition var1)
	{
		;
	}
}
