package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.arisux.airi.lib.PlayerLib;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.ExtendedEntityPlayer;
import com.arisux.avp.packets.client.PacketChannelClientUpdate;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketChannelServerUpdate implements IMessage, IMessageHandler<PacketChannelServerUpdate, PacketChannelServerUpdate>
{
	public String username, channel;

	public PacketChannelServerUpdate()
	{
		;
	}

	public PacketChannelServerUpdate(String channel, String username)
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

	@Override public PacketChannelServerUpdate onMessage(PacketChannelServerUpdate packet, MessageContext ctx)
	{
		EntityPlayer targetPlayer = PlayerLib.getPlayerForUsername(ctx.getServerHandler().playerEntity.worldObj, packet.username);
		
		if (targetPlayer != null)
		{
			ExtendedEntityPlayer extendedTargetPlayer = (ExtendedEntityPlayer) targetPlayer.getExtendedProperties(ExtendedEntityPlayer.ID_PROPERTIES);
			extendedTargetPlayer.setBroadcastChannel(this.channel);
			AliensVsPredator.INSTANCE.network.sendToAll(new PacketChannelClientUpdate(packet.channel, packet.username));
		}
		
		return null;
	}
}
