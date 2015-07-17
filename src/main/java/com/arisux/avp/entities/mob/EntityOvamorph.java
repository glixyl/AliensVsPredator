package com.arisux.avp.entities.mob;

import com.arisux.avp.AliensVsPredator;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityOvamorph extends EntitySpeciesAlien implements IMob
{
	public int hatchingTime;
	public boolean hasHatched;

	public EntityOvamorph(World par1World)
	{
		super(par1World);
		this.setSize(0.5F, 0.5F);
		this.hatchingTime = 600;
		this.experienceValue = 10;
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

		EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 15.0D);

		if (player != null || this.hasHatched)
		{
			if (!this.worldObj.isRemote && this.hatchingTime-- <= 1 || this.hasHatched)
			{
				EntityFacehugger facehugger = new EntityFacehugger(this.worldObj);
				facehugger.setLocationAndAngles(posX, posY, posZ, 0F, 0F);
				worldObj.spawnEntityInWorld(facehugger);

				this.setDead();
			}
		}
	}
	
	public void setHatched(boolean hasHatched)
	{
		this.hasHatched = hasHatched;
	}
}
