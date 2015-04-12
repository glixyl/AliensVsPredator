package com.arisux.avp.packets.server;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.extended.ExtendedEntityPlayer;
import com.arisux.avp.packets.client.PacketSyncEEPPC;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class PacketSyncEEPPS implements IMessage, IMessageHandler<PacketSyncEEPPS, PacketSyncEEPPS>
{
	public NBTTagCompound tag;
	private int entityId;

	public PacketSyncEEPPS()
	{
		;
	}

	public PacketSyncEEPPS(int entityId, NBTTagCompound tag)
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
	@Override public PacketSyncEEPPS onMessage(PacketSyncEEPPS packet, MessageContext ctx)
	{
		Entity entity = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(packet.entityId);

		if (entity != null)
		{
			ExtendedEntityPlayer extendedPlayer = (ExtendedEntityPlayer) entity.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);

			if (extendedPlayer != null)
			{
				extendedPlayer.loadNBTData(packet.tag);
				AliensVsPredator.network().sendToAll(new PacketSyncEEPPC(entity.getEntityId(), extendedPlayer.asNBTTagCompound()));
			}
		}

		return null;
	}
}
