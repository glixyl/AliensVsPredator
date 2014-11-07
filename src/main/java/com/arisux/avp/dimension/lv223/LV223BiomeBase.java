package com.arisux.avp.dimension.lv223;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public abstract class LV223BiomeBase extends BiomeGenBase
{
	public static final LV223Biome prometheusBiome = new LV223Biome(42).setColor(353825);

	public LV223BiomeBase(int var1)
	{
		super(var1);
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.theBiomeDecorator = new LV223BiomeDecorator(this);
	}

	@Override
	public float getSpawningChance()
	{
		return 0.12F;
	}

	@Override
	public BiomeDecorator createBiomeDecorator()
	{
		return this.theBiomeDecorator;
	}
}