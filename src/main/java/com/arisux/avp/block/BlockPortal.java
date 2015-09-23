package com.arisux.avp.block;

import java.util.Random;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.DimensionHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockPortal extends HookedBlock
{
	private int dimensionId;

	public BlockPortal(int dimensionId)
	{
		super(Material.portal);
		setLightOpacity(100);
		setTickRandomly(true);
		this.dimensionId = dimensionId;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int posX, int posY, int posZ)
	{
		return null;
	}

	@Override
	public void randomDisplayTick(World worldObj, int posX, int posY, int posZ, Random rand)
	{
		super.randomDisplayTick(worldObj, posX, posY, posZ, rand);

		for (int i = 6; i > 0; --i)
		{
			worldObj.spawnParticle("enchantmenttable", posX + rand.nextDouble(), posY + rand.nextDouble(), posZ + rand.nextDouble(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int posX, int posY, int posZ, Entity entity)
	{
		if ((entity.ridingEntity == null) && (entity.riddenByEntity == null) && ((entity instanceof EntityPlayerMP)))
		{
			EntityPlayerMP player = (EntityPlayerMP) entity;

			if (player.timeUntilPortal > 0)
			{
				player.timeUntilPortal = 10;
			}
			else if (player.dimension != this.dimensionId)
			{
				player.timeUntilPortal = 10;
				DimensionHandler.teleportPlayerToDimension(player, this.dimensionId);
			}
			else
			{
				player.timeUntilPortal = 10;
				DimensionHandler.teleportPlayerToDimension(player, 0);
			}
		}
	}
}
