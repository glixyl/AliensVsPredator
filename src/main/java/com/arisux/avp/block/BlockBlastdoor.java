package com.arisux.avp.block;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityBlastdoor;
import com.arisux.avp.packets.client.PacketOpenBlastdoor;

import net.minecraft.block.Block;
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

public class BlockBlastdoor extends HookedBlock
{
	public BlockBlastdoor(Material material)
	{
		super(material);
		setTickRandomly(true);
	}

	@Override
	public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int side, float subX, float subY, float subZ)
	{
		TileEntityBlastdoor tile = (TileEntityBlastdoor) world.getTileEntity(posX, posY, posZ);

		if (tile != null && tile.isOperational())
		{
			AliensVsPredator.network().sendToAll(new PacketOpenBlastdoor(!tile.isDoorOpen(), posX, posY, posZ));
			tile.getDescriptionPacket();
		}
		return true;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn)
	{
		if (worldIn.getTileEntity(x, y, z) != null && worldIn.getTileEntity(x, y, z) instanceof TileEntityBlastdoor)
		{
			TileEntityBlastdoor te = (TileEntityBlastdoor) worldIn.getTileEntity(x, y, z);
			te.direction = getFacing(placer);
		}
	}

	@Override
	public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
	{
		if (world.getTileEntity(posX, posY, posZ) != null && world.getTileEntity(posX, posY, posZ) instanceof TileEntityBlastdoor)
		{
			TileEntityBlastdoor te = (TileEntityBlastdoor) world.getTileEntity(posX, posY, posZ);

			if (te.direction != null)
			{
				if (te.direction == ForgeDirection.NORTH || te.direction == ForgeDirection.SOUTH)
				{
					world.setBlockToAir(posX, posY, posZ);
					world.setBlockToAir(posX + 1, posY, posZ);
					world.setBlockToAir(posX + 2, posY, posZ);
					world.setBlockToAir(posX + 3, posY, posZ);
					world.setBlockToAir(posX, posY + 1, posZ);
					world.setBlockToAir(posX, posY + 2, posZ);
					world.setBlockToAir(posX + 1, posY + 2, posZ);
					world.setBlockToAir(posX + 2, posY + 2, posZ);
					world.setBlockToAir(posX + 3, posY + 2, posZ);
					world.setBlockToAir(posX + 3, posY + 1, posZ);
					world.setBlockToAir(posX + 1, posY + 1, posZ);
					world.setBlockToAir(posX + 2, posY + 1, posZ);
				}
				if (te.direction == ForgeDirection.EAST || te.direction == ForgeDirection.WEST)
				{
					world.setBlockToAir(posX, posY, posZ);
					world.setBlockToAir(posX, posY, posZ - 1);
					world.setBlockToAir(posX, posY, posZ - 2);
					world.setBlockToAir(posX, posY, posZ - 3);
					world.setBlockToAir(posX, posY + 1, posZ);
					world.setBlockToAir(posX, posY + 2, posZ);
					world.setBlockToAir(posX, posY + 2, posZ - 1);
					world.setBlockToAir(posX, posY + 2, posZ - 2);
					world.setBlockToAir(posX, posY + 2, posZ - 3);
					world.setBlockToAir(posX, posY + 1, posZ - 3);
					world.setBlockToAir(posX, posY + 1, posZ - 1);
					world.setBlockToAir(posX, posY + 1, posZ - 2);
				}
			}
		}

		world.removeTileEntity(posX, posY, posZ);

		super.breakBlock(world, posX, posY, posZ, blockBroken, meta);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityBlastdoor();
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

	public static ForgeDirection getFacing(Entity entity)
	{
		int dir = MathHelper.floor_double((entity.rotationYaw / 90) + 0.5) & 3;
		return ForgeDirection.VALID_DIRECTIONS[Direction.directionToFacing[dir]];
	}
}
