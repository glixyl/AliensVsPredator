package com.arisux.avp.entities.tile.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.client.model.ModelRenderer;

public class ModelTransformer extends ModelBaseWrapper
{
	public ModelRenderer powerOutput;
	public ModelRenderer rightSupport;
	public ModelRenderer leftSupport;
	public ModelRenderer base;
	public ModelRenderer topSupport;
	public ModelRenderer core;
	public ModelRenderer powerInput;

	public ModelTransformer()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.core = new ModelRenderer(this, 36, 17);
		this.core.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.core.addBox(-4.0F, -13.0F, -4.0F, 8, 13, 8, 0.0F);
		this.base = new ModelRenderer(this, 44, 0);
		this.base.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.base.addBox(-8.0F, 0.0F, -8.0F, 16, 1, 16, 0.0F);
		this.powerInput = new ModelRenderer(this, 44, 0);
		this.powerInput.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.powerInput.addBox(-2.0F, -9.0F, -8.0F, 4, 9, 2, 0.0F);
		this.powerOutput = new ModelRenderer(this, 0, 0);
		this.powerOutput.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.powerOutput.addBox(-2.0F, -9.0F, 6.0F, 4, 9, 2, 0.0F);
		this.rightSupport = new ModelRenderer(this, 12, 0);
		this.rightSupport.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.rightSupport.addBox(4.0F, -15.0F, -2.0F, 4, 16, 4, 0.0F);
		this.leftSupport = new ModelRenderer(this, 28, 0);
		this.leftSupport.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.leftSupport.addBox(-8.0F, -15.0F, -2.0F, 4, 16, 4, 0.0F);
		this.topSupport = new ModelRenderer(this, 92, 0);
		this.topSupport.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.topSupport.addBox(-4.0F, -15.0F, -2.0F, 8, 2, 4, 0.0F);
	}
	
	@Override
	public void render(float boxTranslation)
	{
		this.core.render(boxTranslation);
		this.base.render(boxTranslation);
		this.powerInput.render(boxTranslation);
		this.powerOutput.render(boxTranslation);
		this.rightSupport.render(boxTranslation);
		this.leftSupport.render(boxTranslation);
		this.topSupport.render(boxTranslation);
	}
}
