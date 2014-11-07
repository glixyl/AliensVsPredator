package com.arisux.avp.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.arisux.avp.damagesource.DamageSourceAcid;
import com.google.common.io.ByteArrayDataOutput;

public class EntityAcidPool extends EntityMob implements IMob
{
	public int strength;
	public int age;

	public EntityAcidPool(World var1)
	{
		super(var1);
		this.isImmuneToFire = false;
		this.ignoreFrustumCheck = true;
		this.setSize(0.1F, 0.1F);
		this.strength = 100;
		this.age = 0;
	}

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

	public void setStrength(int var1)
	{
		this.age = (100 - var1) * 10;
		this.strength = 100 - this.age / 10;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (!this.worldObj.isRemote)
		{
			double range = 0.7;
			EntityPlayer targetPlayer = (EntityPlayer) (this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand((double) (range * 2), 0.1D, (double) (range * 2)), this));

			if (targetPlayer != null)
			{
				this.setAttackTarget(targetPlayer);
				targetPlayer.attackEntityFrom(DamageSourceAcid.causeAcidicDamage(this, targetPlayer), 4F);
			}
		}

		++this.age;
		this.strength = 100 - this.age / 10;

		if (!this.worldObj.isRemote)
		{
			if (this.strength <= 0 || this.age > 600)
			{
				this.setDead();
			}
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

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setShort("age", (short) this.age);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		this.age = nbt.getShort("age");
	}

	public void writeSpawnData(ByteArrayDataOutput var1)
	{
		var1.writeInt(this.age);
	}
}
