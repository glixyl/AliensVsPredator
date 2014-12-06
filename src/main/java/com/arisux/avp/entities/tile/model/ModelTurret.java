package com.arisux.avp.entities.tile.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import com.arisux.avp.entities.tile.TileEntityTurret;

public class ModelTurret extends ModelBase
{
	private ModelRenderer barrel,
		supportLeft,
		headBase2,
		neckBase,
		headBase3,
		headBase,
		barrelGuard,
		sightBase3,
		rightFoot,
		supportRight,
		supportCenter,
		centerFoot,
		legHub,
		LeftFoot,
		neck,
		leftLeg,
		rightLeg,
		centerLeg;

	public ModelTurret()
	{
		textureWidth = 64;
		textureHeight = 32;

		barrel = new ModelRenderer(this, 19, 22);
		barrel.addBox(-0.5F, -1.2F, 10F, 1, 1, 6);
		barrel.setRotationPoint(0F, 11F, 0F);
		barrel.setTextureSize(64, 32);
		barrel.mirror = true;
		setRotation(barrel, 0F, 0F, 0F);
		supportLeft = new ModelRenderer(this, 43, 22);
		supportLeft.addBox(-0.5F, -0.8F, -3F, 1, 4, 1);
		supportLeft.setRotationPoint(0F, 11F, 0F);
		supportLeft.setTextureSize(64, 32);
		supportLeft.mirror = true;
		setRotation(supportLeft, 0.6108652F, -2.617994F, 0F);
		headBase2 = new ModelRenderer(this, 19, 0);
		headBase2.addBox(-2F, -3F, -3.5F, 4, 4, 7);
		headBase2.setRotationPoint(0F, 11F, 0F);
		headBase2.setTextureSize(64, 32);
		headBase2.mirror = true;
		setRotation(headBase2, 0F, 0F, 0F);
		neckBase = new ModelRenderer(this, 42, 0);
		neckBase.addBox(-1.5F, 3.6F, -1.5F, 3, 1, 3);
		neckBase.setRotationPoint(0F, 11F, 0F);
		neckBase.setTextureSize(64, 32);
		neckBase.mirror = true;
		setRotation(neckBase, 0F, 0.7853982F, 0F);
		headBase3 = new ModelRenderer(this, 19, 12);
		headBase3.addBox(-2.5F, -1F, -4F, 5, 1, 8);
		headBase3.setRotationPoint(0F, 11F, 0F);
		headBase3.setTextureSize(64, 32);
		headBase3.mirror = true;
		setRotation(headBase3, 0F, 0F, 0F);
		headBase = new ModelRenderer(this, 0, 0);
		headBase.addBox(-1.5F, -1F, -1.5F, 3, 3, 3);
		headBase.setRotationPoint(0F, 11F, 0F);
		headBase.setTextureSize(64, 32);
		headBase.mirror = true;
		setRotation(headBase, 0F, 0F, 0F);
		barrelGuard = new ModelRenderer(this, 0, 9);
		barrelGuard.addBox(-1F, -1.5F, 3.5F, 2, 2, 7);
		barrelGuard.setRotationPoint(0F, 11F, 0F);
		barrelGuard.setTextureSize(64, 32);
		barrelGuard.mirror = true;
		setRotation(barrelGuard, 0F, 0F, 0F);
		sightBase3 = new ModelRenderer(this, 0, 19);
		sightBase3.addBox(-0.5F, -2.2F, 3.5F, 1, 2, 8);
		sightBase3.setRotationPoint(0F, 11F, 0F);
		sightBase3.setTextureSize(64, 32);
		sightBase3.mirror = true;
		setRotation(sightBase3, 0F, 0F, 0F);
		rightFoot = new ModelRenderer(this, 53, 5);
		rightFoot.addBox(2.1F, 12F, 0F, 2, 1, 3);
		rightFoot.setRotationPoint(0F, 11F, 0F);
		rightFoot.setTextureSize(64, 32);
		rightFoot.mirror = true;
		setRotation(rightFoot, 0F, -2.495821F, 0F);
		supportRight = new ModelRenderer(this, 43, 22);
		supportRight.addBox(-0.5F, -0.8F, -3F, 1, 4, 1);
		supportRight.setRotationPoint(0F, 11F, 0F);
		supportRight.setTextureSize(64, 32);
		supportRight.mirror = true;
		setRotation(supportRight, 0.6108652F, 2.617994F, 0F);
		supportCenter = new ModelRenderer(this, 34, 22);
		supportCenter.addBox(-0.5F, -0.8F, -3F, 1, 4, 3);
		supportCenter.setRotationPoint(0F, 11F, 0F);
		supportCenter.setTextureSize(64, 32);
		supportCenter.mirror = true;
		setRotation(supportCenter, 0.6108652F, 0F, 0F);
		centerFoot = new ModelRenderer(this, 53, 5);
		centerFoot.addBox(-1F, 12F, 6.1F, 2, 1, 3);
		centerFoot.setRotationPoint(0F, 11F, 0F);
		centerFoot.setTextureSize(64, 32);
		centerFoot.mirror = true;
		setRotation(centerFoot, 0F, 0F, 0F);
		legHub = new ModelRenderer(this, 42, 5);
		legHub.addBox(-1.5F, 8.2F, -4.5F, 3, 3, 2);
		legHub.setRotationPoint(0F, 11F, 0F);
		legHub.setTextureSize(64, 32);
		legHub.mirror = true;
		setRotation(legHub, 0.8726646F, 0F, 0F);
		LeftFoot = new ModelRenderer(this, 53, 5);
		LeftFoot.addBox(-3.9F, 12F, 0F, 2, 1, 3);
		LeftFoot.setRotationPoint(0F, 11F, 0F);
		LeftFoot.setTextureSize(64, 32);
		LeftFoot.mirror = true;
		setRotation(LeftFoot, 0F, 2.530727F, 0F);
		neck = new ModelRenderer(this, 46, 12);
		neck.addBox(-1F, 2.2F, -4F, 2, 8, 1);
		neck.setRotationPoint(0F, 11F, 0F);
		neck.setTextureSize(64, 32);
		neck.mirror = true;
		setRotation(neck, 0.8726646F, 0F, 0F);
		leftLeg = new ModelRenderer(this, 53, 12);
		leftLeg.addBox(-3.4F, 2.8F, -10F, 1, 7, 2);
		leftLeg.setRotationPoint(0F, 11F, 0F);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.8726646F, 2.530727F, 0F);
		rightLeg = new ModelRenderer(this, 53, 12);
		rightLeg.addBox(2.6F, 2.8F, -10F, 1, 7, 2);
		rightLeg.setRotationPoint(0F, 11F, 0F);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.8726646F, -2.495821F, 0F);
		centerLeg = new ModelRenderer(this, 48, 22);
		centerLeg.addBox(-1F, 10.4F, 0.1F, 2, 5, 1);
		centerLeg.setRotationPoint(0F, 11F, 0F);
		centerLeg.setTextureSize(64, 32);
		centerLeg.mirror = true;
		setRotation(centerLeg, 0.4712389F, 0F, 0F);
	}

	public void render(TileEntityTurret tile, float boxTranslation)
	{
		this.setRotationAngles(tile);
		barrel.render(boxTranslation);
		supportLeft.render(boxTranslation);
		headBase2.render(boxTranslation);
		neckBase.render(boxTranslation);
		headBase3.render(boxTranslation);
		headBase.render(boxTranslation);
		barrelGuard.render(boxTranslation);
		sightBase3.render(boxTranslation);
		rightFoot.render(boxTranslation);
		supportRight.render(boxTranslation);
		supportCenter.render(boxTranslation);
		centerFoot.render(boxTranslation);
		legHub.render(boxTranslation);
		LeftFoot.render(boxTranslation);
		neck.render(boxTranslation);
		leftLeg.render(boxTranslation);
		rightLeg.render(boxTranslation);
		centerLeg.render(boxTranslation);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(TileEntityTurret tile)
	{
		if (tile != null && tile.getEntity() != null)
		{
			float rotationYaw = -tile.getRotationYaw() / (180F / (float) Math.PI);
			float rotationPitch = -tile.getRotationPitch() / (180F / (float) Math.PI);

			barrel.rotateAngleY = rotationYaw;
			barrel.rotateAngleX = rotationPitch;
			headBase.rotateAngleY = rotationYaw;
			headBase.rotateAngleX = rotationPitch;
			headBase3.rotateAngleY = rotationYaw;
			headBase3.rotateAngleX = rotationPitch;
			barrelGuard.rotateAngleY = rotationYaw;
			barrelGuard.rotateAngleX = rotationPitch;
			sightBase3.rotateAngleY = rotationYaw;
			sightBase3.rotateAngleX = rotationPitch;
			headBase2.rotateAngleY = rotationYaw;
			headBase2.rotateAngleX = rotationPitch;
		}
	}
}
