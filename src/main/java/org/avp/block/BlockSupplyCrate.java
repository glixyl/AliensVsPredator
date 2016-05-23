package org.avp.block;

import java.util.Random;

import org.avp.AliensVsPredator;
import org.avp.entities.EntitySupplyChute;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockSupplyCrate extends BlockFalling
{
    public BlockSupplyCrate()
    {
        super(Material.iron);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer playerIn, int side, float subX, float subY, float subZ)
    {
        InventoryPlayer inv = playerIn.inventory;
        inv.addItemStackToInventory(new ItemStack(AliensVsPredator.items().itemAK47, 1));
        inv.addItemStackToInventory(new ItemStack(AliensVsPredator.items().itemAmmoAR, 64));
        inv.addItemStackToInventory(new ItemStack(AliensVsPredator.items().helmMarine, 1));
        inv.addItemStackToInventory(new ItemStack(AliensVsPredator.items().plateMarine, 1));
        inv.addItemStackToInventory(new ItemStack(AliensVsPredator.items().legsMarine, 1));
        inv.addItemStackToInventory(new ItemStack(AliensVsPredator.items().bootsMarine, 1));
        world.setBlockToAir(x, y, z);

        return true;
    }
    
    @Override
    public int tickRate(World world)
    {
        return 2;
    }

    @Override
    public void updateTick(World world, int posX, int posY, int posZ, Random random)
    {
        if (!world.isRemote)
        {
            if (func_149831_e(world, posX, posY - 1, posZ) && posY >= 0)
            {
                byte b0 = 32;

                if (!fallInstantly && world.checkChunksExist(posX - b0, posY - b0, posZ - b0, posX + b0, posY + b0, posZ + b0))
                {
                    EntitySupplyChute crate = new EntitySupplyChute(world, (double) ((float) posX + 0.5F), (double) ((float) posY + 0.5F), (double) ((float) posZ + 0.5F), this, world.getBlockMetadata(posX, posY, posZ));
                    this.func_149829_a(crate);
                    world.spawnEntityInWorld(crate);
                }
                else
                {
                    world.setBlockToAir(posX, posY, posZ);

                    while (func_149831_e(world, posX, posY - 1, posZ) && posY > 0)
                    {
                        --posY;
                    }

                    if (posY > 0)
                    {
                        world.setBlock(posX, posY, posZ, this);
                    }
                }
            }
        }
    }
}
