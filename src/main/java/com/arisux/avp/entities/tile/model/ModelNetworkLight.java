package com.arisux.avp.entities.tile.model;

import com.arisux.avp.entities.tile.TileEntityNetworkLight;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelNetworkLight extends ModelBase
{
	ModelRenderer Shape1;
	ModelRenderer Shape2;

	public ModelNetworkLight()
	{
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 16, 8, 16);
		Shape1.setRotationPoint(-8F, 16F, -8F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 25);
		Shape2.addBox(0F, 0F, 0F, 4, 7, 4);
		Shape2.setRotationPoint(-2F, 9F, -2F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
	}

	public void render(TileEntityNetworkLight tile, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Shape1.render(f5);
		if(tile.getNetwork() != null) {
			Shape2.render(f5);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}

}
