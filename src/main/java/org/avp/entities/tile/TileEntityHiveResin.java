package org.avp.entities.tile;

import java.util.UUID;

import org.avp.util.IHiveSignature;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class TileEntityHiveResin extends TileEntity implements IHiveSignature
{
    private UUID signature;
    private Block blockCovering;

    @Override
    public void updateEntity()
    {
        ;
    }

    @Override
    public UUID getHiveSignature()
    {
        return this.signature;
    }

    @Override
    public void setHiveSignature(UUID signature)
    {
        this.signature = signature;
    }

    public void setBlockCovering(Block blockCovering)
    {
        this.blockCovering = blockCovering;
    }

    public Block getBlockCovering()
    {
        return this.blockCovering;
    }
}
