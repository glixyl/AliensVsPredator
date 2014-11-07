package com.arisux.avp.items.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelM4 extends ModelBase
{
	ModelRenderer barrel;
	ModelRenderer clip;
	ModelRenderer stock1;
	ModelRenderer stock2;
	ModelRenderer stock3;
	ModelRenderer handle;
	ModelRenderer mainBody1;
	ModelRenderer mainBody2;
	ModelRenderer mainBody3;
	ModelRenderer triggerGuard;
	ModelRenderer barrelGuard;
	ModelRenderer sightBase1;
	ModelRenderer sightBase2;
	ModelRenderer sightBase3;
	ModelRenderer sight1;
	ModelRenderer sight2;

	public ModelM4()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.barrel = new ModelRenderer(this, 13, 12);
		this.barrel.addBox(0.0F, -1.7F, -6.0F, 1, 1, 9);
		this.barrel.setRotationPoint(-4.5F, 10.7F, 9.0F);
		this.barrel.setTextureSize(64, 32);
		this.barrel.mirror = true;
		this.setRotation(this.barrel, 0.0F, 0.0F, 0.0F);
		this.clip = new ModelRenderer(this, 27, 0);
		this.clip.addBox(0.5F, 0.5F, 0.3F, 1, 4, 2);
		this.clip.setRotationPoint(-5.0F, 11.0F, 0.0F);
		this.clip.setTextureSize(64, 32);
		this.clip.mirror = true;
		this.setRotation(this.clip, 0.2617994F, 0.0F, 0.0F);
		this.stock1 = new ModelRenderer(this, 16, 0);
		this.stock1.addBox(0.5F, 0.0F, -7.0F, 1, 1, 4);
		this.stock1.setRotationPoint(-5.0F, 9.0F, 0.0F);
		this.stock1.setTextureSize(64, 32);
		this.stock1.mirror = true;
		this.setRotation(this.stock1, 0.0F, 0.0F, 0.0F);
		this.stock2 = new ModelRenderer(this, 9, 0);
		this.stock2.addBox(0.0F, -0.4F, -8.0F, 2, 3, 1);
		this.stock2.setRotationPoint(-5.0F, 9.0F, 0.0F);
		this.stock2.setTextureSize(64, 32);
		this.stock2.mirror = true;
		this.setRotation(this.stock2, 0.0F, 0.0F, 0.0F);
		this.stock3 = new ModelRenderer(this, 4, 11);
		this.stock3.addBox(-4.5F, 6.5F, -11.0F, 1, 1, 3);
		this.stock3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.stock3.setTextureSize(64, 32);
		this.stock3.mirror = true;
		this.setRotation(this.stock3, 0.3839724F, 0.0F, 0.0F);
		this.handle = new ModelRenderer(this, 5, 5);
		this.handle.addBox(0.0F, -0.4F, 0.0F, 1, 1, 4);
		this.handle.setRotationPoint(-4.5F, 14.0F, -3.9F);
		this.handle.setTextureSize(64, 32);
		this.handle.mirror = true;
		this.setRotation(this.handle, 1.186824F, 0.0F, 0.0F);
		this.mainBody1 = new ModelRenderer(this, 34, 14);
		this.mainBody1.addBox(0.0F, -2.0F, -3.0F, 2, 2, 6);
		this.mainBody1.setRotationPoint(-5.0F, 10.7F, 0.0F);
		this.mainBody1.setTextureSize(64, 32);
		this.mainBody1.mirror = true;
		this.setRotation(this.mainBody1, 0.0F, 0.0F, 0.0F);
		this.mainBody2 = new ModelRenderer(this, 0, 5);
		this.mainBody2.addBox(1.0F, 0.0F, 0.0F, 0, 1, 2);
		this.mainBody2.setRotationPoint(-5.0F, 10.7F, -3.0F);
		this.mainBody2.setTextureSize(64, 32);
		this.mainBody2.mirror = true;
		this.setRotation(this.mainBody2, 0.0F, 0.0F, 0.0F);
		this.mainBody3 = new ModelRenderer(this, 0, 0);
		this.mainBody3.addBox(0.5F, 0.0F, 3.0F, 1, 1, 3);
		this.mainBody3.setRotationPoint(-5.0F, 10.7F, -3.0F);
		this.mainBody3.setTextureSize(64, 32);
		this.mainBody3.mirror = true;
		this.setRotation(this.mainBody3, 0.0F, 0.0F, 0.0F);
		this.triggerGuard = new ModelRenderer(this, 27, 7);
		this.triggerGuard.addBox(0.5F, 1.0F, 0.0F, 1, 0, 3);
		this.triggerGuard.setRotationPoint(-5.0F, 10.7F, -3.0F);
		this.triggerGuard.setTextureSize(64, 32);
		this.triggerGuard.mirror = true;
		this.setRotation(this.triggerGuard, 0.0F, 0.0F, 0.0F);
		this.barrelGuard = new ModelRenderer(this, 0, 16);
		this.barrelGuard.addBox(-0.5F, -2.0F, -5.5F, 2, 2, 4);
		this.barrelGuard.setRotationPoint(-4.5F, 10.7F, 9.0F);
		this.barrelGuard.setTextureSize(64, 32);
		this.barrelGuard.mirror = true;
		this.setRotation(this.barrelGuard, 0.0F, 0.0F, 0.0F);
		this.sightBase1 = new ModelRenderer(this, 1, 9);
		this.sightBase1.addBox(0.0F, -3.0F, -11.8F, 1, 1, 0);
		this.sightBase1.setRotationPoint(-4.5F, 10.7F, 9.0F);
		this.sightBase1.setTextureSize(64, 32);
		this.sightBase1.mirror = true;
		this.setRotation(this.sightBase1, 0.0F, 0.0F, 0.0F);
		this.sightBase2 = new ModelRenderer(this, 36, 6);
		this.sightBase2.addBox(-4.5F, 8.4F, -3.0F, 1, 1, 6);
		this.sightBase2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.sightBase2.setTextureSize(64, 32);
		this.sightBase2.mirror = true;
		this.setRotation(this.sightBase2, 0.0F, 0.0F, 0.0F);
		this.sightBase3 = new ModelRenderer(this, 16, 6);
		this.sightBase3.addBox(-4.5F, 8.4F, 3.5F, 1, 1, 4);
		this.sightBase3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.sightBase3.setTextureSize(64, 32);
		this.sightBase3.mirror = true;
		this.setRotation(this.sightBase3, 0.0F, 0.0F, 0.0F);
		this.sight1 = new ModelRenderer(this, 1, 11);
		this.sight1.addBox(0.0F, -3.0F, -1.8F, 1, 1, 0);
		this.sight1.setRotationPoint(-4.5F, 10.7F, 9.0F);
		this.sight1.setTextureSize(64, 32);
		this.sight1.mirror = true;
		this.setRotation(this.sight1, 0.0F, 0.0F, 0.0F);
		this.sight2 = new ModelRenderer(this, 1, 13);
		this.sight2.addBox(0.5F, -3.0F, -2.5F, 0, 1, 1);
		this.sight2.setRotationPoint(-4.5F, 10.7F, 9.0F);
		this.sight2.setTextureSize(64, 32);
		this.sight2.mirror = true;
		this.setRotation(this.sight2, 0.0F, 0.0F, 0.0F);
	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.barrel.render(f5);
		this.clip.render(f5);
		this.stock1.render(f5);
		this.stock2.render(f5);
		this.stock3.render(f5);
		this.handle.render(f5);
		this.mainBody1.render(f5);
		this.mainBody2.render(f5);
		this.mainBody3.render(f5);
		this.triggerGuard.render(f5);
		this.barrelGuard.render(f5);
		this.sightBase1.render(f5);
		this.sightBase2.render(f5);
		this.sightBase3.render(f5);
		this.sight1.render(f5);
		this.sight2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}