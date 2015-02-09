package com.arisux.avp.packets.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

import com.arisux.avp.items.ItemFirearm;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketAmmoClientUpdate implements IMessage, IMessageHandler<PacketAmmoClientUpdate, PacketAmmoClientUpdate>
{
	public int ammo;

	public PacketAmmoClientUpdate()
	{
		;
	}

	public PacketAmmoClientUpdate(int ammo)
	{
		this.ammo = ammo;
	}
	
	@Override public void fromBytes(ByteBuf buf)
	{
		this.ammo = buf.readInt();
	}

	@Override public void toBytes(ByteBuf buf)
	{
		buf.writeInt(ammo);
	}

	@Override public PacketAmmoClientUpdate onMessage(PacketAmmoClientUpdate packet, MessageContext ctx)
	{
		((ItemFirearm) Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem().getItem()).setAmmoCount(packet.ammo);
		return null;
	}
}
