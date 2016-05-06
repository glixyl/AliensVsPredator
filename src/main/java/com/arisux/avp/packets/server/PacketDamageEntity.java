package com.arisux.avp.packets.server;

import com.arisux.avp.DamageSources;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;

public class PacketDamageEntity implements IMessage, IMessageHandler<PacketDamageEntity, PacketDamageEntity>
{
    public int entityId, entitySourceId;
    public float damage;

    public PacketDamageEntity()
    {
        ;
    }

    public PacketDamageEntity(Entity entity, Entity entitySource, float damage)
    {
        this.entityId = entity != null ? entity.getEntityId() : -1;
        this.entitySourceId = entitySource != null ? entitySource.getEntityId() : -1;
        this.damage = damage;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.entityId = buf.readInt();
        this.entitySourceId = buf.readInt();
        this.damage = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.entityId);
        buf.writeInt(this.entitySourceId);
        buf.writeFloat(this.damage);
    }

    @Override
    public PacketDamageEntity onMessage(PacketDamageEntity packet, MessageContext ctx)
    {
        if (packet.entityId != -1)
        {
            Entity entity = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(packet.entityId);
            Entity entitySource = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(packet.entitySourceId);

            if (entity != null)
            {
                entity.hurtResistantTime = 0;
                entity.attackEntityFrom(DamageSources.causeLaserMineDamage(entitySource, entity), packet.damage);
            }
        }

        return null;
    }
}
