package org.avp.packets.server;

import org.avp.AliensVsPredator;
import org.avp.entities.EntityNuke;

import com.arisux.airi.AIRI;
import com.arisux.airi.lib.WorldUtil.Entities.Players.Inventories;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class PacketSpawnNuke implements IMessage, IMessageHandler<PacketSpawnNuke, PacketSpawnNuke>
{
    public PacketSpawnNuke()
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
    public PacketSpawnNuke onMessage(PacketSpawnNuke packet, MessageContext ctx)
    {
        EntityPlayer player = ctx.getServerHandler().playerEntity;

        if (player != null && AliensVsPredator.settings().areNukesEnabled())
        {
            AIRI.logger.info("Player %s has just initiated a nuclear explosion at %s, %s, %s", player.getCommandSenderName(), player.posX, player.posY, player.posZ);
            EntityNuke nuke = new EntityNuke(ctx.getServerHandler().playerEntity.worldObj);
            nuke.setLocationAndAngles(ctx.getServerHandler().playerEntity.posX, ctx.getServerHandler().playerEntity.posY, ctx.getServerHandler().playerEntity.posZ, 0F, 0F);
            ctx.getServerHandler().playerEntity.worldObj.spawnEntityInWorld(nuke);
            Inventories.consumeItem(ctx.getServerHandler().playerEntity, ctx.getServerHandler().playerEntity.getCurrentEquippedItem().getItem());
        }

        return null;
    }
}
