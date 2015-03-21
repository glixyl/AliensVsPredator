package com.arisux.avp.entities.mob.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import com.arisux.airi.lib.client.ModelBaseExtension;

public class ModelChestburster extends ModelBaseExtension
{
	public ModelRenderer body1, body2, body3, body4, body5, body7, mouth, body8, body9, body10, body11, body12, body13, body14, body15, body16, tail1, tail2, tail3, tail4, tail5, tail6, tail7, body17;

	public ModelChestburster()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.body1 = new ModelRenderer(this, 14, 0);
		this.body1.addBox(-2.0F, 0.0F, 1.0F, 4, 3, 4);
		this.body1.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body1.setTextureSize(64, 32);
		this.body1.mirror = true;
		this.setRotation(this.body1, 0.0F, 0.0F, 0.0F);
		this.body2 = new ModelRenderer(this, 30, 0);
		this.body2.addBox(-1.5F, -1.0F, -1.0F, 3, 1, 3);
		this.body2.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body2.setTextureSize(64, 32);
		this.body2.mirror = true;
		this.setRotation(this.body2, 0.296706F, 0.0F, 0.0F);
		this.body3 = new ModelRenderer(this, 42, 0);
		this.body3.addBox(-1.5F, -5.0F, -0.1F, 3, 3, 3);
		this.body3.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body3.setTextureSize(64, 32);
		this.body3.mirror = true;
		this.setRotation(this.body3, 1.134464F, 0.0F, 0.0F);
		this.body4 = new ModelRenderer(this, 14, 7);
		this.body4.addBox(-2.0F, -1.8F, 0.0F, 4, 3, 3);
		this.body4.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body4.setTextureSize(64, 32);
		this.body4.mirror = true;
		this.setRotation(this.body4, -0.8552113F, 0.0F, 0.0F);
		this.body5 = new ModelRenderer(this, 30, 4);
		this.body5.addBox(-1.5F, -2.8F, -0.8F, 3, 3, 3);
		this.body5.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body5.setTextureSize(64, 32);
		this.body5.mirror = true;
		this.setRotation(this.body5, 0.837758F, 0.0F, 0.0F);
		this.body7 = new ModelRenderer(this, 0, 0);
		this.body7.addBox(-2.0F, -10.0F, 0.9F, 4, 9, 3);
		this.body7.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body7.setTextureSize(64, 32);
		this.body7.mirror = true;
		this.setRotation(this.body7, 1.308997F, 0.0F, 0.0F);
		this.mouth = new ModelRenderer(this, 54, 0);
		this.mouth.addBox(-1.5F, -9.0F, 1.0F, 3, 5, 1);
		this.mouth.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.mouth.setTextureSize(64, 32);
		this.mouth.mirror = true;
		this.setRotation(this.mouth, 1.413717F, 0.0F, 0.0F);
		this.body8 = new ModelRenderer(this, 42, 6);
		this.body8.addBox(-1.5F, -0.5F, -1.0F, 3, 1, 3);
		this.body8.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body8.setTextureSize(64, 32);
		this.body8.mirror = true;
		this.setRotation(this.body8, 0.296706F, 0.0F, 0.0F);
		this.body9 = new ModelRenderer(this, 30, 10);
		this.body9.addBox(-2.5F, -1.8F, -2.5F, 5, 1, 1);
		this.body9.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body9.setTextureSize(64, 32);
		this.body9.mirror = true;
		this.setRotation(this.body9, 0.0F, 0.0F, 0.0F);
		this.body10 = new ModelRenderer(this, 54, 6);
		this.body10.addBox(-2.0F, -1.8F, -1.5F, 1, 3, 1);
		this.body10.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body10.setTextureSize(64, 32);
		this.body10.mirror = true;
		this.setRotation(this.body10, 0.5585054F, 0.0F, 0.1396263F);
		this.body11 = new ModelRenderer(this, 54, 6);
		this.body11.addBox(1.0F, -1.8F, -1.5F, 1, 3, 1);
		this.body11.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body11.setTextureSize(64, 32);
		this.body11.mirror = true;
		this.setRotation(this.body11, 0.5585054F, 0.0F, -0.1396263F);
		this.body12 = new ModelRenderer(this, 0, 12);
		this.body12.addBox(-1.5F, -10.5F, 1.5F, 3, 9, 3);
		this.body12.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body12.setTextureSize(64, 32);
		this.body12.mirror = true;
		this.setRotation(this.body12, 1.308997F, 0.0F, 0.0F);
		this.body13 = new ModelRenderer(this, 42, 10);
		this.body13.addBox(0.5F, -1.2F, -2.7F, 1, 0, 2);
		this.body13.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body13.setTextureSize(64, 32);
		this.body13.mirror = true;
		this.setRotation(this.body13, ((float) Math.PI / 4F), 0.0F, -0.1396263F);
		this.body14 = new ModelRenderer(this, 48, 10);
		this.body14.addBox(-1.5F, -1.2F, -2.7F, 1, 0, 2);
		this.body14.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body14.setTextureSize(64, 32);
		this.body14.mirror = true;
		this.setRotation(this.body14, ((float) Math.PI / 4F), 0.0F, 0.1396263F);
		this.body15 = new ModelRenderer(this, 58, 6);
		this.body15.addBox(0.5F, -1.2F, -1.7F, 1, 2, 1);
		this.body15.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body15.setTextureSize(64, 32);
		this.body15.mirror = true;
		this.setRotation(this.body15, ((float) Math.PI / 4F), 0.0F, -0.1396263F);
		this.body16 = new ModelRenderer(this, 58, 6);
		this.body16.addBox(-1.5F, -1.2F, -1.7F, 1, 2, 1);
		this.body16.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body16.setTextureSize(64, 32);
		this.body16.mirror = true;
		this.setRotation(this.body16, ((float) Math.PI / 4F), 0.0F, 0.1396263F);
		this.tail1 = new ModelRenderer(this, 12, 13);
		this.tail1.addBox(-2.0F, -1.5F, 0.0F, 4, 3, 4);
		this.tail1.setRotationPoint(0.0F, 22.5F, 9.0F);
		this.tail1.setTextureSize(64, 32);
		this.tail1.mirror = true;
		this.setRotation(this.tail1, 0.0F, 0.0F, 0.0F);
		this.tail2 = new ModelRenderer(this, 12, 13);
		this.tail2.addBox(-2.0F, -1.5F, 0.0F, 4, 3, 4);
		this.tail2.setRotationPoint(0.0F, 22.5F, 5.0F);
		this.tail2.setTextureSize(64, 32);
		this.tail2.mirror = true;
		this.setRotation(this.tail2, 0.0F, 0.0F, 0.0F);
		this.tail3 = new ModelRenderer(this, 28, 12);
		this.tail3.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 4);
		this.tail3.setRotationPoint(0.0F, 23.0F, 13.0F);
		this.tail3.setTextureSize(64, 32);
		this.tail3.mirror = true;
		this.setRotation(this.tail3, 0.0F, 0.0F, 0.0F);
		this.tail4 = new ModelRenderer(this, 28, 12);
		this.tail4.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 4);
		this.tail4.setRotationPoint(0.0F, 23.0F, 17.0F);
		this.tail4.setTextureSize(64, 32);
		this.tail4.mirror = true;
		this.setRotation(this.tail4, 0.0F, 0.0F, 0.0F);
		this.tail5 = new ModelRenderer(this, 28, 12);
		this.tail5.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 4);
		this.tail5.setRotationPoint(0.0F, 23.0F, 21.0F);
		this.tail5.setTextureSize(64, 32);
		this.tail5.mirror = true;
		this.setRotation(this.tail5, 0.0F, 0.0F, 0.0F);
		this.tail6 = new ModelRenderer(this, 42, 12);
		this.tail6.addBox(-1.0F, -0.5F, 0.0F, 2, 1, 4);
		this.tail6.setRotationPoint(0.0F, 23.5F, 25.0F);
		this.tail6.setTextureSize(64, 32);
		this.tail6.mirror = true;
		this.setRotation(this.tail6, 0.0F, 0.0F, 0.0F);
		this.tail7 = new ModelRenderer(this, 54, 10);
		this.tail7.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 4);
		this.tail7.setRotationPoint(0.0F, 23.5F, 29.0F);
		this.tail7.setTextureSize(64, 32);
		this.tail7.mirror = true;
		this.setRotation(this.tail7, 0.0F, 0.0F, 0.0F);
		this.body17 = new ModelRenderer(this, 28, 18);
		this.body17.addBox(-2.0F, -2.1F, 0.4F, 4, 2, 3);
		this.body17.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body17.setTextureSize(64, 32);
		this.body17.mirror = true;
		this.setRotation(this.body17, 1.012291F, 0.0F, 0.0F);
	}

	@Override
	public void render(Entity entity, float swingProgress, float swingProgressPrev, float idleProgress, float headYaw, float headPitch, float boxTranslation)
	{
		super.render(entity, swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation);
		this.setRotationAngles(swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation, entity);

		this.body1.render(boxTranslation);
		this.body2.render(boxTranslation);
		this.body3.render(boxTranslation);
		this.body4.render(boxTranslation);
		this.body5.render(boxTranslation);
		this.body7.render(boxTranslation);
		this.mouth.render(boxTranslation);
		this.body8.render(boxTranslation);
		this.body9.render(boxTranslation);
		this.body10.render(boxTranslation);
		this.body11.render(boxTranslation);
		this.body12.render(boxTranslation);
		this.body13.render(boxTranslation);
		this.body14.render(boxTranslation);
		this.body15.render(boxTranslation);
		this.body16.render(boxTranslation);
		this.tail1.render(boxTranslation);
		this.tail2.render(boxTranslation);
		this.tail3.render(boxTranslation);
		this.tail4.render(boxTranslation);
		this.tail5.render(boxTranslation);
		this.tail6.render(boxTranslation);
		this.tail7.render(boxTranslation);
		this.body17.render(boxTranslation);
	}

	@Override
	public void setRotationAngles(float swingProgress, float swingProgressPrev, float idleProgress, float headYaw, float headPitch, float boxTranslation, Entity entity)
	{
		super.setRotationAngles(swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation, entity);
	}
}
