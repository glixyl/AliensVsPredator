package com.arisux.avp.block;

import com.arisux.avp.AliensVsPredator;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockSupplies extends BlockFalling
{
	public BlockSupplies(Material material)
	{
		super(material);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer playerIn, int side, float subX, float subY, float subZ)
	{
		InventoryPlayer inv = playerIn.inventory;
		inv.addItemStackToInventory(new ItemStack(AliensVsPredator.items().itemAK47, 1));
		inv.addItemStackToInventory(new ItemStack(AliensVsPredator.items().itemAmmoAR, 64));
		inv.addItemStackToInventory(new ItemStack(AliensVsPredator.items().helmMarine, 1));
		inv.addItemStackToInventory(new ItemStack(AliensVsPredator.items().plateMarine, 1));
		inv.addItemStackToInventory(new ItemStack(AliensVsPredator.items().legsMarine, 1));
		inv.addItemStackToInventory(new ItemStack(AliensVsPredator.items().bootsMarine, 1));
		world.setBlockToAir(x, y, z);

		return true;
	}
}
