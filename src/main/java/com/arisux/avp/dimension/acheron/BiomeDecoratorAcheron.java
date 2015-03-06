package com.arisux.avp.dimension.acheron;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.dimension.BiomeLVBase;

public class BiomeDecoratorAcheron extends BiomeDecorator
{
	protected World world;
	public BiomeLVBase biome;
	public WorldGenerator stalagmiteGen;

	public BiomeDecoratorAcheron(BiomeLVBase biome)
	{
		super();
		this.biome = biome;
		this.stalagmiteGen = new WorldGenFlowers(AliensVsPredator.instance().blocks.terrainStalagmite);
	}

	@Override
	public void decorateChunk(World world, Random seed, BiomeGenBase biome, int chunkX, int chunkZ)
	{
		if (this.world != null)
		{
			return;
		}

		this.world = world;
		this.randomGenerator = seed;
		this.chunk_X = chunkX;
		this.chunk_Z = chunkZ;
		this.genDecorations(biome);
		this.world = null;
		this.randomGenerator = null;
	}

	@Override
	protected void genDecorations(BiomeGenBase biome)
	{
		this.generateOres();

		WorldUtil.generateWorldGenInChunk(world, this.stalagmiteGen, this.randomGenerator, 10, new CoordData(chunk_X, 0, chunk_Z));
	}

	@Override
	protected void generateOres()
	{
		WorldUtil.generateBlockInChunk(world, AliensVsPredator.instance().blocks.terrainUniDirt, this.randomGenerator, 20, 32, 0, 128, new CoordData(chunk_X, 0, chunk_Z));
	}
}