package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.avp.entities.ExtendedEntityPlayer;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.*;

public class PacketInfectPlayerServerUpdate implements IMessage, IMessageHandler<PacketInfectPlayerServerUpdate, PacketInfectPlayerServerUpdate>
{
	public String username;
	public boolean infected;

	public PacketInfectPlayerServerUpdate()
	{
		;
	}

	public PacketInfectPlayerServerUpdate(String username, boolean infected)
	{
		this.username = username;
		this.infected = infected;
	}
	
	@Override public void fromBytes(ByteBuf buf)
	{
		this.username = ByteBufUtils.readUTF8String(buf);
		this.infected = buf.readBoolean();
	}

	@Override public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, this.username);
		buf.writeBoolean(this.infected);
	}

	@Override public PacketInfectPlayerServerUpdate onMessage(PacketInfectPlayerServerUpdate packet, MessageContext ctx)
	{
		EntityPlayer targetPlayer = WorldUtil.Entities.Players.getPlayerForUsername(ctx.getServerHandler().playerEntity.worldObj, packet.username);
		ExtendedEntityPlayer extendedTargetPlayer = (ExtendedEntityPlayer) targetPlayer.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);
		
		extendedTargetPlayer.setImpregnatedTime(extendedTargetPlayer.maxImpregnatedTime);
		extendedTargetPlayer.setPlayerImpregnated(packet.infected);
		
		return null;
	}
}
