package com.arisux.avp.event;

import com.arisux.avp.items.ItemFirearm;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EntityTrackingEvent
{
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer entityPlayer = (EntityPlayer) event.entityLiving;

			for (ItemStack itemStack : entityPlayer.inventory.mainInventory)
			{
				if (itemStack.getItem() instanceof ItemFirearm)
				{
					ItemFirearm itemFirearm = (ItemFirearm) itemStack.getItem();
					itemFirearm.setAmmoCount(0);
				}
			}
		}
	}
}
