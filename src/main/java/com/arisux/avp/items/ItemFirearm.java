package com.arisux.avp.items;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.arisux.airi.lib.*;
import com.arisux.airi.lib.ItemTypes.HookedItem;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.packets.server.PacketShootBulletServerUpdate;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFirearm extends HookedItem
{
	public ItemAmmo itemAmmo;
	public float recoil;
	public double curCooldown, maxCooldown;
	public int curAmmo, maxAmmo, maxReload, curReload;
	public String sound;
	public boolean cancelRightClick = false;

	public ItemFirearm(int maxAmmo, float recoil, double fireRate, int reloadSpeed, ItemAmmo item, String snd)
	{
		super();
		this.maxStackSize = 1;
		this.sound = snd;
		this.recoil = recoil;
		this.itemAmmo = item;
		this.maxAmmo = maxAmmo;
		this.curAmmo = 0;
		this.maxReload = reloadSpeed;
		this.curReload = 0;
		this.maxCooldown = fireRate;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);

		--this.curCooldown;
		--this.curReload;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (cancelRightClick)
		{
			this.cancelRightClick = false;
			return par1ItemStack;
		}

		// if (!par2World.isRemote) AliensVsPredator.instance().network.sendToServer(new PacketAmmoClientUpdate(curAmmo));

		if (par2World.isRemote && this.curAmmo > 0 && this.curCooldown <= 0 && this.curReload <= 0 || par2World.isRemote && par3EntityPlayer.capabilities.isCreativeMode)
		{
			this.renderRecoil();
			this.fixDelay();
		}

		if (par3EntityPlayer.inventory.hasItemStack(new ItemStack(this.itemAmmo)) && this.curAmmo <= 0 && this.curReload <= 0)
		{
			// AliensVsPredator.instance().network.sendToServer(new PacketReloadFirearmServerUpdate());
			this.reload(par3EntityPlayer);
		}

		if (this.curAmmo > 0 && this.curCooldown <= 0 && this.curReload <= 0 || par3EntityPlayer.capabilities.isCreativeMode)
		{
			par2World.playSoundAtEntity(par3EntityPlayer, this.sound, 0.5F, 1.0F);
			if (par3EntityPlayer.capabilities.isCreativeMode)
			{
				AliensVsPredator.instance().network.sendToServer(new PacketShootBulletServerUpdate(this.itemAmmo.getInflictionDamage() * 15));
			}
			else
			{
				AliensVsPredator.instance().network.sendToServer(new PacketShootBulletServerUpdate(this.itemAmmo.getInflictionDamage()));
			}
			this.curCooldown = this.maxCooldown;

			if (!par3EntityPlayer.capabilities.isCreativeMode)
			{
				this.curAmmo--;
			}
		}

		return par1ItemStack;
	}

	public void reload(EntityPlayer player)
	{
		if (player.inventory.hasItemStack(new ItemStack(this.itemAmmo)) && this.curAmmo < this.maxAmmo && this.curReload <= 0)
		{
			WorldUtil.Entities.Players.Inventories.consumeItem(player, this.itemAmmo);

			this.curAmmo = this.maxAmmo;
			this.curReload = this.maxReload;
		}
	}

	public void setCurrentAmmo(int curAmmo)
	{
		this.curAmmo = curAmmo;
	}

	@SideOnly(Side.CLIENT)
	public void renderRecoil()
	{
		Minecraft mc = FMLClientHandler.instance().getClient();
		mc.thePlayer.renderArmPitch -= this.recoil * 40.0F;
		mc.thePlayer.renderArmYaw += this.recoil * 5.0F;
		mc.thePlayer.rotationPitch -= this.recoil * 1.4F;
	}

	@SideOnly(Side.CLIENT)
	public void fixDelay()
	{
		AccessWrapper.setEquippedProgress(0.85F);
		AccessWrapper.setRightClickDelayTimer((int) this.maxCooldown);
	}

	public boolean getCanFire(EntityPlayer player)
	{
		return this.curAmmo > 0 && this.curCooldown <= 0 && this.curReload <= 0 || player.capabilities.isCreativeMode;
	}

	public int getMaxAmmo()
	{
		return maxAmmo;
	}

	public int getCurrentAmmo()
	{
		return curAmmo;
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
	{
		return true;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
	{
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, @SuppressWarnings("rawtypes") List par3List, boolean par4)
	{
		par3List.add("Left click to aim, Right click to fire");
		par3List.add("Press R to reload");
	}

	public int getReloadSpeed()
	{
		return this.maxReload;
	}

	public static class ItemAmmo extends HookedItem
	{
		private float damage;

		public ItemAmmo(float damage)
		{
			super();
			this.damage = damage;
		}

		public float getInflictionDamage()
		{
			return damage;
		}
	}
}
