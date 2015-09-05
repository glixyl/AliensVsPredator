package com.arisux.avp.dimension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import com.arisux.avp.AliensVsPredator;

public class TeleporterLV extends Teleporter
{
	private final WorldServer worldServer;
	private final Random rand;
	private final LongHashMap destinationCoordinateCache = new LongHashMap();
	private final List<Long> destinationCoordinateKeys = new ArrayList<Long>();

	public TeleporterLV(WorldServer worldServer)
	{
		super(worldServer);
		this.worldServer = worldServer;
		this.rand = new Random(worldServer.getSeed());
	}

	@Override
	public void placeInPortal(Entity entity, double posX, double posY, double posZ, float yaw)
	{
		if (this.worldServer.provider.dimensionId != 1)
		{
			if (!this.placeInExistingPortal(entity, posX, posY, posZ, yaw))
			{
				this.makePortal(entity);
				this.placeInExistingPortal(entity, posX, posY, posZ, yaw);
			}
		}
		else
		{
			int i = MathHelper.floor_double(entity.posX);
			int j = MathHelper.floor_double(entity.posY) - 1;
			int k = MathHelper.floor_double(entity.posZ);
			byte b0 = 1;
			byte b1 = 0;

			for (int l = -2; l <= 2; ++l)
			{
				for (int i1 = -2; i1 <= 2; ++i1)
				{
					for (int j1 = -1; j1 < 3; ++j1)
					{
						int k1 = i + i1 * b0 + l * b1;
						int l1 = j + j1;
						int i2 = k + i1 * b1 - l * b0;
						boolean flag = j1 < 0;
						this.worldServer.setBlock(k1, l1, i2, flag ? AliensVsPredator.blocks().blockEngineerShipFloor : Blocks.air);
					}
				}
			}

			entity.setLocationAndAngles((double) i, (double) j, (double) k, entity.rotationYaw, 0.0F);
			entity.motionX = entity.motionY = entity.motionZ = 0.0D;
		}
	}

	@Override
	public boolean placeInExistingPortal(Entity entity, double posX, double posY, double posZ, float yaw)
	{
		short short1 = 128;
		double d3 = -1.0D;
		int i = 0;
		int j = 0;
		int k = 0;
		int l = MathHelper.floor_double(entity.posX);
		int i1 = MathHelper.floor_double(entity.posZ);
		long j1 = ChunkCoordIntPair.chunkXZ2Int(l, i1);
		boolean flag = true;
		double d7;
		int l3;

		if (this.destinationCoordinateCache.containsItem(j1))
		{
			Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition) this.destinationCoordinateCache.getValueByKey(j1);
			d3 = 0.0D;
			i = portalposition.posX;
			j = portalposition.posY;
			k = portalposition.posZ;
			portalposition.lastUpdateTime = this.worldServer.getTotalWorldTime();
			flag = false;
		}
		else
		{
			for (l3 = l - short1; l3 <= l + short1; ++l3)
			{
				double d4 = (double) l3 + 0.5D - entity.posX;

				for (int l1 = i1 - short1; l1 <= i1 + short1; ++l1)
				{
					double d5 = (double) l1 + 0.5D - entity.posZ;

					for (int i2 = this.worldServer.getActualHeight() - 1; i2 >= 0; --i2)
					{
						if (this.worldServer.getBlock(l3, i2, l1) == AliensVsPredator.blocks().blockPortalVarda)
						{
							while (this.worldServer.getBlock(l3, i2 - 1, l1) == AliensVsPredator.blocks().blockPortalVarda)
							{
								--i2;
							}

							d7 = (double) i2 + 0.5D - entity.posY;
							double d8 = d4 * d4 + d7 * d7 + d5 * d5;

							if (d3 < 0.0D || d8 < d3)
							{
								d3 = d8;
								i = l3;
								j = i2;
								k = l1;
							}
						}
					}
				}
			}
		}

		if (d3 >= 0.0D)
		{
			if (flag)
			{
				this.destinationCoordinateCache.add(j1, new Teleporter.PortalPosition(i, j, k, this.worldServer.getTotalWorldTime()));
				this.destinationCoordinateKeys.add(Long.valueOf(j1));
			}

			double d11 = (double) i + 0.5D;
			double d6 = (double) j + 0.5D;
			d7 = (double) k + 0.5D;
			int i4 = -1;

			if (this.worldServer.getBlock(i - 1, j, k) == AliensVsPredator.blocks().blockPortalVarda)
			{
				i4 = 2;
			}

			if (this.worldServer.getBlock(i + 1, j, k) == AliensVsPredator.blocks().blockPortalVarda)
			{
				i4 = 0;
			}

			if (this.worldServer.getBlock(i, j, k - 1) == AliensVsPredator.blocks().blockPortalVarda)
			{
				i4 = 3;
			}

			if (this.worldServer.getBlock(i, j, k + 1) == AliensVsPredator.blocks().blockPortalVarda)
			{
				i4 = 1;
			}

			int j2 = entity.getTeleportDirection();

			if (i4 > -1)
			{
				int k2 = Direction.rotateLeft[i4];
				int l2 = Direction.offsetX[i4];
				int i3 = Direction.offsetZ[i4];
				int j3 = Direction.offsetX[k2];
				int k3 = Direction.offsetZ[k2];
				boolean flag1 = !this.worldServer.isAirBlock(i + l2 + j3, j, k + i3 + k3) || !this.worldServer.isAirBlock(i + l2 + j3, j + 1, k + i3 + k3);
				boolean flag2 = !this.worldServer.isAirBlock(i + l2, j, k + i3) || !this.worldServer.isAirBlock(i + l2, j + 1, k + i3);

				if (flag1 && flag2)
				{
					i4 = Direction.rotateOpposite[i4];
					k2 = Direction.rotateOpposite[k2];
					l2 = Direction.offsetX[i4];
					i3 = Direction.offsetZ[i4];
					j3 = Direction.offsetX[k2];
					k3 = Direction.offsetZ[k2];
					l3 = i - j3;
					d11 -= (double) j3;
					int k1 = k - k3;
					d7 -= (double) k3;
					flag1 = !this.worldServer.isAirBlock(l3 + l2 + j3, j, k1 + i3 + k3) || !this.worldServer.isAirBlock(l3 + l2 + j3, j + 1, k1 + i3 + k3);
					flag2 = !this.worldServer.isAirBlock(l3 + l2, j, k1 + i3) || !this.worldServer.isAirBlock(l3 + l2, j + 1, k1 + i3);
				}

				float f1 = 0.5F;
				float f2 = 0.5F;

				if (!flag1 && flag2)
				{
					f1 = 1.0F;
				}
				else if (flag1 && !flag2)
				{
					f1 = 0.0F;
				}
				else if (flag1 && flag2)
				{
					f2 = 0.0F;
				}

				d11 += (double) ((float) j3 * f1 + f2 * (float) l2);
				d7 += (double) ((float) k3 * f1 + f2 * (float) i3);
				float f3 = 0.0F;
				float f4 = 0.0F;
				float f5 = 0.0F;
				float f6 = 0.0F;

				if (i4 == j2)
				{
					f3 = 1.0F;
					f4 = 1.0F;
				}
				else if (i4 == Direction.rotateOpposite[j2])
				{
					f3 = -1.0F;
					f4 = -1.0F;
				}
				else if (i4 == Direction.rotateRight[j2])
				{
					f5 = 1.0F;
					f6 = -1.0F;
				}
				else
				{
					f5 = -1.0F;
					f6 = 1.0F;
				}

				double d9 = entity.motionX;
				double d10 = entity.motionZ;
				entity.motionX = d9 * (double) f3 + d10 * (double) f6;
				entity.motionZ = d9 * (double) f5 + d10 * (double) f4;
				entity.rotationYaw = yaw - (float) (j2 * 90) + (float) (i4 * 90);
			}
			else
			{
				entity.motionX = entity.motionY = entity.motionZ = 0.0D;
			}

			entity.setLocationAndAngles(d11, d6, d7, entity.rotationYaw, entity.rotationPitch);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean makePortal(Entity entity)
	{
		byte b0 = 16;
		double d0 = -1.0D;
		int i = MathHelper.floor_double(entity.posX);
		int j = MathHelper.floor_double(entity.posY);
		int k = MathHelper.floor_double(entity.posZ);
		int l = i;
		int i1 = j;
		int j1 = k;
		int k1 = 0;
		int l1 = this.rand.nextInt(4);
		int i2;
		double d1;
		int k2;
		double d2;
		int i3;
		int j3;
		int k3;
		int l3;
		int i4;
		int j4;
		int k4;
		int l4;
		int i5;
		double d3;
		double d4;

		for (i2 = i - b0; i2 <= i + b0; ++i2)
		{
			d1 = (double) i2 + 0.5D - entity.posX;

			for (k2 = k - b0; k2 <= k + b0; ++k2)
			{
				d2 = (double) k2 + 0.5D - entity.posZ;
				label274:

				for (i3 = this.worldServer.getActualHeight() - 1; i3 >= 0; --i3)
				{
					if (this.worldServer.isAirBlock(i2, i3, k2))
					{
						while (i3 > 0 && this.worldServer.isAirBlock(i2, i3 - 1, k2))
						{
							--i3;
						}

						for (j3 = l1; j3 < l1 + 4; ++j3)
						{
							k3 = j3 % 2;
							l3 = 1 - k3;

							if (j3 % 4 >= 2)
							{
								k3 = -k3;
								l3 = -l3;
							}

							for (i4 = 0; i4 < 3; ++i4)
							{
								for (j4 = 0; j4 < 4; ++j4)
								{
									for (k4 = -1; k4 < 4; ++k4)
									{
										l4 = i2 + (j4 - 1) * k3 + i4 * l3;
										i5 = i3 + k4;
										int j5 = k2 + (j4 - 1) * l3 - i4 * k3;

										if (k4 < 0 && !this.worldServer.getBlock(l4, i5, j5).getMaterial().isSolid() || k4 >= 0 && !this.worldServer.isAirBlock(l4, i5, j5))
										{
											continue label274;
										}
									}
								}
							}

							d3 = (double) i3 + 0.5D - entity.posY;
							d4 = d1 * d1 + d3 * d3 + d2 * d2;

							if (d0 < 0.0D || d4 < d0)
							{
								d0 = d4;
								l = i2;
								i1 = i3;
								j1 = k2;
								k1 = j3 % 4;
							}
						}
					}
				}
			}
		}

		if (d0 < 0.0D)
		{
			for (i2 = i - b0; i2 <= i + b0; ++i2)
			{
				d1 = (double) i2 + 0.5D - entity.posX;

				for (k2 = k - b0; k2 <= k + b0; ++k2)
				{
					d2 = (double) k2 + 0.5D - entity.posZ;
					label222:

					for (i3 = this.worldServer.getActualHeight() - 1; i3 >= 0; --i3)
					{
						if (this.worldServer.isAirBlock(i2, i3, k2))
						{
							while (i3 > 0 && this.worldServer.isAirBlock(i2, i3 - 1, k2))
							{
								--i3;
							}

							for (j3 = l1; j3 < l1 + 2; ++j3)
							{
								k3 = j3 % 2;
								l3 = 1 - k3;

								for (i4 = 0; i4 < 4; ++i4)
								{
									for (j4 = -1; j4 < 4; ++j4)
									{
										k4 = i2 + (i4 - 1) * k3;
										l4 = i3 + j4;
										i5 = k2 + (i4 - 1) * l3;

										if (j4 < 0 && !this.worldServer.getBlock(k4, l4, i5).getMaterial().isSolid() || j4 >= 0 && !this.worldServer.isAirBlock(k4, l4, i5))
										{
											continue label222;
										}
									}
								}

								d3 = (double) i3 + 0.5D - entity.posY;
								d4 = d1 * d1 + d3 * d3 + d2 * d2;

								if (d0 < 0.0D || d4 < d0)
								{
									d0 = d4;
									l = i2;
									i1 = i3;
									j1 = k2;
									k1 = j3 % 2;
								}
							}
						}
					}
				}
			}
		}

		int k5 = l;
		int j2 = i1;
		k2 = j1;
		int l5 = k1 % 2;
		int l2 = 1 - l5;

		if (k1 % 4 >= 2)
		{
			l5 = -l5;
			l2 = -l2;
		}

		boolean flag;

		if (d0 < 0.0D)
		{
			if (i1 < 70)
			{
				i1 = 70;
			}

			if (i1 > this.worldServer.getActualHeight() - 10)
			{
				i1 = this.worldServer.getActualHeight() - 10;
			}

			j2 = i1;

			for (i3 = -1; i3 <= 1; ++i3)
			{
				for (j3 = 1; j3 < 3; ++j3)
				{
					for (k3 = -1; k3 < 3; ++k3)
					{
						l3 = k5 + (j3 - 1) * l5 + i3 * l2;
						i4 = j2 + k3;
						j4 = k2 + (j3 - 1) * l2 - i3 * l5;
						flag = k3 < 0;
						this.worldServer.setBlock(l3, i4, j4, flag ? AliensVsPredator.blocks().blockEngineerShipFloor : Blocks.air);
					}
				}
			}
		}

		for (i3 = 0; i3 < 4; ++i3)
		{
			for (j3 = 0; j3 < 4; ++j3)
			{
				for (k3 = -1; k3 < 4; ++k3)
				{
					l3 = k5 + (j3 - 1) * l5;
					i4 = j2 + k3;
					j4 = k2 + (j3 - 1) * l2;
					flag = j3 == 0 || j3 == 3 || k3 == -1 || k3 == 3;
					this.worldServer.setBlock(l3, i4, j4, (Block) (flag ? AliensVsPredator.blocks().blockEngineerShipFloor : AliensVsPredator.blocks().blockPortalVarda), 0, 2);
				}
			}

			for (j3 = 0; j3 < 4; ++j3)
			{
				for (k3 = -1; k3 < 4; ++k3)
				{
					l3 = k5 + (j3 - 1) * l5;
					i4 = j2 + k3;
					j4 = k2 + (j3 - 1) * l2;
					this.worldServer.notifyBlocksOfNeighborChange(l3, i4, j4, this.worldServer.getBlock(l3, i4, j4));
				}
			}
		}

		return true;
	}
}
