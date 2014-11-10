package com.arisux.avp.entities.tile.model;

import com.arisux.avp.entities.tile.TileEntityBlastdoor;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBlastdoor extends ModelBase
{
	ModelRenderer DoorLeftCorner1;
	ModelRenderer DoorLeftCorner2;
	ModelRenderer DoorLeftMain;
	ModelRenderer DoorLeftDiagonal2;
	ModelRenderer DoorLeftDiagonal1;
	ModelRenderer DoorRightMain;
	ModelRenderer DoorRightArrow;
	ModelRenderer FrameLeft;
	ModelRenderer FrameBaseCenterTop;
	ModelRenderer Bar_3;
	ModelRenderer FrameBaseCenter;
	ModelRenderer Bar_2;
	ModelRenderer FrameRight;
	ModelRenderer Bar_1;
	ModelRenderer Bar_4;

	public ModelBlastdoor()
	{
		textureWidth = 512;
		textureHeight = 256;

		DoorLeftCorner1 = new ModelRenderer(this, 141, 0);
		DoorLeftCorner1.addBox(16F, 2F, 0F, 12, 11, 6);
		DoorLeftCorner1.setRotationPoint(-4F, -23F, 0F);
		DoorLeftCorner1.setTextureSize(64, 32);
		DoorLeftCorner1.mirror = true;
		setRotation(DoorLeftCorner1, 0F, 0F, 0F);
		DoorLeftCorner2 = new ModelRenderer(this, 141, 0);
		DoorLeftCorner2.addBox(16F, 35F, 0F, 12, 13, 6);
		DoorLeftCorner2.setRotationPoint(-4F, -26F, 0F);
		DoorLeftCorner2.setTextureSize(64, 32);
		DoorLeftCorner2.mirror = true;
		setRotation(DoorLeftCorner2, 0F, 0F, 0F);
		DoorLeftMain = new ModelRenderer(this, 182, 0);
		DoorLeftMain.addBox(-1F, 0F, 0F, 17, 45, 6);
		DoorLeftMain.setRotationPoint(-4F, -23F, 0F);
		DoorLeftMain.setTextureSize(64, 32);
		DoorLeftMain.mirror = true;
		setRotation(DoorLeftMain, 0F, 0F, 0F);
		DoorLeftDiagonal2 = new ModelRenderer(this, 103, 2);
		DoorLeftDiagonal2.addBox(-12.7F, 26.3F, 0F, 10, 16, 6);
		DoorLeftDiagonal2.setRotationPoint(-4F, -23F, 0F);
		DoorLeftDiagonal2.setTextureSize(64, 32);
		DoorLeftDiagonal2.mirror = true;
		setRotation(DoorLeftDiagonal2, 0F, 0F, -0.7853982F);
		DoorLeftDiagonal1 = new ModelRenderer(this, 106, 30);
		DoorLeftDiagonal1.addBox(-5.1F, 18.8F, 0F, 16, 10, 6);
		DoorLeftDiagonal1.setRotationPoint(-4F, -23F, 0F);
		DoorLeftDiagonal1.setTextureSize(64, 32);
		DoorLeftDiagonal1.mirror = true;
		setRotation(DoorLeftDiagonal1, 0F, 0F, -0.7853982F);
		DoorRightMain = new ModelRenderer(this, 235, 0);
		DoorRightMain.addBox(0F, 0F, 0F, 29, 45, 6);
		DoorRightMain.setRotationPoint(24F, -23F, -4F);
		DoorRightMain.setTextureSize(64, 32);
		DoorRightMain.mirror = true;
		setRotation(DoorRightMain, 0F, 0F, 0F);
		DoorRightArrow = new ModelRenderer(this, 307, 0);
		DoorRightArrow.addBox(9F, 9F, 0F, 16, 16, 6);
		DoorRightArrow.setRotationPoint(24F, -23F, -4F);
		DoorRightArrow.setTextureSize(64, 32);
		DoorRightArrow.mirror = true;
		setRotation(DoorRightArrow, 0F, 0F, 0.7853982F);
		FrameLeft = new ModelRenderer(this, 82, 52);
		FrameLeft.addBox(0.1333333F, -1.2F, 0F, 3, 17, 16);
		FrameLeft.setRotationPoint(-8F, -8F, -8F);
		FrameLeft.setTextureSize(64, 32);
		FrameLeft.mirror = true;
		setRotation(FrameLeft, 0F, 0F, 0F);
		FrameBaseCenterTop = new ModelRenderer(this, 1, 28);
		FrameBaseCenterTop.addBox(0F, 0F, 0F, 34, 3, 16);
		FrameBaseCenterTop.setRotationPoint(7F, -24F, -8F);
		FrameBaseCenterTop.setTextureSize(64, 32);
		FrameBaseCenterTop.mirror = true;
		setRotation(FrameBaseCenterTop, 0F, 0F, 0F);
		Bar_3 = new ModelRenderer(this, 1, 52);
		Bar_3.addBox(0F, 0F, 0F, 3, 22, 16);
		Bar_3.setRotationPoint(41F, 24F, -8F);
		Bar_3.setTextureSize(64, 32);
		Bar_3.mirror = true;
		setRotation(Bar_3, 0F, 0F, -2.414427F);
		FrameBaseCenter = new ModelRenderer(this, 1, 8);
		FrameBaseCenter.addBox(0F, 0F, 0F, 34, 2, 16);
		FrameBaseCenter.setRotationPoint(7F, 22F, -8F);
		FrameBaseCenter.setTextureSize(64, 32);
		FrameBaseCenter.mirror = true;
		setRotation(FrameBaseCenter, 0F, 0F, 0F);
		Bar_2 = new ModelRenderer(this, 43, 52);
		Bar_2.addBox(-3F, 0F, 0F, 3, 21, 16);
		Bar_2.setRotationPoint(41F, -24F, -8F);
		Bar_2.setTextureSize(64, 32);
		Bar_2.mirror = true;
		setRotation(Bar_2, 0F, 0F, -0.7912101F);
		FrameRight = new ModelRenderer(this, 82, 52);
		FrameRight.addBox(-0.1F, -0.2F, 0F, 3, 17, 16);
		FrameRight.setRotationPoint(53F, -9F, -8F);
		FrameRight.setTextureSize(64, 32);
		FrameRight.mirror = true;
		setRotation(FrameRight, 0F, 0F, 0F);
		Bar_1 = new ModelRenderer(this, 43, 52);
		Bar_1.addBox(0F, 0F, 0F, 3, 21, 16);
		Bar_1.setRotationPoint(7F, -24F, -8F);
		Bar_1.setTextureSize(64, 32);
		Bar_1.mirror = true;
		setRotation(Bar_1, 0F, 0F, 0.7912159F);
		Bar_4 = new ModelRenderer(this, 1, 52);
		Bar_4.addBox(-3F, 0F, 0F, 3, 22, 16);
		Bar_4.setRotationPoint(7F, 24F, -8F);
		Bar_4.setTextureSize(64, 32);
		Bar_4.mirror = true;
		setRotation(Bar_4, 0F, 0F, 2.414419F);
	}

	public void render(TileEntityBlastdoor entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5);
		DoorLeftCorner1.render(f5);
		DoorLeftCorner2.render(f5);
		DoorLeftMain.render(f5);
		DoorLeftDiagonal2.render(f5);
		DoorLeftDiagonal1.render(f5);
		DoorRightMain.render(f5);
		DoorRightArrow.render(f5);
		FrameLeft.render(f5);
		FrameBaseCenterTop.render(f5);
		Bar_3.render(f5);
		FrameBaseCenter.render(f5);
		Bar_2.render(f5);
		FrameRight.render(f5);
		Bar_1.render(f5);
		Bar_4.render(f5);
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
