package org.avp.packets.client;

import org.avp.entities.tile.TileEntityBlastdoor;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketOpenBlastdoor implements IMessage, IMessageHandler<PacketOpenBlastdoor, PacketOpenBlastdoor>
{
    private boolean open;
    private int x;
    private int y;
    private int z;

    public PacketOpenBlastdoor()
    {
        ;
    }

    public PacketOpenBlastdoor(boolean open, int x, int y, int z)
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
    public PacketOpenBlastdoor onMessage(PacketOpenBlastdoor packet, MessageContext ctx)
    {
        World world = Minecraft.getMinecraft().thePlayer.worldObj;
        TileEntity tile = world.getTileEntity(packet.x, packet.y, packet.z);

        if (world != null && tile != null && tile instanceof TileEntityBlastdoor)
        {
            TileEntityBlastdoor blastdoor = (TileEntityBlastdoor) tile;

            if (blastdoor != null)
            {
                blastdoor.setOpen(packet.open, false);
            }
        }

        return null;
    }
}
