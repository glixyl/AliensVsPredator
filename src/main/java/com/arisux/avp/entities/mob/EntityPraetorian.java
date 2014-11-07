package com.arisux.avp.entities.mob;

import java.util.Random;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;

public class EntityPraetorian extends EntityXenomorph
{
	private ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_PRAETORIAN);

	public EntityPraetorian(World var1)
	{
		super(var1);
		this.experienceValue = 300;
		this.setSize(1.0F, 3.0F);
		this.tasks.addTask(0, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.6F));
		this.tasks.addTask(2, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAIBreakDoor(this));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(5, new EntityAIAttackOnCollide(this, EntityMarine.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAIAttackOnCollide(this, EntityYautja.class, 1.0D, false));
		this.tasks.addTask(7, new EntityAIAttackOnCollide(this, EntityAgeable.class, 1.0D, false));
		this.tasks.addTask(8, new EntityAIAttackOnCollide(this, EntityAnimal.class, 1.0D, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityYautja.class, 0, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMarine.class, 0, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityAgeable.class, 0, false));
		this.setEvolveTo(EntityQueen.class, 100);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5500000238418579D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
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

	protected boolean isAIEnabled()
	{
		return true;
	}

	protected String getHurtSound()
	{
		return AliensVsPredator.INSTANCE.properties.SOUND_PRAETORIAN_HURT;
	}

	protected String getLivingSound()
	{
		return AliensVsPredator.INSTANCE.properties.SOUND_PRAETORIAN_LIVING;
	}

	protected String getDeathSound()
	{
		return AliensVsPredator.INSTANCE.properties.SOUND_PRAETORIAN_DEATH;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@Override
	public ResourceLocation getResource()
	{
		return this.resourceLocation;
	}
}