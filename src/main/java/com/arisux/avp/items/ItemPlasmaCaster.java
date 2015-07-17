package com.arisux.avp.items;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityPlasma;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

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
		;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		EntityPlasma plasma = new EntityPlasma(world).setPlasmaSize(2F);;

		if (plasma != null)
		{
			float speed = 1.5F * plasma.getPlasmaSize();
			plasma.setLocationAndAngles(player.posX, player.posY + player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
			plasma.motionX = -MathHelper.sin(plasma.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(plasma.rotationPitch / 180.0F * (float) Math.PI) * speed;
			plasma.motionZ = MathHelper.cos(plasma.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(plasma.rotationPitch / 180.0F * (float) Math.PI) * speed;
			plasma.motionY = -MathHelper.sin((plasma.rotationPitch) / 180.0F * (float) Math.PI) * speed;
			plasma.release();
			world.spawnEntityInWorld(plasma);
			world.playSoundEffect(player.posX, player.posY, player.posZ, AliensVsPredator.properties().SOUND_WEAPON_PLASMACASTER, 0.5F, 0.5F);
		}
		
		return super.onItemRightClick(stack, world, player);
	}
}
