package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityList;

import com.arisux.avp.entities.tile.TileEntityTurret;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketTurretAddTargetUpdate implements IMessage, IMessageHandler<PacketTurretAddTargetUpdate, PacketTurretAddTargetUpdate>
{
	public int x, y, z;
	public int globalID;

	public PacketTurretAddTargetUpdate()
	{
		;
	}

	public PacketTurretAddTargetUpdate(int x, int y, int z, int globalID)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.globalID = globalID;
	}

	@Override public void fromBytes(ByteBuf buf)
	{
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.globalID = buf.readInt();
	}

	@Override public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(globalID);
	}

	@SuppressWarnings("unchecked")
	@Override public PacketTurretAddTargetUpdate onMessage(PacketTurretAddTargetUpdate packet, MessageContext ctx)
	{
		TileEntityTurret tile = (TileEntityTurret) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(packet.x, packet.y, packet.z);

		if (tile != null)
		{
			tile.setDangerous(EntityList.getClassFromID(packet.globalID));
		}
		
		return null;
	}
}
