package com.arisux.avp.entities.tile.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import com.arisux.avp.entities.tile.TileEntityServer;

public class ModelServer extends ModelBase
{
	ModelRenderer BlockBottom;
	ModelRenderer BlockCenterLeft;
	ModelRenderer BlockCenterRight;
	ModelRenderer PlateLeftFront;
	ModelRenderer BlockTop;

	public ModelServer()
	{
		textureWidth = 512;
		textureHeight = 32;

		BlockBottom = new ModelRenderer(this, 0, 0);
		BlockBottom.addBox(0F, 0F, 0F, 16, 16, 16);
		BlockBottom.setRotationPoint(-8F, 8F, -8F);
		BlockBottom.setTextureSize(512, 32);
		BlockBottom.mirror = true;
		setRotation(BlockBottom, 0F, 0F, 0F);
		BlockCenterLeft = new ModelRenderer(this, 68, 0);
		BlockCenterLeft.addBox(0F, 0F, 0F, 7, 12, 8);
		BlockCenterLeft.setRotationPoint(-8F, -4F, 0F);
		BlockCenterLeft.setTextureSize(512, 32);
		BlockCenterLeft.mirror = true;
		setRotation(BlockCenterLeft, 0F, 0F, 0F);
		BlockCenterRight = new ModelRenderer(this, 102, 0);
		BlockCenterRight.addBox(0F, 0F, 0F, 9, 12, 16);
		BlockCenterRight.setRotationPoint(-1F, -4F, -8F);
		BlockCenterRight.setTextureSize(512, 32);
		BlockCenterRight.mirror = true;
		setRotation(BlockCenterRight, 0F, 0F, 0F);
		PlateLeftFront = new ModelRenderer(this, 156, 0);
		PlateLeftFront.addBox(0F, 0F, 0F, 1, 12, 8);
		PlateLeftFront.setRotationPoint(-8F, -4F, -8F);
		PlateLeftFront.setTextureSize(512, 32);
		PlateLeftFront.mirror = true;
		setRotation(PlateLeftFront, 0F, 0F, 0F);
		BlockTop = new ModelRenderer(this, 179, 0);
		BlockTop.addBox(0F, 0F, 0F, 16, 12, 16);
		BlockTop.setRotationPoint(-8F, -16F, -8F);
		BlockTop.setTextureSize(512, 32);
		BlockTop.mirror = true;
		setRotation(BlockTop, 0F, 0F, 0F);
	}

	public void render(TileEntityServer tile, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5);
		BlockBottom.render(f5);
		BlockCenterLeft.render(f5);
		BlockCenterRight.render(f5);
		PlateLeftFront.render(f5);
		BlockTop.render(f5);
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
