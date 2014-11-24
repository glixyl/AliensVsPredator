package com.arisux.avp.util;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.util.ResourceLocation;

import com.arisux.airi.lib.util.ModelTexMap;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.mob.model.*;
import com.arisux.avp.entities.mob.render.*;

@SuppressWarnings("all")
public enum PlayerMode
{
	NORMAL(0, new PlayerModeLevelMapping[]{ 
		new PlayerModeLevelMapping(0, null) 
	}),
	MARINE(1, new PlayerModeLevelMapping[]{
		new PlayerModeLevelMapping(0, new ModelTexMap(new ModelBiped(), RenderMarine.resourceLocation))
	}), 
	PREDATOR(2, new PlayerModeLevelMapping[]{
		new PlayerModeLevelMapping(0, new ModelTexMap(new ModelYautja(), RenderYautja.resourceLocation))
	}), 
	XENOMORPH(3, new PlayerModeLevelMapping[]{
		new PlayerModeLevelMapping(1, new ModelTexMap(new ModelOvamorph(), RenderOvamorph.resourceLocation)),
		new PlayerModeLevelMapping(2, new ModelTexMap(new ModelFacehugger(), RenderFacehugger.resourceLocation)),
		new PlayerModeLevelMapping(10, new ModelTexMap(new ModelChestburster(), RenderChestburster.resourceLocation)),
		new PlayerModeLevelMapping(20, new ModelTexMap(new ModelDrone(), new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_DRONE_ADVANCED_BLOOD))),
		new PlayerModeLevelMapping(45, new ModelTexMap(new ModelWarrior(), new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_WARRIOR_BLOOD))),
		new PlayerModeLevelMapping(65, new ModelTexMap(new ModelPraetorian(), new ResourceLocation(AliensVsPredator.properties().TEXTURE_PATH_PRAETORIAN))),
		new PlayerModeLevelMapping(90, new ModelTexMap(new ModelQueen(), RenderQueen.resourceLocation))
	});
	
	public int id;
	public PlayerModeLevelMapping[] assignedLevels;
	
	private PlayerMode(int id, PlayerModeLevelMapping[] assignedLevelModels)
	{
		this.id = id;
		this.assignedLevels = assignedLevelModels;
	}
	
	public PlayerModeLevelMapping getLevelMappingForLevel(int level)
	{
		for (int x = 0; x < assignedLevels.length; x++)
		{
			PlayerModeLevelMapping mapping = assignedLevels[x];

			if (mapping.isLevelReached(level))
			{
				return mapping;
			}
		}
		return null;
	}
}
