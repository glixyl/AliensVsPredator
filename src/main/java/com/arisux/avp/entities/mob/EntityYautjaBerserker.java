package com.arisux.avp.entities.mob;

import java.util.Random;

import com.arisux.avp.AliensVsPredator;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityYautjaBerserker extends EntityMob
{
	public EntityYautjaBerserker(World world)
	{
		super(world);
		this.experienceValue = 450;
		this.setSize(1.0F, 2.5F);
		this.getNavigator().setCanSwim(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 0.800000011920929D, true));
		this.tasks.addTask(3, new EntityAIWander(this, 0.800000011920929D));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(8, new EntityAIPanic(this, 0.800000011920929D));
		this.targetTasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntitySpeciesAlien.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityMarine.class, 0, true));
		this.targetTasks.addTask(4, new EntityAIHurtByTarget(this, true));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(180.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5199999761581421D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(10.0D);
	}

	@Override
	protected void attackEntity(Entity entity, float damage)
	{
		if (this.attackTime <= 0 && damage < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
		{
			this.attackTime = 20;
			this.attackEntityAsMob(entity);
		}

		if (damage > 2.0F && damage < 6.0F && this.rand.nextInt(10) == 0)
		{
			if (this.onGround)
			{
				double dX = entity.posX - this.posX;
				double dZ = entity.posZ - this.posZ;
				float speed = MathHelper.sqrt_double(dX * dX + dZ * dZ);
				this.motionX = dX / speed * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
				this.motionZ = dZ / speed * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
				this.motionY = 0.4000000059604645D;
			}
		}
		else
		{
			super.attackEntity(entity, damage);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		int damage = 5;

		if (this.isPotionActive(Potion.damageBoost))
		{
			damage += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
		}

		if (this.isPotionActive(Potion.weakness))
		{
			damage -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
		}

		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public int getTotalArmorValue()
	{
		return 7;
	}

	@Override
	protected void collideWithEntity(Entity par1Entity)
	{
		if (par1Entity instanceof IMob && this.getRNG().nextInt(20) == 0 && !(par1Entity instanceof EntityYautjaBerserker))
		{
			this.setAttackTarget((EntityLivingBase) par1Entity);
			this.setRevengeTarget((EntityLivingBase) par1Entity);
		}

		super.collideWithEntity(par1Entity);
	}

	@Override
	public boolean isInWater()
	{
		return this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.900000023841858D, 0.0D), Material.water, this);
	}

	@Override
	protected String getLivingSound()
	{
		return AliensVsPredator.properties().SOUND_YAUTJA_LIVING;
	}

	@Override
	protected String getHurtSound()
	{
		return AliensVsPredator.properties().SOUND_YAUTJA_HURT;
	}

	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_YAUTJA_DEATH;
	}

	@Override
	protected void dropFewItems(boolean flag, int i)
	{
		if ((new Random()).nextInt(6) == 1)
		{
			this.entityDropItem(new ItemStack(AliensVsPredator.items().itemSpear), 1);
		}
	}

	@Override
	protected void dropRareDrop(int par1)
	{
		this.entityDropItem(new ItemStack(AliensVsPredator.items().helmTitanium), 1);
		this.entityDropItem(new ItemStack(AliensVsPredator.items().plateTitanium), 1);
		this.entityDropItem(new ItemStack(AliensVsPredator.items().legsTitanium), 1);
		this.entityDropItem(new ItemStack(AliensVsPredator.items().bootsTitanium), 1);
		this.entityDropItem(new ItemStack(AliensVsPredator.items().itemWristBlade), 1);
		this.entityDropItem(new ItemStack(AliensVsPredator.items().itemPlasmaCaster), 1);
	}

	@Override
	public boolean canDespawn()
	{
		return false;
	}
}
