package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.event.BucketHandlingEvent;
import com.arisux.avp.fluids.FluidBlackGoo;
import com.arisux.avp.fluids.FluidMist;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidHandler implements IInitializable
{
    public static FluidHandler instance = new FluidHandler();

    public Fluid fluidBlackGoo = new FluidBlackGoo().setUnlocalizedName("blackGoo");
    public Fluid fluidMist = new FluidMist().setUnlocalizedName("mist");

    @Override
    public void initialize(FMLInitializationEvent event)
    {
        this.registerFluids(event);
        this.registerFluidContainers(event);
        this.registerBucketEvents(event);
    }

    private void registerFluids(FMLInitializationEvent event)
    {
        FluidRegistry.registerFluid(fluidBlackGoo);
        FluidRegistry.registerFluid(fluidMist);
    }

    private void registerFluidContainers(FMLInitializationEvent event)
    {
        FluidContainerRegistry.registerFluidContainer(fluidBlackGoo, new ItemStack(AliensVsPredator.items().blackGooBucket), new ItemStack(Items.bucket));
        FluidContainerRegistry.registerFluidContainer(fluidMist, new ItemStack(AliensVsPredator.items().mistBucket), new ItemStack(Items.bucket));
    }

    private void registerBucketEvents(FMLInitializationEvent event)
    {
        BucketHandlingEvent.INSTANCE.buckets.put(AliensVsPredator.blocks().blockBlackGoo, AliensVsPredator.items().blackGooBucket);
        BucketHandlingEvent.INSTANCE.buckets.put(AliensVsPredator.blocks().blockMist, AliensVsPredator.items().mistBucket);
    }
}
