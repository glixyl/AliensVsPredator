package com.arisux.avp.entities.mob;

import com.arisux.airi.lib.WorldUtil.Entities;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.util.EvolutionType;
import com.arisux.avp.util.EmbryoType;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityChestburster extends EntitySpeciesAlien implements IMob
{
    protected Minecraft mc;
    private int parasiteType;

    public EntityChestburster(World world)
    {
        super(world);

        this.setSize(1.0F, 0.4F);
        this.experienceValue = 16;
        this.getNavigator().setCanSwim(true);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.23F, 0.4F));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityYautja.class, 16.0F, 0.23F, 0.4F));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityEngineer.class, 16.0F, 0.23F, 0.4F));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityMarine.class, 16.0F, 0.23F, 0.4F));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 0.800000011920929D, true));
        this.tasks.addTask(8, new EntityAIWander(this, 0.800000011920929D));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAILeapAtTarget(this, 0.8F));
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

    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public boolean canBreatheUnderwater()
    {
        return true;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
    }

    @Override
    protected void tickEvolution()
    {
        if (!this.worldObj.isRemote)
        {
            if (this.worldObj.getWorldTime() % 40 == 0)
            {
                EvolutionType evolution = EvolutionType.getEvolutionMappingFor(this.getClass());

                if (evolution != null)
                {
                    if (this.ticksExisted >= this.getMaxParasiteAge() || this.getJellyLevel() >= evolution.getLevel())
                    {
                        if (this.getJellyLevel() >= evolution.getLevel() && this.ticksExisted < this.getMaxParasiteAge())
                        {
                            this.setJellyLevel(this.getJellyLevel() - evolution.getLevel());
                        }

                        EntityXenomorph xeno = (EntityXenomorph) Entities.constructEntity(this.worldObj, this.getGrownParasiteType());
                        xeno.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
                        this.worldObj.spawnEntityInWorld(xeno);

                        for (int particleCount = 0; particleCount < 8; ++particleCount)
                        {
                            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
                        }

                        this.setDead();
                    }
                }
            }
        }
    }

    protected Entity findPlayerToAttack(EntityPlayer entityplayer)
    {
        float f = this.getBrightness(1.0F);

        if (f < 0.5F)
        {
            double d = 40.0D;
            return this.worldObj.getClosestVulnerablePlayerToEntity(this, d);
        }
        else
        {
            return null;
        }
    }

    @Override
    protected String getDeathSound()
    {
        return AliensVsPredator.properties().SOUND_CHESTBURSTER_DEATH;
    }

    @Override
    protected String getHurtSound()
    {
        return AliensVsPredator.properties().SOUND_CHESTBURSTER_HURT;
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
    public boolean isPotionApplicable(PotionEffect par1PotionEffect)
    {
        return par1PotionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(par1PotionEffect);
    }

    public void setHostParasiteType(EmbryoType embryoType)
    {
        this.parasiteType = embryoType.getTypeId();
    }

    public Class<? extends EntityXenomorph> getGrownParasiteType()
    {
        EmbryoType hostParasiteType = EmbryoType.get(this.parasiteType);
        return hostParasiteType == null ? EmbryoType.NORMAL.getResult() : hostParasiteType.getResult();
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.parasiteType = nbt.getInteger("parasiteType");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("parasiteType", this.parasiteType);
    }

    public int getMaxParasiteAge()
    {
        return 18000;
    }
}
