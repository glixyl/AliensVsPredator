package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;

import com.arisux.avp.entities.EntityGrenade;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketGrenadeLaunchServerUpdate implements IMessage, IMessageHandler<PacketGrenadeLaunchServerUpdate, PacketGrenadeLaunchServerUpdate>
{
	public PacketGrenadeLaunchServerUpdate()
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

	@Override public PacketGrenadeLaunchServerUpdate onMessage(PacketGrenadeLaunchServerUpdate packet, MessageContext ctx)
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
