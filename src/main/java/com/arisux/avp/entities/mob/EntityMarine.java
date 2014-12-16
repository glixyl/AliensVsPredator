package com.arisux.avp.entities.mob;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.arisux.airi.lib.WorldUtil.Entities;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAcidPool;
import com.arisux.avp.entities.EntityBullet;
import com.arisux.avp.util.MarineTypes;

public class EntityMarine extends EntityCreature implements IMob, IRangedAttackMob
{
	private MarineTypes marineType;
	private ArrayList<Class<? extends Entity>> targetList = new ArrayList<Class<? extends Entity>>();
	private ArrayList<Class<? extends Entity>> safeList = new ArrayList<Class<? extends Entity>>();
	private EntityAIBase aiRangedAttack;

	public EntityMarine(World world)
	{
		super(world);
		this.marineType = MarineTypes.getTypeForId(new Random(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())).nextInt(MarineTypes.values().length));
		this.aiRangedAttack = new EntityAIArrowAttack(this, 0.4D, (int) getMarineType().getFirearmItem().getFirerate(), 24);
		this.experienceValue = 5;
		this.dataWatcher.addObject(18, new Integer(15));
		this.dataWatcher.addObject(17, "");
		this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
		this.tasks.addTask(1, this.aiRangedAttack);
		this.tasks.addTask(2, new EntityAIWander(this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
		this.tasks.addTask(3, new EntityAISwimming(this));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.applyTargets();
	}

	public MarineTypes getMarineType()
	{
		return marineType;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6499999761581421D);
	}

	private void applyTargets()
	{
		targetList.add(EntitySpeciesAlien.class);
		targetList.add(EntityMob.class);
		targetList.add(EntityYautja.class);
		safeList.add(EntityAcidPool.class);
		safeList.add(EntityPlayer.class);
		safeList.add(EntityMarine.class);
	}

	public boolean isAcceptableTarget(Entity entity)
	{
		for (Class<? extends Entity> entityClass : safeList)
		{
			if (entityClass.isInstance(entity))
			{
				return false;
			}
		}

		for (Class<? extends Entity> entityClass : targetList)
		{
			if (entityClass.isInstance(entity))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	protected void dropFewItems(boolean hitByPlayer, int lootingLevel)
	{
		super.dropFewItems(hitByPlayer, lootingLevel);

		if (hitByPlayer)
		{
			this.entityDropItem(new ItemStack(this.getMarineType().getFirearmItem().getAmmoItem()), this.rand.nextInt(3));
		}
	}

	@Override
	public float getBlockPathWeight(int posX, int posY, int posZ)
	{
		return 0.5F - this.worldObj.getLightBrightness(posX, posY, posZ);
	}

	@Override
	public int getTotalArmorValue()
	{
		return 10;
	}

	@Override
	protected String getHurtSound()
	{
		return AliensVsPredator.properties().SOUND_MARINE_HURT;
	}

	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_MARINE_DEATH;
	}

	@Override
	public ItemStack getHeldItem()
	{
		return new ItemStack(AliensVsPredator.instance().items.itemM41A);
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void updateAITick()
	{
		super.updateAITick();

		this.dataWatcher.updateObject(18, Integer.valueOf(15));
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.getAttackTarget() != null && this.getAttackTarget().isDead)
		{
			this.setAttackTarget(null);
		}

		if (this.getAttackTarget() == null || this.getAttackTarget().isDead || this.getAttackTarget() != null && !Entities.canEntityBeSeenBy(this.getAttackTarget(), this))
		{
			for (Class<? extends Entity> cls : this.targetList)
			{
				EntityLivingBase foundEntity = (EntityLivingBase) (this.worldObj.findNearestEntityWithinAABB(cls, this.boundingBox.expand(32D * 2, 64.0D, 32D * 2), this));

				if (foundEntity != null)
				{
					if (isAcceptableTarget(foundEntity))
					{
						this.setAttackTarget(foundEntity);
					}
				}
			}
		}

		if (this.getAttackTarget() != null)
		{
			if (this.getAttackTarget().getDistanceToEntity(this) > 20 || Entities.canEntityBeSeenBy(this.getAttackTarget(), this))
			{
				this.getNavigator().tryMoveToEntityLiving(this.getAttackTarget(), this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
			}
		}
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase targetEntity, float damage)
	{
		if (this.getAttackTarget() != null)
		{
			if (this.getEntitySenses().canSee(this.getAttackTarget()))
			{
				EntityBullet entityBullet = new EntityBullet(this.worldObj, this, targetEntity, 10F, 0.0000001F);
				this.worldObj.spawnEntityInWorld(entityBullet);
				this.playSound(getMarineType().getGunfireSound(), 0.7F, 1F);
				this.worldObj.spawnParticle("largesmoke", this.posX, this.posY + this.getEyeHeight(), this.posZ, 1, 1, 1);
			}
		}
	}
}
