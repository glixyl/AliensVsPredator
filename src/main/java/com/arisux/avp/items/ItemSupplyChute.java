package com.arisux.avp.items;

import com.arisux.avp.AliensVsPredator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSupplyChute extends Item{
	
	public ItemSupplyChute(){
		setUnlocalizedName("supplyChute");
		setMaxStackSize(1);
	}
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn){
		if(playerIn.capabilities.isCreativeMode||playerIn.inventory.consumeInventoryItem(this)){
		        if (!worldIn.isRemote)
		         {
		             worldIn.setBlock((int) (playerIn.posX + 1), (int) (playerIn.posY + 80), (int) (playerIn.posZ), AliensVsPredator.blocks().blockSupplies);
		         }
		         return itemStackIn;
		   }
		   return itemStackIn;
	}
}
