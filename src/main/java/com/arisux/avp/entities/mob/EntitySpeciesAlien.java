package com.arisux.avp.entities.mob;

import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAcidPool;
import com.arisux.avp.interfaces.IHiveSignature;
import com.arisux.avp.packets.client.PacketKillCountUpdate;

public abstract class EntitySpeciesAlien extends EntityHostileExtraterrestrial implements IMob, IHiveSignature
{
	private Class<? extends EntitySpeciesAlien> entityEvolveTo;
	private UUID signature;
	protected int killedEntities;
	protected int minKillsToEvolve;

	public EntitySpeciesAlien(World world)
	{
		super(world);
		this.jumpMovementFactor = 0.2F;
		this.killedEntities = 0;
		this.setEvolveTo(null, 0);
	}

	@Override
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
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);

		nbt.setInteger("killedEntities", this.killedEntities);

		if (signature != null)
		{
			nbt.setString("hiveSignature", this.signature.toString());
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);

		this.killedEntities = nbt.getInteger("killedEntities");

		String signature = nbt.getString("hiveSignature");

		if (signature != null && signature.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}"))
		{
			this.signature = UUID.fromString(signature);
		}
	}

	@Override
	public void onKillEntity(EntityLivingBase par1EntityLivingBase)
	{
		super.onKillEntity(par1EntityLivingBase);

		this.killedEntities++;
		AliensVsPredator.instance().network.sendToAll(new PacketKillCountUpdate(this.getKilledEntities(), Integer.valueOf(this.getEntityId())));

		if (!this.worldObj.isRemote && entityEvolveTo != null && minKillsToEvolve != 0 && this.killedEntities >= minKillsToEvolve)
		{
			EntitySpeciesAlien entity = (EntitySpeciesAlien) WorldUtil.Entities.constructEntity(this.worldObj, this.entityEvolveTo);
			entity.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
			this.worldObj.spawnEntityInWorld(entity);
			this.setDead();
		}
	}

	@Override
	protected boolean canDespawn()
	{
		return !(this.getKilledEntities() > 1);
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void onDeath(DamageSource damagesource)
	{
		super.onDeath(damagesource);

		if (!this.worldObj.isRemote)
		{
			EntityAcidPool entity = new EntityAcidPool(this.worldObj);
			entity.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.worldObj.spawnEntityInWorld(entity);
			
			if (this instanceof EntityQueen)
			{
				int randomJelly = this.rand.nextInt(196);
				this.dropItem(AliensVsPredator.instance().items.itemRoyalJelly, 32 +(randomJelly / 2 + randomJelly));
			}
			
			if (this.rand.nextInt(4) == 0)
			{
				this.dropItem(AliensVsPredator.instance().items.itemRoyalJelly, 1 + this.rand.nextInt(5));
			}
		}
	}
	
	@Override
	protected void dropRareDrop(int rate)
	{
		this.dropItem(AliensVsPredator.instance().items.itemRoyalJelly, 4);
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

	@Override
	public UUID getHiveSignature()
	{
		return this.signature;
	}

	@Override
	public void setHiveSignature(UUID signature)
	{
		this.signature = signature;
	}
}
