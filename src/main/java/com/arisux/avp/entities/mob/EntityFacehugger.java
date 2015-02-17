package com.arisux.avp.entities.mob;

import java.util.ArrayList;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.extended.ExtendedEntityLivingBase;

public class EntityFacehugger extends EntitySpeciesAlien implements IMob
{
	private boolean isFertile;
	
	public EntityFacehugger(World world)
	{
		super(world);
		this.isFertile = true;
		this.setSize(0.9F, 0.9F);
		this.experienceValue = 10;
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.8999999761581421D, false));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityAnimal.class, 0.8999999761581421D, true));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityCreeper.class, 0.8999999761581421D, true));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityMarine.class, 0.8999999761581421D, true));
		this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.8999999761581421D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(5, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(6, new EntityAIWander(this, 0.4000000059604645D));
		this.tasks.addTask(7, new EntityAIAttackOnCollide(this, EntityYautja.class, 0.8999999761581421D, true));
		this.tasks.addTask(8, new EntityAISwimming(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCreeper.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMarine.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityYautja.class, 0, true));
		this.addTargetType(EntityCreeper.class);
		this.addTargetType(EntityPlayer.class);
		this.addTargetType(EntityAnimal.class);
		this.addTargetType(EntityMarine.class);
		this.addTargetType(EntityYautja.class);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(14.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6999999761581421D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.50D);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		ArrayList<EntityItem> entityItemList = (ArrayList<EntityItem>) WorldUtil.Entities.getEntitiesInCoordsRange(worldObj, EntityItem.class, new com.arisux.airi.lib.WorldUtil.Blocks.CoordData(this), 8);

		for (EntityItem entityItem : entityItemList)
		{
			if (entityItem.delayBeforeCanPickup <= 0)
			{
				ItemStack stack = entityItem.getDataWatcher().getWatchableObjectItemStack(10);

				if (stack.getItem() == AliensVsPredator.instance().items.itemRoyalJelly)
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

	@Override
	public void onCollideWithPlayer(EntityPlayer p_70100_1_)
	{
		super.onCollideWithPlayer(p_70100_1_);
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
		leaper.motionX += rX / sq * 0.5D * 0.800000011920929D + leaper.motionX * 0.20000000298023224D;
		leaper.motionZ += rZ / sq * 0.5D * 0.800000011920929D + leaper.motionZ * 0.20000000298023224D;
		leaper.motionY = leapMotionY;
	}

	@Override
	public void collideWithEntity(Entity entity)
	{
		if (this.isFertile && entity instanceof EntityLivingBase)
		{
			EntityLivingBase entityLiving = (EntityLivingBase) entity;
			ExtendedEntityLivingBase extendedLiving = (ExtendedEntityLivingBase) entityLiving.getExtendedProperties(ExtendedEntityLivingBase.IDENTIFIER);

			if (entityLiving instanceof EntityPlayer)
			{
				if (!((EntityPlayer) entityLiving).capabilities.isCreativeMode)
				{
					this.mountEntity(entityLiving);
					extendedLiving.setContainsEmbryo(true);
					this.isFertile = false;
				}
			}
			else
			{
				this.mountEntity(entity);
				extendedLiving.setContainsEmbryo(true);
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
		return 0.8F;
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
}
