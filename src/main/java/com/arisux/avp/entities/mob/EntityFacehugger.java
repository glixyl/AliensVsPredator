package com.arisux.avp.entities.mob;

import java.util.Collections;
import java.util.List;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.ai.alien.EntitySelectorXenomorph;
import com.arisux.avp.entities.extended.ExtendedEntityLivingBase;
import com.arisux.avp.util.Embryo;
import com.arisux.avp.util.EmbryoType;
import com.arisux.avp.util.IFacehugSelector;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget.Sorter;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFacehugger extends EntitySpeciesAlien implements IMob
{
    private Sorter facehugTargetSorter;

    public EntityFacehugger(World world)
    {
        super(world);
        this.setSize(0.8F, 0.8F);
        this.experienceValue = 10;
        this.getNavigator().setCanSwim(true);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 0.55D, true));
        this.tasks.addTask(8, new EntityAIWander(this, 0.55D));
        this.targetTasks.addTask(2, new EntityAILeapAtTarget(this, 0.8F));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, Entity.class, 0, false, false, EntitySelectorXenomorph.instance));
        this.facehugTargetSorter = new EntityAINearestAttackableTarget.Sorter(this);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(30, 1);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(14.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.55D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.50D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.getAttackTarget() != null && this.getAttackTarget().riddenByEntity instanceof EntityFacehugger)
        {
            IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.followRange);
            double targetDistance = iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();

            List list = this.worldObj.selectEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(targetDistance, 4.0D, targetDistance), EntitySelectorXenomorph.instance);
            list.remove(this.getAttackTarget());
            Collections.sort(list, this.facehugTargetSorter);

            if (!list.isEmpty() && list.get(0) != null)
            {
                this.setAttackTarget((EntityLivingBase) list.get(0));
            }
        }

        if (!this.isFertile())
        {
            this.tasks.taskEntries.clear();
            this.targetTasks.taskEntries.clear();
        }

        this.fallDistance = 0F;

        if (this.isCollidedHorizontally)
        {
            this.motionY += 0.15F;
        }

        if (this.isRiding())
        {
            this.rotationYaw = 0F;
            this.rotationYawHead = 0F;

            if (this.ridingEntity instanceof EntityLivingBase)
            {
                EntityLivingBase ridingEntity = (EntityLivingBase) this.ridingEntity;
                ridingEntity.rotationYaw = 0F;
                ridingEntity.rotationYawHead = 0F;
            }
        }
    }

    @Override
    public boolean canMoveToJelly()
    {
        return super.canMoveToJelly() && this.isFertile();
    }

    @Override
    protected void onPickupJelly(EntityItem entityItem)
    {
        super.onPickupJelly(entityItem);
        this.setFertile(1);
    }

    @Override
    public boolean isOnLadder()
    {
        return this.motionY > 1.0099999997764826D;
    }

    @Override
    public void collideWithEntity(Entity entity)
    {
        this.latchOnEntity(entity);
    }

    protected void latchOnEntity(Entity entity)
    {
        if (this.isFertile() && entity.riddenByEntity == null && entity instanceof EntityLivingBase && !(entity instanceof EntitySpeciesAlien))
        {
            EntityLivingBase living = (EntityLivingBase) entity;
            ExtendedEntityLivingBase extendedLiving = (ExtendedEntityLivingBase) living.getExtendedProperties(ExtendedEntityLivingBase.IDENTIFIER);

            if (!(living instanceof EntityPlayer) || living instanceof EntityPlayer && !((EntityPlayer) living).capabilities.isCreativeMode)
            {
                IFacehugSelector facehuggable = null;

                if (entity instanceof IFacehugSelector)
                {
                    facehuggable = (IFacehugSelector) entity;
                }

                if (facehuggable != null && facehuggable.canFacehuggerAttach() || facehuggable == null)
                {
                    this.mountEntity(living);
                    this.implantEmbryo(extendedLiving);
                }
            }

        }
    }

    protected void implantEmbryo(ExtendedEntityLivingBase extendedLiving)
    {
        extendedLiving.setEmbryo(new Embryo(EmbryoType.getMappingFromHost(extendedLiving.getEntityLivingBase().getClass()))
        {
        });
        extendedLiving.syncClients();
        this.setFertile(0);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float damage)
    {
        return super.attackEntityFrom(damagesource, damage);
    }

    @Override
    public void onDeath(DamageSource damagesource)
    {
        super.onDeath(damagesource);
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public double getYOffset()
    {
        return 0.3D;
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    protected String getDeathSound()
    {
        return AliensVsPredator.properties().SOUND_FACEHUGGER_DEATH;
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @SuppressWarnings("all")
    @Override
    protected void attackEntity(Entity entity, float attackStrength)
    {
        ;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    public boolean isPotionApplicable(PotionEffect effect)
    {
        return effect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(effect);
    }

    @Override
    public void onKillEntity(EntityLivingBase host)
    {
        super.onKillEntity(host);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.setFertile(nbt.getInteger("Fertile"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("Fertile", this.isFertileDatawatcher());
    }

    public boolean isFertile()
    {
        return this.isFertileDatawatcher() == 1;
    }

    public int isFertileDatawatcher()
    {
        return this.dataWatcher.getWatchableObjectInt(30);
    }

    public void setFertile(int isFertile)
    {
        this.dataWatcher.updateObject(30, isFertile);
    }
}
