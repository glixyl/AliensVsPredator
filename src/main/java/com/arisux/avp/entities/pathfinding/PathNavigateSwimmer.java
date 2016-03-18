package com.arisux.avp.entities.pathfinding;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class PathNavigateSwimmer extends PathNavigate
{
    public PathNavigateSwimmer(EntityLiving entityLiving, World worldIn)
    {
        super(entityLiving, worldIn);
    }

    @Override
    protected PathFinder getPathFinder()
    {
        return new PathFinder(new SwimNodeProcessor());
    }

    @Override
    protected boolean canNavigate()
    {
        return this.isInLiquid();
    }
    
    @Override
    protected Vec3 getEntityPosition()
    {
    	return Vec3.createVectorHelper(this.theEntity.posX, this.theEntity.posY + (double)this.theEntity.height * 0.5D, this.theEntity.posZ);
    }

    @Override
    protected void pathFollow()
    {
    	System.out.println("PathNavigateSwimmer.pathFollow");
    	
        Vec3 entityPos = this.getEntityPosition();
        float widthSquared = this.theEntity.width * this.theEntity.width;

        if (entityPos.squareDistanceTo(this.currentPath.getVectorFromIndex(this.theEntity, this.currentPath.getCurrentPathIndex())) < (double)widthSquared)
        {
            this.currentPath.incrementPathIndex();
        }

        for (int i = Math.min(this.currentPath.getCurrentPathIndex() + 6, this.currentPath.getCurrentPathLength() - 1); i > this.currentPath.getCurrentPathIndex(); --i)
        {
            Vec3 currentPathVec = this.currentPath.getVectorFromIndex(this.theEntity, i);

            if (currentPathVec.squareDistanceTo(entityPos) <= 36.0D && this.isDirectPathBetweenPoints(entityPos, currentPathVec, 0, 0, 0))
            {
                this.currentPath.setCurrentPathIndex(i);
                break;
            }
        }

        this.checkForStuck(entityPos);
    }

    @Override
    protected void removeSunnyPath()
    {
        super.removeSunnyPath();
    }

    @Override
    protected boolean isDirectPathBetweenPoints(Vec3 posVec31, Vec3 posVec32, int sizeX, int sizeY, int sizeZ)
    {
        MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(posVec31, Vec3.createVectorHelper(posVec32.xCoord, posVec32.yCoord + (double)this.theEntity.height * 0.5D, posVec32.zCoord), false);
        return movingobjectposition == null || movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.MISS;
    }
}