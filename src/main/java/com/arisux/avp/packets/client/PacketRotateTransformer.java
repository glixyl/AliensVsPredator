package com.arisux.avp.packets.client;

import com.arisux.avp.entities.tile.TileEntityTransformer;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class PacketRotateTransformer implements IMessage, IMessageHandler<PacketRotateTransformer, PacketRotateTransformer>
{
	public int direction;
	public int x;
	public int y;
	public int z;
	
	public PacketRotateTransformer()
	{
		;
	}

	public PacketRotateTransformer(int direction, int x, int y, int z)
	{
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.direction = buf.readInt();
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(direction);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	@Override
	public PacketRotateTransformer onMessage(PacketRotateTransformer packet, MessageContext ctx)
	{
		World world = Minecraft.getMinecraft().thePlayer.worldObj;
		
		if (world != null)
		{
			TileEntity tile = world.getTileEntity(packet.x, packet.y, packet.z);

			if (tile != null && tile instanceof TileEntityTransformer)
			{
				TileEntityTransformer tileTransformer = (TileEntityTransformer) tile;
				tileTransformer.setDirection(ForgeDirection.getOrientation(packet.direction));
			}
		}

		return null;
	}
}
