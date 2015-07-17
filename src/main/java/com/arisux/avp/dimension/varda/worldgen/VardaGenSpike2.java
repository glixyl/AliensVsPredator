package com.arisux.avp.dimension.varda.worldgen;

import java.util.Random;

import com.arisux.avp.AliensVsPredator;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class VardaGenSpike2 extends WorldGenerator
{
	protected Block[] getSpawnBlocks()
	{
		return new Block[] { AliensVsPredator.blocks().terrainUniDirt };
	}

	public boolean isLocationValid(World world, int posX, int posY, int posZ)
	{
		int distanceToAir = 0;
		Block checkID = world.getBlock(posX, posY, posZ);

		while (checkID != Blocks.air)
		{
			distanceToAir++;
			checkID = world.getBlock(posX, posY + distanceToAir, posZ);
		}

		if (distanceToAir > 3)
		{
			return false;
		}
		posY += distanceToAir - 1;

		Block blockID = world.getBlock(posX, posY, posZ);
		Block blockIDAbove = world.getBlock(posX, posY + 1, posZ);
		Block blockIDBelow = world.getBlock(posX, posY - 1, posZ);

		for (Block x : getSpawnBlocks())
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

	@Override
	public boolean generate(World world, Random rand, int posX, int posY, int posZ)
	{
		if ((!isLocationValid(world, posX, posY, posZ)) || (!isLocationValid(world, posX + 2, posY, posZ)) || (!isLocationValid(world, posX + 2, posY, posZ + 2)) || (!isLocationValid(world, posX, posY, posZ + 2)))
		{
			return false;
		}
		
		Block blockDirt = AliensVsPredator.blocks().terrainUniDirt;

		world.setBlock(posX + 0, posY + 0, posZ + 1, blockDirt);
		world.setBlock(posX + 0, posY + 1, posZ + 1, blockDirt);
		world.setBlock(posX + 0, posY + 2, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 0, posZ + 0, blockDirt);
		world.setBlock(posX + 1, posY + 0, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 0, posZ + 2, blockDirt);
		world.setBlock(posX + 1, posY + 1, posZ + 0, blockDirt);
		world.setBlock(posX + 1, posY + 1, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 2, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 3, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 4, posZ + 1, blockDirt);
		world.setBlock(posX + 1, posY + 5, posZ + 1, blockDirt);
		world.setBlock(posX + 2, posY + 0, posZ + 1, blockDirt);
		world.setBlock(posX + 2, posY + 1, posZ + 1, blockDirt);

		return true;
	}
}