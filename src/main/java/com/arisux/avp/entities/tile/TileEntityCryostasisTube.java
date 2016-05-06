package com.arisux.avp.entities.tile;

import java.util.Random;

import com.arisux.avp.entities.fx.EntityBubbleFX;
import com.arisux.avp.interfaces.energy.IVoltageReceiver;
import com.arisux.avp.items.ItemEntitySummoner;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCryostasisTube extends TileEntityElectrical implements IVoltageReceiver
{
    public int rotation;
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

        // int bubbleLife = 20;
        //
        // if (this.worldObj.isRemote)
        // {
        // for (int x = 1; x > 0; --x)
        // {
        // Random rand = new Random();
        //
        // double bubbleX = 0;
        // double bubbleY = 0;
        // double bubbleZ = 0;
        //
        // for (int r = 3; r > 0; r--)
        // {
        // bubbleX = bubbleX + (rand.nextDouble() / (bubbleLife - this.ticksExisted));
        // bubbleY = bubbleY + (rand.nextDouble() / (bubbleLife - this.ticksExisted));
        // bubbleZ = bubbleZ + (rand.nextDouble() / (bubbleLife - this.ticksExisted));
        // }
        //
        // this.spawnBubbleParticle(bubbleX, bubbleY, bubbleZ, 0.04F, 0.02D, rand);
        // }
        // }

        Random rand = new Random();

        if (this.worldObj.isRemote && !this.isCracked() && rand.nextInt(8) == 0)
        {
            this.spawnBubbleParticle(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.55, 0, 0.75, 0, rand);
        }
    }

    @SideOnly(Side.CLIENT)
    public void spawnBubbleParticle(double bubbleX, double bubbleY, double bubbleZ, double motionX, double motionY, double motionZ, Random rand)
    {
        Minecraft.getMinecraft().effectRenderer.addEffect(new EntityBubbleFX(this.worldObj, bubbleX, bubbleY, bubbleZ, motionX, motionY, motionZ));
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
        nbt.setInteger("Rotation", this.rotation);
        nbt.setBoolean("Cracked", this.cracked);
        nbt.setBoolean("Shattered", this.shattered);

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
        this.rotation = nbt.getInteger("Rotation");
        this.cracked = nbt.getBoolean("Cracked");
        this.shattered = nbt.getBoolean("Shattered");

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

    public void setDirection(byte direction)
    {
        this.rotation = direction;
    }

    public int getTicksExisted()
    {
        return ticksExisted;
    }
}
