package com.arisux.avp;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;

public class Materials
{
	public static final Material mist = (new MaterialGas(MapColor.airColor));
	
	public static class MaterialGas extends MaterialLiquid
	{
		public MaterialGas(MapColor mapColor)
		{
			super(mapColor);
		}
		
		@Override
		public boolean blocksMovement()
		{
			return false;
		}
	}
}
