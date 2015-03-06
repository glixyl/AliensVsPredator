package com.arisux.avp.dimension.varda;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import com.arisux.avp.dimension.BiomeLVBase;
import com.arisux.avp.dimension.acheron.worldgen.*;
import com.arisux.avp.dimension.varda.worldgen.VardaGenSpike1;
import com.arisux.avp.dimension.varda.worldgen.VardaGenSpike2;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorVarda implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		this.generateSurface(world, random, chunkX * 16, chunkZ * 16);
	}

	private void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		if (world.provider.getBiomeGenForCoords(chunkX, chunkZ) == BiomeLVBase.varda)
		{
			for (int i = 0; i < 26; i++)
			{
				int randPosX1 = chunkX + random.nextInt(16);
				int randPosY1 = random.nextInt(128);
				int randPosZ1 = chunkZ + random.nextInt(16);
				new VardaGenSpike1().generate(world, random, randPosX1, randPosY1, randPosZ1);
			}

			for (int i = 0; i < 27; i++)
			{
				int randPosX2 = chunkX + random.nextInt(16);
				int randPosY2 = random.nextInt(128);
				int randPosZ2 = chunkZ + random.nextInt(16);
				new VardaGenSpike2().generate(world, random, randPosX2, randPosY2, randPosZ2);
			}
		}
		
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
