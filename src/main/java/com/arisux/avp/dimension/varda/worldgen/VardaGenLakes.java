package com.arisux.avp.dimension.varda.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.arisux.avp.AliensVsPredator;

public class VardaGenLakes extends WorldGenerator
{
	private Block blockIndex;

	public VardaGenLakes(Block var1)
	{
		this.blockIndex = var1;
	}

	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		var3 -= 8;

		for (var5 -= 8; (var4 > 5) && (var1.isAirBlock(var3, var4, var5)); var4--)
			;
		if (var4 <= 4)
		{
			return false;
		}

		var4 -= 4;
		boolean[] var6 = new boolean[2048];
		int var7 = var2.nextInt(4) + 4;

		for (int var8 = 0; var8 < var7; var8++)
		{
			double var9 = var2.nextDouble() * 6.0D + 3.0D;
			double var11 = var2.nextDouble() * 4.0D + 2.0D;
			double var13 = var2.nextDouble() * 6.0D + 3.0D;
			double var15 = var2.nextDouble() * (16.0D - var9 - 2.0D) + 1.0D + var9 / 2.0D;
			double var17 = var2.nextDouble() * (8.0D - var11 - 4.0D) + 2.0D + var11 / 2.0D;
			double var19 = var2.nextDouble() * (16.0D - var13 - 2.0D) + 1.0D + var13 / 2.0D;

			for (int var21 = 1; var21 < 15; var21++)
			{
				for (int var22 = 1; var22 < 15; var22++)
				{
					for (int var23 = 1; var23 < 7; var23++)
					{
						double var24 = (var21 - var15) / (var9 / 2.0D);
						double var26 = (var23 - var17) / (var11 / 2.0D);
						double var28 = (var22 - var19) / (var13 / 2.0D);
						double var30 = var24 * var24 + var26 * var26 + var28 * var28;

						if (var30 >= 1.0D)
							continue;
						var6[((var21 * 16 + var22) * 8 + var23)] = true;
					}

				}

			}

		}

		for (int var8 = 0; var8 < 16; var8++)
		{
			for (int var32 = 0; var32 < 16; var32++)
			{
				for (int var10 = 0; var10 < 8; var10++)
				{
					boolean var33 = (var6[((var8 * 16 + var32) * 8 + var10)] == false) && (((var8 < 15) && (var6[(((var8 + 1) * 16 + var32) * 8 + var10)] != false)) || ((var8 > 0) && (var6[(((var8 - 1) * 16 + var32) * 8 + var10)] != false)) || ((var32 < 15) && (var6[((var8 * 16 + var32 + 1) * 8 + var10)] != false)) || ((var32 > 0) && (var6[((var8 * 16 + (var32 - 1)) * 8 + var10)] != false)) || ((var10 < 7) && (var6[((var8 * 16 + var32) * 8 + var10 + 1)] != false)) || ((var10 > 0) && (var6[((var8 * 16 + var32) * 8 + (var10 - 1))] != false)));

					if (!var33)
						continue;
					Material var12 = var1.getBlock(var3 + var8, var4 + var10, var5 + var32).getMaterial();

					if ((var10 >= 4) && (var12.isLiquid()))
					{
						return false;
					}

					if ((var10 < 4) && (!var12.isSolid()) && (var1.getBlock(var3 + var8, var4 + var10, var5 + var32) != this.blockIndex))
					{
						return false;
					}
				}
			}

		}

		for (int var8 = 0; var8 < 16; var8++)
		{
			for (int var32 = 0; var32 < 16; var32++)
			{
				for (int var10 = 0; var10 < 8; var10++)
				{
					if (var6[((var8 * 16 + var32) * 8 + var10)] == false)
						continue;
					var1.setBlock(var3 + var8, var4 + var10, var5 + var32, var10 >= 4 ? Blocks.air : this.blockIndex);
				}
			}

		}

		for (int var8 = 0; var8 < 16; var8++)
		{
			for (int var32 = 0; var32 < 16; var32++)
			{
				for (int var10 = 4; var10 < 8; var10++)
				{
					if ((var6[((var8 * 16 + var32) * 8 + var10)] == false) || (var1.getBlock(var3 + var8, var4 + var10 - 1, var5 + var32) != Blocks.dirt) || (var1.getSavedLightValue(EnumSkyBlock.Sky, var3 + var8, var4 + var10, var5 + var32) <= 0))
						continue;
					var1.setBlock(var3 + var8, var4 + var10 - 1, var5 + var32, AliensVsPredator.instance().blocks.terrainUniDirt);
				}
			}

		}

		if (this.blockIndex.getMaterial() == Material.lava)
		{
			for (int var8 = 0; var8 < 16; var8++)
			{
				for (int var32 = 0; var32 < 16; var32++)
				{
					for (int var10 = 0; var10 < 8; var10++)
					{
						boolean var33 = (var6[((var8 * 16 + var32) * 8 + var10)] == false) && (((var8 < 15) && (var6[(((var8 + 1) * 16 + var32) * 8 + var10)] != false)) || ((var8 > 0) && (var6[(((var8 - 1) * 16 + var32) * 8 + var10)] != false)) || ((var32 < 15) && (var6[((var8 * 16 + var32 + 1) * 8 + var10)] != false)) || ((var32 > 0) && (var6[((var8 * 16 + (var32 - 1)) * 8 + var10)] != false)) || ((var10 < 7) && (var6[((var8 * 16 + var32) * 8 + var10 + 1)] != false)) || ((var10 > 0) && (var6[((var8 * 16 + var32) * 8 + (var10 - 1))] != false)));

						if ((!var33) || ((var10 >= 4) && (var2.nextInt(2) == 0)) || (!var1.getBlock(var3 + var8, var4 + var10, var5 + var32).getMaterial().isSolid()))
							continue;
						var1.setBlock(var3 + var8, var4 + var10, var5 + var32, AliensVsPredator.instance().blocks.terrainUniStone);
					}
				}
			}

		}

		if (this.blockIndex.getMaterial() == Material.water)
		{
			for (int var8 = 0; var8 < 16; var8++)
			{
				for (int var32 = 0; var32 < 16; var32++)
				{
					byte var34 = 4;

					if (!var1.isBlockFreezable(var3 + var8, var4 + var34, var5 + var32))
						continue;
					var1.setBlock(var3 + var8, var4 + var34, var5 + var32, Blocks.ice);
				}
			}

		}

		return true;
	}
}