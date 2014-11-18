package com.arisux.avp.dimension.lv223;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.arisux.avp.AliensVsPredator;

public class LV223GenLandSpike extends WorldGenerator
{
	static final byte[] otherCoordPairs = { 2, 0, 0, 1, 2, 1 };
	Random rand = new Random();
	World worldObj;
	int[] basePos = { 0, 0, 0 };
	int heightLimit = 0;
	int height;
	double heightAttenuation = 0.618D;
	double branchDensity = 1.0D;
	double branchSlope = 0.381D;
	double scaleWidth = 1.0D;
	double leafDensity = 1.0D;
	int trunkSize = 1;
	int heightLimitLimit = 12;
	int leafDistanceLimit = 4;
	int[][] leafNodes;

	public LV223GenLandSpike(boolean var1)
	{
		super(var1);
	}

	void generateLeafNodeList()
	{
		this.height = (int) (this.heightLimit * this.heightAttenuation);

		if (this.height >= this.heightLimit)
		{
			this.height = (this.heightLimit - 1);
		}

		int var1 = (int) (1.382D + Math.pow(this.leafDensity * this.heightLimit / 13.0D, 2.0D));

		if (var1 < 1)
		{
			var1 = 1;
		}

		int[][] var2 = new int[var1 * this.heightLimit][4];
		int var3 = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
		int var4 = 1;
		int var5 = this.basePos[1] + this.height;
		int var6 = var3 - this.basePos[1];
		var2[0][0] = this.basePos[0];
		var2[0][1] = var3;
		var2[0][2] = this.basePos[2];
		var2[0][3] = var5;
		var3--;

		while (var6 >= 0)
		{
			int var7 = 0;
			float var8 = layerSize(var6);

			if (var8 < 0.0F)
			{
				var3--;
				var6--;
			} else
			{
				for (double var9 = 0.5D; var7 < var1; var7++)
				{
					double var11 = this.scaleWidth * var8 * (this.rand.nextFloat() + 0.328D);
					double var13 = this.rand.nextFloat() * 2.0D * 3.141592653589793D;
					int var15 = MathHelper.floor_double(var11 * Math.sin(var13) + this.basePos[0] + var9);
					int var16 = MathHelper.floor_double(var11 * Math.cos(var13) + this.basePos[2] + var9);
					int[] var17 = { var15, var3, var16 };
					int[] var18 = { var15, var3 + this.leafDistanceLimit, var16 };

					if (checkBlockLine(var17, var18) != -1)
						continue;
					int[] var19 = { this.basePos[0], this.basePos[1], this.basePos[2] };
					double var20 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - var17[0]), 2.0D) + Math.pow(Math.abs(this.basePos[2] - var17[2]), 2.0D));
					double var22 = var20 * this.branchSlope;

					if (var17[1] - var22 > var5)
					{
						var19[1] = var5;
					} else
					{
						var19[1] = (int) (var17[1] - var22);
					}

					if (checkBlockLine(var19, var17) != -1)
						continue;
					var2[var4][0] = var15;
					var2[var4][1] = var3;
					var2[var4][2] = var16;
					var2[var4][3] = var19[1];
					var4++;
				}

				var3--;
				var6--;
			}
		}

		this.leafNodes = new int[var4][4];
		System.arraycopy(var2, 0, this.leafNodes, 0, var4);
	}

	void genTreeLayer(int var1, int var2, int var3, float var4, byte var5, Block var6)
	{
		int var7 = (int) (var4 + 0.618D);
		byte var8 = otherCoordPairs[var5];
		byte var9 = otherCoordPairs[(var5 + 3)];
		int[] var10 = { var1, var2, var3 };
		int[] var11 = { 0, 0, 0 };
		int var12 = -var7;
		int var13 = -var7;

		for (var11[var5] = var10[var5]; var12 <= var7; var12++)
		{
			var10[var8] += var12;
			var13 = -var7;

			while (var13 <= var7)
			{
				double var14 = Math.pow(Math.abs(var12) + 0.5D, 2.0D) + Math.pow(Math.abs(var13) + 0.5D, 2.0D);

				if (var14 > var4 * var4)
				{
					var13++;
				} else
				{
					var10[var9] += var13;
					Block var16 = this.worldObj.getBlock(var11[0], var11[1], var11[2]);

					if ((var16 != Blocks.air) && (var16 != AliensVsPredator.instance().blocks.terrainStalagmite))
					{
						var13++;
					} else
					{
						setBlockAndNotifyAdequately(this.worldObj, var11[0], var11[1], var11[2], var6, 0);
						var13++;
					}
				}
			}
		}
	}

	float layerSize(int var1)
	{
		if (var1 < this.heightLimit * 0.3D)
		{
			return -1.618F;
		}

		float var2 = this.heightLimit / 2.0F;
		float var3 = this.heightLimit / 2.0F - var1;
		float var4;
		if (var3 == 0.0F)
		{
			var4 = var2;
		} else
		{
			if (Math.abs(var3) >= var2)
			{
				var4 = 0.0F;
			} else
			{
				var4 = (float) Math.sqrt(Math.pow(Math.abs(var2), 2.0D) - Math.pow(Math.abs(var3), 2.0D));
			}
		}
		var4 *= 0.5F;
		return var4;
	}

	float leafSize(int var1)
	{
		return (var1 >= 0) && (var1 < this.leafDistanceLimit) ? 2.0F : (var1 != 0) && (var1 != this.leafDistanceLimit - 1) ? 3.0F : -1.0F;
	}

	void generateLeafNode(int var1, int var2, int var3)
	{
		int var4 = var2;

		for (int var5 = var2 + this.leafDistanceLimit; var4 < var5; var4++)
		{
			float var6 = leafSize(var4 - var2);
			genTreeLayer(var1, var4, var3, var6, (byte) 1, AliensVsPredator.instance().blocks.terrainStalagmite);
		}
	}

	void placeBlockLine(int[] var1, int[] var2, Block var3)
	{
		int[] var4 = { 0, 0, 0 };
		byte var5 = 0;

		for (byte var6 = 0; var5 < 3; var5 = (byte) (var5 + 1))
		{
			var2[var5] -= var1[var5];

			if (Math.abs(var4[var5]) <= Math.abs(var4[var6]))
				continue;
			var6 = var5;
		}

		byte var6 = 0;

		if (var4[var6] != 0)
		{
			byte var7 = otherCoordPairs[var6];
			byte var8 = otherCoordPairs[(var6 + 3)];
			byte var9;
			if (var4[var6] > 0)
			{
				var9 = 1;
			} else
			{
				var9 = -1;
			}

			double var10 = var4[var7] / var4[var6];
			double var12 = var4[var8] / var4[var6];
			int[] var14 = { 0, 0, 0 };
			int var15 = 0;

			for (int var16 = var4[var6] + var9; var15 != var16; var15 += var9)
			{
				var14[var6] = MathHelper.floor_double(var1[var6] + var15 + 0.5D);
				var14[var7] = MathHelper.floor_double(var1[var7] + var15 * var10 + 0.5D);
				var14[var8] = MathHelper.floor_double(var1[var8] + var15 * var12 + 0.5D);
				int var18 = Math.abs(var14[0] - var1[0]);
				int var19 = Math.abs(var14[2] - var1[2]);
				int var20 = Math.max(var18, var19);

				if (var20 > 0)
				{
					if (var18 == var20)
					{
					} else if (var19 == var20)
					{
					}
				}

				worldObj.setBlock(var14[0], var14[1], var14[2], var3);
			}
		}
	}

	void generateLeaves()
	{
		int var1 = 0;

		for (int var2 = this.leafNodes.length; var1 < var2; var1++)
		{
			int var3 = this.leafNodes[var1][0];
			int var4 = this.leafNodes[var1][1];
			int var5 = this.leafNodes[var1][2];
			generateLeafNode(var3, var4, var5);
		}
	}

	boolean leafNodeNeedsBase(int var1)
	{
		return var1 >= this.heightLimit * 0.2D;
	}

	void generateTrunk()
	{
		int var1 = this.basePos[0];
		int var2 = this.basePos[1];
		int var3 = this.basePos[1] + this.height;
		int var4 = this.basePos[2];
		int[] var5 = { var1, var2, var4 };
		int[] var6 = { var1, var3, var4 };
		placeBlockLine(var5, var6, AliensVsPredator.instance().blocks.terrainUniSand);

		if (this.trunkSize == 2)
		{
			var5[0] += 1;
			var6[0] += 1;
			placeBlockLine(var5, var6, AliensVsPredator.instance().blocks.terrainUniSand);
			var5[2] += 1;
			var6[2] += 1;
			placeBlockLine(var5, var6, AliensVsPredator.instance().blocks.terrainUniSand);
			var5[0] += -1;
			var6[0] += -1;
			placeBlockLine(var5, var6, AliensVsPredator.instance().blocks.terrainUniSand);
		}
	}

	void generateLeafNodeBases()
	{
		int var1 = 0;
		int var2 = this.leafNodes.length;

		for (int[] var3 = { this.basePos[0], this.basePos[1], this.basePos[2] }; var1 < var2; var1++)
		{
			int[] var4 = this.leafNodes[var1];
			int[] var5 = { var4[0], var4[1], var4[2] };
			var3[1] = var4[3];
			int var6 = var3[1] - this.basePos[1];

			if (!leafNodeNeedsBase(var6))
				continue;
			placeBlockLine(var3, var5, AliensVsPredator.instance().blocks.terrainUniSand);
		}
	}

	int checkBlockLine(int[] var1, int[] var2)
	{
		int[] var3 = { 0, 0, 0 };
		byte var4 = 0;

		for (byte var5 = 0; var4 < 3; var4 = (byte) (var4 + 1))
		{
			var2[var4] -= var1[var4];

			if (Math.abs(var3[var4]) <= Math.abs(var3[var5]))
				continue;
			var5 = var4;
		}

		byte var5 = 0;

		if (var3[var5] == 0)
		{
			return -1;
		}

		byte var6 = otherCoordPairs[var5];
		byte var7 = otherCoordPairs[(var5 + 3)];
		byte var8;
		if (var3[var5] > 0)
		{
			var8 = 1;
		} else
		{
			var8 = -1;
		}

		double var9 = var3[var6] / var3[var5];
		double var11 = var3[var7] / var3[var5];
		int[] var13 = { 0, 0, 0 };
		int var14 = 0;

		int tmp = 0;

		for (int var15 = var3[var5] + var8; var14 != var15; var14 += var8)
		{
			tmp = var15;
			var1[var5] += var14;
			var13[var6] = MathHelper.floor_double(var1[var6] + var14 * var9);
			var13[var7] = MathHelper.floor_double(var1[var7] + var14 * var11);
			Block var16 = this.worldObj.getBlock(var13[0], var13[1], var13[2]);

			if ((var16 != Blocks.air) && (var16 != AliensVsPredator.instance().blocks.terrainStalagmite))
			{
				break;
			}
		}

		return var14 == tmp ? -1 : Math.abs(var14);
	}

	boolean validTreeLocation()
	{
		int[] var1 = { this.basePos[0], this.basePos[1], this.basePos[2] };
		int[] var2 = { this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2] };
		Block var3 = this.worldObj.getBlock(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);

		if ((var3 != Blocks.dirt) && (var3 != Blocks.grass))
		{
			return false;
		}

		int var4 = checkBlockLine(var1, var2);

		if (var4 == -1)
		{
			return true;
		}
		if (var4 < 6)
		{
			return false;
		}

		this.heightLimit = var4;
		return true;
	}

	@Override
	public void setScale(double var1, double var3, double var5)
	{
		this.heightLimitLimit = (int) (var1 * 12.0D);

		if (var1 > 0.5D)
		{
			this.leafDistanceLimit = 5;
		}

		this.scaleWidth = var3;
		this.leafDensity = var5;
	}

	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		this.worldObj = var1;
		long var6 = var2.nextLong();
		this.rand.setSeed(var6);
		this.basePos[0] = var3;
		this.basePos[1] = var4;
		this.basePos[2] = var5;

		if (this.heightLimit == 0)
		{
			this.heightLimit = (5 + this.rand.nextInt(this.heightLimitLimit));
		}

		if (!validTreeLocation())
		{
			return false;
		}

		generateLeafNodeList();
		generateLeaves();
		generateTrunk();
		generateLeafNodeBases();
		return true;
	}
}