package org.avp.util;

import org.avp.AliensVsPredator;

import com.arisux.airi.lib.WorldUtil.Entities.Players.Inventories;

import net.minecraft.entity.player.EntityPlayer;

public class Common
{
    public static boolean isPlayerWearingArmorSet(EntityPlayer player)
    {
        return Inventories.getHelmSlotItemStack(player) != null && Inventories.getChestSlotItemStack(player) != null && Inventories.getLegsSlotItemStack(player) != null && Inventories.getBootSlotItemStack(player) != null;
    }

    public static boolean isPlayerWearingCelticArmorSet(EntityPlayer player)
    {
        return isPlayerWearingArmorSet(player) && Inventories.getHelmSlotItemStack(player).getItem() == AliensVsPredator.items().helmTitanium && Inventories.getChestSlotItemStack(player).getItem() == AliensVsPredator.items().plateTitanium && Inventories.getLegsSlotItemStack(player).getItem() == AliensVsPredator.items().legsTitanium && Inventories.getBootSlotItemStack(player).getItem() == AliensVsPredator.items().bootsTitanium;
    }
}
