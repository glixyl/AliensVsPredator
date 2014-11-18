package com.arisux.avp.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.BiomeGenSwamp;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		this.generateSurface(world, random, chunkX * 16, chunkZ * 16);
	}

	private void generateSurface(World world, Random random, int blockX, int blockZ)
	{
		for (int i = 0; i < 4; ++i)
		{
			random.nextInt(16);
			random.nextInt(128);
			random.nextInt(16);
		}

		for (int i = 0; i < 7; ++i)
		{
			int randPosX = blockX + random.nextInt(16);
			int randPosY = random.nextInt(128);
			int randPosZ = blockZ + random.nextInt(16);
			(new WorldGenMinable(AliensVsPredator.instance().blocks.oreCopper, 4)).generate(world, random, randPosX, randPosY, randPosZ);
		}

		for (int i = 0; i < 9; ++i)
		{
			int randPosX = blockX + random.nextInt(16);
			int randPosY = random.nextInt(128);
			int randPosZ = blockZ + random.nextInt(16);

			if (world.provider.getBiomeGenForCoords(randPosX, randPosZ) instanceof BiomeGenSwamp)
			{
				(new WorldGenMinable(AliensVsPredator.instance().blocks.oreSilicon, 4)).generate(world, random, randPosX, randPosY, randPosZ);
			}
		}

		for (int i = 0; i < 5; ++i)
		{
			int randPosX = blockX + random.nextInt(16);
			int randPosY = random.nextInt(128);
			int randPosZ = blockZ + random.nextInt(16);
			if (world.provider.getBiomeGenForCoords(randPosX, randPosZ) instanceof BiomeGenOcean)
			{
				(new WorldGenMinable(AliensVsPredator.instance().blocks.oreLithium, 5)).generate(world, random, randPosX, randPosY, randPosZ);
			}
		}

		for (int i = 0; i < 5; ++i)
		{
			int randPosX = blockX + random.nextInt(16);
			int randPosY = random.nextInt(128);
			int randPosZ = blockZ + random.nextInt(16);
			(new WorldGenMinable(AliensVsPredator.instance().blocks.terrainHiveResin, 4)).generate(world, random, randPosX, randPosY, randPosZ);
		}

		for (int i = 0; i < 10; ++i)
		{
			int randPosX = blockX + random.nextInt(16);
			int randPosY = random.nextInt(128);
			int randPosZ = blockZ + random.nextInt(16);
			(new WorldGenMinable(AliensVsPredator.instance().blocks.oreBauxite, 9)).generate(world, random, randPosX, randPosY, randPosZ);
		}
	}
}
