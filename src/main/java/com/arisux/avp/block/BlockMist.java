package com.arisux.avp.block;

import com.arisux.airi.lib.client.render.IconSet;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.Materials;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockMist extends BlockFluidClassic
{
	@SideOnly(Side.CLIENT)
	private final IconSet iconSet = AliensVsPredator.resources().ICONSET_MIST;

	public BlockMist(Fluid fluid, Material material)
	{
		super(fluid, Materials.mist);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		switch (side)
		{
			case 0:
				return iconSet.still;
			case 1:
				return iconSet.still;
			default:
				return iconSet.flowing;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		iconSet.registerIcons(register);
		AliensVsPredator.fluids().fluidMist.setIcons(iconSet.still, iconSet.flowing);
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
