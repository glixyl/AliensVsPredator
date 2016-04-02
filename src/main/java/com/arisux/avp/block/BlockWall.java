package com.arisux.avp.block;

import com.arisux.airi.lib.client.render.IconSet;
import com.arisux.airi.lib.enums.IconSides;
import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockWall extends Block
{
	public BlockWall(Material material)
	{
		super(material);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		IconSet iconSet = AliensVsPredator.resources().ICONSET_WALLW;
		IconSides iconSide = IconSides.getSideFor(side);

		switch (iconSide)
		{
			case BOTTOM:
				return iconSet.bottom;
			case TOP:
				return iconSet.top;
			case BACK:
				return iconSet.back;
			case FRONT:
				return iconSet.front;
			case LEFT:
				return iconSet.left;
			case RIGHT:
				return iconSet.right;
			default:
				return iconSet.bottom;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		AliensVsPredator.resources().ICONSET_WALLW.registerIcons(register);
	}
}
