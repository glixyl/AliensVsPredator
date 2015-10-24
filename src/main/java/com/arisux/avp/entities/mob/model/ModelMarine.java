package com.arisux.avp.entities.mob.model;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.GlStateManager;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelMarine extends ModelBase
{
	public ModelRenderer bipedHead, bipedHeadwear, bipedBody, bipedRightArm, bipedLeftArm, bipedRightLeg, bipedLeftLeg, bipedEars, bipedCloak;
	public int heldItemLeft, heldItemRight;
	public boolean isSneak, aimedBow;

	public ModelMarine()
	{
		this(0.0F);
	}
	
	public ModelMarine(float scale)
	{
		this.heldItemLeft = 0;
		this.heldItemRight = 0;
		this.isSneak = false;
		this.aimedBow = false;
		this.textureWidth = 64;
		this.textureHeight = 43;
		this.bipedCloak = new ModelRenderer(this, 0, 0);
		this.bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, scale);
		this.bipedEars = new ModelRenderer(this, 24, 0);
		this.bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, scale);
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale);
		this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedHeadwear = new ModelRenderer(this, 32, 0);
		this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale + 0.5F);
		this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedBody = new ModelRenderer(this, 16, 16);
		this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scale);
		this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedRightArm = new ModelRenderer(this, 40, 16);
		this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, scale);
		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.bipedLeftArm = new ModelRenderer(this, 40, 16);
		this.bipedLeftArm.mirror = true;
		this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, scale);
		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.bipedRightLeg = new ModelRenderer(this, 0, 16);
		this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
		this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
		this.bipedLeftLeg.mirror = true;
		this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
		this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
	}

	@Override
	public void render(Entity entity, float swingProgress, float swingProgressPrev, float idleProgress, float headYaw, float headPitch, float boxTranslation)
	{
		super.render(entity, swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation);
		this.setRotationAngles(swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation, entity);

		if (this.isChild)
		{
			float scale = 2.0F;
			GL11.glPushMatrix();
			GlStateManager.scale(1.5F / scale, 1.5F / scale, 1.5F / scale);
			GlStateManager.translate(0.0F, 16.0F * boxTranslation, 0.0F);
			this.bipedHead.render(boxTranslation);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GlStateManager.scale(1.0F / scale, 1.0F / scale, 1.0F / scale);
			GlStateManager.translate(0.0F, 24.0F * boxTranslation, 0.0F);
			this.bipedBody.render(boxTranslation);
			this.bipedRightArm.render(boxTranslation);
			this.bipedLeftArm.render(boxTranslation);
			this.bipedRightLeg.render(boxTranslation);
			this.bipedLeftLeg.render(boxTranslation);
			this.bipedHeadwear.render(boxTranslation);
			GL11.glPopMatrix();
		} else
		{
			this.bipedHead.render(boxTranslation);
			this.bipedBody.render(boxTranslation);
			this.bipedRightArm.render(boxTranslation);
			this.bipedLeftArm.render(boxTranslation);
			this.bipedRightLeg.render(boxTranslation);
			this.bipedLeftLeg.render(boxTranslation);
			this.bipedHeadwear.render(boxTranslation);
		}
	}

	@Override
	public void setRotationAngles(float swingProgress, float swingProgressPrev, float idleProgress, float headYaw, float headPitch, float boxTranslation, Entity entity)
	{
		this.bipedHead.rotateAngleY = headYaw / (180F / (float) Math.PI);
		this.bipedHead.rotateAngleX = headPitch / (180F / (float) Math.PI);
		this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
		this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
		this.bipedRightArm.rotateAngleX = MathHelper.cos(swingProgress * 0.6662F + (float) Math.PI) * 2.0F * swingProgressPrev * 0.5F;
		this.bipedLeftArm.rotateAngleX = MathHelper.cos(swingProgress * 0.6662F) * 2.0F * swingProgressPrev * 0.5F;
		this.bipedRightArm.rotateAngleZ = 0.0F;
		this.bipedLeftArm.rotateAngleZ = 0.0F;
		this.bipedRightLeg.rotateAngleX = MathHelper.cos(swingProgress * 0.6662F) * 1.4F * swingProgressPrev;
		this.bipedLeftLeg.rotateAngleX = MathHelper.cos(swingProgress * 0.6662F + (float) Math.PI) * 1.4F * swingProgressPrev;
		this.bipedRightLeg.rotateAngleY = 0.0F;
		this.bipedLeftLeg.rotateAngleY = 0.0F;

		if (this.isRiding)
		{
			this.bipedRightArm.rotateAngleX += -((float) Math.PI / 5F);
			this.bipedLeftArm.rotateAngleX += -((float) Math.PI / 5F);
			this.bipedRightLeg.rotateAngleX = -((float) Math.PI * 2F / 5F);
			this.bipedLeftLeg.rotateAngleX = -((float) Math.PI * 2F / 5F);
			this.bipedRightLeg.rotateAngleY = ((float) Math.PI / 10F);
			this.bipedLeftLeg.rotateAngleY = -((float) Math.PI / 10F);
		}

		if (this.heldItemLeft != 0)
		{
			this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - ((float) Math.PI / 10F) * this.heldItemLeft;
		}

		if (this.heldItemRight != 0)
		{
			this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float) Math.PI / 10F) * this.heldItemRight;
		}

		this.bipedRightArm.rotateAngleY = 0.0F;
		this.bipedLeftArm.rotateAngleY = 0.0F;
		float var8;
		float var9;

		if (this.onGround > -9990.0F)
		{
			var8 = this.onGround;
			this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(var8) * (float) Math.PI * 2.0F) * 0.2F;
			this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
			this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
			this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
			var8 = 1.0F - this.onGround;
			var8 *= var8;
			var8 *= var8;
			var8 = 1.0F - var8;
			var9 = MathHelper.sin(var8 * (float) Math.PI);
			float var10 = MathHelper.sin(this.onGround * (float) Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
			this.bipedRightArm.rotateAngleX = (float) (this.bipedRightArm.rotateAngleX - (var9 * 1.2D + var10));
			this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
			this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * (float) Math.PI) * -0.4F;
		}

		if (this.isSneak)
		{
			this.bipedBody.rotateAngleX = 0.5F;
			this.bipedRightArm.rotateAngleX += 0.4F;
			this.bipedLeftArm.rotateAngleX += 0.4F;
			this.bipedRightLeg.rotationPointZ = 4.0F;
			this.bipedLeftLeg.rotationPointZ = 4.0F;
			this.bipedRightLeg.rotationPointY = 9.0F;
			this.bipedLeftLeg.rotationPointY = 9.0F;
			this.bipedHead.rotationPointY = 1.0F;
			this.bipedHeadwear.rotationPointY = 1.0F;
		} else
		{
			this.bipedBody.rotateAngleX = 0.0F;
			this.bipedRightLeg.rotationPointZ = 0.1F;
			this.bipedLeftLeg.rotationPointZ = 0.1F;
			this.bipedRightLeg.rotationPointY = 12.0F;
			this.bipedLeftLeg.rotationPointY = 12.0F;
			this.bipedHead.rotationPointY = 0.0F;
			this.bipedHeadwear.rotationPointY = 0.0F;
		}

		this.bipedRightArm.rotateAngleZ += MathHelper.cos(idleProgress * 0.09F) * 0.05F + 0.05F;
		this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(idleProgress * 0.09F) * 0.05F + 0.05F;
		this.bipedRightArm.rotateAngleX += MathHelper.sin(idleProgress * 0.067F) * 0.05F;
		this.bipedLeftArm.rotateAngleX -= MathHelper.sin(idleProgress * 0.067F) * 0.05F;

		if (null == null)
		{
			var8 = 0.0F;
			var9 = 0.0F;
			this.bipedRightArm.rotateAngleZ = 0.0F;
			this.bipedLeftArm.rotateAngleZ = 0.0F;
			this.bipedRightArm.rotateAngleY = -(0.1F - var8 * 0.6F) + this.bipedHead.rotateAngleY;
			this.bipedLeftArm.rotateAngleY = 0.1F - var8 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
			this.bipedRightArm.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
			this.bipedLeftArm.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
			this.bipedRightArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
			this.bipedLeftArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
			this.bipedRightArm.rotateAngleZ += MathHelper.cos(idleProgress * 0.09F) * 0.05F + 0.05F;
			this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(idleProgress * 0.09F) * 0.05F + 0.05F;
			this.bipedRightArm.rotateAngleX += MathHelper.sin(idleProgress * 0.067F) * 0.05F;
			this.bipedLeftArm.rotateAngleX -= MathHelper.sin(idleProgress * 0.067F) * 0.05F;
		}
	}

	@Override
	public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
	{
		super.setLivingAnimations(par1EntityLivingBase, par2, par3, par4);
	}
}
