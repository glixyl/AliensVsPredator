package org.avp.entities.pathfinding;

import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;

import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;

public class PathFinder
{
    /** The path being generated */
    private Path path = new Path();

    /** Selection of path points to add to the path */
    private PathPoint[] pathOptions = new PathPoint[32];

    private NodeProcessor nodeProcessor;

    public PathFinder(NodeProcessor nodeProcessor)
    {
        this.nodeProcessor = nodeProcessor;
    }

    public PathEntity createEntityPathTo(IBlockAccess blockAccess, Entity entity, Entity targetEntity, float maxDistance)
    {
        return this.createEntityPathTo(blockAccess, entity, targetEntity.posX, targetEntity.getBoundingBox().minY, targetEntity.posZ, maxDistance);
    }

    public PathEntity createEntityPathTo(IBlockAccess blockAccess, Entity entity, CoordData targetCoord, float maxDistance)
    {
        return this.createEntityPathTo(blockAccess, entity, (double) ((float) targetCoord.posX + 0.5F), (double) ((float) targetCoord.posY + 0.5F), (double) ((float) targetCoord.posZ + 0.5F), maxDistance);
    }

    private PathEntity createEntityPathTo(IBlockAccess blockAccess, Entity entity, double posX, double posY, double posZ, float maxDistance)
    {
        this.path.clearPath();
        this.nodeProcessor.initProcessor(blockAccess, entity);
        PathPoint pathpoint = this.nodeProcessor.getPathPointTo(entity);
        PathPoint pathpoint1 = this.nodeProcessor.getPathPointToCoords(entity, posX, posY, posZ);
        PathEntity pathentity = this.addToPath(entity, pathpoint, pathpoint1, maxDistance);
        this.nodeProcessor.postProcess();
        return pathentity;
    }

    private PathEntity addToPath(Entity entity, PathPoint pathPointStart, PathPoint pathpointEnd, float maxDistance)
    {
        pathPointStart.totalPathDistance = 0.0F;
        pathPointStart.distanceToNext = pathPointStart.distanceToSquared(pathpointEnd);
        pathPointStart.distanceToTarget = pathPointStart.distanceToNext;
        this.path.clearPath();
        this.path.addPoint(pathPointStart);
        PathPoint pathpoint2 = pathPointStart;

        while (!this.path.isPathEmpty())
        {
            PathPoint pathpoint3 = this.path.dequeue();

            if (pathpoint3.equals(pathpointEnd))
            {
                return this.createEntityPath(pathPointStart, pathpointEnd);
            }

            if (pathpoint3.distanceToSquared(pathpointEnd) < pathpoint2.distanceToSquared(pathpointEnd))
            {
                pathpoint2 = pathpoint3;
            }

            pathpoint3.visited = true;
            int i = this.nodeProcessor.findPathOptions(this.pathOptions, entity, pathpoint3, pathpointEnd, maxDistance);

            for (int j = 0; j < i; ++j)
            {
                PathPoint pathpoint4 = this.pathOptions[j];
                float f1 = pathpoint3.totalPathDistance + pathpoint3.distanceToSquared(pathpoint4);

                if (f1 < maxDistance * 2.0F && (!pathpoint4.isAssigned() || f1 < pathpoint4.totalPathDistance))
                {
                    pathpoint4.previous = pathpoint3;
                    pathpoint4.totalPathDistance = f1;
                    pathpoint4.distanceToNext = pathpoint4.distanceToSquared(pathpointEnd);

                    if (pathpoint4.isAssigned())
                    {
                        this.path.changeDistance(pathpoint4, pathpoint4.totalPathDistance + pathpoint4.distanceToNext);
                    }
                    else
                    {
                        pathpoint4.distanceToTarget = pathpoint4.totalPathDistance + pathpoint4.distanceToNext;
                        this.path.addPoint(pathpoint4);
                    }
                }
            }
        }

        if (pathpoint2 == pathPointStart)
        {
            return null;
        }
        else
        {
            return this.createEntityPath(pathPointStart, pathpoint2);
        }
    }

    /**
     * Returns a new PathEntity for a given start and end point
     */
    private PathEntity createEntityPath(PathPoint start, PathPoint end)
    {
        int i = 1;
        PathPoint pathpoint2;

        for (pathpoint2 = end; pathpoint2.previous != null; pathpoint2 = pathpoint2.previous)
        {
            ++i;
        }

        PathPoint[] apathpoint = new PathPoint[i];
        pathpoint2 = end;
        --i;

        for (apathpoint[i] = end; pathpoint2.previous != null; apathpoint[i] = pathpoint2)
        {
            pathpoint2 = pathpoint2.previous;
            --i;
        }

        return new PathEntity(apathpoint);
    }
}
