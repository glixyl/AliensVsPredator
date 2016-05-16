package org.avp.entities.mob;

import java.util.Random;

import org.avp.AliensVsPredator;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCrusher extends EntityXenomorph
{
    public EntityCrusher(World var1)
    {
        super(var1);
        this.jumpMovementFactor = 0.2F;
        this.experienceValue = 300;
        this.setSize(1.0F, 3.0F);
        this.getNavigator().setCanSwim(true);
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(90.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5500000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1F);
    }

    @Override
    protected void dropRareDrop(int rate)
    {
        if (new Random().nextInt(4) == 1)
            this.entityDropItem(new ItemStack(AliensVsPredator.items().helmXeno), 1);
        if (new Random().nextInt(4) == 1)
            this.entityDropItem(new ItemStack(AliensVsPredator.items().plateXeno), 1);
        if (new Random().nextInt(4) == 1)
            this.entityDropItem(new ItemStack(AliensVsPredator.items().legsXeno), 1);
        if (new Random().nextInt(4) == 1)
            this.entityDropItem(new ItemStack(AliensVsPredator.items().bootsXeno), 1);

        super.dropRareDrop(rate);
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    protected String getHurtSound()
    {
        return AliensVsPredator.properties().SOUND_CRUSHER_HURT;
    }

    @Override
    protected String getLivingSound()
    {
        return AliensVsPredator.properties().SOUND_CRUSHER_LIVING;
    }

    @Override
    protected String getDeathSound()
    {
        return AliensVsPredator.properties().SOUND_CRUSHER_DEATH;
    }

    @Override
    public int getTotalArmorValue()
    {
        return 5;
    }
}
