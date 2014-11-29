package com.arisux.avp.entities.mob;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityBullet;

public class EntityMarine extends EntityCreature implements IMob, IRangedAttackMob
{
	private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 0.8D, 20, 60, 15.0F);

	public enum MarineTypes
	{
		M4(0), AK47(1), M4A1(2), SNIPER(3), M56SG(4);

		private int value;

		private MarineTypes(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}
	}

	private MarineTypes marineType;

	public EntityMarine(World par1World)
	{
		super(par1World);
		this.experienceValue = 5;
		this.dataWatcher.addObject(18, new Integer(15));
		this.dataWatcher.addObject(17, "");
		this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIWander(this, 0.800000011920929D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(3, new EntityAILookIdle(this));
		this.tasks.addTask(4, this.aiArrowAttack);
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySpeciesAlien.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityYautja.class, 0, true));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.marineType = MarineTypes.values()[this.rand.nextInt(5)];
	}
	
	public MarineTypes getMarineType()
	{
		return marineType;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4499999761581421D);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		super.dropFewItems(par1, par2);

		switch (getMarineType())
		{
			case M4:
				if (rand.nextInt(3) == 1)
				{
					this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.itemAmmoAC), 1);
				}
				break;
			case AK47:
				if (rand.nextInt(3) == 1)
				{
					this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.itemAmmoAR), 1);
				}
				break;
			case M4A1:
				if (rand.nextInt(3) == 1)
				{
					this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.itemAmmoPistol), 1);
				}
				break;
			case SNIPER:
				if (rand.nextInt(3) == 1)
				{
					this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.itemAmmoSMG), 1);
				}
				break;
			case M56SG:
				if (rand.nextInt(3) == 1)
				{
					this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.itemAmmoSniper), 1);
				}
				break;
		}

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
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	public int getTotalArmorValue()
	{
		return 10;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		double range = 20D;
		EntityLivingBase targetEntity = (EntityLivingBase) (this.worldObj.findNearestEntityWithinAABB(EntityLiving.class, this.boundingBox.expand(range * 2, 64.0D, range * 2), this));

		if (targetEntity instanceof EntityMob && !worldObj.isRemote)
		{
			if (targetEntity instanceof EntityXenomorph)
				targetEntity.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1F);

			this.attackEntityWithRangedAttack(targetEntity, 1F);
			this.setAttackTarget(targetEntity);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damage)
	{
		if (super.attackEntityFrom(damageSource, damage))
		{
			Entity damageSourceEntity = damageSource.getEntity();

			if (this.riddenByEntity != damageSourceEntity && this.ridingEntity != damageSourceEntity)
			{
				if (damageSourceEntity != this)
				{
					this.entityToAttack = damageSourceEntity;
				}

				return true;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return false;
		}
	}

	@Override
	protected void attackEntity(Entity entity, float damage)
	{
		if (this.attackTime <= 0 && damage < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
		{
			this.attackTime = 20;
			this.attackEntityAsMob(entity);
		}

		if (this.getBrightness(1.0F) > 0.5F && this.rand.nextInt(100) == 0)
		{
			this.entityToAttack = null;
		}
		else
		{
			if (damage > 2.0F && damage < 6.0F && this.rand.nextInt(10) == 0)
			{
				if (this.onGround)
				{
					double d = entity.posX - this.posX;
					double d1 = entity.posZ - this.posZ;
					float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
					this.motionX = d / f2 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
					this.motionZ = d1 / f2 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
					this.motionY = 0.4000000059604645D;
				}
			}
			else
			{
				super.attackEntity(entity, damage);
			}
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity targetEntity)
	{
		int damage = 4;

		if (this.isPotionActive(Potion.damageBoost))
		{
			damage += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
		}

		if (this.isPotionActive(Potion.weakness))
		{
			damage -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
		}

		return targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
	}

	@Override
	public float getBlockPathWeight(int posX, int posY, int posZ)
	{
		return 0.5F - this.worldObj.getLightBrightness(posX, posY, posZ);
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase targetEntity, float f)
	{
		if (targetEntity instanceof EntityMob && !targetEntity.isDead)
		{
			this.getLookHelper().setLookPosition(targetEntity.posX, targetEntity.posY + targetEntity.getEyeHeight(), targetEntity.posZ, 10.0F, this.getVerticalFaceSpeed());

			if (this.canEntityBeSeen(targetEntity))
			{
				this.worldObj.spawnEntityInWorld(new EntityBullet(this.worldObj, this, 1.6F, 0.25D));
			}
		}
	}

	@Override
	protected void updateAITick()
	{
		this.dataWatcher.updateObject(18, Integer.valueOf(15));
	}
}
