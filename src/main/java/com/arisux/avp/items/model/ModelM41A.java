package com.arisux.avp.items.model;

import net.minecraft.client.model.ModelRenderer;

import com.arisux.airi.lib.render.ModelBaseExtension;

public class ModelM41A extends ModelBaseExtension
{
	ModelRenderer stockEnd;
	ModelRenderer stock1;
	ModelRenderer sight1;
	ModelRenderer sight2;
	ModelRenderer underrail;
	ModelRenderer underrail2;
	ModelRenderer sight3;
	ModelRenderer gunBase1;
	ModelRenderer gunBase2;
	ModelRenderer barrel;
	ModelRenderer gunBase3;
	ModelRenderer mechanism;
	ModelRenderer grip;
	ModelRenderer grip2;
	ModelRenderer handle;
	ModelRenderer gunFrame;

	public ModelM41A()
	{
		this.textureWidth = 256;
		this.textureHeight = 128;
		this.stockEnd = new ModelRenderer(this, 13, 26);
		this.stockEnd.addBox(-2.0F, 18.0F, -2.0F, 2, 4, 1);
		this.stockEnd.setRotationPoint(-3.0F, -10.0F, -5.0F);
		this.stockEnd.setTextureSize(256, 128);
		this.stockEnd.mirror = true;
		this.setRotation(this.stockEnd, 0.0F, 0.0F, 0.0F);
		this.stock1 = new ModelRenderer(this, 37, 12);
		this.stock1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		this.stock1.setRotationPoint(-5.0F, 8.033334F, -6.0F);
		this.stock1.setTextureSize(256, 128);
		this.stock1.mirror = true;
		this.setRotation(this.stock1, 0.0F, 0.0F, 0.0F);
		this.sight1 = new ModelRenderer(this, 12, 11);
		this.sight1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
		this.sight1.setRotationPoint(-4.5F, 6.7F, -3.0F);
		this.sight1.setTextureSize(256, 128);
		this.sight1.mirror = true;
		this.setRotation(this.sight1, -0.9687629F, 0.0F, 0.0F);
		this.sight2 = new ModelRenderer(this, 0, 0);
		this.sight2.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1);
		this.sight2.setRotationPoint(-4.5F, 7.65F, -3.0F);
		this.sight2.setTextureSize(256, 128);
		this.sight2.mirror = true;
		this.setRotation(this.sight2, ((float) Math.PI / 2F), 0.0F, 0.0F);
		this.underrail = new ModelRenderer(this, 17, 9);
		this.underrail.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
		this.underrail.setRotationPoint(-5.0F, 9.0F, -4.0F);
		this.underrail.setTextureSize(256, 128);
		this.underrail.mirror = true;
		this.setRotation(this.underrail, 0.0F, 0.0F, 0.0F);
		this.underrail2 = new ModelRenderer(this, 16, 0);
		this.underrail2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 7);
		this.underrail2.setRotationPoint(-5.5F, 9.0F, -2.0F);
		this.underrail2.setTextureSize(256, 128);
		this.underrail2.mirror = true;
		this.setRotation(this.underrail2, 0.0F, 0.0F, 0.0F);
		this.sight3 = new ModelRenderer(this, 0, 8);
		this.sight3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
		this.sight3.setRotationPoint(-4.5F, 7.35F, 3.3F);
		this.sight3.setTextureSize(256, 128);
		this.sight3.mirror = true;
		this.setRotation(this.sight3, 0.7326949F, 0.0F, 0.0F);
		this.gunBase1 = new ModelRenderer(this, 38, 1);
		this.gunBase1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 8);
		this.gunBase1.setRotationPoint(-5.0F, 9.0F, 1.0F);
		this.gunBase1.setTextureSize(256, 128);
		this.gunBase1.mirror = true;
		this.setRotation(this.gunBase1, 0.0F, 0.0F, 0.0F);
		this.gunBase2 = new ModelRenderer(this, 5, 0);
		this.gunBase2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 5);
		this.gunBase2.setRotationPoint(-5.0F, 8.0F, 1.3F);
		this.gunBase2.setTextureSize(256, 128);
		this.gunBase2.mirror = true;
		this.setRotation(this.gunBase2, 0.0F, 0.0F, 0.0F);
		this.barrel = new ModelRenderer(this, 5, 11);
		this.barrel.addBox(0.0F, 0.5F, 0.0F, 1, 1, 2);
		this.barrel.setRotationPoint(-4.5F, 9.0F, 9.0F);
		this.barrel.setTextureSize(256, 128);
		this.barrel.mirror = true;
		this.setRotation(this.barrel, 0.0F, 0.0F, 0.0F);
		this.gunBase3 = new ModelRenderer(this, 37, 17);
		this.gunBase3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 12);
		this.gunBase3.setRotationPoint(-4.5F, 10.0F, -2.0F);
		this.gunBase3.setTextureSize(256, 128);
		this.gunBase3.mirror = true;
		this.setRotation(this.gunBase3, 0.0F, 0.0F, 0.0F);
		this.mechanism = new ModelRenderer(this, 0, 14);
		this.mechanism.addBox(0.0F, 0.0F, -0.5F, 1, 1, 1);
		this.mechanism.setRotationPoint(-4.5F, 11.0F, 10.0F);
		this.mechanism.setTextureSize(256, 128);
		this.mechanism.mirror = true;
		this.setRotation(this.mechanism, 0.0F, 0.0F, 0.0F);
		this.grip = new ModelRenderer(this, 0, 19);
		this.grip.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.grip.setRotationPoint(-4.5F, 13.8F, 1.3F);
		this.grip.setTextureSize(256, 128);
		this.grip.mirror = true;
		this.setRotation(this.grip, 0.7570103F, 0.0F, 0.0F);
		this.grip2 = new ModelRenderer(this, 0, 26);
		this.grip2.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2);
		this.grip2.setRotationPoint(-4.966667F, 10.53333F, 0.0F);
		this.grip2.setTextureSize(256, 128);
		this.grip2.mirror = true;
		this.setRotation(this.grip2, 0.0F, 0.0F, 0.0F);
		this.handle = new ModelRenderer(this, 26, 9);
		this.handle.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.handle.setRotationPoint(-4.5F, 13.0F, -3.9F);
		this.handle.setTextureSize(256, 128);
		this.handle.mirror = true;
		this.setRotation(this.handle, 0.9462628F, 0.0F, 0.0F);
		this.gunFrame = new ModelRenderer(this, 18, 15);
		this.gunFrame.addBox(0.0F, 0.0F, 0.0F, 2, 1, 4);
		this.gunFrame.setRotationPoint(-5.0F, 10.0F, -3.0F);
		this.gunFrame.setTextureSize(256, 128);
		this.gunFrame.mirror = true;
		this.setRotation(this.gunFrame, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void render(float boxTranslation)
	{
		this.stockEnd.render(boxTranslation);
		this.stock1.render(boxTranslation);
		this.sight1.render(boxTranslation);
		this.sight2.render(boxTranslation);
		this.underrail.render(boxTranslation);
		this.underrail2.render(boxTranslation);
		this.sight3.render(boxTranslation);
		this.gunBase1.render(boxTranslation);
		this.gunBase2.render(boxTranslation);
		this.barrel.render(boxTranslation);
		this.gunBase3.render(boxTranslation);
		this.mechanism.render(boxTranslation);
		this.grip.render(boxTranslation);
		this.grip2.render(boxTranslation);
		this.handle.render(boxTranslation);
		this.gunFrame.render(boxTranslation);
	}
}
