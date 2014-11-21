package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.arisux.airi.engine.WorldEngine;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.ExtendedEntityPlayer;
import com.arisux.avp.packets.client.PacketBroadcastRadiusClientUpdate;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.*;

public class PacketBroadcastRadiusServerUpdate implements IMessage, IMessageHandler<PacketBroadcastRadiusServerUpdate, PacketBroadcastRadiusServerUpdate>
{
	public String username;
	public int broadcastRadius;

	public PacketBroadcastRadiusServerUpdate()
	{
		;
	}

	public PacketBroadcastRadiusServerUpdate(int broadcastRadius, String username)
	{
		this.broadcastRadius = broadcastRadius;
		this.username = username;
	}
	
	@Override public void fromBytes(ByteBuf buf)
	{
		this.broadcastRadius = buf.readInt();
		this.username = ByteBufUtils.readUTF8String(buf);
	}

	@Override public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.broadcastRadius);
		ByteBufUtils.writeUTF8String(buf, this.username);
	}

	@Override public PacketBroadcastRadiusServerUpdate onMessage(PacketBroadcastRadiusServerUpdate packet, MessageContext ctx)
	{
		EntityPlayer targetPlayer = WorldEngine.Entities.Players.getPlayerForUsername(ctx.getServerHandler().playerEntity.worldObj, packet.username);
		
		if (targetPlayer != null)
		{
			ExtendedEntityPlayer extendedTargetPlayer = (ExtendedEntityPlayer) targetPlayer.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);
			extendedTargetPlayer.setBroadcastRadius(packet.broadcastRadius);
			AliensVsPredator.instance().network.sendToAll(new PacketBroadcastRadiusClientUpdate(packet.broadcastRadius, packet.username));
		}
		
		return null;
	}
}
