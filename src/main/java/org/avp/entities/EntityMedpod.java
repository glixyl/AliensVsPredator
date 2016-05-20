package org.avp.entities;

import java.util.List;

import org.avp.entities.extended.ExtendedEntityLivingBase;
import org.avp.entities.extended.ExtendedEntityPlayer;
import org.avp.entities.mob.EntitySpeciesAlien;
import org.avp.entities.tile.TileEntityMedpod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityMedpod extends Entity
{
    private TileEntityMedpod tile;
    private Entity lastRiddenEntity;
    private int lastRiddenEntityId;

    public EntityMedpod(World worldObj)
    {
        super(worldObj);
        this.setSize(1.0F, 1.0F);
    }

    @SuppressWarnings("all")
    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.tile == null && this.worldObj.isRemote)
        {
            TileEntity tile = this.worldObj.getTileEntity(((int) Math.floor(this.posX)), ((int) this.posY), ((int) Math.floor(this.posZ)));

            if (tile != null)
            {
                this.setTile((TileEntityMedpod) tile);
            }
        }

        if (this.tile != null && this.tile.getEntity() == null)
        {
            this.tile.setEntity(this);
        }

        if (this.getTileEntity() == null || this.getTileEntity() != null && this.getTileEntity().getEntity() != this)
        {
            this.setDead();
        }

        if (this.riddenByEntity == null && this.getTileEntity() != null && this.getTileEntity().isOpen())
        {
            List<Entity> entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ));

            if (entities != null && !entities.isEmpty())
            {
                Entity entity = entities.get(0);

                if (!entity.isRiding() && !entity.isSneaking() && (entity != this.lastRiddenEntity && entity.getEntityId() != this.lastRiddenEntityId) && !(entity instanceof EntitySpeciesAlien))
                {
                    entity.mountEntity(this);
                    lastRiddenEntity = entity;
                }
            }
        }

        if (lastRiddenEntity != null)
        {
            lastRiddenEntityId = lastRiddenEntity.getEntityId();
        }

        if (this.lastRiddenEntity == null)
        {
            if (lastRiddenEntityId != 0)
            {
                this.lastRiddenEntity = this.worldObj.getEntityByID(lastRiddenEntityId);
            }
        }

        if (this.riddenByEntity != null && this.getTileEntity() != null)
        {
            if (this.getTileEntity().getVoltage() > 0 && !this.getTileEntity().isOpen() && this.riddenByEntity instanceof EntityLivingBase)
            {
                EntityLivingBase living = (EntityLivingBase) this.riddenByEntity;
                ExtendedEntityLivingBase extended = ExtendedEntityLivingBase.get(living);

                living.setHealth(living.getMaxHealth());

                if (!this.worldObj.isRemote)
                {
                    living.curePotionEffects(new ItemStack(Items.milk_bucket, 1));
                    living.getActivePotionEffects().clear();
                }

                if (extended.doesEntityContainEmbryo())
                {
                    extended.setEmbryo(null);
                }

                if (living.riddenByEntity != null && living.riddenByEntity instanceof EntitySpeciesAlien)
                {
                    living.riddenByEntity.setDead();
                }

                if (living instanceof EntityPlayer)
                {
                    EntityPlayer player = (EntityPlayer) living;
                    ExtendedEntityPlayer extendedPlayer = ExtendedEntityPlayer.get(player);

                    player.getFoodStats().setFoodLevel(20);
                }
            }
        }
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return super.getCollisionBox(entity);
    }

    @Override
    public AxisAlignedBB getBoundingBox()
    {
        return null;
    }

    public EntityMedpod setTile(TileEntityMedpod tile)
    {
        this.tile = tile;
        return this;
    }

    public TileEntityMedpod getTileEntity()
    {
        return tile;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt)
    {
        this.lastRiddenEntityId = nbt.getInteger("LastRiddenEntityId");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("LastRiddenEntityId", this.lastRiddenEntityId);
    }

    @Override
    protected void entityInit()
    {
        ;
    }
}
