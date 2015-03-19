package com.arisux.avp.dimension.varda;

import net.minecraft.world.biome.BiomeGenBase;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.dimension.BiomeLVBase;

public class BiomeVarda extends BiomeLVBase
{
	public BiomeVarda(int biomeId)
	{
		super(biomeId);
		this.topBlock = AliensVsPredator.blocks().terrainUniDirt;
		this.fillerBlock = AliensVsPredator.blocks().terrainUniStone;
		this.setBiomeName(AliensVsPredator.properties().DIMENSION_NAME_VARDA);
		this.setHeight(new BiomeGenBase.Height(1.0F, 8.0F));
		this.setTemperatureRainfall(0.7F, 0.1F);
		this.setDisableRain();
		this.theBiomeDecorator = new BiomeDecoratorVarda(this);
	}

	@Override
	public BiomeVarda setColor(int color)
	{
		this.func_150557_a(color, false);
		return this;
	}
}