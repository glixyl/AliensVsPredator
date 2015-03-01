package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.extended.ExtendedEntityPlayer;
import com.arisux.avp.packets.client.PacketClientBroadcastRadiusUpdate;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.*;

public class PacketBroadcastRadiusUpdate implements IMessage, IMessageHandler<PacketBroadcastRadiusUpdate, PacketBroadcastRadiusUpdate>
{
	public String username;
	public int broadcastRadius;

	public PacketBroadcastRadiusUpdate()
	{
		;
	}

	public PacketBroadcastRadiusUpdate(int broadcastRadius, String username)
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

	@Override public PacketBroadcastRadiusUpdate onMessage(PacketBroadcastRadiusUpdate packet, MessageContext ctx)
	{
		EntityPlayer targetPlayer = WorldUtil.Entities.Players.getPlayerForUsername(ctx.getServerHandler().playerEntity.worldObj, packet.username);
		
		if (targetPlayer != null)
		{
			ExtendedEntityPlayer extendedTargetPlayer = (ExtendedEntityPlayer) targetPlayer.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);
			extendedTargetPlayer.setBroadcastRadius(packet.broadcastRadius);
			AliensVsPredator.instance().network.sendToAll(new PacketClientBroadcastRadiusUpdate(packet.broadcastRadius, packet.username));
		}
		
		return null;
	}
}
