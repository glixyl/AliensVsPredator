package org.avp.entities.tile;

import org.avp.items.ItemEntitySummoner;
import org.avp.util.IVoltageReceiver;

import com.arisux.airi.lib.interfaces.IRotatable;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCryostasisTube extends TileEntityElectrical implements IVoltageReceiver, IRotatable
{
    private ForgeDirection direction;
    public Entity stasisEntity;
    public ItemStack stasisItemstack;
    private boolean cracked;
    private boolean shattered;
    private int ticksExisted;

    public TileEntityCryostasisTube()
    {
        super(false);
        this.setThresholdVoltage(90);
    }

    @Override
    public void updateEntity()
    {
        this.ticksExisted++;
        super.updateEntity();
        this.updateEnergyAsReceiver();

        if (this.stasisEntity != null && !this.isOperational())
        {
            if (this.worldObj.getWorldTime() % 100 == 0)
            {
                if (this.worldObj.rand.nextInt(8) == 0)
                {
                    if (this.isCracked())
                    {
                        this.setShattered(true);
                    }

                    this.setCracked(true);
                }
            }
        }

        if (this.stasisItemstack != null)
        {
            ItemEntitySummoner item = (ItemEntitySummoner) this.stasisItemstack.getItem();
            Entity entity = item.createNewEntity(worldObj);

            if (entity != null)
            {
                if (this.isShattered())
                {
                    if (!this.worldObj.isRemote)
                    {
                        entity.setLocationAndAngles(this.xCoord, this.yCoord, this.zCoord, 0F, 0F);
                        this.worldObj.spawnEntityInWorld(entity);
                    }

                    this.stasisItemstack = null;
                    this.stasisEntity = null;
                }
            }
            else
            {
                this.stasisEntity = ((ItemEntitySummoner) this.stasisItemstack.getItem()).createNewEntity(this.worldObj);
            }
        }

        if (stasisEntity != null && worldObj.isRemote)
        {
            stasisEntity.onUpdate();
        }
    }

    @Override
    public Block getBlockType()
    {
        return Blocks.beacon;
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
        readFromNBT(packet.func_148857_g());
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setBoolean("Cracked", this.cracked);
        nbt.setBoolean("Shattered", this.shattered);

        if (this.direction != null)
        {
            nbt.setInteger("Direction", this.direction.ordinal());
        }

        if (this.stasisItemstack != null)
        {
            NBTTagCompound nbtStack = new NBTTagCompound();
            this.stasisItemstack.writeToNBT(nbtStack);
            nbt.setTag("StasisItemstack", nbtStack);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.cracked = nbt.getBoolean("Cracked");
        this.shattered = nbt.getBoolean("Shattered");

        if (ForgeDirection.getOrientation(nbt.getInteger("Direction")) != null)
        {
            this.direction = ForgeDirection.getOrientation(nbt.getInteger("Direction"));
        }

        NBTTagCompound nbtStack = nbt.getCompoundTag("StasisItemstack");
        this.stasisItemstack = ItemStack.loadItemStackFromNBT(nbtStack);

        if (this.stasisEntity == null && this.stasisItemstack != null)
        {
            this.stasisEntity = ((ItemEntitySummoner) this.stasisItemstack.getItem()).createNewEntity(this.worldObj);
        }
    }

    public void setCracked(boolean cracked)
    {
        this.cracked = cracked;
    }

    public void setShattered(boolean shattered)
    {
        this.shattered = shattered;
    }

    public boolean isCracked()
    {
        return this.cracked;
    }

    public boolean isShattered()
    {
        return this.shattered;
    }

    @Override
    public boolean canConnectPower(ForgeDirection from)
    {
        return true;
    }

    @Override
    public double getCurrentVoltage(ForgeDirection from)
    {
        return this.getVoltage();
    }

    @Override
    public double getMaxVoltage(ForgeDirection from)
    {
        return 120;
    }

    public int getTicksExisted()
    {
        return ticksExisted;
    }

    @Override
    public ForgeDirection getDirection()
    {
        return this.direction;
    }

    @Override
    public void setDirection(ForgeDirection facing)
    {
        this.direction = facing;
    }
}
