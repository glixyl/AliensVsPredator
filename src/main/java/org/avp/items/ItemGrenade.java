package org.avp.items;

import java.util.List;

import org.avp.entities.EntityGrenade;

import com.arisux.airi.lib.ItemTypes.HookedItem;
import com.arisux.airi.lib.WorldUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGrenade extends HookedItem
{
    private boolean isFlaming;

    public ItemGrenade(boolean isFlaming)
    {
        super();
        this.maxStackSize = 16;
        this.isFlaming = isFlaming;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World worldObj, EntityPlayer entityplayer)
    {
        if (!worldObj.isRemote)
        {
            EntityGrenade grenade = new EntityGrenade(worldObj, entityplayer);
            grenade.setFlaming(this.isFlaming);
            worldObj.spawnEntityInWorld(grenade);
            WorldUtil.Entities.Players.Inventories.consumeItem(entityplayer, this);
        }

        return itemstack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("all")
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        par3List.add("Right click to throw (explodes)");
    }
}
