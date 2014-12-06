package com.arisux.avp.entities.tile.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import com.arisux.avp.entities.tile.TileEntityNetworkLight;

public class ModelNetworkLight extends ModelBase
{
	ModelRenderer box0;
	ModelRenderer box1;

	public ModelNetworkLight()
	{
		textureWidth = 64;
		textureHeight = 32;

		box0 = new ModelRenderer(this, 0, 0);
		box0.addBox(0F, 0F, 0F, 16, 8, 16);
		box0.setRotationPoint(-8F, 16F, -8F);
		box0.setTextureSize(64, 32);
		box0.mirror = true;
		setRotation(box0, 0F, 0F, 0F);
		box1 = new ModelRenderer(this, 0, 25);
		box1.addBox(0F, 0F, 0F, 4, 7, 4);
		box1.setRotationPoint(-2F, 9F, -2F);
		box1.setTextureSize(64, 32);
		box1.mirror = true;
		setRotation(box1, 0F, 0F, 0F);
	}

	public void render(TileEntityNetworkLight tile, float boxTranslation)
	{
		this.setRotationAngles(tile);
		box0.render(boxTranslation);
		
		if (tile.getNetwork() != null)
		{
			box1.render(boxTranslation);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(TileEntityNetworkLight tile)
	{
		;
	}
}
