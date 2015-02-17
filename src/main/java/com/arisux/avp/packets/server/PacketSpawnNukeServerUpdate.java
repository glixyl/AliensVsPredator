package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;

import com.arisux.airi.lib.WorldUtil.Entities.Players.Inventories;
import com.arisux.avp.entities.EntityNuke;

import cpw.mods.fml.common.network.simpleimpl.*;

public class PacketSpawnNukeServerUpdate implements IMessage, IMessageHandler<PacketSpawnNukeServerUpdate, PacketSpawnNukeServerUpdate>
{
	public PacketSpawnNukeServerUpdate()
	{
		;
	}

	@Override
	public void toBytes(ByteBuf buffer)
	{
		;
	}

	@Override
	public void fromBytes(ByteBuf buffer)
	{
		;
	}

	@Override public PacketSpawnNukeServerUpdate onMessage(PacketSpawnNukeServerUpdate packet, MessageContext ctx)
	{
		if (ctx.getServerHandler().playerEntity != null)
		{
			EntityNuke nuke = new EntityNuke(ctx.getServerHandler().playerEntity.worldObj);
			nuke.setLocationAndAngles(ctx.getServerHandler().playerEntity.posX, ctx.getServerHandler().playerEntity.posY, ctx.getServerHandler().playerEntity.posZ, 0F, 0F);
			ctx.getServerHandler().playerEntity.worldObj.spawnEntityInWorld(nuke);
			Inventories.consumeItem(ctx.getServerHandler().playerEntity, ctx.getServerHandler().playerEntity.getCurrentEquippedItem().getItem());
		}
		
		return null;
	}
}
