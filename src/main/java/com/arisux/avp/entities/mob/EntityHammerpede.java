package com.arisux.avp.entities.mob;

import java.util.ArrayList;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAcidPool;
import com.arisux.avp.entities.ai.alien.EntityAIClimb;
import com.arisux.avp.entities.ai.alien.EntityAIQueenIdentificationTask;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityHammerpede extends EntitySpeciesAlien implements IMob
{
	public EntityHammerpede(World par1World)
	{
		super(par1World);

		this.setSize(1.0F, 0.2F);
		this.experienceValue = 16;
		this.getNavigator().setCanSwim(true);
		this.getNavigator().setAvoidsWater(false);
		this.tasks.addTask(2, new EntityAIWander(this, 0.8D));
		
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

		this.attackAI();

		if(this.getAttackTarget() == null)
		{
			if(this.worldObj.getWorldTime() % 100 == 0)
			{
				this.lurkInBlackGoo();
			}
		}

		if(this.getAttackTarget() != null && this.getAttackTarget().isDead)
		{
			this.setAttackTarget(null);
		}

		else if(this.getAttackTarget() != null)
		{
			if(this.getAttackTarget() instanceof EntityPlayer)
			{
				EntityPlayer target = (EntityPlayer) this.getAttackTarget();
				if(target.capabilities.isCreativeMode)
				{
					this.setAttackTarget(null);
					return;
				}
				else
				{
					if(this.getDistance(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ) < 3)
					{
						this.attackEntityAsMob(this.getAttackTarget());
					}
				}
			}
			if(this.getDistance(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ) < 3)
			{
				this.attackEntityAsMob(this.getAttackTarget());
			}
		}
	}

	public void lurkInBlackGoo()
	{	
		double range = this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();
		ArrayList<CoordData> coordData = WorldUtil.Blocks.getCoordDataInRangeForBlocks((int) this.posX, (int) this.posY, (int) this.posZ, (int) range, this.worldObj, AliensVsPredator.blocks().blockBlackGoo);
		if(coordData.size() > 0)
		{
			this.getNavigator().tryMoveToXYZ((double) coordData.get(0).posX, (double) coordData.get(0).posY, (double) coordData.get(0).posZ, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
		}
	}

	public void attackAI()
	{
		if (worldObj.getWorldInfo().getWorldTime() % 70 == 0)
		{
			double range = this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();
			Entity targetEntity = (this.worldObj.findNearestEntityWithinAABB(EntityLiving.class, this.boundingBox.expand(range * 10, 64.0D, range * 10), this));
			Entity targetPlayer = (this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand(range * 10, 64.0D, range * 10), this));
			if (targetPlayer != null && !((EntityPlayer) targetPlayer).capabilities.isCreativeMode)
			{
				this.setAttackTarget((EntityLivingBase) targetPlayer);
				this.getNavigator().tryMoveToEntityLiving(targetPlayer, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
				if (this.isCollidedHorizontally)
				{
					this.addVelocity(0, 0.6D, 0);
				}
			}
			else if (targetEntity != null && !(targetEntity instanceof EntityAcidPool) && !(targetEntity instanceof EntitySpeciesAlien))
			{
				this.setAttackTarget((EntityLivingBase) targetEntity);
				this.getNavigator().tryMoveToEntityLiving(targetEntity, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
				if (this.isCollidedHorizontally)
				{
					this.addVelocity(0, 0.6D, 0);
				}
			}
			else
			{
				this.setAttackTarget(null);
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
