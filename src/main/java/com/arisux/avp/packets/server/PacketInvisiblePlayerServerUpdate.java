package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketInvisiblePlayerServerUpdate implements IMessage, IMessageHandler<PacketInvisiblePlayerServerUpdate, PacketInvisiblePlayerServerUpdate>
{
	public boolean invisible;

	public PacketInvisiblePlayerServerUpdate()
	{
		;
	}

	public PacketInvisiblePlayerServerUpdate(boolean invisible)
	{
		this.invisible = invisible;
	}

	@Override public void fromBytes(ByteBuf buf)
	{
		this.invisible = buf.readBoolean();
	}

	@Override public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(invisible);
	}

	@Override public PacketInvisiblePlayerServerUpdate onMessage(PacketInvisiblePlayerServerUpdate packet, MessageContext ctx)
	{
		ctx.getServerHandler().playerEntity.setInvisible(packet.invisible);
		return null;
	}
}
