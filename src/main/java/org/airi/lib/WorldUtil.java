package com.arisux.airi.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.airi.lib.world.CustomExplosion;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

@SuppressWarnings("all")
public class WorldUtil
{
    /**
     * Create an explosion in the specified world, at the specified coordinates, with the specified effects.
     * 
     * @param entity - The entity that triggered the explosion.
     * @param worldObj - The world that the explosion should be created in.
     * @param data - The CoordData containing the coordinates to create an explosion at.
     * @param strength - The strength of the explosion
     * @param isFlaming - Set to true if the explosion causes surrounding blocks to catch on fire.
     * @param isSmoking - Set to true if the explosion emits smoke particles.
     * @param doesBlockDamage - Set to true if the explosion does physical Block damage.
     * @return Return the instance of the explosion that was just created.
     */
    public static Explosion createExplosion(Entity entity, World worldObj, Blocks.CoordData data, float strength, boolean isFlaming, boolean isSmoking, boolean doesBlockDamage)
    {
        Explosion explosion = new Explosion(worldObj, entity, data.posX, data.posY, data.posZ, strength);
        explosion.isFlaming = isFlaming;
        explosion.isSmoking = isSmoking;

        if (doesBlockDamage)
        {
            explosion.doExplosionA();
        }

        explosion.doExplosionB(true);

        return explosion;
    }

    public static CustomExplosion createCustomExplosion(Entity var1, World worldObj, int posX, int posY, int posZ, float var5)
    {
        CustomExplosion explosion = new CustomExplosion(worldObj, var5, var5, var5, posX, posY, posZ, (new Random()).nextLong());
        explosion.start();

        return explosion;
    }

    /**
     * Gets the next safe position above the specified position
     * 
     * @param entity - The position we're checking for safe positions above.
     * @return The safe position.
     */
    public static CoordData getNextSafePositionAbove(CoordData pos, World world)
    {
        for (int y = (int) pos.posY; y < world.getHeight(); y++)
        {
            CoordData position = new CoordData(pos.posX, y + 1, pos.posZ);

            if (Entities.isPositionSafe(position, world))
            {
                return position;
            }
        }

        return pos;
    }

    public static boolean canSeeSky(CoordData pos, World world)
    {
        for (int y = (int) pos.posY; y < world.getHeight(); y++)
        {
            CoordData position = new CoordData(pos.posX, y + 1, pos.posZ);

            if (position.getBlock(world) != net.minecraft.init.Blocks.air)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Get the light intensity as an Integer at the specified coordinates in the specified world.
     * 
     * @param worldObj - World to check for brightness values in.
     * @param data - CoordData containing coordinates of the location to check brightness at.
     * @return Returns light intensity of a block as an Integer.
     */
    public static int getLightAtCoord(World worldObj, Blocks.CoordData data)
    {
        int block = worldObj.getSkyBlockTypeBrightness(EnumSkyBlock.Block, (int) data.posX, (int) data.posY, (int) data.posZ);
        int sky = worldObj.getSkyBlockTypeBrightness(EnumSkyBlock.Sky, (int) data.posX, (int) data.posY, (int) data.posZ) - worldObj.calculateSkylightSubtracted(0f);

        return Math.max(block, sky);
    }

    /**
     * Gets the next safe position below the specified position
     * 
     * @param pos - The position  we're checking for safe positions below.
     * @return The safe position.
     */
    public static CoordData getNextSafePositionBelow(CoordData pos, World world)
    {
        for (int y = (int) pos.posY; y > 0; y--)
        {
            CoordData position = new CoordData(pos.posX, y - 1, pos.posZ);

            if (Entities.isPositionSafe(position, world))
            {
                return position;
            }
        }

        return pos;
    }

    /**
     * Generate a group of the specified Block in the World, a given amount of times, in a Chunk at the given 
     * CoordData's X and Z coords using the specified group size and seed.
     * 
     * @param world - The World instance to generate in.
     * @param block - The Block instance to generate.
     * @param seed - The seed to generate random group coords at.
     * @param genPerChunk - The amount of times to generate this block group per chunk.
     * @param groupSize - The amount of blocks to generate per generation.
     * @param chunkCoord - The CoordData containing the X and Z coordinates of the Chunk to generate in.
     */
    public static void generateBlockInChunk(World world, Block block, Random seed, int genPerChunk, int groupSize, CoordData chunkCoord)
    {
        generateBlockInChunk(world, block, seed, genPerChunk, groupSize, 0, 128, chunkCoord, BiomeGenBase.getBiomeGenArray());
    }

    /**
     * Generate a group of the specified Block in the World, a given amount of times, in a Chunk at the given 
     * CoordData's X and Z coords using the specified group size and seed.
     * 
     * @param world - The World instance to generate in.
     * @param block - The Block instance to generate.
     * @param seed - The seed to generate random group coords at.
     * @param genPerChunk - The amount of times to generate this block group per chunk.
     * @param groupSize - The amount of blocks to generate per generation.
     * @param chunkCoord - The CoordData containing the X and Z coordinates of the Chunk to generate in.
     * @param biomes - The BiomeGenBase instances to generate in.
     */
    public static void generateBlockInChunk(World world, Block block, Random seed, int genPerChunk, int groupSize, CoordData chunkCoord, BiomeGenBase[] biomes)
    {
        generateBlockInChunk(world, block, seed, genPerChunk, groupSize, 0, 128, chunkCoord, biomes);
    }

    /**
     * Generate a group of the specified Block in the World, a given amount of times, in a Chunk at the given 
     * CoordData's X and Z coords using the specified group size and seed.
     * 
     * @param world - The World instance to generate in.
     * @param block - The Block instance to generate.
     * @param seed - The seed to generate random group coords at.
     * @param genPerChunk - The amount of times to generate this block group per chunk.
     * @param groupSize - The amount of blocks to generate per generation.
     * @param levelStart - The level that this block group can start generating on
     * @param levelEnd - The level that this block group can stop generating on
     * @param chunkCoord - The CoordData containing the X and Z coordinates of the Chunk to generate in.
     */
    public static void generateBlockInChunk(World world, Block block, Random seed, int genPerChunk, int groupSize, int levelStart, int levelEnd, CoordData chunkCoord)
    {
        generateBlockInChunk(world, block, seed, genPerChunk, groupSize, 0, 128, chunkCoord, BiomeGenBase.getBiomeGenArray());
    }

    /**
     * Generate a group of the specified Block in the World, a given amount of times, in a Chunk at the given 
     * CoordData's X and Z coords using the specified group size and seed.
     * 
     * @param world - The World instance to generate in.
     * @param block - The Block instance to generate.
     * @param seed - The seed to generate random group coords at.
     * @param genPerChunk - The amount of times to generate this block group per chunk.
     * @param groupSize - The amount of blocks to generate per generation.
     * @param levelStart - The level that this block group can start generating on
     * @param levelEnd - The level that this block group can stop generating on
     * @param chunkCoord - The CoordData containing the X and Z coordinates of the Chunk to generate in.
     * @param biomes - The BiomeGenBase instances to generate in.
     */
    public static void generateBlockInChunk(World world, Block block, Random seed, int genPerChunk, int groupSize, int levelStart, int levelEnd, CoordData chunkCoord, BiomeGenBase[] biomes)
    {
        for (BiomeGenBase biome : biomes)
        {
            if (world.provider.getBiomeGenForCoords((int) chunkCoord.posX, (int) chunkCoord.posZ) == biome)
            {
                for (int i = 0; i < genPerChunk; ++i)
                {
                    int posX = (int) chunkCoord.posX + seed.nextInt(16);
                    int posY = levelStart + seed.nextInt(levelEnd);
                    int posZ = (int) chunkCoord.posZ + seed.nextInt(16);
                    (new WorldGenMinable(block, groupSize)).generate(world, seed, posX, posY, posZ);
                }
            }
        }
    }

    /**
     * Generate the specified WorldGenerator instance in the World, a given amount of times, in a Chunk at the given 
     * CoordData's X and Z coords using the specified seed.
     * 
     * @param world - The World instance to generate in.
     * @param worldGen - The WorldGenerator instance to generate.
     * @param seed - The seed to generate random group coords at.
     * @param genPerChunk - The amount of times to generate this WorldGenerator per chunk.
     * @param chunkCoord - The CoordData containing the X and Z coordinates of the Chunk to generate in.
     */
    public static void generateWorldGenInChunk(World world, WorldGenerator worldGen, Random seed, int genPerChunk, CoordData chunkCoord)
    {
        generateWorldGenInChunk(world, worldGen, seed, genPerChunk, 0, 128, chunkCoord, BiomeGenBase.getBiomeGenArray());
    }

    /**
     * Generate the specified WorldGenerator instance in the World, a given amount of times, in a Chunk at the given 
     * CoordData's X and Z coords using the specified seed.
     * 
     * @param world - The World instance to generate in.
     * @param worldGen - The WorldGenerator instance to generate.
     * @param seed - The seed to generate random group coords at.
     * @param genPerChunk - The amount of times to generate this WorldGenerator per chunk.
     * @param chunkCoord - The CoordData containing the X and Z coordinates of the Chunk to generate in.
     * @param biomes - The BiomeGenBase instances to generate in.
     */
    public static void generateWorldGenInChunk(World world, WorldGenerator worldGen, Random seed, int genPerChunk, CoordData chunkCoord, BiomeGenBase[] biomes)
    {
        generateWorldGenInChunk(world, worldGen, seed, genPerChunk, 0, 128, chunkCoord, biomes);
    }

    /**
     * Generate the specified WorldGenerator instance in the World, a given amount of times, in a Chunk at the given 
     * CoordData's X and Z coords using the specified seed.
     * 
     * @param world - The World instance to generate in.
     * @param worldGen - The WorldGenerator instance to generate.
     * @param seed - The seed to generate random group coords at.
     * @param genPerChunk - The amount of times to generate this WorldGenerator per chunk.
     * @param levelStart - The level that this WorldGenerator can start generating on
     * @param levelEnd - The level that this WorldGenerator can stop generating on
     * @param chunkCoord - The CoordData containing the X and Z coordinates of the Chunk to generate in.
     */
    public static void generateWorldGenInChunk(World world, WorldGenerator worldGen, Random seed, int genPerChunk, int levelStart, int levelEnd, CoordData chunkCoord)
    {
        generateWorldGenInChunk(world, worldGen, seed, genPerChunk, 0, 128, chunkCoord, BiomeGenBase.getBiomeGenArray());
    }

    /**
     * Generate the specified WorldGenerator instance in the World, a given amount of times, in a Chunk at the given 
     * CoordData's X and Z coords using the specified seed.
     * 
     * @param world - The World instance to generate in.
     * @param worldGen - The WorldGenerator instance to generate.
     * @param seed - The seed to generate random group coords at.
     * @param genPerChunk - The amount of times to generate this WorldGenerator per chunk.
     * @param levelStart - The level that this WorldGenerator can start generating on
     * @param levelEnd - The level that this WorldGenerator can stop generating on
     * @param chunkCoord - The CoordData containing the X and Z coordinates of the Chunk to generate in.
     * @param biomes - The BiomeGenBase instances to generate in.
     */
    public static void generateWorldGenInChunk(World world, WorldGenerator worldGen, Random seed, int genPerChunk, int levelStart, int levelEnd, CoordData chunkCoord, BiomeGenBase[] biomes)
    {
        for (BiomeGenBase biome : biomes)
        {
            if (world.provider.getBiomeGenForCoords((int) chunkCoord.posX, (int) chunkCoord.posZ) == biome)
            {
                for (int i = 0; i < genPerChunk; ++i)
                {
                    int posX = (int) chunkCoord.posX + seed.nextInt(16);
                    int posY = levelStart + seed.nextInt(levelEnd);
                    int posZ = (int) chunkCoord.posZ + seed.nextInt(16);
                    worldGen.generate(world, seed, posX, posY, posZ);
                }
            }
        }
    }

    public static Entity getEntityByUUID(World world, UUID uuid)
    {
        for (Object o : world.getLoadedEntityList().toArray())
        {
            if (o instanceof Entity)
            {
                Entity entity = (Entity) o;
                
                if (entity.getPersistentID().equals(uuid))
                {
                    return entity;
                }
            }
        }
        
        return null;
    }

    public static class NBT
    {
        /**
         * Copies an ArrayList of Strings into an NBTTagList of Strings.
         * 
         * @param list - The ArrayList to be converted.
         * @return NBTTagList created from the ArrayList.
         */
        public static NBTTagList newStringNBTList(ArrayList<String> list)
        {
            NBTTagList nbttaglist = new NBTTagList();

            for (int j = 0; j < list.size(); ++j)
            {
                String s1 = list.get(j);
                nbttaglist.appendTag(new NBTTagString(s1));
            }

            return nbttaglist;
        }

        /**
         * Copies an ArrayList of NBTTagCompounds into an NBTTagList of NBTTagCompounds.
         * 
         * @param list - The ArrayList to be converted.
         * @return NBTTagList created from the ArrayList.
         */
        public static NBTTagList newCompoundNBTList(ArrayList<NBTTagCompound> list)
        {
            NBTTagList nbttaglist = new NBTTagList();

            for (int j = 0; j < list.size(); ++j)
            {
                NBTTagCompound nbt = list.get(j);
                nbttaglist.appendTag(nbt);
            }

            return nbttaglist;
        }

        /**
         * Writes an uncompressed NBTTagCompound to the specified File
         * 
         * @param nbt - The NBTTagCompound to be written.
         * @param file - The File to write to.
         */
        public static void write(NBTTagCompound nbt, File file)
        {
            try
            {
                CompressedStreamTools.write(nbt, file);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        /**
         * @param file - The File to read an NBTTagCompound from.
         * @return The uncompressed NBTTagCompound being read from the specified File.
         */
        public static NBTTagCompound read(File file)
        {
            NBTTagCompound nbtTagCompound = null;

            try
            {
                nbtTagCompound = CompressedStreamTools.read(file);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return nbtTagCompound;
        }

        /**
         * Writes a compressed NBTTagCompound to the specified File
         * 
         * @param nbt - The NBTTagCompound to be written.
         * @param file - The File to write to.
         * @throws IOException 
         */
        public static void writeCompressed(NBTTagCompound nbt, File file) throws IOException
        {
            FileOutputStream stream = new FileOutputStream(file);

            CompressedStreamTools.writeCompressed(nbt, stream);

            if (stream != null)
            {
                stream.close();
            }
        }

        /**
         * @param file - The File to read an NBTTagCompound from.
         * @return The compressed NBTTagCompound being read from the specified File.
         * @throws IOException 
         */
        public static NBTTagCompound readCompressed(File file) throws IOException
        {
            NBTTagCompound nbtTagCompound = null;
            FileInputStream stream = new FileInputStream(file);

            nbtTagCompound = CompressedStreamTools.readCompressed(stream);

            if (stream != null)
            {
                stream.close();
            }

            return nbtTagCompound;
        }
    }

    public static class Blocks
    {
        public static ArrayList<CoordData> getCoordDataInRange(int posX, int posY, int posZ, int range)
        {
            ArrayList<CoordData> data = new ArrayList<CoordData>();

            for (int x = posX - range; x < posX + range * 2; x++)
            {
                for (int y = posY - range; y < posY + range * 2; y++)
                {
                    for (int z = posZ - range; z < posZ + range * 2; z++)
                    {
                        data.add(new CoordData(x, y, z));
                    }
                }
            }

            return data;
        }

        public static ArrayList<CoordData> getCoordDataInRangeForBlocks(int posX, int posY, int posZ, int range, World world, Block... types)
        {
            ArrayList<CoordData> data = new ArrayList<CoordData>();

            for (int x = posX - range; x < posX + range * 2; x++)
            {
                for (int y = posY - range; y < posY + range * 2; y++)
                {
                    for (int z = posZ - range; z < posZ + range * 2; z++)
                    {
                        CoordData coordData = new CoordData(x, y, z);
                        Block block = coordData.getBlock(world);

                        if (Arrays.asList(types).contains(block))
                        {
                            data.add(coordData);
                        }
                    }
                }
            }

            return data;
        }

        public static ArrayList<CoordData> getCoordDataInRangeForBlocksExcluding(int posX, int posY, int posZ, int range, World world, Block... types)
        {
            ArrayList<CoordData> data = new ArrayList<CoordData>();

            for (int x = posX - range; x < posX + range * 2; x++)
            {
                for (int y = posY - range; y < posY + range * 2; y++)
                {
                    for (int z = posZ - range; z < posZ + range * 2; z++)
                    {
                        CoordData coordData = new CoordData(x, y, z);
                        Block block = coordData.getBlock(world);

                        if (!Arrays.asList(types).contains(block))
                        {
                            data.add(coordData);
                        }
                    }
                }
            }

            return data;
        }

        public static class CoordData
        {
            public double posX, posY, posZ;
            public int meta;
            public Block block;
            public UniqueIdentifier identifier;

            public CoordData(Entity entity)
            {
                this(Math.round(entity.posX), Math.round(entity.posY), Math.round(entity.posZ));
            }

            public CoordData(TileEntity tileEntity)
            {
                this(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, tileEntity.getWorld() != null ? tileEntity.getWorld().getBlock(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord) : null, tileEntity.getWorld() != null ? tileEntity.getWorld().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord) : 0);
            }

            public CoordData(double posX, double posY, double posZ, Block block)
            {
                this(posX, posY, posZ, block, 0);
            }

            public CoordData(double posX, double posY, double posZ, Block block, double meta)
            {
                this(posX, posY, posZ, GameRegistry.findUniqueIdentifierFor(block));
                this.block = block;
            }

            public CoordData(double posX, double posY, double posZ, String id)
            {
                this(posX, posY, posZ, id, 0);
            }

            public CoordData(double posX, double posY, double posZ, String id, double meta)
            {
                this(posX, posY, posZ, new UniqueIdentifier(id), 0);
            }

            public CoordData(double posX, double posY, double posZ, UniqueIdentifier identifier)
            {
                this(posX, posY, posZ, identifier, 0);
            }

            public CoordData(double posX, double posY, double posZ, UniqueIdentifier identifier, int meta)
            {
                this.identifier = identifier;
                this.posX = posX;
                this.posY = posY;
                this.posZ = posZ;
                this.meta = meta;
            }

            public CoordData(double posX, double posY, double posZ)
            {
                this.posX = posX;
                this.posY = posY;
                this.posZ = posZ;
            }

            public CoordData(long posX, long posY, long posZ)
            {
                this.posX = (double) posX;
                this.posY = (double) posY;
                this.posZ = (double) posZ;
            }

            public CoordData(float posX, float posY, float posZ)
            {
                this.posX = (double) posX;
                this.posY = (double) posY;
                this.posZ = (double) posZ;
            }

            public CoordData(int posX, int posY, int posZ)
            {
                this.posX = (double) posX;
                this.posY = (double) posY;
                this.posZ = (double) posZ;
            }

            @Override
            public boolean equals(Object o)
            {
                if (o == null || this != o || !(o instanceof CoordData) || o instanceof CoordData && ((CoordData) o).posX != this.posX || o instanceof CoordData && ((CoordData) o).posY != this.posY || o instanceof CoordData && ((CoordData) o).posZ != this.posZ)
                {
                    return false;
                }

                return true;
            }

            @Override
            public int hashCode()
            {
                int result = (int) this.posX;
                result = 31 * result + (int) this.posY;
                result = 31 * result + (int) this.posZ;
                return result;
            }

            public double x()
            {
                return posX;
            }

            public double y()
            {
                return posY;
            }

            public double z()
            {
                return posZ;
            }

            public Block getBlock(World world)
            {
                return getBlock(world, false);
            }

            public Block getBlock(World world, boolean force)
            {
                return this.block == null && world != null || force ? world.getBlock((int) this.posX, (int) this.posY, (int) this.posZ) : this.block;
            }

            public int getBlockMetadata(World world)
            {
                return this.meta == 0 && world != null ? world.getBlockMetadata((int) this.posX, (int) this.posY, (int) this.posZ) : 0;
            }

            public TileEntity getTileEntity(World world)
            {
                return world.getTileEntity((int) this.posX, (int) this.posY, (int) this.posZ);
            }

            public CoordData min(CoordData data)
            {
                return new CoordData(Math.min(this.posX, data.posX), Math.min(this.posY, data.posY), Math.min(this.posZ, data.posZ));
            }

            public CoordData max(CoordData data)
            {
                return new CoordData(Math.max(this.posX, data.posX), Math.max(this.posY, data.posY), Math.max(this.posZ, data.posZ));
            }

            public CoordData add(CoordData data)
            {
                return new CoordData(this.posX + data.posX, this.posY + data.posY, this.posZ + data.posZ);
            }

            public CoordData add(double posX, double posY, double posZ)
            {
                return this.add(new CoordData(posX, posY, posZ));
            }

            public CoordData subtract(CoordData data)
            {
                return new CoordData(this.max(data).posX - this.min(data).posX, this.max(data).posY - this.min(data).posY, this.max(data).posZ - this.min(data).posZ);
            }

            public CoordData subtract(double posX, double posY, double posZ)
            {
                return this.add(new CoordData(posX, posY, posZ));
            }

            public CoordData offsetX(double amount)
            {
                this.posX = this.posX + amount;
                return this;
            }

            public CoordData offsetY(double amount)
            {
                this.posY = this.posY + amount;
                return this;
            }

            public CoordData offsetZ(double amount)
            {
                this.posZ = this.posZ + amount;
                return this;
            }

            public void set(CoordData data)
            {
                this.identifier = data.identifier;
                this.block = data.block;
                this.posX = data.posX;
                this.posY = data.posY;
                this.posZ = data.posZ;
            }

            public UniqueIdentifier identity()
            {
                return this.identity(null);
            }

            public UniqueIdentifier identity(World world)
            {
                return this.identifier == null ? GameRegistry.findUniqueIdentifierFor(this.getBlock(world)) : this.identifier;
            }

            public NBTTagCompound writeToNBT()
            {
                return this.writeToNBT(null);
            }

            public NBTTagCompound writeToNBT(NBTTagCompound nbt)
            {
                return this.writeToNBT(nbt, "Id", "PosX", "PosY", "PosZ");
            }

            public NBTTagCompound writeToNBT(NBTTagCompound nbt, String labelId, String labelX, String labelY, String labelZ)
            {
                NBTTagCompound dataTag = nbt == null ? new NBTTagCompound() : nbt;

                dataTag.setString(labelId, String.format("%s:%s", this.identity().modId, this.identity().name));
                dataTag.setDouble(labelX, this.posX);
                dataTag.setDouble(labelY, this.posY);
                dataTag.setDouble(labelZ, this.posZ);

                return dataTag;
            }

            public CoordData readFromNBT(NBTTagCompound nbt)
            {
                return this.readFromNBT(nbt, "Id", "PosX", "PosY", "PosZ");
            }

            public CoordData readFromNBT(NBTTagCompound nbt, String labelId, String labelX, String labelY, String labelZ)
            {
                return new CoordData(nbt.getInteger(labelX), nbt.getInteger(labelY), nbt.getInteger(labelZ), nbt.getString(labelId));
            }

            @Override
            public String toString()
            {
                return String.format("CoordData/Coords[%s, %s, %s]/Block[%s:%s]/Object[%s]", this.posX, this.posY, this.posZ, this.block, this.meta, this.getClass());
            }

            public boolean isAnySurfaceVisible(World world)
            {
                CoordData up = this.add(0, 1, 0);
                CoordData down = this.add(0, -1, 0);
                CoordData left = this.add(-1, 0, 0);
                CoordData right = this.add(1, 0, 0);
                CoordData front = this.add(0, 0, -1);
                CoordData back = this.add(0, 0, 1);

                return up.getBlock(world) == net.minecraft.init.Blocks.air || down.getBlock(world) == net.minecraft.init.Blocks.air || left.getBlock(world) == net.minecraft.init.Blocks.air || right.getBlock(world) == net.minecraft.init.Blocks.air || front.getBlock(world) == net.minecraft.init.Blocks.air || back.getBlock(world) == net.minecraft.init.Blocks.air;
            }

            public CoordData findSafePosAround(World world)
            {
                CoordData pos = this;
                CoordData up = pos.add(0, 1, 0);
                CoordData down = pos.add(0, -1, 0);
                CoordData left = pos.add(-1, 0, 0);
                CoordData right = pos.add(1, 0, 0);
                CoordData front = pos.add(0, 0, -1);
                CoordData frontLeft = pos.add(-1, 0, -1);
                CoordData frontRight = pos.add(1, 0, -1);
                CoordData back = pos.add(0, 0, 1);
                CoordData backLeft = pos.add(-1, 0, 1);
                CoordData backRight = pos.add(1, 0, 1);

                if (pos.getBlock(world) != net.minecraft.init.Blocks.air)
                {
                    if (left.getBlock(world) == net.minecraft.init.Blocks.air)
                    {
                        pos = left;
                    }
                    else if (right.getBlock(world) == net.minecraft.init.Blocks.air)
                    {
                        pos = right;
                    }
                    else if (front.getBlock(world) == net.minecraft.init.Blocks.air)
                    {
                        pos = front;
                    }
                    else if (frontLeft.getBlock(world) == net.minecraft.init.Blocks.air)
                    {
                        pos = frontLeft;
                    }
                    else if (frontRight.getBlock(world) == net.minecraft.init.Blocks.air)
                    {
                        pos = frontRight;
                    }
                    else if (back.getBlock(world) == net.minecraft.init.Blocks.air)
                    {
                        pos = left;
                    }
                    else if (backLeft.getBlock(world) == net.minecraft.init.Blocks.air)
                    {
                        pos = backLeft;
                    }
                    else if (backRight.getBlock(world) == net.minecraft.init.Blocks.air)
                    {
                        pos = backRight;
                    }
                    else if (up.getBlock(world) == net.minecraft.init.Blocks.air)
                    {
                        pos = up;
                    }
                    else if (down.getBlock(world) == net.minecraft.init.Blocks.air)
                    {
                        pos = down;
                    }
                }

                return pos.add(0.5, 0.0, 0.5);
            }

            public static CoordData fromBytes(ByteBuf buf)
            {
                return new CoordData(buf.readDouble(), buf.readDouble(), buf.readDouble());
            }

            public void toBytes(ByteBuf buf)
            {
                buf.writeDouble(this.x());
                buf.writeDouble(this.y());
                buf.writeDouble(this.z());
            }
        }

        public static class CoordSelection implements Iterable<CoordData>
        {
            private CoordData pos1;
            private CoordData pos2;

            public CoordSelection(CoordData pos1, CoordData pos2)
            {
                this.pos1 = pos1;
                this.pos2 = pos2;
            }

            public static CoordSelection areaFromSize(CoordData coord, int[] size)
            {
                if (size[0] <= 0 || size[1] <= 0 || size[2] <= 0)
                {
                    throw new IllegalArgumentException();
                }

                return new CoordSelection(coord, new CoordData(coord.posX + size[0] - 1, coord.posY + size[1] - 1, coord.posZ + size[2] - 1));
            }

            public CoordData getPos1()
            {
                return pos1;
            }

            public void setPos1(CoordData point1)
            {
                this.pos1 = point1;
            }

            public CoordData getPos2()
            {
                return pos2;
            }

            public void setPos2(CoordData point2)
            {
                this.pos2 = point2;
            }

            public CoordData min()
            {
                return pos1.min(pos2);
            }

            public CoordData max()
            {
                return pos1.max(pos2);
            }

            public double[] areaSize()
            {
                return new double[] { max().subtract(min()).posX + 1, max().subtract(min()).posY + 1, max().subtract(min()).posZ + 1 };
            }

            public boolean contains(CoordData coord)
            {
                return coord.posX >= min().posX && coord.posY >= min().posY && coord.posZ >= min().posZ && coord.posX <= max().posX && coord.posY <= max().posY && coord.posZ <= max().posZ;
            }

            public AxisAlignedBB asAxisAlignedBB()
            {
                return AxisAlignedBB.getBoundingBox(min().posX, min().posY, min().posZ, max().posX, max().posY, max().posZ);
            }

            @Override
            public Iterator<CoordData> iterator()
            {
                return new Blocks.CoordSelectionIterator(min(), max());
            }
        }

        public static class CoordSelectionIterator implements Iterator<CoordData>
        {
            private CoordData min;
            private CoordData max;

            private int curX;
            private int curY;
            private int curZ;

            public CoordSelectionIterator(CoordData min, CoordData max)
            {
                this.min = min;
                this.max = max;
                this.curX = (int) min.posX;
                this.curY = (int) min.posY;
                this.curZ = (int) min.posZ;
            }

            @Override
            public boolean hasNext()
            {
                return this.curX <= this.max.posX && this.curY <= this.max.posY && this.curZ <= this.max.posZ;
            }

            @Override
            public CoordData next()
            {
                CoordData coord = this.hasNext() ? new CoordData(this.curX, this.curY, this.curZ) : null;

                this.curX++;

                if (this.curX > this.max.posX)
                {
                    this.curX = (int) this.min.posX;
                    this.curY++;

                    if (this.curY > this.max.posY)
                    {
                        this.curY = (int) this.min.posY;
                        this.curZ++;
                    }
                }

                return coord;
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        }

        public static String getDomain(Block block)
        {
            String domain = "minecraft:";

            if (block.getUnlocalizedName().contains(":"))
            {
                domain = (block.getUnlocalizedName().split(":")[0] + ":").replace("tile.", "");
            }

            return domain;
        }

    }

    public static class TileEntities
    {
        /**
         * Move the specified TileEntity to the specified CoordData coordinates.
         * 
         * @param tileEntity - The TileEntity to change the position of.
         * @param coord - The CoordData instance containing the new set of coordinates.
         */
        public static void setTileEntityPosition(TileEntity tileEntity, Blocks.CoordData coord)
        {
            tileEntity.xCoord = (int) coord.posX;
            tileEntity.yCoord = (int) coord.posY;
            tileEntity.zCoord = (int) coord.posZ;
        }

        /**
         * Move the specified TileEntity by the specified amount of coordinates.
         * 
         * @param tileEntity - The TileEntity to be moved.
         * @param coord - The CoordData instance containing the coordinates to add.
         */
        public static void moveTileEntity(TileEntity tileEntity, Blocks.CoordData coord)
        {
            WorldUtil.TileEntities.setTileEntityPosition(tileEntity, (new Blocks.CoordData(tileEntity)).add(coord));
        }

    }

    public static class Entities
    {
        /**
         * Get the first Entity instance of the specified class type found, 
         * within specified range, at the specified world coordinates.
         * 
         * @param worldObj - World instance to scan for entities in
         * @param entityClass - Entity class type to scan for.
         * @param data - The CoordData containing the coordinates to start scanning at.
         * @param range - Range of blocks to scan within.
         * @return First Entity instance found using the specified parameters.
         */
        public static Entity getEntityInCoordsRange(World worldObj, Class<? extends Entity> entityClass, Blocks.CoordData data, int range)
        {
            return getEntityInCoordsRange(worldObj, entityClass, data, range, 16);
        }

        /**
         * Checks if the specified position is safe for an entity to spawn at.
         * 
         * @param pos - The position we are checking.
         * @param world - The world instance we are checking in.
         * @return true if the position is safe.
         */

        public static boolean isPositionSafe(CoordData pos, World world)
        {
            if (pos != null && world != null)
            {
                CoordData newPos = new CoordData(pos.posX, pos.posY, pos.posZ);
                CoordData newPosBelow = new CoordData(pos.posX, pos.posY - 1, pos.posZ);

                return newPosBelow.getBlock(world) != net.minecraft.init.Blocks.air && newPos.getBlock(world) == net.minecraft.init.Blocks.air;
            }

            return false;
        }

        /**
         * Gets a safe position for the entity to spawn at from the given position.
         * 
         * @param pos - The position that we should check around.
         * @param world - The world we're checking for a safe position in.
         * @return The safe position.
         */
        public static CoordData getSafePosition(CoordData pos, World world)
        {
            CoordData newSafePosition = WorldUtil.getNextSafePositionAbove(pos, world);

            if (newSafePosition == null)
            {
                newSafePosition = WorldUtil.getNextSafePositionBelow(pos, world);
            }

            // if (!(isPositionSafe(newSafePosition, world)))
            // {
            // Random rand = new Random();
            // newSafePosition = getSafePosition(new CoordData(rand.nextInt(30000), rand.nextInt(256), rand.nextInt(30000)), world);
            // }

            return newSafePosition;
        }

        /**
         * Get the first Entity instance of the specified class type found, 
         * within specified range, at the specified world coordinates, within specified height.
         * 
         * @param worldObj - World instance to scan for entities in
         * @param entityClass - Entity class type to scan for.
         * @param data - The CoordData containing the coordinates to start scanning at.
         * @param range - Range of blocks to scan within.
         * @param height - Height to scan for entities within
         * @return First Entity instance found using the specified parameters.
         */
        public static Entity getEntityInCoordsRange(World worldObj, Class<? extends Entity> entityClass, Blocks.CoordData data, int range, int height)
        {
            List<? extends Entity> entities = getEntitiesInCoordsRange(worldObj, entityClass, data, range, height);

            return entities.size() >= 1 ? (Entity) entities.get(worldObj.rand.nextInt(entities.size())) : null;
        }

        /**
         * Get a random Entity instance of the specified class type found, 
         * within specified range, at the specified world coordinates, within specified height.
         * 
         * @param worldObj - World instance to scan for entities in
         * @param entityClass - Entity class type to scan for.
         * @param data - The CoordData containing the coordinates to start scanning at.
         * @param range - Range of blocks to scan within.
         * @return First Entity instance found using the specified parameters.
         */
        public static Entity getRandomEntityInCoordsRange(World worldObj, Class<? extends Entity> entityClass, Blocks.CoordData data, int range)
        {
            return getRandomEntityInCoordsRange(worldObj, entityClass, data, range, 16);
        }

        /**
         * Get a random Entity instance of the specified class type found, 
         * within specified range, at the specified world coordinates, within specified height.
         * 
         * @param worldObj - World instance to scan for entities in
         * @param entityClass - Entity class type to scan for.
         * @param data - The CoordData containing the coordinates to start scanning at.
         * @param range - Range of blocks to scan within.
         * @param height - Height to scan for entities within
         * @return First Entity instance found using the specified parameters.
         */
        public static Entity getRandomEntityInCoordsRange(World worldObj, Class<? extends Entity> entityClass, Blocks.CoordData data, int range, int height)
        {
            List<? extends Entity> entities = getEntitiesInCoordsRange(worldObj, entityClass, data, range, height);

            return entities.size() > 1 ? (Entity) entities.get((new Random()).nextInt(entities.size())) : null;
        }

        /**
         * Gets all Entity instances of the specified class type found, 
         * within specified range, at the specified world coordinates, within specified height.
         * 
         * @param worldObj - World instance to scan for entities in
         * @param entityClass - Entity class type to scan for.
         * @param data - The CoordData containing the coordinates to start scanning at.
         * @param range - Range of blocks to scan within.
         * @return All the Entity instances found using the specified parameters.
         */
        public static List<? extends Entity> getEntitiesInCoordsRange(World worldObj, Class<? extends Entity> entityClass, Blocks.CoordData data, int range)
        {
            return getEntitiesInCoordsRange(worldObj, entityClass, data, range, 16);
        }

        /**
         * Gets all Entity instances of the specified class type found, 
         * within specified range, at the specified world coordinates, within specified height.
         * 
         * @param worldObj - World instance to scan for entities in
         * @param entityClass - Entity class type to scan for.
         * @param data - The CoordData containing the coordinates to start scanning at.
         * @param range - Range of blocks to scan within.
         * @param height - Height to scan for entities within
         * @return All the Entity instances found using the specified parameters.
         */
        @SuppressWarnings("unchecked")
        public static List<? extends Entity> getEntitiesInCoordsRange(World worldObj, Class<? extends Entity> entityClass, Blocks.CoordData data, int range, int height)
        {
            return worldObj.getEntitiesWithinAABB(entityClass, AxisAlignedBB.getBoundingBox(data.posX, data.posY, data.posZ, data.posX + 1, data.posY + 1, data.posZ + 1).expand(range * 2, height, range * 2));
        }

        /**
         * @param entity - The entity that entityLooking is looking for.
         * @param entityLooking - The entity that is looking for the first entity.
         * @return Returns true if the first Entity can be seen by the second Entity.
         */
        public static boolean canEntityBeSeenBy(Entity entity, Entity entityLooking)
        {
            return rayTrace(entity, entityLooking) == null;
        }

        public static boolean canCoordBeSeenBy(Entity entity, CoordData coord)
        {
            return rayTrace(entity, coord) == null;
        }

        /**
         * @param entity - The entity that entityLooking is looking for.
         * @param entityLooking - The entity that is looking for the first entity.
         * @return Returns the MovingObjectPosition hit by the rayTrace.
         */
        public static MovingObjectPosition rayTrace(Entity entity, Entity entityLooking)
        {
            return entity != null && entityLooking != null && entity.worldObj != null ? entity.worldObj.rayTraceBlocks(Vec3.createVectorHelper(entity.posX, entity.posY + (entity.height / 2), entity.posZ), Vec3.createVectorHelper(entityLooking.posX, entityLooking.posY + entityLooking.getEyeHeight(), entityLooking.posZ)) : null;
        }

        public static MovingObjectPosition rayTrace(Entity entity, CoordData coord)
        {
            return entity != null && coord != null && entity.worldObj != null ? entity.worldObj.rayTraceBlocks(Vec3.createVectorHelper(entity.posX, entity.posY + (entity.height / 2), entity.posZ), Vec3.createVectorHelper(coord.posX, coord.posY, coord.posZ)) : null;
        }

        public static MovingObjectPosition rayTrace(EntityLivingBase player, int reach)
        {
            Vec3 pos = player.getPosition(1F);
            Vec3 entityLook = player.getLook(1F);

            Entity pointedEntity = null;
            Vec3 hitVec = null;
            Vec3 posReach = null;

            if (entityLook != null)
            {
                posReach = pos.addVector(entityLook.xCoord * reach, entityLook.yCoord * reach, entityLook.zCoord * reach);

                List<Entity> entities = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(entityLook.xCoord * reach, entityLook.yCoord * reach, entityLook.zCoord * reach).expand(1.0F, 1.0F, 1.0F));

                for (Entity listEntity : entities)
                {
                    if (listEntity.canBeCollidedWith())
                    {
                        float borderSize = listEntity.getCollisionBorderSize();
                        AxisAlignedBB axisalignedbb = listEntity.boundingBox.expand(borderSize, borderSize, borderSize);
                        MovingObjectPosition movingObjPos = axisalignedbb.calculateIntercept(pos, posReach);

                        if (axisalignedbb.isVecInside(pos))
                        {
                            pointedEntity = listEntity;
                            hitVec = movingObjPos == null ? pos : movingObjPos.hitVec;
                        }
                        else if (movingObjPos != null)
                        {
                            if (listEntity == player.ridingEntity && !listEntity.canRiderInteract())
                            {
                                pointedEntity = listEntity;
                                hitVec = movingObjPos.hitVec;
                            }
                            else
                            {
                                pointedEntity = listEntity;
                                hitVec = movingObjPos.hitVec;
                            }
                        }
                    }
                }
            }

            if (pointedEntity != null && hitVec != null)
            {
                return new MovingObjectPosition(pointedEntity, hitVec);
            }

            if (posReach != null)
            {
                MovingObjectPosition blockHitVec = player.worldObj.rayTraceBlocks(pos, posReach, true, true, true);

                if (blockHitVec != null)
                {
                    return blockHitVec;
                }
            }

            return null;
        }

        public static MovingObjectPosition rayTrace(Entity entity, int reach)
        {
            Vec3 pos = Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ);
            Vec3 entityLook = entity.getLookVec();

            Entity pointedEntity = null;
            Vec3 hitVec = null;
            Vec3 posReach = null;

            if (entityLook != null)
            {
                posReach = pos.addVector(entityLook.xCoord * reach, entityLook.yCoord * reach, entityLook.zCoord * reach);

                List<Entity> entities = entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.addCoord(entityLook.xCoord * reach, entityLook.yCoord * reach, entityLook.zCoord * reach).expand(1.0F, 1.0F, 1.0F));

                for (Entity listEntity : entities)
                {
                    if (listEntity.canBeCollidedWith())
                    {
                        float borderSize = listEntity.getCollisionBorderSize();
                        AxisAlignedBB axisalignedbb = listEntity.boundingBox.expand(borderSize, borderSize, borderSize);
                        MovingObjectPosition movingObjPos = axisalignedbb.calculateIntercept(pos, posReach);

                        if (axisalignedbb.isVecInside(pos))
                        {
                            pointedEntity = listEntity;
                            hitVec = movingObjPos == null ? pos : movingObjPos.hitVec;
                        }
                        else if (movingObjPos != null)
                        {
                            if (listEntity == entity.ridingEntity && !listEntity.canRiderInteract())
                            {
                                pointedEntity = listEntity;
                                hitVec = movingObjPos.hitVec;
                            }
                            else
                            {
                                pointedEntity = listEntity;
                                hitVec = movingObjPos.hitVec;
                            }
                        }
                    }
                }
            }

            if (pointedEntity != null && hitVec != null)
            {
                return new MovingObjectPosition(pointedEntity, hitVec);
            }

            if (posReach != null)
            {
                MovingObjectPosition blockHitVec = entity.worldObj.rayTraceBlocks(pos, posReach, true, true, true);

                if (blockHitVec != null)
                {
                    return blockHitVec;
                }
            }

            return null;
        }

        /**
         * Constructs a new Entity instance from the specified class name in the specified world.
         * 
         * @param worldObj - World to construct the Entity instance in.
         * @param name - String name of the entity class of which will be constructed.
         * @return Entity instance constructed using this method.
         */
        @SuppressWarnings("unchecked")
        public static Entity constructEntityViaClasspath(World worldObj, String name)
        {
            try
            {
                return constructEntity(worldObj, (Class<? extends Entity>) Class.forName(name));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Constructs a new Entity instance from the specified class in the specified world.
         * 
         * @param worldObj - World to construct the Entity instance in.
         * @param c - The entity class of which will be constructed.
         * @return Entity instance constructed using this method.
         */
        public static Entity constructEntity(World worldObj, Class<? extends Entity> c)
        {
            try
            {
                return (c.getConstructor(World.class)).newInstance(new Object[] { worldObj });
            }
            catch (Exception e)
            {
                AIRI.logger.bug("Failed to construct entity: " + (c != null ? c.getName() : c));
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Causes the facer entity to face the faced entity.
         * 
         * @param facer - The Entity that is going to be facing the faced entity.
         * @param faced - The Entity that is going to be faced.
         * @param maxYaw - The max rotation yaw that the facer can rotate.
         * @param maxPitch - The max rotation pitch that the facer can rotate.
         */
        public static void faceEntity(Entity facer, Entity faced, float maxYaw, float maxPitch)
        {
            double xDistance = faced.posX - facer.posX;
            double zDistance = faced.posZ - facer.posZ;
            double yDistance;

            if (faced instanceof EntityLivingBase)
            {
                EntityLivingBase entitylivingbase = (EntityLivingBase) faced;
                yDistance = entitylivingbase.posY + entitylivingbase.getEyeHeight() - (facer.posY + facer.getEyeHeight());
            }
            else
            {
                yDistance = (faced.boundingBox.minY + faced.boundingBox.maxY) / 2.0D - (facer.posY + facer.getEyeHeight());
            }

            double d3 = MathHelper.sqrt_double(xDistance * xDistance + zDistance * zDistance);
            float f2 = (float) (Math.atan2(zDistance, xDistance) * 180.0D / Math.PI) - 90.0F;
            float f3 = (float) (-(Math.atan2(yDistance, d3) * 180.0D / Math.PI));
            facer.rotationPitch = updateRotation(facer.rotationPitch, f3, maxPitch);
            facer.rotationYaw = updateRotation(facer.rotationYaw, f2, maxYaw);
        }

        /**
         * @param currentRotation - The current rotation value.
         * @param targetRotation - The target rotation value.
         * @param maxChange - The maximum rotation change allowed.
         * @return Amount of rotation that is results based on the current, target, and max rotation.
         */
        public static float updateRotation(float currentRotation, float targetRotation, float maxChange)
        {
            float newRotation = MathHelper.wrapAngleTo180_float(targetRotation - currentRotation);
            return currentRotation + (newRotation > maxChange ? maxChange : newRotation < -maxChange ? -maxChange : maxChange);
        }

        /**
         * Apply a block collision for the provided Entity instance.
         * @param entity - The entity to apply a collision for
         */
        public static void applyCollision(Entity entity)
        {
            int minX = MathHelper.floor_double(entity.boundingBox.minX + 0.001D);
            int minY = MathHelper.floor_double(entity.boundingBox.minY + 0.001D);
            int minZ = MathHelper.floor_double(entity.boundingBox.minZ + 0.001D);
            int maxX = MathHelper.floor_double(entity.boundingBox.maxX - 0.001D);
            int maxY = MathHelper.floor_double(entity.boundingBox.maxY - 0.001D);
            int maxZ = MathHelper.floor_double(entity.boundingBox.maxZ - 0.001D);

            if (entity.worldObj.checkChunksExist(minX, minY, minZ, maxX, maxY, maxZ))
            {
                for (int x = minX; x <= maxX; ++x)
                {
                    for (int y = minY; y <= maxY; ++y)
                    {
                        for (int z = minZ; z <= maxZ; ++z)
                        {
                            Block block = entity.worldObj.getBlock(x, y, z);

                            try
                            {
                                block.onEntityCollidedWithBlock(entity.worldObj, x, y, z, entity);
                            }
                            catch (Throwable throwable)
                            {
                                AIRI.logger.bug("Exception while handling entity collision with block.");
                            }
                        }
                    }
                }
            }
        }

        public static boolean isInLava(Entity entity)
        {
            return isInMaterial(entity, Material.lava);
        }

        public static boolean isInWater(Entity entity)
        {
            return isInMaterial(entity, Material.water);
        }

        public static boolean isInMaterial(Entity entity, Material material)
        {
            if (entity != null && entity.worldObj != null && entity.getBoundingBox() != null)
            {
                return entity.worldObj.isMaterialInBB(entity.getBoundingBox().expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), material);
            }

            return false;
        }

        public static class Players
        {
            /**
             * @param player - The EntityPlayer to check for XP.
             * @return the amount of XP required to reach the next level.
             */
            public static float getXPToNextLevel(EntityPlayer player)
            {
                return getXPCurrentLevelMax(player) - getXPCurrentLevel(player);
            }

            /**
             * @param player - The EntityPlayer to check for XP of the current level.
             * @return the amount of XP the player has on the current level.
             */
            public static float getXPCurrentLevel(EntityPlayer player)
            {
                return player.experience * getXPCurrentLevelMax(player);
            }

            /**
             * @param player - The EntityPlayer to check for XP.
             * @return the max amount of XP required for this level.
             */
            public static float getXPCurrentLevelMax(EntityPlayer player)
            {
                return player.xpBarCap();
            }

            /**
             * @param player - The EntityPlayer to check for XP totals.
             * @return the total amount of XP gained through all the player's levels.
             */
            public static float getXPTotal(EntityPlayer player)
            {
                return player.experienceTotal;
            }

            /**
             * @param player - The EntityPlayer to check the level of.
             * @return the player's level.
             */
            public static float getXPLevel(EntityPlayer player)
            {
                return player.experienceLevel;
            }

            /**
             * @param player - Instance of the receiving player
             * @param message - The string to be sent to the player
             */
            public static void sendToChat(EntityPlayer player, String message)
            {
                player.addChatComponentMessage(new ChatComponentText(message));
            }

            /**
             * This method sends an HTTP request to the Arisux web servers, which retrieve 
             * the UUID from Mojang's auth servers, and relays it back to this method.
             * 
             * @param username - The username of the player to retrieve a UUID from
             * @return The UUID of the specified player.
             */
            public static String getUUID(String username)
            {
                String retrieved = NetworkUtil.getURLContents(String.format(AIRI.settings().getServer() + "/login/auth-functions.php?function=uuid&user=%s", username));
                return AIRI.settings().isNetworkingEnabled() ? retrieved != null && retrieved.length() >= 32 ? retrieved : username : username;
            }

            /**
             * Returns an instance of EntityPlayer for the first player found with the specified username.
             * 
             * @param worldObj - World instance to scan for players in.
             * @param username - Username to scan players for.
             * @return EntityPlayer instance of the specified player's username.
             */
            public static EntityPlayer getPlayerForUsername(World worldObj, String username)
            {
                for (Object player : worldObj.playerEntities)
                {
                    if (((EntityPlayer) player).getCommandSenderName().equalsIgnoreCase(username))
                    {
                        return (EntityPlayer) player;
                    }
                }

                return null;
            }

            /**
             * Returns an instance of EntityPlayer for the first player found with the specified ICommandSender details
             * 
             * @param commandSender - The ICommandSender instance to get details from
             * @return EntityPlayer instance of the specified ICommandSender
             */
            public static EntityPlayer getPlayerForCommandSender(ICommandSender commandSender)
            {
                return getPlayerForUsername(commandSender.getEntityWorld(), commandSender.getCommandSenderName());
            }

            public static class Inventories
            {
                public static void dropItemsInAt(IInventory inv, World world, double xCoord, double yCoord, double zCoord)
                {
                    if (inv != null)
                    {
                        for (byte i = 0; i < inv.getSizeInventory(); i++)
                        {
                            ItemStack stack = inv.getStackInSlotOnClosing(i);

                            if (stack != null)
                            {
                                EntityItem entity = new EntityItem(world, xCoord + world.rand.nextFloat(), yCoord + world.rand.nextFloat(), zCoord + world.rand.nextFloat(), stack);
                                float velocity = 0.05F;

                                entity.motionX = (-0.5F + world.rand.nextFloat()) * velocity;
                                entity.motionY = (4F + world.rand.nextFloat()) * velocity;
                                entity.motionZ = (-0.5F + world.rand.nextFloat()) * velocity;

                                world.spawnEntityInWorld(entity);
                            }
                        }
                    }
                }

                /**
                 * Get the slot id of the specified item in the specified inventory.
                 * 
                 * @param item - The item we are getting the slot id of.
                 * @param inventory - The inventory of which we are searching in.
                 * @return
                 */
                public static int getSlotForItemIn(Item item, InventoryPlayer inventory)
                {
                    for (int id = 0; id < inventory.getSizeInventory(); ++id)
                    {
                        if (inventory.getStackInSlot(id) != null && inventory.getStackInSlot(id).getItem() == item)
                        {
                            return id;
                        }
                    }

                    return -1;
                }

                /**
                 * @param items - List of Items to choose a random Item from.
                 * @return an ItemStack instance instantaniated from a random Item chosen from the provided Item Array.
                 */
                public static ItemStack randomItemStackFromArray(Item[] items)
                {
                    return randomItemStackFromArray(items, new Random());
                }

                /**
                 * @param items - List of Items to choose a random Item from.
                 * @param rand - Random instance to use
                 * @return an ItemStack instance instantaniated from a random Item chosen from the provided Item Array.
                 */
                public static ItemStack randomItemStackFromArray(Item[] items, Random rand)
                {
                    return newStack(items[rand.nextInt(items.length)]);
                }

                /** 
                 * @param obj - Item or Block instance 
                 * @return A new ItemStack instance of the specified Object
                 **/
                public static ItemStack newStack(Object obj)
                {
                    return newStack(obj, 1);
                }

                /** 
                 * @param obj - Item or Block instance 
                 * @param amount - Amount of Items in this ItemStack
                 * @return A new ItemStack instance of the specified Object
                 **/
                public static ItemStack newStack(Object obj, int amount)
                {
                    return obj instanceof Block ? new ItemStack((Block) obj, amount) : obj instanceof Item ? new ItemStack((Item) obj, amount) : null;
                }

                /** 
                 * @param stack - Instance of ItemStack 
                 * @return the Item instance of the specified ItemStack
                 **/
                public static Item getItem(ItemStack stack)
                {
                    return stack.getItem();
                }

                /**
                 * @param stack - Instance of ItemStack 
                 * @return the Block instance of the specified ItemStack
                 **/
                public static Block getBlock(ItemStack stack)
                {
                    return Block.getBlockFromItem(stack.getItem());
                }

                /**
                 * Consumes a single item of the specified type from the specified player's inventory.
                 * Only comsumes an item if the player is in survival mode.
                 * 
                 * @param player - The player to consume an item from.
                 * @param item - The item to consume.
                 */
                public static boolean consumeItem(EntityPlayer player, Item item)
                {
                    return consumeItem(player, item, false);
                }

                /**
                 * Consumes a single item of the specified type from the specified player's inventory.
                 * Only comsumes an item if the player is in survival mode, unless forced.
                 * 
                 * @param player - The player to consume an item from.
                 * @param item - The item to consume.
                 * @param force - Forces an item to be consumed, regardless of gamemode.
                 */
                public static boolean consumeItem(EntityPlayer player, Item item, boolean force)
                {
                    if (!player.capabilities.isCreativeMode || force)
                    {
                        return player.inventory.consumeInventoryItem(item);
                    }

                    return true;
                }

                /**
                 * Returns the ItemStack residing in the specified player's helm slot.
                 * 
                 * @param player - The player to return an ItemStack from
                 * @return An ItemStack residing in the helm slot.
                 */
                public static ItemStack getHelmSlotItemStack(EntityPlayer player)
                {
                    return player.inventory.armorItemInSlot(3);
                }

                /**
                 * Returns the ItemStack residing in the specified player's chestplate slot.
                 * 
                 * @param player - The player to return an ItemStack from
                 * @return An ItemStack residing in the chestplate slot.
                 */
                public static ItemStack getChestSlotItemStack(EntityPlayer player)
                {
                    return player.inventory.armorItemInSlot(2);
                }

                /**
                 * Returns the ItemStack residing in the specified player's leggings slot.
                 * 
                 * @param player - The player to return an ItemStack from
                 * @return An ItemStack residing in the leggings slot.
                 */
                public static ItemStack getLegsSlotItemStack(EntityPlayer player)
                {
                    return player.inventory.armorItemInSlot(1);
                }

                /**
                 * Returns the ItemStack residing in the specified player's boots slot.
                 * 
                 * @param player - The player to return an ItemStack from
                 * @return An ItemStack residing in the boots slot.
                 */
                public static ItemStack getBootSlotItemStack(EntityPlayer player)
                {
                    return player.inventory.armorItemInSlot(0);
                }

                public static int getAmountOfItemPlayerHas(Item item, EntityPlayer player)
                {
                    int amount = 0;

                    for (Object obj : player.inventory.mainInventory)
                    {
                        ItemStack stack = (ItemStack) obj;

                        if (stack != null && stack.getItem() == item)
                        {
                            amount += stack.stackSize;
                        }
                    }

                    return amount;
                }
            }

            public static List<EntityPlayer> getPlayersInWorld(World world)
            {
                return world.playerEntities;
            }
        }
    }
}
