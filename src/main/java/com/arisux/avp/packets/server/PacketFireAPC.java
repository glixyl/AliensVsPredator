package com.arisux.avp.packets.server;

import com.arisux.avp.entities.EntityGrenade;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketFireAPC implements IMessage, IMessageHandler<PacketFireAPC, PacketFireAPC>
{
    public PacketFireAPC()
    {
        ;
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        ;
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        ;
    }

    @Override
    public PacketFireAPC onMessage(PacketFireAPC packet, MessageContext ctx)
    {
        if (ctx.getServerHandler().playerEntity != null && ctx.getServerHandler().playerEntity.worldObj != null)
        {
            EntityGrenade grenade = new EntityGrenade(ctx.getServerHandler().playerEntity.worldObj, ctx.getServerHandler().playerEntity);
            grenade.explodeOnImpact = true;
            grenade.velocity = 1D;
            ctx.getServerHandler().playerEntity.worldObj.spawnEntityInWorld(grenade);
        }
        return null;
    }
}
