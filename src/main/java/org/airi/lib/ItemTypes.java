package com.arisux.airi.lib;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTypes
{
    public static class HookedItem extends Item
    {
        private String description, textureLocation;
        private boolean disableIcon;

        public HookedItem()
        {
            this.disableIcon = false;
        }

        public Item setDescription(String desc)
        {
            this.description = StatCollector.translateToLocal(desc);
            return this;
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
        {
            if (description != null)
            {
                for (String line : this.description.split("\n"))
                {
                    par3List.add(line);
                }
            }
        }

        @Override
        public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
        {
            super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
        }

        public String getTextureLocation()
        {
            return Minecraft.getMinecraft().theWorld.getWorldInfo().getWorldTime() % 100L == 0L || this.textureLocation == null ? this.textureLocation = iconString.split(":")[0] + ":" + "textures/items/" + iconString.split(":")[1] + ".png" : textureLocation;
        }

        @Override
        public void registerIcons(IIconRegister iconRegister)
        {
            if (!disableIcon)
            {
                super.registerIcons(iconRegister);
            }
        }

        public HookedItem disableIcon()
        {
            this.disableIcon = true;
            return this;
        }
    }

    public static class HookedItemPickaxe extends ItemPickaxe
    {
        public HookedItemPickaxe(ToolMaterial material)
        {
            super(material);
        }
    }

    public static class HookedItemAxe extends ItemAxe
    {
        public HookedItemAxe(ToolMaterial material)
        {
            super(material);
        }
    }
}
