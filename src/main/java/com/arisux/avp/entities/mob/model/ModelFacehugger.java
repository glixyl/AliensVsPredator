package com.arisux.avp.entities.mob.model;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.client.ModelBaseExtension;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelFacehugger extends ModelBaseExtension
{
	public ModelRenderer body, leg8, leg6, leg4, leg2, leg7, leg5, leg3, leg1, legArch1, legArch2, legArch3, legArch4, tailMain;
	public ModelRenderer[] tailBoxes;
	public float rotationToGround;

	public ModelFacehugger()
	{
		this.body = new ModelRenderer(this, 0, 0);
		this.body.addBox(-3.0F, -3.0F, -3.0F, 6, 1, 8);
		this.body.setRotationPoint(0.0F, 22.0F, 0.0F);
		this.body.setTextureSize(64, 32);
		this.body.mirror = true;
		this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
		this.leg8 = new ModelRenderer(this, 46, 2);
		this.leg8.addBox(-1.0F, -1.0F, -1.0F, 8, 1, 1);
		this.leg8.setRotationPoint(4.4F, 20.0F, -0.9F);
		this.leg8.setTextureSize(64, 32);
		this.leg8.mirror = true;
		this.setRotation(this.leg8, 0.0F, 0.5759587F, 0.5410521F);
		this.leg6 = new ModelRenderer(this, 46, 2);
		this.leg6.addBox(-0.5F, -1.0F, -0.8F, 8, 1, 1);
		this.leg6.setRotationPoint(4.1F, 19.5F, 0.8F);
		this.leg6.setTextureSize(64, 32);
		this.leg6.mirror = true;
		this.setRotation(this.leg6, 0.0F, 0.2792527F, 0.5410521F);
		this.leg4 = new ModelRenderer(this, 46, 2);
		this.leg4.addBox(-1.0F, -1.0F, -1.0F, 8, 1, 1);
		this.leg4.setRotationPoint(4.3F, 20.0F, 2.8F);
		this.leg4.setTextureSize(64, 32);
		this.leg4.mirror = true;
		this.setRotation(this.leg4, 0.0F, -0.2792527F, 0.5410521F);
		this.leg2 = new ModelRenderer(this, 46, 2);
		this.leg2.addBox(-1.0F, -1.0F, -1.0F, 8, 1, 1);
		this.leg2.setRotationPoint(4.0F, 20.0F, 4.0F);
		this.leg2.setTextureSize(64, 32);
		this.leg2.mirror = true;
		this.setRotation(this.leg2, 0.0F, -0.5759587F, 0.5410521F);
		this.leg7 = new ModelRenderer(this, 46, 2);
		this.leg7.addBox(-7.0F, -1.0F, -1.0F, 8, 1, 1);
		this.leg7.setRotationPoint(-4.4F, 20.0F, -0.9F);
		this.leg7.setTextureSize(64, 32);
		this.leg7.mirror = true;
		this.setRotation(this.leg7, 0.0F, -0.5759587F, -0.5410521F);
		this.leg5 = new ModelRenderer(this, 46, 2);
		this.leg5.addBox(-7.5F, -1.0F, -0.8F, 8, 1, 1);
		this.leg5.setRotationPoint(-4.1F, 19.5F, 0.8F);
		this.leg5.setTextureSize(64, 32);
		this.leg5.mirror = true;
		this.setRotation(this.leg5, 0.0F, -0.2792527F, -0.5410521F);
		this.leg3 = new ModelRenderer(this, 46, 2);
		this.leg3.addBox(-7.0F, -1.0F, -1.0F, 8, 1, 1);
		this.leg3.setRotationPoint(-4.3F, 20.0F, 2.8F);
		this.leg3.setTextureSize(64, 32);
		this.leg3.mirror = true;
		this.setRotation(this.leg3, 0.0F, 0.2792527F, -0.5410521F);
		this.leg1 = new ModelRenderer(this, 46, 2);
		this.leg1.addBox(-7.0F, -1.0F, -1.0F, 8, 1, 1);
		this.leg1.setRotationPoint(-4.0F, 20.0F, 4.0F);
		this.leg1.setTextureSize(64, 32);
		this.leg1.mirror = true;
		this.setRotation(this.leg1, 0.0F, 0.5759587F, -0.5410521F);
		this.legArch1 = new ModelRenderer(this, 46, 0);
		this.legArch1.addBox(-3.0F, -3.0F, -3.0F, 8, 1, 1);
		this.legArch1.setRotationPoint(-1.0F, 21.5F, 6.0F);
		this.legArch1.setTextureSize(64, 32);
		this.legArch1.mirror = true;
		this.setRotation(this.legArch1, 0.0F, 0.0F, 0.0F);
		this.legArch2 = new ModelRenderer(this, 46, 0);
		this.legArch2.addBox(-3.0F, -3.0F, -3.0F, 8, 1, 1);
		this.legArch2.setRotationPoint(-1.0F, 21.5F, 4.65F);
		this.legArch2.setTextureSize(64, 32);
		this.legArch2.mirror = true;
		this.setRotation(this.legArch2, 0.0F, 0.0F, 0.0F);
		this.legArch3 = new ModelRenderer(this, 46, 0);
		this.legArch3.addBox(-3.0F, -3.0F, -3.0F, 8, 1, 1);
		this.legArch3.setRotationPoint(-1.0F, 21.5F, 3.0F);
		this.legArch3.setTextureSize(64, 32);
		this.legArch3.mirror = true;
		this.setRotation(this.legArch3, 0.0F, 0.0F, 0.0F);
		this.legArch4 = new ModelRenderer(this, 46, 0);
		this.legArch4.addBox(-3.0F, -3.0F, -3.0F, 8, 1, 1);
		this.legArch4.setRotationPoint(-1.0F, 21.5F, 1.5F);
		this.legArch4.setTextureSize(64, 32);
		this.legArch4.mirror = true;
		this.setRotation(this.legArch4, 0.0F, 0.0F, 0.0F);
		this.tailMain = new ModelRenderer(this, 29, 0);
		this.tailMain.addBox(-0.5F, 0.0F, -6.3F, 1, 2, 1);
		this.tailMain.rotateAngleX = 0.9F;
		this.tailMain.setRotationPoint(0.0F, 22.0F, 5.0F);
		this.tailBoxes = new ModelRenderer[15];

		for (int x = 0; x < 7; ++x)
		{
			this.tailBoxes[x] = new ModelRenderer(this, 29, 0);
			this.tailBoxes[x].addBox(-0.5F, 0.0F, -6.3F, 1, 2, 1);
			this.tailBoxes[x].setRotationPoint(0.0F, 1.0F, 0.0F);

			if (x == 0)
			{
				this.tailMain.addChild(this.tailBoxes[x]);
			}
			else
			{
				this.tailBoxes[x - 1].addChild(this.tailBoxes[x]);
			}
		}

		for (int x = 7; x < 15; ++x)
		{
			this.tailBoxes[x] = new ModelRenderer(this, 29, 0);
			this.tailBoxes[x].addBox(-0.5F, 0.0F, -6.3F, 1, 2, 1);
			this.tailBoxes[x].setRotationPoint(0.0F, 1.0F, 0.0F);
			this.tailBoxes[x - 1].addChild(this.tailBoxes[x]);
		}
	}

	@Override
	public void render(Entity entity, float swingProgress, float swingProgressPrev, float idleProgress, float headYaw, float headPitch, float boxTranslation)
	{
		super.render(entity, swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation);
		this.setRotationAngles(swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation, entity);
		this.body.render(boxTranslation);
		this.leg8.render(boxTranslation);
		this.leg6.render(boxTranslation);
		this.leg4.render(boxTranslation);
		this.leg2.render(boxTranslation);
		this.leg7.render(boxTranslation);
		this.leg5.render(boxTranslation);
		this.leg3.render(boxTranslation);
		this.leg1.render(boxTranslation);
		this.legArch1.render(boxTranslation);
		this.legArch2.render(boxTranslation);
		this.legArch3.render(boxTranslation);
		this.legArch4.render(boxTranslation);
		this.tailMain.render(boxTranslation);
	}

	@Override
	public void setRotationAngles(float swingProgress, float swingProgressPrev, float idleProgress, float headYaw, float headPitch, float boxTranslation, Entity entity)
	{
		super.setRotationAngles(swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation, entity);
		this.setLivingAnimations((EntityLivingBase) entity, 0F, 0F, 0F);

		float angleZ = ((float) Math.PI / 4F);
		this.leg1.rotateAngleZ = -angleZ;
		this.leg2.rotateAngleZ = angleZ;
		this.leg3.rotateAngleZ = -angleZ * 0.74F;
		this.leg4.rotateAngleZ = angleZ * 0.74F;
		this.leg5.rotateAngleZ = -angleZ * 0.74F;
		this.leg6.rotateAngleZ = angleZ * 0.74F;
		this.leg7.rotateAngleZ = -angleZ;
		this.leg8.rotateAngleZ = angleZ;

		float angleYOffset = 0.0F;
		float angleY = 0.3926991F;
		this.leg1.rotateAngleX = angleY * 2.0F + angleYOffset;
		this.leg2.rotateAngleY = -angleY * 2.0F - angleYOffset;
		this.leg3.rotateAngleY = angleY * 1.0F + angleYOffset;
		this.leg4.rotateAngleY = -angleY * 1.0F - angleYOffset;
		this.leg5.rotateAngleY = -angleY * 1.0F + angleYOffset;
		this.leg6.rotateAngleY = angleY * 1.0F - angleYOffset;
		this.leg7.rotateAngleY = -angleY * 2.0F + angleYOffset;
		this.leg8.rotateAngleY = angleY * 2.0F - angleYOffset;

		float frontSetXY = -(MathHelper.cos(swingProgress * 0.6662F * 2.0F + 0.0F) * 0.4F) * swingProgressPrev;
		float frontSetZ = Math.abs(MathHelper.sin(swingProgress * 0.6662F + 0.0F) * 0.4F) * swingProgressPrev;
		this.leg1.rotateAngleX += frontSetXY;
		this.leg2.rotateAngleY += -frontSetXY;
		this.leg1.rotateAngleZ += frontSetZ;
		this.leg2.rotateAngleZ += -frontSetZ;

		float midFrontSetXY = -(MathHelper.cos(swingProgress * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * swingProgressPrev;
		float midFrontSetZ = Math.abs(MathHelper.sin(swingProgress * 0.6662F + (float) Math.PI) * 0.4F) * swingProgressPrev;
		this.leg3.rotateAngleY += midFrontSetXY;
		this.leg4.rotateAngleY += -midFrontSetXY;
		this.leg3.rotateAngleZ += midFrontSetZ;
		this.leg4.rotateAngleZ += -midFrontSetZ;

		float midBackSetXY = -(MathHelper.cos(swingProgress * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * swingProgressPrev;
		float midBackSetZ = Math.abs(MathHelper.sin(swingProgress * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * swingProgressPrev;
		this.leg5.rotateAngleY += midBackSetXY;
		this.leg6.rotateAngleY += -midBackSetXY;
		this.leg5.rotateAngleZ += midBackSetZ;
		this.leg6.rotateAngleZ += -midBackSetZ;

		float backSetXY = -(MathHelper.cos(swingProgress * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F) * swingProgressPrev;
		float backSetZ = Math.abs(MathHelper.sin(swingProgress * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F) * swingProgressPrev;
		this.leg7.rotateAngleY += backSetXY;
		this.leg8.rotateAngleY += -backSetXY;
		this.leg7.rotateAngleZ += backSetZ;
		this.leg8.rotateAngleZ += -backSetZ;

		if (entity != null)
		{
			if (!entity.isCollidedVertically)
			{
				rotationToGround = headPitch;
			}
			else if (rotationToGround > 0 && rotationToGround < 180F)
			{
				rotationToGround -= 10F;
			}
			else if (rotationToGround < 0)
			{
				rotationToGround += 10F;
			}
		}

		if (!(rotationToGround >= -10F && rotationToGround <= 10F))
		{
			GL11.glRotatef(rotationToGround, 1F, 0F, 0F);
		}
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float var2, float var3, float var4)
	{
		this.tailMain.rotationPointY = 15.0F;
		this.tailMain.rotationPointZ = 7.5F;
		this.tailMain.rotateAngleX = 1.1F;
		float f = (0.5F + var3) * 0.125F;
		float f1 = var2 * 0.6662F + ((Minecraft.getMinecraft().thePlayer.worldObj.getWorldTime() % 100) / (entity != null && !entity.isSprinting() ? 4.978873F : 0.9178873F)) * 0.6662F;
		this.tailMain.rotateAngleY = 0.0F;
		this.tailMain.rotateAngleX += MathHelper.sin(f1) * f;

		for (int x = 0; x < this.tailBoxes.length; ++x)
		{
			this.tailBoxes[x].rotateAngleZ = 0.0F;
			this.tailBoxes[x].rotateAngleX = 0.05F;
			this.tailBoxes[x].rotateAngleX += MathHelper.sin(f1 - (x + 1) * 0.25F) * f;
		}
	}
}
