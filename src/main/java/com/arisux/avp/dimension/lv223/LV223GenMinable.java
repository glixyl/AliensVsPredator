package com.arisux.avp.dimension.lv223;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.arisux.avp.AliensVsPredator;

public class LV223GenMinable extends WorldGenerator
{
	private Block minableBlockId;
	private int numberOfBlocks;
	public LV223GenMinable(Block var1, int var2)
	{
		this.minableBlockId = var1;
		this.numberOfBlocks = var2;
	}

	public LV223GenMinable(Block var1, int var2, int var3)
	{
		this(var1, var3);
	}

	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		float var6 = var2.nextFloat() * 3.141593F;
		double var7 = var3 + 8 + MathHelper.sin(var6) * this.numberOfBlocks / 8.0F;
		double var9 = var3 + 8 - MathHelper.sin(var6) * this.numberOfBlocks / 8.0F;
		double var11 = var5 + 8 + MathHelper.cos(var6) * this.numberOfBlocks / 8.0F;
		double var13 = var5 + 8 - MathHelper.cos(var6) * this.numberOfBlocks / 8.0F;
		double var15 = var4 + var2.nextInt(3) - 2;
		double var17 = var4 + var2.nextInt(3) - 2;

		for (int var19 = 0; var19 <= this.numberOfBlocks; var19++)
		{
			double var20 = var7 + (var9 - var7) * var19 / this.numberOfBlocks;
			double var22 = var15 + (var17 - var15) * var19 / this.numberOfBlocks;
			double var24 = var11 + (var13 - var11) * var19 / this.numberOfBlocks;
			double var26 = var2.nextDouble() * this.numberOfBlocks / 16.0D;
			double var28 = (MathHelper.sin(var19 * 3.141593F / this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
			double var30 = (MathHelper.sin(var19 * 3.141593F / this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
			int var32 = MathHelper.floor_double(var20 - var28 / 2.0D);
			int var33 = MathHelper.floor_double(var22 - var30 / 2.0D);
			int var34 = MathHelper.floor_double(var24 - var28 / 2.0D);
			int var35 = MathHelper.floor_double(var20 + var28 / 2.0D);
			int var36 = MathHelper.floor_double(var22 + var30 / 2.0D);
			int var37 = MathHelper.floor_double(var24 + var28 / 2.0D);

			for (int var38 = var32; var38 <= var35; var38++)
			{
				double var39 = (var38 + 0.5D - var20) / (var28 / 2.0D);

				if (var39 * var39 >= 1.0D)
					continue;
				for (int var41 = var33; var41 <= var36; var41++)
				{
					double var42 = (var41 + 0.5D - var22) / (var30 / 2.0D);

					if (var39 * var39 + var42 * var42 >= 1.0D)
						continue;
					for (int var44 = var34; var44 <= var37; var44++)
					{
						double var45 = (var44 + 0.5D - var24) / (var28 / 2.0D);

						if ((var39 * var39 + var42 * var42 + var45 * var45 >= 1.0D) || (var1.getBlock(var38, var41, var44) != AliensVsPredator.instance().blocks.terrainUniStone))
							continue;
						var1.setBlock(var38, var41, var44, this.minableBlockId);
					}

				}

			}

		}

		return true;
	}
}