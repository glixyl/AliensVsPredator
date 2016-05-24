package org.avp.items;

import java.util.List;

import org.avp.AliensVsPredator;
import org.avp.entities.EntityFlame;

import com.arisux.airi.lib.ItemTypes.HookedItem;
import com.arisux.airi.lib.WorldUtil.Entities.Players;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ItemFlamethrower extends HookedItem
{
    protected Item ammo;

    public ItemFlamethrower(Item ammo)
    {
        super();
        this.maxStackSize = 1;
        this.ammo = ammo;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World worldObj, EntityPlayer entityPlayer)
    {
        if (this.canThrowFlame(worldObj, entityPlayer))
        {
            if (!worldObj.isRemote)
            {
                EntityFlame entity = new EntityFlame(worldObj, entityPlayer);
                entity.setLocationAndAngles(entity.posX, entity.posY - 0.35, entity.posZ, entity.rotationYaw, entity.rotationPitch);
                worldObj.spawnEntityInWorld(entity);
                worldObj.playSoundEffect(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, AliensVsPredator.properties().SOUND_WEAPON_FLAMETHROWER, 0.5F, 0.5F);
            }
        }

        return itemstack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("all")
    public void addInformation(ItemStack itemstack, EntityPlayer entityPlayer, List tooltipList, boolean par4)
    {
        super.addInformation(itemstack, entityPlayer, tooltipList, par4);
        tooltipList.add("Left click to aim. Right click to use.");
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        return true;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, int posX, int posY, int posZ, EntityPlayer player)
    {
        return false;
    }

    public boolean canThrowFlame(World worldObj, EntityPlayer playerIn)
    {
        if (playerIn.inventory.hasItem(this.ammo))
        {
            ItemStack ammoStack = playerIn.inventory.getStackInSlot(Players.Inventories.getSlotForItemIn(this.ammo, playerIn.inventory));

            if (ammoStack != null && ammoStack.getItem() != null)
            {
                if (ammoStack.getCurrentDurability() < ammoStack.getMaxDurability())
                {
                    ammoStack.damageItem(1, playerIn);
                }
                else
                {
                    playerIn.inventory.consumeInventoryItem(ammoStack.getItem());
                }

                return true;
            }
        }
        return false;
    }

    public Item getAmmo()
    {
        return ammo;
    }
}
