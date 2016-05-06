package com.arisux.avp.entities.pathfinding;

import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class SwimNodeProcessor extends NodeProcessor
{
    public void initProcessor(IBlockAccess iblockaccessIn, Entity entityIn)
    {
        super.initProcessor(iblockaccessIn, entityIn);
    }

    public void postProcess()
    {
        super.postProcess();
    }

    public PathPoint getPathPointTo(Entity entity)
    {
        return this.openPoint(MathHelper.floor_double(entity.getBoundingBox().minX), MathHelper.floor_double(entity.getBoundingBox().minY + 0.5D), MathHelper.floor_double(entity.getBoundingBox().minZ));
    }

    public PathPoint getPathPointToCoords(Entity entityIn, double x, double y, double target)
    {
        return this.openPoint(MathHelper.floor_double(x - (double) (entityIn.width / 2.0F)), MathHelper.floor_double(y + 0.5D), MathHelper.floor_double(target - (double) (entityIn.width / 2.0F)));
    }

    public int findPathOptions(PathPoint[] pathOptions, Entity entityIn, PathPoint currentPoint, PathPoint targetPoint, float maxDistance)
    {
        int i = 0;
        EnumFacing[] aenumfacing = EnumFacing.values();
        int j = aenumfacing.length;

        for (int k = 0; k < j; ++k)
        {
            EnumFacing enumfacing = aenumfacing[k];
            PathPoint pathpoint2 = this.getSafePoint(entityIn, currentPoint.xCoord + enumfacing.getFrontOffsetX(), currentPoint.yCoord + enumfacing.getFrontOffsetY(), currentPoint.zCoord + enumfacing.getFrontOffsetZ());

            if (pathpoint2 != null && !pathpoint2.visited && pathpoint2.distanceTo(targetPoint) < maxDistance)
            {
                pathOptions[i++] = pathpoint2;
            }
        }

        return i;
    }

    private PathPoint getSafePoint(Entity entityIn, int x, int y, int z)
    {
        int l = this.func_176186_b(entityIn, x, y, z);
        return l == -1 ? this.openPoint(x, y, z) : null;
    }

    private int func_176186_b(Entity entity, int posX, int posY, int posZ)
    {
        for (int x = posX; x < posX + this.entitySizeX; ++x)
        {
            for (int y = posY; y < posY + this.entitySizeY; ++y)
            {
                for (int z = posZ; z < posZ + this.entitySizeZ; ++z)
                {
                    CoordData blockpos = new CoordData(x, y, z);
                    Block block = blockpos.getBlock(entity.worldObj);

                    if (block.getMaterial() != Material.water)
                    {
                        return 0;
                    }
                }
            }
        }

        return -1;
    }
}
