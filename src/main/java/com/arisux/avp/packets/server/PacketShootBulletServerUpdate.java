package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;

import com.arisux.avp.entities.EntityBullet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketShootBulletServerUpdate implements IMessage, IMessageHandler<PacketShootBulletServerUpdate, PacketShootBulletServerUpdate>
{
	public double damage;

	public PacketShootBulletServerUpdate()
	{
		;
	}

	public PacketShootBulletServerUpdate(double damage)
	{
		this.damage = damage;
	}

	@Override public void fromBytes(ByteBuf buf)
	{
		this.damage = buf.readDouble();
	}

	@Override public void toBytes(ByteBuf buf)
	{
		buf.writeDouble(damage);
	}

	@Override public PacketShootBulletServerUpdate onMessage(PacketShootBulletServerUpdate packet, MessageContext ctx)
	{
		ctx.getServerHandler().playerEntity.worldObj.spawnEntityInWorld(new EntityBullet(ctx.getServerHandler().playerEntity.worldObj, ctx.getServerHandler().playerEntity, 15.0F, packet.damage));
		
		return null;
	}
}
