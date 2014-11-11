package com.arisux.avp.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockTypeLib.HookedBlockContainer;
import com.arisux.avp.entities.tile.TileEntityBlastdoor;

public class BlockBlastdoor extends HookedBlockContainer
{
	public BlockBlastdoor(Material material)
	{
		super(material);
		this.disableIcon();
	}
	
	@Override
	public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int side, float subX, float subY, float subZ)
	{
		TileEntityBlastdoor tile = (TileEntityBlastdoor) world.getTileEntity(posX, posY, posZ);
		tile.setDoorOpen(!tile.isDoorOpen());
		
		return super.onBlockActivated(world, posX, posY, posZ, player, side, subX, subY, subZ);
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
