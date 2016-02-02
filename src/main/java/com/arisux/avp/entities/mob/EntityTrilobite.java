package com.arisux.avp.entities.mob;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.ai.alien.EntitySelectorXenomorph;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityTrilobite extends EntitySpeciesAlien implements IMob
{
	public EntityTrilobite(World world)
	{
		super(world);

		this.setSize(1.5F, 1.5F);
		this.experienceValue = 32;
		this.getNavigator().setCanSwim(true);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.800000011920929D, true));
		this.tasks.addTask(1, new EntityAIWander(this, 0.800000011920929D));
		this.targetTasks.addTask(1, new EntityAILeapAtTarget(this, 0.8F));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, Entity.class, /** targetChance **/0, /** shouldCheckSight **/false, /** nearbyOnly **/false, EntitySelectorXenomorph.instance));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(44.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5499999761581421D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
	}

	/* @Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte) 0));
	} */

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		this.fallDistance = 0F;
	}

	/* protected Entity findPlayerToAttack(EntityPlayer entityplayer)
	{
		float brightness = this.getBrightness(1.0F);

		if (brightness < 0.5F)
		{
			double range = 40.0D;
			return this.worldObj.getClosestVulnerablePlayerToEntity(this, range);
		} else
		{
			return null;
		}
	} */

	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_FACEHUGGER_DEATH;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return true;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public boolean isOnLadder()
	{
		return this.isCollidedHorizontally;
	}

	public boolean isClimbing()
	{
		return this.isOnLadder() && this.motionY > 1.0099999997764826D;
	}

	/* @Override
	protected void attackEntity(Entity entity, float f)
	{
		if (f > 2.0F && f < 6.0F && this.rand.nextInt(50) == 0)
		{
			if (this.onGround)
			{
				double var4 = entity.posX - this.posX;
				double var6 = entity.posZ - this.posZ;
				float var8 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
				this.motionX = var4 / var8 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
				this.motionZ = var6 / var8 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
				this.motionY = 0.4000000059604645D;
			}
		} else
		{
			super.attackEntity(entity, f);
		}
	} */

	@Override
	public boolean isPotionApplicable(PotionEffect potionEffect)
	{
		return potionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(potionEffect);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
	}
}
