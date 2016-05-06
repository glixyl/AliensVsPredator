package com.arisux.avp.entities.mob;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.ai.alien.EntitySelectorXenomorph;
import com.arisux.avp.entities.extended.ExtendedEntityLivingBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
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
    private boolean isFertile;

    public EntityFacehugger(World world)
    {
        super(world);
        this.isFertile = true;
        this.setSize(0.8F, 0.8F);
        this.experienceValue = 10;
        this.getNavigator().setCanSwim(true);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 0.55D, true));
        this.tasks.addTask(8, new EntityAIWander(this, 0.55D));
        this.targetTasks.addTask(2, new EntityAILeapAtTarget(this, 0.8F));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, Entity.class, /** targetChance **/
            0, /** shouldCheckSight **/
            false, /** nearbyOnly **/
            false, EntitySelectorXenomorph.instance));
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
    protected void onPickupJelly(EntityItem entityItem)
    {
        super.onPickupJelly(entityItem);
        this.isFertile = true;
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
        if (this.isFertile && entity instanceof EntityLivingBase && !(entity instanceof EntitySpeciesAlien))
        {
            EntityLivingBase living = (EntityLivingBase) entity;
            ExtendedEntityLivingBase extendedLiving = (ExtendedEntityLivingBase) living.getExtendedProperties(ExtendedEntityLivingBase.IDENTIFIER);

            if (!(living instanceof EntityPlayer) || living instanceof EntityPlayer && !((EntityPlayer) living).capabilities.isCreativeMode)
            {
                this.mountEntity(living);

                // TODO: Replace this with a method that sets the parasite type that is implanted into this host. This will allow for mob-specific facehuggers.
                extendedLiving.setContainsEmbryo(true);
                extendedLiving.syncClients();
                this.isFertile = false;
            }
        }
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
        this.isFertile = nbt.getBoolean("fertile");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setBoolean("fertile", this.isFertile);
    }
}
