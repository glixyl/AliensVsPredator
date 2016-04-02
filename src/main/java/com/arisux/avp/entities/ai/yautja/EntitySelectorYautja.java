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
			if (currentItem != null) // this check works well and is compatible with the Xenomorph item set bonus, which requires empty hands to climb
			{
				return true;
			}
		}			
		return (entity instanceof EntitySpeciesAlien) || (entity instanceof EntitySpeciesEngineer) || (entity instanceof EntityMarine);
    }
}
