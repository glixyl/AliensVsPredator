package com.arisux.avp.entities.mob;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Entities;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.util.HostParasiteTypes;

public class EntityChestburster extends EntitySpeciesAlien implements IMob
{
	protected Minecraft mc;
	private int parasiteType;
	
	public EntityChestburster(World par1World)
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

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		ArrayList<EntityItem> entityItemList = (ArrayList<EntityItem>) WorldUtil.Entities.getEntitiesInCoordsRange(worldObj, EntityItem.class, new com.arisux.airi.lib.WorldUtil.Blocks.CoordData(this), 8);

		for (EntityItem entityItem : entityItemList)
		{
			if (entityItem.delayBeforeCanPickup <= 0)
			{
				ItemStack stack = entityItem.getDataWatcher().getWatchableObjectItemStack(10);

				if (stack.getItem() == AliensVsPredator.instance().items.itemRoyalJelly)
				{
					this.getNavigator().setPath(this.getNavigator().getPathToEntityLiving(entityItem), 1);

					if (this.getDistanceToEntity(entityItem) < 1)
					{
						this.ticksExisted += 1000;
						entityItem.setDead();
					}
					break;
				}
			}
		}
		
		if (!this.worldObj.isRemote)
		{
			if (this.ticksExisted >= this.getMaxParasiteAge())
			{
				EntityXenomorph entityxeno = (EntityXenomorph) Entities.constructEntity(this.worldObj, this.getGrownParasiteType());
				double d = this.posX;
				double d1 = this.posY;
				double d2 = this.posZ;
				entityxeno.setLocationAndAngles(d, d1, d2, 0.0F, 0.0F);
				this.worldObj.spawnEntityInWorld(entityxeno);

				for (int i = 0; i < 8; ++i)
				{
					this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
				}

				this.setDead();
			}
		}
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
	
	public void setHostParasiteType(HostParasiteTypes hostParasiteType)
	{
		this.parasiteType = hostParasiteType.id;
	}
	
	public HostParasiteTypes getHostParasiteTypeMap()
	{
		HostParasiteTypes hostParasiteType = HostParasiteTypes.get(parasiteType);
		
		if (hostParasiteType == null)
		{
			return HostParasiteTypes.NORMAL;
		}
		
		return hostParasiteType;
	}
	
	public Class<? extends EntityXenomorph> getGrownParasiteType()
	{
		return this.getHostParasiteTypeMap().getParasiteType();
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.parasiteType = nbt.getInteger("parasiteType");
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("parasiteType", this.parasiteType);
	}
	
	public int getMaxParasiteAge()
	{
		return 18000;
	}
}
