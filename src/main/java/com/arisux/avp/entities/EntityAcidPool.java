package com.arisux.avp.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.arisux.avp.damagesource.DamageSourceAcid;

public class EntityAcidPool extends EntityMob implements IMob
{
	public EntityAcidPool(World var1)
	{
		super(var1);
		this.isImmuneToFire = false;
		this.ignoreFrustumCheck = true;
		this.setSize(0.1F, 0.1F);
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
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return false;
	}

	@Override
	public boolean canBePushed()
	{
		return false;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public boolean isInRangeToRenderDist(double var1)
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (!this.worldObj.isRemote)
		{
			double range = 0.7;
			EntityPlayer targetPlayer = (EntityPlayer) (this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand(range * 2, 0.1D, range * 2), this));

			if (targetPlayer != null)
			{
				this.setAttackTarget(targetPlayer);
				targetPlayer.attackEntityFrom(DamageSourceAcid.causeAcidicDamage(this, targetPlayer), 4F);
			}
		}

		this.worldObj.spawnParticle("smoke", this.posX + this.rand.nextDouble(), this.posY + this.rand.nextDouble(), this.posZ + this.rand.nextDouble(), 0.0D, 0.0D, 0.0D);

		if (!this.worldObj.isRemote)
		{
			// if (this.ticksExisted > 600)
			// {
			// this.setDead();
			// }
		}
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer var1)
	{
		if (!this.worldObj.isRemote)
		{
			byte b0 = 14;
			var1.addPotionEffect(new PotionEffect(Potion.poison.id, b0 * 20, 0));
		}
	}

	@Override
	protected void attackEntity(Entity entity, float f)
	{
		super.attackEntity(entity, f);
	}
}
