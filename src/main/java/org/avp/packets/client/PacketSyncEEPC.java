package org.avp.packets.client;

import org.avp.entities.extended.ExtendedEntityLivingBase;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class PacketSyncEEPC implements IMessage, IMessageHandler<PacketSyncEEPC, PacketSyncEEPC>
{
    public NBTTagCompound tag;
    private int entityId;

    public PacketSyncEEPC()
    {
        ;
    }

    public PacketSyncEEPC(int entityId, NBTTagCompound tag)
    {
        this.entityId = entityId;
        this.tag = tag;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.entityId = buf.readInt();
        this.tag = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.entityId);
        ByteBufUtils.writeTag(buf, tag);
    }

    @Override
    public PacketSyncEEPC onMessage(PacketSyncEEPC packet, MessageContext ctx)
    {
        Entity entity = Minecraft.getMinecraft().thePlayer.worldObj.getEntityByID(packet.entityId);

        if (entity != null)
        {
            ExtendedEntityLivingBase extendedLiving = (ExtendedEntityLivingBase) entity.getExtendedProperties(ExtendedEntityLivingBase.IDENTIFIER);

            if (extendedLiving != null)
            {
                extendedLiving.loadNBTData(packet.tag);
            }
        }

        return null;
    }
}
