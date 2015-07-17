package com.arisux.avp.entities.mob;

import java.util.Random;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAcidProjectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySpitter extends EntityXenomorph implements IRangedAttackMob
{
	public EntitySpitter(World par1World)
	{
		super(par1World);
		this.experienceValue = 275;
		this.setSize(1.0F, 3.0F);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5500000238418579D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
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

				EntityAcidProjectile entityacid = new EntityAcidProjectile(this.worldObj, this, par1EntityLivingBase, 1.6F, 14 - attackDamage * 4);
				entityacid.setDamage(f * 2.0F + this.rand.nextGaussian() * 0.25D + attackDamage * 0.11F);
				if (this.worldObj.getWorldTime() % 30 == 0)
				{
					this.worldObj.spawnEntityInWorld(entityacid);
				}
			}
		}
	}

	@Override
	protected void dropRareDrop(int par1)
	{
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.items().helmXeno), 1);
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.items().plateXeno), 1);
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.items().legsXeno), 1);
		if (new Random().nextInt(4) == 1)
			this.entityDropItem(new ItemStack(AliensVsPredator.items().bootsXeno), 1);

		super.dropRareDrop(par1);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}
}
