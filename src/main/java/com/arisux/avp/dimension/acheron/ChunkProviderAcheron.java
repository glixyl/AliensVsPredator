package com.arisux.avp.dimension.acheron;

import java.util.List;
import java.util.Random;

import com.arisux.avp.AliensVsPredator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

public class ChunkProviderAcheron implements IChunkProvider
{
	private NoiseGeneratorOctaves[] noiseGenOctaves = new NoiseGeneratorOctaves[6];
	private Random randomSeed;
	private World world;
	private double[] noiseArray;
	private double[] stoneNoise = new double[256];

	private BiomeGenBase[] biomesForGeneration;
	private double[] noise3;
	private double[] noise1;
	private double[] noise2;
	private double[] noise5;
	private double[] noise6;
	private float[] field_35388_l;

	public ChunkProviderAcheron(World world, long seed)
	{
		this.world = world;
		this.randomSeed = new Random(seed);
		this.noiseGenOctaves[0] = new NoiseGeneratorOctaves(this.randomSeed, 16);
		this.noiseGenOctaves[1] = new NoiseGeneratorOctaves(this.randomSeed, 16);
		this.noiseGenOctaves[2] = new NoiseGeneratorOctaves(this.randomSeed, 8);
		this.noiseGenOctaves[3] = new NoiseGeneratorOctaves(this.randomSeed, 4);
		this.noiseGenOctaves[4] = new NoiseGeneratorOctaves(this.randomSeed, 10);
		this.noiseGenOctaves[5] = new NoiseGeneratorOctaves(this.randomSeed, 16);
		new NoiseGeneratorOctaves(this.randomSeed, 8);
	}

	public void generateTerrain(int chunkX, int chunkZ, Block[] blocksInChunk)
	{
		byte var4 = 4;
		byte var5 = 16;
		byte var6 = 63;
		int var7 = var4 + 1;
		byte var8 = 17;
		int var9 = var4 + 1;
		this.biomesForGeneration = this.world.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, var7 + 5, var9 + 5);
		this.noiseArray = initializeNoiseField(this.noiseArray, chunkX * var4, 0, chunkZ * var4, var7, var8, var9);

		for (int var10 = 0; var10 < var4; var10++)
		{
			for (int var11 = 0; var11 < var4; var11++)
			{
				for (int var12 = 0; var12 < var5; var12++)
				{
					double var13 = 0.125D;
					double var15 = this.noiseArray[(((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0)];
					double var17 = this.noiseArray[(((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0)];
					double var19 = this.noiseArray[(((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0)];
					double var21 = this.noiseArray[(((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0)];
					double var23 = (this.noiseArray[(((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1)] - var15) * var13;
					double var25 = (this.noiseArray[(((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1)] - var17) * var13;
					double var27 = (this.noiseArray[(((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1)] - var19) * var13;
					double var29 = (this.noiseArray[(((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1)] - var21) * var13;

					for (int var31 = 0; var31 < 8; var31++)
					{
						double var32 = 0.25D;
						double var34 = var15;
						double var36 = var17;
						double var38 = (var19 - var15) * var32;
						for (int var42 = 0; var42 < 4; var42++)
						{
							int var43 = var42 + var10 * 4 << 11 | 0 + var11 * 4 << 7 | var12 * 8 + var31;
							short var44 = 128;
							var43 -= var44;
							double var45 = 0.25D;
							double var47 = (var36 - var34) * var45;
							double var49 = var34 - var47;

							for (int var51 = 0; var51 < 4; var51++)
							{
								if ((var49 += var47) > 0.0D)
								{
									int tmp510_509 = (var43 + var44);
									var43 = tmp510_509;
									blocksInChunk[tmp510_509] = AliensVsPredator.blocks().terrainUniStone;
								}
								else if (var12 * 8 + var31 < var6)
								{
									int tmp543_542 = (var43 + var44);
									var43 = tmp543_542;
									blocksInChunk[tmp543_542] = Blocks.water;
								}
								else
								{
									int tmp563_562 = (var43 + var44);
									var43 = tmp563_562;
									blocksInChunk[tmp563_562] = Blocks.air;
								}
							}

							var34 += var38;
							// tmp543_542 += var40;
						}

						var15 += var23;
						var17 += var25;
						var19 += var27;
						var21 += var29;
					}
				}
			}
		}
	}

	public void replaceBlocksForBiome(int var1, int var2, Block[] var3, BiomeGenBase[] var4)
	{
		byte var5 = 63;
		double var6 = 0.03125D;
		this.stoneNoise = this.noiseGenOctaves[3].generateNoiseOctaves(this.stoneNoise, var1 * 16, var2 * 16, 0, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);

		for (int var8 = 0; var8 < 16; var8++)
		{
			for (int var9 = 0; var9 < 16; var9++)
			{
				BiomeGenBase var10 = var4[(var9 + var8 * 16)];
				// float var11 =
				// var10.getFloatTemperature(p_150564_1_,
				// p_150564_2_, p_150564_3_)
				int var12 = (int) (this.stoneNoise[(var8 + var9 * 16)] / 3.0D + 3.0D + this.randomSeed.nextDouble() * 0.25D);
				int var13 = -1;
				Block var14 = var10.topBlock;
				Block var15 = var10.fillerBlock;

				for (int var16 = 127; var16 >= 0; var16--)
				{
					int var17 = (var9 * 16 + var8) * 128 + var16;

					if (var16 <= 0 + this.randomSeed.nextInt(5))
					{
						var3[var17] = Blocks.bedrock;
					}
					else
					{
						Block var18 = var3[var17];

						if (var18 == Blocks.air)
						{
							var13 = -1;
						}
						else
						{
							if (var18 == Blocks.stone)
								continue;
							if (var13 == -1)
							{
								if (var12 <= 0)
								{
									var14 = Blocks.air;
									var15 = AliensVsPredator.blocks().terrainUniStone;
								}
								else if ((var16 >= var5 - 4) && (var16 <= var5 + 1))
								{
									var14 = var10.topBlock;
									var15 = var10.fillerBlock;
								}

								if ((var16 < var5) && (var14 == Blocks.air))
								{
									var14 = Blocks.water;
								}

								var13 = var12;

								if (var16 >= var5 - 1)
								{
									var3[var17] = var14;
								}
								else
								{
									var3[var17] = var15;
								}
							}
							else
							{
								if (var13 <= 0)
									continue;
								var13--;
								var3[var17] = var15;

								if ((var13 != 0) || (var15 != Blocks.sand))
									continue;
								var13 = this.randomSeed.nextInt(4);
								var15 = Blocks.sandstone;
							}
						}
					}
				}
			}
		}
	}

	@Override
	public Chunk loadChunk(int chunkX, int chunkZ)
	{
		return provideChunk(chunkX, chunkZ);
	}

	@Override
	public Chunk provideChunk(int chunkX, int chunkZ)
	{
		Block[] blocksInChunk = new Block[16 * 16 * 128];
		this.randomSeed.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
		this.generateTerrain(chunkX, chunkZ, blocksInChunk);
		this.biomesForGeneration = this.world.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
		this.replaceBlocksForBiome(chunkX, chunkZ, blocksInChunk, this.biomesForGeneration);

		Chunk chunk = new Chunk(this.world, blocksInChunk, chunkX, chunkZ);
		byte[] biomes = chunk.getBiomeArray();

		for (int x = 0; x < biomes.length; x++)
		{
			biomes[x] = (byte) this.biomesForGeneration[x].biomeID;
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	private double[] initializeNoiseField(double[] var1, int var2, int var3, int var4, int var5, int var6, int var7)
	{
		if (var1 == null)
		{
			var1 = new double[var5 * var6 * var7];
		}

		if (this.field_35388_l == null)
		{
			this.field_35388_l = new float[25];

			for (int var8 = -2; var8 <= 2; var8++)
			{
				for (int var9 = -2; var9 <= 2; var9++)
				{
					float var10 = 10.0F / MathHelper.sqrt_float(var8 * var8 + var9 * var9 + 0.2F);
					this.field_35388_l[(var8 + 2 + (var9 + 2) * 5)] = var10;
				}
			}
		}

		double var44 = 684.41200000000003D;
		double var45 = 684.41200000000003D;
		this.noise5 = this.noiseGenOctaves[4].generateNoiseOctaves(this.noise5, var2, var4, var5, var7, 1.121D, 1.121D, 0.5D);
		this.noise6 = this.noiseGenOctaves[5].generateNoiseOctaves(this.noise6, var2, var4, var5, var7, 200.0D, 200.0D, 0.5D);
		this.noise3 = this.noiseGenOctaves[2].generateNoiseOctaves(this.noise3, var2, var3, var4, var5, var6, var7, var44 / 80.0D, var45 / 160.0D, var44 / 80.0D);
		this.noise1 = this.noiseGenOctaves[0].generateNoiseOctaves(this.noise1, var2, var3, var4, var5, var6, var7, var44, var45, var44);
		this.noise2 = this.noiseGenOctaves[1].generateNoiseOctaves(this.noise2, var2, var3, var4, var5, var6, var7, var44, var45, var44);
		int var14 = 0;
		int var15 = 0;

		for (int var16 = 0; var16 < var5; var16++)
		{
			for (int var17 = 0; var17 < var7; var17++)
			{
				float var18 = 0.0F;
				float var19 = 0.0F;
				float var20 = 0.0F;
				byte var21 = 2;
				BiomeGenBase var22 = this.biomesForGeneration[(var16 + 2 + (var17 + 2) * (var5 + 5))];

				for (int var23 = -var21; var23 <= var21; var23++)
				{
					for (int var24 = -var21; var24 <= var21; var24++)
					{
						BiomeGenBase var25 = this.biomesForGeneration[(var16 + var23 + 2 + (var17 + var24 + 2) * (var5 + 5))];
						float var26 = this.field_35388_l[(var23 + 2 + (var24 + 2) * 5)] / (var25.rootHeight + 2.0F);

						if (var25.rootHeight > var22.rootHeight)
						{
							var26 /= 2.0F;
						}

						var18 += var25.heightVariation * var26;
						var19 += var25.rootHeight * var26;
						var20 += var26;
					}
				}

				var18 /= var20;
				var19 /= var20;
				var18 = var18 * 0.9F + 0.1F;
				var19 = (var19 * 4.0F - 1.0F) / 8.0F;
				double var47 = this.noise6[var15] / 8000.0D;

				if (var47 < 0.0D)
				{
					var47 = -var47 * 0.3D;
				}

				var47 = var47 * 3.0D - 2.0D;

				if (var47 < 0.0D)
				{
					var47 /= 2.0D;

					if (var47 < -1.0D)
					{
						var47 = -1.0D;
					}

					var47 /= 1.4D;
					var47 /= 2.0D;
				}
				else
				{
					if (var47 > 1.0D)
					{
						var47 = 1.0D;
					}

					var47 /= 8.0D;
				}

				var15++;

				for (int var46 = 0; var46 < var6; var46++)
				{
					double var48 = var19;
					double var28 = var18;
					var48 += var47 * 0.2D;
					var48 = var48 * var6 / 16.0D;
					double var30 = var6 / 2.0D + var48 * 4.0D;
					double var32 = 0.0D;
					double var34 = (var46 - var30) * 12.0D * 128.0D / 128.0D / var28;

					if (var34 < 0.0D)
					{
						var34 *= 4.0D;
					}

					double var36 = this.noise1[var14] / 512.0D;
					double var38 = this.noise2[var14] / 512.0D;
					double var40 = (this.noise3[var14] / 10.0D + 1.0D) / 2.0D;

					if (var40 < 0.0D)
					{
						var32 = var36;
					}
					else if (var40 > 1.0D)
					{
						var32 = var38;
					}
					else
					{
						var32 = var36 + (var38 - var36) * var40;
					}

					var32 -= var34;

					if (var46 > var6 - 4)
					{
						double var42 = (var46 - (var6 - 4)) / 3.0F;
						var32 = var32 * (1.0D - var42) + -10.0D * var42;
					}

					var1[var14] = var32;
					var14++;
				}
			}
		}

		return var1;
	}

	@Override
	public boolean chunkExists(int var1, int var2)
	{
		return true;
	}

	@Override
	public void populate(IChunkProvider chunkProvider, int chunkX, int chunkZ)
	{
		BlockSand.fallInstantly = true;
		int posX = chunkX * 16;
		int posZ = chunkZ * 16;
		BiomeGenBase biome = this.world.getBiomeGenForCoords(posX + 16, posZ + 16);
		this.randomSeed.setSeed(this.world.getSeed());
		this.randomSeed.setSeed(chunkX * (this.randomSeed.nextLong() / 2L * 2L + 1L) + chunkZ * (this.randomSeed.nextLong() / 2L * 2L + 1L) ^ this.world.getSeed());

		biome.decorate(this.world, this.randomSeed, posX, posZ);
		SpawnerAnimals.performWorldGenSpawning(this.world, biome, posX + 8, posZ + 8, 16, 16, this.randomSeed);
		posX += 8;
		posZ += 8;

		for (int subX = 0; subX < 16; subX++)
		{
			for (int subZ = 0; subZ < 16; subZ++)
			{
				int precipitationHeight = this.world.getPrecipitationHeight(posX + subX, posZ + subZ);

				if (this.world.isBlockFreezable(subX + posX, precipitationHeight - 1, subZ + posZ))
				{
					this.world.setBlock(subX + posX, precipitationHeight - 1, subZ + posZ, Blocks.ice);
				}

				if (this.world.canBlockFreeze(subX + posX, precipitationHeight, subZ + posZ, true))
				{
					this.world.setBlock(subX + posX, precipitationHeight, subZ + posZ, Blocks.snow);
				}
			}
		}

		BlockSand.fallInstantly = false;
	}

	@Override
	public boolean saveChunks(boolean singlePass, IProgressUpdate progressUpdate)
	{
		return true;
	}

	@Override
	public boolean canSave()
	{
		return true;
	}

	@Override
	public String makeString()
	{
		return "RandomLevelSource";
	}

	@Override
	public List<?> getPossibleCreatures(EnumCreatureType creatureType, int posX, int posY, int posZ)
	{
		BiomeGenBase biome = this.world.getBiomeGenForCoords(posX, posZ);
		return biome == null ? null : biome.getSpawnableList(creatureType);
	}

	@Override
	public int getLoadedChunkCount()
	{
		return 0;
	}

	@Override
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	@Override
	public void recreateStructures(int i, int j)
	{
		;
	}

	@Override
	public void saveExtraData()
	{
		;
	}

	@Override
	public ChunkPosition func_147416_a(World world, String var2, int posX, int posY, int posZ)
	{
		return null;
	}
}
