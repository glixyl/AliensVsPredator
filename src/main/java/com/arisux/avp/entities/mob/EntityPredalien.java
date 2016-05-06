package com.arisux.avp.entities.mob;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityAcidPool;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPredalien extends EntityXenomorph implements IMob
{
    public EntityPredalien(World world)
    {
        super(world);
        this.jumpMovementFactor = 0.2F;
        this.experienceValue = 15;
        this.setSize(1.0F, 4.0F);
        this.ignoreFrustumCheck = true;
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setBreakDoors(true);
        this.getNavigator().setCanSwim(true);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5500000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1F);
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    protected String getHurtSound()
    {
        return AliensVsPredator.properties().SOUND_PRAETORIAN_HURT;
    }

    @Override
    protected String getLivingSound()
    {
        return AliensVsPredator.properties().SOUND_PRAETORIAN_LIVING;
    }

    @Override
    protected String getDeathSound()
    {
        return AliensVsPredator.properties().SOUND_PRAETORIAN_DEATH;
    }

    @Override
    protected void dropRareDrop(int par1)
    {
        ;
    }

    @Override
    public void onDeath(DamageSource damageSource)
    {
        super.onDeath(damageSource);

        if (this.worldObj.isRemote)
        {
            EntityAcidPool entity = new EntityAcidPool(this.worldObj);
            entity.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
            this.worldObj.spawnEntityInWorld(entity);
        }
    }
}
