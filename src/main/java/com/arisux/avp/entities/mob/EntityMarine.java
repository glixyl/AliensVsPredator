package com.arisux.avp.entities.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
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

	private MarineTypes marineTypes;

	public EntityMarine(World par1World)
	{
		super(par1World);
		this.marineTypes = MarineTypes.values()[this.rand.nextInt(5)];
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
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityDrone.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityFacehugger.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityChestburster.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityOvamorph.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityQueen.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityYautja.class, 0, true));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
	}

	public MarineTypes getMarineType()
	{
		return marineTypes;
	}

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
					this.entityDropItem(new ItemStack(AliensVsPredator.INSTANCE.items.itemAmmoAC), 1);
				}
				break;
			case AK47:
				if (rand.nextInt(3) == 1)
				{
					this.entityDropItem(new ItemStack(AliensVsPredator.INSTANCE.items.itemAmmoAR), 1);
				}
				break;
			case M4A1:
				if (rand.nextInt(3) == 1)
				{
					this.entityDropItem(new ItemStack(AliensVsPredator.INSTANCE.items.itemAmmoPistol), 1);
				}
				break;
			case SNIPER:
				if (rand.nextInt(3) == 1)
				{
					this.entityDropItem(new ItemStack(AliensVsPredator.INSTANCE.items.itemAmmoSMG), 1);
				}
				break;
			case M56SG:
				if (rand.nextInt(3) == 1)
				{
					this.entityDropItem(new ItemStack(AliensVsPredator.INSTANCE.items.itemAmmoSniper), 1);
				}
				break;
		}

	}

	protected String getHurtSound()
	{
		return AliensVsPredator.INSTANCE.properties.SOUND_MARINE_HURT;
	}

	protected String getDeathSound()
	{
		return AliensVsPredator.INSTANCE.properties.SOUND_MARINE_DEATH;
	}

	public ItemStack getCurrentItemOrArmor(int par1)
	{
		return new ItemStack(AliensVsPredator.INSTANCE.items.itemM41A);
	}

	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	public int getTotalArmorValue()
	{
		return 7;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		double range = 20D;
		EntityLivingBase targetEntity = (EntityLivingBase) (this.worldObj.findNearestEntityWithinAABB(EntityLiving.class, this.boundingBox.expand((double) (range * 2), 64.0D, (double) (range * 2)), this));

		if (targetEntity instanceof EntityMob && !worldObj.isRemote)
		{
			if (targetEntity instanceof EntityXenomorph)
				targetEntity.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1F);

			this.attackEntityWithRangedAttack(targetEntity, 1F);
			this.setAttackTarget(targetEntity);
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (super.attackEntityFrom(par1DamageSource, par2))
		{
			Entity var3 = par1DamageSource.getEntity();

			if (this.riddenByEntity != var3 && this.ridingEntity != var3)
			{
				if (var3 != this)
				{
					this.entityToAttack = var3;
				}

				return true;
			} else
			{
				return true;
			}
		} else
		{
			return false;
		}
	}

	protected void attackEntity(Entity entity, float f)
	{
		if (this.attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
		{
			this.attackTime = 20;
			this.attackEntityAsMob(entity);
		}

		float f1 = this.getBrightness(1.0F);

		if (f1 > 0.5F && this.rand.nextInt(100) == 0)
		{
			this.entityToAttack = null;
		} else
		{
			if (f > 2.0F && f < 6.0F && this.rand.nextInt(10) == 0)
			{
				if (this.onGround)
				{
					double d = entity.posX - this.posX;
					double d1 = entity.posZ - this.posZ;
					float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
					this.motionX = d / (double) f2 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
					this.motionZ = d1 / (double) f2 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
					this.motionY = 0.4000000059604645D;
				}
			} else
			{
				super.attackEntity(entity, f);
			}
		}
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		int var2 = 4;

		if (this.isPotionActive(Potion.damageBoost))
		{
			var2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
		}

		if (this.isPotionActive(Potion.weakness))
		{
			var2 -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
		}

		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) var2);
	}

	public float getBlockPathWeight(int par1, int par2, int par3)
	{
		return 0.5F - this.worldObj.getLightBrightness(par1, par2, par3);
	}

	public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float f)
	{
		if (par1EntityLivingBase instanceof EntityMob && !par1EntityLivingBase.isDead)
		{
			this.getLookHelper().setLookPosition(par1EntityLivingBase.posX, par1EntityLivingBase.posY + (double) par1EntityLivingBase.getEyeHeight(), par1EntityLivingBase.posZ, 10.0F, (float) this.getVerticalFaceSpeed());

			if (this.canEntityBeSeen(par1EntityLivingBase))
			{
				this.worldObj.spawnEntityInWorld(new EntityBullet(this.worldObj, this, 1.6F, 0.25D));
			}
		}
	}

	protected void updateAITick()
	{
		this.dataWatcher.updateObject(18, Integer.valueOf(15));
	}
}
