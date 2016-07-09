package com.arisux.airi.lib.interfaces;

import net.minecraftforge.common.util.ForgeDirection;

public interface IRotatable
{
    public ForgeDirection getDirection();

    public void setDirection(ForgeDirection facing);
}
