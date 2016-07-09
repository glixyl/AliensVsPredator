package com.arisux.airi.lib;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;

public class RayTraceUtil
{
    private static Entity tracedEntity;

    @SuppressWarnings("unchecked")
    public static MovingObjectPosition getMouseOver(float maxDistance, Block[] ignoredBlocks)
    {
        MovingObjectPosition trace = null;
        Minecraft mc = Minecraft.getMinecraft();

        List<Block> blocksRemoved = new ArrayList<Block>();
        List<Vec3> blockPositions = new ArrayList<Vec3>();
        List<NBTTagCompound> blockDatas = new ArrayList<NBTTagCompound>();
        List<Block> blocksIgnored = new ArrayList<Block>(ignoredBlocks.length);

        for (int i = 0; i < ignoredBlocks.length; i++)
        {
            blocksIgnored.add(ignoredBlocks[i]);
        }

        if (mc.renderViewEntity != null)
        {
            if (mc.theWorld != null)
            {
                tracedEntity = null;
                double d0 = maxDistance;

                while (trace == null || (trace.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && blocksIgnored.contains(mc.theWorld.getBlock(trace.blockX, trace.blockY, trace.blockZ))))
                {
                    if (trace != null && trace.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
                    {
                        NBTTagCompound nbt = new NBTTagCompound();
                        TileEntity tile = mc.theWorld.getTileEntity(trace.blockX, trace.blockY, trace.blockZ);
                        if (tile != null)
                        {
                            tile.writeToNBT(nbt);
                            blockDatas.add(nbt);
                        }
                        else
                        {
                            blockDatas.add(null);
                        }
                        blocksRemoved.add(mc.theWorld.getBlock(trace.blockX, trace.blockY, trace.blockZ));
                        blockPositions.add(Vec3.createVectorHelper(trace.blockX, trace.blockY, trace.blockZ));

                        mc.theWorld.setBlock(trace.blockX, trace.blockY, trace.blockZ, Blocks.air);
                    }
                    trace = mc.renderViewEntity.rayTrace(d0, 1);
                }
                double d1 = d0;
                Vec3 positionVec = mc.renderViewEntity.getPosition(1);

                if (trace != null)
                {
                    d1 = trace.hitVec.distanceTo(positionVec);
                }

                Vec3 lookVec = mc.renderViewEntity.getLook(1);
                Vec3 vec32 = positionVec.addVector(lookVec.xCoord * d0, lookVec.yCoord * d0, lookVec.zCoord * d0);
                tracedEntity = null;
                Vec3 vec33 = null;
                float range = 1.0F;
                List<Entity> list = mc.theWorld.getEntitiesWithinAABBExcludingEntity(mc.renderViewEntity, mc.renderViewEntity.boundingBox.addCoord(lookVec.xCoord * d0, lookVec.yCoord * d0, lookVec.zCoord * d0).expand(range, range, range));
                double d2 = d1;

                for (int i = 0; i < list.size(); ++i)
                {
                    Entity entity = list.get(i);

                    if (entity.canBeCollidedWith())
                    {
                        float f2 = entity.getCollisionBorderSize();
                        AxisAlignedBB axisalignedbb = entity.boundingBox.expand(f2, f2, f2);
                        MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(positionVec, vec32);

                        if (axisalignedbb.isVecInside(positionVec))
                        {
                            if (0.0D < d2 || d2 == 0.0D)
                            {
                                tracedEntity = entity;
                                vec33 = movingobjectposition == null ? positionVec : movingobjectposition.hitVec;
                                d2 = 0.0D;
                            }
                        }
                        else if (movingobjectposition != null)
                        {
                            double d3 = positionVec.distanceTo(movingobjectposition.hitVec);

                            if (d3 < d2 || d2 == 0.0D)
                            {
                                if (entity == mc.renderViewEntity.ridingEntity && !entity.canRiderInteract())
                                {
                                    if (d2 == 0.0D)
                                    {
                                        tracedEntity = entity;
                                        vec33 = movingobjectposition.hitVec;
                                    }
                                }
                                else
                                {
                                    tracedEntity = entity;
                                    vec33 = movingobjectposition.hitVec;
                                    d2 = d3;
                                }
                            }
                        }
                    }
                }

                if (tracedEntity != null && (d2 < d1 || trace == null))
                {
                    trace = new MovingObjectPosition(tracedEntity, vec33);
                }
            }
        }

        for (int i = 0; i < blocksRemoved.size(); i++)
        {
            Block block = blocksRemoved.get(i);
            Vec3 position = blockPositions.get(i);
            NBTTagCompound data = blockDatas.get(i);
            mc.theWorld.setBlock((int) position.xCoord, (int) position.yCoord, (int) position.zCoord, block);
            TileEntity tile = mc.theWorld.getTileEntity((int) position.xCoord, (int) position.yCoord, (int) position.zCoord);

            if (tile != null && data != null)
            {
                tile.readFromNBT(data);
            }
        }

        return trace;
    }
}
