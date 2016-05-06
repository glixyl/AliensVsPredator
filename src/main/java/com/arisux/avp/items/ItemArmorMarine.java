package com.arisux.avp.items;

import com.arisux.airi.lib.RenderUtil;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemArmorMarine extends ItemArmor
{
    public ItemArmorMarine(ArmorMaterial material, int renderIndex, int armorType)
    {
        super(material, renderIndex, armorType);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        switch (slot)
        {
            case 0:
                return RenderUtil.getResourcePath(AliensVsPredator.resources().MARINE1);
            case 1:
                return RenderUtil.getResourcePath(AliensVsPredator.resources().MARINE1);
            case 2:
                return RenderUtil.getResourcePath(AliensVsPredator.resources().MARINE2);
            case 3:
                return RenderUtil.getResourcePath(AliensVsPredator.resources().MARINE1);
            default:
                return RenderUtil.getResourcePath(AliensVsPredator.resources().MARINE1);
        }
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
        // if you are wearing the set, you will receive resistance and damageBoost
        if (player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == AliensVsPredator.items().helmMarine && player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == AliensVsPredator.items().plateMarine && player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == AliensVsPredator.items().legsMarine && player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == AliensVsPredator.items().bootsMarine)
        {
            player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 1, 1));
            player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 1, 1));
        }
    }
}
