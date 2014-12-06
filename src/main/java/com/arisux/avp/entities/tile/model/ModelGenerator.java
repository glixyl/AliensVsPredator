package com.arisux.avp.entities.tile.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import com.arisux.avp.entities.tile.TileEntityGenerator;

public class ModelGenerator extends ModelBase
{
	ModelRenderer base;
	ModelRenderer box0;
	ModelRenderer box1;
	ModelRenderer box2;
	ModelRenderer box3;

	public ModelGenerator()
	{
		textureWidth = 512;
		textureHeight = 32;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(0F, 0F, 0F, 16, 1, 16);
		base.setRotationPoint(-8F, 23F, -8F);
		base.setTextureSize(512, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		box0 = new ModelRenderer(this, 69, 0);
		box0.addBox(0F, 0F, 0F, 8, 5, 7);
		box0.setRotationPoint(-4F, 18F, -7F);
		box0.setTextureSize(512, 32);
		box0.mirror = true;
		setRotation(box0, 0F, 0F, 0F);
		box1 = new ModelRenderer(this, 104, 0);
		box1.addBox(0F, 0F, 0F, 1, 1, 4);
		box1.setRotationPoint(-0.8666667F, 22F, 0F);
		box1.setTextureSize(512, 32);
		box1.mirror = true;
		setRotation(box1, 0F, 0F, 0F);
		box2 = new ModelRenderer(this, 119, 0);
		box2.addBox(0F, 0F, 0F, 5, 2, 3);
		box2.setRotationPoint(-3F, 21F, 4F);
		box2.setTextureSize(512, 32);
		box2.mirror = true;
		setRotation(box2, 0F, 0F, 0F);
		box3 = new ModelRenderer(this, 139, 0);
		box3.addBox(0F, 0F, 0F, 12, 7, 1);
		box3.setRotationPoint(-6F, 16F, -8F);
		box3.setTextureSize(512, 32);
		box3.mirror = true;
		setRotation(box3, 0F, 0F, 0F);
	}

	public void render(TileEntityGenerator tile, float boxTranslation)
	{
		this.setRotationAngles(tile);
		base.render(boxTranslation);
		box0.render(boxTranslation);
		box1.render(boxTranslation);
		box2.render(boxTranslation);
		box3.render(boxTranslation);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(TileEntityGenerator tile)
	{
		;
	}
}
