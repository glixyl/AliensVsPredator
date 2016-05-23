package org.avp.util;

import com.arisux.airi.lib.client.ModelBaseWrapper;
import com.arisux.airi.lib.client.ModelTexMap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;

public class LevelData
{
    private int level;
    private Achievement achievement;

    @SideOnly(Side.CLIENT)
    private ModelTexMap<? extends ModelBaseWrapper> modelTexMap;

    public LevelData(int level)
    {
        this(level, null);
    }

    public LevelData(int level, Achievement achievement)
    {
        this.level = level;
        this.achievement = achievement;
    }

    public boolean isPlayerLevelReached(EntityPlayer player)
    {
        return player.experienceLevel >= this.level;
    }

    public boolean isLevelReached(int level)
    {
        return level >= this.level;
    }

    public int getLevel()
    {
        return level;
    }

    public ModelTexMap<? extends ModelBaseWrapper> getModelTexMap()
    {
        return modelTexMap;
    }

    public Achievement getAchievement()
    {
        return achievement;
    }

    @SideOnly(Side.CLIENT)
    public LevelData setModelTexMap(ModelTexMap<? extends ModelBaseWrapper> modelTexMap)
    {
        this.modelTexMap = modelTexMap;
        return this;
    }
}
