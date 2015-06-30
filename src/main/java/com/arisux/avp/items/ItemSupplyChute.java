package com.arisux.avp.items;

import net.minecraft.block.BlockGravel;
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
		             //worldIn.setBlock((int) (playerIn.posX), (int) (playerIn.posY + 40), (int) (playerIn.posZ), BlockGravel());
		         }
		         return itemStackIn;
		   }
		   return itemStackIn;
		}
}
