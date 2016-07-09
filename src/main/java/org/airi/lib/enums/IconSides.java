package com.arisux.airi.lib.enums;

public enum IconSides
{
    DEFAULT(-1), BOTTOM(0), TOP(1), BACK(2), FRONT(3), LEFT(4), RIGHT(5);

    public int side;

    IconSides(int side)
    {
        this.side = side;
    }

    public int getSide()
    {
        return side;
    }

    public static IconSides getSideFor(int side)
    {
        for (IconSides iconSide : values())
        {
            if (iconSide.side == side)
            {
                return iconSide;
            }
        }

        return DEFAULT;
    }
}
