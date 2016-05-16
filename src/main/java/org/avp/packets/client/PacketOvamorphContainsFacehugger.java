package org.avp.packets.client;

import org.avp.entities.mob.EntityOvamorph;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class PacketOvamorphContainsFacehugger implements IMessage, IMessageHandler<PacketOvamorphContainsFacehugger, PacketOvamorphContainsFacehugger>
{
    private boolean containsFacehugger;
    private int entityId;

    public PacketOvamorphContainsFacehugger()
    {
        ;
    }

    public PacketOvamorphContainsFacehugger(boolean containsFacehugger, int entityId)
    {
        this.containsFacehugger = containsFacehugger;
        this.entityId = entityId;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.containsFacehugger = buf.readBoolean();
        this.entityId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(containsFacehugger);
        buf.writeInt(entityId);
    }

    @Override
    public PacketOvamorphContainsFacehugger onMessage(PacketOvamorphContainsFacehugger packet, MessageContext ctx)
    {
        World world = Minecraft.getMinecraft().thePlayer.worldObj;
        Entity entity = world.getEntityByID(packet.entityId);

        if (world != null && entity != null && entity instanceof EntityOvamorph)
        {
            EntityOvamorph ovamorph = (EntityOvamorph) entity;
            ovamorph.setContainsFacehugger(packet.containsFacehugger);
        }

        return null;
    }
}
