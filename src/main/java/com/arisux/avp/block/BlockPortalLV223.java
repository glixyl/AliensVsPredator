package com.arisux.avp.block;

import java.util.Random;

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

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.dimension.varda.TeleporterVarda;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPortalLV223 extends BlockBreakable
{
	public BlockPortalLV223()
	{
		super("avp:blockPortalVarda", Material.portal, false);
		setTickRandomly(true);
	}

	@Override
	public void updateTick(World var1, int var2, int var3, int var4, Random var5)
	{
		super.updateTick(var1, var2, var3, var4, var5);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
	{
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
	{
		super.setBlockBoundsBasedOnState(var1, var2, var3, var4);
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

	public boolean tryToCreatePortal(World var1, int var2, int var3, int var4)
	{
		return false;
	}

	@Override
	public int quantityDropped(Random var1)
	{
		return 0;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int posX, int posY, int posZ, Entity entity)
	{
		if ((entity.ridingEntity == null) && (entity.riddenByEntity == null) && ((entity instanceof EntityPlayerMP)))
		{
			EntityPlayerMP player = (EntityPlayerMP) entity;
			MinecraftServer server = MinecraftServer.getServer();

			if (player.timeUntilPortal > 0)
			{
				player.timeUntilPortal = 10;
			} else if (player.dimension != AliensVsPredator.properties().DIMENSION_ID_LV223)
			{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, AliensVsPredator.properties().DIMENSION_ID_LV223, new TeleporterVarda(server.worldServerForDimension(AliensVsPredator.properties().DIMENSION_ID_LV223)));
			} else
			{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterVarda(server.worldServerForDimension(1)));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int posX, int posY, int posZ, int side)
	{
		return true;
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
		;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
	{
		return super.getPickBlock(target, world, x, y, z);
	}
}