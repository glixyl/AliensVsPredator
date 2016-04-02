package com.arisux.avp.items.model;

import com.arisux.airi.lib.client.ModelBaseExtension;

import net.minecraft.client.model.ModelRenderer;

public class Model88MOD4 extends ModelBaseExtension
{
	public ModelRenderer barrelRight;
	public ModelRenderer underGrip;
	public ModelRenderer barrelLeft;
	public ModelRenderer rack;
	public ModelRenderer trigger;
	public ModelRenderer handleGrip;
	public ModelRenderer butt1;
	public ModelRenderer barrelNose;
	public ModelRenderer barrelSupport;
	public ModelRenderer gripSupport;

	public Model88MOD4()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.barrelNose = new ModelRenderer(this, 7, 25);
		this.barrelNose.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.barrelNose.addBox(0.0F, -0.7F, -2.5F, 1, 1, 2, 0.0F);
		this.butt1 = new ModelRenderer(this, 50, 0);
		this.butt1.mirror = true;
		this.butt1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.butt1.addBox(0.0F, -7.9F, 3.5F, 1, 2, 1, 0.0F);
		this.setRotation(butt1, -1.1693705988362009F, -0.0F, 0.0F);
		this.barrelLeft = new ModelRenderer(this, 25, 0);
		this.barrelLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.barrelLeft.addBox(0.2F, -1.0F, -2.0F, 1, 2, 11, 0.0F);
		this.barrelSupport = new ModelRenderer(this, 14, 25);
		this.barrelSupport.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.barrelSupport.addBox(0.0F, -1.5F, -2.0F, 1, 1, 1, 0.0F);
		this.setRotation(barrelSupport, 0.767944870877505F, -0.0F, 0.0F);
		this.rack = new ModelRenderer(this, 0, 25);
		this.rack.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rack.addBox(0.0F, 1.3F, -2.0F, 1, 1, 2, 0.0F);
		this.gripSupport = new ModelRenderer(this, 43, 15);
		this.gripSupport.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.gripSupport.addBox(0.0F, 2.0F, 4.3F, 1, 3, 2, 0.0F);
		this.setRotation(gripSupport, 0.13962634015954636F, -0.0F, 0.0F);
		this.underGrip = new ModelRenderer(this, 0, 14);
		this.underGrip.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.underGrip.addBox(0.0F, 0.7F, -1.5F, 1, 1, 9, 0.0F);
		this.trigger = new ModelRenderer(this, 21, 14);
		this.trigger.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.trigger.addBox(0.5F, 1.5F, 0.0F, 0, 3, 5, 0.0F);
		this.barrelRight = new ModelRenderer(this, 0, 0);
		this.barrelRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.barrelRight.addBox(-0.2F, -1.0F, -2.0F, 1, 2, 11, 0.0F);
		this.handleGrip = new ModelRenderer(this, 36, 15);
		this.handleGrip.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.handleGrip.addBox(0.0F, 0.5F, 4.0F, 1, 5, 2, 0.0F);
		this.setRotation(handleGrip, 0.13962634015954636F, -0.0F, 0.0F);
	}

	@Override
	public void render(float boxTranslation)
	{
		super.render(boxTranslation);
		this.barrelNose.render(boxTranslation);
		this.butt1.render(boxTranslation);
		this.barrelLeft.render(boxTranslation);
		this.barrelSupport.render(boxTranslation);
		this.rack.render(boxTranslation);
		this.gripSupport.render(boxTranslation);
		this.underGrip.render(boxTranslation);
		this.trigger.render(boxTranslation);
		this.barrelRight.render(boxTranslation);
		this.handleGrip.render(boxTranslation);
	}
}
