package com.arisux.avp.entities;

import java.util.ArrayList;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.DamageSources;
import com.arisux.avp.entities.tile.TileEntityCryostasisTube;
import com.arisux.avp.items.ItemFlamethrower;
import com.arisux.avp.items.ItemM240IncineratorUnit;
import com.arisux.avp.items.ItemNostromoFlamethrower;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFlameFX;
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
	protected int flameLife;
	protected int flameIntensity;
	protected int flameSpread;
	protected double flameTailWidth;

	public EntityFlame(World world, EntityLivingBase entityLivingBase)
	{
		super(world, entityLivingBase);
		this.flameLife = 25;
		this.flameSpread = 1;
		this.flameIntensity = 60;
		this.flameTailWidth = 0.02;
	}

	public EntityFlame(World world)
	{
		super(world);
		this.flameLife = 25;
		this.flameSpread = 1;
		this.flameIntensity = 60;
		this.flameTailWidth = 0.02;
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		
		if (this.getThrower() != null && this.getThrower().getHeldItem() != null)
		{
			ItemFlamethrower flamethrower = (ItemFlamethrower) this.getThrower().getHeldItem().getItem();

			if (flamethrower instanceof ItemM240IncineratorUnit)
			{
				this.flameLife = 30;
				this.flameSpread = 1;
			}

			if (flamethrower instanceof ItemNostromoFlamethrower)
			{
				this.flameLife = 12;
				this.flameSpread = 2;
				this.flameTailWidth = 0.6;
			}
		}
	}

	@Override
	public void onUpdate()
	{
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		MovingObjectPosition movingObjectPosition = this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY, this.posZ), Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ));

		if (!this.worldObj.isRemote)
		{
			Entity entityHit = WorldUtil.Entities.getEntityInCoordsRange(worldObj, EntityLiving.class, new CoordData(this), flameSpread, flameSpread);

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

		if (this.ticksExisted >= flameLife)
		{
			this.setDead();
		}

		if (this.worldObj.isRemote)
		{
			for (int x = flameIntensity; x > 0; --x)
			{
				double flameX = 0;
				double flameY = 0;
				double flameZ = 0;

				for (int r = 3; r > 0; r--)
				{
					flameX = flameX + (this.rand.nextDouble() / (flameLife - this.ticksExisted));
					flameY = flameY + (this.rand.nextDouble() / (flameLife - this.ticksExisted));
					flameZ = flameZ + (this.rand.nextDouble() / (flameLife - this.ticksExisted));
				}

				this.spawnFlameParticle(flameX, flameY, flameZ, 0.04F);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void spawnFlameParticle(double flameX, double flameY, double flameZ, float flameGravity)
	{
		Minecraft.getMinecraft().effectRenderer.addEffect(new EntityFlameFX(this.worldObj, this.posX - (flameX / 2), this.posY - (flameY / 2), this.posZ - (flameZ / 2), this.rand.nextGaussian() * flameTailWidth, -this.motionY * (flameGravity * this.ticksExisted) - this.rand.nextGaussian() * flameTailWidth, this.rand.nextGaussian() * flameTailWidth));
	}

	@Override
	protected void onImpact(MovingObjectPosition movingObjectPosition)
	{
		int posX = movingObjectPosition.blockX;
		int posY = movingObjectPosition.blockY;
		int posZ = movingObjectPosition.blockZ;

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
		}

		if (rand.nextInt(10) == 0)
		{
			ArrayList<CoordData> list = WorldUtil.Blocks.getCoordDataInRangeForBlocks(movingObjectPosition.blockX, movingObjectPosition.blockY, movingObjectPosition.blockZ, 1, this.worldObj, AliensVsPredator.blocks().blockCryostasisTube);

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
		
		if (this.getThrower() != null && this.getThrower().getHeldItem() != null)
		{
			ItemFlamethrower flamethrower = (ItemFlamethrower) this.getThrower().getHeldItem().getItem();

			if (flamethrower instanceof ItemM240IncineratorUnit)
			{
				this.setFire(posX, posY, posZ);
			}

			if (flamethrower instanceof ItemNostromoFlamethrower)
			{
				this.setFire(posX, posY, posZ);
				this.setFire(posX + 1, posY, posZ);
				this.setFire(posX - 1, posY, posZ);
				this.setFire(posX, posY, posZ + 1);
				this.setFire(posX, posY, posZ - 1);
			}
		}

		this.setDead();
	}
	
	public void setFire(int posX, int posY, int posZ)
	{
		Block block = this.worldObj.getBlock(posX, posY, posZ);
		
		if (block == Blocks.air)
		{
			this.worldObj.setBlock(posX, posY, posZ, Blocks.fire);
		}
	}
}
