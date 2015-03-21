package com.arisux.avp.util;


@SuppressWarnings("all")
public enum PlayerMode
{
	NORMAL(0, new LevelData[] { 
		new LevelData(0)
	}),
	MARINE(1, new LevelData[] {
		new LevelData(0)
	}), 
	PREDATOR(2, new LevelData[] {
		new LevelData(0)
	}), 
	XENOMORPH(3, new LevelData[] {
		new LevelData(0),
		new LevelData(1),
		new LevelData(10),
		new LevelData(20),
		new LevelData(45),
		new LevelData(65),
		new LevelData(90)
	});
	
	public int id;
	public LevelData[] assignedLevels;
	
	PlayerMode(int id, LevelData[] assignedLevelModels)
	{
		this.id = id;
		this.assignedLevels = assignedLevelModels;
	}
	
	public LevelData getLevelMappingForLevel(int level)
	{
		for (int x = assignedLevels.length; x > 0; x--)
		{
			LevelData mapping = assignedLevels[x - 1];

			if (mapping.isLevelReached(level))
			{
				return mapping;
			}
		}
		return null;
	}

	public static PlayerMode get(int id)
	{
		for(PlayerMode mode : values())
		{
			if (mode.id == id)
			{
				return mode;
			}
		}
		
		return null;
	}
}
