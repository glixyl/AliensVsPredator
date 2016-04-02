package com.arisux.avp.block;

import java.util.Random;

import com.arisux.avp.AliensVsPredator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockStalagmite extends Block
{
	public BlockStalagmite(Material material)
	{
		super(material);
		this.setTickRandomly(true);
		float size = 0.2F;
		this.setBlockBounds(0.5F - size, 0.0F, 0.5F - size, 0.5F + size, size * 3.0F, 0.5F + size);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int posX, int posY, int posZ)
	{
		return super.canPlaceBlockAt(world, posX, posY, posZ) && this.canThisPlantGrowOn(world.getBlock(posX, posY - 1, posZ));
	}

	protected boolean canThisPlantGrowOn(Block block)
	{
		return block == AliensVsPredator.blocks().terrainUniDirt || block == AliensVsPredator.blocks().terrainUniStone || block.getMaterial() == Material.ground || block.getMaterial() == Material.rock;
	}

	@Override
	public void onNeighborBlockChange(World world, int posX, int posY, int posZ, Block block)
	{
		super.onNeighborBlockChange(world, posX, posY, posZ, block);
		this.checkFlowerChange(world, posX, posY, posZ);
	}

	@Override
	public void updateTick(World world, int posX, int posY, int posZ, Random rand)
	{
		this.checkFlowerChange(world, posX, posY, posZ);
	}

	protected final void checkFlowerChange(World world, int posX, int posY, int posZ)
	{
		if (!this.canBlockStay(world, posX, posY, posZ))
		{
			this.dropBlockAsItem(world, posX, posY, posZ, world.getBlockMetadata(posX, posY, posZ), 0);
			world.setBlockToAir(posX, posY, posZ);
		}
	}

	@Override
	public boolean canBlockStay(World world, int posX, int posY, int posZ)
	{
		return this.canThisPlantGrowOn(world.getBlock(posX, posY - 1, posZ));
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}
}
