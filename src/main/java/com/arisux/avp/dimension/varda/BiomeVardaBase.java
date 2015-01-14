package com.arisux.avp.dimension.varda;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public abstract class BiomeVardaBase extends BiomeGenBase
{
	public static final BiomeVarda varda = new BiomeVarda(42).setColor(0x353825);

	public BiomeVardaBase(int biomeId)
	{
		super(biomeId);
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.theBiomeDecorator = new BiomeDecoratorVarda(this);
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