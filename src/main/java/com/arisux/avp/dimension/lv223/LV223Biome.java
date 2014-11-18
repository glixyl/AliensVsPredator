package com.arisux.avp.dimension.lv223;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.arisux.avp.AliensVsPredator;

public class LV223Biome extends LV223BiomeBase
{
	public Height height = new BiomeGenBase.Height(1.0F, 6.0F);

	public LV223Biome(int var1)
	{
		super(var1);
		this.topBlock = AliensVsPredator.instance().blocks.terrainUniDirt;
		this.fillerBlock = AliensVsPredator.instance().blocks.terrainUniStone;
		this.setHeight(height);
		this.temperature = 4.0F;
		this.rainfall = 0.1F;
		this.biomeName = AliensVsPredator.properties().DIMENSION_NAME_LV223;
		((LV223BiomeDecorator) this.theBiomeDecorator).stalagtitesPerChunk = 10;
	}

	public WorldGenerator getRandomWorldGenForTrees(Random var1)
	{
		return var1.nextInt(5) == 0 ? this.worldGeneratorBigTree : var1.nextInt(2) == 0 ? this.worldGeneratorTrees : this.worldGeneratorTrees;
	}

	@Override
	public LV223Biome setColor(int par1)
	{
		this.func_150557_a(par1, false);
		return this;
	}

	public Height getHeight()
	{
		return this.height;
	}
}