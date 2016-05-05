package com.arisux.avp.entities.tile.model;

import com.arisux.airi.lib.client.ModelBaseWrapper;

import net.minecraft.client.model.ModelRenderer;

public class ModelPowercell extends ModelBaseWrapper
{
	private ModelRenderer cell;
	private ModelRenderer connector1;
	private ModelRenderer connector2;
	private ModelRenderer connector3;
	private ModelRenderer connector4;

	public ModelPowercell()
	{
		textureWidth = 128;
		textureHeight = 64;

		cell = new ModelRenderer(this, 0, 0);
		cell.addBox(-4F, 0F, -4F, 8, 16, 8);
		cell.setRotationPoint(0F, 8F, 0F);
		cell.setTextureSize(128, 64);
		cell.mirror = true;
		setRotation(cell, 0F, 0F, 0F);
		connector1 = new ModelRenderer(this, 33, 0);
		connector1.addBox(2F, 6F, -2F, 6, 4, 4);
		connector1.setRotationPoint(0F, 8F, 0F);
		connector1.setTextureSize(128, 64);
		connector1.mirror = true;
		setRotation(connector1, 0F, 1.570796F, 0F);
		connector2 = new ModelRenderer(this, 33, 9);
		connector2.addBox(2F, 6F, -2F, 6, 4, 4);
		connector2.setRotationPoint(0F, 8F, 0F);
		connector2.setTextureSize(128, 64);
		connector2.mirror = true;
		setRotation(connector2, 0F, 0F, 0F);
		connector3 = new ModelRenderer(this, 33, 18);
		connector3.addBox(2F, 6F, -2F, 6, 4, 4);
		connector3.setRotationPoint(0F, 8F, 0F);
		connector3.setTextureSize(128, 64);
		connector3.mirror = true;
		setRotation(connector3, 0F, 3.141593F, 0F);
		connector4 = new ModelRenderer(this, 54, 0);
		connector4.addBox(2F, 6F, -2F, 6, 4, 4);
		connector4.setRotationPoint(0F, 8F, 0F);
		connector4.setTextureSize(128, 64);
		connector4.mirror = true;
		setRotation(connector4, 0F, 4.712389F, 0F);
	}

	@Override
	public void render(float boxTranslation)
	{
		super.render(boxTranslation);

		cell.render(boxTranslation);
		connector1.render(boxTranslation);
		connector2.render(boxTranslation);
		connector3.render(boxTranslation);
		connector4.render(boxTranslation);
	}
}
