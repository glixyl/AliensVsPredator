package com.arisux.avp.packets.client;

import com.arisux.avp.entities.mob.EntitySpeciesAlien;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class PacketJellyLevelUpdate implements IMessage, IMessageHandler<PacketJellyLevelUpdate, PacketJellyLevelUpdate>
{
	public int uuid, jellyLevel;

	public PacketJellyLevelUpdate()
	{
		;
	}

	public PacketJellyLevelUpdate(int jellyLevel, int uuid)
	{
		this.jellyLevel = jellyLevel;
		this.uuid = uuid;
	}
	
	@Override public void fromBytes(ByteBuf buf)
	{
		this.jellyLevel = buf.readInt();
		this.uuid = buf.readInt();
	}

	@Override public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.jellyLevel);
		buf.writeInt(this.uuid);
	}

	@Override public PacketJellyLevelUpdate onMessage(PacketJellyLevelUpdate packet, MessageContext ctx)
	{
		EntitySpeciesAlien alien = ((EntitySpeciesAlien) Minecraft.getMinecraft().thePlayer.worldObj.getEntityByID(packet.uuid));

		if (alien != null)
		{
			alien.setJellyLevel(packet.jellyLevel);
		}
		
		return null;
	}
}
