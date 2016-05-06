package com.arisux.avp.entities.mob;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityRunnerDrone extends EntityDrone
{
    public EntityRunnerDrone(World world)
    {
        super(world);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.63D);
    }
}
