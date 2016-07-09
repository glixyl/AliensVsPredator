package com.arisux.airi;

import java.util.ArrayList;

import com.arisux.airi.lib.interfaces.IInitializablePre;
import com.arisux.airi.lib.world.Structure;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;

public class LocalEventHandler implements IInitializablePre
{
    private IInitializablePre clientEvents;
    private ArrayList<Structure> structuresInQueue = new ArrayList<Structure>();

    @Override
    public void preInitialize(FMLPreInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);

        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            FMLCommonHandler.instance().bus().register(clientEvents = new ClientSideEvents());
            clientEvents.preInitialize(event);
        }
    }

    @SideOnly(Side.CLIENT)
    public ClientSideEvents getClientEvents()
    {
        return (ClientSideEvents) clientEvents;
    }

    @SubscribeEvent
    public void serverTick(TickEvent.ServerTickEvent event)
    {
        @SuppressWarnings("unchecked")
        ArrayList<Structure> structures = (ArrayList<Structure>) structuresInQueue.clone();

        for (Structure structure : structures)
        {
            if (structure.process())
            {
                this.structuresInQueue.remove(structure);
            }
        }
    }

    public ArrayList<Structure> getStructuresInQueue()
    {
        return structuresInQueue;
    }
}
