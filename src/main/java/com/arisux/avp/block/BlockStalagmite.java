package com.arisux.avp.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.AliensVsPredator;

public class BlockStalagmite extends HookedBlock
{
	public BlockStalagmite(Material var3)
	{
		super(var3);
		this.setTickRandomly(true);
		float var4 = 0.2F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
	}

	@Override
	public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
	{
		return super.canPlaceBlockAt(var1, var2, var3, var4) && this.canThisPlantGrowOnThisBlockID(var1.getBlock(var2, var3 - 1, var4));
	}

	protected boolean canThisPlantGrowOnThisBlockID(Block var1)
	{
		return var1 == AliensVsPredator.blocks().terrainUniDirt || var1 == AliensVsPredator.blocks().terrainUniStone || var1 == Blocks.nether_brick || var1 == Blocks.netherrack || var1 == Blocks.farmland;
	}

	@Override
	public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5)
	{
		super.onNeighborBlockChange(var1, var2, var3, var4, var5);
		this.checkFlowerChange(var1, var2, var3, var4);
	}

	@Override
	public void updateTick(World var1, int var2, int var3, int var4, Random var5)
	{
		this.checkFlowerChange(var1, var2, var3, var4);
	}

	protected final void checkFlowerChange(World var1, int var2, int var3, int var4)
	{
		if (!this.canBlockStay(var1, var2, var3, var4))
		{
			this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
			var1.setBlockToAir(var2, var3, var4);
		}
	}

	@Override
	public boolean canBlockStay(World var1, int var2, int var3, int var4)
	{
		return (var1.getFullBlockLightValue(var2, var3, var4) >= 8 || var1.canBlockSeeTheSky(var2, var3, var4)) && this.canThisPlantGrowOnThisBlockID(var1.getBlock(var2, var3 - 1, var4));
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
