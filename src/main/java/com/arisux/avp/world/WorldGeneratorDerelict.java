package com.arisux.avp.world;

import java.util.Random;

import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldGeneratorDerelict implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		CoordData chunkCoords = new CoordData(chunkX, 0, chunkZ);

		for (int x = 0; x <= 1; x++)
		{
			CoordData genCoords = new CoordData(chunkCoords.posX + random.nextInt(16), 0 + random.nextInt(128), chunkCoords.posZ + random.nextInt(16), world);

			if (genCoords.getBlock() == Blocks.grass)
			{
				AliensVsPredator.schematics().derelict.generate(world, genCoords);
			}
		}
	}
}
