package com.arisux.avp.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.arisux.airi.engine.WorldEngine;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityPlasma;
import com.arisux.avp.entities.ExtendedEntityPlayer;

public class ItemPlasmaCaster extends Item
{
	public ItemPlasmaCaster()
	{
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(50);
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag)
	{
		if (itemstack.getItemDamage() > 0)
		{
			itemstack.damageItem(1, (EntityLiving) entity);
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if (!world.isRemote)
		{
			if (entityplayer.capabilities.isCreativeMode || !entityplayer.capabilities.isCreativeMode && entityplayer.inventory.hasItemStack(WorldEngine.Entities.Players.Inventories.newStack(AliensVsPredator.instance().items.itemEnergy)))
			{
				world.spawnEntityInWorld(new EntityPlasma(world, entityplayer));
				world.playSoundEffect(entityplayer.posX, entityplayer.posY, entityplayer.posZ, AliensVsPredator.properties().SOUND_WEAPON_PLASMACASTER, 0.5F, 0.5F);

				if (!entityplayer.capabilities.isCreativeMode)
				{
					WorldEngine.Entities.Players.Inventories.consumeItem(entityplayer, AliensVsPredator.instance().items.itemEnergy);
				}
			}
		}

		return itemstack;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ)
	{
		ExtendedEntityPlayer extendedPlayer = (ExtendedEntityPlayer) player.getExtendedProperties(ExtendedEntityPlayer.ID_PROPERTIES);
		EntityPlasma plasma;

		if (extendedPlayer.getPlasmaEntityId() == 0)
		{
			plasma = new EntityPlasma(world, player);
			extendedPlayer.setPlasmaEntityId(plasma.getEntityId());
			world.spawnEntityInWorld(plasma);
		}

		plasma = (EntityPlasma) world.getEntityByID(extendedPlayer.getPlasmaEntityId());

		if (plasma != null)
		{
			plasma.setLocationAndAngles(player.posX, player.posY + player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
			plasma.increaseSize();
		}

		return true;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int itemUseCount)
	{
		ExtendedEntityPlayer extendedPlayer = (ExtendedEntityPlayer) player.getExtendedProperties(ExtendedEntityPlayer.ID_PROPERTIES);
		EntityPlasma plasma = (EntityPlasma) world.getEntityByID(extendedPlayer.getPlasmaEntityId());

		if (plasma != null)
		{
			plasma.release();
		}

		extendedPlayer.setPlasmaEntityId(0);

		super.onPlayerStoppedUsing(itemStack, world, player, itemUseCount);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack)
	{
		return 20 * 5;
	}
}
