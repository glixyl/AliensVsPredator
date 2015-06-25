package com.arisux.avp.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockTypes.HookedBlockContainer;
import com.arisux.avp.entities.tile.PoweredTileEntity;
import com.arisux.avp.entities.tile.TileEntityPowerline;

public class BlockPowerline extends HookedBlockContainer
{
	public BlockPowerline(Material material)
	{
		super(material);
		this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		this.setTickRandomly(true);
	}

	@Override
	public void updateTick(World world, int posX, int posY, int posZ, Random rand)
	{
		super.updateTick(world, posX, posY, posZ, rand);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg)
	{
		;
	}
	
	@Override
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
    {
		TileEntityPowerline pte = (TileEntityPowerline) world.getTileEntity(x, y, z);
		TileEntity te = world.getTileEntity(tileX, tileY, tileZ);
		if(te instanceof TileEntityPowerline){
			TileEntityPowerline pe = (TileEntityPowerline) te;
			if(pe.state == true && pte.state == false){
				pe.state = pte.state;
			}
			if(pe.state == false && pte.state == true){
				pte.state = pe.state;
			}
		}
		if(world.getTileEntity(tileX, tileY, tileZ) == null){
			pte.state = false;
		}
    }
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityPowerline();
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
}