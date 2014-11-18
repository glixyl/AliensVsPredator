package com.arisux.avp.dimension.lv223;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;

import com.arisux.avp.AliensVsPredator;

public class LV223BiomeDecorator extends BiomeDecorator
{
	protected World a;
	public int lakesPerChunk;
	public LV223BiomeBase biomeGenBase;
	public WorldGenLakes lakeGeneration;
	public WorldGenLiquids caveWaterGen;
	public WorldGenerator MDirtGen;
	public WorldGenerator StalagmiteGen;
	public WorldGenerator LandSpikeGen;
	public int stalagtitesPerChunk;

	public LV223BiomeDecorator(LV223BiomeBase biomeGenBase)
	{
		super();
		this.biomeGenBase = biomeGenBase;
		this.lakeGeneration = new WorldGenLakes(Blocks.water);
		this.caveWaterGen = new WorldGenLiquids(Blocks.flowing_water);
		this.MDirtGen = new LV223GenMinable(AliensVsPredator.instance().blocks.terrainUniDirt, 32);
		this.StalagmiteGen = new WorldGenFlowers(AliensVsPredator.instance().blocks.terrainStalagmite);
		this.LandSpikeGen = new LV223GenLandSpike(true);
		this.lakesPerChunk = 0;
		this.treesPerChunk = 0;
		this.stalagtitesPerChunk = 10;
	}

	@Override
	public void decorateChunk(World var1, Random var2, BiomeGenBase biomeGenBase, int var3, int var4)
	{
		if (this.a != null)
		{
			throw new RuntimeException("Already decorating Prometheus!");
		}

		this.a = var1;
		this.randomGenerator = var2;
		this.chunk_X = var3;
		this.chunk_Z = var4;
		decorate();
		this.a = null;
		this.randomGenerator = null;
	}

	// previously overridden
	protected void decorate()
	{
		generateOres();
		int var1 = this.stalagtitesPerChunk;

		if (this.randomGenerator.nextInt(2) == 0)
		{
			for (int var2 = 0; var2 < this.randomGenerator.nextInt(20); var2++)
			{
				var1++;
			}

		}

		for (int var3 = 0; var3 < var1; var3++)
		{
			int var4 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			int var2 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			WorldGenerator var5 = LV223BiomeBase.prometheusBiome.getRandomWorldGenForTrees(this.randomGenerator);
			var5.setScale(1.0D, 1.0D, 1.0D);
			var5.generate(this.a, this.randomGenerator, var4, this.a.getHeightValue(var4, var2), var2);
		}

		if (this.generateLakes)
		{
			for (int var3 = 0; var3 < 150; var3++)
			{
				int var4 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				int var2 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(120) + 8);
				int var6 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenLiquids(Blocks.flowing_water).generate(this.a, this.randomGenerator, var4, var2, var6);
			}

			for (int var3 = 0; var3 < 60; var3++)
			{
				int var4 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				int var2 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(112) + 8) + 8);
				int var6 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenLiquids(Blocks.flowing_lava).generate(this.a, this.randomGenerator, var4, var2, var6);
			}
		}

		for (int var3 = 0; var3 < this.stalagtitesPerChunk; var3++)
		{
			int var4 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			int var2 = this.randomGenerator.nextInt(128);
			int var6 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.StalagmiteGen.generate(this.a, this.randomGenerator, var4, var2, var6);
		}

		for (int var3 = 0; var3 < 10; var3++)
		{
			int var4 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			int var2 = this.randomGenerator.nextInt(128);
			int var6 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.LandSpikeGen.generate(this.a, this.randomGenerator, var4, var2, var6);
		}
	}

	protected void genStandardOre(int var1, WorldGenerator var2, int var3, int var4)
	{
		for (int var5 = 0; var5 < var1; var5++)
		{
			int var6 = this.chunk_X + this.randomGenerator.nextInt(16);
			int var7 = this.randomGenerator.nextInt(var4 - var3) + var3;
			int var8 = this.chunk_Z + this.randomGenerator.nextInt(16);
			var2.generate(this.a, this.randomGenerator, var6, var7, var8);
		}
	}

	@Override
	protected void generateOres()
	{
		genStandardOre(20, this.MDirtGen, 0, 128);
	}
}