package com.arisux.avp.entities.mob;

import java.util.ArrayList;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.airi.lib.WorldUtil.Entities;
import com.arisux.avp.entities.EntityAcidPool;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityHammerpede extends EntitySpeciesAlien implements IMob
{
	public static IEntitySelector entitySelector = new IEntitySelector()
	{
		@Override
		public boolean isEntityApplicable(Entity entity)
		{
			return !(entity instanceof EntitySpeciesAlien) && !(entity instanceof EntityHammerpede) && !(entity instanceof EntityAcidPool);
		}
	};
	
	public EntityHammerpede(World par1World)
	{
		super(par1World);

		this.setSize(0.5F, 0.5F);
		this.experienceValue = 16;
		this.getNavigator().setCanSwim(true);
		this.getNavigator().setAvoidsWater(false);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.8D, true));
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, Entity.class, 10 /** targetChance **/, false /** checkSight **/, false /** nearbyOnly **/, entitySelector));
	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(14.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5499999761581421D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.5D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
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
		
		this.fallDistance = 0F;
		
		this.lurkInBlackGoo();
		
		if (this.getAttackTarget() == null && this.worldObj.getWorldTime() % 60 == 0 && this.rand.nextInt(3) == 0)
		{
			ArrayList<EntityLivingBase> entities = (ArrayList<EntityLivingBase>) WorldUtil.Entities.getEntitiesInCoordsRange(worldObj, EntityLivingBase.class, new CoordData(this), (int)this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue() / 2);
		
			for (EntityLivingBase entity : entities)
			{
				if (entitySelector.isEntityApplicable(entity) && Entities.canEntityBeSeenBy(entity, this) && (!entitySelector.isEntityApplicable(entity.getLastAttacker()) && (entity.ticksExisted - entity.getLastAttackerTime() > 150) ))
				{
					if (entity instanceof EntityPlayer && !((EntityPlayer) entity).capabilities.isCreativeMode)
					
					this.setAttackTarget(entity);
				}
			}
		}
	}

	public void lurkInBlackGoo()
	{
		if (this.getAttackTarget() == null)
		{
			if (this.worldObj.getWorldTime() % 40 == 0 && this.rand.nextInt(4) == 0)
			{
				if (this.worldObj.getBlock((int) this.posX, (int) this.posY, (int) this.posZ) != AliensVsPredator.blocks().blockBlackGoo)
				{
					double range = this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue() / 2;
					ArrayList<CoordData> coordData = WorldUtil.Blocks.getCoordDataInRangeForBlocks((int) this.posX, (int) this.posY, (int) this.posZ, (int) range, this.worldObj, AliensVsPredator.blocks().blockBlackGoo);

					if (coordData.size() > 0)
					{
						CoordData selectedCoord = coordData.get(this.rand.nextInt(coordData.size()));
						this.getNavigator().tryMoveToXYZ((double) selectedCoord.posX, (double) selectedCoord.posY, (double) selectedCoord.posZ, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
					}
				}
			}
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
	protected void attackEntity(Entity entity, float damage)
	{
		super.attackEntity(entity, damage);
	}

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
