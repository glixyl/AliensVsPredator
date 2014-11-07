package com.arisux.avp.entities;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockLib.CoordData;
import com.arisux.airi.lib.WorldLib;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.EntityYautja;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityProximityMine extends Entity
{
	private String ownerUUID;
	private int tickCounter1;
	public int direction;
	public int xPosition;
	public int yPosition;
	public int zPosition;

	public EntityProximityMine(World world)
	{
		super(world);
		this.tickCounter1 = 0;
		this.direction = 0;
		this.yOffset = 0.0F;
		this.setSize(0.5F, 0.5F);
	}

	public EntityProximityMine(World world, int posX, int posY, int posZ, int direction, String ownerUUID)
	{
		this(world);
		this.direction = direction;
		this.xPosition = posX;
		this.yPosition = posY;
		this.zPosition = posZ;
		this.setDirectionBasedBounds(direction);
		this.ownerUUID = ownerUUID;
	}

	@Override
	protected void entityInit()
	{
		;
	}

	@SuppressWarnings("unchecked")
	public boolean canStay()
	{
		if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() > 0)
		{
			return false;
		} else
		{
			byte i = 1;
			byte j = 1;
			int k = this.xPosition;
			int l = this.yPosition;
			int i1 = this.zPosition;

			if (this.direction == 0)
			{
				k = MathHelper.floor_double(this.posX - 0.5D);
			}

			if (this.direction == 1)
			{
				i1 = MathHelper.floor_double(this.posZ - 0.5D);
			}

			if (this.direction == 2)
			{
				k = MathHelper.floor_double(this.posX - 0.5D);
			}

			if (this.direction == 3)
			{
				i1 = MathHelper.floor_double(this.posZ - 0.5D);
			}

			l = MathHelper.floor_double(this.posY - 0.5D);
			int l1;

			for (int list = 0; list < i; ++list)
			{
				for (l1 = 0; l1 < j; ++l1)
				{
					Material material;

					if (this.direction != 0 && this.direction != 2)
					{
						material = this.worldObj.getBlock(this.xPosition, l + l1, i1 + list).getMaterial();
					} else
					{
						material = this.worldObj.getBlock(k + list, l + l1, this.zPosition).getMaterial();
					}

					if (!material.isSolid())
					{
						return false;
					}
				}
			}

			List<Entity> var9 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox);

			for (l1 = 0; l1 < var9.size(); ++l1)
			{
				if (var9.get(l1) instanceof EntityProximityMine)
				{
					return false;
				}
			}

			return true;
		}
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;

		if (!this.worldObj.isRemote)
		{
			int entityAmount = this.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox((double) this.posX, (double) this.posY, (double) this.posZ, (double) (this.posX + 1), (double) (this.posY + 1), (double) (this.posZ + 1)).expand((double) (2), 50.0D, (double) (2))).size();
			int yautjaAmount = this.worldObj.getEntitiesWithinAABB(EntityYautja.class, AxisAlignedBB.getBoundingBox((double) this.posX, (double) this.posY, (double) this.posZ, (double) (this.posX + 1), (double) (this.posY + 1), (double) (this.posZ + 1)).expand((double) (3), 50.0D, (double) (3))).size();

			if (entityAmount >= 1 && yautjaAmount == 0)
			{
				this.explode();
			}
		} else if (this.tickCounter1++ == 100 && !this.worldObj.isRemote)
		{
			this.tickCounter1 = 0;

			if (!this.canStay())
			{
				this.setDead();
				this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(AliensVsPredator.INSTANCE.items.itemProximityMine)));
			}
		}

	}
	
	public void explode()
	{
		WorldLib.createExplosion(null, worldObj, new CoordData(this), 2F, false, true, AliensVsPredator.INSTANCE.settings.areExplosionsEnabled());
		this.setDead();
	}

	@SideOnly(Side.CLIENT)
	public boolean attackEntityFrom(DamageSource damagesource, int i)
	{
		if (!this.isDead)
		{
			this.setDead();
		}

		return true;
	}

	public void setDirectionBasedBounds(int side)
	{
		this.direction = side;
		this.prevRotationYaw = this.rotationYaw = (float) (side * 90);
		float f = 16.0F;
		float f1 = 16.0F;
		float f2 = 16.0F;

		if (side != 0 && side != 2)
		{
			f = 0.5F;
		} else
		{
			f2 = 0.5F;
		}

		f /= 32.0F;
		f1 /= 32.0F;
		f2 /= 32.0F;
		float f3 = (float) this.xPosition + 0.5F;
		float f4 = (float) this.yPosition + 0.5F;
		float f5 = (float) this.zPosition + 0.5F;
		float f6 = 0.5625F;
		
		if (side == 0)
		{
			f5 -= f6;
		}

		if (side == 1)
		{
			f3 -= f6;
		}

		if (side == 2)
		{
			f5 += f6;
		}

		if (side == 3)
		{
			f3 += f6;
		}

		if (side == 0)
		{
			f3 -= 0.0F;
		}

		if (side == 1)
		{
			f5 += 0.0F;
		}

		if (side == 2)
		{
			f3 += 0.0F;
		}

		if (side == 3)
		{
			f5 -= 0.0F;
		}

		f4 += 0.0F;
		this.setPosition((double) f3, (double) f4, (double) f5);
		float f7 = -0.00625F;
		this.boundingBox.setBounds((double) (f3 - f - f7), (double) (f4 - f1 - f7), (double) (f5 - f2 - f7), (double) (f3 + f + f7), (double) (f4 + f1 + f7), (double) (f5 + f2 + f7));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setByte("Dir", (byte) this.direction);
		nbttagcompound.setInteger("TileX", this.xPosition);
		nbttagcompound.setInteger("TileY", this.yPosition);
		nbttagcompound.setInteger("TileZ", this.zPosition);
		nbttagcompound.setString("Owner", this.ownerUUID);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		this.setDirectionBasedBounds(this.direction = nbttagcompound.getByte("Dir"));
		this.xPosition = nbttagcompound.getInteger("TileX");
		this.yPosition = nbttagcompound.getInteger("TileY");
		this.zPosition = nbttagcompound.getInteger("TileZ");
		this.ownerUUID = nbttagcompound.getString("Owner");
	}
}
