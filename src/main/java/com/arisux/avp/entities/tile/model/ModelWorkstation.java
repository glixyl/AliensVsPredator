package com.arisux.avp.entities.tile.model;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.init.Blocks;

import com.arisux.avp.AliensVsPredator;
import com.arisux.avp.entities.tile.TileEntityWorkstation;

public class ModelWorkstation extends ModelBase
{
	ModelRenderer Pillar;
	ModelRenderer Pillarbase1;
	ModelRenderer Desk;
	ModelRenderer MainArm;
	ModelRenderer LeftArm;
	ModelRenderer ArmLeft;
	ModelRenderer ArmCenter;
	ModelRenderer ArmRight;
	ModelRenderer RightArm;
	ModelRenderer ScreenLeft;
	ModelRenderer ScreenCenter;
	ModelRenderer ScreenRight;

	public ModelWorkstation()
	{
		textureWidth = 128;
		textureHeight = 64;

		Pillar = new ModelRenderer(this, 42, 21);
		Pillar.addBox(0F, 0F, 0F, 10, 18, 4);
		Pillar.setRotationPoint(-5F, 4F, 2F);
		Pillar.setTextureSize(128, 64);
		Pillar.mirror = true;
		setRotation(Pillar, 0F, 0F, 0F);
		Pillarbase1 = new ModelRenderer(this, 1, 17);
		Pillarbase1.addBox(0F, 0F, 0F, 16, 2, 4);
		Pillarbase1.setRotationPoint(-8F, 22F, 2F);
		Pillarbase1.setTextureSize(128, 64);
		Pillarbase1.mirror = true;
		setRotation(Pillarbase1, 0F, 0F, 0F);
		Desk = new ModelRenderer(this, 42, 1);
		Desk.addBox(0F, 0F, 0F, 16, 2, 16);
		Desk.setRotationPoint(-8F, 6F, -10.6F);
		Desk.setTextureSize(128, 64);
		Desk.mirror = true;
		setRotation(Desk, 0.1115358F, 0F, 0F);
		MainArm = new ModelRenderer(this, 1, 7);
		MainArm.addBox(0F, 0F, 0F, 4, 8, 1);
		MainArm.setRotationPoint(-2F, -4F, 5F);
		MainArm.setTextureSize(128, 64);
		MainArm.mirror = true;
		setRotation(MainArm, 0F, 0F, 0F);
		LeftArm = new ModelRenderer(this, 1, 1);
		LeftArm.addBox(-6F, 0F, 0F, 6, 4, 1);
		LeftArm.setRotationPoint(-8F, -4F, 6F);
		LeftArm.setTextureSize(128, 64);
		LeftArm.mirror = true;
		setRotation(LeftArm, 3.141593F, -0.3346145F, 0F);
		ArmLeft = new ModelRenderer(this, 1, 1);
		ArmLeft.addBox(0F, 0F, 0F, 6, 4, 1);
		ArmLeft.setRotationPoint(-8F, -4F, 6F);
		ArmLeft.setTextureSize(128, 64);
		ArmLeft.mirror = true;
		setRotation(ArmLeft, 3.141593F, 0F, 0F);
		ArmCenter = new ModelRenderer(this, 16, 1);
		ArmCenter.addBox(0F, 0F, 0F, 4, 4, 1);
		ArmCenter.setRotationPoint(-2F, -4F, 6F);
		ArmCenter.setTextureSize(128, 64);
		ArmCenter.mirror = true;
		setRotation(ArmCenter, 3.141593F, 0F, 0F);
		ArmRight = new ModelRenderer(this, 1, 1);
		ArmRight.addBox(0F, 0F, 0F, 6, 4, 1);
		ArmRight.setRotationPoint(2F, -4F, 6F);
		ArmRight.setTextureSize(128, 64);
		ArmRight.mirror = true;
		setRotation(ArmRight, 3.141593F, 0F, 0F);
		RightArm = new ModelRenderer(this, 1, 1);
		RightArm.addBox(0F, 0F, 0F, 6, 4, 1);
		RightArm.setRotationPoint(8F, -4F, 6F);
		RightArm.setTextureSize(128, 64);
		RightArm.mirror = true;
		setRotation(RightArm, 3.141593F, 0.3346075F, 0F);
		ScreenLeft = new ModelRenderer(this, 1, 24);
		ScreenLeft.addBox(-16F, 0F, 0F, 16, 9, 1);
		ScreenLeft.setRotationPoint(-8F, -10F, 4F);
		ScreenLeft.setTextureSize(128, 64);
		ScreenLeft.mirror = true;
		setRotation(ScreenLeft, 0.1115358F, -0.3346075F, 0F);
		ScreenCenter = new ModelRenderer(this, 1, 24);
		ScreenCenter.addBox(0F, 0F, 0F, 16, 9, 1);
		ScreenCenter.setRotationPoint(-8F, -10F, 4F);
		ScreenCenter.setTextureSize(128, 64);
		ScreenCenter.mirror = true;
		setRotation(ScreenCenter, 0.1115358F, 0F, 0F);
		ScreenRight = new ModelRenderer(this, 1, 24);
		ScreenRight.addBox(0F, 0F, 0F, 16, 9, 1);
		ScreenRight.setRotationPoint(8F, -10F, 4F);
		ScreenRight.setTextureSize(128, 64);
		ScreenRight.mirror = true;
		setRotation(ScreenRight, 0.111544F, 0.3346145F, 0F);
	}

	public void render(TileEntityWorkstation tile, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5, tile);
		Block b = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord);
		Block left, leftUp;
		Block right, rightUp;
		
		switch (tile.rotation)
		{
			case 3:
				/* Left is plus z, right is minus z */
				leftUp = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord + 1, tile.zCoord + 1);
				rightUp = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord + 1, tile.zCoord - 1);
				left = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord + 1);
				right = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord - 1);
				break;
			case 2:
				/* Left is minus x, right is plus x */
				leftUp = tile.getWorldObj().getBlock(tile.xCoord + 1, tile.yCoord + 1, tile.zCoord);
				rightUp = tile.getWorldObj().getBlock(tile.xCoord - 1, tile.yCoord + 1, tile.zCoord);
				left = tile.getWorldObj().getBlock(tile.xCoord + 1, tile.yCoord, tile.zCoord);
				right = tile.getWorldObj().getBlock(tile.xCoord - 1, tile.yCoord, tile.zCoord);
				break;
			case 1:
				/* Left is minus z, right is plus z */
				leftUp = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord + 1, tile.zCoord - 1);
				rightUp = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord + 1, tile.zCoord + 1);
				left = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord - 1);
				right = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord + 1);
				break;
			case 0:
				/* Left is plus x, right is minus x, case 4 */
				leftUp = tile.getWorldObj().getBlock(tile.xCoord - 1, tile.yCoord + 1, tile.zCoord);
				rightUp = tile.getWorldObj().getBlock(tile.xCoord + 1, tile.yCoord + 1, tile.zCoord);
				left = tile.getWorldObj().getBlock(tile.xCoord - 1, tile.yCoord, tile.zCoord);
				right = tile.getWorldObj().getBlock(tile.xCoord + 1, tile.yCoord, tile.zCoord);
				break;
			default:
				left = b;
				right = b;
				leftUp = b;
				rightUp = b;
				break;
		}
		Pillar.render(f5);
		Pillarbase1.render(f5);
		Desk.render(f5);
		MainArm.render(f5);
		ArmLeft.render(f5);
		ArmCenter.render(f5);
		ArmRight.render(f5);
		ScreenCenter.render(f5);
		
		if (left != AliensVsPredator.instance.blocks.blockWorkstation && leftUp == Blocks.air && left.getBlockBoundsMaxY() <= 1.5)
		{
			LeftArm.render(f5);
			ScreenLeft.render(f5);
		}
		
		if (right != AliensVsPredator.instance.blocks.blockWorkstation && rightUp == Blocks.air  && right.getBlockBoundsMaxY() <= 1.5)
		{
			RightArm.render(f5);
			ScreenRight.render(f5);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, TileEntityWorkstation tile)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}
}