package org.avp.block;

import java.util.Random;

import org.avp.entities.EntitySupplyChute;
import org.avp.entities.tile.TileEntitySupplyCrate;

import com.arisux.airi.lib.interfaces.IRotatable;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSupplyCrate extends BlockFalling
{
	public BlockSupplyCrate()
	{
		super(Material.iron);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntitySupplyCrate();
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}

	@Override
	public int getRenderType()
	{
		return -1;
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
	public int tickRate(World world)
	{
		return 2;
	}

	@Override
	public void updateTick(World world, int posX, int posY, int posZ, Random random)
	{
		if (!world.isRemote)
		{
			if (canFallBelow(world, posX, posY - 1, posZ) && posY >= 0)
			{
				byte b0 = 32;

				if (!fallInstantly && world.checkChunksExist(posX - b0, posY - b0, posZ - b0, posX + b0, posY + b0, posZ + b0))
				{
					EntitySupplyChute crate = new EntitySupplyChute(world, (double) ((float) posX + 0.5F), (double) ((float) posY + 0.5F), (double) ((float) posZ + 0.5F), this, world.getBlockMetadata(posX, posY, posZ));
					this.onStartFalling(crate);
					world.spawnEntityInWorld(crate);
				}
			}
			else
			{
				world.setBlockToAir(posX, posY, posZ);

				while (canFallBelow(world, posX, posY - 1, posZ) && posY > 0)
				{
					--posY;
				}

				if (posY > 0)
				{
					world.setBlock(posX, posY, posZ, this);
				}
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int side, float blockX, float blockY, float blockZ)
	{
		TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);

		if (tileEntity != null && tileEntity instanceof TileEntitySupplyCrate)
		{
			TileEntitySupplyCrate supplyCrate = (TileEntitySupplyCrate) tileEntity;

			if (!player.isSneaking())
			{
				supplyCrate.openGui(player);
			}
			else
			{
				supplyCrate.setOpen(!supplyCrate.isOpen());
			}
		}

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int posX, int posY, int posZ, EntityLivingBase placer, ItemStack itemstack)
	{
		TileEntity tile = world.getTileEntity(posX, posY, posZ);

		if (tile != null && tile instanceof IRotatable && placer != null)
		{
			IRotatable rotatable = (IRotatable) tile;
			rotatable.setDirection(getFacing(placer));
		}
	}

	public static ForgeDirection getFacing(Entity entity)
	{
		int dir = MathHelper.floor_double((entity.rotationYaw / 90) + 0.5) & 3;
		return ForgeDirection.VALID_DIRECTIONS[Direction.directionToFacing[dir]];
	}
}
