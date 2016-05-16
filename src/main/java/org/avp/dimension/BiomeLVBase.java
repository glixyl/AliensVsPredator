package org.avp.dimension;

import org.avp.AliensVsPredator;
import org.avp.dimension.acheron.BiomeAcheron;
import org.avp.dimension.varda.BiomeVarda;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public abstract class BiomeLVBase extends BiomeGenBase
{
    public static BiomeLVBase varda = new BiomeVarda(AliensVsPredator.settings().biomeIdVarda()).setColor(0x353825);
    public static BiomeLVBase acheron = new BiomeAcheron(AliensVsPredator.settings().biomeIdAcheron()).setColor(0x353825);

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
