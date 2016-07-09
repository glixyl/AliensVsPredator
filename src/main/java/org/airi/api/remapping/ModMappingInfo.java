package com.arisux.airi.api.remapping;

import com.arisux.airi.lib.ModUtil;

public class ModMappingInfo
{
    public MappingInfo map;

    public ModMappingInfo(String modID)
    {
        this.map = new MappingInfo(modID, modID, this);
    }

    public ModMappingInfo(String oldID, String newID, String classLocation)
    {
        this.map = new MappingInfo(oldID, newID, this);
    }

    public String getClassLocation()
    {
        return ModUtil.getModContainerForId(this.getNewID()).getMod().getClass().getName();
    }

    public String getNewID()
    {
        return this.map.newID;
    }

    public String getOldID()
    {
        return this.map.oldID;
    }

    public static class MappingInfo
    {
        public ModMappingInfo mod;
        public String oldID, newID;

        public MappingInfo(String oldID, String newID, ModMappingInfo mod)
        {
            this.mod = mod;
            this.oldID = oldID;
            this.newID = newID;
        }

        public String getNewID()
        {
            return newID;
        }

        public String getOldID()
        {
            return oldID;
        }

        public ModMappingInfo getMod()
        {
            return mod;
        }
    }
}
