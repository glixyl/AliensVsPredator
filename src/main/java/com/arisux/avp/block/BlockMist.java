package com.arisux.avp.block;

import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockMist extends BlockFluidClassic
{
	public BlockMist()
	{
		super(AliensVsPredator.fluids().fluidMist, AliensVsPredator.materials().mist);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		switch (side)
		{
			case 0:
				return AliensVsPredator.resources().ICONSET_MIST.still;
			case 1:
				return AliensVsPredator.resources().ICONSET_MIST.still;
			default:
				return AliensVsPredator.resources().ICONSET_MIST.flowing;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		AliensVsPredator.resources().ICONSET_MIST.registerIcons(register);
		AliensVsPredator.fluids().fluidMist.setIcons(AliensVsPredator.resources().ICONSET_MIST.still, AliensVsPredator.resources().ICONSET_MIST.flowing);
	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlock(x, y, z).getMaterial().isLiquid())
		{
			return false;
		}

		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z)
	{
		if (world.getBlock(x, y, z).getMaterial().isLiquid())
		{
			return false;
		}

		return super.displaceIfPossible(world, x, y, z);
	}
}
