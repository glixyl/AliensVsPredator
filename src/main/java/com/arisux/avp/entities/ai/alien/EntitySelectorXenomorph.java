package com.arisux.avp.entities.ai.alien;

import com.arisux.avp.entities.EntityAcidPool;
import com.arisux.avp.entities.mob.EntitySpeciesAlien;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;

public class EntitySelectorXenomorph implements IEntitySelector
{
    public static final EntitySelectorXenomorph instance = new EntitySelectorXenomorph();

    @Override
    public boolean isEntityApplicable(Entity entity)
    {
        return !(entity instanceof EntitySpeciesAlien) && !(entity instanceof EntityAcidPool);
    }
}
