package com.arisux.avp.block;

import java.util.Random;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.dimension.TeleporterLV;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPortalAcheron extends BlockBreakable
{
	public BlockPortalAcheron()
	{
		super("avp:blockPortalAcheron", Material.portal, false);
		setTickRandomly(true);
	}

	@Override
	public void updateTick(World world, int posX, int posY, int posZ, Random rand)
	{
		super.updateTick(world, posX, posY, posZ, rand);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int posX, int posY, int posZ)
	{
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int posX, int posY, int posZ)
	{
		super.setBlockBoundsBasedOnState(blockAccess, posX, posY, posZ);
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
	public int quantityDropped(Random random)
	{
		return 0;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int posX, int posY, int posZ, Entity entity)
	{
		if (entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) entity;
			MinecraftServer server = MinecraftServer.getServer();

			if (player.timeUntilPortal > 0)
			{
				player.timeUntilPortal = 10;
			} else if (player.dimension != AliensVsPredator.settings().dimensionIdAcheron())
			{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, AliensVsPredator.settings().dimensionIdAcheron(), new TeleporterLV(server.worldServerForDimension(AliensVsPredator.settings().dimensionIdAcheron())));
			} else
			{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterLV(server.worldServerForDimension(1)));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int posX, int posY, int posZ, Random rand)
	{
		super.randomDisplayTick(world, posX, posY, posZ, rand);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition movingObjPos, World world, int posX, int posY, int posZ)
	{
		return super.getPickBlock(movingObjPos, world, posX, posY, posZ);
	}
}