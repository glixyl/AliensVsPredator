package org.avp.block;

import org.avp.AliensVsPredator;
import org.avp.entities.mob.EntityOvamorph;
import org.avp.entities.mob.EntityQueen;

import com.arisux.airi.lib.client.render.IconSet;
import com.arisux.airi.lib.enums.IconSides;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockTempleSpawner extends Block
{
    public boolean creativeOnly;

    public BlockTempleSpawner(Material material, boolean creativeOnly)
    {
        super(material);
        this.creativeOnly = creativeOnly;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return AliensVsPredator.resources().ICONSET_SPAWNER.getIconForSide(side);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        AliensVsPredator.resources().ICONSET_SPAWNER.registerIcons(register);
    }

    @Override
    public void onNeighborBlockChange(World worldObj, int posX, int posY, int posZ, Block block)
    {
        super.onNeighborBlockChange(worldObj, posX, posY, posZ, block);

        int range = 25;
        boolean isQueenNear = worldObj.getEntitiesWithinAABB(EntityQueen.class, AxisAlignedBB.getBoundingBox(posX, posY, posZ, posX + 1, posY + 1, posZ + 1).expand(range * 2, 50.0D, range * 2)).size() >= 1;

        if (!worldObj.isRemote)
        {
            if (!worldObj.isBlockIndirectlyGettingPowered(posX, posY, posZ))
            {
                worldObj.scheduleBlockUpdate(posX, posY, posZ, this, 4);
            }
            else if (worldObj.isBlockIndirectlyGettingPowered(posX, posY, posZ) && isQueenNear || worldObj.isBlockIndirectlyGettingPowered(posX, posY, posZ) && creativeOnly)
            {
                EntityOvamorph entityEgg = new EntityOvamorph(worldObj);
                entityEgg.setLocationAndAngles(posX + 0.5D, posY + 1.0D, posZ + 0.5D, 0.0F, 0.0F);
                worldObj.spawnEntityInWorld(entityEgg);
            }
        }
    }
}
