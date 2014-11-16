package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;

import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.network.simpleimpl.*;

public class PacketOpenWristbracerGUIServerUpdate implements IMessage, IMessageHandler<PacketOpenWristbracerGUIServerUpdate, PacketOpenWristbracerGUIServerUpdate>
{
	public PacketOpenWristbracerGUIServerUpdate()
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
	public PacketOpenWristbracerGUIServerUpdate onMessage(PacketOpenWristbracerGUIServerUpdate packet, MessageContext ctx)
	{
		FMLNetworkHandler.openGui(ctx.getServerHandler().playerEntity, AliensVsPredator.instance, AliensVsPredator.properties().GUI_WRISTBRACER, ctx.getServerHandler().playerEntity.worldObj, (int) ctx.getServerHandler().playerEntity.posX, (int) ctx.getServerHandler().playerEntity.posY, (int) ctx.getServerHandler().playerEntity.posZ);
		return null;
	}
}
