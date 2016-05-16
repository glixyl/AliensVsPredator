package org.avp.packets.client;

import org.avp.entities.extended.ExtendedEntityPlayer;
import org.avp.util.PlayerMode;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class PacketPlayerModeUpdate implements IMessage, IMessageHandler<PacketPlayerModeUpdate, PacketPlayerModeUpdate>
{
    public int mode;

    public PacketPlayerModeUpdate()
    {
        ;
    }

    public PacketPlayerModeUpdate(int mode)
    {
        this.mode = mode;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.mode = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.mode);
    }

    @Override
    public PacketPlayerModeUpdate onMessage(PacketPlayerModeUpdate packet, MessageContext ctx)
    {
        ExtendedEntityPlayer playerExtension = (ExtendedEntityPlayer) Minecraft.getMinecraft().thePlayer.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);
        playerExtension.setPlayerMode(PlayerMode.get(packet.mode));

        return null;
    }
}
