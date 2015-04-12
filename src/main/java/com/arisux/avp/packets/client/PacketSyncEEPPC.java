package com.arisux.avp.packets.client;

import com.arisux.avp.entities.extended.ExtendedEntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class PacketSyncEEPPC implements IMessage, IMessageHandler<PacketSyncEEPPC, PacketSyncEEPPC>
{
	public NBTTagCompound tag;
	private int entityId;

	public PacketSyncEEPPC()
	{
		;
	}

	public PacketSyncEEPPC(int entityId, NBTTagCompound tag)
	{
		this.entityId = entityId;
		this.tag = tag;
	}

	@Override public void fromBytes(ByteBuf buf)
	{
		this.entityId = buf.readInt();
		this.tag = ByteBufUtils.readTag(buf);
	}

	@Override public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.entityId);
		ByteBufUtils.writeTag(buf, tag);
	}

	@SuppressWarnings("unchecked")
	@Override public PacketSyncEEPPC onMessage(PacketSyncEEPPC packet, MessageContext ctx)
	{
		Entity entity = Minecraft.getMinecraft().thePlayer.worldObj.getEntityByID(packet.entityId);

		if (entity != null)
		{
			ExtendedEntityPlayer extendedPlayer = (ExtendedEntityPlayer) entity.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);

			if (extendedPlayer != null)
			{
				extendedPlayer.loadNBTData(packet.tag);
			}
		}

		return null;
	}
}
