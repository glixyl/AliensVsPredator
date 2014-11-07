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
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.ovamorph.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
