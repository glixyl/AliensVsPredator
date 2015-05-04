package com.arisux.avp.entities.mob;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.airi.lib.WorldUtil.Entities;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityHiveResin;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class EntityDrone extends EntityXenomorph
{
	public int mobType;
	private int resinMultiplier;
	private int resinLevel;

	public EntityDrone(World world)
	{
		super(world);

		this.resinMultiplier = 3;
		this.experienceValue = 100;
		this.setSize(0.8F, 1.8F);
		this.mobType = this.rand.nextInt(2);
		this.setEvolveTo(EntityWarrior.class, 12);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.53D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
	}

	@Override
	protected void dropRareDrop(int par1)
	{
		if (new Random().nextInt(4) == 1)
		{
			this.entityDropItem(new ItemStack(AliensVsPredator.items().helmXeno), 1);
		}

		if (new Random().nextInt(4) == 1)
		{
			this.entityDropItem(new ItemStack(AliensVsPredator.items().plateXeno), 1);
		}

		if (new Random().nextInt(4) == 1)
		{
			this.entityDropItem(new ItemStack(AliensVsPredator.items().legsXeno), 1);
		}

		if (new Random().nextInt(4) == 1)
		{
			this.entityDropItem(new ItemStack(AliensVsPredator.items().bootsXeno), 1);
		}

		super.dropRareDrop(par1);
	}

	@Override
	protected String getHurtSound()
	{
		return AliensVsPredator.properties().SOUND_ALIEN_HURT;
	}

	@Override
	protected String getLivingSound()
	{
		return AliensVsPredator.properties().SOUND_ALIEN_LIVING;
	}

	@Override
	protected String getDeathSound()
	{
		return AliensVsPredator.properties().SOUND_ALIEN_DEATH;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@SuppressWarnings("unchecked")
	public void tickResinLevelAI()
	{
		if (this.rand.nextInt(4) == 0)
		{
			this.resinLevel += 1;
		}

		ArrayList<EntityItem> entityItemList = (ArrayList<EntityItem>) WorldUtil.Entities.getEntitiesInCoordsRange(worldObj, EntityItem.class, new CoordData(this), 8);

		for (EntityItem entityItem : entityItemList)
		{
			if (entityItem.delayBeforeCanPickup <= 0)
			{
				ItemStack stack = entityItem.getDataWatcher().getWatchableObjectItemStack(10);

				if (stack != null && stack.getItem() == AliensVsPredator.items().itemRoyalJelly)
				{
					this.getNavigator().setPath(this.getNavigator().getPathToEntityLiving(entityItem), this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());

					if (this.getDistanceToEntity(entityItem) < 1)
					{
						this.resinLevel += 1000;
						entityItem.setDead();
					}
					break;
				}
			}
		}
	}

	public void tickHiveBuildingAI()
	{
		if (this.getAttackTarget() == null)
		{
			if (this.getHiveSignature() != null && this.worldObj.getWorldTime() % 40 == 0)
			{
				if (this.resinLevel >= 128)
				{
					ArrayList<CoordData> data = WorldUtil.Blocks.getCoordDataInRangeForBlocksExcluding((int) posX, (int) posY, (int) posZ, this.resinMultiplier, this.worldObj, AliensVsPredator.blocks().terrainHiveResin, Blocks.air);

					if (data.size() > 0)
					{
						CoordData coord = data.get(this.rand.nextInt(data.size()));

						if (coord != null)
						{
							Block block = coord.getBlock(this.worldObj);

							if (coord.isAnySurfaceVisible(this.worldObj) && Entities.canCoordBeSeenBy(this, coord))
							{
								this.getNavigator().setPath(this.worldObj.getEntityPathToXYZ(this, coord.posX, coord.posY, coord.posZ, 128, true, true, true, true), 0.8D);
								this.worldObj.setBlock(coord.posX, coord.posY, coord.posZ, AliensVsPredator.blocks().terrainHiveResin);

								TileEntity tileEntity = coord.getTileEntity(this.worldObj);

								if (tileEntity != null && tileEntity instanceof TileEntityHiveResin)
								{
									TileEntityHiveResin resin = (TileEntityHiveResin) tileEntity;
									resin.setHiveSignature(this.getHiveSignature());
									resin.setBlockCovering(block);
								}

								this.resinLevel -= 250;
							}
						}
					}
				}
			}
		}
	}

	public CoordData findValidResinLocation(int attempt)
	{
		attempt++;
		CoordData coord = new CoordData(this).add(-this.resinMultiplier + this.rand.nextInt(this.resinMultiplier * 2), -this.resinMultiplier + this.rand.nextInt(this.resinMultiplier * 2), -this.resinMultiplier + this.rand.nextInt(this.resinMultiplier * 2));
		Block coordBlock = coord.getBlock(this.worldObj);

		if (coordBlock != AliensVsPredator.blocks().terrainHiveResin && coordBlock != Blocks.air && coord.isAnySurfaceVisible(this.worldObj))
		{
			return coord;
		}

		if (attempt > 5)
		{
			this.resinMultiplier += 3;
			return null;
		}

		return this.findValidResinLocation(attempt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);

		this.resinLevel = nbt.getInteger("resinLevel");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);

		nbt.setInteger("resinLevel", this.resinLevel);
	}

	public int getResinLevel()
	{
		return resinLevel;
	}
}
