package com.arisux.avp.entities.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAcidPool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityRoyalFacehugger extends EntityFacehugger implements IMob
{
	public boolean ableToInfect;

	public EntityRoyalFacehugger(World par1World)
	{
		super(par1World);
		this.setSize(0.9F, 0.9F);
		this.ableToInfect = true;
		this.experienceValue = 35;
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.8999999761581421D, false));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.8999999761581421D, true));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityMarine.class, 0.8999999761581421D, true));
		this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.8999999761581421D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(5, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(6, new EntityAIWander(this, 0.4000000059604645D));
		this.tasks.addTask(7, new EntityAIAttackOnCollide(this, EntityYautja.class, 0.8999999761581421D, true));
		this.tasks.addTask(8, new EntityAISwimming(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityMarine.class, 0, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityYautja.class, 0, true));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(45.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		super.onUpdate();
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	public void collideWithEntity(Entity par1Entity)
	{
		if (!this.worldObj.isRemote && par1Entity.riddenByEntity == null && par1Entity instanceof EntityMarine)
		{
			this.mountEntity(par1Entity);
		}
	}

	/**
	 * Called by a player entity when they collide with an entity
	 */
	public void onCollideWithPlayer(EntityPlayer par1Entity)
	{
		if (!this.worldObj.isRemote && par1Entity.riddenByEntity == null && !par1Entity.capabilities.isCreativeMode)
		{
			this.mountEntity(par1Entity);
		}
	}

	/**
	 * Called when the mob's health reaches 0.
	 */
	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);

		if (this.worldObj.isRemote)
		{
			EntityAcidPool entity = new EntityAcidPool(this.worldObj);
			double d = this.posX;
			double d1 = this.posY;
			double d2 = this.posZ;
			entity.setLocationAndAngles(d, d1, d2, 0.0F, 0.0F);
			this.worldObj.spawnEntityInWorld(entity);
		}
	}

	public float facehuggerScaleAmount()
	{
		return 1.15F;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	protected boolean isAIEnabled()
	{
		return true;
	}

	@SideOnly(Side.SERVER)
	public void updateRiderPosition()
	{
		this.ridingEntity.setPositionAndRotation(0.8999999761581421D, 0.9375D, 0.10000000149011612D, 0.1F, 90.1F);
	}

	/**
	 * Returns the Y Offset of this entity.
	 */
	public double getYOffset()
	{
		return -1.2D;
	}

	/**
	 * Returns the current armor value as determined by a call to
	 * InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return 4;
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks
	 * they walk on. used for spiders and wolves to prevent them from
	 * trampling crops
	 */
	protected boolean canTriggerWalking()
	{
		return false;
	}

	protected Entity findPlayerToAttack(EntityPlayer entityplayer)
	{
		float f = this.getBrightness(1.0F);

		if (f < 0.5F)
		{
			double d = 40.0D;
			return this.worldObj.getClosestVulnerablePlayerToEntity(this, d);
		} else
		{
			return null;
		}
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return AliensVsPredator.INSTANCE.properties.SOUND_FACEHUGGER_DEATH;
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away
	 * entities
	 */
	protected boolean canDespawn()
	{
		return false;
	}

	/**
	 * returns true if this entity is by a ladder, false otherwise
	 */
	public boolean isOnLadder()
	{
		return this.isCollidedHorizontally;
	}

	public boolean isClimbing()
	{
		return this.isOnLadder() && this.motionY > 1.0099999997764826D;
	}

	/**
	 * Basic mob attack. Default to touch of death in EntityCreature.
	 * Overridden by each mob to define their attack.
	 */
	protected void attackEntity(Entity entity, float f)
	{
		if (f > 2.0F && f < 6.0F && this.rand.nextInt(50) == 0)
		{
			if (this.onGround)
			{
				this.jump();
				double var4 = entity.posX - this.posX;
				double var6 = entity.posZ - this.posZ;
				float var8 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
				this.motionX = var4 / (double) var8 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
				this.motionZ = var6 / (double) var8 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
				this.motionY = 0.4000000059604645D;
			}
		} else
		{
			super.attackEntity(entity, f);
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to
	 * NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from
	 * NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
	}

	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	public boolean isPotionApplicable(PotionEffect par1PotionEffect)
	{
		return par1PotionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(par1PotionEffect);
	}

	/**
	 * This method gets called when the entity kills another one.
	 */
	public void onKillEntity(EntityLivingBase par1EntityLivingBase)
	{
		super.onKillEntity(par1EntityLivingBase);

		if (par1EntityLivingBase instanceof EntityYautja)
		{
			EntityChestburster entity = new EntityChestburster(this.worldObj);
			double d = this.posX;
			double d1 = this.posY;
			double d2 = this.posZ;
			entity.setLocationAndAngles(d, d1, d2, 0.0F, 0.0F);
			this.worldObj.spawnEntityInWorld(entity);

			for (int i = 0; i < 8; ++i)
			{
				this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}

			this.setDead();
		}
	}
}