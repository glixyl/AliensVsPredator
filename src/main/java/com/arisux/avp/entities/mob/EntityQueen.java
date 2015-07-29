package com.arisux.avp.entities.mob;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.interfaces.IHiveSignature;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityQueen extends EntityXenomorph implements IHiveSignature
{
	public boolean isInStasis;
	private float ovipositorSize;

	public EntityQueen(World world)
	{
		super(world);
		this.setSize(2.0F, 4.0F);
		this.isInStasis = true;
		this.experienceValue = 40000;
		this.jumpMovementFactor = 0.4F;
		this.hurtResistantTime = 0;
		this.ignoreFrustumCheck = true;
		this.ovipositorSize = 0F;
		this.setHiveSignature(this.getUniqueID());
		this.dataWatcher.addObject(14, this.ovipositorSize);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.400000238418579D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

//		if ((this.getAttackTarget() != null || this.getLastAttacker() != null) && this.worldObj.getWorldTime() % 40 == 0)
//		{
//			@SuppressWarnings("unchecked")
//			ArrayList<EntitySpeciesAlien> aliens = (ArrayList<EntitySpeciesAlien>) Entities.getEntitiesInCoordsRange(this.worldObj, EntitySpeciesAlien.class, new CoordData(this), 16);
//
//			for (EntitySpeciesAlien alien : aliens)
//			{
//				if (alien != null && alien.getHiveSignature() != null &&  !(alien instanceof EntityQueen) && alien.getHiveSignature().equals(this.getHiveSignature()))
//				{
//					if (this.rand.nextInt(6) == 0)
//					{
//						if (alien instanceof EntityOvamorph)
//						{
//							EntityOvamorph ovamorph = (EntityOvamorph) alien;
//							ovamorph.setHatched(true);
//						}
//					}
//
//					EntityLivingBase target = this.getAttackTarget() != null ? this.getAttackTarget() : this.getLastAttacker();
//
//					alien.setAttackTarget(target);
//					alien.getNavigator().tryMoveToEntityLiving(target, alien.getMoveHelper().getSpeed());
//				}
//				else if (alien != null && alien.getHiveSignature() == null)
//				{
//					alien.setHiveSignature(this.getHiveSignature());
//				}
//			}
//		}
//
//		if (isJumping)
//		{
//			this.addVelocity(0, 0.15D, 0);
//		}
//
//		if (this.isInStasis)
//		{
//			this.ovipositorSize = this.ovipositorSize < 1.2F ? this.ovipositorSize += 0.0001F : this.ovipositorSize;
//		}
//
//		if (this.worldObj.getWorldTime() % 10 == 0)
//		{
//			if (this.getHealth() > this.getMaxHealth() - (this.getMaxHealth() / 4))
//			{
//				this.heal(2F);
//			}
//
//			if (this.getHealth() > 0 && this.getHealth() < this.getMaxHealth() / 4 * 3)
//			{
//				this.heal(2F);
//			}
//
//			if (this.getHealth() > 0 && this.getHealth() < this.getMaxHealth() / 4 * 2)
//			{
//				this.heal(2F);
//			}
//		}
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected String getHurtSound()
	{
		return AliensVsPredator.properties().SOUND_QUEEN_HURT;
	}

	@Override
	protected String getLivingSound()
	{
		return this.getHealth() > this.getMaxHealth() / 4 ? AliensVsPredator.properties().SOUND_QUEEN_LIVING + ".constant" : AliensVsPredator.properties().SOUND_QUEEN_LIVING;
	}

	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_QUEEN_DEATH;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.setOvipositorSize(nbt.getFloat("ovipositorSize"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setFloat("ovipositorSize", this.getOvipositorSize());
	}

	public float getOvipositorSize()
	{
		return this.dataWatcher.getWatchableObjectFloat(14);
	}

	public void setOvipositorSize(float size)
	{
		this.dataWatcher.updateObject(14, size);
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}
}
