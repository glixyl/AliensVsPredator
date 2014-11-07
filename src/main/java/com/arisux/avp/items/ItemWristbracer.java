package com.arisux.avp.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.inventory.container.ContainerWristbracer;

public class ItemWristbracer extends ItemSword
{
	public ItemWristbracer(ToolMaterial enumtoolmaterial)
	{
		super(enumtoolmaterial);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		player.playSound(AliensVsPredator.INSTANCE.properties.SOUND_WEAPON_WRISTBLADES, 1F, 1F);
		return super.onLeftClickEntity(stack, player, entity);
	}

	public Object getNewContainer(EntityPlayer player)
	{
		return new ContainerWristbracer(player);
	}
}
