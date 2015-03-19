package com.arisux.avp.entities.mob;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;

public class EntityOvamorph extends EntitySpeciesAlien implements IMob
{
	public int hatchingTime;
	public boolean nearPlayer;
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

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		super.onUpdate();

		EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 15.0D);

		if (player != null)
		{
			--this.hatchingTime;
			this.nearPlayer = true;
		}

		if (!this.worldObj.isRemote && this.hatchingTime <= 1)
		{
			EntityFacehugger par1 = new EntityFacehugger(this.worldObj);
			par1.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
			worldObj.spawnEntityInWorld(par1);

			this.setDead();
		}
	}
}
