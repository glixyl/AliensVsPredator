package com.arisux.avp.world;

import java.util.Random;

import com.arisux.airi.AIRI;
import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldGeneratorDerelict implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		for (final DerelictLocation location : AliensVsPredator.worldgen().getSaveHandler().getWorldInfo().getDerelictLocations())
		{
			if (!location.isGenerated() && location.getCoord().posX / 16 == chunkX && location.getCoord().posZ / 16 == chunkZ)
			{
				AIRI.logger.info("Generating a Derelict in chunk at %s, %s", chunkX, chunkZ);
				location.generate(world);
			}
		}
	}
}
