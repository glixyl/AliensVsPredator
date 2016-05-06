package com.arisux.avp.util;

import net.minecraftforge.common.util.ForgeDirection;

public interface IRotatable
{
    public ForgeDirection getDirection();

    public void setDirection(ForgeDirection facing);
}
