package com.arisux.avp.packets.server;

import com.arisux.airi.lib.WorldUtil.Entities.Players.Inventories;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityBullet;
import com.arisux.avp.entities.EntityGrenade;

import cpw.mods.fml.common.network.simpleimpl.*;
import io.netty.buffer.ByteBuf;

public class PacketFireAPC implements IMessage, IMessageHandler<PacketFireAPC, PacketFireAPC>
{
	public PacketFireAPC()
	{
		;
	}

	@Override
	public void toBytes(ByteBuf buffer)
	{
		;
	}

	@Override
	public void fromBytes(ByteBuf buffer)
	{
		;
	}

	@Override
	public PacketFireAPC onMessage(PacketFireAPC packet, MessageContext ctx)
	{
		if (ctx.getServerHandler().playerEntity != null && ctx.getServerHandler().playerEntity.worldObj != null)
		{
			EntityGrenade grenade = new EntityGrenade(ctx.getServerHandler().playerEntity.worldObj, ctx.getServerHandler().playerEntity);
			grenade.explodeOnImpact = true;
			ctx.getServerHandler().playerEntity.worldObj.spawnEntityInWorld(grenade);
		}
		return null;
	}
}