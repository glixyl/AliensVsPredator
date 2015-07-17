package com.arisux.avp.dimension.varda.worldgen;

import java.util.Random;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.dimension.varda.WorldGeneratorVarda;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class VardaGenSpike1 extends WorldGeneratorVarda
{
	protected Block[] GetValidSpawnBlocks()
	{
		return new Block[] { AliensVsPredator.blocks().terrainUniDirt };
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
				return true;
			if ((blockID == AliensVsPredator.blocks().terrainStalagmite) && (blockIDBelow == x))
			{
				return true;
			}
		}
		return false;
	}

	public boolean generate(World world, Random rand, int posX, int posY, int posZ)
	{
		if ((!LocationIsValidSpawn(world, posX, posY, posZ)) || (!LocationIsValidSpawn(world, posX + 2, posY, posZ)) || (!LocationIsValidSpawn(world, posX + 2, posY, posZ + 2)) || (!LocationIsValidSpawn(world, posX, posY, posZ + 2)))
		{
			return false;
		}
		
		Block blockDirt = AliensVsPredator.blocks().terrainUniDirt;

		world.setBlock(posX + 0, posY + 0, posZ + 1, blockDirt);
		world.setBlock(posX + 0, posY + 1, posZ + 1, blockDirt);
		world.setBlock(posX + 0, posY + 2, posZ + 1, blockDirt);
		world.setBlock(posX + 0, posY + 3, posZ + 1, blockDirt);
		world.setBlock(posX + 0, posY + 4, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 0, posZ + 0, blockDirt);
		world.setBlock(posX + 1, posY + 0, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 0, posZ + 2, blockDirt);
		world.setBlock(posX + 1, posY + 1, posZ + 0, blockDirt);
		world.setBlock(posX + 1, posY + 1, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 1, posZ + 2, blockDirt);
		world.setBlock(posX + 1, posY + 2, posZ + 0, blockDirt);
		world.setBlock(posX + 1, posY + 2, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 2, posZ + 2, blockDirt);
		world.setBlock(posX + 1, posY + 3, posZ + 0, blockDirt);
		world.setBlock(posX + 1, posY + 3, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 3, posZ + 2, blockDirt);
		world.setBlock(posX + 1, posY + 4, posZ + 0, blockDirt);
		world.setBlock(posX + 1, posY + 4, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 5, posZ + 0, blockDirt);
		world.setBlock(posX + 1, posY + 5, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 6, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 7, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 8, posZ + 1, blockDirt);
		world.setBlock(posX + 2, posY + 0, posZ + 1, blockDirt);
		world.setBlock(posX + 2, posY + 1, posZ + 1, blockDirt);
		world.setBlock(posX + 2, posY + 2, posZ + 1, blockDirt);

		return true;
	}
}