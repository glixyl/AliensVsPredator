package com.arisux.avp.entities.mob.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOvamorph extends ModelBase
{
	public ModelRenderer ovamorph;

	public ModelOvamorph()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.setTextureOffset("AlienEggPiece1.Shape1", 0, 0);
		this.setTextureOffset("AlienEggPiece1.Shape2", 0, 25);
		this.setTextureOffset("AlienEggPiece1.Shape3", 0, 12);
		this.ovamorph = new ModelRenderer(this, "AlienEggPiece1");
		this.ovamorph.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.setRotation(this.ovamorph, 0.0F, 0.0F, 0.0F);
		this.ovamorph.mirror = true;
		this.ovamorph.addBox("Shape1", -3.5F, -6.0F, -3.5F, 7, 5, 7);
		this.ovamorph.addBox("Shape2", -1.5F, -9.0F, -1.5F, 3, 3, 3);
		this.ovamorph.addBox("Shape3", -2.5F, -8.0F, -2.5F, 5, 8, 5);
	}

	@Override
	public void render(Entity entity, float swingProgress, float swingProgressPrev, float idleProgress, float headYaw, float headPitch, float boxTranslation)
	{
		super.render(entity, swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation);
		this.setRotationAngles(swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation, entity);
		this.ovamorph.render(boxTranslation);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float swingProgress, float swingProgressPrev, float idleProgress, float headYaw, float headPitch, float boxTranslation, Entity entity)
	{
		super.setRotationAngles(swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation, entity);
	}
}
