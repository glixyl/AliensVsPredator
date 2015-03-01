package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;

import com.arisux.avp.entities.EntityGrenade;

import cpw.mods.fml.common.network.simpleimpl.*;

public class PacketLaunchGrenade implements IMessage, IMessageHandler<PacketLaunchGrenade, PacketLaunchGrenade>
{
	public PacketLaunchGrenade()
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

	@Override public PacketLaunchGrenade onMessage(PacketLaunchGrenade packet, MessageContext ctx)
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
