package com.arisux.avp.dimension.varda.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenBase;

import com.arisux.avp.AliensVsPredator;

public class VardaGenCaves extends MapGenBase
{
	protected void generateLargeCaveNode(long var1, int var3, int var4, Block[] var5, double var6, double var8, double var10)
	{
		generateCaveNode(var1, var3, var4, var5, var6, var8, var10, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
	}

	protected void generateCaveNode(long var1, int var3, int var4, Block[] var5, double var6, double var8, double var10, float var12, float var13, float var14, int var15, int var16, double var17)
	{
		double var19 = var3 * 16 + 8;
		double var21 = var4 * 16 + 8;
		float var23 = 0.0F;
		float var24 = 0.0F;
		Random var25 = new Random(var1);

		if (var16 <= 0)
		{
			int var26 = this.range * 16 - 16;
			var16 = var26 - var25.nextInt(var26 / 4);
		}

		boolean var62 = false;

		if (var15 == -1)
		{
			var15 = var16 / 2;
			var62 = true;
		}

		int var27 = var25.nextInt(var16 / 2) + var16 / 4;

		for (boolean var28 = var25.nextInt(6) == 0; var15 < var16; var15++)
		{
			double var29 = 1.5D + MathHelper.sin(var15 * 3.141593F / var16) * var12 * 1.0F;
			double var31 = var29 * var17;
			float var33 = MathHelper.cos(var14);
			float var34 = MathHelper.sin(var14);
			var6 += MathHelper.cos(var13) * var33;
			var8 += var34;
			var10 += MathHelper.sin(var13) * var33;

			if (var28)
			{
				var14 *= 0.92F;
			} else
			{
				var14 *= 0.7F;
			}

			var14 += var24 * 0.1F;
			var13 += var23 * 0.1F;
			var24 *= 0.9F;
			var23 *= 0.75F;
			var24 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 2.0F;
			var23 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 4.0F;

			if ((!var62) && (var15 == var27) && (var12 > 1.0F) && (var16 > 0))
			{
				generateCaveNode(var25.nextLong(), var3, var4, var5, var6, var8, var10, var25.nextFloat() * 0.5F + 0.5F, var13 - 1.570796F, var14 / 3.0F, var15, var16, 1.0D);
				generateCaveNode(var25.nextLong(), var3, var4, var5, var6, var8, var10, var25.nextFloat() * 0.5F + 0.5F, var13 + 1.570796F, var14 / 3.0F, var15, var16, 1.0D);
				return;
			}

			if ((!var62) && (var25.nextInt(4) == 0))
				continue;
			double var35 = var6 - var19;
			double var37 = var10 - var21;
			double var39 = var16 - var15;
			double var41 = var12 + 2.0F + 16.0F;

			if (var35 * var35 + var37 * var37 - var39 * var39 > var41 * var41)
			{
				return;
			}

			if ((var6 < var19 - 16.0D - var29 * 2.0D) || (var10 < var21 - 16.0D - var29 * 2.0D) || (var6 > var19 + 16.0D + var29 * 2.0D) || (var10 > var21 + 16.0D + var29 * 2.0D))
				continue;
			int var43 = MathHelper.floor_double(var6 - var29) - var3 * 16 - 1;
			int var44 = MathHelper.floor_double(var6 + var29) - var3 * 16 + 1;
			int var45 = MathHelper.floor_double(var8 - var31) - 1;
			int var46 = MathHelper.floor_double(var8 + var31) + 1;
			int var47 = MathHelper.floor_double(var10 - var29) - var4 * 16 - 1;
			int var48 = MathHelper.floor_double(var10 + var29) - var4 * 16 + 1;

			if (var43 < 0)
			{
				var43 = 0;
			}

			if (var44 > 16)
			{
				var44 = 16;
			}

			if (var45 < 1)
			{
				var45 = 1;
			}

			if (var46 > 120)
			{
				var46 = 120;
			}

			if (var47 < 0)
			{
				var47 = 0;
			}

			if (var48 > 16)
			{
				var48 = 16;
			}

			boolean var49 = false;

			for (int var51 = var43; (!var49) && (var51 < var44); var51++)
			{
				for (int var52 = var47; (!var49) && (var52 < var48); var52++)
				{
					for (int var53 = var46 + 1; (!var49) && (var53 >= var45 - 1); var53--)
					{
						int var50 = (var51 * 16 + var52) * 128 + var53;

						if ((var53 < 0) || (var53 >= 128))
							continue;
						if ((var5[var50] == Blocks.flowing_water) || (var5[var50] == Blocks.water))
						{
							var49 = true;
						}

						if ((var53 == var45 - 1) || (var51 == var43) || (var51 == var44 - 1) || (var52 == var47) || (var52 == var48 - 1))
							continue;
						var53 = var45;
					}

				}

			}

			if (var49)
				continue;
			for (int var51 = var43; var51 < var44; var51++)
			{
				double var63 = (var51 + var3 * 16 + 0.5D - var6) / var29;

				for (int var50 = var47; var50 < var48; var50++)
				{
					double var54 = (var50 + var4 * 16 + 0.5D - var10) / var29;
					int var56 = (var51 * 16 + var50) * 128 + var46;
					boolean var57 = false;

					if (var63 * var63 + var54 * var54 >= 1.0D)
						continue;
					for (int var58 = var46 - 1; var58 >= var45; var58--)
					{
						double var59 = (var58 + 0.5D - var8) / var31;

						if ((var59 > -0.7D) && (var63 * var63 + var59 * var59 + var54 * var54 < 1.0D))
						{
							Block var61 = var5[var56];

							if (var61 == AliensVsPredator.instance().blocks.terrainUniDirt)
							{
								var57 = true;
							}

							if ((var61 != AliensVsPredator.instance().blocks.terrainUniStone) || (var61 != AliensVsPredator.instance().blocks.terrainUniDirt) || (var61 != AliensVsPredator.instance().blocks.terrainUniDirt))
							{
								if (var58 < 10)
								{
									var5[var56] = Blocks.flowing_lava;
								} else
								{
									var5[var56] = Blocks.air;

									if ((var57) && (var5[(var56 - 1)] == AliensVsPredator.instance().blocks.terrainUniDirt))
									{
										var5[(var56 - 1)] = this.worldObj.getBiomeGenForCoords(var51 + var3 * 16, var50 + var4 * 16).topBlock;
									}
								}
							}
						}

						var56--;
					}
				}

			}

			if (var62)
				break;
		}
	}

	// overridden
	protected void recursiveGenerate(World var1, int var2, int var3, int var4, int var5, Block[] var6)
	{
		int var7 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(40) + 1) + 1);

		if (this.rand.nextInt(15) != 0)
		{
			var7 = 0;
		}

		for (int var8 = 0; var8 < var7; var8++)
		{
			double var9 = var2 * 16 + this.rand.nextInt(16);
			double var11 = this.rand.nextInt(this.rand.nextInt(120) + 8);
			double var13 = var3 * 16 + this.rand.nextInt(16);
			int var15 = 1;

			if (this.rand.nextInt(2) == 0)
			{
				generateLargeCaveNode(this.rand.nextLong(), var4, var5, var6, var9, var11, var13);
				var15 += this.rand.nextInt(4);
			}

			for (int var16 = 0; var16 < var15; var16++)
			{
				float var17 = this.rand.nextFloat() * 3.141593F * 2.0F;
				float var18 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float var19 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

				if (this.rand.nextInt(10) == 0)
				{
					var19 *= (this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F);
				}

				generateCaveNode(this.rand.nextLong(), var4, var5, var6, var9, var11, var13, var19, var17, var18, 0, 0, 1.0D);
			}
		}
	}
}