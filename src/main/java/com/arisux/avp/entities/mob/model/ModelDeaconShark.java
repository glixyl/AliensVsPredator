package com.arisux.avp.entities.mob.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelDeaconShark extends ModelBaseWrapper
{
	public ModelRenderer body1;
	public ModelRenderer body2;
	public ModelRenderer lFrontFin;
	public ModelRenderer rFrontFin;
	public ModelRenderer dorsalFin;
	public ModelRenderer muzzle1;
	public ModelRenderer body3;
	public ModelRenderer rHindFin;
	public ModelRenderer lHindFin;
	public ModelRenderer tail1;
	public ModelRenderer tail2;
	public ModelRenderer rTailFin;
	public ModelRenderer lTailFin;
	public ModelRenderer tail3;
	public ModelRenderer tail4;
	public ModelRenderer tailSpines;
	public ModelRenderer tailSpinesLower;
	public ModelRenderer tailFin;
	public ModelRenderer rHindFlipper;
	public ModelRenderer lHindFlipper;
	public ModelRenderer lFrontFlipper;
	public ModelRenderer rFrontFlipper;
	public ModelRenderer jawUpper2;
	public ModelRenderer jawLower2;
	public ModelRenderer muzzleSlope;
	public ModelRenderer jawUpper;
	public ModelRenderer jawInnerUpper;
	public ModelRenderer jawLower;
	public ModelRenderer jawInnerLower;

	public ModelDeaconShark()
	{
		this.textureWidth = 256;
		this.textureHeight = 128;
		this.body1 = new ModelRenderer(this, 47, 0);
		this.body1.setRotationPoint(-1.0F, 4.0F, -14.0F);
		this.body1.addBox(-3.5F, -5.0F, 0.0F, 8, 10, 12, 0.0F);
		this.tail4 = new ModelRenderer(this, 98, 25);
		this.tail4.setRotationPoint(0.0F, 0.0F, 8.0F);
		this.tail4.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 9, 0.0F);
		this.jawUpper2 = new ModelRenderer(this, 74, 99);
		this.jawUpper2.setRotationPoint(0.0F, -1.6F, -2.0F);
		this.jawUpper2.addBox(-3.5F, -1.0F, -7.0F, 8, 4, 7, 0.0F);
		this.setRotation(jawUpper2, -0.08726646259971647F, -0.0F, 0.0F);
		this.rFrontFlipper = new ModelRenderer(this, 0, 45);
		this.rFrontFlipper.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rFrontFlipper.addBox(0.5F, 7.0F, -3.0F, 0, 12, 7, 0.0F);
		this.muzzleSlope = new ModelRenderer(this, 0, 84);
		this.muzzleSlope.setRotationPoint(0.0F, -4.5F, -12.5F);
		this.muzzleSlope.addBox(-3.0F, -1.1F, -8.4F, 7, 2, 8, 0.0F);
		this.setRotation(muzzleSlope, 0.10471975511965977F, -0.0F, 0.0F);
        this.jawInnerUpper = new ModelRenderer(this, 118, 52);
        this.jawInnerUpper.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.jawInnerUpper.addBox(-2.5F, -1.6F, -21.0F, 5, 2, 8, 0.0F);
        this.setRotation(jawInnerUpper, 0.03490658503988659F, -0.0F, 0.0F);
		this.tailSpines = new ModelRenderer(this, 30, 66);
		this.tailSpines.setRotationPoint(0.0F, -0.5F, 1.9F);
		this.tailSpines.addBox(0.0F, -3.0F, -3.9F, 0, 4, 10, 0.0F);
		this.body2 = new ModelRenderer(this, 88, 0);
		this.body2.setRotationPoint(0.0F, 0.0F, 12.0F);
		this.body2.addBox(-2.5F, -4.0F, 0.0F, 6, 8, 11, 0.0F);
		this.tail3 = new ModelRenderer(this, 77, 25);
		this.tail3.setRotationPoint(0.0F, -0.5F, 8.0F);
		this.tail3.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 8, 0.0F);
		this.jawUpper = new ModelRenderer(this, 72, 45);
		this.jawUpper.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.jawUpper.addBox(-3.5F, -1.0F, -21.0F, 8, 4, 14, 0.0F);
        this.jawInnerLower = new ModelRenderer(this, 118, 64);
        this.jawInnerLower.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.jawInnerLower.addBox(-2.5F, 0.0F, -21.0F, 5, 2, 8, 0.0F);
        this.setRotation(jawInnerLower, 0.05235987755982988F, -0.0F, 0.0F);
		this.rTailFin = new ModelRenderer(this, 54, 90);
		this.rTailFin.mirror = true;
		this.rTailFin.setRotationPoint(0.0F, 0.0F, 4.0F);
		this.rTailFin.addBox(-5.0F, 0.0F, -2.7F, 5, 1, 4, 0.0F);
		this.setRotation(rTailFin, 0.0F, 0.6806784082777886F, 0.0F);
		this.muzzle1 = new ModelRenderer(this, 0, 0);
		this.muzzle1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.muzzle1.addBox(-4.5F, -5.5F, -13.0F, 10, 11, 13, 0.0F);
		this.lHindFlipper = new ModelRenderer(this, 15, 48);
		this.lHindFlipper.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.lHindFlipper.addBox(-0.5F, 5.0F, -3.0F, 0, 9, 7, 0.0F);
		this.tail1 = new ModelRenderer(this, 29, 25);
		this.tail1.setRotationPoint(0.5F, 0.5F, 9.0F);
		this.tail1.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 8, 0.0F);
		this.rHindFin = new ModelRenderer(this, 128, 21);
		this.rHindFin.setRotationPoint(-2.0F, 0.0F, 7.0F);
		this.rHindFin.addBox(-1.0F, -0.5F, -1.0F, 1, 12, 2, 0.0F);
		this.setRotation(rHindFin, 0.0F, -0.0F, 0.593411945678072F);
		this.jawLower2 = new ModelRenderer(this, 115, 99);
		this.jawLower2.setRotationPoint(0.0F, -1.6F, -2.0F);
		this.jawLower2.addBox(-3.5F, 1.2F, -7.5F, 8, 4, 7, 0.0F);
		this.setRotation(jawLower2, 0.20943951023931953F, -0.0F, 0.0F);
		this.lFrontFlipper = new ModelRenderer(this, 0, 45);
		this.lFrontFlipper.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.lFrontFlipper.addBox(0.5F, 7.0F, -3.0F, 0, 12, 7, 0.0F);
		this.body3 = new ModelRenderer(this, 0, 25);
		this.body3.setRotationPoint(0.0F, 0.0F, 11.0F);
		this.body3.addBox(-2.0F, -3.0F, 0.0F, 5, 6, 9, 0.0F);
		this.tail2 = new ModelRenderer(this, 54, 25);
		this.tail2.setRotationPoint(0.0F, 0.5F, 8.0F);
		this.tail2.addBox(-1.5F, -2.0F, 0.0F, 3, 3, 8, 0.0F);
		this.rFrontFin = new ModelRenderer(this, 128, 0);
		this.rFrontFin.setRotationPoint(-3.7F, 0.2F, 4.0F);
		this.rFrontFin.addBox(0.0F, 0.0F, -1.0F, 1, 16, 2, 0.0F);
		this.setRotation(rFrontFin, 0.0F, -0.0F, 0.593411945678072F);
		this.tailFin = new ModelRenderer(this, 30, 44);
		this.tailFin.setRotationPoint(0.0F, -0.1F, 2.0F);
		this.tailFin.addBox(0.0F, -3.5F, -2.0F, 0, 7, 13, 0.0F);
		this.lHindFin = new ModelRenderer(this, 128, 21);
		this.lHindFin.mirror = true;
		this.lHindFin.setRotationPoint(4.0F, 0.0F, 7.0F);
		this.lHindFin.addBox(-1.0F, -0.5F, -1.0F, 1, 12, 2, 0.0F);
		this.setRotation(lHindFin, 0.0F, -0.0F, -0.593411945678072F);
		this.lFrontFin = new ModelRenderer(this, 128, 0);
		this.lFrontFin.mirror = true;
		this.lFrontFin.setRotationPoint(3.7F, 0.2F, 4.0F);
		this.lFrontFin.addBox(0.0F, 0.0F, -1.0F, 1, 16, 2, 0.0F);
		this.setRotation(lFrontFin, 0.0F, -0.0F, -0.593411945678072F);
		this.tailSpinesLower = new ModelRenderer(this, 32, 93);
		this.tailSpinesLower.setRotationPoint(0.0F, -0.2F, 1.9F);
		this.tailSpinesLower.addBox(0.0F, 1.1F, -3.9F, 0, 4, 10, 0.0F);
		this.rHindFlipper = new ModelRenderer(this, 15, 48);
		this.rHindFlipper.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rHindFlipper.addBox(-0.5F, 5.0F, -3.0F, 0, 9, 7, 0.0F);
		this.dorsalFin = new ModelRenderer(this, 54, 75);
		this.dorsalFin.setRotationPoint(-0.5F, 0.0F, 0.0F);
		this.dorsalFin.addBox(0.0F, -11.0F, -4.0F, 2, 7, 5, 0.0F);
		this.setRotation(dorsalFin, -0.6283185307179586F, -0.0F, 0.0F);
		this.jawLower = new ModelRenderer(this, 72, 67);
		this.jawLower.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.jawLower.addBox(-3.5F, 1.2F, -21.5F, 8, 4, 14, 0.0F);
		this.lTailFin = new ModelRenderer(this, 54, 90);
		this.lTailFin.setRotationPoint(0.0F, 0.0F, 4.0F);
		this.lTailFin.addBox(0.0F, 0.0F, -2.7F, 5, 1, 4, 0.0F);
		this.setRotation(lTailFin, 0.0F, -0.6806784082777886F, 0.0F);
		this.tail3.addChild(this.tail4);
		this.muzzle1.addChild(this.jawUpper2);
		this.rFrontFin.addChild(this.rFrontFlipper);
		this.muzzle1.addChild(this.muzzleSlope);
		this.muzzle1.addChild(this.jawInnerUpper);
		this.tail3.addChild(this.tailSpines);
		this.body1.addChild(this.body2);
		this.tail2.addChild(this.tail3);
		this.jawUpper2.addChild(this.jawUpper);
		this.muzzle1.addChild(this.jawInnerLower);
		this.tail1.addChild(this.rTailFin);
		this.body1.addChild(this.muzzle1);
		this.lHindFin.addChild(this.lHindFlipper);
		this.body3.addChild(this.tail1);
		this.body2.addChild(this.rHindFin);
		this.muzzle1.addChild(this.jawLower2);
		this.lFrontFin.addChild(this.lFrontFlipper);
		this.body2.addChild(this.body3);
		this.tail1.addChild(this.tail2);
		this.body1.addChild(this.rFrontFin);
		this.tail4.addChild(this.tailFin);
		this.body2.addChild(this.lHindFin);
		this.body1.addChild(this.lFrontFin);
		this.tail3.addChild(this.tailSpinesLower);
		this.rHindFin.addChild(this.rHindFlipper);
		this.body1.addChild(this.dorsalFin);
		this.jawLower2.addChild(this.jawLower);
		this.tail1.addChild(this.lTailFin);
	}
	
	@Override
	public void render(Entity entity, float swingProgress, float swingProgressPrev, float idleProgress, float headYaw, float headPitch, float boxTranslation)
	{
		super.render(entity, swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation);
		
		this.body1.render(boxTranslation);
		this.body2.rotateAngleY = MathHelper.cos(swingProgress * 0.6662F + (float)Math.PI) * swingProgressPrev * 0.25F;
		this.body3.rotateAngleY = -0.085F;
		this.tail1.rotateAngleY = -0.085F;
		this.tail2.rotateAngleY = -0.085F;
		this.tail3.rotateAngleY = -0.085F;
		this.tail4.rotateAngleY = -0.085F;
		
		float tailSpeed = entity != null ? (0.5F) : 0F;
	    this.body2.rotateAngleY += MathHelper.sin(idleProgress * tailSpeed) * 0.1F + 0.06;
		this.body3.rotateAngleY += MathHelper.sin(idleProgress * tailSpeed) * 0.1F + 0.06;
		this.tail1.rotateAngleY += MathHelper.sin(idleProgress * tailSpeed) * 0.1F + 0.06;
		this.tail2.rotateAngleY += MathHelper.sin(idleProgress * tailSpeed) * 0.1F + 0.06;
		this.tail3.rotateAngleY += MathHelper.sin(idleProgress * tailSpeed) * 0.1F + 0.06;
		this.tail4.rotateAngleY += MathHelper.sin(idleProgress * tailSpeed) * 0.1F + 0.06;
		
		this.jawLower2.rotateAngleX = MathHelper.cos(swingProgress * 0.6662F + (float)Math.PI) * swingProgressPrev * 0.25F;
		this.jawLower2.rotateAngleX += MathHelper.sin(idleProgress * 0.05F) * 0.1F + 0.06;
		
		this.jawUpper2.rotateAngleX = MathHelper.cos(swingProgress * 0.6662F + (float)Math.PI) * swingProgressPrev * 0.25F;
		this.jawUpper2.rotateAngleX -= MathHelper.sin(idleProgress * 0.05F) * 0.04F + 0.06;

		this.jawInnerLower.offsetZ = MathHelper.cos(swingProgress * 0.6662F + (float)Math.PI) * swingProgressPrev * 0.25F;
		this.jawInnerLower.offsetZ -= MathHelper.sin(idleProgress * 0.05F) * 0.5F + 0.06;
		this.jawInnerLower.rotateAngleX = MathHelper.cos(swingProgress * 0.6662F + (float)Math.PI) * swingProgressPrev * 0.25F;
		this.jawInnerLower.rotateAngleX += MathHelper.sin(idleProgress * 0.05F) * 0.05F + 0.06;
		
		this.jawInnerUpper.offsetZ = MathHelper.cos(swingProgress * 0.6662F + (float)Math.PI) * swingProgressPrev * 0.25F;
		this.jawInnerUpper.offsetZ -= MathHelper.sin(idleProgress * 0.05F) * 0.5F + 0.06;
	}
}
