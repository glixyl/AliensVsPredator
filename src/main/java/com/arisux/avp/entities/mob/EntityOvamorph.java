package com.arisux.avp.entities.mob;

import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityOvamorph extends EntitySpeciesAlien implements IMob
{
	private int hatchingTime;
	private boolean hasHatched;
	private boolean acceleratedHatching;
	private int openProgress;
	private int hatchWaitTimer;
	private final int maxOpenProgress = 21;
	private boolean containsFacehugger;

	public EntityOvamorph(World par1World)
	{
		super(par1World);
		this.setSize(0.5F, 0.5F);
		this.hatchingTime = 600;
		this.experienceValue = 10;
		this.openProgress = -maxOpenProgress;
		this.hatchWaitTimer = 20 * 10;
		this.containsFacehugger = true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);

		this.containsFacehugger = nbt.getBoolean("containsFacehugger");
		this.openProgress = nbt.getInteger("openProgress");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);

		nbt.setBoolean("containsFacehugger", this.containsFacehugger);
		nbt.setInteger("openProgress", this.openProgress);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	protected void dropRareDrop(int rate)
	{
		this.dropItem(AliensVsPredator.items().itemRoyalJelly, 1);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.getHealth() < this.getMaxHealth())
		{
			this.acceleratedHatching = true;
		}

		if (!this.containsFacehugger)
		{
			this.openProgress = this.getMaxOpenProgress();
		}

		if (this.containsFacehugger)
		{
			int x = MathHelper.floor_double(this.posX);
			int y = MathHelper.floor_double(this.posY);
			int z = MathHelper.floor_double(this.posZ);

			if (this.worldObj.getBlock(x, y, z).getMaterial() != AliensVsPredator.materials().mist || this.acceleratedHatching)
			{
				int hatchAcceleration = this.acceleratedHatching ? 8 : 1;
				EntityPlayer closestPlayer = this.worldObj.getClosestPlayerToEntity(this, 15.0D);	

				if (closestPlayer != null && !closestPlayer.capabilities.isCreativeMode || this.hasHatched)
				{
					if (this.acceleratedHatching || this.hatchingTime <= 0)
					{
						this.openProgress = this.openProgress < (maxOpenProgress) ? openProgress + 1 : this.openProgress;
					}

					if ((this.hatchingTime -= hatchAcceleration) <= 1 || this.hasHatched)
					{
						if (this.hatchWaitTimer-- <= 0)
						{
							this.hatch();
						}
					}
				}
			}
		}
	}

	@Override
	protected void collideWithEntity(Entity entity)
	{
		super.collideWithEntity(entity);

		// if (!(entity instanceof EntityOvamorph) && !(entity instanceof EntitySpeciesAlien))
		// {
		// this.acceleratedHatching = true;
		// }
	}

	@Override
	protected void damageEntity(DamageSource source, float amount)
	{
		super.damageEntity(source, amount);
	}

	private void hatch()
	{
		if (!this.worldObj.isRemote)
		{
			EntityFacehugger facehugger = new EntityFacehugger(this.worldObj);
			CoordData pos = new CoordData(this).findSafePosAround(this.worldObj);

			facehugger.setLocationAndAngles(pos.posX, pos.posY, pos.posZ, 0F, 0F);
			worldObj.spawnEntityInWorld(facehugger);
			facehugger.motionY = 0.75F;

			this.setContainsFacehugger(false);
		}
	}

	public void setHatched(boolean hasHatched)
	{
		this.hasHatched = hasHatched;
	}

	public int getOpenProgress()
	{
		return this.openProgress;
	}

	public int getMaxOpenProgress()
	{
		return maxOpenProgress;
	}

	public boolean hasFacehugger()
	{
		return containsFacehugger;
	}

	public void setContainsFacehugger(boolean containsFacehugger)
	{
		this.containsFacehugger = containsFacehugger;
	}
}
