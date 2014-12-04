package com.arisux.avp.packets.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;

import com.arisux.airi.lib.WorldUtil;

import cpw.mods.fml.common.network.simpleimpl.*;

public class PacketSpawnEntityServerUpdate implements IMessage, IMessageHandler<PacketSpawnEntityServerUpdate, PacketSpawnEntityServerUpdate>
{
	public int x, y, z, globalID = 0;

	public PacketSpawnEntityServerUpdate()
	{
		;
	}

	public PacketSpawnEntityServerUpdate(int x, int y, int z, int globalID)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.globalID = globalID;
	}

	@Override
	public void toBytes(ByteBuf buffer)
	{
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		buffer.writeInt(globalID);
	}

	@Override
	public void fromBytes(ByteBuf buffer)
	{
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.globalID = buffer.readInt();
	}

	@SuppressWarnings("unchecked")
	@Override public PacketSpawnEntityServerUpdate onMessage(PacketSpawnEntityServerUpdate message, MessageContext ctx)
	{
		if (ctx.getServerHandler().playerEntity != null && ctx.getServerHandler().playerEntity.worldObj != null)
		{
			Entity entity = WorldUtil.Entities.constructEntity(ctx.getServerHandler().playerEntity.worldObj, EntityList.getClassFromID(message.globalID));

			if (entity != null)
			{
				entity.setLocationAndAngles(message.x + 0.5D, message.y + 1D, message.z + 0.5D, 0.0F, 0.0F);
				ctx.getServerHandler().playerEntity.worldObj.spawnEntityInWorld(entity);
			}
		}
		
		return null;
	}
}
