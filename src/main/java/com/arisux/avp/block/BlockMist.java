package com.arisux.avp.block;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.Materials;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockMist extends BlockFluidClassic
{
	@SideOnly(Side.CLIENT)
	protected IIcon stillIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon flowingIcon;

	public BlockMist(Fluid fluid, Material material)
	{
		super(fluid, Materials.mist);
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return (side == 0 || side == 1) ? stillIcon : flowingIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		stillIcon = register.registerIcon("avp:mist.still");
		flowingIcon = register.registerIcon("avp:mist.flowing");
		AliensVsPredator.fluids().fluidMist.setIcons(stillIcon, flowingIcon);
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
