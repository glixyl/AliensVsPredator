package com.arisux.avp.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.arisux.airi.lib.ItemTypeLib.HookedItem;
import com.arisux.airi.lib.PlayerLib;
import com.arisux.airi.lib.WorldLib;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.packets.server.PacketSpawnEntityServerUpdate;

public class ItemEntitySummoner extends HookedItem
{
	private Class<? extends Entity> c;

	public ItemEntitySummoner(String domain, Class<? extends Entity> c)
	{
		super();
		this.c = c;
		this.setDescription("Summoner for " + c.getSimpleName().replace("Entity", ""));
		this.setUnlocalizedName(domain + "summon." + c.getSimpleName());
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		Entity entity = createNewEntity(par2World);
		PlayerLib.consumeItem(par3EntityPlayer, this);

		if (par2World.isRemote && entity != null)
		{
			MovingObjectPosition ray = par3EntityPlayer.rayTrace(50D, 1F);
			AliensVsPredator.INSTANCE.network.sendToServer(new PacketSpawnEntityServerUpdate(ray.blockX, ray.blockY, ray.blockZ, EntityList.getEntityID(entity)));
		}

		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
	}
	
	public Class<? extends Entity> getEntityClass()
	{
		return c;
	}
	
	public Entity createNewEntity(World worldObj)
	{
		return WorldLib.getEntityFromClass(worldObj, c);
	}
	
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		;
	}
}