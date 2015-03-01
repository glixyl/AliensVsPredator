package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.extended.ExtendedEntityPlayer;
import com.arisux.avp.packets.client.PacketChannelClientUpdate;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.*;

public class PacketChannelUpdate implements IMessage, IMessageHandler<PacketChannelUpdate, PacketChannelUpdate>
{
	public String username, channel;

	public PacketChannelUpdate()
	{
		;
	}

	public PacketChannelUpdate(String channel, String username)
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

	@Override public PacketChannelUpdate onMessage(PacketChannelUpdate packet, MessageContext ctx)
	{
		EntityPlayer targetPlayer = WorldUtil.Entities.Players.getPlayerForUsername(ctx.getServerHandler().playerEntity.worldObj, packet.username);
		
		if (targetPlayer != null)
		{
			ExtendedEntityPlayer extendedTargetPlayer = (ExtendedEntityPlayer) targetPlayer.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);
			extendedTargetPlayer.setBroadcastChannel(this.channel);
			AliensVsPredator.instance().network.sendToAll(new PacketChannelClientUpdate(packet.channel, packet.username));
		}
		
		return null;
	}
}
