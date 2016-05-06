package com.arisux.avp.world;

import com.arisux.airi.lib.WorldUtil.Blocks.CoordData;
import com.arisux.airi.lib.world.Structure;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.world.WorldServer;

public class StructureDerelict extends Structure
{
    @Override
    public String getName()
    {
        return "Derelict";
    }

    public StructureDerelict(WorldServer world, CoordData data)
    {
        super(AliensVsPredator.schematics().derelict, world, data);
    }

    @Override
    public void onProcessing()
    {
        ;
    }

    @Override
    public void onProcessingComplete()
    {
        ;
    }
}
