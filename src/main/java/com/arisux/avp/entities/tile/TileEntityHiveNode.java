package com.arisux.avp.entities.tile;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.*;

public class TileEntityHiveNode extends TileEntity
{
	public Random rand = new Random();
	public int tick = 20;

	@Override
	public void updateEntity()
	{
		if (this.tick-- <= 1 && AliensVsPredator.instance.settings.doesHiveSpawnMobs())
		{
			double d = (double) this.xCoord;
			double d1 = (double) this.yCoord;
			double d2 = (double) this.zCoord;

			for (int i = 10; i > 0; i--)
			{
				this.worldObj.spawnParticle("reddust", (double) this.xCoord + 0.5D, (double) this.yCoord + this.rand.nextDouble() * (double) this.rand.nextInt(2), (double) this.zCoord + 0.5D, 0.0D, 3.0D, 0.0D);
				this.worldObj.spawnParticle("reddust", (double) this.xCoord + 0.5D, (double) this.yCoord + 1.0D + this.rand.nextDouble() * 2.0D, (double) this.zCoord + 0.5D, 0.0D, 6.0D, 0.0D);
			}

			if (!this.worldObj.isRemote && this.rand.nextInt(256) <= 1)
			{
				Class<? extends Entity> entityClass = null;
				new EntityDrone(this.worldObj);
				int entityAmount;
				byte var16;
				byte var19;
				byte var18;

				switch (this.rand.nextInt(5))
				{
					case 1:
						entityClass = EntityDrone.class;
						EntityDrone var15 = new EntityDrone(this.worldObj);
						var16 = 15;
						var19 = 1;
						var18 = 10;

						if (entityClass != null && this.rand.nextInt(var19) == 1)
						{
							entityAmount = this.worldObj.getEntitiesWithinAABB(entityClass, AxisAlignedBB.getBoundingBox((double) this.xCoord, (double) this.yCoord, (double) this.zCoord, (double) (this.xCoord + 1), (double) (this.yCoord + 1), (double) (this.zCoord + 1)).expand((double) (var16 * 2), 50.0D, (double) (var16 * 2))).size();

							if (entityAmount <= var18)
							{
								var15.setLocationAndAngles(d, d1 + 1.0D, d2, 0.0F, 0.0F);
								this.worldObj.spawnEntityInWorld(var15);
							}
						}

						break;

					case 2:
						entityClass = EntityWarrior.class;
						EntityWarrior var17 = new EntityWarrior(this.worldObj);
						var16 = 30;
						var19 = 5;
						var18 = 5;

						if (entityClass != null && this.rand.nextInt(var19) == 1)
						{
							entityAmount = this.worldObj.getEntitiesWithinAABB(entityClass, AxisAlignedBB.getBoundingBox((double) this.xCoord, (double) this.yCoord, (double) this.zCoord, (double) (this.xCoord + 1), (double) (this.yCoord + 1), (double) (this.zCoord + 1)).expand((double) (var16 * 2), 50.0D, (double) (var16 * 2))).size();

							if (entityAmount <= var18)
							{
								var17.setLocationAndAngles(d, d1 + 1.0D, d2, 0.0F, 0.0F);
								this.worldObj.spawnEntityInWorld(var17);
							}
						}

						break;

					case 3:
						entityClass = EntityPraetorian.class;
						EntityPraetorian var13 = new EntityPraetorian(this.worldObj);
						var16 = 50;
						var19 = 9;
						var18 = 2;

						if (entityClass != null && this.rand.nextInt(var19) == 1)
						{
							entityAmount = this.worldObj.getEntitiesWithinAABB(entityClass, AxisAlignedBB.getBoundingBox((double) this.xCoord, (double) this.yCoord, (double) this.zCoord, (double) (this.xCoord + 1), (double) (this.yCoord + 1), (double) (this.zCoord + 1)).expand((double) (var16 * 2), 50.0D, (double) (var16 * 2))).size();

							if (entityAmount <= var18)
							{
								var13.setLocationAndAngles(d, d1 + 1.0D, d2, 0.0F, 0.0F);
								this.worldObj.spawnEntityInWorld(var13);
							}
						}

						break;

					case 4:
						entityClass = EntitySpitter.class;
						EntitySpitter var14 = new EntitySpitter(this.worldObj);
						var16 = 35;
						var19 = 15;
						var18 = 3;

						if (entityClass != null && this.rand.nextInt(var19) == 1)
						{
							entityAmount = this.worldObj.getEntitiesWithinAABB(entityClass, AxisAlignedBB.getBoundingBox((double) this.xCoord, (double) this.yCoord, (double) this.zCoord, (double) (this.xCoord + 1), (double) (this.yCoord + 1), (double) (this.zCoord + 1)).expand((double) (var16 * 2), 50.0D, (double) (var16 * 2))).size();

							if (entityAmount <= var18)
							{
								var14.setLocationAndAngles(d, d1 + 1.0D, d2, 0.0F, 0.0F);
								this.worldObj.spawnEntityInWorld(var14);
							}
						}

						break;

					case 5:
						entityClass = EntityCrusher.class;
						EntityCrusher entityInstance = new EntityCrusher(this.worldObj);
						var16 = 35;
						var19 = 15;
						var18 = 2;

						if (entityClass != null && this.rand.nextInt(var19) == 1)
						{
							entityAmount = this.worldObj.getEntitiesWithinAABB(entityClass, AxisAlignedBB.getBoundingBox((double) this.xCoord, (double) this.yCoord, (double) this.zCoord, (double) (this.xCoord + 1), (double) (this.yCoord + 1), (double) (this.zCoord + 1)).expand((double) (var16 * 2), 50.0D, (double) (var16 * 2))).size();

							if (entityAmount <= var18)
							{
								entityInstance.setLocationAndAngles(d, d1 + 1.0D, d2, 0.0F, 0.0F);
								this.worldObj.spawnEntityInWorld(entityInstance);
							}
						}
				}
			}

			this.tick = 20;
		}
	}
}
