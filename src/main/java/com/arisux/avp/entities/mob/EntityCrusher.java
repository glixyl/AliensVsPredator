package com.arisux.avp.entities.mob;

import java.util.Random;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityCrusher extends EntityXenomorph
{
	@SideOnly(Side.CLIENT)
	private ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_CRUSHER);

	public EntityCrusher(World var1)
	{
		super(var1);
		this.jumpMovementFactor = 0.2F;
		this.experienceValue = 300;
		this.setSize(1.0F, 3.0F);
		this.tasks.addTask(0, new EntityAILeapAtTarget(this, 0.6F));
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIBreakDoor(this));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityMarine.class, 1.0D, true));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityYautja.class, 1.0D, false));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityAgeable.class, 1.0D, false));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityAnimal.class, 1.0D, false));
		this.tasks.addTask(4, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 0.699999988079071D, false));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(90.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5500000238418579D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	@Override
	protected void dropRareDrop(int par1)
	{
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.helmXeno), 1);
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.plateXeno), 1);
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.legsXeno), 1);
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.instance().items.bootsXeno), 1);

		super.dropRareDrop(par1);
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected String getHurtSound()
	{
		return AliensVsPredator.properties().SOUND_CRUSHER_HURT;
	}

	@Override
	protected String getLivingSound()
	{
		return AliensVsPredator.properties().SOUND_CRUSHER_LIVING;
	}

	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_CRUSHER_DEATH;
	}

	@Override
	public int getTotalArmorValue()
	{
		return 5;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ResourceLocation getResource()
	{
		return resourceLocation;
	}
}
