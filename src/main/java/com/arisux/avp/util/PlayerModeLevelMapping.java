package com.arisux.avp.util;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;

public class PlayerModeLevelMapping
{
	private int level;
	private ModelBase playerModel;
	private Achievement achievement;
	
	public PlayerModeLevelMapping(int level, ModelBase playerModel)
	{
		this(level, playerModel, null);
	}
	
	public PlayerModeLevelMapping(int level, ModelBase playerModel, Achievement achievement)
	{
		this.level = level;
		this.playerModel = playerModel;
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
	
	public ModelBase getPlayerModel()
	{
		return playerModel;
	}
	
	public Achievement getAchievement()
	{
		return achievement;
	}
}
