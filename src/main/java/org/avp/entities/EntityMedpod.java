package org.avp.entities;

import org.avp.entities.tile.TileEntityMedpod;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityMedpod extends EntityLiving
{
    private TileEntityMedpod tile;

    public EntityMedpod(TileEntityMedpod tile, World worldObj)
    {
        super(worldObj);
        this.setSize(1.0F, 1.0F);
        this.tile = tile;
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
    }

    public TileEntityMedpod getTileEntity()
    {
        return tile;
    }
}
