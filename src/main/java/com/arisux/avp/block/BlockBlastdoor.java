package com.arisux.avp.block;

import com.arisux.avp.entities.tile.TileEntityBlastdoor;
import com.arisux.avp.items.ItemMaintenanceJack;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
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

public class BlockBlastdoor extends Block
{
	public BlockBlastdoor(Material material)
	{
		super(material);
		setTickRandomly(true);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		return;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int side, float subX, float subY, float subZ)
	{
		TileEntity tile = world.getTileEntity(posX, posY, posZ);

		if (tile != null && tile instanceof TileEntityBlastdoor)
		{
			TileEntityBlastdoor blastdoor = (TileEntityBlastdoor) tile;

			if (blastdoor.isChild() && blastdoor.getParent() != null && canOpen(blastdoor.getParent(), player))
			{
				this.onOpen(blastdoor.getParent(), world, player);
			}
			else if (blastdoor.isParent() && canOpen(blastdoor, player))
			{
				this.onOpen(blastdoor, world, player);
			}
		}
		return true;
	}

	private void onOpen(TileEntityBlastdoor blastdoor, World world, EntityPlayer player)
	{
		if (isOpenedByJack(blastdoor, player))
		{
			blastdoor.setBeingPryedOpen(true);
			blastdoor.setDoorProgress(blastdoor.getDoorProgress() + 0.05F);
			int percentOpen = (int) (((blastdoor.getDoorProgress() >= blastdoor.getMaxDoorPryProgress() ? blastdoor.getMaxDoorPryProgress() : blastdoor.getDoorProgress()) * 100) / blastdoor.getMaxDoorPryProgress());

			ItemMaintenanceJack jack = (ItemMaintenanceJack) player.getCurrentEquippedItem().getItem();
			jack.onPryBlastDoor(player, player.getCurrentEquippedItem());

			if (percentOpen >= 100)
			{
				jack.onOpenBlastDoor(player, player.getCurrentEquippedItem());
			}

			if (world.isRemote)
			{
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("\u00A77 Blast door \u00A7a" + percentOpen + "% open."));
			}
		}
		else
		{
			blastdoor.setOpen(!blastdoor.isOpen());

			if (world.isRemote)
			{
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("\u00A77 Blast door \u00A7a" + (blastdoor.isOpen() ? "opened" : "closed") + "."));
			}
		}
	}

	private boolean isOpenedByJack(TileEntityBlastdoor blastdoor, EntityPlayer player)
	{
		return player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemMaintenanceJack && !blastdoor.isOperational() && !blastdoor.isOpen();
	}

	private boolean canOpen(TileEntityBlastdoor blastdoor, EntityPlayer player)
	{
		return blastdoor.isOperational() || isOpenedByJack(blastdoor, player);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int posX, int posY, int posZ)
	{
		return super.canPlaceBlockAt(world, posX, posY, posZ);
	}

	@Override
	public void onBlockPlacedBy(World world, int posX, int posY, int posZ, EntityLivingBase placer, ItemStack itemstack)
	{
		TileEntity tile = world.getTileEntity(posX, posY, posZ);

		if (tile != null && tile instanceof TileEntityBlastdoor && placer != null)
		{
			TileEntityBlastdoor blastdoor = (TileEntityBlastdoor) tile;

			blastdoor.setDirection(getFacing(placer));

			if (!blastdoor.setup(true))
			{
				world.setBlockToAir(posX, posY, posZ);
			}
		}
	}

	@Override
	public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
	{
		TileEntity tile = world.getTileEntity(posX, posY, posZ);

		if (tile != null && tile instanceof TileEntityBlastdoor)
		{
			TileEntityBlastdoor blastdoor = (TileEntityBlastdoor) tile;

			if (blastdoor.isChild())
			{
				if (blastdoor.getParent() != null)
				{
					world.setBlockToAir(blastdoor.getParent().xCoord, blastdoor.getParent().yCoord, blastdoor.getParent().zCoord);
					blastdoor.getParent().breakChildren();
				}
			}
			else
			{
				blastdoor.breakChildren();
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

			if (tile.isOpen())
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
