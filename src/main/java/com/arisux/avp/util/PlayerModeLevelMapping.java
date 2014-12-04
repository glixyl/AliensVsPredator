package com.arisux.avp.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;

import com.arisux.airi.lib.render.ModelTexMap;

public class PlayerModeLevelMapping
{
	private int level;
	private ModelTexMap modelTexMap;
	private Achievement achievement;
	
	public PlayerModeLevelMapping(int level, ModelTexMap mappedModelTexture)
	{
		this(level, mappedModelTexture, null);
	}
	
	public PlayerModeLevelMapping(int level, ModelTexMap mappedModelTexture, Achievement achievement)
	{
		this.level = level;
		this.modelTexMap = mappedModelTexture;
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
	
	public ModelTexMap getModelTexMap()
	{
		return modelTexMap;
	}
	
	public Achievement getAchievement()
	{
		return achievement;
	}
}
