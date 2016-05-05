package com.arisux.avp.packets.client;

import com.arisux.avp.interfaces.IOpenable;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketOpenable implements IMessage, IMessageHandler<PacketOpenable, PacketOpenable>
{
	private boolean open;
	private int x;
	private int y;
	private int z;

	public PacketOpenable()
	{
		;
	}

	public PacketOpenable(boolean open, int x, int y, int z)
	{
		this.open = open;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.open = buf.readBoolean();
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(open);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	@Override
	public PacketOpenable onMessage(PacketOpenable packet, MessageContext ctx)
	{
		World world = Minecraft.getMinecraft().thePlayer.worldObj;
		TileEntity tile = world.getTileEntity(packet.x, packet.y, packet.z);

		if (world != null && tile != null && tile instanceof IOpenable)
		{
			IOpenable openable = (IOpenable) tile;

			if (openable != null)
			{
				openable.setOpen(packet.open);
			}
		}

		return null;
	}
}
