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
import com.arisux.avp.dimension.lv223.LV223Teleporter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPortalLV223 extends BlockBreakable
{
	public BlockPortalLV223()
	{
		super("", Material.portal, false);
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
		if ((var1.getBlock(var2 - 1, var3, var4) != this) && (var1.getBlock(var2 + 1, var3, var4) != this))
		{
			float var5 = 0.125F;
			float var6 = 0.5F;
			setBlockBounds(0.5F - var5, 0.0F, 0.5F - var6, 0.5F + var5, 1.0F, 0.5F + var6);
		} else
		{
			float var5 = 0.5F;
			float var6 = 0.125F;
			setBlockBounds(0.5F - var5, 0.0F, 0.5F - var6, 0.5F + var5, 1.0F, 0.5F + var6);
		}
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
	public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
	{
		if ((var5.ridingEntity == null) && (var5.riddenByEntity == null) && ((var5 instanceof EntityPlayerMP)))
		{
			EntityPlayerMP var6 = (EntityPlayerMP) var5;
			MinecraftServer var7 = MinecraftServer.getServer();

			if (var6.timeUntilPortal > 0)
			{
				var6.timeUntilPortal = 10;
			} else if (var6.dimension != AliensVsPredator.properties().DIMENSION_ID_LV223)
			{
				var6.timeUntilPortal = 10;
				var6.mcServer.getConfigurationManager().transferPlayerToDimension(var6, AliensVsPredator.properties().DIMENSION_ID_LV223, new LV223Teleporter(var7.worldServerForDimension(AliensVsPredator.properties().DIMENSION_ID_LV223)));
			} else
			{
				var6.timeUntilPortal = 10;
				var6.mcServer.getConfigurationManager().transferPlayerToDimension(var6, 0, new LV223Teleporter(var7.worldServerForDimension(1)));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
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
	public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
	{
		if (var5.nextInt(100) == 0)
		{
			var1.playSoundEffect(var2 + 0.5D, var3 + 0.5D, var4 + 0.5D, "portal.portal", 0.5F, var5.nextFloat() * 0.4F + 0.8F);
		}
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
	{
		return super.getPickBlock(target, world, x, y, z);
	}
}