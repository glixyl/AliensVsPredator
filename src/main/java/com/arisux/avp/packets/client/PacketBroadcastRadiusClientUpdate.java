package com.arisux.avp.packets.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import com.arisux.airi.lib.PlayerLib;
import com.arisux.avp.entities.ExtendedEntityPlayer;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketBroadcastRadiusClientUpdate implements IMessage, IMessageHandler<PacketBroadcastRadiusClientUpdate, PacketBroadcastRadiusClientUpdate>
{
	public String username;
	public int broadcastRadius;

	public PacketBroadcastRadiusClientUpdate()
	{
		;
	}

	public PacketBroadcastRadiusClientUpdate(int broadcastRadius, String username)
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

	@Override public PacketBroadcastRadiusClientUpdate onMessage(PacketBroadcastRadiusClientUpdate packet, MessageContext ctx)
	{
		EntityPlayer targetPlayer = PlayerLib.getPlayerForUsername(Minecraft.getMinecraft().thePlayer.worldObj, packet.username);
		
		if (targetPlayer != null)
		{
			ExtendedEntityPlayer extendedTargetPlayer = (ExtendedEntityPlayer) targetPlayer.getExtendedProperties(ExtendedEntityPlayer.ID_PROPERTIES);
			extendedTargetPlayer.setBroadcastRadius(packet.broadcastRadius);
		}
		return null;
	}
}