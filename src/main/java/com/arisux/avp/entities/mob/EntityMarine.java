package com.arisux.avp.entities.mob;

import java.util.ArrayList;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.airi.lib.WorldUtil.Entities;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityBullet;
import com.arisux.avp.util.MarineTypes;

@SuppressWarnings("all")
public class EntityMarine extends EntityCreature implements IMob, IRangedAttackMob
{
	private MarineTypes marineType;
	private ArrayList<Class<? extends Entity>> targetList = new ArrayList();
	private EntityAIBase aiRangedAttack;

	public EntityMarine(World worldObj)
	{
		this(worldObj, MarineTypes.M4);
	}
	
	public EntityMarine(World worldObj, MarineTypes marineType)
	{
		super(worldObj);
		this.aiRangedAttack = new EntityAIArrowAttack(this, 0.1D, (int) getMarineType().getFirearmItem().getFirerate(), 24);

		this.experienceValue = 5;
		this.dataWatcher.addObject(18, new Integer(15));
		this.dataWatcher.addObject(17, "");
		this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
		this.tasks.addTask(1, this.aiRangedAttack);
		this.tasks.addTask(2, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIWander(this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(2, new EntityAILookIdle(this));
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
	}

	public boolean isAcceptableTarget(Entity entity)
	{
		ArrayList<Class<? extends Entity>> targetList = new ArrayList();
		targetList.add(EntitySpeciesAlien.class);

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
		
		EntityLivingBase targetEntity = (EntityLivingBase) Entities.getEntityInCoordsRange(this.worldObj, EntityLiving.class, new CoordData(this), 32, 32);

		if (targetEntity != null && !targetEntity.isDead && isAcceptableTarget(targetEntity))
		{
			this.setAttackTarget(targetEntity);
		}
		else
		{
			this.setAttackTarget(null);
		}
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase targetEntity, float danage)
	{
		EntityBullet entityBullet = new EntityBullet(this.worldObj, this, targetEntity, 10F, danage);
		this.worldObj.spawnEntityInWorld(entityBullet);
		this.playSound(getMarineType().getGunfireSound(), 0.7F, 1F);
		this.worldObj.spawnParticle("largesmoke", this.posX, this.posY + this.getEyeHeight(), this.posZ, 1, 1, 1);
	}
}
