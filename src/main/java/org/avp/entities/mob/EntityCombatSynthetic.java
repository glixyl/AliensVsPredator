package org.avp.entities.mob;

import org.avp.AliensVsPredator;
import org.avp.ItemHandler;
import org.avp.entities.EntityBullet;
import org.avp.util.IFacehugSelector;

import com.arisux.airi.lib.WorldUtil.Entities.Players.Inventories;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCombatSynthetic extends EntityCreature implements IMob, IRangedAttackMob, IFacehugSelector
{
    public EntityCombatSynthetic(World par1World)
    {
        super(par1World);
        this.experienceValue = 40;
        this.dataWatcher.addObject(18, new Integer(15));
        this.dataWatcher.addObject(17, "");
        this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4499999761581421D);
    }

    @Override
    protected void dropFewItems(boolean par1, int par2)
    {
        super.dropFewItems(par1, par2);

        ItemHandler items = AliensVsPredator.items();
        this.entityDropItem(Inventories.randomItemStackFromArray(new Item[] { items.itemAmmoAC, items.itemAmmoAR, items.itemAmmoPistol, items.itemAmmoSMG }, rand), rand.nextInt(6));
    }

    @Override
    protected String getHurtSound()
    {
        return AliensVsPredator.properties().SOUND_MARINE_HURT;
    }

    @Override
    protected String getDeathSound()
    {
        return AliensVsPredator.properties().SOUND_MARINE_DEATH;
    }

    @Override
    public ItemStack getHeldItem()
    {
        return new ItemStack(AliensVsPredator.items().itemM41A);
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    @Override
    public int getTotalArmorValue()
    {
        return 20;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        double range = 20D;
        EntityLivingBase targetEntity = (EntityLivingBase) (this.worldObj.findNearestEntityWithinAABB(EntityLiving.class, this.boundingBox.expand(range * 2, 64.0D, range * 2), this));

        if (targetEntity instanceof EntityMob && !worldObj.isRemote)
        {
            if (targetEntity instanceof EntityXenomorph)
            {
                targetEntity.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1F);
            }

            this.attackEntityWithRangedAttack(targetEntity, 1F);
            this.setAttackTarget(targetEntity);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (super.attackEntityFrom(par1DamageSource, par2))
        {
            Entity damageSourceEntity = par1DamageSource.getEntity();

            if (this.riddenByEntity != damageSourceEntity && this.ridingEntity != damageSourceEntity)
            {
                if (damageSourceEntity != this)
                {
                    this.entityToAttack = damageSourceEntity;
                }

                return true;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    protected void attackEntity(Entity entity, float f)
    {
        if (this.attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            this.attackEntityAsMob(entity);
        }

        float f1 = this.getBrightness(1.0F);

        if (f1 > 0.5F && this.rand.nextInt(100) == 0)
        {
            this.entityToAttack = null;
        }
        else
        {
            if (f > 2.0F && f < 6.0F && this.rand.nextInt(10) == 0)
            {
                if (this.onGround)
                {
                    double rX = entity.posX - this.posX;
                    double rZ = entity.posZ - this.posZ;
                    float sq = MathHelper.sqrt_double(rX * rX + rZ * rZ);
                    this.motionX = rX / sq * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                    this.motionZ = rZ / sq * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                    this.motionY = 0.4000000059604645D;
                }
            }
            else
            {
                super.attackEntity(entity, f);
            }
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        int damage = 6;

        if (this.isPotionActive(Potion.damageBoost))
        {
            damage += 10 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }

        if (this.isPotionActive(Potion.weakness))
        {
            damage -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
        }

        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
    }

    @Override
    public float getBlockPathWeight(int posX, int posY, int posZ)
    {
        return 0.5F - this.worldObj.getLightBrightness(posX, posY, posZ);
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase entityToAttack, float f)
    {
        if (entityToAttack instanceof EntityMob && !entityToAttack.isDead)
        {
            this.getLookHelper().setLookPosition(entityToAttack.posX, entityToAttack.posY + entityToAttack.getEyeHeight(), entityToAttack.posZ, 10.0F, this.getVerticalFaceSpeed());

            if (this.canEntityBeSeen(entityToAttack))
            {
                this.worldObj.spawnEntityInWorld(new EntityBullet(this.worldObj, this, entityToAttack, 1.6F, 0.25D));
            }
        }
    }

    @Override
    protected void updateAITick()
    {
        this.dataWatcher.updateObject(18, Integer.valueOf(15));
    }

    @Override
    public boolean canFacehuggerAttach()
    {
        return false;
    }
}
