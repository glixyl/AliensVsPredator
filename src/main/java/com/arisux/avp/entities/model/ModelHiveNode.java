package com.arisux.avp.entities.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHiveNode extends ModelBase
{
	ModelRenderer Node;
	ModelRenderer Base1;
	ModelRenderer Base2;
	ModelRenderer Base3;

	public ModelHiveNode()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.Node = new ModelRenderer(this, 48, 0);
		this.Node.addBox(-2.0F, 11.0F, -2.0F, 4, 8, 4);
		this.Node.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Node.setTextureSize(128, 64);
		this.Node.mirror = true;
		this.setRotation(this.Node, 0.0F, 0.0F, 0.0F);
		this.Base1 = new ModelRenderer(this, 0, 0);
		this.Base1.addBox(-6.0F, 22.0F, -6.0F, 12, 2, 12);
		this.Base1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Base1.setTextureSize(128, 64);
		this.Base1.mirror = true;
		this.setRotation(this.Base1, 0.0F, 0.0F, 0.0F);
		this.Base2 = new ModelRenderer(this, 0, 14);
		this.Base2.addBox(-5.0F, 20.0F, -5.0F, 10, 2, 10);
		this.Base2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Base2.setTextureSize(128, 64);
		this.Base2.mirror = true;
		this.setRotation(this.Base2, 0.0F, 0.0F, 0.0F);
		this.Base3 = new ModelRenderer(this, 0, 26);
		this.Base3.addBox(-4.0F, 19.0F, -4.0F, 8, 1, 8);
		this.Base3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Base3.setTextureSize(128, 64);
		this.Base3.mirror = true;
		this.setRotation(this.Base3, 0.0F, 0.0F, 0.0F);
	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.Node.render(f5);
		this.Base1.render(f5);
		this.Base2.render(f5);
		this.Base3.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	/**
	 * Sets the model's various rotation angles. For bipeds, par1 and par2
	 * are used for animating the movement of arms and legs, where par1
	 * represents the time(so that arms and legs swing back and forth) and
	 * par2 represents how "far" arms and legs can swing at most.
	 */
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
