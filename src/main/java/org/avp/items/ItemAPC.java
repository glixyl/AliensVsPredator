package org.avp.items;

import java.util.List;

import org.avp.entities.EntityAPC;

import com.arisux.airi.lib.ItemTypes.HookedItem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemAPC extends HookedItem
{

    public ItemAPC()
    {
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabTransport);
    }

    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        float f = 1.0F;
        float f1 = playerIn.prevRotationPitch + (playerIn.rotationPitch - playerIn.prevRotationPitch) * f;
        float f2 = playerIn.prevRotationYaw + (playerIn.rotationYaw - playerIn.prevRotationYaw) * f;
        double d0 = playerIn.prevPosX + (playerIn.posX - playerIn.prevPosX) * (double) f;
        double d1 = playerIn.prevPosY + (playerIn.posY - playerIn.prevPosY) * (double) f + 1.62D - (double) playerIn.yOffset;
        double d2 = playerIn.prevPosZ + (playerIn.posZ - playerIn.prevPosZ) * (double) f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
        MovingObjectPosition movingobjectposition = worldIn.rayTraceBlocks(vec3, vec31, true);

        if (movingobjectposition == null)
        {
            return itemStackIn;
        }
        else
        {
            Vec3 vec32 = playerIn.getLook(f);
            boolean flag = false;
            float f9 = 1.0F;
            List list = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, playerIn.boundingBox.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand((double) f9, (double) f9, (double) f9));
            int i;

            for (i = 0; i < list.size(); ++i)
            {
                Entity entity = (Entity) list.get(i);

                if (entity.canBeCollidedWith())
                {
                    float f10 = entity.getCollisionBorderSize();
                    AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double) f10, (double) f10, (double) f10);

                    if (axisalignedbb.isVecInside(vec3))
                    {
                        flag = true;
                    }
                }
            }

            if (flag)
            {
                return itemStackIn;
            }
            else
            {
                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
                {
                    i = movingobjectposition.blockX;
                    int j = movingobjectposition.blockY;
                    int k = movingobjectposition.blockZ;

                    if (worldIn.getBlock(i, j, k) == Blocks.snow_layer)
                    {
                        --j;
                    }

                    EntityAPC entityapc = new EntityAPC(worldIn, (double) ((float) i + 0.5F), (double) ((float) j + 1.0F), (double) ((float) k + 0.5F));
                    entityapc.rotationYaw = (float) (((MathHelper.floor_double((double) (playerIn.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) - 1) * 90);

                    if (!worldIn.getCollidingBoundingBoxes(entityapc, entityapc.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty())
                    {
                        List<Entity> e = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, entityapc.boundingBox);
                        for (Entity entityIn : e)
                        {
                            if (entityIn instanceof EntityLivingBase)
                            {
                                EntityLivingBase entity = (EntityLivingBase) entityIn;
                                entity.setHealth(0);
                            }
                            else
                            {
                                entityIn.setDead();
                            }
                        }
                    }

                    if (!worldIn.isRemote)
                    {
                        worldIn.spawnEntityInWorld(entityapc);
                    }

                    if (!playerIn.capabilities.isCreativeMode)
                    {
                        --itemStackIn.stackSize;
                    }
                }

                return itemStackIn;
            }
        }
    }
}
