package com.arisux.avp.dimension.varda;

import java.util.Random;

import com.arisux.avp.dimension.varda.worldgen.VardaGenSpike1;
import com.arisux.avp.dimension.varda.worldgen.VardaGenSpike2;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class GeneratorVarda implements IWorldGenerator
{
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		generateSurface(world, random, chunkX * 16, chunkZ * 16);
	}

	private void generateSurface(World var1, Random var2, int var3, int var4)
	{
		for (int i = 0; i < 26; i++)
		{
			int randPosX1 = var3 + var2.nextInt(16);
			int randPosY1 = var2.nextInt(128);
			int randPosZ1 = var4 + var2.nextInt(16);
			new VardaGenSpike1().generate(var1, var2, randPosX1, randPosY1, randPosZ1);
		}

		for (int i = 0; i < 27; i++)
		{
			int randPosX2 = var3 + var2.nextInt(16);
			int randPosY2 = var2.nextInt(128);
			int randPosZ2 = var4 + var2.nextInt(16);
			new VardaGenSpike2().generate(var1, var2, randPosX2, randPosY2, randPosZ2);
		}
	}
}