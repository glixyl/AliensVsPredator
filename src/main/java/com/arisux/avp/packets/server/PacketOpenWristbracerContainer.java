package com.arisux.avp.packets.server;

import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketOpenWristbracerContainer implements IMessage, IMessageHandler<PacketOpenWristbracerContainer, PacketOpenWristbracerContainer>
{
	public PacketOpenWristbracerContainer()
	{
		;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		;
	}

	@Override
	public PacketOpenWristbracerContainer onMessage(PacketOpenWristbracerContainer packet, MessageContext ctx)
	{
		FMLNetworkHandler.openGui(ctx.getServerHandler().playerEntity, AliensVsPredator.instance(), AliensVsPredator.properties().GUI_WRISTBRACER, ctx.getServerHandler().playerEntity.worldObj, (int) ctx.getServerHandler().playerEntity.posX, (int) ctx.getServerHandler().playerEntity.posY, (int) ctx.getServerHandler().playerEntity.posZ);
		return null;
	}
}
