package com.arisux.avp.entities.mob;

import com.arisux.avp.AliensVsPredator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityOvamorph extends EntitySpeciesAlien implements IMob
{
	public int hatchingTime;
	public boolean hasHatched;
	public boolean acceleratedHatching;

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
		
        int x = MathHelper.floor_double(this.posX);
        int y = MathHelper.floor_double(this.posY);
        int z = MathHelper.floor_double(this.posZ);

		if (this.worldObj.getBlock(x, y, z).getMaterial() != AliensVsPredator.materials().mist || this.acceleratedHatching)
		{
			int hatchAcceleration = this.acceleratedHatching ? 8 : 1;
			
			if (this.worldObj.getClosestPlayerToEntity(this, 15.0D) != null || this.hasHatched)
			{
				if (!this.worldObj.isRemote && (this.hatchingTime -= hatchAcceleration) <= 1 || this.hasHatched)
				{
					this.hatch();
				}
			}
		}
	}
	
	@Override
	protected void collideWithEntity(Entity entity)
	{
		super.collideWithEntity(entity);
		
		if (!(entity instanceof EntityOvamorph) && !(entity instanceof EntitySpeciesAlien))
		{
			this.acceleratedHatching = true;
		}
	}
	
	@Override
	protected void damageEntity(DamageSource source, float amount)
	{
		super.damageEntity(source, amount);
		
		this.acceleratedHatching = true;
	}
	
	private void hatch()
	{
		EntityFacehugger facehugger = new EntityFacehugger(this.worldObj);
		facehugger.setLocationAndAngles(posX, posY, posZ, 0F, 0F);
		worldObj.spawnEntityInWorld(facehugger);

		this.setDead();
	}
	
	public void setHatched(boolean hasHatched)
	{
		this.hasHatched = hasHatched;
	}
}
