package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;

import com.arisux.avp.items.ItemFirearm;

import cpw.mods.fml.common.network.simpleimpl.*;

public class PacketReloadFirearmServerUpdate implements IMessage, IMessageHandler<PacketReloadFirearmServerUpdate, PacketReloadFirearmServerUpdate>
{
	public PacketReloadFirearmServerUpdate()
	{
		;
	}

	@Override public void fromBytes(ByteBuf buf)
	{
		;
	}

	@Override public void toBytes(ByteBuf buf)
	{
		;
	}

	@Override public PacketReloadFirearmServerUpdate onMessage(PacketReloadFirearmServerUpdate message, MessageContext ctx)
	{
		((ItemFirearm) ctx.getServerHandler().playerEntity.inventory.getCurrentItem().getItem()).reload(ctx.getServerHandler().playerEntity);
		
		return null;
	}
}
