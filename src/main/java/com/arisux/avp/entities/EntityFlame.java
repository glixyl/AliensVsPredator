package com.arisux.avp.entities;

import java.util.ArrayList;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.DamageSources;
import com.arisux.avp.entities.tile.TileEntityCryostasisTube;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityFlame extends EntityThrowable
{
	public EntityFlame(World world)
	{
		super(world);
		this.setSize(0.5F, 0.5F);
		this.yOffset = this.height / 2.0F;
	}

	public EntityFlame(World world, EntityLivingBase entityLivingBase)
	{
		super(world, entityLivingBase);
	}

	@Override
	public void onUpdate()
	{
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		MovingObjectPosition movingObjectPosition = this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY, this.posZ), Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ));

		if (!this.worldObj.isRemote)
		{
			Entity entityHit = WorldUtil.Entities.getEntityInCoordsRange(worldObj, EntityLiving.class, new CoordData(this), 1, 1);

			if (entityHit != null && !entityHit.isImmuneToFire())
			{
				entityHit.setFire(10);
				entityHit.attackEntityFrom(DamageSources.causeFlamethrowerDamage(this, entityHit), 4F);
			}
		}

		if (movingObjectPosition != null)
		{
			this.onImpact(movingObjectPosition);
		}

		if (this.ticksExisted >= 25)
		{
			this.setDead();
		}

		for (int x = 29; x > 0; --x)
		{
			this.worldObj.spawnParticle("flame", this.posX + this.rand.nextDouble(), this.posY + this.rand.nextDouble(), this.posZ + this.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected void onImpact(MovingObjectPosition movingObjectPosition)
	{
		int posX = movingObjectPosition.blockX;
		int posY = movingObjectPosition.blockY;
		int posZ = movingObjectPosition.blockZ;

		if (this.rand.nextInt(10) == 0)
		{
			ArrayList<CoordData> list = WorldUtil.Blocks.getCoordDataInRangeForBlocks(posX, posY, posZ, 1, this.worldObj, AliensVsPredator.instance().blocks.blockCryostasisTube);

			for (CoordData coord : list)
			{
				TileEntity tile = coord.getTileEntity(this.worldObj);

				if (tile instanceof TileEntityCryostasisTube)
				{
					TileEntityCryostasisTube tube = (TileEntityCryostasisTube) tile;
					tube.setCracked(true);
					
					if (tube.isCracked())
					{
						tube.setShattered(true);
					}
				}
			}
		}

		if (!this.worldObj.isRemote)
		{
			switch (movingObjectPosition.sideHit)
			{
				case 0:
					--posY;
					break;

				case 1:
					++posY;
					break;

				case 2:
					--posZ;
					break;

				case 3:
					++posZ;
					break;

				case 4:
					--posX;
					break;

				case 5:
					++posX;
			}

			this.worldObj.setBlock(posX, posY, posZ, Blocks.fire);
			this.setDead();
		}
	}
}
