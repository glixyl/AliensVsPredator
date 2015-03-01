package com.arisux.avp.entities;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.DamageSources;
import com.arisux.avp.packets.server.PacketDamageEntity;

public class EntityLaserMine extends Entity
{
	private String ownerUUID;
	public int direction;
	public MovingObjectPosition laserHit;

	public EntityLaserMine(World world)
	{
		super(world);
		this.ignoreFrustumCheck = true;
		this.yOffset = 0.0F;
		this.setSize(0.5F, 0.5F);
	}

	public EntityLaserMine(World world, int posX, int posY, int posZ, int direction, String ownerUUID)
	{
		this(world);
		this.direction = direction;
		this.ownerUUID = ownerUUID;
		this.setLocationAndAngles(posX, posY, posZ, 0, 0);
		this.setDirectionBasedBounds(direction);
	}

	@Override
	protected void entityInit()
	{
		;
	}

	@SuppressWarnings("unchecked")
	public boolean canStay()
	{
		List<Entity> entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox);

		for (Entity entity : entities)
		{
			if (entity instanceof EntityLaserMine)
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public Vec3 getLookVec()
	{
		float f1 = MathHelper.cos(-this.rotationYaw * 0.017453292F - (float) Math.PI);
		float f2 = MathHelper.sin(-this.rotationYaw * 0.017453292F - (float) Math.PI);
		float f3 = -MathHelper.cos(-this.rotationPitch * 0.017453292F);
		float f4 = MathHelper.sin(-this.rotationPitch * 0.017453292F);
		return Vec3.createVectorHelper((f2 * f3), f4, -(f1 * f3));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.worldObj.getWorldTime() % 10 == 0)
		{
			this.laserHit = WorldUtil.Entities.rayTrace(this, this.getLaserMaxDepth());
		}

		if (this.worldObj.isRemote)
		{
			if (this.getLaserHit() != null && this.getLaserHit().entityHit != null)
			{
				if (!(this.getLaserHit().entityHit instanceof EntityLaserMine))
				{
					if (!(this.getLaserHit().entityHit instanceof EntityPlayer))
					{
						this.explode(this.getLaserHit().entityHit);
					}
					else if (this.getLaserHit().entityHit instanceof EntityPlayer && !((EntityPlayer) this.getLaserHit().entityHit).capabilities.isCreativeMode)
					{
						this.explode(this.getLaserHit().entityHit);
					}
				}
			}

			if (!this.canStay())
			{
				this.drop();
			}
		}
	}

	public MovingObjectPosition getLaserHit()
	{
		return laserHit;
	}

	public int getLaserMaxDepth()
	{
		return 32;
	}

	public double getLaserHitDistanceFromMine()
	{
		if (this.getLaserHit() != null && this.getLaserHit().entityHit != null)
		{
			return (this.posX - this.getLaserHit().entityHit.posX) + (this.posZ - this.getLaserHit().entityHit.posZ) + (this.posY - this.getLaserHit().entityHit.posY);
		}

		if (this.getLaserHit() != null && this.getLaserHit().hitVec != null)
		{
			return (this.posX - this.getLaserHit().hitVec.xCoord) + (this.posZ - this.getLaserHit().hitVec.zCoord) + (this.posY - this.getLaserHit().hitVec.yCoord);
		}

		return this.getLaserMaxDepth();
	}

	public void drop()
	{
		this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(AliensVsPredator.instance().items.itemProximityMine)));
		this.setDead();
	}

	public void explode(Entity entityHit)
	{
		Explosion explosion = new Explosion(worldObj, this, this.posX, this.posY, this.posZ, 4F);
		explosion.isSmoking = true;
		explosion.doExplosionB(true);

		if (entityHit != null)
		{
			entityHit.attackEntityFrom(DamageSources.causeLaserMineDamage(this, entityHit), 15F);

			if (this.worldObj.isRemote)
			{
				AliensVsPredator.instance().network.sendToServer(new PacketDamageEntity(entityHit, this, 15F));
			}
		}

		this.setDead();
	}

	public void setDirectionBasedBounds(int side)
	{
		this.rotationYaw = 90F * (this.direction);
		float bounds = -0.00625F;
		float f = 16.0F;
		float f1 = 16.0F;
		float f2 = 16.0F;

		if (side != 0 && side != 2)
		{
			f = 0.5F;
		}
		else
		{
			f2 = 0.5F;
		}

		f /= 32.0F;
		f1 /= 32.0F;
		f2 /= 32.0F;
		float xPos = (float) (this.posX + 0.5F);
		float yPos = (float) (this.posY + 0.5F);
		float zPos = (float) (this.posZ + 0.5F);
		float f6 = 0.5625F;

		if (side == 0)
			zPos -= f6;

		if (side == 1)
			xPos -= f6;

		if (side == 2)
			zPos += f6;

		if (side == 3)
			xPos += f6;

		if (side == 0)
			xPos -= 0.0F;

		if (side == 1)
			zPos += 0.0F;

		if (side == 2)
			xPos += 0.0F;

		if (side == 3)
			zPos -= 0.0F;

		yPos += 0.0F;
		this.setPosition(xPos, yPos, zPos);
		this.boundingBox.setBounds(xPos - f - bounds, yPos - f1 - bounds, zPos - f2 - bounds, xPos + f + bounds, yPos + f1 + bounds, zPos + f2 + bounds);
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float damage)
	{
		if (!this.worldObj.isRemote)
		{
			this.drop();
		}

		return true;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setByte("Dir", (byte) this.direction);
		nbttagcompound.setString("Owner", this.ownerUUID);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		// this.setDirectionBasedBounds(this.direction = nbttagcompound.getByte("Dir"));
		this.direction = nbttagcompound.getByte("Dir");
		this.ownerUUID = nbttagcompound.getString("Owner");
	}
}
