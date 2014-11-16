package com.arisux.avp.entities.mob;

import java.util.Random;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.arisux.airi.AIRI;
import com.arisux.airi.engine.BlockLib.CoordData;
import com.arisux.airi.engine.WorldEngine;
import com.arisux.avp.AliensVsPredator;

public class EntityDrone extends EntityXenomorph
{
	private static final ResourceLocation texBasic = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_DRONE_BASIC);
	private static final ResourceLocation texAdv = new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_DRONE_ADVANCED);
	public int mobType;
	public EntityQueen targetQueen;

	public EntityDrone(World world)
	{
		super(world);

		this.jumpMovementFactor = 0.1F;
		this.experienceValue = 100;
		this.setSize(0.8F, 1.8F);
		this.mobType = this.rand.nextInt(2);
		this.tasks.addTask(0, new EntityAILeapAtTarget(this, 0.6F));
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIBreakDoor(this));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityMarine.class, 1.0D, true));
		this.tasks.addTask(5, new EntityAIAttackOnCollide(this, EntityYautja.class, 1.0D, false));
		this.tasks.addTask(6, new EntityAIAttackOnCollide(this, EntityAgeable.class, 1.0D, false));
		this.tasks.addTask(7, new EntityAIAttackOnCollide(this, EntityAnimal.class, 1.0D, false));
		this.tasks.addTask(8, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(10, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityYautja.class, 0, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, false));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityMarine.class, 0, false));
		this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityAgeable.class, 0, false));
		this.setEvolveTo(EntityWarrior.class, 12);
	}

	@Override
	public ResourceLocation getResource()
	{
		switch (this.mobType)
		{
			case 0:
				return texBasic;

			case 1:
				return texAdv;

			default:
				return null;
		}
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5000000238418579D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
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
	protected String getHurtSound()
	{
		return AliensVsPredator.properties().SOUND_ALIEN_HURT;
	}

	@Override
	protected String getLivingSound()
	{
		return AliensVsPredator.properties().SOUND_ALIEN_LIVING;
	}

	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_ALIEN_DEATH;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.targetQueen != null && !this.targetQueen.isDead)
		{
			this.pathToQueen(this.targetQueen);
		}
		else
		{
			this.targetQueen = findQueen();
		}

	}

	public EntityQueen findQueen()
	{
		EntityQueen queen;
		Entity ent = WorldEngine.Entities.getEntityInCoordsRange(this.worldObj, EntityQueen.class, new CoordData(this), 50);
		if (ent instanceof EntityQueen)
		{
			queen = (EntityQueen) ent;
			AIRI.logger.info("Queen found at " + queen.posX + ", " + queen.posY + ", " + queen.posZ);
		}
		else
		{
			queen = null;
		}
		return queen;
	}

	public void pathToQueen(EntityQueen q)
	{
		if (!q.isDead)
		{
			this.getNavigator().tryMoveToEntityLiving(q, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * 2.5D);
		}
		else
		{
			this.targetQueen = null;
		}
		// this.getNavigator().tryMoveToXYZ(q.posX, q.posY, q.posZ, 0.6);
		AIRI.logger.info("Pathing to queen at " + q.posX + ", " + q.posY + ", " + q.posZ);
	}
}
