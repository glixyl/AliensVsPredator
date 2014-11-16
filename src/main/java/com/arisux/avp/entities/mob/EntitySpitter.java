package com.arisux.avp.entities.mob;

import java.util.Random;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAcidSpit;

public class EntitySpitter extends EntityXenomorph implements IRangedAttackMob
{
	private ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_SPITTER);

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

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5500000238418579D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected String getHurtSound()
	{
		return AliensVsPredator.properties().SOUND_SPITTER_HURT;
	}

	@Override
	protected String getLivingSound()
	{
		return AliensVsPredator.properties().SOUND_SPITTER_LIVING;
	}

	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_SPITTER_DEATH;
	}

	@Override
	public int getTotalArmorValue()
	{
		return 2;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float f)
	{
		if (!par1EntityLivingBase.isDead)
		{
			this.getLookHelper().setLookPosition(par1EntityLivingBase.posX, par1EntityLivingBase.posY + par1EntityLivingBase.getEyeHeight(), par1EntityLivingBase.posZ, 10.0F, this.getVerticalFaceSpeed());

			if (this.canEntityBeSeen(par1EntityLivingBase))
			{
				int attackDamage = 2;

				EntityAcidSpit entityacid = new EntityAcidSpit(this.worldObj, this, par1EntityLivingBase, 1.6F, 14 - attackDamage * 4);
				entityacid.setDamage(f * 2.0F + this.rand.nextGaussian() * 0.25D + attackDamage * 0.11F);
				this.worldObj.spawnEntityInWorld(entityacid);
			}
		}
	}

	@Override
	protected void dropRareDrop(int par1)
	{
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.instance.items.helmXeno), 1);
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.instance.items.plateXeno), 1);
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.instance.items.legsXeno), 1);
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.instance.items.bootsXeno), 1);

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
