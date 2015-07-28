package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.event.BucketHandlingEvent;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidHandler implements IInitializable
{
	public Fluid fluidBlackGoo = new Fluid("blackGoo").setUnlocalizedName("blackGoo");

	@Override
	public void initialize(FMLInitializationEvent event)
	{
		FluidRegistry.registerFluid(fluidBlackGoo);
		FluidContainerRegistry.registerFluidContainer(fluidBlackGoo, new ItemStack(AliensVsPredator.items().blackGooBucket), new ItemStack(Items.bucket));
		BucketHandlingEvent.INSTANCE.buckets.put(AliensVsPredator.blocks().blockBlackGoo, AliensVsPredator.items().blackGooBucket);
	}
}
