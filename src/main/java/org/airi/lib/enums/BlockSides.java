package com.arisux.airi.lib.enums;

public enum BlockSides
{
    DOWN(0), UP(1), EAST(2), WEST(3), NORTH(4), SOUTH(5);

    private int id;

    BlockSides(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public static BlockSides getSide(int sideId)
    {
        for (BlockSides side : values())
        {
            if (side.id == sideId)
            {
                return side;
            }
        }

        return null;
    }
}
