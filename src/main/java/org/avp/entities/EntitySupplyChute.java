package org.avp.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.world.World;

public class EntitySupplyChute extends EntityFallingBlock
{
    public EntitySupplyChute(World world)
    {
        super(world);
    }

    public EntitySupplyChute(World world, double x, double y, double z, Block block)
    {
        this(world, x, y, z, block, 0);
    }

    public EntitySupplyChute(World world, double x, double y, double z, Block block, int meta)
    {
        super(world, x, y, z, block, meta);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
    }

    @Override
    public void onUpdate()
    {
        if (this.getBlock() != null)
        {
            super.onUpdate();
        }
    }
}
