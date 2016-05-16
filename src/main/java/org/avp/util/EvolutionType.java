package org.avp.util;

import org.avp.entities.mob.EntityChestburster;
import org.avp.entities.mob.EntityCrusher;
import org.avp.entities.mob.EntityDrone;
import org.avp.entities.mob.EntityPraetorian;
import org.avp.entities.mob.EntityQueen;
import org.avp.entities.mob.EntityRunnerDrone;
import org.avp.entities.mob.EntityRunnerWarrior;
import org.avp.entities.mob.EntityWarrior;
import org.avp.entities.mob.EntityXenomorph;

import net.minecraft.entity.Entity;

@SuppressWarnings("all")
public enum EvolutionType
{
    CHESTBURSTER(0, 12, EntityChestburster.class, null), DRONE(1, 12, EntityDrone.class, EntityWarrior.class), WARRIOR(2, 32, EntityWarrior.class, EntityPraetorian.class), PRAETORIAN(3, 100, EntityPraetorian.class, EntityQueen.class), RUNNER_DRONE(4, 12, EntityRunnerDrone.class, EntityRunnerWarrior.class), RUNNER_WARRIOR(5, 32, EntityRunnerWarrior.class, EntityCrusher.class), CRUSHER(5, 32, EntityCrusher.class, EntityQueen.class);

    public int id;
    private int level;
    private Class<? extends Entity> base;
    private Class<? extends EntityXenomorph> evolution;

    EvolutionType(int id, int level, Class<? extends Entity> base, Class<? extends EntityXenomorph> evolution)
    {
        this.id = id;
        this.level = level;
        this.base = base;
        this.evolution = evolution;
    }

    public int getLevel()
    {
        return level;
    }

    public Class<? extends EntityXenomorph> getEvolution()
    {
        return evolution;
    }

    public Class<? extends Entity> getBase()
    {
        return base;
    }

    public int getId()
    {
        return id;
    }

    public static EvolutionType get(int id)
    {
        for (EvolutionType mode : values())
        {
            if (mode.id == id)
            {
                return mode;
            }
        }

        return null;
    }

    public static EvolutionType getEvolutionMappingFor(Class<? extends Entity> base)
    {
        for (EvolutionType map : values())
        {
            if (map.getBase() == base)
            {
                return map;
            }
        }

        return null;
    }
}
