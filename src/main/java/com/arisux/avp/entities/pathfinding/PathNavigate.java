package com.arisux.avp.entities.pathfinding;

import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.airi.lib.WorldUtil.Entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;

public abstract class PathNavigate extends net.minecraft.pathfinding.PathNavigate
{
    protected EntityLiving theEntity;
    protected World worldObj;
    protected PathEntity currentPath;
    protected double speed;
    private final IAttributeInstance pathSearchRange;
    private int totalTicks;
    private int ticksAtLastPos;
    private Vec3 lastPosCheck = Vec3.createVectorHelper(0, 0, 0);
    private float heightRequirement = 1.0F;
    private final PathFinder pathFinder;

    public PathNavigate(EntityLiving entityLiving, World worldIn)
    {
    	super(entityLiving, worldIn);
        this.theEntity = entityLiving;
        this.worldObj = worldIn;
        this.pathSearchRange = entityLiving.getEntityAttribute(SharedMonsterAttributes.followRange);
        this.pathFinder = this.getPathFinder();
    }

    protected abstract PathFinder getPathFinder();

    @Override
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    @Override
    public float getPathSearchRange()
    {
        return (float)this.pathSearchRange.getAttributeValue();
    }

    @Override
    public final PathEntity getPathToXYZ(double posX, double posY, double posZ)
    {
        return this.getPathToCoord(new CoordData(MathHelper.floor_double(posX), (int)posY, MathHelper.floor_double(posZ)));
    }

    public PathEntity getPathToCoord(CoordData coord)
    {
        if (!this.canNavigate())
        {
            return null;
        }
        else
        {
            float searchRange = this.getPathSearchRange();
            this.worldObj.theProfiler.startSection("pathfind");
            int i = (int)(searchRange + 8.0F);
            CoordData subCoord = coord.subtract(i, i, i);
            CoordData addCoord = coord.add(i, i, i);
            ChunkCache chunkcache = new ChunkCache(this.worldObj, subCoord.posX, subCoord.posY, subCoord.posZ, addCoord.posX, addCoord.posY, addCoord.posZ, 0);
            PathEntity pathentity = this.pathFinder.createEntityPathTo(chunkcache, this.theEntity, coord, searchRange);
            this.worldObj.theProfiler.endSection();
            return pathentity;
        }
    }

    @Override
    public boolean tryMoveToXYZ(double x, double y, double z, double speedIn)
    {
        PathEntity pathentity = this.getPathToXYZ((double)MathHelper.floor_double(x), (double)((int)y), (double)MathHelper.floor_double(z));
        return this.setPath(pathentity, speedIn);
    }

    public void setHeightRequirement(float heightRequirement)
    {
        this.heightRequirement = heightRequirement;
    }

    @Override
    public PathEntity getPathToEntityLiving(Entity entityLiving)
    {
        if (!this.canNavigate())
        {
            return null;
        }
        else
        {
            float searchRange = this.getPathSearchRange();
            this.worldObj.theProfiler.startSection("pathfind");
            CoordData coord = (new CoordData(this.theEntity)).add(0, 1, 0);
            int i = (int)(searchRange + 16.0F);
            CoordData subCoord = coord.subtract(i, i, i);
            CoordData addCoord = coord.add(i, i, i);
            ChunkCache chunkcache = new ChunkCache(this.worldObj, subCoord.posX, subCoord.posY, subCoord.posZ, addCoord.posX, addCoord.posY, addCoord.posZ, 0);
            PathEntity pathentity = this.pathFinder.createEntityPathTo(chunkcache, this.theEntity, entityLiving, searchRange);
            this.worldObj.theProfiler.endSection();
            return pathentity;
        }
    }

    @Override
    public boolean tryMoveToEntityLiving(Entity entityIn, double speedIn)
    {
        PathEntity pathentity = this.getPathToEntityLiving(entityIn);
        return pathentity != null ? this.setPath(pathentity, speedIn) : false;
    }

    @Override
    public boolean setPath(PathEntity pathEntityIn, double speedIn)
    { 
        if (pathEntityIn == null)
        {
            this.currentPath = null;
            return false;
        }
        else
        {
            if (!pathEntityIn.isSamePath(this.currentPath))
            {
                this.currentPath = pathEntityIn;
            }

            this.removeSunnyPath();

            if (this.currentPath.getCurrentPathLength() == 0)
            {
                return false;
            }
            else
            {
                this.speed = speedIn;
                Vec3 vec3 = this.getEntityPosition();
                this.ticksAtLastPos = this.totalTicks;
                this.lastPosCheck = vec3;
                return true;
            }
        }
    }

    @Override
    public PathEntity getPath()
    {
        return this.currentPath;
    }

    @Override
    public void onUpdateNavigation()
    {
        ++this.totalTicks;

        if (!this.noPath())
        {
            Vec3 vec3;

            if (this.canNavigate())
            {
                this.pathFollow();
            }
            else if (this.currentPath != null && this.currentPath.getCurrentPathIndex() < this.currentPath.getCurrentPathLength())
            {
                vec3 = this.getEntityPosition();
                Vec3 vec31 = this.currentPath.getVectorFromIndex(this.theEntity, this.currentPath.getCurrentPathIndex());

                if (vec3.yCoord > vec31.yCoord && !this.theEntity.onGround && MathHelper.floor_double(vec3.xCoord) == MathHelper.floor_double(vec31.xCoord) && MathHelper.floor_double(vec3.zCoord) == MathHelper.floor_double(vec31.zCoord))
                {
                    this.currentPath.setCurrentPathIndex(this.currentPath.getCurrentPathIndex() + 1);
                }
            }

            if (!this.noPath())
            {
                vec3 = this.currentPath.getPosition(this.theEntity);

                if (vec3 != null)
                {
                    this.theEntity.getMoveHelper().setMoveTo(vec3.xCoord, vec3.yCoord, vec3.zCoord, this.speed);
                }
            }
        }
    }
    
    protected void pathFollow()
    {
        Vec3 vec3 = this.getEntityPosition();
        int i = this.currentPath.getCurrentPathLength();

        for (int j = this.currentPath.getCurrentPathIndex(); j < this.currentPath.getCurrentPathLength(); ++j)
        {
            if (this.currentPath.getPathPointFromIndex(j).yCoord != (int)vec3.yCoord)
            {
                i = j;
                break;
            }
        }

        float f = this.theEntity.width * this.theEntity.width * this.heightRequirement;
        int k;

        for (k = this.currentPath.getCurrentPathIndex(); k < i; ++k)
        {
            Vec3 vec31 = this.currentPath.getVectorFromIndex(this.theEntity, k);

            if (vec3.squareDistanceTo(vec31) < (double)f)
            {
                this.currentPath.setCurrentPathIndex(k + 1);
            }
        }

        k = MathHelper.ceiling_float_int(this.theEntity.width);
        int j1 = (int)this.theEntity.height + 1;
        int l = k;

        for (int i1 = i - 1; i1 >= this.currentPath.getCurrentPathIndex(); --i1)
        {
            if (this.isDirectPathBetweenPoints(vec3, this.currentPath.getVectorFromIndex(this.theEntity, i1), k, j1, l))
            {
                this.currentPath.setCurrentPathIndex(i1);
                break;
            }
        }

        this.checkForStuck(vec3);
    }

    protected void checkForStuck(Vec3 vec3)
    {
        if (this.totalTicks - this.ticksAtLastPos > 100)
        {
            if (vec3.squareDistanceTo(this.lastPosCheck) < 2.25D)
            {
                this.clearPathEntity();
            }

            this.ticksAtLastPos = this.totalTicks;
            this.lastPosCheck = vec3;
        }
    }

    @Override
    public boolean noPath()
    {
        return this.currentPath == null || this.currentPath.isFinished();
    }

    @Override
    public void clearPathEntity()
    {
        this.currentPath = null;
    }

    protected abstract Vec3 getEntityPosition();

    protected abstract boolean canNavigate();

    protected boolean isInLiquid()
    {
        return Entities.isInWater(this.theEntity) || Entities.isInLava(this.theEntity);
    }

    protected void removeSunnyPath() {}

    protected abstract boolean isDirectPathBetweenPoints(Vec3 posVec31, Vec3 posVec32, int sizeX, int sizeY, int sizeZ);
}