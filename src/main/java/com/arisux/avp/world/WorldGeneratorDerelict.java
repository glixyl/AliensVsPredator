package com.arisux.avp.world;

import java.util.Random;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldGeneratorDerelict implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		WorldServer worldServer = MinecraftServer.getServer().worldServerForDimension(0);

		if (chunkX == 0 && chunkZ == 0)
		{
			CoordData data = new CoordData(0, 120, 0);
			StructureDerelict derelict = new StructureDerelict(worldServer, data);
			
			AIRI.instance().events.getStructuresInQueue().add(derelict);
		}
		
//		for (final DerelictLocation location : AliensVsPredator.worldgen().getSaveHandler().getWorldInfo().getDerelictLocations())
//		{
//			if (!location.isGenerated() && location.getCoord().posX / 16 == chunkX && location.getCoord().posZ / 16 == chunkZ)
//			{
//				AIRI.logger.info("Generating a Derelict in chunk at %s, %s", chunkX, chunkZ);
//				location.generate(world);
//			}
//		}
	}
}
