package com.arisux.avp.dimension;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

import com.arisux.avp.dimension.acheron.BiomeAcheron;
import com.arisux.avp.dimension.varda.BiomeVarda;

public abstract class BiomeLVBase extends BiomeGenBase
{
	public static final BiomeLVBase varda = new BiomeVarda(42).setColor(0x353825);
	public static final BiomeLVBase acheron = new BiomeAcheron(43).setColor(0x353825);

	public BiomeLVBase(int biomeId)
	{
		super(biomeId);
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
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