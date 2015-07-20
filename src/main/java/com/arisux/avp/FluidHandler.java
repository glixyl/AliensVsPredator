package com.arisux.avp;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import com.arisux.airi.lib.ModUtil.IBHandler;
import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.airi.lib.interfaces.IMod;
import com.arisux.avp.event.BucketHandler;

import cpw.mods.fml.common.event.FMLInitializationEvent;

public class FluidHandler extends IBHandler implements IInitializable
{
	public FluidHandler()
	{
		super(AliensVsPredator.instance());
	}

	public Fluid
		fluidBlackGoo = new Fluid("blackGoo").setUnlocalizedName("blackGoo");

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		FluidRegistry.registerFluid(fluidBlackGoo);
		FluidContainerRegistry.registerFluidContainer(fluidBlackGoo, new ItemStack(AliensVsPredator.items().blackGooBucket), new ItemStack(Items.bucket));
		BucketHandler.INSTANCE.buckets.put(AliensVsPredator.blocks().blockBlackGoo, AliensVsPredator.items().blackGooBucket);
	}
}
