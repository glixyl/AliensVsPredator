package com.arisux.avp.packets.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.avp.entities.ExtendedEntityPlayer;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.*;

public class PacketChannelClientUpdate implements IMessage, IMessageHandler<PacketChannelClientUpdate, PacketChannelClientUpdate>
{
	public String username, channel;

	public PacketChannelClientUpdate()
	{
		;
	}

	public PacketChannelClientUpdate(String channel, String username)
	{
		this.channel = channel;
		this.username = username;
	}
	
	@Override public void fromBytes(ByteBuf buf)
	{
		this.channel = ByteBufUtils.readUTF8String(buf);
		this.username = ByteBufUtils.readUTF8String(buf);
	}

	@Override public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, this.channel);
		ByteBufUtils.writeUTF8String(buf, this.username);
	}

	@Override public PacketChannelClientUpdate onMessage(PacketChannelClientUpdate packet, MessageContext ctx)
	{
		EntityPlayer targetPlayer = WorldUtil.Entities.Players.getPlayerForUsername(Minecraft.getMinecraft().thePlayer.worldObj, packet.username);
		
		if (targetPlayer != null)
		{
			ExtendedEntityPlayer extendedTargetPlayer = (ExtendedEntityPlayer) targetPlayer.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);
			extendedTargetPlayer.setBroadcastChannel(packet.channel);
		}
		
		return null;
	}
}
