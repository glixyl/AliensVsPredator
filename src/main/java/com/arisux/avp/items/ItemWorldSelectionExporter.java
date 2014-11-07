package com.arisux.avp.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.arisux.airi.lib.BlockLib.CoordData;
import com.arisux.airi.lib.ItemTypeLib.HookedItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWorldSelectionExporter extends HookedItem
{
	public ItemWorldSelectionExporter()
	{
		super();
		this.maxStackSize = 1;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, int X, int Y, int Z, EntityPlayer player)
	{
		if (!player.worldObj.isRemote)
		{
			player.worldObj.markBlockForUpdate(X, Y, Z);
			this.writeSelectionDataToStack(new CoordData(X, Y, Z, player.worldObj.getBlock(X, Y, Z)), null, stack);
		}

		return true;
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (!player.worldObj.isRemote)
		{
			this.writeSelectionDataToStack(null, new CoordData((int) x, (int) y, (int) z, player.worldObj.getBlock((int) x, (int) y, (int) z)), stack);
		}

		return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}

	public void writeSelectionDataToStack(CoordData pos1, CoordData pos2, ItemStack stack)
	{
		NBTTagCompound tag = stack.getTagCompound() != null ? stack.getTagCompound() : new NBTTagCompound();
		NBTTagCompound lastSelection1 = (NBTTagCompound) tag.getTag("SelectedPos1");
		NBTTagCompound lastSelection2 = (NBTTagCompound) tag.getTag("SelectedPos2");
		CoordData lastPos1 = lastSelection1 != null ? new CoordData(lastSelection1.getInteger("PosX"), lastSelection1.getInteger("PosY"), lastSelection1.getInteger("PosZ"), lastSelection1.getString("Id")) : null;
		CoordData lastPos2 = lastSelection2 != null ? new CoordData(lastSelection2.getInteger("PosX"), lastSelection2.getInteger("PosY"), lastSelection2.getInteger("PosZ"), lastSelection2.getString("Id")) : null;

		if (pos1 != null)
		{
			tag.setTag("SelectedPos1", pos1.writeToNBT());
		}
		
		if (pos2 != null)
		{
			tag.setTag("SelectedPos2", pos2.writeToNBT());
		}

		stack.setTagCompound(tag);

		if (lastPos1 != null && lastPos2 != null)
		{
			stack.setStackDisplayName(String.format("World Selector - Pos1(%s, %s, %s) - Pos2(%s, %s, %s)", lastPos1.posX, lastPos1.posY, lastPos1.posZ, lastPos2.posX, lastPos2.posY, lastPos2.posZ));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("World Selection Exporter");
	}
}
