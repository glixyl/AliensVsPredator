package com.arisux.avp.entities.mob;

import java.util.ArrayList;
import java.util.UUID;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAcidPool;
import com.arisux.avp.interfaces.IHiveSignature;
import com.arisux.avp.packets.client.PacketJellyLevelUpdate;
import com.arisux.avp.util.EvolutionType;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntitySpeciesAlien extends EntityMob implements IMob, IHiveSignature
{
	private UUID signature;
	private int jellyLevel;

	public EntitySpeciesAlien(World world)
	{
		super(world);
		this.jumpMovementFactor = 0.2F;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);

		nbt.setInteger("jellyLevel", this.jellyLevel);
		nbt.setString("hiveSignature", signature != null ? this.signature.toString() : "");
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);

		this.jellyLevel = nbt.getInteger("jellyLevel");
		this.signature = this.uuidFromNBT(nbt);
	}

	private UUID uuidFromNBT(NBTTagCompound nbt)
	{
		String signature = nbt.getString("hiveSignature");

		if (signature != null && signature.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}"))
		{
			return UUID.fromString(signature);
		}

		return null;
	}

	@Override
	public void onKillEntity(EntityLivingBase par1EntityLivingBase)
	{
		super.onKillEntity(par1EntityLivingBase);
		this.setJellyLevel(this.getJellyLevel() + 1);
	}

	@Override
	protected boolean canDespawn()
	{
		return this.getJellyLevel() < 1;
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
				this.dropItem(AliensVsPredator.items().itemRoyalJelly, 32 + (randomJelly / 2 + randomJelly));
			}

			if (this.rand.nextInt(4) == 0)
			{
				this.dropItem(AliensVsPredator.items().itemRoyalJelly, 1 + this.rand.nextInt(5));
			}
		}
	}

	@Override
	protected void dropRareDrop(int rate)
	{
		this.dropItem(AliensVsPredator.items().itemRoyalJelly, 4);
	}

	protected void tickEvolution()
	{
		if (this.worldObj.getWorldTime() % 40 == 0)
		{
			EvolutionType evolution = EvolutionType.getEvolutionMappingFor(this.getClass());

			if (!this.worldObj.isRemote && evolution != null && evolution.getEvolution() != null && evolution.getLevel() != 0 && this.jellyLevel >= evolution.getLevel())
			{
				EntitySpeciesAlien alien = (EntitySpeciesAlien) WorldUtil.Entities.constructEntity(this.worldObj, evolution.getEvolution());
				alien.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
				this.worldObj.spawnEntityInWorld(alien);
				this.setDead();
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected void tickJellyPickupAI()
	{
		if (!this.worldObj.isRemote)
		{
			ArrayList<EntityItem> entityItemList = (ArrayList<EntityItem>) WorldUtil.Entities.getEntitiesInCoordsRange(worldObj, EntityItem.class, new com.arisux.airi.lib.WorldUtil.Blocks.CoordData(this), 8);
	
			for (EntityItem entityItem : entityItemList)
			{
				if (entityItem.delayBeforeCanPickup <= 0)
				{
					ItemStack stack = entityItem.getDataWatcher().getWatchableObjectItemStack(10);
	
					if (stack.getItem() == AliensVsPredator.items().itemRoyalJelly)
					{
						this.getNavigator().setPath(this.getNavigator().getPathToEntityLiving(entityItem), 1);
	
						if (this.getDistanceToEntity(entityItem) < 1)
						{
							for (int i = entityItem.getEntityItem().stackSize; i > 0; i--)
							{
								this.setJellyLevel(this.getJellyLevel() + 1);
							}
							
							entityItem.setDead();
						}
						break;
					}
				}
			}
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		this.tickEvolution();
		this.tickJellyPickupAI();
	}

	public int getJellyLevel()
	{
		return this.jellyLevel;
	}

	public void setJellyLevel(int jellyLevel)
	{
		this.jellyLevel = jellyLevel;

		if (!this.worldObj.isRemote)
		{
			AliensVsPredator.network().sendToAll(new PacketJellyLevelUpdate(jellyLevel, Integer.valueOf(this.getEntityId())));
		}
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
