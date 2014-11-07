package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;

import com.arisux.avp.items.ItemFirearm;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

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
		((ItemFirearm) ctx.getServerHandler().playerEntity.inventory.getCurrentItem().getItem()).setCurrentAmmo(((ItemFirearm) ctx.getServerHandler().playerEntity.inventory.getCurrentItem().getItem()).getMaxAmmo());

		return null;
	}
}
