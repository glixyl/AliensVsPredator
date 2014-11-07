package com.arisux.avp.entities.mob;

import java.util.Random;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAcidSpit;

public class EntitySpitter extends EntityXenomorph implements IRangedAttackMob
{
	private ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_SPITTER);

	public EntitySpitter(World par1World)
	{
		super(par1World);
		this.experienceValue = 275;
		this.setSize(1.0F, 3.0F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.6F));
		// this.tasks.addTask(2, new EntityAIAttackOnCollide(this,
		// EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(2, new EntityAIArrowAttack(this, this.getAIMoveSpeed(), 30, 15F));
		// this.tasks.addTask(3, new EntityAIAttackOnCollide(this,
		// EntityMarine.class, 1.0D, true));
		// this.tasks.addTask(4, new EntityAIAttackOnCollide(this,
		// EntityYautja.class, 1.0D, false));
		// this.tasks.addTask(5, new EntityAIAttackOnCollide(this,
		// EntityAgeable.class, 1.0D, false));
		// this.tasks.addTask(6, new EntityAIAttackOnCollide(this,
		// EntityAnimal.class, 1.0D, false));
		this.tasks.addTask(7, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityYautja.class, 0, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, false));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityMarine.class, 0, false));
		this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityAgeable.class, 0, false));

	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5500000238418579D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
	}

	protected boolean isAIEnabled()
	{
		return true;
	}

	protected String getHurtSound()
	{
		return AliensVsPredator.INSTANCE.properties.SOUND_SPITTER_HURT;
	}

	protected String getLivingSound()
	{
		return AliensVsPredator.INSTANCE.properties.SOUND_SPITTER_LIVING;
	}

	protected String getDeathSound()
	{
		return AliensVsPredator.INSTANCE.properties.SOUND_SPITTER_DEATH;
	}

	public int getTotalArmorValue()
	{
		return 2;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float f)
	{
		if (!par1EntityLivingBase.isDead)
		{
			this.getLookHelper().setLookPosition(par1EntityLivingBase.posX, par1EntityLivingBase.posY + (double) par1EntityLivingBase.getEyeHeight(), par1EntityLivingBase.posZ, 10.0F, (float) this.getVerticalFaceSpeed());

			if (this.canEntityBeSeen(par1EntityLivingBase))
			{
				int attackDamage = 2;

				EntityAcidSpit entityacid = new EntityAcidSpit(this.worldObj, this, par1EntityLivingBase, 1.6F, (float) (14 - attackDamage * 4));
				entityacid.setDamage((double) (f * 2.0F) + this.rand.nextGaussian() * 0.25D + (double) ((float) attackDamage * 0.11F));
				this.worldObj.spawnEntityInWorld(entityacid);
			}
		}
	}

	@Override
	protected void dropRareDrop(int par1)
	{
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.INSTANCE.items.helmXeno), 1);
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.INSTANCE.items.plateXeno), 1);
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.INSTANCE.items.legsXeno), 1);
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.INSTANCE.items.bootsXeno), 1);

		super.dropRareDrop(par1);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.getAttackTarget() != null && !(this.getAttackTarget() instanceof EntityXenomorph) && this.getDistanceToEntity(this.getAttackTarget()) < 30)
		{
			// this.attackEntityWithRangedAttack(this.getAttackTarget(),
			// 1F);
			this.getMoveHelper().setMoveTo(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ, 1D);
		}
	}

	@Override
	public ResourceLocation getResource()
	{
		return resourceLocation;
	}
}
