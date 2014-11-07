package com.arisux.avp.items.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMotionTracker extends ModelBase
{
	ModelRenderer handleBottom;
	ModelRenderer handle;
	ModelRenderer display;
	ModelRenderer keypad;
	ModelRenderer handleTop;
	ModelRenderer base;
	ModelRenderer sensorSupport;
	ModelRenderer sensor;

	public ModelMotionTracker()
	{
		textureWidth = 64;
		textureHeight = 32;
		handleBottom = new ModelRenderer(this, 20, 6);
		handleBottom.addBox(0F, 0F, 0F, 1, 1, 4);
		handleBottom.setRotationPoint(-0.5F, 2F, -0.5F);
		handleBottom.setTextureSize(64, 32);
		handleBottom.mirror = true;
		setRotation(handleBottom, 0F, 0F, 0F);
		handle = new ModelRenderer(this, 20, 12);
		handle.addBox(0F, 0F, 0F, 1, 5, 2);
		handle.setRotationPoint(-0.5F, -3F, -0.5F);
		handle.setTextureSize(64, 32);
		handle.mirror = true;
		setRotation(handle, 0F, 0F, 0F);
		display = new ModelRenderer(this, 22, 0);
		display.addBox(0F, 0F, 0F, 4, 3, 2);
		display.setRotationPoint(1F, -2.5F, 3.4F);
		display.setTextureSize(64, 32);
		display.mirror = true;
		setRotation(display, 0F, 0F, 0F);
		keypad = new ModelRenderer(this, 11, 6);
		keypad.addBox(0F, 0F, 3F, 1, 2, 3);
		keypad.setRotationPoint(-1.3F, -1F, 0.5F);
		keypad.setTextureSize(64, 32);
		keypad.mirror = true;
		setRotation(keypad, 0F, 0F, 0F);
		handleTop = new ModelRenderer(this, 9, 0);
		handleTop.addBox(0F, 0F, 0F, 2, 1, 4);
		handleTop.setRotationPoint(-1F, -4F, -1F);
		handleTop.setTextureSize(64, 32);
		handleTop.mirror = true;
		setRotation(handleTop, 0F, 0F, 0F);
		base = new ModelRenderer(this, 0, 7);
		base.addBox(0F, 0F, 0F, 2, 8, 3);
		base.setRotationPoint(-1F, -4F, 3F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		sensorSupport = new ModelRenderer(this, 0, 0);
		sensorSupport.addBox(0F, 0F, 0F, 2, 4, 2);
		sensorSupport.setRotationPoint(-1F, -2.1F, 6.3F);
		sensorSupport.setTextureSize(64, 32);
		sensorSupport.mirror = true;
		setRotation(sensorSupport, -0.5759587F, 0F, 0F);
		sensor = new ModelRenderer(this, 11, 12);
		sensor.addBox(0F, 0F, 0F, 2, 3, 2);
		sensor.setRotationPoint(-1F, -4F, 6F);
		sensor.setTextureSize(64, 32);
		sensor.mirror = true;
		setRotation(sensor, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		handleBottom.render(f5);
		handle.render(f5);
		display.render(f5);
		keypad.render(f5);
		handleTop.render(f5);
		base.render(f5);
		sensorSupport.render(f5);
		sensor.render(f5);
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
