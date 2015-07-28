package com.arisux.avp.world;

import java.util.ArrayList;
import java.util.Random;

import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.event.world.WorldEvent;

public class WorldGeneratorDerelict implements IWorldGenerator
{
	private ArrayList<CoordData> derelictCoords;
	private Random rand;

	public WorldGeneratorDerelict()
	{
		this.rand = new Random();
	}

	public void loadLocations(WorldEvent.Load event)
	{
		if (this.derelictCoords == null)
		{
			ArrayList<CoordData> derelictCoords = new ArrayList<CoordData>();
			derelictCoords.add(new CoordData(newCoord(), newCoord(), newCoord()));
			derelictCoords.add(new CoordData(newCoord(), newCoord(), newCoord()));
			derelictCoords.add(new CoordData(newCoord(), newCoord(), newCoord()));
			this.derelictCoords = derelictCoords;
		}
	}

	public void saveLocations(WorldEvent.Save event)
	{
		;
	}

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

	public ArrayList<CoordData> getDerelictCoords()
	{
		return this.derelictCoords;
	}

	public int getRangeFromSpawn()
	{
		return 1000;
	}

	public int newCoord()
	{
		return 0 - this.rand.nextInt(this.getRangeFromSpawn()) + this.rand.nextInt(this.getRangeFromSpawn() * 2);
	}
}
