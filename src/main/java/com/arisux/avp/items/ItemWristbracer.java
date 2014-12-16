package com.arisux.avp.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.Constants;

import com.arisux.airi.lib.ItemTypes.HookedItem;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.inventory.container.ContainerWristbracer;

public class ItemWristbracer extends HookedItem
{
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		player.playSound(AliensVsPredator.properties().SOUND_WEAPON_WRISTBLADES, 1F, 1F);

		if (playersWristbracerContainsBlades(player))
		{
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), getDamageToApply());

			if (!player.worldObj.isRemote && !player.capabilities.isCreativeMode)
			{
				ItemStack bladesStack = getBladesStack(player.getCurrentEquippedItem());
				NBTTagCompound nbt = player.getCurrentEquippedItem().getTagCompound();
				NBTTagList items = player.getCurrentEquippedItem().getTagCompound().getTagList("Items", Constants.NBT.TAG_COMPOUND);

				if (bladesStack != null && !player.worldObj.isRemote)
				{
					for (int slot = 0; slot < items.tagCount(); slot++)
					{
						NBTTagCompound tag = items.getCompoundTagAt(0);

						if (tag.getByte("Slot") == 0)
						{
							items.removeTag(slot);
							bladesStack.writeToNBT(tag);
							tag.setShort("Damage", (short) (bladesStack.getItemDamage() + 1));
							tag.setByte("Slot", (byte) slot);
							items.appendTag(tag);
						}
					}
				}

				nbt.setTag("Items", items);
				player.getCurrentEquippedItem().setTagCompound(nbt);
				((ContainerWristbracer) getNewContainer(player)).saveToNBT();
			}
		}

		return super.onLeftClickEntity(stack, player, entity);
	}

	public Object getNewContainer(EntityPlayer player)
	{
		return new ContainerWristbracer(player);
	}
	
	public static float getDamageToApply()
	{
		return AliensVsPredator.instance().items.YAUTJA_TOOLS.getDamageVsEntity() * 1.5F;
	}

	public static ItemStack getBladesStack(ItemStack itemstack)
	{
		if (itemstack != null && itemstack.getTagCompound() != null)
		{
			NBTTagList items = itemstack.getTagCompound().getTagList("Items", Constants.NBT.TAG_COMPOUND);

			if (items != null)
			{
				for (byte x = 0; x < items.tagCount(); x++)
				{
					ItemStack stack = ItemStack.loadItemStackFromNBT(items.getCompoundTagAt(x));

					if (stack != null && items.getCompoundTagAt(x) != null && items.getCompoundTagAt(x).getByte("Slot") == 0)
					{
						return stack;
					}
				}
			}
		}

		return null;
	}

	public static boolean playersWristbracerContainsBlades(EntityPlayer player)
	{
		return player.getCurrentEquippedItem() != null && ItemWristbracer.getBladesStack(player.getCurrentEquippedItem()) != null && ItemWristbracer.getBladesStack(player.getCurrentEquippedItem()).getItem() == AliensVsPredator.instance().items.itemWristbracerBlades;
	}
}
