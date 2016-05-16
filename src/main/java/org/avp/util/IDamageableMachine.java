package org.avp.util;

public interface IDamageableMachine
{
    public int getMachineDamage();

    public int getMaxMachineDamage();

    public void damageMachine(int amount);
}
