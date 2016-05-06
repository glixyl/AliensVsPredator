package com.arisux.avp;

import com.arisux.airi.lib.interfaces.IInitializable;
import com.arisux.avp.dimension.acheron.WorldGeneratorAcheron;
import com.arisux.avp.dimension.varda.WorldGeneratorVarda;
import com.arisux.avp.world.WorldGenerator;
import com.arisux.avp.world.WorldGeneratorDerelict;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class WorldHandler implements IInitializable
{
    public static final WorldHandler instance = new WorldHandler();
    private SaveHandler saveHandler;
    private IWorldGenerator worldGeneratorVarda;
    private IWorldGenerator worldGeneratorAcheron;
    private IWorldGenerator worldGeneratorDerelict;

    public WorldHandler()
    {
        this.saveHandler = new SaveHandler();
    }

    @Override
    public void initialize(FMLInitializationEvent event)
    {
        GameRegistry.registerWorldGenerator(new WorldGenerator(), 1);
        GameRegistry.registerWorldGenerator(this.worldGeneratorVarda = new WorldGeneratorVarda(), 1);
        GameRegistry.registerWorldGenerator(this.setWorldGeneratorAcheron(new WorldGeneratorAcheron()), 1);
        GameRegistry.registerWorldGenerator(this.worldGeneratorDerelict = new WorldGeneratorDerelict(), 1);
    }

    public WorldGeneratorVarda getWorldGeneratorVarda()
    {
        return (WorldGeneratorVarda) worldGeneratorVarda;
    }

    public WorldGeneratorDerelict getWorldGeneratorDerelict()
    {
        return (WorldGeneratorDerelict) worldGeneratorDerelict;
    }

    public SaveHandler getSaveHandler()
    {
        return saveHandler;
    }

    public IWorldGenerator getWorldGeneratorAcheron()
    {
        return worldGeneratorAcheron;
    }

    public IWorldGenerator setWorldGeneratorAcheron(IWorldGenerator worldGeneratorAcheron)
    {
        this.worldGeneratorAcheron = worldGeneratorAcheron;
        return worldGeneratorAcheron;
    }
}
