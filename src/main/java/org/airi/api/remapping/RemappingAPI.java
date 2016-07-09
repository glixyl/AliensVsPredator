package com.arisux.airi.api.remapping;

import java.util.ArrayList;

import com.arisux.airi.AIRI;
import com.arisux.airi.api.remapping.ModMappingInfo.MappingInfo;
import com.arisux.airi.lib.interfaces.IInitializablePre;

import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class RemappingAPI implements IInitializablePre
{
    /** Entire mods that will be remapped **/
    private ArrayList<ModMappingInfo> modsToRemap = new ArrayList<ModMappingInfo>();

    /** Individual IDs that will be remapped **/
    private ArrayList<MappingInfo> mappingsToRemap = new ArrayList<MappingInfo>();

    public RemappingAPI()
    {
        ;
    }

    @Override
    public void preInitialize(FMLPreInitializationEvent event)
    {
        ;
    }

    public void onLoadMissingMapping(FMLMissingMappingsEvent event)
    {
        AIRI.logger.warning("Invalid mappings found, searching for new mappings...");

        for (MissingMapping mapping : event.getAll())
        {
            for (ModMappingInfo mod : this.modsToRemap)
            {
                if (mapping.name.contains(mod.getOldID()))
                {
                    try
                    {
                        if (Class.forName(mod.getClassLocation()) != null)
                        {
                            replaceMapping(mapping, mod.getOldID() + ":", mod.getNewID() + ":");
                        }
                    }
                    catch (ClassNotFoundException e)
                    {
                        AIRI.logger.warning("Invalid mappings were detected, but the mod targetted for the new mappings is not present: " + mod.getClassLocation());
                    }
                }
            }

            for (MappingInfo newMapping : this.mappingsToRemap)
            {
                if (mapping.name.contains(newMapping.getOldID()))
                {
                    try
                    {
                        if (Class.forName(newMapping.getMod().getClassLocation()) != null)
                        {
                            replaceMapping(mapping, newMapping.getOldID(), newMapping.getNewID());
                        }
                    }
                    catch (ClassNotFoundException e)
                    {
                        AIRI.logger.warning("Invalid mappings were detected, but the mod targetted for the new mappings is not present: " + newMapping.getMod().getClassLocation());
                    }
                }
            }
        }
    }

    public void registerRemappedMod(String oldID, String newID, String modClassLocation)
    {
        this.modsToRemap.add(new ModMappingInfo(oldID, newID, modClassLocation));
    }

    public void registerMappingInfo(String oldID, String newID, String modId)
    {
        this.mappingsToRemap.add(new MappingInfo(oldID, newID, new ModMappingInfo(modId)));
    }

    public void replaceMapping(MissingMapping mapping, String oldID, String newID)
    {
        String newName = (mapping.name).replace(oldID, newID);

        /** Check for and replace missing item mappings **/
        if (mapping.type == GameRegistry.Type.ITEM)
        {
            AIRI.logger.info("Converting item mapping [" + mapping.name + "@" + mapping.id + "] -> [" + newName + "@" + mapping.id + "]");

            Item item = (Item) Item.itemRegistry.getObject(newName);

            if (item != null)
                mapping.remap(item);
            else
                AIRI.logger.warning("Error converting item mapping [" + mapping.name + "@" + mapping.id + "]");
        }

        /** Check for and replace missing block mappings **/
        if (mapping.type == GameRegistry.Type.BLOCK)
        {
            Block block = (Block) Block.blockRegistry.getObject(newName);

            AIRI.logger.info("Converting block mapping [" + mapping.name + "@" + mapping.id + "] -> [" + newName + "@" + mapping.id + "]");

            if (block != null)
                mapping.remap(block);
            else
                AIRI.logger.warning("Error converting block mapping. [" + mapping.name + "@" + mapping.id + "]");
        }
    }
}
