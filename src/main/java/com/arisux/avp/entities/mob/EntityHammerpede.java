package com.arisux.avp.entities.mob;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;

public class EntityHammerpede extends EntitySpeciesAlien implements IMob
{
	protected Minecraft mc;
	
	public EntityHammerpede(World par1World)
	{
		super(par1World);

		this.setSize(1.0F, 0.2F);
		this.experienceValue = 16;
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.5699999928474426D, false));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityMarine.class, 0.5699999928474426D, true));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityAnimal.class, 0.5699999928474426D, true));
		this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.5699999928474426D));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.targetTasks.addTask(6, new EntityAIHurtByTarget(this, false));
		this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
		this.targetTasks.addTask(8, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, true));
		this.targetTasks.addTask(10, new EntityAINearestAttackableTarget(this, EntityMarine.class, 0, true));
		this.targetTasks.addTask(11, new EntityAINearestAttackableTarget(this, EntityYautja.class, 0, true));
		this.tasks.addTask(12, new EntityAIWander(this, 0.4000000059604645D));
		this.tasks.addTask(13, new EntityAIAttackOnCollide(this, EntityYautja.class, 0.5699999928474426D, true));
		this.tasks.addTask(14, new EntityAISwimming(this));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(14.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6499999761581421D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.5D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte) 0));
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	protected Entity findPlayerToAttack(EntityPlayer entityplayer)
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
	}

	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_CHESTBURSTER_DEATH;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
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

	@Override
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
	}

	@Override
	public boolean isPotionApplicable(PotionEffect par1PotionEffect)
	{
		return par1PotionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(par1PotionEffect);
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
