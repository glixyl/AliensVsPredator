package com.arisux.avp.entities.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.client.model.ModelRenderer;

public class ModelBullet extends ModelBaseWrapper
{
	ModelRenderer caseFR;
	ModelRenderer case2;
	ModelRenderer tip1;
	ModelRenderer tip2;
	ModelRenderer caseBR;
	ModelRenderer caseFL;
	ModelRenderer caseBL;

	public ModelBullet()
	{
		textureWidth = 64;
		textureHeight = 32;

		caseFR = new ModelRenderer(this, 0, 0);
		caseFR.addBox(0.4F, -1F, 0.4F, 1, 4, 1);
		caseFR.setRotationPoint(0F, 0F, 0F);
		caseFR.setTextureSize(64, 32);
		caseFR.mirror = true;
		setRotation(caseFR, 0F, 0F, 0F);
		case2 = new ModelRenderer(this, 10, 0);
		case2.addBox(0.5F, -2.5F, 0.5F, 1, 2, 1);
		case2.setRotationPoint(0F, 0F, 0F);
		case2.setTextureSize(64, 32);
		case2.mirror = true;
		setRotation(case2, 0F, 0F, 0F);
		tip1 = new ModelRenderer(this, 25, 0);
		tip1.addBox(1F, -4F, 0.5F, 0, 2, 1);
		tip1.setRotationPoint(0F, 0F, 0F);
		tip1.setTextureSize(64, 32);
		tip1.mirror = true;
		setRotation(tip1, 0F, 0F, 0F);
		tip2 = new ModelRenderer(this, 29, 0);
		tip2.addBox(-1F, -4F, 0.5F, 0, 2, 1);
		tip2.setRotationPoint(0F, 0F, 0F);
		tip2.setTextureSize(64, 32);
		tip2.mirror = true;
		setRotation(tip2, 0F, 1.570796F, 0F);
		caseBR = new ModelRenderer(this, 0, 6);
		caseBR.addBox(0.4F, -1F, 0.6F, 1, 4, 1);
		caseBR.setRotationPoint(0F, 0F, 0F);
		caseBR.setTextureSize(64, 32);
		caseBR.mirror = true;
		setRotation(caseBR, 0F, 0F, 0F);
		caseFL = new ModelRenderer(this, 5, 0);
		caseFL.addBox(0.6F, -1F, 0.4F, 1, 4, 1);
		caseFL.setRotationPoint(0F, 0F, 0F);
		caseFL.setTextureSize(64, 32);
		caseFL.mirror = true;
		setRotation(caseFL, 0F, 0F, 0F);
		caseBL = new ModelRenderer(this, 5, 6);
		caseBL.addBox(0.6F, -1F, 0.6F, 1, 4, 1);
		caseBL.setRotationPoint(0F, 0F, 0F);
		caseBL.setTextureSize(64, 32);
		caseBL.mirror = true;
		setRotation(caseBL, 0F, 0F, 0F);
	}

	@Override
	public void render(float boxTranslation)
	{
		super.render(boxTranslation);
		caseFR.render(boxTranslation);
		case2.render(boxTranslation);
		tip1.render(boxTranslation);
		tip2.render(boxTranslation);
		caseBR.render(boxTranslation);
		caseFL.render(boxTranslation);
		caseBL.render(boxTranslation);
	}
}
