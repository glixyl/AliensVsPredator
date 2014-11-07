package com.arisux.avp.packets.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

import com.arisux.avp.entities.mob.EntitySpeciesAlien;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketKillCountClientUpdate implements IMessage, IMessageHandler<PacketKillCountClientUpdate, PacketKillCountClientUpdate>
{
	public int uuid, killCount;

	public PacketKillCountClientUpdate()
	{
		;
	}

	public PacketKillCountClientUpdate(int killCount, int uuid)
	{
		this.killCount = killCount;
		this.uuid = uuid;
	}
	
	@Override public void fromBytes(ByteBuf buf)
	{
		this.killCount = buf.readInt();
		this.uuid = buf.readInt();
	}

	@Override public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.killCount);
		buf.writeInt(this.uuid);
	}

	@Override public PacketKillCountClientUpdate onMessage(PacketKillCountClientUpdate packet, MessageContext ctx)
	{
		EntitySpeciesAlien entity = ((EntitySpeciesAlien) Minecraft.getMinecraft().thePlayer.worldObj.getEntityByID(packet.uuid));

		if (entity != null)
		{
			entity.setKilledEntities(packet.killCount);
		}
		return null;
	}
}
