package com.arisux.avp.entities.pathfinding;

import net.minecraft.util.MathHelper;

public class PathPoint extends net.minecraft.pathfinding.PathPoint
{
    /** The x coordinate of this point */
    public final int xCoord;
    /** The y coordinate of this point */
    public final int yCoord;
    /** The z coordinate of this point */
    public final int zCoord;
    /** A hash of the coordinates used to identify this point */
    private final int hash;
    /** The index of this point in its assigned path */
    int index = -1;
    /** The distance along the path to this point */
    float totalPathDistance;
    /** The linear distance to the next point */
    float distanceToNext;
    /** The distance to the target */
    float distanceToTarget;
    /** The point preceding this in its assigned path */
    PathPoint previous;
    /** True if the pathfinder has already visited this point */
    public boolean visited;

    public PathPoint(int x, int y, int z)
    {
    	super(x, y, z);
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
        this.hash = makeHash(x, y, z);
    }

    public static int makeHash(int x, int y, int z)
    {
        return y & 255 | (x & 32767) << 8 | (z & 32767) << 24 | (x < 0 ? Integer.MIN_VALUE : 0) | (z < 0 ? 32768 : 0);
    }

    /**
     * Returns the linear distance to another path point
     */
    public float distanceTo(PathPoint pathPointIn)
    {
        float x = (float)(pathPointIn.xCoord - this.xCoord);
        float y = (float)(pathPointIn.yCoord - this.yCoord);
        float z = (float)(pathPointIn.zCoord - this.zCoord);
        return MathHelper.sqrt_float(x * x + y * y + z * z);
    }

    /**
     * Returns the squared distance to another path point
     */
    public float distanceToSquared(PathPoint pathPointIn)
    {
        float x = (float)(pathPointIn.xCoord - this.xCoord);
        float y = (float)(pathPointIn.yCoord - this.yCoord);
        float z = (float)(pathPointIn.zCoord - this.zCoord);
        return x * x + y * y + z * z;
    }

    public boolean equals(Object pathPoint)
    {
        if (!(pathPoint instanceof PathPoint))
        {
            return false;
        }
        else
        {
            PathPoint pathpoint = (PathPoint)pathPoint;
            return this.hash == pathpoint.hash && this.xCoord == pathpoint.xCoord && this.yCoord == pathpoint.yCoord && this.zCoord == pathpoint.zCoord;
        }
    }

    public int hashCode()
    {
        return this.hash;
    }

    /**
     * Returns true if this point has already been assigned to a path
     */
    public boolean isAssigned()
    {
        return this.index >= 0;
    }

    public String toString()
    {
        return this.xCoord + ", " + this.yCoord + ", " + this.zCoord;
    }
}