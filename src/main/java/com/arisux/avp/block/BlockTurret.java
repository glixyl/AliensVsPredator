package com.arisux.avp.block;

import java.util.Random;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityTurret;
import com.arisux.avp.packets.server.PacketAddTuretTarget;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockTurret extends Block
{
	public BlockTurret(Material material)
	{
		super(material);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2F, 1.0F);
		this.setTickRandomly(true);
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
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public void updateTick(World world, int posX, int posY, int posZ, Random rand)
	{
		super.updateTick(world, posX, posY, posZ, rand);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		return new TileEntityTurret();
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}

	@Override
	public void onBlockPlacedBy(World worldObj, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn)
	{
		super.onBlockPlacedBy(worldObj, x, y, z, placer, itemIn);

		TileEntityTurret tile = (TileEntityTurret) worldObj.getTileEntity(x, y, z);

		if (tile != null)
		{
			tile.setDirection(MathHelper.floor_double(((placer.rotationYaw * 4F) / 360F) + 0.5D) & 3);
		}
	}

	@Override
	public boolean onBlockActivated(World worldObj, int xCoord, int yCoord, int zCoord, EntityPlayer player, int side, float subX, float subY, float subZ)
	{
		TileEntityTurret tile = (TileEntityTurret) worldObj.getTileEntity(xCoord, yCoord, zCoord);

		if (tile != null)
		{
			if (!worldObj.isRemote)
			{
				for (int i = 0; i < tile.getDangerousTargets().size(); i++)
				{
					if (tile.getDangerousTargets().get(i) != null)
						AliensVsPredator.network().sendToAll(new PacketAddTuretTarget(xCoord, yCoord, zCoord, EntityList.getEntityID(WorldUtil.Entities.constructEntity(worldObj, tile.getDangerousTargets().get(i)))));
				}
			}
		}

		FMLNetworkHandler.openGui(player, AliensVsPredator.instance(), AliensVsPredator.properties().GUI_TURRET, worldObj, xCoord, yCoord, zCoord);

		return true;
	}

	@Override
	public void onBlockDestroyedByPlayer(World worldObj, int posX, int posY, int posZ, int meta)
	{
		super.onBlockDestroyedByPlayer(worldObj, posX, posY, posZ, meta);

		TileEntityTurret tile = (TileEntityTurret) worldObj.getTileEntity(posX, posY, posZ);

		if (tile != null && tile.getEntity() != null)
		{
			if (!worldObj.isRemote)
			{
				for (int i = 0; i < tile.inventoryAmmo.getSizeInventory(); i++)
				{
					ItemStack stack = tile.inventoryAmmo.getStackInSlot(i);

					if (stack != null)
					{
						EntityItem entityitem = new EntityItem(worldObj, posX, posY, posZ, stack);
						entityitem.delayBeforeCanPickup = 10;
						worldObj.spawnEntityInWorld(entityitem);
					}
				}
				
				for (int i = 0; i < tile.inventoryExpansion.getSizeInventory(); i++)
				{
					ItemStack stack = tile.inventoryExpansion.getStackInSlot(i);

					if (stack != null)
					{
						EntityItem entityitem = new EntityItem(worldObj, posX, posY, posZ, stack);
						entityitem.delayBeforeCanPickup = 10;
						worldObj.spawnEntityInWorld(entityitem);
					}
				}
			}

			tile.getEntity().setDead();
		}
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int posX, int posY, int posZ, Explosion sourceExplosion)
	{
		super.onBlockDestroyedByExplosion(world, posX, posY, posZ, sourceExplosion);
		this.onBlockDestroyedByPlayer(world, posX, posY, posZ, 0);
	}
}
