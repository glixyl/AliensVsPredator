package com.arisux.avp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockTypes.HookedBlockContainer;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityBlastdoor;

public class BlockBlastdoor extends HookedBlockContainer
{
	public BlockBlastdoor(Material material)
	{
		super(material);
	}

	@Override
	public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int side, float subX, float subY, float subZ)
	{
		TileEntityBlastdoor tile = (TileEntityBlastdoor) world.getTileEntity(posX, posY, posZ);

		if (tile != null)
		{
			tile.setDoorOpen(!tile.isDoorOpen());
		}

		return true;
	}

	@Override
	public int onBlockPlaced(World world, int posX, int posY, int posZ, int side, float subX, float subY, float subZ, int meta)
	{
		return super.onBlockPlaced(world, posX, posY, posZ, side, subX, subY, subZ, meta);
	}

	@Override
	public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
	{
		world.setBlockToAir(posX, posY, posZ);
		world.setBlockToAir(posX + 1, posY, posZ);
		world.setBlockToAir(posX, posY + 1, posZ);
		world.setBlockToAir(posX, posY + 2, posZ);
	
		world.removeTileEntity(posX, posY, posZ);
		
		super.breakBlock(world, posX, posY, posZ, blockBroken, meta);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityBlastdoor();
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
}
