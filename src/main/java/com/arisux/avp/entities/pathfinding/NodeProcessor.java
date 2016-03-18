package com.arisux.avp.entities.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public abstract class NodeProcessor
{
    protected IBlockAccess blockAccess;
    protected IntHashMap pointMap = new IntHashMap();
    protected int entitySizeX;
    protected int entitySizeY;
    protected int entitySizeZ;

    public void initProcessor(IBlockAccess blockAccess, Entity entity)
    {
        this.blockAccess = blockAccess;
        this.pointMap.clearMap();
        this.entitySizeX = MathHelper.floor_float(entity.width + 1.0F);
        this.entitySizeY = MathHelper.floor_float(entity.height + 1.0F);
        this.entitySizeZ = MathHelper.floor_float(entity.width + 1.0F);
    }

    public void postProcess() {}

    protected PathPoint openPoint(int x, int y, int z)
    {
        int l = PathPoint.makeHash(x, y, z);
        PathPoint pathpoint = (PathPoint)this.pointMap.lookup(l);

        if (pathpoint == null)
        {
            pathpoint = new PathPoint(x, y, z);
            this.pointMap.addKey(l, pathpoint);
        }

        return pathpoint;
    }

    public abstract PathPoint getPathPointTo(Entity entityIn);

    public abstract PathPoint getPathPointToCoords(Entity entityIn, double x, double y, double target);

    public abstract int findPathOptions(PathPoint[] pathOptions, Entity entityIn, PathPoint currentPoint, PathPoint targetPoint, float maxDistance);
}