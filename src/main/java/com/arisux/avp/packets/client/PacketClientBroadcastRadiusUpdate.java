package com.arisux.avp.packets.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.avp.entities.extended.ExtendedEntityPlayer;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.*;

public class PacketClientBroadcastRadiusUpdate implements IMessage, IMessageHandler<PacketClientBroadcastRadiusUpdate, PacketClientBroadcastRadiusUpdate>
{
	public String username;
	public int broadcastRadius;

	public PacketClientBroadcastRadiusUpdate()
	{
		;
	}

	public PacketClientBroadcastRadiusUpdate(int broadcastRadius, String username)
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

	@Override public PacketClientBroadcastRadiusUpdate onMessage(PacketClientBroadcastRadiusUpdate packet, MessageContext ctx)
	{
		EntityPlayer targetPlayer = WorldUtil.Entities.Players.getPlayerForUsername(Minecraft.getMinecraft().thePlayer.worldObj, packet.username);
		
		if (targetPlayer != null)
		{
			ExtendedEntityPlayer extendedTargetPlayer = (ExtendedEntityPlayer) targetPlayer.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);
			extendedTargetPlayer.setBroadcastRadius(packet.broadcastRadius);
		}
		return null;
	}
}
