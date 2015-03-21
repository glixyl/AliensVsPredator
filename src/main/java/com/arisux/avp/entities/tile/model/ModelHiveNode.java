package com.arisux.avp.entities.tile.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHiveNode extends ModelBase
{
	ModelRenderer node;
	ModelRenderer base0;
	ModelRenderer base1;
	ModelRenderer base2;

	public ModelHiveNode()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.node = new ModelRenderer(this, 48, 0);
		this.node.addBox(-2.0F, 11.0F, -2.0F, 4, 8, 4);
		this.node.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.node.setTextureSize(128, 64);
		this.node.mirror = true;
		this.setRotation(this.node, 0.0F, 0.0F, 0.0F);
		this.base0 = new ModelRenderer(this, 0, 0);
		this.base0.addBox(-6.0F, 22.0F, -6.0F, 12, 2, 12);
		this.base0.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.base0.setTextureSize(128, 64);
		this.base0.mirror = true;
		this.setRotation(this.base0, 0.0F, 0.0F, 0.0F);
		this.base1 = new ModelRenderer(this, 0, 14);
		this.base1.addBox(-5.0F, 20.0F, -5.0F, 10, 2, 10);
		this.base1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.base1.setTextureSize(128, 64);
		this.base1.mirror = true;
		this.setRotation(this.base1, 0.0F, 0.0F, 0.0F);
		this.base2 = new ModelRenderer(this, 0, 26);
		this.base2.addBox(-4.0F, 19.0F, -4.0F, 8, 1, 8);
		this.base2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.base2.setTextureSize(128, 64);
		this.base2.mirror = true;
		this.setRotation(this.base2, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void render(Entity entity, float swingProgress, float swingProgressPrev, float idleProgress, float headYaw, float headPitch, float boxTranslation)
	{
		super.render(entity, swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation);
		this.setRotationAngles(swingProgress, swingProgressPrev, idleProgress, headYaw, headPitch, boxTranslation, entity);
		this.node.render(boxTranslation);
		this.base0.render(boxTranslation);
		this.base1.render(boxTranslation);
		this.base2.render(boxTranslation);
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
