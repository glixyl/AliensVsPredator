package org.avp.entities.tile;

import org.avp.AliensVsPredator;
import org.avp.entities.EntityMedpod;
import org.avp.packets.client.PacketOpenable;
import org.avp.util.IOpenable;
import org.avp.util.IVoltageReceiver;

import com.arisux.airi.lib.interfaces.IRotatable;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityMedpod extends TileEntityElectrical implements IOpenable, IVoltageReceiver, IRotatable
{
    private ForgeDirection direction;
    private boolean isOpen;
    private EntityMedpod medpodEntity;
    private float doorProgress;

    public TileEntityMedpod()
    {
        super(false);
        this.isOpen = false;
        this.doorProgress = -0.01F;
    }

    public EntityMedpod getEntity()
    {
        return this.medpodEntity;
    }

    public void setEntity(Entity entity)
    {
        this.medpodEntity = (EntityMedpod) entity;
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.getNbtCompound());
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        if (this.direction != null)
        {
            nbt.setInteger("Direction", this.direction.ordinal());
        }
        nbt.setFloat("DoorProgress", this.doorProgress);
        nbt.setBoolean("Open", this.isOpen);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        if (ForgeDirection.getOrientation(nbt.getInteger("Direction")) != null)
        {
            this.direction = ForgeDirection.getOrientation(nbt.getInteger("Direction"));
        }
        this.doorProgress = nbt.getFloat("DoorProgress");
        this.isOpen = nbt.getBoolean("Open");
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        this.updateEnergyAsReceiver();

        if (this.getEntity() == null && !this.worldObj.isRemote)
        {
            this.medpodEntity = new EntityMedpod(worldObj);
            this.medpodEntity.setTile(this);
            this.medpodEntity.setLocationAndAngles(this.xCoord, this.yCoord, this.zCoord, 0F, 0F);
            this.worldObj.spawnEntityInWorld(this.medpodEntity);
        }

        if (this.worldObj != null && this.getEntity() != null)
        {
            float rotation = 0F;

            if (this.getDirection() == ForgeDirection.NORTH)
                rotation = 0F;

            if (this.getDirection() == ForgeDirection.SOUTH)
                rotation = 180F;

            if (this.getDirection() == ForgeDirection.EAST)
                rotation = 90F;

            if (this.getDirection() == ForgeDirection.WEST)
                rotation = -90F;

            this.getEntity().setLocationAndAngles(this.xCoord + getEntity().width / 2, this.yCoord, this.zCoord + getEntity().width / 2, rotation, 0F);
        }

        if (this.isOpen())
        {
            this.doorProgress = this.doorProgress < getMaxDoorProgress() ? this.doorProgress + 0.025F : this.doorProgress;
        }

        if (!this.isOpen())
        {
            this.doorProgress = this.doorProgress > 0.0F ? this.doorProgress - 0.025F : this.doorProgress;
        }
    }

    @Override
    public ForgeDirection getDirection()
    {
        return direction;
    }

    @Override
    public void setDirection(ForgeDirection direction)
    {
        this.direction = direction;
    }

    @Override
    public void setOpen(boolean isOpen)
    {
        if (this.getVoltage() > 0)
        {
            this.isOpen = isOpen;

            if (!this.worldObj.isRemote)
            {
                AliensVsPredator.network().sendToAll(new PacketOpenable(isOpen, this.xCoord, this.yCoord, this.zCoord));
            }

            if (this.getEntity().riddenByEntity == null)
            {
                this.getEntity().clearLastRidden();
            }

            if (isOpen && this.getEntity() != null && this.getEntity().riddenByEntity != null)
            {
                this.getEntity().riddenByEntity = null;
            }
        }
    }

    @Override
    public boolean isOpen()
    {
        return isOpen;
    }

    @Override
    public double getCurrentVoltage(ForgeDirection from)
    {
        return this.voltage;
    }

    @Override
    public double getMaxVoltage(ForgeDirection from)
    {
        return 240;
    }

    @Override
    public boolean canConnectPower(ForgeDirection from)
    {
        return true;
    }

    public float getDoorProgress()
    {
        return doorProgress;
    }

    public float getMaxDoorProgress()
    {
        return 3.125F;
    }
}
