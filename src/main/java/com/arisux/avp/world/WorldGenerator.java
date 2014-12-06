package com.arisux.avp.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random seed, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		this.generateSurface(world, seed, chunkX * 16, chunkZ * 16);
	}

	private void generateSurface(World world, Random seed, int chunkX, int chunkZ)
	{
		CoordData chunkCoords = new CoordData(chunkX, 0, chunkZ);
		
		WorldUtil.generateBlockInChunk(world, AliensVsPredator.instance().blocks.oreCopper, seed, 3, 4, 16, 48, chunkCoords);
		WorldUtil.generateBlockInChunk(world, AliensVsPredator.instance().blocks.oreSilicon, seed, 2, 3, 0, 32, chunkCoords);
		WorldUtil.generateBlockInChunk(world, AliensVsPredator.instance().blocks.oreLithium, seed, 3, 1, 0, 16, chunkCoords, new BiomeGenBase[]{ BiomeGenBase.ocean });
		WorldUtil.generateBlockInChunk(world, AliensVsPredator.instance().blocks.oreBauxite, seed, 4, 8, 16, 64, chunkCoords);
	}
}
