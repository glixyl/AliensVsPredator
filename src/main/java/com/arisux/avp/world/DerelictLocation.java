package com.arisux.avp.world;

import java.util.Random;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class DerelictLocation
{
    private Random rand;
    private CoordData coord;
    private boolean generated;
    private int index;

    public DerelictLocation(boolean generated, int index)
    {
        this.rand = new Random();
        this.generated = generated;
        this.index = index;
        this.coord = new CoordData(this.newDerelictXZCoord(), this.newDerelictYCoord(), this.newDerelictXZCoord());
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public CoordData getCoord()
    {
        return coord;
    }

    public DerelictLocation setCoord(CoordData coord)
    {
        this.coord = coord;
        return this;
    }

    public boolean isGenerated()
    {
        return generated;
    }

    public void setGenerated(boolean generated)
    {
        this.generated = generated;
    }

    public void save(NBTTagCompound tag)
    {
        NBTTagCompound tagLocation = new NBTTagCompound();
        tagLocation.setBoolean("Generated", this.generated);
        tagLocation.setInteger("PosX", (int) this.getCoord().posX);
        tagLocation.setInteger("PosY", (int) this.getCoord().posY);
        tagLocation.setInteger("PosZ", (int) this.getCoord().posZ);
        tag.setTag("Location" + this.getIndex(), tagLocation);
    }

    public void load(NBTTagCompound tag)
    {
        NBTTagCompound tagLocation = tag.getCompoundTag("Location" + this.getIndex());
        this.generated = tagLocation.getBoolean("Generated");
        int posX = tag != null ? (tagLocation.getInteger("PosX") == 0 ? this.newDerelictXZCoord() : tagLocation.getInteger("PosX")) : this.newDerelictXZCoord();
        int posY = tag != null ? (tagLocation.getInteger("PosY") == 0 ? this.newDerelictYCoord() : tagLocation.getInteger("PosY")) : this.newDerelictYCoord();
        int posZ = tag != null ? (tagLocation.getInteger("PosZ") == 0 ? this.newDerelictXZCoord() : tagLocation.getInteger("PosZ")) : this.newDerelictXZCoord();

        this.setCoord(new CoordData(posX, posY, posZ));
    }

    public int getDerelictSpawnRange()
    {
        return 1000;
    }

    public int newDerelictXZCoord()
    {
        return 0 - this.rand.nextInt(this.getDerelictSpawnRange()) + this.rand.nextInt(this.getDerelictSpawnRange() * 2);
    }

    public int newDerelictYCoord()
    {
        return 64 + this.rand.nextInt(64);
    }

    public void generate(World world)
    {
        this.generate(world, 0, 0, 0);
    }

    public void generate(World world, int addX, int addY, int addZ)
    {
        AIRI.instance().events.getStructuresInQueue().add(new StructureDerelict(MinecraftServer.getServer().worldServerForDimension(0), this.getCoord().add(addX, addY, addZ)));
        this.setGenerated(true);
    }
}
