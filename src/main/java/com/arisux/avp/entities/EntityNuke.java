package com.arisux.avp.entities;

import com.arisux.airi.lib.WorldUtil;
import com.arisux.airi.lib.client.Sound;
import com.arisux.avp.AliensVsPredator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityNuke extends EntityThrowable
{
    public EntityNuke(World world)
    {
        super(world);
        this.setSize(0.5F, 0.5F);
        this.yOffset = this.height / 2.0F;
        this.ignoreFrustumCheck = true;
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;
        }

        if (this.worldObj.getWorldTime() % 20 == 0)
        {
            new Sound("avp:weapon.blades.alarm").playSound(this, 10F, 1F);
        }

        if (this.ticksExisted >= this.getDetonationTicks())
        {
            if (AliensVsPredator.settings().areExplosionsEnabled())
            {
                WorldUtil.createCustomExplosion((Entity) null, worldObj, (int) this.posX, (int) this.posY, (int) this.posZ, 47F);
            }

            this.setDead();
        }
        else
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    public double getDetonationTicks()
    {
        return 20 * 30;
    }

    @Override
    protected void onImpact(MovingObjectPosition movingObjectPosition)
    {
        ;
    }
}
