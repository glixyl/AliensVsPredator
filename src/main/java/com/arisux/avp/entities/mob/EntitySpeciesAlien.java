package com.arisux.avp.entities.mob;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAcidPool;
import com.arisux.avp.packets.client.PacketKillCountClientUpdate;

public class EntitySpeciesAlien extends EntityMob implements IMob
{
	private int killedEntities;
	private int minKillsToEvolve;
	private Class<? extends EntitySpeciesAlien> entityEvolveTo;

	public EntitySpeciesAlien(World var1)
	{
		super(var1);
		this.jumpMovementFactor = 0.2F;
		this.killedEntities = 0;
		this.setEvolveTo(null, 0);
	}

	protected void entityInit()
	{
		super.entityInit();
	}

	public void setEvolveTo(Class<? extends EntitySpeciesAlien> entityEvolveTo, int minKillsToEvolve)
	{
		this.entityEvolveTo = entityEvolveTo;
		this.minKillsToEvolve = minKillsToEvolve;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound)
	{
		super.writeEntityToNBT(par1nbtTagCompound);

		par1nbtTagCompound.setInteger("killedEntities", this.killedEntities);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound)
	{
		super.readEntityFromNBT(par1nbtTagCompound);

		this.killedEntities = par1nbtTagCompound.getInteger("killedEntities");
	}

	@Override
	public void onKillEntity(EntityLivingBase par1EntityLivingBase)
	{
		super.onKillEntity(par1EntityLivingBase);

		this.killedEntities++;
		AliensVsPredator.INSTANCE.network.sendToAll(new PacketKillCountClientUpdate(this.getKilledEntities(), Integer.valueOf(this.getEntityId())));

		if (!this.worldObj.isRemote && entityEvolveTo != null && minKillsToEvolve != 0 && this.killedEntities >= minKillsToEvolve)
		{
			try
			{
				EntitySpeciesAlien entity = (EntitySpeciesAlien) entityEvolveTo.getDeclaredConstructor(World.class).newInstance(worldObj);
				entity.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
				this.worldObj.spawnEntityInWorld(entity);
				this.setDead();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	protected boolean canDespawn()
	{
		if (this.getKilledEntities() > 5)
			return false;
		else
			return true;
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);

		if (!this.worldObj.isRemote)
		{
			EntityAcidPool entity = new EntityAcidPool(this.worldObj);
			double d = this.posX;
			double d1 = this.posY;
			double d2 = this.posZ;
			entity.setLocationAndAngles(d, d1, d2, 0.0F, 0.0F);
			this.worldObj.spawnEntityInWorld(entity);
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	public int getKilledEntities()
	{
		return killedEntities;
	}

	public void setKilledEntities(int kills)
	{
		this.killedEntities = kills;
	}
}
