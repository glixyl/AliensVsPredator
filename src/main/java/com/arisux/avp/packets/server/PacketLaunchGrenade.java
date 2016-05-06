package com.arisux.avp.packets.server;

import com.arisux.airi.lib.WorldUtil.Entities.Players.Inventories;
import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.EntityGrenade;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketLaunchGrenade implements IMessage, IMessageHandler<PacketLaunchGrenade, PacketLaunchGrenade>
{
    public PacketLaunchGrenade()
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
    public PacketLaunchGrenade onMessage(PacketLaunchGrenade packet, MessageContext ctx)
    {
        if (ctx.getServerHandler().playerEntity != null && ctx.getServerHandler().playerEntity.worldObj != null)
        {
            boolean hasNormal = ctx.getServerHandler().playerEntity.inventory.hasItem(AliensVsPredator.items().itemGrenade);
            boolean hasIncendiary = ctx.getServerHandler().playerEntity.inventory.hasItem(AliensVsPredator.items().itemIncendiaryGrenade);

            if (hasNormal || hasIncendiary)
            {
                EntityGrenade grenade = new EntityGrenade(ctx.getServerHandler().playerEntity.worldObj, ctx.getServerHandler().playerEntity);
                grenade.explodeOnImpact = true;
                grenade.setFlaming(hasIncendiary);
                ctx.getServerHandler().playerEntity.worldObj.spawnEntityInWorld(grenade);
                Inventories.consumeItem(ctx.getServerHandler().playerEntity, !hasIncendiary ? AliensVsPredator.items().itemGrenade : AliensVsPredator.items().itemIncendiaryGrenade);
            }
        }

        return null;
    }
}
