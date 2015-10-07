package com.arisux.avp.world;

import java.util.Random;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random seed, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		this.generateSurface(world, seed, chunkX * 16, chunkZ * 16);
	}

	private void generateSurface(World world, Random rand, int chunkX, int chunkZ)
	{
		CoordData chunkCoords = new CoordData(chunkX, 0, chunkZ);
		
		WorldUtil.generateBlockInChunk(world, AliensVsPredator.blocks().oreCopper, rand, 70, 6, 16, 64, chunkCoords, new BiomeGenBase[] { BiomeGenBase.desert, BiomeGenBase.desertHills });
		WorldUtil.generateBlockInChunk(world, AliensVsPredator.blocks().oreSilicon, rand, 30, 3, 0, 32, chunkCoords);
		WorldUtil.generateBlockInChunk(world, AliensVsPredator.blocks().oreLithium, rand, 50, 1, 0, 128, chunkCoords, new BiomeGenBase[] { BiomeGenBase.ocean, BiomeGenBase.beach, BiomeGenBase.deepOcean });
		WorldUtil.generateBlockInChunk(world, AliensVsPredator.blocks().oreBauxite, rand, 50, 8, 16, 128, chunkCoords);
	}
}
