package org.avp.dimension.varda;

import java.util.Random;

import org.avp.AliensVsPredator;
import org.avp.dimension.BiomeLVBase;
import org.avp.dimension.varda.worldgen.VardaGenSpike;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorVarda extends BiomeDecorator
{
    protected World world;
    public BiomeLVBase biome;
    public WorldGenerator stalagmiteGen;
    public WorldGenerator landSpikeGen;
    public WorldGenLakes lakeGeneration;
    public WorldGenLiquids caveWaterGen;

    public BiomeDecoratorVarda(BiomeLVBase biome)
    {
        super();
        this.biome = biome;
        this.stalagmiteGen = new WorldGenFlowers(AliensVsPredator.blocks().terrainStalagmite);
        this.landSpikeGen = new VardaGenSpike(true);
        this.lakeGeneration = new WorldGenLakes(Blocks.water);
        this.caveWaterGen = new WorldGenLiquids(Blocks.flowing_water);
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
        WorldUtil.generateWorldGenInChunk(world, this.landSpikeGen, this.randomGenerator, 10, new CoordData(chunk_X, 0, chunk_Z));
    }

    @Override
    protected void generateOres()
    {
        WorldUtil.generateBlockInChunk(world, AliensVsPredator.blocks().terrainUniDirt, this.randomGenerator, 20, 32, 0, 128, new CoordData(chunk_X, 0, chunk_Z));
    }
}
