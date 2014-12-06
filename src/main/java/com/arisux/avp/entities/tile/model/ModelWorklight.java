package com.arisux.avp.entities.tile.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import com.arisux.avp.entities.tile.TileEntityWorklight;

public class ModelWorklight extends ModelBase
{
	ModelRenderer base0;
	ModelRenderer base1;
	ModelRenderer base3;
	ModelRenderer box0;
	ModelRenderer box1;
	ModelRenderer box2;
	ModelRenderer box3;
	ModelRenderer battery0;
	ModelRenderer battery1;
	ModelRenderer rod0;
	ModelRenderer rod1;
	ModelRenderer rod2;
	ModelRenderer light0;
	ModelRenderer light1;
	ModelRenderer light2;
	ModelRenderer light3;

	public ModelWorklight()
	{
		textureWidth = 128;
		textureHeight = 512;

		base0 = new ModelRenderer(this, 0, 0);
		base0.addBox(0F, 0F, 0F, 16, 6, 8);
		base0.setRotationPoint(-8F, 18F, 0F);
		base0.setTextureSize(128, 512);
		base0.mirror = true;
		setRotation(base0, 0F, 0F, 0F);
		base1 = new ModelRenderer(this, 57, 0);
		base1.addBox(0F, 0F, 0F, 16, 1, 3);
		base1.setRotationPoint(-8F, 23F, -3F);
		base1.setTextureSize(128, 512);
		base1.mirror = true;
		setRotation(base1, 0F, 0F, 0F);
		box0 = new ModelRenderer(this, 61, 13);
		box0.addBox(0F, 0F, 0F, 9, 2, 4);
		box0.setRotationPoint(-8F, 20F, -3F);
		box0.setTextureSize(128, 512);
		box0.mirror = true;
		setRotation(box0, 0.5201907F, 0F, 0F);
		box1 = new ModelRenderer(this, 25, 27);
		box1.addBox(0F, 0F, -3F, 9, 3, 3);
		box1.setRotationPoint(-8F, 20F, 0F);
		box1.setTextureSize(128, 512);
		box1.mirror = true;
		setRotation(box1, 0F, 0F, 0F);
		battery0 = new ModelRenderer(this, 71, 26);
		battery0.addBox(0F, 0F, 0F, 5, 4, 3);
		battery0.setRotationPoint(2F, 19F, -2.5F);
		battery0.setTextureSize(128, 512);
		battery0.mirror = true;
		setRotation(battery0, 0F, 0F, 0F);
		battery1 = new ModelRenderer(this, 106, 0);
		battery1.addBox(0F, 0F, 0F, 3, 1, 2);
		battery1.setRotationPoint(3F, 18.5F, -1.5F);
		battery1.setTextureSize(128, 512);
		battery1.mirror = true;
		setRotation(battery1, 0.0371786F, 0F, 0F);
		base3 = new ModelRenderer(this, 26, 16);
		base3.addBox(0F, 0F, 0F, 5, 1, 5);
		base3.setRotationPoint(-2.5F, 17F, 1.5F);
		base3.setTextureSize(128, 512);
		base3.mirror = true;
		setRotation(base3, 0F, 0F, 0F);
		rod0 = new ModelRenderer(this, 0, 26);
		rod0.addBox(-1F, -57F, -1F, 2, 57, 2);
		rod0.setRotationPoint(0F, 17F, 4F);
		rod0.setTextureSize(128, 512);
		rod0.mirror = true;
		setRotation(rod0, 0F, 0.7853982F, 0F);
		light0 = new ModelRenderer(this, 19, 43);
		light0.addBox(-1.5F, -1.5F, -1.5F, 9, 5, 3);
		light0.setRotationPoint(-10F, -38.5F, 3F);
		light0.setTextureSize(128, 512);
		light0.mirror = true;
		setRotation(light0, 0.2230717F, 0F, 0F);
		light1 = new ModelRenderer(this, 51, 43);
		light1.addBox(-1.5F, -1.5F, -1.5F, 9, 5, 3);
		light1.setRotationPoint(4F, -38.5F, 3F);
		light1.setTextureSize(128, 512);
		light1.mirror = true;
		setRotation(light1, 0.2230717F, 0F, 0F);
		light2 = new ModelRenderer(this, 19, 43);
		light2.addBox(-1.5F, -1.5F, -1.5F, 9, 5, 3);
		light2.setRotationPoint(-10F, -28.5F, 3F);
		light2.setTextureSize(128, 512);
		light2.mirror = true;
		setRotation(light2, 0.2230717F, 0F, 0F);
		light3 = new ModelRenderer(this, 19, 43);
		light3.addBox(-1.5F, -1.5F, -1.5F, 9, 5, 3);
		light3.setRotationPoint(4F, -28.5F, 3F);
		light3.setTextureSize(128, 512);
		light3.mirror = true;
		setRotation(light3, 0.2230717F, 0F, 0F);
		box2 = new ModelRenderer(this, 10, 25);
		box2.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		box2.setRotationPoint(0F, -27F, 4F);
		box2.setTextureSize(128, 512);
		box2.mirror = true;
		setRotation(box2, 0F, 0F, 0F);
		rod2 = new ModelRenderer(this, 18, 54);
		rod2.addBox(-12F, -0.5F, -0.5F, 24, 1, 1);
		rod2.setRotationPoint(0F, -27F, 4F);
		rod2.setTextureSize(128, 512);
		rod2.mirror = true;
		setRotation(rod2, 0F, 0F, 0F);
		box3 = new ModelRenderer(this, 13, 35);
		box3.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		box3.setRotationPoint(0F, -37F, 4F);
		box3.setTextureSize(128, 512);
		box3.mirror = true;
		setRotation(box3, 0F, 0F, 0F);
		rod1 = new ModelRenderer(this, 24, 58);
		rod1.addBox(-12F, -0.5F, -0.5F, 24, 1, 1);
		rod1.setRotationPoint(0F, -37F, 4F);
		rod1.setTextureSize(128, 512);
		rod1.mirror = true;
		setRotation(rod1, 0F, 0F, 0F);
	}

	public void render(TileEntityWorklight tile, float boxTranslation)
	{
		setRotationAngles(tile);
		base0.render(boxTranslation);
		base1.render(boxTranslation);
		box0.render(boxTranslation);
		box1.render(boxTranslation);
		battery0.render(boxTranslation);
		battery1.render(boxTranslation);
		base3.render(boxTranslation);
		rod0.render(boxTranslation);
		light0.render(boxTranslation);
		light1.render(boxTranslation);
		light2.render(boxTranslation);
		light3.render(boxTranslation);
		box2.render(boxTranslation);
		rod2.render(boxTranslation);
		box3.render(boxTranslation);
		rod1.render(boxTranslation);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(TileEntityWorklight tile)
	{
		;
	}
}
