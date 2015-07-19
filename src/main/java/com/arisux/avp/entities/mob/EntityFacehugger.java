package com.arisux.avp.entities.mob;

import java.util.ArrayList;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.ai.alien.EntityAIFacehuggerMountTarget;
import com.arisux.avp.entities.extended.ExtendedEntityLivingBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFacehugger extends EntitySpeciesAlien implements IMob
{
	private boolean isFertile;
	private EntityLivingBase entityToFacehug;

	public EntityFacehugger(World world)
	{
		super(world);
		this.isFertile = true;
		this.setSize(0.9F, 0.9F);
		this.experienceValue = 10;
		this.targetTasks.addTask(0, new EntityAIFacehuggerMountTarget(this));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(14.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5999999761581421D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.50D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48.0D);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.isCollidedHorizontally)
		{
			this.motionY += 0.2F;
		}

		if (this.isRiding())
		{
			this.rotationYaw = 0F;
			this.rotationYawHead = 0F;
		}

		if (!this.isFertile && !this.isRiding())
		{
			if (!this.worldObj.isRemote)
			{
				EntityItem entityItem = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, WorldUtil.Entities.Players.Inventories.newStack(AliensVsPredator.items().itemSummonerFacehugger));
				entityItem.setLocationAndAngles(this.posX, this.posY, this.posZ, 0, 0);
				this.worldObj.spawnEntityInWorld(entityItem);
			}

			this.setDead();
		}

		if (!this.isFertile)
		{
			@SuppressWarnings("unchecked")
			ArrayList<EntityItem> entityItemList = (ArrayList<EntityItem>) WorldUtil.Entities.getEntitiesInCoordsRange(worldObj, EntityItem.class, new com.arisux.airi.lib.WorldUtil.Blocks.CoordData(this), 8);

			for (EntityItem entityItem : entityItemList)
			{
				if (entityItem.delayBeforeCanPickup <= 0)
				{
					ItemStack stack = entityItem.getDataWatcher().getWatchableObjectItemStack(10);

					if (stack != null && stack.getItem() == AliensVsPredator.items().itemRoyalJelly)
					{
						this.getNavigator().setPath(this.getNavigator().getPathToEntityLiving(entityItem), 1);

						if (this.getDistanceToEntity(entityItem) < 1)
						{
							this.isFertile = true;
							entityItem.setDead();
						}
						break;
					}
				}
			}
		}
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player)
	{
		super.onCollideWithPlayer(player);
	}

	@Override
	public boolean isOnLadder()
	{
		return this.motionY > 1.0099999997764826D;
	}

	public void jumpAtEntity(EntityLivingBase leaper, EntityLivingBase leapTarget, double leapMotionY)
	{
		double rX = leapTarget.posX - leaper.posX;
		double rZ = leapTarget.posZ - leaper.posZ;
		float sq = MathHelper.sqrt_double(rX * rX + rZ * rZ);
		leaper.motionX += rX / sq * 0.5D * 0.800000011920929D + leaper.motionX * 0.16000000298023224D;
		leaper.motionZ += rZ / sq * 0.5D * 0.800000011920929D + leaper.motionZ * 0.16000000298023224D;
		leaper.motionY = leapMotionY;
	}

	@Override
	public void collideWithEntity(Entity entity)
	{
		if (this.isFertile && entity instanceof EntityLivingBase && !(entity instanceof EntitySpeciesAlien))
		{
			EntityLivingBase entityLiving = (EntityLivingBase) entity;
			ExtendedEntityLivingBase extendedLiving = (ExtendedEntityLivingBase) entityLiving.getExtendedProperties(ExtendedEntityLivingBase.IDENTIFIER);

			if (!(entityLiving instanceof EntityPlayer) || entityLiving instanceof EntityPlayer && !((EntityPlayer) entityLiving).capabilities.isCreativeMode)
			{
				this.mountEntity(entityLiving);
				extendedLiving.setContainsEmbryo(true);
				extendedLiving.syncClients();
				this.isFertile = false;
			}
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float damage)
	{
		return super.attackEntityFrom(damagesource, damage);
	}

	@Override
	public void onDeath(DamageSource damagesource)
	{
		super.onDeath(damagesource);
	}

	public float facehuggerScaleAmount()
	{
		return 0.7F;
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public double getYOffset()
	{
		if (this.ridingEntity instanceof EntityPlayer || this.ridingEntity instanceof EntityMarine)
		{
			return 0.2D;
		}

		return 0.3D;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_FACEHUGGER_DEATH;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	protected void attackEntity(Entity entity, float attackStrength)
	{
		;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean isPotionApplicable(PotionEffect effect)
	{
		return effect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(effect);
	}

	@Override
	public void onKillEntity(EntityLivingBase host)
	{
		super.onKillEntity(host);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.isFertile = nbt.getBoolean("fertile");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setBoolean("fertile", this.isFertile);
	}

	public boolean isFertile()
	{
		return this.isFertile;
	}

	public EntityLivingBase getEntityToFacehug()
	{
		return entityToFacehug;
	}

	public void setEntityToFacehug(EntityLivingBase entityToFacehug)
	{
		this.entityToFacehug = entityToFacehug;
	}
}
