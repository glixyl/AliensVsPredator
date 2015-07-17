package com.arisux.avp.packets.server;

import com.arisux.avp.items.ItemFirearm;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketReloadFirearm implements IMessage, IMessageHandler<PacketReloadFirearm, PacketReloadFirearm>
{
	public PacketReloadFirearm()
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

	@Override public PacketReloadFirearm onMessage(PacketReloadFirearm message, MessageContext ctx)
	{
		((ItemFirearm) ctx.getServerHandler().playerEntity.inventory.getCurrentItem().getItem()).reload(ctx.getServerHandler().playerEntity);
		
		return null;
	}
}
