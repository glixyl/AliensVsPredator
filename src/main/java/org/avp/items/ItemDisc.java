package org.avp.items;

import com.arisux.airi.lib.ItemTypes.HookedItem;

import org.avp.AliensVsPredator;
import org.avp.entities.EntitySmartDisc;

import com.arisux.airi.lib.WorldUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDisc extends HookedItem
{
    @Override
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int itemInUseCount)
    {
        if (entityplayer.inventory.hasItemStack(new ItemStack(AliensVsPredator.items().itemDisc)))
        {
            int remainingCount = this.getMaxItemUseDuration(itemstack) - itemInUseCount;
            float charge = remainingCount / 20.0F;
            charge = (charge * charge + charge * 2.0F) / 3.0F;

            if (charge >= 0.1F)
            {
                boolean crit = charge > 1.5F ? true : false;
                charge = charge > 1.5F ? 1.5F : charge;
                charge *= 1.5F;

                if (!world.isRemote)
                {
                    EntitySmartDisc entity = new EntitySmartDisc(world, entityplayer, itemstack, charge);
                    entity.setIsCritical(crit);
                    world.spawnEntityInWorld(entity);
                }

                world.playSoundAtEntity(entityplayer, "random.bow", 0.6F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.0F));
                WorldUtil.Entities.Players.Inventories.consumeItem(entityplayer, this);
            }
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemstack)
    {
        return 72000;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.block;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if (entityplayer.inventory.hasItemStack(new ItemStack(AliensVsPredator.items().itemDisc)))
        {
            entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        }

        return itemstack;
    }
}
