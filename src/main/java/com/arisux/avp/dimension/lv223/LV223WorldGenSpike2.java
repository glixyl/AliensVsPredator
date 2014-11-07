package com.arisux.avp.dimension.lv223;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.arisux.avp.AliensVsPredator;

public class LV223WorldGenSpike2 extends WorldGenerator
{
	protected Block[] GetValidSpawnBlocks()
	{
		return new Block[] { AliensVsPredator.INSTANCE.blocks.terrainUniDirt };
	}

	public boolean LocationIsValidSpawn(World world, int i, int j, int k)
	{
		int distanceToAir = 0;
		Block checkID = world.getBlock(i, j, k);

		while (checkID != Blocks.air)
		{
			distanceToAir++;
			checkID = world.getBlock(i, j + distanceToAir, k);
		}

		if (distanceToAir > 3)
		{
			return false;
		}
		j += distanceToAir - 1;

		Block blockID = world.getBlock(i, j, k);
		Block blockIDAbove = world.getBlock(i, j + 1, k);
		Block blockIDBelow = world.getBlock(i, j - 1, k);

		for (Block x : GetValidSpawnBlocks())
		{
			if (blockIDAbove != Blocks.air)
			{
				return false;
			}
			if (blockID == x)
			{
				return true;
			}
			if ((blockID == Blocks.snow) && (blockIDBelow == x))
			{
				return true;
			}
		}
		return false;
	}

	public boolean generate(World world, Random rand, int i, int j, int k)
	{
		if ((!LocationIsValidSpawn(world, i, j, k)) || (!LocationIsValidSpawn(world, i + 2, j, k)) || (!LocationIsValidSpawn(world, i + 2, j, k + 2)) || (!LocationIsValidSpawn(world, i, j, k + 2)))
		{
			return false;
		}

		world.setBlock(i + 0, j + 0, k + 1, AliensVsPredator.INSTANCE.blocks.terrainUniDirt);
		world.setBlock(i + 0, j + 1, k + 1, AliensVsPredator.INSTANCE.blocks.terrainUniDirt);
		world.setBlock(i + 0, j + 2, k + 1, AliensVsPredator.INSTANCE.blocks.terrainUniDirt);
		world.setBlock(i + 1, j + 0, k + 0, AliensVsPredator.INSTANCE.blocks.terrainUniDirt);
		world.setBlock(i + 1, j + 0, k + 1, AliensVsPredator.INSTANCE.blocks.terrainUniDirt);
		world.setBlock(i + 1, j + 0, k + 2, AliensVsPredator.INSTANCE.blocks.terrainUniDirt);
		world.setBlock(i + 1, j + 1, k + 0, AliensVsPredator.INSTANCE.blocks.terrainUniDirt);
		world.setBlock(i + 1, j + 1, k + 1, AliensVsPredator.INSTANCE.blocks.terrainUniDirt);
		world.setBlock(i + 1, j + 2, k + 1, AliensVsPredator.INSTANCE.blocks.terrainUniDirt);
		world.setBlock(i + 1, j + 3, k + 1, AliensVsPredator.INSTANCE.blocks.terrainUniDirt);
		world.setBlock(i + 1, j + 4, k + 1, AliensVsPredator.INSTANCE.blocks.terrainUniDirt);
		world.setBlock(i + 1, j + 5, k + 1, AliensVsPredator.INSTANCE.blocks.terrainUniDirt);
		world.setBlock(i + 2, j + 0, k + 1, AliensVsPredator.INSTANCE.blocks.terrainUniDirt);
		world.setBlock(i + 2, j + 1, k + 1, AliensVsPredator.INSTANCE.blocks.terrainUniDirt);

		return true;
	}
}