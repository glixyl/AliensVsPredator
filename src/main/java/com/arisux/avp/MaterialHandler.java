package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class MaterialHandler implements IInitializable
{
	public static MaterialHandler instance = new MaterialHandler();
	private Armors armors = new Armors();
	private Tools tools = new Tools();
	
    public final Material mist = new MaterialTransparent(MapColor.lightBlueColor);
    public final Material blackgoo = new MaterialTransparent(MapColor.blackColor);
    
    public static class Armors
    {
    	public final ArmorMaterial celtic = EnumHelper.addArmorMaterial("celtic", 32, new int[] { 4, 7, 5, 3 }, 9);
    	public final ArmorMaterial chitin = EnumHelper.addArmorMaterial("chitin", 30, new int[] { 2, 7, 5, 3 }, 7);
    	public final ArmorMaterial kevlar = EnumHelper.addArmorMaterial("kevlar", 25, new int[] { 2, 6, 3, 2 }, 5);
    	public final ArmorMaterial pressuresuit = EnumHelper.addArmorMaterial("pressuresuit", 24, new int[] { 2, 4, 3, 2 }, 6);
    }
    
    public static class Tools
    {
    	public final ToolMaterial celtic = EnumHelper.addToolMaterial("celtic", 12, 1430, 9.0F, 8.0F, 9);
    	public final ToolMaterial chitin = EnumHelper.addToolMaterial("chitin", 7, 730, 10.0F, 7.0F, 14);
    }
    
    public Armors armors()
	{
		return armors;
	}
    
    public Tools tools()
	{
		return tools;
	}
	
	@Override
	public void initialize(FMLInitializationEvent event)
	{
		;
	}
}
