package org.avp.dimension.acheron;

import java.util.Random;

import org.avp.dimension.BiomeLVBase;
import org.avp.dimension.acheron.worldgen.TerrainFormation;
import org.avp.dimension.acheron.worldgen.TerrainFormation1;
import org.avp.dimension.acheron.worldgen.TerrainFormation2;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldGeneratorAcheron implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        this.generateSurface(world, random, chunkX * 16, chunkZ * 16);
    }

    private void generateSurface(World world, Random random, int chunkX, int chunkZ)
    {
        if (world.provider.getBiomeGenForCoords(chunkX, chunkZ) == BiomeLVBase.acheron)
        {
            for (int i = 0; i < 18; i++)
            {
                int randPosX1 = chunkX + random.nextInt(16);
                int randPosY1 = random.nextInt(128);
                int randPosZ1 = chunkZ + random.nextInt(16);
                new TerrainFormation().generate(world, random, randPosX1, randPosY1, randPosZ1);
            }

            for (int i = 0; i < 18; i++)
            {
                int randPosX1 = chunkX + random.nextInt(16);
                int randPosY1 = random.nextInt(128);
                int randPosZ1 = chunkZ + random.nextInt(16);
                new TerrainFormation1().generate(world, random, randPosX1, randPosY1, randPosZ1);
            }

            for (int i = 0; i < 18; i++)
            {
                int randPosX1 = chunkX + random.nextInt(16);
                int randPosY1 = random.nextInt(128);
                int randPosZ1 = chunkZ + random.nextInt(16);
                new TerrainFormation2().generate(world, random, randPosX1, randPosY1, randPosZ1);
            }
        }
    }
}
