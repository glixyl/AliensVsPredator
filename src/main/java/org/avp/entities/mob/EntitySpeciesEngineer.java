package org.avp.entities.mob;

import java.util.Random;

import org.avp.AliensVsPredator;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntitySpeciesEngineer extends EntityMob
{
    public EntitySpeciesEngineer(World world)
    {
        super(world);
        this.experienceValue = 250;
        this.setSize(1.0F, 2.5F);
        this.getNavigator().setCanSwim(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 0.800000011920929D, true));
        this.tasks.addTask(8, new EntityAIWander(this, 0.800000011920929D));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntitySpeciesAlien.class, 0, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntitySpeciesYautja.class, 0, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityMarine.class, 0, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    public void onDeath(DamageSource damagesource)
    {
        super.onDeath(damagesource);
        if (new Random().nextInt(20) == 0) // 5% chance of dropping NBT drive, a crafting ingredient of the assembler (creation theme - play on NBT..)
            this.entityDropItem(new ItemStack(AliensVsPredator.items().itemFlashDrive), 0.0F);
        if (new Random().nextInt(20) == 0) // thematic drop, allowing the player to understand the source of the black goo
            this.entityDropItem(new ItemStack(AliensVsPredator.items().itemPhial), 0.0F);
        if (new Random().nextInt(20) == 0)
            this.entityDropItem(new ItemStack(AliensVsPredator.items().itemPhialEmpty), 0.0F);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(17, String.valueOf(this.rand.nextBoolean()));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(160.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5199999761581421D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1F);
    }

    @Override
    protected void attackEntity(Entity entity, float damage)
    {
        if (this.attackTime <= 0 && damage < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            this.attackEntityAsMob(entity);
        }

        if (damage > 2.0F && damage < 6.0F && this.rand.nextInt(10) == 0)
        {
            if (this.onGround)
            {
                double dX = entity.posX - this.posX;
                double dZ = entity.posZ - this.posZ;
                float speed = MathHelper.sqrt_double(dX * dX + dZ * dZ);
                this.motionX = dX / speed * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                this.motionZ = dZ / speed * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                this.motionY = 0.4000000059604645D;
            }
        }
        else
        {
            super.attackEntity(entity, damage);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        int damage = 5;

        if (this.isPotionActive(Potion.damageBoost))
        {
            damage += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }

        if (this.isPotionActive(Potion.weakness))
        {
            damage -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
        }

        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public int getTotalArmorValue()
    {
        return 7;
    }

    @Override
    protected void collideWithEntity(Entity entity)
    {
        if (entity instanceof IMob && this.getRNG().nextInt(20) == 0 && !(entity instanceof EntitySpeciesEngineer))
        {
            this.setAttackTarget((EntityLivingBase) entity);
            this.setRevengeTarget((EntityLivingBase) entity);
        }

        super.collideWithEntity(entity);
    }

    @Override
    public boolean isInWater()
    {
        return this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.900000023841858D, 0.0D), Material.water, this);
    }

    @Override
    protected String getLivingSound()
    {
        return null;
    }

    @Override
    protected String getHurtSound()
    {
        return null;
    }

    @Override
    protected String getDeathSound()
    {
        return null;
    }

    @Override
    protected void dropFewItems(boolean flag, int i)
    {
        ;
    }

    @Override
    protected void dropRareDrop(int par1)
    {
        ;
    }

    @Override
    public boolean canDespawn()
    {
        return false;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag)
    {
        super.readEntityFromNBT(tag);
        this.setWearingMask(tag.getBoolean("wearingMask"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag)
    {
        super.writeEntityToNBT(tag);
        tag.setBoolean("wearingMask", this.isWearingMask());
    }

    public boolean isWearingMask()
    {
        return Boolean.parseBoolean(this.dataWatcher.getWatchableObjectString(17));
    }

    public void setWearingMask(boolean wearingMask)
    {
        if (!this.worldObj.isRemote)
        {
            this.dataWatcher.updateObject(17, String.valueOf(wearingMask));
        }
    }
}
