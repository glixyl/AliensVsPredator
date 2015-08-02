package com.arisux.avp.block;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.entities.tile.TileEntityBlastdoor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
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
		this.setRenderNormal(false);
		this.setOpaque(false);
	}

	@Override
	public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int side, float subX, float subY, float subZ)
	{
		TileEntityBlastdoor tile = (TileEntityBlastdoor) world.getTileEntity(posX, posY, posZ);

		if (tile != null && (tile.isOperational() || tile.isChild() && tile.getParent() != null && tile.getParent().isOperational()))
		{
			tile.setDoorOpen(!tile.isDoorOpen());

			if (world.isRemote)
			{
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("\u00A77 Blast door \u00A7a" + (tile.isDoorOpen() ? "opened" : "closed") + "."));
			}
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
			te.setup();
		}
	}

	@Override
	public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
	{
		TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);

		if (tileEntity != null && tileEntity instanceof TileEntityBlastdoor)
		{
			TileEntityBlastdoor tile = (TileEntityBlastdoor) tileEntity;

			if (tile.isChild())
			{
				world.setBlockToAir(tile.getParent().xCoord, tile.getParent().yCoord, tile.getParent().zCoord);
				tile.getParent().breakChildren();
			}
			else
			{
				tile.breakChildren();
			}
		}

		world.removeTileEntity(posX, posY, posZ);

		super.breakBlock(world, posX, posY, posZ, blockBroken, meta);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int posX, int posY, int posZ)
	{
		TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);

		if (tileEntity != null && tileEntity instanceof TileEntityBlastdoor)
		{
			TileEntityBlastdoor tile = (TileEntityBlastdoor) tileEntity;

			if (tile.isDoorOpen())
			{
				return null;
			}
			else
			{
				return AxisAlignedBB.getBoundingBox((double) posX + this.minX, (double) posY + this.minY, (double) posZ + this.minZ, (double) posX + this.maxX, (double) posY + this.maxY, (double) posZ + this.maxZ);
			}
		}

		return AxisAlignedBB.getBoundingBox((double) posX + this.minX, (double) posY + this.minY, (double) posZ + this.minZ, (double) posX + this.maxX, (double) posY + this.maxY, (double) posZ + this.maxZ);
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
