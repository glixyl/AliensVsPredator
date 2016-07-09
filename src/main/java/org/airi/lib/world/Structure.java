package com.arisux.airi.lib.world;

import java.util.ArrayList;

import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;

import net.minecraft.init.Blocks;
import net.minecraft.world.WorldServer;

public abstract class Structure
{
    private Schematic schematic;
    private WorldServer world;
    private CoordData data;
    private ArrayList<CoordData> blockQueue;

    public Structure(Schematic schematic, WorldServer world, CoordData data)
    {
        this.schematic = schematic;
        this.world = world;
        this.data = data;
        this.blockQueue = new ArrayList<CoordData>();
        this.queueBlocks();
    }

    public Schematic getSchematic()
    {
        return schematic;
    }

    public WorldServer getWorld()
    {
        return world;
    }

    public CoordData getData()
    {
        return data;
    }

    public ArrayList<CoordData> getBlockQueue()
    {
        return blockQueue;
    }

    public void queueBlocks()
    {
        this.schematic.addBlocksToQueue(this);
    }

    public boolean process()
    {
        this.onProcessing();

        if (world.getWorldInfo().getWorldTime() % 80 == 0)
        {
            System.out.println("Generating " + this.getName() + ": " + this.blockQueue.size() + " blocks left in queue.");
        }

        try
        {
            int sectionSize = 10;
            int queueSize = this.blockQueue.size();

            if (queueSize < sectionSize)
            {
                sectionSize = queueSize;
            }

            for (int i = 0; i < sectionSize; i++)
            {
                CoordData coord = this.blockQueue.get(this.blockQueue.size() - 1 - i);

                if (coord.block != Blocks.air)
                {
                    this.world.setBlock((int) coord.posX, (int) coord.posY, (int) coord.posZ, coord.block, coord.meta, 2);
                }

                this.blockQueue.remove(coord);
            }

            if (this.blockQueue.size() <= 0)
            {
                this.onProcessingComplete();
                System.out.println("Generation of " + this.getName() + " completed.");

                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.onProcessingComplete();
            System.out.println("Generation of " + this.getName() + " completed with an error: " + e);

            return true;
        }

        return false;
    }

    public abstract String getName();

    public abstract void onProcessing();

    public abstract void onProcessingComplete();
}
