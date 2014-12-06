package com.arisux.avp.entities.tile.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import com.arisux.avp.entities.tile.TileEntityServer;

public class ModelServer extends ModelBase
{
	ModelRenderer boxBottom;
	ModelRenderer boxMidLeft;
	ModelRenderer boxMidRight;
	ModelRenderer plateLeft;
	ModelRenderer boxTop;

	public ModelServer()
	{
		textureWidth = 512;
		textureHeight = 32;

		boxBottom = new ModelRenderer(this, 0, 0);
		boxBottom.addBox(0F, 0F, 0F, 16, 16, 16);
		boxBottom.setRotationPoint(-8F, 8F, -8F);
		boxBottom.setTextureSize(512, 32);
		boxBottom.mirror = true;
		setRotation(boxBottom, 0F, 0F, 0F);
		boxMidLeft = new ModelRenderer(this, 68, 0);
		boxMidLeft.addBox(0F, 0F, 0F, 7, 12, 8);
		boxMidLeft.setRotationPoint(-8F, -4F, 0F);
		boxMidLeft.setTextureSize(512, 32);
		boxMidLeft.mirror = true;
		setRotation(boxMidLeft, 0F, 0F, 0F);
		boxMidRight = new ModelRenderer(this, 102, 0);
		boxMidRight.addBox(0F, 0F, 0F, 9, 12, 16);
		boxMidRight.setRotationPoint(-1F, -4F, -8F);
		boxMidRight.setTextureSize(512, 32);
		boxMidRight.mirror = true;
		setRotation(boxMidRight, 0F, 0F, 0F);
		plateLeft = new ModelRenderer(this, 156, 0);
		plateLeft.addBox(0F, 0F, 0F, 1, 12, 8);
		plateLeft.setRotationPoint(-8F, -4F, -8F);
		plateLeft.setTextureSize(512, 32);
		plateLeft.mirror = true;
		setRotation(plateLeft, 0F, 0F, 0F);
		boxTop = new ModelRenderer(this, 179, 0);
		boxTop.addBox(0F, 0F, 0F, 16, 12, 16);
		boxTop.setRotationPoint(-8F, -16F, -8F);
		boxTop.setTextureSize(512, 32);
		boxTop.mirror = true;
		setRotation(boxTop, 0F, 0F, 0F);
	}

	public void render(TileEntityServer tile, float boxTranslation)
	{
		setRotationAngles(tile);
		boxBottom.render(boxTranslation);
		boxMidLeft.render(boxTranslation);
		boxMidRight.render(boxTranslation);
		plateLeft.render(boxTranslation);
		boxTop.render(boxTranslation);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(TileEntityServer tile)
	{
		;
	}
}
