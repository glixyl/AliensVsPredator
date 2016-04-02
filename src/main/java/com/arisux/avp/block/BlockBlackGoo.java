package com.arisux.avp.block;

import com.arisux.airi.lib.client.render.IconSet;
import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockBlackGoo extends BlockFluidClassic
{
	public BlockBlackGoo(Fluid fluid, Material material)
	{
		super(fluid, material);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		switch (side)
		{
			case 0:
				return AliensVsPredator.resources().ICONSET_BLACK_GOO.still;
			case 1:
				return AliensVsPredator.resources().ICONSET_BLACK_GOO.still;
			default:
				return AliensVsPredator.resources().ICONSET_BLACK_GOO.flowing;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		AliensVsPredator.resources().ICONSET_BLACK_GOO.registerIcons(register);
		AliensVsPredator.fluids().fluidBlackGoo.setIcons(AliensVsPredator.resources().ICONSET_BLACK_GOO.still, AliensVsPredator.resources().ICONSET_BLACK_GOO.flowing);
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
