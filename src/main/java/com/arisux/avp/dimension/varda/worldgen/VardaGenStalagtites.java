package com.arisux.avp.dimension.varda.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class VardaGenStalagtites extends WorldGenerator
{
    private Block block;

    public VardaGenStalagtites(Block block)
    {
        this.block = block;
    }

    @Override
    public boolean generate(World world, Random rand, int posX, int posY, int posZ)
    {
        for (int x = 0; x < 64; x++)
        {
            int blockX = posX + rand.nextInt(8) - rand.nextInt(8);
            int blockY = posY + rand.nextInt(4) - rand.nextInt(4);
            int blockZ = posZ + rand.nextInt(8) - rand.nextInt(8);

            if (world.isAirBlock(blockX, blockY, blockZ) && block.canBlockStay(world, blockX, blockY, blockZ))
            {
                world.setBlock(blockX, blockY, blockZ, this.block);
            }
        }

        return true;
    }
}
