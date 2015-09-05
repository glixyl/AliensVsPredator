package com.arisux.avp.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.arisux.airi.lib.BlockTypes.HookedBlock;
import com.arisux.avp.entities.tile.TileEntityAmpule;

public class BlockAmpule extends HookedBlock
{
	public BlockAmpule()
	{
		super(Material.iron);
		this.disableIcon();
		this.setLightOpacity(2);
		this.setRenderNormal(false);
		this.setOpaque(false);
		float size = 0.3F;
		this.setBlockBounds(size, 0.0F, size, 1.0F - size, 1F, 1.0F - size);
	}

	@Override
	public void updateTick(World world, int posX, int posY, int posZ, Random random)
	{
		super.updateTick(world, posX, posY, posZ, random);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityAmpule();
	}
	
	@Override
	public void onBlockPlacedBy(World world, int posX, int posY, int posZ, EntityLivingBase placer, ItemStack itemstack)
	{
		TileEntity tile = world.getTileEntity(posX, posY, posZ);

		if (tile != null && tile instanceof TileEntityAmpule && placer != null)
		{
			TileEntityAmpule ampule = (TileEntityAmpule) tile;
			ampule.setDirection(getFacing(placer));
		}
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess blockaccess, int posX, int posY, int posZ, int side)
	{
		return false;
	}
	
	public static ForgeDirection getFacing(Entity entity)
	{
		int dir = MathHelper.floor_double((entity.rotationYaw / 90) + 0.5) & 3;
		return ForgeDirection.VALID_DIRECTIONS[Direction.directionToFacing[dir]];
	}
}
