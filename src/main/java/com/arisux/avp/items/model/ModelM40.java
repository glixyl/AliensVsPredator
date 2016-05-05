package com.arisux.avp.items.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.client.model.ModelRenderer;

public class ModelM40 extends ModelBaseWrapper
{
	ModelRenderer cap;
	ModelRenderer core;
	ModelRenderer ridge1;
	ModelRenderer ridge2;
	ModelRenderer grip;
	ModelRenderer bottom;

	public ModelM40()
	{
		textureWidth = 64;
		textureHeight = 32;

		cap = new ModelRenderer(this, 0, 0);
		cap.addBox(-0.5F, -3.2F, -0.5F, 3, 3, 3);
		cap.setRotationPoint(0F, 0F, 0F);
		cap.setTextureSize(64, 32);
		cap.mirror = true;
		setRotation(cap, 0F, 0F, 0F);
		core = new ModelRenderer(this, 0, 8);
		core.addBox(0F, -1F, 0F, 2, 8, 2);
		core.setRotationPoint(0F, 0F, 0F);
		core.setTextureSize(64, 32);
		core.mirror = true;
		setRotation(core, 0F, 0F, 0F);
		ridge1 = new ModelRenderer(this, 14, 0);
		ridge1.addBox(-0.5F, 0F, -0.5F, 3, 1, 3);
		ridge1.setRotationPoint(0F, 0F, 0F);
		ridge1.setTextureSize(64, 32);
		ridge1.mirror = true;
		setRotation(ridge1, 0F, 0F, 0F);
		ridge2 = new ModelRenderer(this, 28, 0);
		ridge2.addBox(-0.5F, 1.3F, -0.5F, 3, 1, 3);
		ridge2.setRotationPoint(0F, 0F, 0F);
		ridge2.setTextureSize(64, 32);
		ridge2.mirror = true;
		setRotation(ridge2, 0F, 0F, 0F);
		grip = new ModelRenderer(this, 14, 8);
		grip.addBox(-0.5F, 2.5F, -0.5F, 3, 4, 3);
		grip.setRotationPoint(0F, 0F, 0F);
		grip.setTextureSize(64, 32);
		grip.mirror = true;
		setRotation(grip, 0F, 0F, 0F);
		bottom = new ModelRenderer(this, 28, 8);
		bottom.addBox(-0.5F, 7F, -0.5F, 3, 2, 3);
		bottom.setRotationPoint(0F, 0F, 0F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
	}

	@Override
	public void render(float boxTranslation)
	{
		super.render(boxTranslation);
		cap.render(boxTranslation);
		core.render(boxTranslation);
		ridge1.render(boxTranslation);
		ridge2.render(boxTranslation);
		grip.render(boxTranslation);
		bottom.render(boxTranslation);
	}
}
