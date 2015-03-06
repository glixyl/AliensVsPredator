package com.arisux.avp.dimension.acheron;

import net.minecraft.world.biome.BiomeGenBase;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.dimension.BiomeLVBase;

public class BiomeAcheron extends BiomeLVBase
{
	public BiomeAcheron(int biomeId)
	{
		super(biomeId);
		this.topBlock = AliensVsPredator.instance().blocks.terrainUniDirt;
		this.fillerBlock = AliensVsPredator.instance().blocks.terrainUniStone;
		this.setBiomeName(AliensVsPredator.properties().DIMENSION_NAME_ACHERON);
		this.setHeight(new BiomeGenBase.Height(0.1F, 0.4F));
		this.setTemperatureRainfall(0.7F, 0.1F);
		this.setDisableRain();
		this.theBiomeDecorator = new BiomeDecoratorAcheron(this);
	}

	@Override
	public BiomeAcheron setColor(int color)
	{
		this.func_150557_a(color, false);
		return this;
	}
}