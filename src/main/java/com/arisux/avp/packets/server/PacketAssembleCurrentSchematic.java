package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.api.AssemblerAPI;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.*;

public class PacketAssembleCurrentSchematic implements IMessage, IMessageHandler<PacketAssembleCurrentSchematic, PacketAssembleCurrentSchematic>
{
	public String schematicId;

	public PacketAssembleCurrentSchematic()
	{
		;
	}

	public PacketAssembleCurrentSchematic(String schematicId)
	{
		this.schematicId = schematicId;
	}

	@Override public void fromBytes(ByteBuf buf)
	{
		this.schematicId = ByteBufUtils.readUTF8String(buf);
	}

	@Override public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, this.schematicId);
	}

	@Override public PacketAssembleCurrentSchematic onMessage(PacketAssembleCurrentSchematic packet, MessageContext ctx)
	{
		EntityPlayer player = ctx.getServerHandler().playerEntity;

		if (player != null)
		{
			AssemblerAPI.instance.assembleSchematicAsPlayer(AliensVsPredator.assembler().getSchematicForId(packet.schematicId), player);
		}
		
		return null;
	}
}
