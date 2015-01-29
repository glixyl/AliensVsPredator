package com.arisux.avp.dimension.varda;

import net.minecraft.world.biome.BiomeGenBase;

import com.arisux.avp.AliensVsPredator;

public class BiomeVarda extends BiomeVardaBase
{
	public BiomeVarda(int biomeId)
	{
		super(biomeId);
		this.biomeName = AliensVsPredator.properties().DIMENSION_NAME_VARDA;
		this.topBlock = AliensVsPredator.instance().blocks.terrainUniDirt;
		this.fillerBlock = AliensVsPredator.instance().blocks.terrainUniStone;
		this.setHeight(new BiomeGenBase.Height(1.0F, 8.0F));
		this.setTemperatureRainfall(0.7F, 0.1F);
		this.setDisableRain();
	}

	@Override
	public BiomeVarda setColor(int color)
	{
		this.func_150557_a(color, false);
		return this;
	}
}