package com.arisux.avp.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelBiped;

import com.arisux.avp.entities.mob.model.*;

@SuppressWarnings("serial")
public enum PlayerMode
{
	NORMAL(0, new ArrayList<PlayerModeLevelMapping>(){
		{ new PlayerModeLevelMapping(0, new ModelBiped()); }
	}),
	MARINE(1, new ArrayList<PlayerModeLevelMapping>(){
		{ new PlayerModeLevelMapping(0, new ModelBiped()); }
	}), 
	PREDATOR(2, new ArrayList<PlayerModeLevelMapping>(){
		{ new PlayerModeLevelMapping(0, new ModelYautja()); }
	}), 
	XENOMORPH(3, new ArrayList<PlayerModeLevelMapping>(){
		{ new PlayerModeLevelMapping(0, new ModelOvamorph()); }
		{ new PlayerModeLevelMapping(1, new ModelFacehugger()); }
		{ new PlayerModeLevelMapping(10, new ModelChestburster()); }
		{ new PlayerModeLevelMapping(20, new ModelDrone()); }
		{ new PlayerModeLevelMapping(45, new ModelWarrior()); }
		{ new PlayerModeLevelMapping(65, new ModelPraetorian()); }
		{ new PlayerModeLevelMapping(90, new ModelQueen()); }
	}); 
	
	public int id;
	public List<PlayerModeLevelMapping> assignedLevels;
	
	private PlayerMode(int id, List<PlayerModeLevelMapping> assignedLevelModels)
	{
		this.id = id;
		this.assignedLevels = assignedLevelModels;
	}
}
