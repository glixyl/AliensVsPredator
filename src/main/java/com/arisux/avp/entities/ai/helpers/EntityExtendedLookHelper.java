package com.arisux.avp.entities.ai.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.util.MathHelper;

public class EntityExtendedLookHelper extends EntityLookHelper
{
    private EntityLiving entity;
	public double posX;
	public double posY;
	public double posZ;
	public float deltaLookPitch;
	public float deltaLookYaw;
    public boolean isLooking;
	
	public EntityExtendedLookHelper(EntityLiving entityLiving)
	{
		super(entityLiving);
		this.entity = entityLiving;
	}
	
	@Override
	public void setLookPosition(double posX, double posY, double posZ, float deltaLookYaw, float deltaLookPitch)
	{
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.deltaLookYaw = deltaLookYaw;
		this.deltaLookPitch = deltaLookPitch;
        this.isLooking = true;
		super.setLookPosition(this.posX, this.posY, this.posZ, this.deltaLookYaw, this.deltaLookPitch);
	}
	
	@Override
	public void setLookPositionWithEntity(Entity entity, float deltaLookYaw, float deltaLookPitch)
	{
		this.deltaLookYaw = deltaLookYaw;
		this.deltaLookPitch = deltaLookPitch;
        this.posX = entity.posX;

        if (entity instanceof EntityLivingBase)
        {
            this.posY = entity.posY + (double)entity.getEyeHeight();
        }
        else
        {
            this.posY = (entity.boundingBox.minY + entity.boundingBox.maxY) / 2.0D;
        }

        this.posZ = entity.posZ;
        this.isLooking = true;
        
		super.setLookPositionWithEntity(entity, this.deltaLookYaw, this.deltaLookPitch);
	}
	
	@Override
	public void onUpdateLook()
    {
        this.entity.rotationPitch = 0.0F;

        if (this.isLooking)
        {
            this.isLooking = false;
            double d0 = this.posX - this.entity.posX;
            double d1 = this.posY - (this.entity.posY + (double)this.entity.getEyeHeight());
            double d2 = this.posZ - this.entity.posZ;
            double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);
            float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f1 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
            this.entity.rotationPitch = updateRotation(this.entity.rotationPitch, f1, this.deltaLookPitch);
            this.entity.rotationYawHead = updateRotation(this.entity.rotationYawHead, f, this.deltaLookYaw);
        }
        else
        {
            this.entity.rotationYawHead = updateRotation(this.entity.rotationYawHead, this.entity.renderYawOffset, 10.0F);
        }

        float angle = MathHelper.wrapAngleTo180_float(this.entity.rotationYawHead - this.entity.renderYawOffset);

        if (!this.entity.getNavigator().noPath())
        {
            if (angle < -75.0F)
            {
                this.entity.rotationYawHead = this.entity.renderYawOffset - 75.0F;
            }

            if (angle > 75.0F)
            {
                this.entity.rotationYawHead = this.entity.renderYawOffset + 75.0F;
            }
        }
    }

	public static float updateRotation(float angle1, float angle2, float angle3)
	{
		float wrappedAngle = MathHelper.wrapAngleTo180_float(angle2 - angle1);

		if (wrappedAngle > angle3)
		{
			wrappedAngle = angle3;
		}

		if (wrappedAngle < -angle3)
		{
			wrappedAngle = -angle3;
		}

		return angle1 + wrappedAngle;
	}
}
