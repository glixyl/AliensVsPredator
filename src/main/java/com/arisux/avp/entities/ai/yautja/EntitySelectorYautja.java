package com.arisux.avp.entities.ai.yautja;

import com.arisux.avp.entities.mob.EntitySpeciesYautja;
import com.arisux.avp.entities.mob.EntitySpeciesEngineer;
import com.arisux.avp.entities.mob.EntitySpeciesAlien;
import com.arisux.avp.entities.mob.EntityMarine;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer; 
import net.minecraft.item.ItemStack;

public class EntitySelectorYautja implements IEntitySelector
{
    public static final EntitySelectorYautja instance = new EntitySelectorYautja();

    @Override
    public boolean isEntityApplicable(Entity entity)
    {		
        if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			ItemStack currentItem = player.getCurrentEquippedItem();
			if (currentItem != null) // the problem with both vanilla and modded minecraft
			{                        // is that even blocks can be dangerous, i.e. a lever with TNT, lava bucket
				return true;  // if you have anything in your hands, you will be attacked- which should simplify understanding for players
				              // tried to come up with a safe list array, but it got out of hand quickly and didn't cover mods
			}
		}			
		return (entity instanceof EntitySpeciesAlien) || (entity instanceof EntitySpeciesEngineer) || (entity instanceof EntityMarine);
    }
}
