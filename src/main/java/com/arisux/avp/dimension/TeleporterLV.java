package com.arisux.avp.dimension;

import java.util.*;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.*;

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
	public void placeInPortal(Entity entity, double xCoord, double yCoord, double zCoord, float yaw)
	{
		if (this.worldServer.provider.dimensionId != 1)
		{
			if (!placeInExistingPortal(entity, xCoord, yCoord, zCoord, yaw))
			{
				this.makePortal(entity);
				this.placeInExistingPortal(entity, xCoord, yCoord, zCoord, yaw);
			}
		} else
		{
			int entityPosX = MathHelper.floor_double(entity.posX);
			int entityPosY = MathHelper.floor_double(entity.posY) - 1;
			int entityPosZ = MathHelper.floor_double(entity.posZ);
			byte var12 = 1;
			byte var13 = 0;

			if (worldServer.getBlock(entityPosX, entityPosY, entityPosZ) == Blocks.air)
			{
				for (int portalBlockX = -2; portalBlockX <= 2; portalBlockX++)
				{
					for (int portalBlockZ = -2; portalBlockZ <= 2; portalBlockZ++)
					{
						for (int height = -1; height < 3; height++)
						{
							int blockX = entityPosX + portalBlockZ * var12 + portalBlockX * var13;
							int blockY = entityPosY + height;
							int blockZ = entityPosZ + portalBlockZ * var13 - portalBlockX * var12;
							this.worldServer.setBlock(blockX, blockY, blockZ, (height < 0) ? AliensVsPredator.blocks().blockDerelict2 : Blocks.air);
						}
					}
				}

				entity.setLocationAndAngles(entityPosX, entityPosY, entityPosZ, entity.rotationYaw, 0F);
				entity.setVelocity(0D, 0D, 0D);
			} 
			else
			{
				this.placeInPortal(entity, xCoord, yCoord, zCoord, yaw);
			}
		}
	}

	@Override
	public boolean placeInExistingPortal(Entity entity, double var2, double var4, double var6, float var8)
	{
		short var9 = 128;
		double var10 = -1.0D;
		int var12 = 0;
		int var13 = 0;
		int var14 = 0;
		int var15 = MathHelper.floor_double(entity.posX);
		int var16 = MathHelper.floor_double(entity.posZ);
		long var17 = ChunkCoordIntPair.chunkXZ2Int(var15, var16);
		boolean var19 = true;

		if (this.destinationCoordinateCache.containsItem(var17))
		{
			PortalPosition var26 = (PortalPosition) this.destinationCoordinateCache.getValueByKey(var17);
			var10 = 0.0D;
			var12 = var26.posX;
			var13 = var26.posY;
			var14 = var26.posZ;
			var26.lastUpdateTime = this.worldServer.getTotalWorldTime();
			var19 = false;
		} else
		{
			for (int var22 = var15 - var9; var22 <= var15 + var9; var22++)
			{
				double var24 = var22 + 0.5D - entity.posX;

				for (int var46 = var16 - var9; var46 <= var16 + var9; var46++)
				{
					double var27 = var46 + 0.5D - entity.posZ;

					for (int var23 = this.worldServer.getActualHeight() - 1; var23 >= 0; var23--)
					{
						if (this.worldServer.getBlock(var22, var23, var46) != AliensVsPredator.blocks().blockPortalVarda)
							continue;
						while (this.worldServer.getBlock(var22, var23 - 1, var46) == AliensVsPredator.blocks().blockPortalVarda)
						{
							var23--;
						}

						double var20 = var23 + 0.5D - entity.posY;
						double var29 = var24 * var24 + var20 * var20 + var27 * var27;

						if ((var10 >= 0.0D) && (var29 >= var10))
							continue;
						var10 = var29;
						var12 = var22;
						var13 = var23;
						var14 = var46;
					}
				}

			}

		}

		if (var10 >= 0.0D)
		{
			if (var19)
			{
				this.destinationCoordinateCache.add(var17, new PortalPosition(var12, var13, var14, this.worldServer.getTotalWorldTime()));
				this.destinationCoordinateKeys.add(Long.valueOf(var17));
			}

			double var24 = var12 + 0.5D;
			double var47 = var13 + 0.5D;
			double var20 = var14 + 0.5D;
			int var28 = -1;

			if (this.worldServer.getBlock(var12 - 1, var13, var14) == AliensVsPredator.blocks().blockPortalVarda)
			{
				var28 = 2;
			}

			if (this.worldServer.getBlock(var12 + 1, var13, var14) == AliensVsPredator.blocks().blockPortalVarda)
			{
				var28 = 0;
			}

			if (this.worldServer.getBlock(var12, var13, var14 - 1) == AliensVsPredator.blocks().blockPortalVarda)
			{
				var28 = 3;
			}

			if (this.worldServer.getBlock(var12, var13, var14 + 1) == AliensVsPredator.blocks().blockPortalVarda)
			{
				var28 = 1;
			}

			int var23 = entity.getMaxSafePointTries();

			if (var28 > -1)
			{
				int var48 = net.minecraft.util.Direction.rotateOpposite[var28];
				int var30 = net.minecraft.util.Direction.offsetX[var28];
				int var31 = net.minecraft.util.Direction.offsetZ[var28];
				int var32 = net.minecraft.util.Direction.offsetX[var48];
				int var33 = net.minecraft.util.Direction.offsetZ[var48];
				boolean var34 = (!this.worldServer.isAirBlock(var12 + var30 + var32, var13, var14 + var31 + var33)) || (!this.worldServer.isAirBlock(var12 + var30 + var32, var13 + 1, var14 + var31 + var33));
				boolean var35 = (!this.worldServer.isAirBlock(var12 + var30, var13, var14 + var31)) || (!this.worldServer.isAirBlock(var12 + var30, var13 + 1, var14 + var31));

				if ((var34) && (var35))
				{
					var28 = net.minecraft.util.Direction.facingToDirection[var28];
					var48 = net.minecraft.util.Direction.facingToDirection[var48];
					var30 = net.minecraft.util.Direction.offsetX[var28];
					var31 = net.minecraft.util.Direction.offsetZ[var28];
					var32 = net.minecraft.util.Direction.offsetX[var48];
					var33 = net.minecraft.util.Direction.offsetZ[var48];
					int var22 = var12 - var32;
					var24 -= var32;
					int var36 = var14 - var33;
					var20 -= var33;
					var34 = (!this.worldServer.isAirBlock(var22 + var30 + var32, var13, var36 + var31 + var33)) || (!this.worldServer.isAirBlock(var22 + var30 + var32, var13 + 1, var36 + var31 + var33));
					var35 = (!this.worldServer.isAirBlock(var22 + var30, var13, var36 + var31)) || (!this.worldServer.isAirBlock(var22 + var30, var13 + 1, var36 + var31));
				}

				float var49 = 0.5F;
				float var37 = 0.5F;

				if ((!var34) && (var35))
				{
					var49 = 1.0F;
				} else if ((var34) && (!var35))
				{
					var49 = 0.0F;
				} else if ((var34) && (var35))
				{
					var37 = 0.0F;
				}

				var24 += var32 * var49 + var37 * var30;
				var20 += var33 * var49 + var37 * var31;
				float var38 = 0.0F;
				float var39 = 0.0F;
				float var40 = 0.0F;
				float var41 = 0.0F;

				if (var28 == var23)
				{
					var38 = 1.0F;
					var39 = 1.0F;
				} else if (var28 == net.minecraft.util.Direction.facingToDirection[var23])
				{
					var38 = -1.0F;
					var39 = -1.0F;
				} else if (var28 == net.minecraft.util.Direction.facingToDirection[var23])
				{
					var40 = 1.0F;
					var41 = -1.0F;
				} else
				{
					var40 = -1.0F;
					var41 = 1.0F;
				}

				double var42 = entity.motionX;
				double var44 = entity.motionZ;
				entity.motionX = (var42 * var38 + var44 * var41);
				entity.motionZ = (var42 * var40 + var44 * var39);
				entity.rotationYaw = (var8 - var23 * 90 + var28 * 90);
			} else
			{
				entity.motionX = (entity.motionY = entity.motionZ = 0.0D);
			}

			entity.setLocationAndAngles(var24, var47, var20, entity.rotationYaw, entity.rotationPitch);
			return true;
		}

		return false;
	}

	@Override
	public boolean makePortal(Entity var1)
	{
		byte var2 = 16;
		double var3 = -1.0D;
		int var5 = MathHelper.floor_double(var1.posX);
		int var6 = MathHelper.floor_double(var1.posY);
		int var7 = MathHelper.floor_double(var1.posZ);
		int var8 = var5;
		int var9 = var6;
		int var10 = var7;
		int var11 = 0;
		int var12 = this.rand.nextInt(4);

		for (int var13 = var5 - var2; var13 <= var5 + var2; var13++)
		{
			double var14 = var13 + 0.5D - var1.posX;

			for (int var19 = var7 - var2; var19 <= var7 + var2; var19++)
			{
				double var16 = var19 + 0.5D - var1.posZ;

				label430: for (int var18 = this.worldServer.getActualHeight() - 1; var18 >= 0; var18--)
				{
					if (!this.worldServer.isAirBlock(var13, var18, var19))
						continue;
					while ((var18 > 0) && (this.worldServer.isAirBlock(var13, var18 - 1, var19)))
					{
						var18--;
					}

					for (int var20 = var12; var20 < var12 + 4; var20++)
					{
						int var21 = var20 % 2;
						int var22 = 1 - var21;

						if (var20 % 4 >= 2)
						{
							var21 = -var21;
							var22 = -var22;
						}

						for (int var23 = 0; var23 < 3; var23++)
						{
							for (int var24 = 0; var24 < 4; var24++)
							{
								for (int var25 = -1; var25 < 4; var25++)
								{
									int var26 = var13 + (var24 - 1) * var21 + var23 * var22;
									int var27 = var18 + var25;
									int var32 = var19 + (var24 - 1) * var22 - var23 * var21;

									if (((var25 < 0) && (!this.worldServer.getBlock(var26, var27, var32).getMaterial().isSolid())) || ((var25 >= 0) && (!this.worldServer.isAirBlock(var26, var27, var32))))
									{
										break label430;
									}
								}
							}
						}
						double var30 = var18 + 0.5D - var1.posY;
						double var28 = var14 * var14 + var30 * var30 + var16 * var16;

						if ((var3 >= 0.0D) && (var28 >= var3))
							continue;
						var3 = var28;
						var8 = var13;
						var9 = var18;
						var10 = var19;
						var11 = var20 % 4;
					}
				}

			}

		}

		if (var3 < 0.0D)
		{
			for (int var13 = var5 - var2; var13 <= var5 + var2; var13++)
			{
				double var14 = var13 + 0.5D - var1.posX;

				for (int var19 = var7 - var2; var19 <= var7 + var2; var19++)
				{
					double var16 = var19 + 0.5D - var1.posZ;

					label780: for (int var18 = this.worldServer.getActualHeight() - 1; var18 >= 0; var18--)
					{
						if (!this.worldServer.isAirBlock(var13, var18, var19))
							continue;
						while ((var18 > 0) && (this.worldServer.isAirBlock(var13, var18 - 1, var19)))
						{
							var18--;
						}

						for (int var20 = var12; var20 < var12 + 2; var20++)
						{
							int var21 = var20 % 2;
							int var22 = 1 - var21;

							for (int var23 = 0; var23 < 4; var23++)
							{
								for (int var24 = -1; var24 < 4; var24++)
								{
									int var25 = var13 + (var23 - 1) * var21;
									int var26 = var18 + var24;
									int var27 = var19 + (var23 - 1) * var22;

									if (((var24 < 0) && (!this.worldServer.getBlock(var25, var26, var27).getMaterial().isSolid())) || ((var24 >= 0) && (!this.worldServer.isAirBlock(var25, var26, var27))))
									{
										break label780;
									}
								}
							}
							double var30 = var18 + 0.5D - var1.posY;
							double var28 = var14 * var14 + var30 * var30 + var16 * var16;

							if ((var3 >= 0.0D) && (var28 >= var3))
								continue;
							var3 = var28;
							var8 = var13;
							var9 = var18;
							var10 = var19;
							var11 = var20 % 2;
						}
					}
				}

			}

		}

		int var32 = var8;
		int var33 = var9;
		int var19 = var10;
		int var34 = var11 % 2;
		int var35 = 1 - var34;

		if (var11 % 4 >= 2)
		{
			var34 = -var34;
			var35 = -var35;
		}

		if (var3 < 0.0D)
		{
			if (var9 < 70)
			{
				var9 = 70;
			}

			if (var9 > this.worldServer.getActualHeight() - 10)
			{
				var9 = this.worldServer.getActualHeight() - 10;
			}

			var33 = var9 + 30;

			for (int var18 = -1; var18 <= 1; var18++)
			{
				for (int var20 = 1; var20 < 3; var20++)
				{
					for (int var21 = -1; var21 < 3; var21++)
					{
						int var22 = var32 + (var20 - 1) * var34 + var18 * var35;
						int var23 = var33 + var21 + 70;
						int var24 = var19 + (var20 - 1) * var35 - var18 * var34;
						boolean var36 = var21 < 0;
						this.worldServer.setBlock(var22, var23, var24, var36 ? AliensVsPredator.blocks().blockDerelict2 : Blocks.air);
					}
				}
			}
		}

		for (int var18 = 0; var18 < 4; var18++)
		{
			for (int var20 = 0; var20 < 4; var20++)
			{
				for (int var21 = -1; var21 < 4; var21++)
				{
					int var22 = var32 + (var20 - 1) * var34;
					int var23 = var33 + var21 + 70;
					int var24 = var19 + (var20 - 1) * var35;
					boolean var36 = (var20 == 0) || (var20 == 3) || (var21 == -1) || (var21 == 3);
					boolean var37 = var21 == -1;
					this.worldServer.setBlock(var22, var23, var24, var36 ? AliensVsPredator.blocks().blockDerelict2 : AliensVsPredator.blocks().blockPortalVarda);
					this.worldServer.setBlock(var22, var23, var24 + 1, var37 ? AliensVsPredator.blocks().blockDerelict2 : Blocks.air);
					this.worldServer.setBlock(var22, var23, var24 - 1, var37 ? AliensVsPredator.blocks().blockDerelict2 : Blocks.air);
				}

			}

			for (int var20 = 0; var20 < 4; var20++)
			{
				for (int var21 = -1; var21 < 4; var21++)
				{
					int var22 = var32 + (var20 - 1) * var34;
					int var23 = var33 + var21;
					int var24 = var19 + (var20 - 1) * var35;
					this.worldServer.notifyBlocksOfNeighborChange(var22, var23, var24, this.worldServer.getBlock(var22, var23, var24));
				}
			}
		}

		return true;
	}
}