package org.avp.items;

import org.avp.AliensVsPredator;

import com.arisux.airi.lib.ItemTypes.HookedItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSupplyChute extends HookedItem
{

    public ItemSupplyChute()
    {
        setUnlocalizedName("supplyChute");
        setMaxStackSize(1);
    }

    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        if (playerIn.capabilities.isCreativeMode || playerIn.inventory.consumeInventoryItem(this))
        {
            if (!worldIn.isRemote)
            {
                worldIn.setBlock((int) (playerIn.posX + 1), (int) (playerIn.posY + 80), (int) (playerIn.posZ), AliensVsPredator.blocks().blockSupplyCrate);
            }
            return itemStackIn;
        }
        return itemStackIn;
    }
}
